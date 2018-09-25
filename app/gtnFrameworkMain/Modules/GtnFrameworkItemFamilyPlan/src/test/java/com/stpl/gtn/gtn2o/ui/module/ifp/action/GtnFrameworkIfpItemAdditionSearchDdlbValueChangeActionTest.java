package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class})
public class GtnFrameworkIfpItemAdditionSearchDdlbValueChangeActionTest {

	@Test
	public void testConfigureParams_1()
		throws Exception {
		GtnFrameworkIfpItemAdditionSearchDdlbValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchDdlbValueChangeAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testCreateInstance_1()
		throws Exception {
		GtnFrameworkIfpItemAdditionSearchDdlbValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchDdlbValueChangeAction();

		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	@Test
	public void testGtnFrameworkIfpItemAdditionSearchDdlbValueChangeAction()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpItemAdditionSearchDdlbValueChangeAction fixture = new GtnFrameworkIfpItemAdditionSearchDdlbValueChangeAction();
		String componentId = "";
		
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		List<String> fieldValues = new ArrayList<String>();
		fieldValues.add("cid");
		gtnUIFrameWorkActionConfig.setFieldValues(fieldValues);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent); 
		
		doReturn("caption").when(baseComponent).getCaptionFromComboBox();
		doNothing().when(baseComponent).loadFieldValue("caption");
		
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

