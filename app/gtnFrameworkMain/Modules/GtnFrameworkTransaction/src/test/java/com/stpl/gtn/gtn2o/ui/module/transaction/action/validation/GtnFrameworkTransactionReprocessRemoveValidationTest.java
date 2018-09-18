/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameWorkTransactioneRecordTypeAction;
import com.stpl.gtn.gtn2o.ui.module.transaction.action.GtnUIFrameworkTransactionViewActionTest;
import com.stpl.gtn.gtn2o.ui.module.transaction.bean.GtnUIFrameworkTransactionComponentTypeListBean;
import com.stpl.gtn.gtn2o.ui.module.transaction.constants.GtnTransactionUIConstants;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.transaction.bean.GtnWSTransactionTableCheckAllBean;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import static org.mockito.Mockito.doReturn;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class})
public class GtnFrameworkTransactionReprocessRemoveValidationTest {
    
    public GtnFrameworkTransactionReprocessRemoveValidationTest() {
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
     * Test of configureParams method, of class GtnFrameworkTransactionReprocessRemoveValidation.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkTransactionReprocessRemoveValidation instance = new GtnFrameworkTransactionReprocessRemoveValidation();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkTransactionReprocessRemoveValidation.
     */
    @Test
    public void testDoAction() throws Exception {
//        System.out.println("doAction");
//        String componentId = "";
//        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
//        GtnFrameworkTransactionReprocessRemoveValidation instance = new GtnFrameworkTransactionReprocessRemoveValidation();
//        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        System.out.println("doAction");
        String componentId = "";
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkComponentData.class);
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        GtnUIFrameworkTransactionComponentTypeListBean componentTypeBean=new GtnUIFrameworkTransactionComponentTypeListBean();
        GtnWSTransactionTableCheckAllBean checkallBean=new GtnWSTransactionTableCheckAllBean();
        checkallBean.setCheckAll(true);
        Set<String> checkedIdSet=new HashSet<>();
        checkedIdSet.add("ds");
        checkedIdSet.add("d2s");
        checkedIdSet.add("ds4");
        checkedIdSet.add("d7s");
        checkedIdSet.add("8ds");        
        checkallBean.setCheckedIdSet(checkedIdSet);
        String portletName="fssfsf";
        GtnUIFrameworkTransactionComponentTypeListBean componentBean=new GtnUIFrameworkTransactionComponentTypeListBean();
        List<Object> obj=Arrays.asList("0",GtnTransactionUIConstants.REPROCESS,GtnTransactionUIConstants.RESULTS_PANEL_LAYOUT,componentBean.isOutBoundModule(),portletName);
        GtnUIFrameworkBaseComponent tableBaseComponent=Mockito.mock(GtnUIFrameworkBaseComponent.class);
        List<GtnWsRecordBean> dataTableRecordList=new ArrayList<>();
        GtnWsRecordBean bean=new GtnWsRecordBean();
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        bean.addProperties("gddgdg");
        dataTableRecordList.add(bean);
        
        GtnUIFrameworkComponentData tableComponent=Mockito.mock(GtnUIFrameworkComponentData.class);
        
               
        doReturn(tableComponent).when(tableBaseComponent).getComponentData();
        
        doReturn(checkallBean).when(tableComponent).getSharedPopupData();
        
        doReturn(dataTableRecordList).when(tableComponent).getDataTableRecordList();
           
        PowerMockito.when( GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
        
        gtnUIFrameWorkActionConfig.setActionParameterList(obj);
        GtnFrameworkTransactionReprocessRemoveValidation instance = new GtnFrameworkTransactionReprocessRemoveValidation();
        GtnFrameworkTransactionReprocessRemoveValidation in = Mockito.spy(instance);
        GtnUIFrameworkWebserviceResponse response=new GtnUIFrameworkWebserviceResponse();
        Object[] outBountData={"Gdg","ghdgd","gdgd","Dgdgd"};
        response.setOutBountData(outBountData);
        doReturn(response).when(in).callTransactionValidationService(Mockito.any(GtnUIFrameworkWebserviceRequest.class));
        in.doAction(componentId, gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkTransactionReprocessRemoveValidation.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkTransactionReprocessRemoveValidation instance = new GtnFrameworkTransactionReprocessRemoveValidation();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of getProcessName method, of class GtnFrameworkTransactionReprocessRemoveValidation.
     */
    @Test
    public void testGetProcessName() {
        System.out.println("getProcessName");
        GtnFrameworkTransactionReprocessRemoveValidation instance = new GtnFrameworkTransactionReprocessRemoveValidation();
    try{
        Method method = instance.getClass().getDeclaredMethod("getProcessName");
        method.setAccessible(true);
        method.invoke(instance);
         } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkTransactionReprocessRemoveValidationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
