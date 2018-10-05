/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.processmonitor.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import de.steinwedel.messagebox.MessageBox;
import java.util.Arrays;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
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
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class})
public class GtnFrameworkTableClickActionTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    public GtnFrameworkTableClickActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkTableClickAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkTableClickAction instance = new GtnFrameworkTableClickAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of doAction method, of class GtnFrameworkTableClickAction.
     */
    @Test
    public void testDoAction() throws Exception {
        System.out.println("doAction");
        String componentId = "V009";
    
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,GtnUIFrameWorkAlertAction.class,MessageBox.class); 
   
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig() ;
        
        
        GtnWsRecordBean tableBean = new GtnWsRecordBean();
			tableBean.addProperties("28sep18");
			tableBean.addProperties("Both");
			tableBean.addProperties("Holiday Schedule");
			tableBean.addProperties(new Date());
			tableBean.addProperties("John Smith");
			tableBean.addProperties(248);
			tableBean.addProperties(7307);
			tableBean.addProperties(15L);
			tableBean.addProperties(15L);
			tableBean.addProperties(null);
			tableBean.addProperties(8);
                        tableBean.addProperties(null);
                        tableBean.addProperties(null);
                        tableBean.addProperties(1);
                        tableBean.addProperties(30);
                        tableBean.addProperties(null);
                        tableBean.addProperties(null);
                        
                        
        tableBean.setRecordHeader(Arrays.asList("processName","processType","slaCalenderMasterSid","modifiedDate","modifiedBy"));
        
        gtnUIFrameWorkActionConfig.addActionParameter(componentId);
        gtnUIFrameWorkActionConfig.addActionParameter("searchResultTable");
        gtnUIFrameWorkActionConfig.addActionParameter("processName");
        gtnUIFrameWorkActionConfig.addActionParameter(true);
        gtnUIFrameWorkActionConfig.addActionParameter(tableBean);
        
       
        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        doNothing().when(object).setComponentEnable(Mockito.anyBoolean());
        doNothing().when(object).setCaption(Mockito.anyString());
        doNothing().when(object).setVisible(Mockito.anyBoolean());
        
        doReturn("addProcessName").when(object).getStringFromField();
        doReturn("addProcess").when(object).getCaptionFromComboBox();
        doReturn(new Date()).when(object).getDateFromDateField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object); 
        
        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(object);
        
        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        
        GtnFrameworkTableClickAction instance=new GtnFrameworkTableClickAction();
        instance.doAction(componentId, gtnUIFrameWorkActionConfig);
        assertFalse(componentId.isEmpty());
    }

    /**
     * Test of createInstance method, of class GtnFrameworkTableClickAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkTableClickAction instance = new GtnFrameworkTableClickAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
