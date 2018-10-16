package com.stpl.gtn.gtn2o.ui.module.itemgroup.action.validation;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

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
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGrpValidationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.itemgroupreponse.GtnWsItemGroupResponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class })
public class GtnFrameworkItemGrpCommonValidationActionTest {
	/**
	 * Run the GtnFrameworkItemGrpCommonValidationAction() constructor test.
	 *
	 * 
	 */
	@Test
	public void testGtnFrameworkItemGrpCommonValidationAction_1() throws Exception {
		GtnFrameworkItemGrpCommonValidationAction result = new GtnFrameworkItemGrpCommonValidationAction();
		assertNotNull(result);

	}

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnFrameworkItemGrpCommonValidationAction fixture = new GtnFrameworkItemGrpCommonValidationAction();
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

		GtnFrameworkItemGrpCommonValidationAction fixture = new GtnFrameworkItemGrpCommonValidationAction();
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
	public void testGtnFrameworkItemGrpCommonValidation_doAction_getImtdCount_isZero() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkItemGrpCommonValidationAction fixture = new GtnFrameworkItemGrpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("20516");
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn("20516");

		GtnFrameworkItemGrpCommonValidationAction action = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		doReturn(response).when(action).getResponse(Mockito.any());

		GtnWsItemGroupResponse itemGroupResponse = Mockito.mock(GtnWsItemGroupResponse.class);
		doReturn(itemGroupResponse).when(response).getGtnWsItemGroupResponse();

		GtnWsItemGrpValidationBean validationBean = Mockito.mock(GtnWsItemGrpValidationBean.class);
		doReturn(validationBean).when(itemGroupResponse).getGtnWsItemGrpValidationBean();

		// (validationBean.getImtdCount() == 0) = true // throws an error
		doReturn(0).when(validationBean).getImtdCount();

		try {
			action.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testGtnFrameworkItemGrpCommonValidation_doAction_getImtdCount_notZero() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkItemGrpCommonValidationAction fixture = new GtnFrameworkItemGrpCommonValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("20516");
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn("20516");

		GtnFrameworkItemGrpCommonValidationAction action = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse response = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		doReturn(response).when(action).getResponse(Mockito.any());

		GtnWsItemGroupResponse itemGroupResponse = Mockito.mock(GtnWsItemGroupResponse.class);
		doReturn(itemGroupResponse).when(response).getGtnWsItemGroupResponse();

		GtnWsItemGrpValidationBean validationBean = Mockito.mock(GtnWsItemGrpValidationBean.class);
		doReturn(validationBean).when(itemGroupResponse).getGtnWsItemGrpValidationBean();

		// (validationBean.getImtdCount() == 0) = false
		doReturn(1).when(validationBean).getImtdCount();

		action.doAction(componentId, gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testGtnFrameworkItemGrpCommonValidation_doAction_getResponse() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkItemGrpCommonValidationAction itemGrpCommonValidationAction = new GtnFrameworkItemGrpCommonValidationAction();
		GtnUIFrameworkWebserviceRequest updateWsRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		itemGrpCommonValidationAction.getResponse(updateWsRequest);

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