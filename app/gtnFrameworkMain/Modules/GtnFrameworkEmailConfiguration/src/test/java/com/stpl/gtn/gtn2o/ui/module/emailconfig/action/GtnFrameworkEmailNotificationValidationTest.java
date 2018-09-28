/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.emailconfig.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAlertAction;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.emailconfig.bean.GtnWsEMailConfigurationBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.emailconfig.GtnWsMailConfigurationResponse;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class,GtnUIFrameworkActionExecutor.class,MessageBox.class,GtnUIFrameWorkAlertAction.class})
public class GtnFrameworkEmailNotificationValidationTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    public GtnFrameworkEmailNotificationValidationTest() {
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
     * Test of validationForNotification method, of class GtnFrameworkEmailNotificationValidation.
     */
    @Test
    public void testValidationForNotification() throws Exception {
       
        System.out.println("doAction");
        String componentId = "V009";

        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class,MessageBox.class,GtnUIFrameWorkAlertAction.class);

        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

        List<Object> resetActionParams = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        resetActionParams.set(1, GtnFrameworkCommonConstants.ADD_PROCESS_NAME);
        resetActionParams.set(2, GtnFrameworkCommonConstants.ADD_EMAIL_TO);
        resetActionParams.set(3, GtnFrameworkCommonConstants.ADD_SUBJECT);
        resetActionParams.set(4, GtnFrameworkCommonConstants.ADD_EMAIL_CC);
        resetActionParams.set(5, GtnFrameworkCommonConstants.ADD_EMAIL_BODY);
        resetActionParams.set(6, GtnFrameworkCommonConstants.ADD_FAILURE_TO);
        resetActionParams.set(7, GtnFrameworkCommonConstants.ADD_SUBJECT1);
        resetActionParams.set(8, GtnFrameworkCommonConstants.ADD_EMAIL_CC1);
        resetActionParams.set(9, GtnFrameworkCommonConstants.ADD_EMAIL_BODY1);
        gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);

        GtnUIFrameworkBaseComponent object = Mockito.mock(GtnUIFrameworkBaseComponent.class);
        MessageBox messageBox = Mockito.mock(MessageBox.class);

        doReturn("").when(object).getStringFromField();

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(object);

        when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString())).thenReturn(object);

        doNothing().when(object).loadFieldValue(Mockito.anyObject());
        
        GtnFrameworkEmailNotificationValidation emailNotificationValidation=new GtnFrameworkEmailNotificationValidation();
      
        
        GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
        GtnWsMailConfigurationResponse gtnWsMailConfigurationResponse = new GtnWsMailConfigurationResponse();
        GtnWsEMailConfigurationBean eMailConfigurationBean = new GtnWsEMailConfigurationBean();
        List<Object[]> defaultDataLoad = new ArrayList<>();
        Object[] ob = {3, "SUPPORT@BPITECHNOLOGIES.COM", "SUCCESS - CFF_OUTBOUND_INTERFACE", "SUPPORT@BPITECHNOLOGIES.COM", "SUCCESSFUL PROCESS PLEASE VERIFY VALID AND INVALID", "COUNTS", "SUPPORT@BPITECHNOLOGIES.COM", "FAILURE - CFF_OUTBOUND_INTERFACE",
            "SUPPORT@BPITECHNOLOGIES.COM", "CFF_OUTBOUND_INTERFACE FAILURE", "CFF_OUTBOUND_INTERFACE FAILURE"};
        defaultDataLoad.add(ob);
        eMailConfigurationBean.setComboboxOnChangeDataLoad(defaultDataLoad);
        gtnWsMailConfigurationResponse.seteMailConfigurationBean(eMailConfigurationBean);
        gtnWsMailConfigurationResponse.setNotificationSuccess(true);
        gtnUIFrameworkWebserviceResponse.setGtnWsMailConfigurationResponse(gtnWsMailConfigurationResponse);
        thrown.expect(GtnFrameworkValidationFailedException.class);
        emailNotificationValidation.validationForNotification(eMailConfigurationBean, componentId);
    }
    
}
