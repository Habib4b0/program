package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;


/**
 * @author praveen.kumar
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkBaseComponent.class, GtnUIFrameworkActionExecutor.class})
public class GtnUIFrameWorkDefaultResetValueActionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameWorkDefaultResetValueAction instance = new GtnUIFrameWorkDefaultResetValueAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameWorkDefaultResetValueAction instance = new GtnUIFrameWorkDefaultResetValueAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
	@Test
	public void testDoAction_1() throws GtnFrameworkGeneralException {
		GtnUIFrameWorkDefaultResetValueAction instance = new GtnUIFrameWorkDefaultResetValueAction();
		String componentId="";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4, 5);
		resetActionParams.set(2, null);
		resetActionParams.set(1, null);
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		instance.doAction(componentId, gtnUIFrameWorkActionConfig);
		
	}

	@Test
	public void testDoAction_2() throws GtnFrameworkGeneralException {
		GtnUIFrameWorkDefaultResetValueAction instance = new GtnUIFrameWorkDefaultResetValueAction();
		String componentId="";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class, GtnUIFrameWorkAction.class, GtnUIFrameworkActionExecutor.class);
		List<Object> resetActionParams = Arrays.asList(1, 2, 3, 4);
		resetActionParams.set(2, null);
		resetActionParams.set(1, null);
		gtnUIFrameWorkActionConfig.setActionParameterList(resetActionParams);
		instance.doAction(componentId, gtnUIFrameWorkActionConfig);
		
	}
}
