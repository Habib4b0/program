package com.stpl.gtn.gtn2o.ui.module.ifp.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanValidationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.ifpresponse.GtnWsIfpReponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnFrameworkIfpStringContants.class,
		GtnFrameworkIfpValueChangeManager.class })
public class GtnFrameworkIfpTableFieldFactoryTest {

	@Test
	public void testConfigureParams() throws Exception {
		GtnFrameworkIfpTableFieldFactory fixture = new GtnFrameworkIfpTableFieldFactory();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		fixture.configureParams(gtnUIFrameWorkActionConfig);

	}

	@Test
	public void testCreateInstance() throws Exception {
		GtnFrameworkIfpTableFieldFactory fixture = new GtnFrameworkIfpTableFieldFactory();

		GtnUIFrameWorkAction result = fixture.createInstance();

		assertNotNull(result);
	}

	@Test
	public void testGtnFrameworkIfpTableFieldFactory_doAction() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkIfpStringContants.class,
				GtnFrameworkIfpValueChangeManager.class);
		
		GtnFrameworkIfpTableFieldFactory fixture = new GtnFrameworkIfpTableFieldFactory();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinFieldFactoryComponentData("")).thenReturn(componentData);

		GtnUIFrameworkActionParameter actionparameter = Mockito.mock(GtnUIFrameworkActionParameter.class);
		doReturn(actionparameter).when(componentData).getActionParameter();
		doReturn("checkRecordId").when(actionparameter).getPropertyId();
 
		List<String> dateFieldPropertyList = new ArrayList<>();
		dateFieldPropertyList.add("checkRecordId");
		when(GtnFrameworkIfpStringContants.getDateFieldPropertiesList()).thenReturn(dateFieldPropertyList);

		when(GtnFrameworkIfpValueChangeManager.isValueChangeAllowed()).thenReturn(true);
		 
		doReturn((Serializable)new Object().toString()).when(actionparameter).getCurrentValue(); 
		
		GtnWsRecordBean gtnWsRecordBean = Mockito.mock(GtnWsRecordBean.class);
		doReturn(gtnWsRecordBean).when(actionparameter).getItemId();
		
		List<Object> propertyList = new ArrayList<Object>(); 
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		
		doReturn(propertyList).when(gtnWsRecordBean).getProperties();
		
		GtnFrameworkIfpTableFieldFactory gtnFrameworkIfpTableFieldFactory = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse updateResponse = new GtnUIFrameworkWebserviceResponse();
		doReturn(updateResponse).when(gtnFrameworkIfpTableFieldFactory).getResponse(Mockito.any());
		
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		 
		doReturn(updateResponse).when(gtnFrameworkIfpTableFieldFactory).getCheckBoxValueChangeLogicResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		updateResponse.setGtnWsIfpReponse(gtnWsIfpReponse); 
		 
		//validationBean.getCheckedCount() == validationBean.getCount() = True
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCheckedCount();
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCount();
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(baseComponent);
		
		gtnFrameworkIfpTableFieldFactory.doAction(componentId, gtnUIFrameWorkActionConfig); 
 
	} 
	
	@Test
	public void testGtnFrameworkIfpTableFieldFactory_doAction_checkElse() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkIfpStringContants.class,
				GtnFrameworkIfpValueChangeManager.class);
		
		GtnFrameworkIfpTableFieldFactory fixture = new GtnFrameworkIfpTableFieldFactory();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinFieldFactoryComponentData("")).thenReturn(componentData);

		GtnUIFrameworkActionParameter actionparameter = Mockito.mock(GtnUIFrameworkActionParameter.class);
		doReturn(actionparameter).when(componentData).getActionParameter();
		
		 
 		doReturn("").when(actionparameter).getPropertyId();
   
		List<String> dateFieldPropertyList = new ArrayList<>();
		
		// typeString - Integer.class.getName() 
		dateFieldPropertyList.add("falsePart");
		when(GtnFrameworkIfpStringContants.getDateFieldPropertiesList()).thenReturn(dateFieldPropertyList);

		// isValueChangeAllowed - false
		when(GtnFrameworkIfpValueChangeManager.isValueChangeAllowed()).thenReturn(false);
		 
		doReturn((Serializable)new Object().toString()).when(actionparameter).getCurrentValue(); 
		
		GtnWsRecordBean gtnWsRecordBean = Mockito.mock(GtnWsRecordBean.class);
		doReturn(gtnWsRecordBean).when(actionparameter).getItemId();
		 
		List<Object> propertyList = new ArrayList<Object>(); 
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
 		propertyList.add(0); 
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		
		doReturn(propertyList).when(gtnWsRecordBean).getProperties();
		
		GtnFrameworkIfpTableFieldFactory gtnFrameworkIfpTableFieldFactory = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse updateResponse = Mockito.mock(GtnUIFrameworkWebserviceResponse.class);
		
		// When response is null 
		doReturn(null).when(gtnFrameworkIfpTableFieldFactory).getResponse(Mockito.any());
		
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		  
		doReturn(updateResponse).when(gtnFrameworkIfpTableFieldFactory).getCheckBoxValueChangeLogicResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		updateResponse.setGtnWsIfpReponse(gtnWsIfpReponse); 
		 
		//validationBean.getCheckedCount() == validationBean.getCount() = false
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCheckedCount();
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCount(); 
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(baseComponent);
		
		gtnFrameworkIfpTableFieldFactory.doAction(componentId, gtnUIFrameWorkActionConfig); 
 
	} 
	
	@Test
	public void testGtnFrameworkIfpTableFieldFactory_doAction_checkBoxValueChangeLogic_countFalse() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnFrameworkIfpStringContants.class,
				GtnFrameworkIfpValueChangeManager.class);
		
		GtnFrameworkIfpTableFieldFactory fixture = new GtnFrameworkIfpTableFieldFactory();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinFieldFactoryComponentData("")).thenReturn(componentData);

		GtnUIFrameworkActionParameter actionparameter = Mockito.mock(GtnUIFrameworkActionParameter.class);
		doReturn(actionparameter).when(componentData).getActionParameter();
		doReturn("checkRecordId").when(actionparameter).getPropertyId();
 
		List<String> dateFieldPropertyList = new ArrayList<>();
		dateFieldPropertyList.add("checkRecordId");
		when(GtnFrameworkIfpStringContants.getDateFieldPropertiesList()).thenReturn(dateFieldPropertyList);

		when(GtnFrameworkIfpValueChangeManager.isValueChangeAllowed()).thenReturn(true);
		 
		doReturn((Serializable)new Object().toString()).when(actionparameter).getCurrentValue(); 
		
		GtnWsRecordBean gtnWsRecordBean = Mockito.mock(GtnWsRecordBean.class);
		doReturn(gtnWsRecordBean).when(actionparameter).getItemId();
		
		List<Object> propertyList = new ArrayList<Object>(); 
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		propertyList.add(0);
		
		doReturn(propertyList).when(gtnWsRecordBean).getProperties();
		
		GtnFrameworkIfpTableFieldFactory gtnFrameworkIfpTableFieldFactory = Mockito.spy(fixture);
		GtnUIFrameworkWebserviceResponse updateResponse = new GtnUIFrameworkWebserviceResponse();
		doReturn(updateResponse).when(gtnFrameworkIfpTableFieldFactory).getResponse(Mockito.any());
		
		GtnIFamilyPlanValidationBean gtnIFamilyPlanValidationBean = Mockito.mock(GtnIFamilyPlanValidationBean.class);
		GtnWsIfpReponse gtnWsIfpReponse = new GtnWsIfpReponse();
		 
		doReturn(updateResponse).when(gtnFrameworkIfpTableFieldFactory).getCheckBoxValueChangeLogicResponse(Mockito.any());
		gtnWsIfpReponse.setGtnIFamilyPlanValidationBean(gtnIFamilyPlanValidationBean);
		updateResponse.setGtnWsIfpReponse(gtnWsIfpReponse); 
		 
		//validationBean.getCheckedCount() == validationBean.getCount() = false
		doReturn(5).when(gtnIFamilyPlanValidationBean).getCheckedCount();
		doReturn(6).when(gtnIFamilyPlanValidationBean).getCount();
		
		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.any())).thenReturn(baseComponent);
		
		gtnFrameworkIfpTableFieldFactory.doAction(componentId, gtnUIFrameWorkActionConfig); 
 
	} 
	
	@Test
	public void testGtnFrameworkIfpTableFieldFactory_doAction_getCheckBoxValueChangeLogicResponse() throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpTableFieldFactory gtnFrameworkIfpTableFieldFactory = new GtnFrameworkIfpTableFieldFactory();
		GtnUIFrameworkWebserviceRequest updateWsRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		gtnFrameworkIfpTableFieldFactory.getCheckBoxValueChangeLogicResponse(updateWsRequest);
		
	}
	
	@Test
	public void testGtnFrameworkIfpTableFieldFactory_doAction_getResponse() throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		GtnFrameworkIfpTableFieldFactory gtnFrameworkIfpTableFieldFactory = new GtnFrameworkIfpTableFieldFactory();
		GtnUIFrameworkWebserviceRequest updateWsRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		gtnFrameworkIfpTableFieldFactory.getResponse(updateWsRequest);
		
	}


	@Before
	public void setUp() throws Exception {
		// add additional set up code here
	}

	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

}