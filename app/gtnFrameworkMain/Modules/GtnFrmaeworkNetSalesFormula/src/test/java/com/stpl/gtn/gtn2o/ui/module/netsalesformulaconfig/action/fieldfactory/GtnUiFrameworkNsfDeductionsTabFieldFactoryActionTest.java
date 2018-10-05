package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.fieldfactory;

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
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFCommonLogic;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

/**
 * 
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class })
public class GtnUiFrameworkNsfDeductionsTabFieldFactoryActionTest {
	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfDeductionsTabFieldFactoryAction fixture = new GtnUiFrameworkNsfDeductionsTabFieldFactoryAction();
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

		GtnUiFrameworkNsfDeductionsTabFieldFactoryAction fixture = new GtnUiFrameworkNsfDeductionsTabFieldFactoryAction();
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
	public void testGtnUiFrameworkNsfDeductionsTabFieldFactory_doAction_1() throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);
		GtnUiFrameworkNsfDeductionsTabFieldFactoryAction fixture = new GtnUiFrameworkNsfDeductionsTabFieldFactoryAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinFieldFactoryComponentData(Mockito.anyString())).thenReturn(componentData);
		
		GtnUIFrameworkActionParameter actionParameter = Mockito.mock(GtnUIFrameworkActionParameter.class);
		doReturn(actionParameter).when(componentData).getActionParameter();
		
		GtnWsRecordBean itemId = Mockito.mock(GtnWsRecordBean.class);
		
		doReturn(itemId).when(actionParameter).getItemId();
		
		List<Object> properties = new ArrayList<>();
		properties.add(0);
		properties.add(1);  
		properties.add(2);
		
		List<Object> recordHeader = new ArrayList<>();
		recordHeader.add(0);
		recordHeader.add(1);
		
		doReturn(properties).when(itemId).getRecordHeader();
		doReturn(properties).when(itemId).getProperties();  
		
		when(GtnFrameworkNSFCommonLogic.updateField("", new Object(), true, 1, true, "", true)).thenReturn(true);
		
		// if(GtnFrameworkCommonConstants.CHECK_RECORD_ID.equals(actionParam.getPropertyId()) = true
		doReturn("checkRecordId").when(actionParameter).getPropertyId();
		doReturn("").when(actionParameter).getTableComponentId();
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		GtnUIFrameworkPagedTableLogic pagedTableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(pagedTableLogic).when(baseComponent).getLogicFromPagedDataTable();
		
		doReturn(new Object()).when(actionParameter).getCurrentValue();
		
		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);

	}
	
	@Test
	public void testGtnUiFrameworkNsfDeductionsTabFieldFactory_doAction_2() throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkNSFCommonLogic.class);
		GtnUiFrameworkNsfDeductionsTabFieldFactoryAction fixture = new GtnUiFrameworkNsfDeductionsTabFieldFactoryAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinFieldFactoryComponentData(Mockito.anyString())).thenReturn(componentData);
		
		GtnUIFrameworkActionParameter actionParameter = Mockito.mock(GtnUIFrameworkActionParameter.class);
		doReturn(actionParameter).when(componentData).getActionParameter();
		
		GtnWsRecordBean itemId = Mockito.mock(GtnWsRecordBean.class);
		
		doReturn(itemId).when(actionParameter).getItemId();
		
		List<Object> properties = new ArrayList<>();
		properties.add(0);
		properties.add(1);  
		properties.add(2);
		
		List<Object> recordHeader = new ArrayList<>();
		recordHeader.add(0);
		recordHeader.add(1);
		
		doReturn(properties).when(itemId).getRecordHeader();
		doReturn(properties).when(itemId).getProperties();  
		
		when(GtnFrameworkNSFCommonLogic.updateField("", new Object(), true, 1, true, "", true)).thenReturn(true);
		
		// if(GtnFrameworkCommonConstants.CHECK_RECORD_ID.equals(actionParam.getPropertyId()) = false
		doReturn("else").when(actionParameter).getPropertyId();
		
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