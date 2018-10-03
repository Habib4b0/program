package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

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

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class})
public class GtnFrameworkCGrpAddAllActionTest {
	@Test
	public void testGtnFrameworkCGrpAddAllAction_1()
		throws Exception {
		GtnFrameworkCGrpAddAllAction result = new GtnFrameworkCGrpAddAllAction();
		assertNotNull(result);
	}

	@Test
	public void testConfigureParams()
		throws Exception {
		GtnFrameworkCGrpAddAllAction fixture = new GtnFrameworkCGrpAddAllAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testCreateInstance()
		throws Exception {
		GtnFrameworkCGrpAddAllAction fixture = new GtnFrameworkCGrpAddAllAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	
	@Test
	public void testDoAction()
		throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCGrpAddAllAction fixture = new GtnFrameworkCGrpAddAllAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
	
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnUIFrameworkGlobalUI.addSearchCriteriaList(gtnWebServiceSearchCriteriaList, "1");

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

		}
	
	@Test
	public void testDoAction_else()
		throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkCGrpAddAllAction fixture = new GtnFrameworkCGrpAddAllAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		
		List<String> fieldValuesList = new ArrayList<>();
		fieldValuesList.add("1");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValuesList);
		
		GtnWebServiceSearchCriteria gtnWebServiceSearchCriteria = new GtnWebServiceSearchCriteria();
		gtnWebServiceSearchCriteria.setFieldId("1");
		gtnWebServiceSearchCriteria.setFilterValue1("2");  

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		gtnWebServiceSearchCriteriaList.add(gtnWebServiceSearchCriteria);
		when(GtnUIFrameworkGlobalUI.addCurrentSearchCriteria(gtnUIFrameWorkActionConfig.getFieldValues())).thenReturn(gtnWebServiceSearchCriteriaList);

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);


		GtnUIFrameworkPagedTableLogic logic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn(logic).when(baseComponent).getLogicFromPagedDataTable();

	//	doNothing().when(logic).startSearchProcess(null, true);
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

		}
	
}