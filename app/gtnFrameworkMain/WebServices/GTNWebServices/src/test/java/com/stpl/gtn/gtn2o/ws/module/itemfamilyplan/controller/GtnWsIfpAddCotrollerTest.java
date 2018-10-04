package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.entity.itemfamilyplan.IfpModel;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
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

	@Mock
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Spy
	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCheckAll_checkAllItems_returns1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		doReturn(1).when(ifpAddWebservice).checkAllItems(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.checkAll(gtnWsRequest);
		assertNotNull(result);
	}

	@Test
	public void testCheckAll_checkAllItems_returns0() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		doReturn(0).when(ifpAddWebservice).checkAllItems(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.checkAll(gtnWsRequest);
		assertNotNull(result);
	}

	@Test
	public void testdeleteIfp() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		doReturn(true).when(ifpAddWebservice).deleteIfpModel(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.deleteIfp(gtnWsRequest);
		assertNotNull(result);
	}

	@Test
	public void testgetIfpItemsTabDelete() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		doReturn(1).when(ifpAddWebservice).getIfpTabDeleteQuery(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.getIfpItemsTabDelete(gtnWsRequest);
		assertNotNull(result);
	}

	@Test
	public void testfetchIfpInformation() throws Exception {

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

	@Test
	public void testItemAdditionMoveLeft_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();

		updateBean.setItemMasterSid(20516);
		gtnIFamilyPlan.setUpdateBean(updateBean);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		doReturn(1).when(gtnSqlQueryEngine).executeInsertOrUpdateQuery(Mockito.anyString(), Mockito.any(Object[].class),
				Mockito.anyObject());

		GtnUIFrameworkWebserviceResponse result = fixture.itemAdditionMoveLeft(gtnWsRequest);
		assertNotNull(result);
	}

	@Test
	public void testItemAdditionMoveRight_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();

		updateBean.setItemMasterSid(20516);
		gtnIFamilyPlan.setUpdateBean(updateBean);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		doReturn(1).when(gtnSqlQueryEngine).executeInsertOrUpdateQuery(Mockito.anyString(), Mockito.any(Object[].class),
				Mockito.anyObject());

		GtnUIFrameworkWebserviceResponse result = fixture.itemAdditionMoveRight(gtnWsRequest);
		assertNotNull(result);
	}
	
	@Test
	public void testItemAdditionMoveAllLeft_1() throws Exception {

		ifpAddWebservice.setGtnWsSqlService(gtnWsSqlService);
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
		
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		gtnWebServiceSearchCriteriaList.add(criteria);
		
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		
		doReturn(1).when(gtnSqlQueryEngine).executeInsertOrUpdateQuery(Mockito.anyString(), Mockito.any(Object[].class),
				Mockito.anyObject());

		try{
			fixture.itemAdditionMoveAllLeft(gtnWsRequest);
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testItemAdditionMoveAllRight_1() throws Exception {

		ifpAddWebservice.setGtnWsSqlService(gtnWsSqlService);
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
		
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		gtnWebServiceSearchCriteriaList.add(criteria);
		
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		
		doReturn(1).when(gtnSqlQueryEngine).executeInsertOrUpdateQuery(Mockito.anyString(), Mockito.any(Object[].class),
				Mockito.anyObject());

		try{
			fixture.itemAdditionMoveAllRight(gtnWsRequest);
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	@Test
	public void testItemsColumnUpdate_1() throws Exception {

		ifpAddWebservice.setGtnWsSqlService(gtnWsSqlService);
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();

		updateBean.setColumnName("else");
		updateBean.setValue(2);
		updateBean.setImtdIfpDetailsSid("111");
		
		gtnIFamilyPlan.setUpdateBean(updateBean);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		doReturn(1).when(gtnSqlQueryEngine).executeInsertOrUpdateQuery(Mockito.anyString(), Mockito.any(Object[].class),
				Mockito.anyObject());

		GtnUIFrameworkWebserviceResponse result = fixture.itemsColumnUpdate(gtnWsRequest);
		assertNotNull(result);
	}

	@Test
	public void testCompaniesResultTableData_1() throws Exception {
		
		ifpAddWebservice.setGtnWsSqlService(gtnWsSqlService);
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();

		updateBean.setItemMasterSid(20516);
		gtnIFamilyPlan.setUpdateBean(updateBean);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		doReturn(1).when(gtnSqlQueryEngine).executeInsertOrUpdateQuery(Mockito.anyString(), Mockito.any(Object[].class),
				Mockito.anyObject());

		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsSearchRequest.setCount(false);

		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
		criteria.setFieldId("ifpItemsTabRecordType");
		criteria.setFilterValue1("CurrentHistoryFuture");
		criteria.setFilterValue1("2");

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<GtnWebServiceSearchCriteria>();
		gtnWebServiceSearchCriteriaList.add(criteria);

		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);

		GtnSerachResponse gtnSerachResponse = Mockito.mock(GtnSerachResponse.class);
		gtnSerachResponse.setCount(2);

		GtnUIFrameworkWebserviceResponse result = fixture.companiesResultTableData(gtnWsRequest);
		assertNotNull(result);
	}

	@Test
	public void testItemAdditionRightTableSearch_1() throws Exception {

		ifpAddWebservice.setGtnWsSqlService(gtnWsSqlService);
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();

		updateBean.setItemMasterSid(20516);
		gtnIFamilyPlan.setUpdateBean(updateBean);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		List<?> resultList = new ArrayList<>();
		doReturn(resultList).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString());

		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsSearchRequest.setCount(false);

		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
		criteria.setFieldId("ifpItemsTabRecordType");
		criteria.setFilterValue1("CurrentHistoryFuture");
		criteria.setFilterValue1("2");

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<GtnWebServiceSearchCriteria>();
		gtnWebServiceSearchCriteriaList.add(criteria);

		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);

		GtnSerachResponse gtnSerachResponse = Mockito.mock(GtnSerachResponse.class);
		gtnSerachResponse.setCount(2);

		try {
			fixture.itemAdditionRightTableSearch(gtnWsRequest);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	@Test
	public void testIfpSearch_1() throws Exception {

		ifpAddWebservice.setGtnWsSqlService(gtnWsSqlService);
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();

		updateBean.setItemMasterSid(20516);
		gtnIFamilyPlan.setUpdateBean(updateBean);
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);

		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setUserId("20516");
		gtnWsGeneralRequest.setSessionId("20516");

		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);

		List<?> resultList = new ArrayList<>();
		doReturn(resultList).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString());

		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsSearchRequest.setCount(false);

		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
		criteria.setFieldId("ifpItemsTabRecordType");
		criteria.setFilterValue1("CurrentHistoryFuture");
		criteria.setFilterValue1("2");

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<GtnWebServiceSearchCriteria>();
		gtnWebServiceSearchCriteriaList.add(criteria);

		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);

		GtnSerachResponse gtnSerachResponse = Mockito.mock(GtnSerachResponse.class);
		gtnSerachResponse.setCount(2);

		try {
			fixture.ifpSearch(gtnWsRequest);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}