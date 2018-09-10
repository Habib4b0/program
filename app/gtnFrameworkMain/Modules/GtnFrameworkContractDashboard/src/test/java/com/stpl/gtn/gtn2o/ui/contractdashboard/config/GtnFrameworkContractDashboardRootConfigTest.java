/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.when;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})

public class GtnFrameworkContractDashboardRootConfigTest {
    
    public GtnFrameworkContractDashboardRootConfigTest() {
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
     * Test of getContractDashboardRootConfig method, of class GtnFrameworkContractDashboardRootConfig.
     */
    @Test
    public void testGetContractDashboardRootConfig() {
        System.out.println("getContractDashboardRootConfig");
        GtnFrameworkContractDashboardRootConfig instance = new GtnFrameworkContractDashboardRootConfig();
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
        List<List<String>> paramList = new ArrayList<>();
        paramList.add(Arrays.asList("1","2"));
        when(GtnUIFrameworkGlobalUI.getPageParameterList()).thenReturn(paramList);       
        GtnUIFrameworkRootConfig result = instance.getContractDashboardRootConfig();
        assertFalse(result.getGtnViewConfigList().isEmpty());
    }
    
}
