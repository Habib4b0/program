package com.stpl.gtn.gtn2o.ui.module.itemgroup.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

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
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkItemGrpAddAllActionTest {
	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnFrameworkItemGrpAddAllAction fixture = new GtnFrameworkItemGrpAddAllAction();
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

		GtnFrameworkItemGrpAddAllAction fixture = new GtnFrameworkItemGrpAddAllAction();
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
	public void testGtnFrameworkItemGrpAddAll_doAction_searchCriteriaList_notEmpty() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkItemGrpAddAllAction fixture = new GtnFrameworkItemGrpAddAllAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<String> vaadinFieldValues = new ArrayList<>();

		gtnUIFrameWorkActionConfig.setFieldValues(vaadinFieldValues);

		GtnWebServiceSearchCriteria currentSearchCriteria = new GtnWebServiceSearchCriteria();
		List<GtnWebServiceSearchCriteria> searchCriteriaList = new ArrayList<GtnWebServiceSearchCriteria>();
		searchCriteriaList.add(currentSearchCriteria);

		when(GtnUIFrameworkGlobalUI.addCurrentSearchCriteria(vaadinFieldValues)).thenReturn(searchCriteriaList);

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		GtnUIFrameworkPagedTableLogic logic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpSelectedResultTable")).thenReturn(baseComponent);
		doReturn(logic).when(baseComponent).getLogicFromPagedDataTable();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnFrameworkItemGrpAddAll_doAction_searchCriteriaList_isEmpty() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkItemGrpAddAllAction fixture = new GtnFrameworkItemGrpAddAllAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<String> vaadinFieldValues = new ArrayList<>();

		gtnUIFrameWorkActionConfig.setFieldValues(vaadinFieldValues);

		// searchCriteriaList is empty
		List<GtnWebServiceSearchCriteria> searchCriteriaList = new ArrayList<GtnWebServiceSearchCriteria>();

		when(GtnUIFrameworkGlobalUI.addCurrentSearchCriteria(vaadinFieldValues)).thenReturn(searchCriteriaList);

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnFrameworkItemGrpAddAll_doAction_searchCriteriaList_notEmpty_catchBlock() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkItemGrpAddAllAction fixture = new GtnFrameworkItemGrpAddAllAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<String> vaadinFieldValues = new ArrayList<>();

		gtnUIFrameWorkActionConfig.setFieldValues(vaadinFieldValues);

		GtnWebServiceSearchCriteria currentSearchCriteria = new GtnWebServiceSearchCriteria();
		List<GtnWebServiceSearchCriteria> searchCriteriaList = new ArrayList<GtnWebServiceSearchCriteria>();
		searchCriteriaList.add(currentSearchCriteria);

		when(GtnUIFrameworkGlobalUI.addCurrentSearchCriteria(vaadinFieldValues)).thenReturn(searchCriteriaList);

		// No GtnUIFrameworkPagedTableLogic is initialized, cause an exception

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