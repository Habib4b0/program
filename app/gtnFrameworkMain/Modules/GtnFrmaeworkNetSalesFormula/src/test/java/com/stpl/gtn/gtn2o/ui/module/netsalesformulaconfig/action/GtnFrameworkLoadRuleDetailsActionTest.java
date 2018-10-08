package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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
public class GtnFrameworkLoadRuleDetailsActionTest {
	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnFrameworkLoadRuleDetailsAction fixture = new GtnFrameworkLoadRuleDetailsAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	/**
	 * Run the GtnUIFrameWorkAction createInstance() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testCreateInstance() throws Exception {

		GtnFrameworkLoadRuleDetailsAction fixture = new GtnFrameworkLoadRuleDetailsAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	/**
	 * Run the void doAction(String,GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testDoAction_1() throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);
		
		GtnFrameworkLoadRuleDetailsAction fixture = new GtnFrameworkLoadRuleDetailsAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParameterList = new ArrayList<Object>();
		actionParameterList.add(0);
		actionParameterList.add(componentId);
		actionParameterList.add("actionParameter");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParameterList);

		GtnUIFrameworkBaseComponent formulaTypeSearchTableComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(formulaTypeSearchTableComponent);
		
		GtnWsRecordBean recordBean = Mockito.mock(GtnWsRecordBean.class);
		recordBean.setPropertyValueByIndex(1, "1");
		recordBean.setPropertyValueByIndex(2, "systemId");
		
		doReturn(recordBean).when(formulaTypeSearchTableComponent).getValueFromComponent();
		
		List<String> fieldValueList = new ArrayList<>();
		fieldValueList.add("0");
		fieldValueList.add("1");
		
		List<String> fieldDescriptionList = new ArrayList<>();
		fieldDescriptionList.add("0");
		fieldDescriptionList.add("1");
		
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValueList);
		gtnUIFrameWorkActionConfig.setFieldDescription(fieldDescriptionList);
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *             if the initialization fails for some reason
	 *
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 *
	 * 
	 */
	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

}