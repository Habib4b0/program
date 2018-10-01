package com.stpl.gtn.gtn2o.ui.module.ifp.action.duallistboxaction;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFrameworkIfpValueChangeManager;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkIfpMoveAllRightActionTest {

	@Test
	public void testConfigureParams()
		throws Exception {
		
		GtnFrameworkIfpMoveAllRightAction fixture = new GtnFrameworkIfpMoveAllRightAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance()
		throws Exception {
		
		GtnFrameworkIfpMoveAllRightAction fixture = new GtnFrameworkIfpMoveAllRightAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testDoAction()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpMoveAllRightAction fixture = new GtnFrameworkIfpMoveAllRightAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(gtnUIFrameworkPagedTableLogic).when(baseComponent).getLogicFromPagedDataTable();
		
		List<String> fieldValueLust = new ArrayList<>();
		fieldValueLust.add("0");
		fieldValueLust.add("1");
		fieldValueLust.add("2");
		fieldValueLust.add("3");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValueLust);
		
		GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
		searchCriteria.setExpression("");
		searchCriteria.setFieldId("0");
		searchCriteria.setFilter(true);
		searchCriteria.setFilterValue1("1");
		searchCriteria.setFilterValue2("2");
		searchCriteria.setFilterValue3("3");
		List<GtnWebServiceSearchCriteria> searchCriteriaList = new ArrayList<>();
		searchCriteriaList.add(searchCriteria);
		
		doReturn(searchCriteriaList).when(gtnUIFrameworkPagedTableLogic).getCurrentSearchCriteria();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn(5).when(baseComponent).getExtPagedTableSize();
		
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(baseComponent).getExtPagedTable();
		
		callMoveAllRightDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testDoActio_getExtPagedTableSize_isZero()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpMoveAllRightAction fixture = new GtnFrameworkIfpMoveAllRightAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(gtnUIFrameworkPagedTableLogic).when(baseComponent).getLogicFromPagedDataTable();
		
		List<String> fieldValueLust = new ArrayList<>();
		fieldValueLust.add("0");
		fieldValueLust.add("1");
		fieldValueLust.add("2");
		fieldValueLust.add("3");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValueLust);
		
		GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
		searchCriteria.setExpression("");
		searchCriteria.setFieldId("0");
		searchCriteria.setFilter(true);
		searchCriteria.setFilterValue1("1");
		searchCriteria.setFilterValue2("2");
		searchCriteria.setFilterValue3("3");
		List<GtnWebServiceSearchCriteria> searchCriteriaList = new ArrayList<>();
		searchCriteriaList.add(searchCriteria);
		
		doReturn(searchCriteriaList).when(gtnUIFrameworkPagedTableLogic).getCurrentSearchCriteria();
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		doReturn(0).when(baseComponent).getExtPagedTableSize();
		
		callMoveAllRightDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}
	
	public void callMoveAllRightDoAction(GtnFrameworkIfpMoveAllRightAction fixture, String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
		
		try{
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}