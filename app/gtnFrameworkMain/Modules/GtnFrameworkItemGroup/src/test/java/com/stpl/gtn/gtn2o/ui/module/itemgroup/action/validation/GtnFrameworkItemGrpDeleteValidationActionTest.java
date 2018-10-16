package com.stpl.gtn.gtn2o.ui.module.itemgroup.action.validation;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkItemGrpDeleteValidationActionTest {
	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testConfigureParams()
		throws Exception {
		
		GtnFrameworkItemGrpDeleteValidationAction fixture = new GtnFrameworkItemGrpDeleteValidationAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	/**
	 * Run the GtnUIFrameWorkAction createInstance() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testCreateInstance()
		throws Exception {
		
		GtnFrameworkItemGrpDeleteValidationAction fixture = new GtnFrameworkItemGrpDeleteValidationAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	/**
	 * Run the void doAction(String,GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGtnFrameworkItemGrpDeleteValidation_doAction_gtnWsRecordBean_notNull()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);
		
		GtnFrameworkItemGrpDeleteValidationAction fixture = new GtnFrameworkItemGrpDeleteValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		GtnWsRecordBean gtnWsRecordBean = Mockito.mock(GtnWsRecordBean.class);
		// (gtnWsRecordBean == null) = false
		doReturn(gtnWsRecordBean).when(baseComponent).getValueFromPagedDataTable();
		
		doReturn("itemGrpName").when(gtnWsRecordBean).getPropertyValueByIndex(0);
		
		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@Test
	public void testGtnFrameworkItemGrpDeleteValidation_doAction_gtnWsRecordBean_iSNull()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);
		
		GtnFrameworkItemGrpDeleteValidationAction fixture = new GtnFrameworkItemGrpDeleteValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		// (gtnWsRecordBean == null) = true
		doReturn(null).when(baseComponent).getValueFromPagedDataTable();
		
		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

}