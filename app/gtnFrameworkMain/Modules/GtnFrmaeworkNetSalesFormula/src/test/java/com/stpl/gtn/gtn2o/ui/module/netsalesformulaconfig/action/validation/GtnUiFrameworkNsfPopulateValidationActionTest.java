package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.validation;

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
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;

/**
 * 
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class,
		GtnUIFrameworkActionExecutor.class })
public class GtnUiFrameworkNsfPopulateValidationActionTest {

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfPopulateValidationAction fixture = new GtnUiFrameworkNsfPopulateValidationAction();
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

		GtnUiFrameworkNsfPopulateValidationAction fixture = new GtnUiFrameworkNsfPopulateValidationAction();
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
	public void testGtnUiFrameworkNsfPopulateValidation_doAction_1() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkNSFCommonLogic.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfPopulateValidationAction fixture = new GtnUiFrameworkNsfPopulateValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add(true);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		when(GtnFrameworkNSFCommonLogic.confirmCheckRecord(Mockito.anyBoolean(), Mockito.anyString()))
				.thenReturn(false);

		GtnUIFrameworkBaseComponent baseComponent1 = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("netSalesFormulaAddView_selectedDeductionsmassUpdateDdlb"))
				.thenReturn(baseComponent1);
		doReturn("fieldDeductions").when(baseComponent1).getStringFromField();

		GtnUIFrameworkBaseComponent baseComponent2 = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("netSalesFormulaAddView_selectedDeductionsAddSubtractDdlb"))
				.thenReturn(baseComponent2);
		doReturn("valueDeductions").when(baseComponent2).getStringFromField();

		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testGtnUiFrameworkNsfPopulateValidation_doAction_isCheckTrue_elseIf_fieldDeductions_isEmpty()
			throws Exception {

		PowerMockito.mockStatic(GtnFrameworkNSFCommonLogic.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfPopulateValidationAction fixture = new GtnUiFrameworkNsfPopulateValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add(true);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		// isCheck = true
		when(GtnFrameworkNSFCommonLogic.confirmCheckRecord(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);

		GtnUIFrameworkBaseComponent baseComponent1 = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("netSalesFormulaAddView_selectedDeductionsmassUpdateDdlb"))
				.thenReturn(baseComponent1);

		// fieldDeductions.isEmpty() = true
		doReturn("").when(baseComponent1).getStringFromField();

		GtnUIFrameworkBaseComponent baseComponent2 = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("netSalesFormulaAddView_selectedDeductionsAddSubtractDdlb"))
				.thenReturn(baseComponent2);

		doReturn("valueDeductions").when(baseComponent2).getStringFromField();

		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testGtnUiFrameworkNsfPopulateValidation_doAction_isCheckTrue_elseIf_valueDeductions_isEmpty()
			throws Exception {

		PowerMockito.mockStatic(GtnFrameworkNSFCommonLogic.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfPopulateValidationAction fixture = new GtnUiFrameworkNsfPopulateValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add(true);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		// isCheck = true
		when(GtnFrameworkNSFCommonLogic.confirmCheckRecord(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);

		GtnUIFrameworkBaseComponent baseComponent1 = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("netSalesFormulaAddView_selectedDeductionsmassUpdateDdlb"))
				.thenReturn(baseComponent1);
		doReturn("fieldDeductions").when(baseComponent1).getStringFromField();

		GtnUIFrameworkBaseComponent baseComponent2 = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("netSalesFormulaAddView_selectedDeductionsAddSubtractDdlb"))
				.thenReturn(baseComponent2);
		// valueDeductions.isEmpty() = true
		doReturn("").when(baseComponent2).getStringFromField();

		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testGtnUiFrameworkNsfPopulateValidation_doAction_else() throws Exception {

		PowerMockito.mockStatic(GtnFrameworkNSFCommonLogic.class, GtnUIFrameworkGlobalUI.class,
				GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfPopulateValidationAction fixture = new GtnUiFrameworkNsfPopulateValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<>();
		actionParemeterList.add(0);
		actionParemeterList.add(true);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		// isCheck = true
		when(GtnFrameworkNSFCommonLogic.confirmCheckRecord(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);

		GtnUIFrameworkBaseComponent baseComponent1 = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("netSalesFormulaAddView_selectedDeductionsmassUpdateDdlb"))
				.thenReturn(baseComponent1);

		// fieldDeductions.isEmpty() = false
		doReturn("fieldDeductions").when(baseComponent1).getStringFromField();

		GtnUIFrameworkBaseComponent baseComponent2 = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("netSalesFormulaAddView_selectedDeductionsAddSubtractDdlb"))
				.thenReturn(baseComponent2);

		// valueDeductions.isEmpty() = false
		doReturn("valueDeductions").when(baseComponent2).getStringFromField();

		// So it will go to else block
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