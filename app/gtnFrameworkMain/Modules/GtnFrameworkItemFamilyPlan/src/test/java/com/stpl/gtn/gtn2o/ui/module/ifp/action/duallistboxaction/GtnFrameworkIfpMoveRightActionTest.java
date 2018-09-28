package com.stpl.gtn.gtn2o.ui.module.ifp.action.duallistboxaction;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkIfpMoveRightActionTest {


	@Test
	public void testConfigureParams()
		throws Exception {
		
		GtnFrameworkIfpMoveRightAction fixture = new GtnFrameworkIfpMoveRightAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance()
		throws Exception {
		
		GtnFrameworkIfpMoveRightAction fixture = new GtnFrameworkIfpMoveRightAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testGtnFrameworkIfpMoveRight_doAction()
		throws Exception {

		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpMoveRightAction fixture = new GtnFrameworkIfpMoveRightAction();
		String componentId = "";
		
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		List<Object> propertiesList = new ArrayList<>();
		propertiesList.add("0");
		propertiesList.add("1");
		propertiesList.add("2");
		propertiesList.add("3");
		propertiesList.add("4");
		propertiesList.add("5");
		propertiesList.add("6");
		propertiesList.add("7");
		propertiesList.add("8");
		
		
		GtnWsRecordBean gtnWsRecordBean = new GtnWsRecordBean();
		gtnWsRecordBean.setParentFlag(true); 
		gtnWsRecordBean.setPropertyValueByIndex(0, "0");
		gtnWsRecordBean.setPropertyValueByIndex(1, "1");
		gtnWsRecordBean.setProperties(propertiesList);
		
		Set<GtnWsRecordBean> gtnWsRecordBeansSet = new HashSet<>();
		gtnWsRecordBeansSet.add(gtnWsRecordBean);
		doReturn(gtnWsRecordBeansSet).when(baseComponent).getValueFromMultiSelectPagedDataTable();
		
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(baseComponent).getExtPagedTable();
		
		GtnUIFrameworkPagedTableLogic gtnUIFrameworkPagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(gtnUIFrameworkPagedTableLogic).when(baseComponent).getLogicFromPagedDataTable();
		
		callMoveRightDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
		
	}
	
	@Test
	public void testGtnFrameworkIfpMoveRight_doAction_dtoSets_isNull()
		throws Exception {
		
		PowerMockito.mockStatic(GtnFrameworkIfpValueChangeManager.class, GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpMoveRightAction fixture = new GtnFrameworkIfpMoveRightAction();
		String componentId = "";
		
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());
		GtnFrameworkIfpValueChangeManager.setValueChangeAllowed(false);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		doReturn(null).when(baseComponent).getValueFromMultiSelectPagedDataTable();
		
		callMoveRightDoAction(fixture, componentId, gtnUIFrameWorkActionConfig);
	}
	
	public void callMoveRightDoAction(GtnFrameworkIfpMoveRightAction fixture, String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
		
		try{
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		}catch(Exception e)
		{
			System.out.println();e.getMessage();
		}
		
	}
	
}