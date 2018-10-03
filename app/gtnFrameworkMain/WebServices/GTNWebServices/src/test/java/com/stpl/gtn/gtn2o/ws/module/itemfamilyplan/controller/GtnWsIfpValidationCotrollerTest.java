package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsIfpValidationCotrollerTest {
	
	@InjectMocks
	@Autowired
	GtnWsIfpValidationCotroller fixture;
	
	@Test
	public void testGtnWsIfpValidationCotroller_1()
		throws Exception {
		
		GtnWsIfpValidationCotroller result = new GtnWsIfpValidationCotroller();
		assertNotNull(result);
	}

	@Test
	public void testIfpCommonValidation_1()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");
		  
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.ifpCommonValidation(gtnWsRequest);
		assertNotNull(result);
		
	}
	
	@Test
	public void testIfpCommonValidation_catchBlock()
		throws Exception {
		
		GtnUIFrameworkWebserviceResponse result = fixture.ifpCommonValidation(null);
		assertNotNull(result);
		
	}
	
	@Test
	public void testifpIdAndIfpNoValidation_1()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpSid(20516);
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.ifpIdAndIfpNoValidation(gtnWsRequest);
		assertNotNull(result);
		
	}
	
	@Test
	public void testifpIdAndIfpNoValidation_ifpSid_isNull()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpSid(null);
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.ifpIdAndIfpNoValidation(gtnWsRequest);
		assertNotNull(result);
		
	}
	
	@Test
	public void testifpIdAndIfpNoValidation_catchBlock()
		throws Exception {

		GtnUIFrameworkWebserviceResponse result = fixture.ifpIdAndIfpNoValidation(null);
		assertNotNull(result);
		
	}
	
	

}