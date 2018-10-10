package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.asi.ui.extfilteringtable.ExtFilterTable;
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
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;

/**
 * 
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class })
public class GtnUiFrameworkEnableDisableActionTest {

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkEnableDisableAction fixture = new GtnUiFrameworkEnableDisableAction();
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

		GtnUiFrameworkEnableDisableAction fixture = new GtnUiFrameworkEnableDisableAction();
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
	public void testGtnUiFrameworkEnableDisable_doAction_isEnable_true() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkEnableDisableAction fixture = new GtnUiFrameworkEnableDisableAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		boolean isEnable = true;
		boolean isAdd = true;

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		actionParemeterList.add(isEnable);
		actionParemeterList.add(isAdd);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		ExtFilterTable extFilterTable = Mockito.mock(ExtFilterTable.class);
		doReturn(extFilterTable).when(baseComponent).getExtFilterTable();

		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("view");

		Object[] objects = { "indicator" };
		doReturn(objects).when(extFilterTable).getVisibleColumns();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkEnableDisable_doAction_isEnable_false() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkEnableDisableAction fixture = new GtnUiFrameworkEnableDisableAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		boolean isEnable = false;
		boolean isAdd = true;

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		actionParemeterList.add(isEnable);
		actionParemeterList.add(isAdd);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		ExtFilterTable extFilterTable = Mockito.mock(ExtFilterTable.class);
		doReturn(extFilterTable).when(baseComponent).getExtFilterTable();

		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("view");

		Object[] objects = { "not indicator" };
		doReturn(objects).when(extFilterTable).getVisibleColumns();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkEnableDisable_doAction_isAdd_false_1() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkEnableDisableAction fixture = new GtnUiFrameworkEnableDisableAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		boolean isEnable = true;
		boolean isAdd = false;

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		actionParemeterList.add(isEnable);
		actionParemeterList.add(isAdd);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		// forMulaType = Contract
		doReturn("Contract").when(baseComponent).getCaptionFromComboBox();

		ExtFilterTable extFilterTable = Mockito.mock(ExtFilterTable.class);
		doReturn(extFilterTable).when(baseComponent).getExtFilterTable();

		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("view");

		Object[] objects = { "not indicator" };
		doReturn(objects).when(extFilterTable).getVisibleColumns();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkEnableDisable_doAction_isAdd_false_2() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkEnableDisableAction fixture = new GtnUiFrameworkEnableDisableAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		boolean isEnable = true;
		boolean isAdd = false;

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		actionParemeterList.add(isEnable);
		actionParemeterList.add(isAdd);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		// forMulaType = Not Contract So, else block will be executed
		doReturn("NotContract").when(baseComponent).getCaptionFromComboBox();

		ExtFilterTable extFilterTable = Mockito.mock(ExtFilterTable.class);
		doReturn(extFilterTable).when(baseComponent).getExtFilterTable();

		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("view");

		Object[] objects = { "not indicator" };
		doReturn(objects).when(extFilterTable).getVisibleColumns();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkEnableDisable_doAction_isAdd_false_3() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkEnableDisableAction fixture = new GtnUiFrameworkEnableDisableAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		boolean isEnable = false;
		boolean isAdd = false;

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		actionParemeterList.add(isEnable);
		actionParemeterList.add(isAdd);

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		// forMulaType = Contract
		doReturn("Contract").when(baseComponent).getCaptionFromComboBox();

		ExtFilterTable extFilterTable = Mockito.mock(ExtFilterTable.class);
		doReturn(extFilterTable).when(baseComponent).getExtFilterTable();

		ExtCustomTable extCustomTable = Mockito.mock(ExtCustomTable.class);
		doReturn(extCustomTable).when(baseComponent).getExtCustomTable();

		String[] editHeaders = new String[] { "0", "1" };
		doReturn(editHeaders).when(extCustomTable).getColumnHeaders();

		Object[] editColumns = new Object[] { 0, 1 };
		doReturn(editColumns).when(extCustomTable).getVisibleColumns();

		// if(checkMode.equals("view")) = false
		when(GtnUIFrameworkGlobalUI.getSessionProperty("mode")).thenReturn("not view");

		Object[] objects = { "not indicator" };
		doReturn(objects).when(extFilterTable).getVisibleColumns();

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