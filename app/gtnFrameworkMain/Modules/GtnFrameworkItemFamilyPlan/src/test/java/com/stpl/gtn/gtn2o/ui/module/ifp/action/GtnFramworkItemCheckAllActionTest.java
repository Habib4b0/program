package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class})
public class GtnFramworkItemCheckAllActionTest {

	@Test
	public void testConfigureParams()
		throws Exception {
		
		GtnFramworkItemCheckAllAction fixture = new GtnFramworkItemCheckAllAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testCreateInstance()
		throws Exception {
		
		GtnFramworkItemCheckAllAction fixture = new GtnFramworkItemCheckAllAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}
 
	@Test
	public void testGtnFramworkItemCheckAllAction()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFramworkItemCheckAllAction fixture = new GtnFramworkItemCheckAllAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent tableBaseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnIFamilyPlanCommonUpdateBean ifpUpdateBean = Mockito.mock(GtnIFamilyPlanCommonUpdateBean.class);
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(tableBaseComponent);
		doReturn(true).when(tableBaseComponent).getTableColumnCheckboxValue(Mockito.anyString()); 
		
		doNothing().when(ifpUpdateBean).setCheckAll(Mockito.anyBoolean());
		doNothing().when(ifpUpdateBean).setColumnName(Mockito.anyString());
		doNothing().when(ifpUpdateBean).setValue(new Object());
		
		GtnWsGeneralRequest generalWSRequest = Mockito.mock(GtnWsGeneralRequest.class);
		
		PowerMockito.when(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")).thenReturn("");
		doNothing().when(generalWSRequest).setSessionId(""); 
		 
		List<GtnWsRecordBean> itemsFromDataTablel = new ArrayList<GtnWsRecordBean>();
		GtnWsRecordBean e = Mockito.mock(GtnWsRecordBean.class);
		itemsFromDataTablel.add(e);
		doReturn(itemsFromDataTablel).when(tableBaseComponent).getItemsFromDataTable();
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}

	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

}