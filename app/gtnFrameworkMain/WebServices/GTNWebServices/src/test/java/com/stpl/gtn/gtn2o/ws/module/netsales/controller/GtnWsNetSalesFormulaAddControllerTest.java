package com.stpl.gtn.gtn2o.ws.module.netsales.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
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

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.controller.GtnWsSearchServiceController;
import com.stpl.gtn.gtn2o.ws.module.netsales.service.GtnWsNsfService;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnUIFrameworkNsfInfoBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.netsales.GtnWsNetSalesFormulaGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsNetSalesFormulaAddControllerTest {

	@InjectMocks
	@Autowired
	GtnWsNetSalesFormulaAddController fixture;

	@Spy
	@Autowired
	private GtnWsSearchServiceController gTNSearchServiceController;

	@Spy
	@Autowired
	private GtnWsNsfService gtnWsNsfService;
	
	@Spy
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	
	@Spy
	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Run the GtnWsNetSalesFormulaAddController() constructor test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnWsNetSalesFormulaAddController_1() throws Exception {

		GtnWsNetSalesFormulaAddController result = new GtnWsNetSalesFormulaAddController();
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * checkAll(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testCheckAll__checkAllItems_returns1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		doReturn(1).when(gtnWsNsfService).checkAllItems(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.checkAll(gtnWsRequest);
		assertNotNull(result);
	}

	@Test
	public void testCheckAll__checkAllItems_returns0() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		doReturn(0).when(gtnWsNsfService).checkAllItems(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.checkAll(gtnWsRequest);
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * checkAllForSales(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testCheckAllForSales_checkAllForSales_returns1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		doReturn(1).when(gtnWsNsfService).checkAllItemsForSales(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.checkAllForSales(gtnWsRequest);
		assertNotNull(result);
	};

	@Test
	public void testCheckAllForSales_checkAllForSales_returns0() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		doReturn(0).when(gtnWsNsfService).checkAllItemsForSales(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.checkAllForSales(gtnWsRequest);
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * deleteSalesDeductOnRefreshResponse(GtnUIFrameworkWebserviceRequest)
	 * method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testDeleteSalesDeductOnRefreshResponse_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		doNothing().when(gtnWsNsfService).deleteSalesDeductOnRefresh(gtnWsRequest);
		GtnUIFrameworkWebserviceResponse result = fixture.deleteSalesDeductOnRefreshResponse(gtnWsRequest);
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * deleteService(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testDeleteService_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		doNothing().when(gtnWsNsfService).deleteNsfRecord(gtnWsRequest);
		GtnUIFrameworkWebserviceResponse result = fixture.deleteService(gtnWsRequest);

		
		assertNotNull(result);
	}

	/**
	 * Run the SessionFactory getSessionFactory() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetSessionFactory_1() throws Exception {

		try {
			fixture.getSessionFactory();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * insertSelectedCustomersIntoTempTable(GtnUIFrameworkWebserviceRequest)
	 * method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testInsertSelectedCustomersIntoTempTable_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		doReturn(1).when(gtnWsNsfService).insertSelectedCustomersIntoTempTable(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.insertSelectedCustomersIntoTempTable(gtnWsRequest);
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * insertSelectedDeductionsIntoTempTable(GtnUIFrameworkWebserviceRequest)
	 * method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testInsertSelectedDeductionsIntoTempTable_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		doReturn(1).when(gtnWsNsfService).insertSelectedDeductionsIntoTempTable(gtnWsRequest);
		GtnUIFrameworkWebserviceResponse result = fixture.insertSelectedDeductionsIntoTempTable(gtnWsRequest);

		
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * loadOnEdit(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testLoadOnEdit_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		doReturn(nsfInfoBean).when(gtnWsNsfService).getNsfData(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.loadOnEdit(gtnWsRequest);

		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * netSalesSaveService(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testNetSalesSaveService_nsfSystemId_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsNetSalesFormulaGeneralRequest formulaGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();

		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		nsfInfoBean.setSystemId(1);
		formulaGeneralRequest.setnSfInfoBean(nsfInfoBean);

		gtnWsRequest.setGtnWsNetSalesGeneralRequest(formulaGeneralRequest);

		doNothing().when(gtnWsNsfService).updateNsfInfo(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.netSalesSaveService(gtnWsRequest);

		
		assertNotNull(result);
	}

	@Test
	public void testNetSalesSaveService_nsfSystemId_0() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsNetSalesFormulaGeneralRequest formulaGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();

		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		nsfInfoBean.setSystemId(0);
		formulaGeneralRequest.setnSfInfoBean(nsfInfoBean);

		gtnWsRequest.setGtnWsNetSalesGeneralRequest(formulaGeneralRequest);

		doReturn(1).when(gtnWsNsfService).saveNsfInfo(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.netSalesSaveService(gtnWsRequest);

		
		assertNotNull(result);
	}
	
	@Test
	public void testNetSalesSaveService_nsfSystemId_catch() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsNetSalesFormulaGeneralRequest formulaGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();

		// no nsfInfoBean initialized. so below line will cause an error
		//GtnUIFrameworkNsfInfoBean nsfInfoBean = gtnWsRequest.getGtnWsNetSalesGeneralRequest().getnSfInfoBean();
		
		gtnWsRequest.setGtnWsNetSalesGeneralRequest(formulaGeneralRequest);

		doNothing().when(gtnWsNsfService).updateNsfInfo(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.netSalesSaveService(gtnWsRequest);

		
		assertNotNull(result);
	}

	@Test
	public void testNetSalesSaveService_nsfSystemId_null() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsNetSalesFormulaGeneralRequest formulaGeneralRequest = new GtnWsNetSalesFormulaGeneralRequest();

		GtnUIFrameworkNsfInfoBean nsfInfoBean = new GtnUIFrameworkNsfInfoBean();
		nsfInfoBean.setSystemId(null);
		formulaGeneralRequest.setnSfInfoBean(nsfInfoBean);

		gtnWsRequest.setGtnWsNetSalesGeneralRequest(formulaGeneralRequest);

		doReturn(1).when(gtnWsNsfService).saveNsfInfo(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.netSalesSaveService(gtnWsRequest);

		
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * nsfRemoveRecordService(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testNsfRemoveRecordService_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		doNothing().when(gtnWsNsfService).removeRecords(gtnWsRequest);
		GtnUIFrameworkWebserviceResponse result = fixture.nsfRemoveRecordService(gtnWsRequest);

		
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * nsfResetTableService(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testNsfResetTableService_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		doNothing().when(gtnWsNsfService).resetTables(gtnWsRequest);
		GtnUIFrameworkWebserviceResponse result = fixture.nsfResetTableService(gtnWsRequest);

		
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * nsfSalesBasisUpdateService(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testNsfSalesBasisUpdateService_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		doNothing().when(gtnWsNsfService).populateColumns(gtnWsRequest);
		GtnUIFrameworkWebserviceResponse result = fixture.nsfSalesBasisUpdateService(gtnWsRequest);

		
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * priceScheduleSearch(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testPriceScheduleSearch_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);

		GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
		searchCriteria.setFilterValue1("filterValue1");

		List<GtnWebServiceSearchCriteria> searchCriteriaList = new ArrayList<GtnWebServiceSearchCriteria>();
		searchCriteriaList.add(searchCriteria);
		searchCriteriaList.add(searchCriteria);

		GtnWsSearchRequest gtnWsSearchRequest = Mockito.mock(GtnWsSearchRequest.class);

		doReturn(gtnWsSearchRequest).when(gtnWsRequest).getGtnWsSearchRequest();
		doReturn(searchCriteriaList).when(gtnWsSearchRequest).getGtnWebServiceSearchCriteriaList();

		GtnUIFrameworkWebserviceResponse frameworkWebserviceResponse = Mockito
				.mock(GtnUIFrameworkWebserviceResponse.class);
		doReturn(frameworkWebserviceResponse).when(gTNSearchServiceController)
				.getSearchResultForAllModule(gtnWsRequest);

		GtnUIFrameworkWebserviceResponse result = fixture.priceScheduleSearch(gtnWsRequest);

		
		assertNotNull(result);
	}
	
	/**
	 * Run the GtnUIFrameworkWebserviceResponse
	 * nsfDuplicateFormulaType(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testNsfDuplicateFormulaType_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		gtnWsRequest.setGtnWsNetSalesGeneralRequest(new GtnWsNetSalesFormulaGeneralRequest());

		SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		doReturn(session).when(sessionFactory).openSession();
		
		GtnWsNetSalesFormulaGeneralRequest netSalesFormulaGeneralRequest = Mockito.mock(GtnWsNetSalesFormulaGeneralRequest.class);
		doReturn(netSalesFormulaGeneralRequest).when(gtnWsRequest).getGtnWsNetSalesGeneralRequest();
		
		GtnUIFrameworkNsfInfoBean nsfInfoBean = Mockito.mock(GtnUIFrameworkNsfInfoBean.class);
		
		doReturn("formulaName").when(nsfInfoBean).getFormulaName();
		doReturn("formulaId").when(nsfInfoBean).getFormulaId();
		
		doReturn(nsfInfoBean).when(netSalesFormulaGeneralRequest).getnSfInfoBean();
		
		doReturn("query").when(gtnWsSqlService).getQuery(Arrays.asList(Mockito.anyString()), Mockito.anyString());
		
		List<Integer> resultsDb = new ArrayList<>();
		resultsDb.add(5);
		
		doReturn(resultsDb).when(gtnSqlQueryEngine).executeSelectQuery("query");
		
		GtnUIFrameworkWebserviceResponse result = fixture.nsfDuplicateFormulaType(gtnWsRequest);

		assertNotNull(result);
	}
	@Test
	public void testNsfDuplicateFormulaType_2() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);

		SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		doReturn(session).when(sessionFactory).openSession();
		
		GtnWsNetSalesFormulaGeneralRequest netSalesFormulaGeneralRequest = Mockito.mock(GtnWsNetSalesFormulaGeneralRequest.class);
		doReturn(netSalesFormulaGeneralRequest).when(gtnWsRequest).getGtnWsNetSalesGeneralRequest();
		
		GtnUIFrameworkNsfInfoBean nsfInfoBean = Mockito.mock(GtnUIFrameworkNsfInfoBean.class);
		
		doReturn("formulaName").when(nsfInfoBean).getFormulaName();
		doReturn("formulaId").when(nsfInfoBean).getFormulaId();
		
		doReturn(nsfInfoBean).when(netSalesFormulaGeneralRequest).getnSfInfoBean();
		
		doReturn("query").when(gtnWsSqlService).getQuery(Arrays.asList(Mockito.anyString()), Mockito.anyString());
		
		List<Integer> resultsDb = new ArrayList<>();
		resultsDb.add(0);
		
		doReturn(resultsDb).when(gtnSqlQueryEngine).executeSelectQuery("query");
		
		GtnUIFrameworkWebserviceResponse result = fixture.nsfDuplicateFormulaType(gtnWsRequest);

		assertNotNull(result);
	}
	
	@Test
	public void testNsfDuplicateFormulaType_catch() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);
		gtnWsRequest.setGtnWsNetSalesGeneralRequest(new GtnWsNetSalesFormulaGeneralRequest());

		SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		doReturn(session).when(sessionFactory).openSession();
		
		GtnWsNetSalesFormulaGeneralRequest netSalesFormulaGeneralRequest = Mockito.mock(GtnWsNetSalesFormulaGeneralRequest.class);
		doReturn(netSalesFormulaGeneralRequest).when(gtnWsRequest).getGtnWsNetSalesGeneralRequest();
		
		GtnUIFrameworkNsfInfoBean nsfInfoBean = Mockito.mock(GtnUIFrameworkNsfInfoBean.class);
		
		doReturn("formulaName").when(nsfInfoBean).getFormulaName();
		doReturn("formulaId").when(nsfInfoBean).getFormulaId();
		
		//no bean passed 
		//doReturn(nsfInfoBean).when(netSalesFormulaGeneralRequest).getnSfInfoBean();
		
		GtnUIFrameworkWebserviceResponse result = fixture.nsfDuplicateFormulaType(gtnWsRequest);

		assertNotNull(result);
	}

	/**
	 * Run the void setSessionFactory(SessionFactory) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testSetSessionFactory_1() throws Exception {

		SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
		fixture.setSessionFactory(sessionFactory);

		
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