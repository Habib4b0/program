package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.entity.itemfamilyplan.IfpModel;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */
public class GtnWsIfpAddCotrollerTest {
	
	@InjectMocks
	@Autowired
	GtnWsIfpAddCotroller fixture;
	
	@Spy
	@Autowired
	private GtnWsIfpAddService ifpAddWebservice;
	
	@Mock
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;
	
	@Spy
	@Autowired
	private GtnWsAllListConfig gtnWebServiceAllListConfig;
	
//	@Spy
//	@Autowired
//	private GtnWsSqlService gtnWsSqlService;

	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCheckAll_checkAllItems_returns1()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		doReturn(1).when(ifpAddWebservice).checkAllItems(gtnWsRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.checkAll(gtnWsRequest);
		assertNotNull(result);
	}
	
	@Test
	public void testCheckAll_checkAllItems_returns0()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		doReturn(0).when(ifpAddWebservice).checkAllItems(gtnWsRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.checkAll(gtnWsRequest);
		assertNotNull(result);
	}
	
	@Test
	public void testdeleteIfp()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		doReturn(true).when(ifpAddWebservice).deleteIfpModel(gtnWsRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.deleteIfp(gtnWsRequest);
		assertNotNull(result);
	}
	
	@Test
	public void testgetIfpItemsTabDelete()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		doReturn(1).when(ifpAddWebservice).getIfpTabDeleteQuery(gtnWsRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.getIfpItemsTabDelete(gtnWsRequest);
		assertNotNull(result);
	}
	
	@Test
	public void testfetchIfpInformation()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
  
		SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction transaction = Mockito.mock(Transaction.class);
		
		doReturn(sessionFactory).when(ifpAddWebservice).getSessionFactory();
		doReturn(session).when(sessionFactory).openSession();
		doReturn(transaction).when(session).beginTransaction();
		
		IfpModel ifpModel = Mockito.mock(IfpModel.class);
		doReturn(ifpModel).when(session).get(IfpModel.class, 20516);
		

		doReturn(1).when(ifpAddWebservice).updateIfpCompaniesTabQuery(gtnWsRequest);
		
		GtnUIFrameworkWebserviceResponse result = fixture.fetchIfpInformation(gtnWsRequest);
		assertNotNull(result);
	}
	
//	@Test
//	public void testItemAdditionMoveRight()
//		throws Exception {
//		
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
//		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
//		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
//		
//		updateBean.setItemMasterSid(20516);
//		gtnIFamilyPlan.setUpdateBean(updateBean);
//		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
//		
//		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
//		gtnWsGeneralRequest.setUserId("20516");
//		gtnWsGeneralRequest.setSessionId("20516");
//		
//		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
//		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
//		
//		gtnWsSqlService.getQuery("getIFPItemAdditionMoveRightQuery");
//		
//		//doReturn(true).when(ifpAddWebservice).deleteIfpModel(gtnWsRequest);
//		
//		GtnUIFrameworkWebserviceResponse result = fixture.itemAdditionMoveRight(gtnWsRequest);
//		assertNotNull(result);
//	}


//	@Test
//	public void testCheckAll_1()
//		throws Exception {
//		
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		
//		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
//		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
//		
//		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
//		ifpInfo.setIfpId("20516");
//		ifpInfo.setIfpNo("20516");
//		ifpInfo.setIfpSid(20516);
//		
//		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
//		updateBean.setClassType("java.util.Integer");
//		updateBean.setColumnName("itemFamilyPlanStatus");
//		updateBean.setValue(1);
//		updateBean.setImtdIfpDetailsSid("20516");
//		
//		gtnIFamilyPlan.setIfpInfo(ifpInfo);
//		gtnIFamilyPlan.setUpdateBean(updateBean);
//		
//		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
//		gtnWsGeneralRequest.setUserId("20516");
//		gtnWsGeneralRequest.setSessionId("20516");
//
//		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
//		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
//		
//		doReturn(1).when(ifpAddWebservice).checkAllItems(gtnWsRequest);
//		
//		GtnUIFrameworkWebserviceResponse result = fixture.checkAll(gtnWsRequest);
//		assertNotNull(result);
//	}

	
//	@Test
//	public void testCompaniesResultTableData_1()
//		throws Exception {
//		GtnWsIfpAddCotroller fixture = new GtnWsIfpAddCotroller();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//		GtnUIFrameworkWebserviceRequest ifpItemsTabRequest = new GtnUIFrameworkWebserviceRequest();
//		ifpItemsTabRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());
//		ifpItemsTabRequest.setGtnWsSearchRequest(new GtnWsSearchRequest());
//		GtnUIFrameworkWebserviceResponse result = fixture.companiesResultTableData(ifpItemsTabRequest);
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testFetchIfpInformation_1()
//		throws Exception {
//		GtnWsIfpAddCotroller fixture = new GtnWsIfpAddCotroller();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnUIFrameworkWebserviceResponse result = fixture.fetchIfpInformation(gtnWsRequest);
//
//		// add additional test code here
//		assertNotNull(result);
//	}
//
//	
//	@Test
//	public void testGetIfpItemsTabDelete_1()
//		throws Exception {
//		GtnWsIfpAddCotroller fixture = new GtnWsIfpAddCotroller();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		GtnUIFrameworkWebserviceResponse result = fixture.getIfpItemsTabDelete(gtnWsRequest);
//
//		// add additional test code here
//		assertNotNull(result);
//	}
//
//	
//	@Test
//	public void testGetSessionFactory_1()
//		throws Exception {
//		GtnWsIfpAddCotroller fixture = new GtnWsIfpAddCotroller();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//
//		SessionFactory result = fixture.getSessionFactory();
//
//		// add additional test code here
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testIfpSearch_1()
//		throws Exception {
//		GtnWsIfpAddCotroller fixture = new GtnWsIfpAddCotroller();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//		GtnUIFrameworkWebserviceRequest ifpSearchRequest = new GtnUIFrameworkWebserviceRequest();
//		ifpSearchRequest.setGtnWsSearchRequest(new GtnWsSearchRequest());
//
//		GtnUIFrameworkWebserviceResponse result = fixture.ifpSearch(ifpSearchRequest);
//
//		// add additional test code here
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testItemAdditionMoveAllLeft_1()
//		throws Exception {
//		GtnWsIfpAddCotroller fixture = new GtnWsIfpAddCotroller();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//		GtnUIFrameworkWebserviceRequest ifpMoveAllLeftRequest = new GtnUIFrameworkWebserviceRequest();
//		ifpMoveAllLeftRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());
//
//		GtnUIFrameworkWebserviceResponse result = fixture.itemAdditionMoveAllLeft(ifpMoveAllLeftRequest);
//
//		// add additional test code here
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the GtnUIFrameworkWebserviceResponse itemAdditionMoveAllLeft(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * @generatedBy CodePro at 10/1/18 10:42 AM
//	 */
//	@Test
//	public void testItemAdditionMoveAllLeft_2()
//		throws Exception {
//		GtnWsIfpAddCotroller fixture = new GtnWsIfpAddCotroller();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//		GtnUIFrameworkWebserviceRequest ifpMoveAllLeftRequest = new GtnUIFrameworkWebserviceRequest();
//		ifpMoveAllLeftRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());
//
//		GtnUIFrameworkWebserviceResponse result = fixture.itemAdditionMoveAllLeft(ifpMoveAllLeftRequest);
//
//		// add additional test code here
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testItemAdditionMoveAllRight_1()
//		throws Exception {
//		GtnWsIfpAddCotroller fixture = new GtnWsIfpAddCotroller();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//		GtnUIFrameworkWebserviceRequest ifpMoveAllRightRequest = new GtnUIFrameworkWebserviceRequest();
//		ifpMoveAllRightRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());
//
//		GtnUIFrameworkWebserviceResponse result = fixture.itemAdditionMoveAllRight(ifpMoveAllRightRequest);
//
//		// add additional test code here
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testItemAdditionRightTableSearch_1()
//		throws Exception {
//		GtnWsIfpAddCotroller fixture = new GtnWsIfpAddCotroller();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//		GtnUIFrameworkWebserviceRequest itemAdditionRightRequest = new GtnUIFrameworkWebserviceRequest();
//		itemAdditionRightRequest.setGtnWsSearchRequest(new GtnWsSearchRequest());
//
//		GtnUIFrameworkWebserviceResponse result = fixture.itemAdditionRightTableSearch(itemAdditionRightRequest);
//
//		// add additional test code here
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testItemsColumnUpdate_1()
//		throws Exception {
//		GtnWsIfpAddCotroller fixture = new GtnWsIfpAddCotroller();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		gtnWsRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());
//
//		GtnUIFrameworkWebserviceResponse result = fixture.itemsColumnUpdate(gtnWsRequest);
//
//		assertNotNull(result);
//	}
//
//	@Test
//	public void testSetSessionFactory_1()
//		throws Exception {
//		GtnWsIfpAddCotroller fixture = new GtnWsIfpAddCotroller();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//		SessionFactory sessionFactory = new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver())))));
//
//		fixture.setSessionFactory(sessionFactory);
//
//	}
}