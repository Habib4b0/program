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
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
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
public class GtnFrameworkItemGrpNameValidationActionTest {

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnFrameworkItemGrpNameValidationAction fixture = new GtnFrameworkItemGrpNameValidationAction();
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

		GtnFrameworkItemGrpNameValidationAction fixture = new GtnFrameworkItemGrpNameValidationAction();
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
	public void testGtnFrameworkItemGrpNameValidation_doAction_if_1() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkItemGrpNameValidationAction fixture = new GtnFrameworkItemGrpNameValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponentName = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent baseComponentNo = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent baseComponentDesc = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent baseComponentCompany = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpName"))
				.thenReturn(baseComponentName);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpNo")).thenReturn(baseComponentNo);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpDesc"))
				.thenReturn(baseComponentDesc);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpCompany"))
				.thenReturn(baseComponentCompany);

		// (name.isEmpty()) = true
		doReturn("").when(baseComponentName).getStringFromField();

		// (no.isEmpty()) = true
		doReturn("").when(baseComponentNo).getStringFromField();

		// (desc.isEmpty()) = true
		doReturn("").when(baseComponentDesc).getStringFromField();

		// (company == null) = true
		doReturn(null).when(baseComponentCompany).getIntegerFromField();

		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGtnFrameworkItemGrpNameValidation_doAction_if_2() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkItemGrpNameValidationAction fixture = new GtnFrameworkItemGrpNameValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponentName = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent baseComponentNo = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent baseComponentDesc = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent baseComponentCompany = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpName"))
				.thenReturn(baseComponentName);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpNo")).thenReturn(baseComponentNo);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpDesc"))
				.thenReturn(baseComponentDesc);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpCompany"))
				.thenReturn(baseComponentCompany);

		// (name.isEmpty()) = true
		doReturn("").when(baseComponentName).getStringFromField();

		// (no.isEmpty()) = true
		doReturn("").when(baseComponentNo).getStringFromField();

		// (desc.isEmpty()) = true
		doReturn("").when(baseComponentDesc).getStringFromField();

		// (company == 0) = true
		doReturn(0).when(baseComponentCompany).getIntegerFromField();

		try {
			fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGtnFrameworkItemGrpNameValidation_doAction_notIf_1() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkItemGrpNameValidationAction fixture = new GtnFrameworkItemGrpNameValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponentName = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent baseComponentNo = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent baseComponentDesc = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent baseComponentCompany = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpName"))
				.thenReturn(baseComponentName);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpNo")).thenReturn(baseComponentNo);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpDesc"))
				.thenReturn(baseComponentDesc);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpCompany"))
				.thenReturn(baseComponentCompany);

		// (name.isEmpty()) = false
		doReturn("name").when(baseComponentName).getStringFromField();

		// (no.isEmpty()) = false
		doReturn("2").when(baseComponentNo).getStringFromField();

		// (desc.isEmpty()) = false
		doReturn("desc").when(baseComponentDesc).getStringFromField();

		// (company == null) = false
		doReturn(5).when(baseComponentCompany).getIntegerFromField();

		when(GtnUIFrameworkGlobalUI.getSessionProperty("itemGroupSid")).thenReturn(20516);

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnFrameworkItemGrpNameValidationAction action = Mockito.spy(fixture);

		GtnWsItemGrpValidationBean grpValidationBean = new GtnWsItemGrpValidationBean();
		// isGroupNameExists() = true
		grpValidationBean.setGroupNameExists(true);

		GtnWsItemGroupResponse gtnWsItemGroupResponse = new GtnWsItemGroupResponse();
		gtnWsItemGroupResponse.setGtnWsItemGrpValidationBean(grpValidationBean);

		response.setGtnWsItemGroupResponse(gtnWsItemGroupResponse);
		doReturn(response).when(action).getResponse(Mockito.any());

		try {
			action.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGtnFrameworkItemGrpNameValidation_doAction_notIf_2() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnFrameworkItemGrpNameValidationAction fixture = new GtnFrameworkItemGrpNameValidationAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkBaseComponent baseComponentName = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent baseComponentNo = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent baseComponentDesc = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		GtnUIFrameworkBaseComponent baseComponentCompany = Mockito.mock(GtnUIFrameworkBaseComponent.class);

		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpName"))
				.thenReturn(baseComponentName);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpNo")).thenReturn(baseComponentNo);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpDesc"))
				.thenReturn(baseComponentDesc);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemGrpInformationItemGrpCompany"))
				.thenReturn(baseComponentCompany);

		// (name.isEmpty()) = false
		doReturn("name").when(baseComponentName).getStringFromField();

		// (no.isEmpty()) = false
		doReturn("2").when(baseComponentNo).getStringFromField();

		// (desc.isEmpty()) = false
		doReturn("desc").when(baseComponentDesc).getStringFromField();

		// (company == 0) = false
		doReturn(5).when(baseComponentCompany).getIntegerFromField();

		when(GtnUIFrameworkGlobalUI.getSessionProperty("itemGroupSid")).thenReturn(20516);

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnFrameworkItemGrpNameValidationAction action = Mockito.spy(fixture);

		GtnWsItemGrpValidationBean grpValidationBean = new GtnWsItemGrpValidationBean();

		// isGroupNameExists() = false
		grpValidationBean.setGroupNameExists(false);

		GtnWsItemGroupResponse gtnWsItemGroupResponse = new GtnWsItemGroupResponse();
		gtnWsItemGroupResponse.setGtnWsItemGrpValidationBean(grpValidationBean);

		response.setGtnWsItemGroupResponse(gtnWsItemGroupResponse);
		doReturn(response).when(action).getResponse(Mockito.any());

		try {
			action.doAction(componentId, gtnUIFrameWorkActionConfig);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGtnFrameworkItemGrpNameValidation_doAction_getResponse() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnFrameworkItemGrpNameValidationAction itemGrpNameValidationAction = new GtnFrameworkItemGrpNameValidationAction();
		GtnUIFrameworkWebserviceRequest request = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		itemGrpNameValidationAction.getResponse(request);

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