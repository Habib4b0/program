package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.itemfamilyplan.IfpModel;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;

/**
 * The class <code>GtnWsIfpSaveServiceTest</code> contains tests for the class
 * <code>{@link GtnWsIfpSaveService}</code>.
 *
 * 
 * @author Spandan.Majumder
 * @version $Revision: 1.0 $
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsIfpSaveServiceTest {
	@InjectMocks
	@Autowired
	GtnWsIfpSaveService fixture;

	@Mock
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

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
	public void testGtnWsIfpSaveService_1() throws Exception {

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
	public void testGetSessionFactory_1() throws Exception {
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
	public void testSaveCfpDetailsQuery_1() throws Exception {

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
	public void testSaveCfpQuery_1() throws Exception {
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnWsIfpSaveService fix = Mockito.spy(fixture);
		HelperTable ht = new HelperTable();

		doReturn(ht).when(fix).getHelperTable(Mockito.any(Integer.class), Mockito.any(Session.class));
		Session s = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);

		doReturn(s).when(sessionFactory).openSession();
		doReturn(1).when(s).save(Mockito.any(IfpModel.class));
		doReturn(tran).when(s).beginTransaction();

		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setCreatedBy("10");
		gtnIFamilyPlan.setIfpInfo(ifpInfo);

		int result = fix.saveCfpQuery(gtnIFamilyPlan);
		assertEquals(1, result);
	}

	@Test
	public void testSaveCfpQuery_catchBlock() throws Exception {
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnWsIfpSaveService fix = Mockito.spy(fixture);
		HelperTable ht = new HelperTable();

		doReturn(ht).when(fix).getHelperTable(Mockito.any(Integer.class), Mockito.any(Session.class));
		Session s = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);

		doReturn(s).when(sessionFactory).openSession();
		Session s1 = Mockito.mock(Session.class);
		doReturn(1).when(s1).save(Mockito.any(IfpModel.class));
		doReturn(tran).when(s).beginTransaction();

		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setCreatedBy("10");
		gtnIFamilyPlan.setIfpInfo(ifpInfo);

		try {
			fix.saveCfpQuery(gtnIFamilyPlan);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testnotesTabInsert() throws Exception {

		GtnWsIfpSaveService gtnWsIfpSaveService = new GtnWsIfpSaveService();

		NotesTabBean ifpNotesBean = new NotesTabBean();
		ifpNotesBean.setCreatedBy(1);
		ifpNotesBean.setFilePath("filepath");
		ifpNotesBean.setMasterTableName("master");

		List<NotesTabBean> notesTabBeansList = new ArrayList<NotesTabBean>();
		notesTabBeansList.add(ifpNotesBean);

		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setCreatedBy("createBy");
		ifpInfo.setCreatedDate(new Date());
		ifpInfo.setIfpCategory(1);
		ifpInfo.setIfpDesignation(2);
		ifpInfo.setIfpEndDate(new Date());
		ifpInfo.setIfpId("id");
		ifpInfo.setIfpName("name");
		ifpInfo.setIfpNo("no");
		ifpInfo.setIfpSid(3);
		ifpInfo.setIfpStartDate(new Date());
		ifpInfo.setIfpStatus(4);
		ifpInfo.setIfpType(5);
		ifpInfo.setInternalNotes("note");
		ifpInfo.setModifiedBy("modifiedBy");
		ifpInfo.setModifiedDate(new Date());
		ifpInfo.setParentIfpId("pid");
		ifpInfo.setRecordLockStatus(false);

		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnIFamilyPlan.setNotesTabList(notesTabBeansList);

		List<Object[]> queryInputList1 = new ArrayList<>();
		doReturn(queryInputList1).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString());

		try {
			gtnWsIfpSaveService.notesTabInsert(gtnIFamilyPlan);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testnotesTabInsert_i_isNot0() throws Exception {

		GtnWsIfpSaveService gtnWsIfpSaveService = new GtnWsIfpSaveService();

		NotesTabBean ifpNotesBean = new NotesTabBean();
		ifpNotesBean.setCreatedBy(1);
		ifpNotesBean.setFilePath("filepath");
		ifpNotesBean.setMasterTableName("master");

		NotesTabBean ifpNotesBean1 = new NotesTabBean();
		ifpNotesBean1.setCreatedBy(2);
		ifpNotesBean1.setFilePath("filepath");
		ifpNotesBean1.setMasterTableName("master");

		List<NotesTabBean> notesTabBeansList = new ArrayList<NotesTabBean>();
		notesTabBeansList.add(ifpNotesBean);
		notesTabBeansList.add(ifpNotesBean1);

		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setCreatedBy("createBy");
		ifpInfo.setCreatedDate(new Date());
		ifpInfo.setIfpCategory(1);
		ifpInfo.setIfpDesignation(2);
		ifpInfo.setIfpEndDate(new Date());
		ifpInfo.setIfpId("id");
		ifpInfo.setIfpName("name");
		ifpInfo.setIfpNo("no");
		ifpInfo.setIfpSid(3);
		ifpInfo.setIfpStartDate(new Date());
		ifpInfo.setIfpStatus(4);
		ifpInfo.setIfpType(5);
		ifpInfo.setInternalNotes("note");
		ifpInfo.setModifiedBy("modifiedBy");
		ifpInfo.setModifiedDate(new Date());
		ifpInfo.setParentIfpId("pid");
		ifpInfo.setRecordLockStatus(false);

		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		gtnIFamilyPlan.setNotesTabList(notesTabBeansList);

		List<Object[]> queryInputList1 = new ArrayList<>();
		doReturn(queryInputList1).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString());

		try {
			gtnWsIfpSaveService.notesTabInsert(gtnIFamilyPlan);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testnotesTabInsert_notesTabRequestList_isEmpty() throws Exception {

		GtnWsIfpSaveService gtnWsIfpSaveService = new GtnWsIfpSaveService();

		List<NotesTabBean> notesTabBeansList = new ArrayList<NotesTabBean>();

		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		gtnIFamilyPlan.setNotesTabList(notesTabBeansList);

		List<Object[]> queryInputList1 = new ArrayList<>();
		doReturn(queryInputList1).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString());

		try {
			gtnWsIfpSaveService.notesTabInsert(gtnIFamilyPlan);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// @Test
	// public void testnotesTabAttachmentForIfp()
	// throws Exception {
	//
	// GtnWsIfpSaveService gtnWsIfpSaveService = new GtnWsIfpSaveService();
	//
	// Session session = Mockito.mock(Session.class);
	// Transaction transaction = Mockito.mock(Transaction.class);
	//
	//// doReturn(sessionFactory).when(gtnWsIfpSaveService).getSessionFactory();
	// doReturn(session).when(sessionFactory).openSession();
	// doReturn(transaction).when(session).beginTransaction();
	//
	// NotesTabBean ifpNotesBean = new NotesTabBean();
	// ifpNotesBean.setCreatedBy(1);
	// ifpNotesBean.setFilePath("filepath");
	// ifpNotesBean.setMasterTableName("master");
	//
	// List<NotesTabBean> notesTabBeansList = new ArrayList<NotesTabBean>();
	// notesTabBeansList.add(ifpNotesBean);
	//
	// GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
	// GtnIFamilyPlanInformationBean ifpInfo = new
	// GtnIFamilyPlanInformationBean();
	// ifpInfo.setCreatedBy("createBy");
	// ifpInfo.setCreatedDate(new Date());
	// ifpInfo.setIfpCategory(1);
	// ifpInfo.setIfpDesignation(2);
	// ifpInfo.setIfpEndDate(new Date());
	// ifpInfo.setIfpId("id");
	// ifpInfo.setIfpName("name");
	// ifpInfo.setIfpNo("no");
	// ifpInfo.setIfpSid(3);
	// ifpInfo.setIfpStartDate(new Date());
	// ifpInfo.setIfpStatus(4);
	// ifpInfo.setIfpType(5);
	// ifpInfo.setInternalNotes("note");
	// ifpInfo.setModifiedBy("modifiedBy");
	// ifpInfo.setModifiedDate(new Date());
	// ifpInfo.setParentIfpId("pid");
	// ifpInfo.setRecordLockStatus(false);
	//
	// gtnIFamilyPlan.setIfpInfo(ifpInfo);
	// gtnIFamilyPlan.setNotesTabList(notesTabBeansList);
	//
	// List<Object[]> queryInputList1 = new ArrayList<>();
	// doReturn(queryInputList1).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString());
	//
	// gtnWsIfpSaveService.notesTabAttachmentForIfp(gtnIFamilyPlan);
	//
	// }

	@Test
	public void testSaveNotesTabDetails_1() throws Exception {
		GtnIFamilyPlanBean ruleInfoBean = new GtnIFamilyPlanBean();
		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpSid(1);
		ruleInfoBean.setIfpInfo(ifpInfo);

		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnWsIfpSaveService fix = Mockito.spy(fixture);
		HelperTable ht = new HelperTable();

		doReturn(ht).when(fix).getHelperTable(Mockito.any(Integer.class), Mockito.any(Session.class));
		Session s = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);

		doReturn(s).when(sessionFactory).openSession();

		doReturn(sessionFactory).when(fix).getSessionFactory();
		doReturn(1).when(s).save(Mockito.any(IfpModel.class));
		doReturn(tran).when(s).beginTransaction();

		fixture.saveNotesTabDetails(ruleInfoBean);

	}

	/**
	 * Run the void updateCfpQuery(GtnIFamilyPlanBean) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testUpdateCfpQuery_1() throws Exception {
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		GtnWsIfpSaveService fix = Mockito.spy(fixture);
		HelperTable ht = new HelperTable();

		doReturn(ht).when(fix).getHelperTable(Mockito.any(Integer.class), Mockito.any(Session.class));
		Session s = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);

		doReturn(s).when(sessionFactory).openSession();

		doReturn(sessionFactory).when(fix).getSessionFactory();
		doReturn(1).when(s).save(Mockito.any(IfpModel.class));
		doReturn(tran).when(s).beginTransaction();

		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setCreatedBy("10");
		gtnIFamilyPlan.setIfpInfo(ifpInfo);

		IfpModel updateIfpModel = new IfpModel();
		doReturn(updateIfpModel).when(s).get(IfpModel.class, ifpInfo.getIfpSid());

		fix.updateCfpQuery(gtnIFamilyPlan);

	}

	/**
	 * Run the String updateFieldsQuery(GtnUIFrameworkWebserviceRequest) method
	 * test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testUpdateFieldsQuery_1() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
		updateBean.setColumnName("itemFamilyPlanStatus");
		updateBean.setValue("1");
		updateBean.setClassType(String.class.getName());
		gtnIFamilyPlan.setUpdateBean(updateBean);
		fixture.updateFieldsQuery(gtnWsRequest);

		updateBean.setClassType("java.util.Date");
		updateBean.setColumnName("itemFamilyPlanStartDate");
		updateBean.setValue(Long.MAX_VALUE);
		String result = fixture.updateFieldsQuery(gtnWsRequest);

		updateBean.setClassType("java.util.Date");
		updateBean.setColumnName("itemFamilyPlanEndDate");
		updateBean.setValue(Long.MAX_VALUE);
		fixture.updateFieldsQuery(gtnWsRequest);

		updateBean.setClassType(String.class.getName());
		updateBean.setColumnName("checkRecordId");
		updateBean.setValue("0");
		fixture.updateFieldsQuery(gtnWsRequest);

		updateBean.setImtdIfpDetailsSid("1");
		updateBean.setClassType(String.class.getName());
		updateBean.setColumnName("default");
		updateBean.setValue("0");
		fixture.updateFieldsQuery(gtnWsRequest);

		assertNotNull(result);
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
		MockitoAnnotations.initMocks(this);
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

	}

}
