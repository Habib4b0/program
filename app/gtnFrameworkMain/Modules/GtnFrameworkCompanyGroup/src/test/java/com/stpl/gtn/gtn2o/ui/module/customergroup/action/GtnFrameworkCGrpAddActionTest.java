package com.stpl.gtn.gtn2o.ui.module.customergroup.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
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
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpInformationBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkCGrpAddActionTest {
	
	@Test
	public void testGtnFrameworkCGrpAddAction(){
		GtnFrameworkCGrpAddAction result = new GtnFrameworkCGrpAddAction();
		assertNotNull(result);
		// add additional test code here
	}

	
	@Test
	public void testConfigureParams()
		throws Exception {
		GtnFrameworkCGrpAddAction fixture = new GtnFrameworkCGrpAddAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);

	
	}

	
	@Test
	public void testCreateInstance(){
		GtnFrameworkCGrpAddAction fixture = new GtnFrameworkCGrpAddAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	
	@Test
	public void testDoAction_1()
		throws Exception {
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUIFrameworkGlobalUI.addSessionProperty("", new Object());

		GtnFrameworkCGrpAddAction fixture = new GtnFrameworkCGrpAddAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		GtnCompanyGrpInformationBean info = new GtnCompanyGrpInformationBean();
		info.setCompanyGrpName("1");
		info.setCompanyGrpNo("2");
		info.setCompanyGrpDesc("3");
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString(),Mockito.anyString())).thenReturn(baseComponent);
		
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		ExtPagedTable extPagedTable = Mockito.mock(ExtPagedTable.class);
		doReturn(extPagedTable).when(baseComponent).getExtPagedTable();

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