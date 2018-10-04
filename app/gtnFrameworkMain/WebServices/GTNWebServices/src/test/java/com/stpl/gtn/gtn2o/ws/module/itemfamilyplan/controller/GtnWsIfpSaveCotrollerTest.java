package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.entity.itemfamilyplan.IfpModel;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpSaveService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 $
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsIfpSaveCotrollerTest {
	
	@InjectMocks
	@Autowired
	GtnWsIfpSaveCotroller fixture;
	
	@Spy
	@Autowired
	private GtnWsIfpSaveService ifpSaveWebservice;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testItemsTabUpdateField_service_getItemsTabUpdateColumns_case_itemFamilyPlanStatus()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest itemsTabUpdateRequest = new GtnUIFrameworkWebserviceRequest();
		itemsTabUpdateRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());
		
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpSid(20516);
		
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
		updateBean.setClassType("java.util.Integer");
		updateBean.setColumnName("itemFamilyPlanStatus");
		updateBean.setValue(2);
		updateBean.setImtdIfpDetailsSid("20516");
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnIFamilyPlan.setUpdateBean(updateBean);
		
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		itemsTabUpdateRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.itemsTabUpdateField(itemsTabUpdateRequest);
		assertNotNull(result);
	}
	
	@Test
	public void testItemsTabUpdateField_service_getItemsTabUpdateColumns_case_itemFamilyPlanStartDate()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest itemsTabUpdateRequest = new GtnUIFrameworkWebserviceRequest();
		itemsTabUpdateRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());
		
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpSid(20516);
		
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
		updateBean.setClassType("java.util.Integer");
		updateBean.setColumnName("itemFamilyPlanStartDate");
		updateBean.setValue(2);
		updateBean.setImtdIfpDetailsSid("20516");
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnIFamilyPlan.setUpdateBean(updateBean);
		
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		itemsTabUpdateRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.itemsTabUpdateField(itemsTabUpdateRequest);
		assertNotNull(result);
	}
	
	@Test
	public void testItemsTabUpdateField_service_getItemsTabUpdateColumns_case_itemFamilyPlanEndDate()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest itemsTabUpdateRequest = new GtnUIFrameworkWebserviceRequest();
		itemsTabUpdateRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());
		
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpSid(20516);
		
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
		updateBean.setClassType("java.util.Integer");
		updateBean.setColumnName("itemFamilyPlanEndDate");
		updateBean.setValue(2);
		updateBean.setImtdIfpDetailsSid("20516");
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnIFamilyPlan.setUpdateBean(updateBean);
		
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		itemsTabUpdateRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.itemsTabUpdateField(itemsTabUpdateRequest);
		assertNotNull(result);
	}
	
	@Test
	public void testItemsTabUpdateField_service_getItemsTabUpdateColumns_case_checkRecordId()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest itemsTabUpdateRequest = new GtnUIFrameworkWebserviceRequest();
		itemsTabUpdateRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());
		
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpSid(20516);
		
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
		updateBean.setClassType("java.util.Integer");
		updateBean.setColumnName("checkRecordId");
		updateBean.setValue(2);
		updateBean.setImtdIfpDetailsSid("20516");
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnIFamilyPlan.setUpdateBean(updateBean);
		
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		itemsTabUpdateRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.itemsTabUpdateField(itemsTabUpdateRequest);
		assertNotNull(result);
	}
	
	@Test
	public void testItemsTabUpdateField_service_getItemsTabUpdateColumns_case_default()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest itemsTabUpdateRequest = new GtnUIFrameworkWebserviceRequest();
		itemsTabUpdateRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());
		
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpSid(20516);
		
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
		updateBean.setClassType("java.util.Integer");
		updateBean.setColumnName("default");
		updateBean.setValue(2);
		updateBean.setImtdIfpDetailsSid("20516");
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnIFamilyPlan.setUpdateBean(updateBean);
		
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		itemsTabUpdateRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.itemsTabUpdateField(itemsTabUpdateRequest);
		assertNotNull(result);
	}
	
	@Test
	public void testItemsTabUpdateField_service_updateFieldsQuery_getImtdIfpDetailsSid_isNull()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest itemsTabUpdateRequest = new GtnUIFrameworkWebserviceRequest();
		itemsTabUpdateRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());
		
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpSid(20516);
		
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
		updateBean.setClassType("java.util.Integer");
		updateBean.setColumnName("itemFamilyPlanStatus");
		updateBean.setValue(2);
		updateBean.setImtdIfpDetailsSid(null);
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnIFamilyPlan.setUpdateBean(updateBean);
		
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		itemsTabUpdateRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.itemsTabUpdateField(itemsTabUpdateRequest);
		assertNotNull(result);
	}

	@Test
	public void testSaveCompanyFamilyPlan__ifpModelSid_iSNull_saveCfpQuery()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpSid(null);
		ifpInfo.setCreatedBy("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpName("20516");
		ifpInfo.setIfpType(20516);
		ifpInfo.setIfpCategory(20516);
		ifpInfo.setIfpDesignation(20516);
		ifpInfo.setIfpStatus(1);
		ifpInfo.setIfpStatus(1);
		ifpInfo.setParentIfpName("20516");
		ifpInfo.setIfpStartDate(new Date());
		ifpInfo.setIfpEndDate(new Date());
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction transaction = Mockito.mock(Transaction.class);
		
		doReturn(sessionFactory).when(ifpSaveWebservice).getSessionFactory();
		doReturn(session).when(sessionFactory).openSession();
		doReturn(transaction).when(session).beginTransaction();
		doReturn(1).when(session).save(Mockito.any());
		doNothing().when(transaction).commit();  
		
		
		
		fixture.saveCompanyFamilyPlan(gtnWsRequest);		
	}
	
	@Test
	public void testSaveCompanyFamilyPlan_ifpModelSid_notNull_updateCfpQuery()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpSid(20516);
		ifpInfo.setCreatedBy("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpName("20516");
		ifpInfo.setIfpType(20516);
		ifpInfo.setIfpCategory(20516);
		ifpInfo.setIfpDesignation(20516);
		ifpInfo.setIfpStatus(1);
		ifpInfo.setIfpStatus(1);
		ifpInfo.setParentIfpName("20516");
		ifpInfo.setIfpStartDate(new Date());
		ifpInfo.setIfpEndDate(new Date());
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction transaction = Mockito.mock(Transaction.class);
		
		doReturn(sessionFactory).when(ifpSaveWebservice).getSessionFactory();
		doReturn(session).when(sessionFactory).openSession();
		doReturn(transaction).when(session).beginTransaction();
		
		IfpModel ifpModel = Mockito.mock(IfpModel.class);
		doReturn(ifpModel).when(session).get(IfpModel.class, 20516);
		
		fixture.saveCompanyFamilyPlan(gtnWsRequest);		
	}
	
	@Test
	public void testSaveCompanyFamilyPlan_ifpModelSid_notNull_updateCfpQuery_catchBlock()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpSid(20516);
		ifpInfo.setCreatedBy("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpName("20516");
		ifpInfo.setIfpType(20516);
		ifpInfo.setIfpCategory(20516);
		ifpInfo.setIfpDesignation(20516);
		ifpInfo.setIfpStatus(1);
		ifpInfo.setIfpStatus(1);
		ifpInfo.setParentIfpName("20516");
		ifpInfo.setIfpStartDate(new Date());
		ifpInfo.setIfpEndDate(new Date());
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		// To cover service class -> saveCfpQuery -> catchblock. not defined session, tracnsaction etc. 
		
		fixture.saveCompanyFamilyPlan(gtnWsRequest);		
	}
	
	@Test
	public void testSaveCompanyFamilyPlan_ifpModelSid_lessThan0_saveCfpQuery_catchBlock()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		
		// ifpModelSid < 0
		ifpInfo.setIfpSid(-5);
		
		ifpInfo.setCreatedBy("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpName("20516");
		ifpInfo.setIfpType(20516);
		ifpInfo.setIfpCategory(20516);
		ifpInfo.setIfpDesignation(20516);
		ifpInfo.setIfpStatus(1);
		ifpInfo.setIfpStatus(1);
		ifpInfo.setParentIfpName("20516");
		ifpInfo.setIfpStartDate(new Date());
		ifpInfo.setIfpEndDate(new Date());
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		// To cover service class -> saveCfpQuery -> catchblock. not defined session, tracnsaction etc. 
		
		fixture.saveCompanyFamilyPlan(gtnWsRequest);		
	}
	
	@Test
	public void testSaveCompanyFamilyPlan_ifpModelSid_equalTo0()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpNo("20516");
		
		// ifpModelSid == 0
		ifpInfo.setIfpSid(0);
		
		ifpInfo.setCreatedBy("20516");
		ifpInfo.setIfpNo("20516");
		ifpInfo.setIfpId("20516");
		ifpInfo.setIfpName("20516");
		ifpInfo.setIfpType(20516);
		ifpInfo.setIfpCategory(20516);
		ifpInfo.setIfpDesignation(20516);
		ifpInfo.setIfpStatus(1);
		ifpInfo.setIfpStatus(1);
		ifpInfo.setParentIfpName("20516");
		ifpInfo.setIfpStartDate(new Date());
		ifpInfo.setIfpEndDate(new Date());
		
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		// To cover service class -> saveCfpQuery -> catchblock. not defined session, tracnsaction etc. 
		
		fixture.saveCompanyFamilyPlan(gtnWsRequest);		
	}
	
}