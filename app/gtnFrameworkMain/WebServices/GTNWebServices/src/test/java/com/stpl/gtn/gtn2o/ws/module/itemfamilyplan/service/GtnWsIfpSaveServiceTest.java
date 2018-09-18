package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.itemfamilyplan.IfpModel;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import org.junit.Ignore;

/**
 * The class <code>GtnWsIfpSaveServiceTest</code> contains tests for the class <code>{@link GtnWsIfpSaveService}</code>.
 *
 * 
 * @author KARTHIK.RAJA
 * @version $Revision: 1.0 $
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnWsIfpSaveServiceTest {
	@InjectMocks
	@Autowired
	GtnWsIfpSaveService fixture ;
	
	@Mock
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	
	@Mock
	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Mock
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;
	/**
	 * Run the GtnWsIfpSaveService() constructor test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnWsIfpSaveService_1()
		throws Exception {

		GtnWsIfpSaveService result = new GtnWsIfpSaveService();

		
		assertNotNull(result);
		assertEquals(null, result.getSessionFactory());
	}

	/**
	 * Run the SessionFactory getSessionFactory() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetSessionFactory_1()
		throws Exception {
		GtnWsIfpSaveService fixture = new GtnWsIfpSaveService();

		SessionFactory result = fixture.getSessionFactory();

		
		assertEquals(null, result);
	}

	/**
	 * Run the String saveCfpDetailsQuery() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testSaveCfpDetailsQuery_1()
		throws Exception {

		String result = fixture.saveCfpDetailsQuery();

	}

	/**
	 * Run the int saveCfpQuery(GtnIFamilyPlanBean) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testSaveCfpQuery_1()
		throws Exception {
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnWsIfpSaveService fix=Mockito.spy(fixture);
		HelperTable ht=new HelperTable();
		
		doReturn(ht).when(fix).getHelperTable(Mockito.any(Integer.class),Mockito.any(Session.class));
		Session s=Mockito.mock(Session.class);
		Transaction tran=Mockito.mock(Transaction.class);
		
		doReturn(s).when(sessionFactory).openSession();
		doReturn(1).when(s).save(Mockito.any(IfpModel.class));
		doReturn(tran).when(s).beginTransaction();
		
		GtnIFamilyPlanInformationBean ifpInfo=new GtnIFamilyPlanInformationBean();
		ifpInfo.setCreatedBy("10");
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		


		int result = fix.saveCfpQuery(gtnIFamilyPlan);

		
		assertEquals(1, result);
	}



//	/**
//	 * Run the void saveNotesTabDetails(GtnIFamilyPlanBean) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testSaveNotesTabDetails_1()
//		throws Exception {
//		GtnWsIfpSaveService fixture = new GtnWsIfpSaveService();
//		GtnIFamilyPlanBean ruleInfoBean = new GtnIFamilyPlanBean();
//
//		fixture.saveNotesTabDetails(ruleInfoBean);
//
//	}

//
//	/**
//	 * Run the void updateCfpQuery(GtnIFamilyPlanBean) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testUpdateCfpQuery_1()
//		throws Exception {
//		GtnWsIfpSaveService fixture = new GtnWsIfpSaveService();
//		GtnIFamilyPlanBean bean = new GtnIFamilyPlanBean();
//
//		fixture.updateCfpQuery(bean);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpSaveService.updateCfpQuery(GtnWsIfpSaveService.java:108)
//	}
//
//
//	/**
//	 * Run the String updateFieldsQuery(GtnUIFrameworkWebserviceRequest) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testUpdateFieldsQuery_1()
//		throws Exception {
//		GtnWsIfpSaveService fixture = new GtnWsIfpSaveService();
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//
//		String result = fixture.updateFieldsQuery(gtnWsRequest);
//
//		
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpSaveService.updateFieldsQuery(GtnWsIfpSaveService.java:146)
//		assertNotNull(result);
//	}



	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * 
	 */
	@Before
	public void setUp()
		throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * 
	 */
	@After
	public void tearDown()
		throws Exception {
		
	}

	
}
