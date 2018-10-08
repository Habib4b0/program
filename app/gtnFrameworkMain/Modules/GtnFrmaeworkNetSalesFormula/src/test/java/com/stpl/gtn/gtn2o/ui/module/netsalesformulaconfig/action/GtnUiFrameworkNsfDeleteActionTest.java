package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

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
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class })
public class GtnUiFrameworkNsfDeleteActionTest {
	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testConfigureParams()
		throws Exception {
		
		GtnUiFrameworkNsfDeleteAction fixture = new GtnUiFrameworkNsfDeleteAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	/**
	 * Run the GtnUIFrameWorkAction createInstance() method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testCreateInstance()
		throws Exception {
		
		GtnUiFrameworkNsfDeleteAction fixture = new GtnUiFrameworkNsfDeleteAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	/**
	 * Run the void doAction(String,GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	
	 */
	@Test
	public void testGtnUiFrameworkNsfDelete_doAction_getValueFromService_true()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);
		
		GtnUiFrameworkNsfDeleteAction fixture = new GtnUiFrameworkNsfDeleteAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		GtnWsRecordBean valueFromPagedDataTable = Mockito.mock(GtnWsRecordBean.class);
		doReturn(valueFromPagedDataTable).when(baseComponent).getValueFromPagedDataTable();
		doReturn(new Integer("2")).when(valueFromPagedDataTable).getPropertyValue("systemId");  
		
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnUiFrameworkNsfDeleteAction gtnUiFrameworkNsfDeleteAction = Mockito.spy(fixture);
		
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		// getValueFromService(nsfInfoBean) will return true
		gtnWsGeneralResponse.setSucess(true);
		response.setGtnWsGeneralResponse(gtnWsGeneralResponse);  
		
		doReturn(response).when(gtnUiFrameworkNsfDeleteAction).getResponse(Mockito.any());
		doReturn(gtnWsGeneralResponse).when(response).getGtnWsGeneralResponse();
		
		gtnUiFrameworkNsfDeleteAction.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testGtnUiFrameworkNsfDelete_doAction_getValueFromService_false()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);
		
		GtnUiFrameworkNsfDeleteAction fixture = new GtnUiFrameworkNsfDeleteAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		GtnWsRecordBean valueFromPagedDataTable = Mockito.mock(GtnWsRecordBean.class);
		doReturn(valueFromPagedDataTable).when(baseComponent).getValueFromPagedDataTable();
		doReturn(new Integer("2")).when(valueFromPagedDataTable).getPropertyValue("systemId");  
		
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnUiFrameworkNsfDeleteAction gtnUiFrameworkNsfDeleteAction = Mockito.spy(fixture);
		
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		// getValueFromService(nsfInfoBean) will return false
		gtnWsGeneralResponse.setSucess(false);
		response.setGtnWsGeneralResponse(gtnWsGeneralResponse);  
		
		doReturn(response).when(gtnUiFrameworkNsfDeleteAction).getResponse(Mockito.any());
		doReturn(gtnWsGeneralResponse).when(response).getGtnWsGeneralResponse();
		
		gtnUiFrameworkNsfDeleteAction.doAction(componentId, gtnUIFrameWorkActionConfig);
	}
	
	@Test
	public void testGtnUiFrameworkNsfDelete_doAction_getValueFromService_catchBlock()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);
		
		GtnUiFrameworkNsfDeleteAction fixture = new GtnUiFrameworkNsfDeleteAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		
		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");

		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);
		
		GtnWsRecordBean valueFromPagedDataTable = Mockito.mock(GtnWsRecordBean.class);
		doReturn(valueFromPagedDataTable).when(baseComponent).getValueFromPagedDataTable();
		doReturn(new Integer("2")).when(valueFromPagedDataTable).getPropertyValue("systemId");  
		
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		GtnUiFrameworkNsfDeleteAction gtnUiFrameworkNsfDeleteAction = Mockito.spy(fixture);
		
		GtnWsGeneralResponse gtnWsGeneralResponse = new GtnWsGeneralResponse();
		gtnWsGeneralResponse.setSucess(true);
		response.setGtnWsGeneralResponse(gtnWsGeneralResponse);  
		
		doReturn(response).when(gtnUiFrameworkNsfDeleteAction).getResponse(Mockito.any());
		// No need to return general response so it will throw null pointer exception
		
		try {
			gtnUiFrameworkNsfDeleteAction.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGtnUiFrameworkNsfDelete_doAction_getResponse() {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		GtnUiFrameworkNsfDeleteAction gtnUiFrameworkNsfDeleteAction = new GtnUiFrameworkNsfDeleteAction();
		GtnUIFrameworkWebserviceRequest gtnRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		gtnUiFrameworkNsfDeleteAction.getResponse(gtnRequest);
	}

	/**  
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

}