package com.stpl.gtn.gtn2o.ws.module.companygroups.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
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
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGroupBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpDataBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpValidationBean;
import com.stpl.gtn.gtn2o.ws.entity.companygroup.CompanyGroup;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemgroup.ItemGroup;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnItemGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.itemgroup.bean.GtnWsItemGroupBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.companygroup.GtnCompanyGroupRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsCompanyGrpServiceTest {
	@InjectMocks
	@Autowired
	GtnWsCompanyGrpService instance;
	@Mock
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	@Mock
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	private GtnUIFrameworkWebserviceRequest getCGReq() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnCompanyGroupRequest ws = new GtnCompanyGroupRequest();
		gtnWsRequest.setGtnCompanyGroupRequest(ws);

		List<GtnCompanyGrpDataBean> gtnWsItemGrpDataBeanList = new ArrayList<>();
		GtnCompanyGrpDataBean bean = new GtnCompanyGrpDataBean();
		gtnWsItemGrpDataBeanList.add(bean);
		GtnCompanyGroupBean gtnWsItemGroupBean = new GtnCompanyGroupBean();

		GtnCompanyGrpInformationBean itemGrpInfoBean = new GtnCompanyGrpInformationBean();
		itemGrpInfoBean.setCompanyGrpSid(0);
		gtnWsItemGroupBean.setGtnCompanyGrpInformationBean(itemGrpInfoBean);
		ws.setGtnCompanyGroupBean(gtnWsItemGroupBean);

		GtnWsGeneralRequest generalReq = new GtnWsGeneralRequest();
		generalReq.setUserId("12");
		generalReq.setSessionId("sessionid");
		ws.setGtnCompanyGrpDataBeanList(gtnWsItemGrpDataBeanList);
		gtnWsRequest.setGtnWsGeneralRequest(generalReq);
		return gtnWsRequest;
	}

	@Test
	public void testGetSessionFactory() {
		assertNotNull(instance.getSessionFactory());
	}

	@Test
	public void testAddCompanyQuery() throws GtnFrameworkGeneralException {
		instance.addCompanyQuery(getCGReq());
	}

	@Test(expected = Exception.class)
	public void testGetAddAllQuery() throws GtnFrameworkGeneralException {
		instance.getAddAllQuery(getCGReq());
	}

	@Test
	public void testGetRemoveQuery() throws GtnFrameworkGeneralException {
		instance.getRemoveQuery(getCGReq());
	}

	@Test(expected = Exception.class)
	public void testGetRemoveAllQuery() throws GtnFrameworkGeneralException {
		instance.getRemoveAllQuery(getCGReq());
	}

	@Test
	public void testGetCompanyGrpFetchQuery() throws GtnFrameworkGeneralException {
		Session s = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);
		doReturn(s).when(sessionFactory).openSession();
		doReturn(tran).when(s).beginTransaction();
		CompanyGroup itemGroup = new CompanyGroup();
		doReturn(itemGroup).when(s).get(CompanyGroup.class, 0);

		instance.getCompanyGrpFetchQuery(getCGReq(), new GtnUIFrameworkWebserviceResponse());
	}

	@Test
	public void testUpdateCompanyGrpDetails() throws GtnFrameworkGeneralException {
		instance.updateCompanyGrpDetails(getCGReq());
	}

	@Test(expected = Exception.class)
	public void testUpdateCompanyGroupDetails() throws GtnFrameworkGeneralException {
		instance.updateCompanyGroupDetails(getCGReq(), new GtnUIFrameworkWebserviceResponse());
	}

	@Test
	public void testGetCompanyGrpDeleteQuery() throws GtnFrameworkGeneralException {
		Session s = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);
		doReturn(s).when(sessionFactory).openSession();
		doReturn(tran).when(s).beginTransaction();
		CompanyGroup itemGroup = new CompanyGroup();
		doReturn(itemGroup).when(s).get(CompanyGroup.class, 0);
		instance.getCompanyGrpDeleteQuery(getCGReq());
	}

	@Test
	public void testSaveCompanyGrpQuery() throws GtnFrameworkGeneralException {
		GtnCompanyGroupBean bean = new GtnCompanyGroupBean();

		GtnCompanyGrpInformationBean info = new GtnCompanyGrpInformationBean();
		bean.setGtnCompanyGrpInformationBean(info);

		Session s = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);
		doReturn(s).when(sessionFactory).openSession();
		doReturn(tran).when(s).beginTransaction();
		doReturn(1).when(s).save(Mockito.any(CompanyGroup.class));

		instance.saveCompanyGrpQuery(bean);
	}

	@Test
	public void testUpdateCompanyGrpQuery() throws GtnFrameworkGeneralException {
		Session s = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);
		doReturn(s).when(sessionFactory).openSession();
		doReturn(tran).when(s).beginTransaction();
		CompanyGroup itemGroup = new CompanyGroup();
		doReturn(itemGroup).when(s).get(CompanyGroup.class, 0);
		GtnCompanyGroupBean bean = new GtnCompanyGroupBean();

		GtnCompanyGrpInformationBean info = new GtnCompanyGrpInformationBean();
		info.setCompanyGrpSid(0);
		bean.setGtnCompanyGrpInformationBean(info);

		instance.updateCompanyGrpQuery(bean);
	}

	@Test
	public void testUpdateCompanyGrpDetailsTable() throws GtnFrameworkGeneralException  {
		GtnCompanyGroupBean bean = new GtnCompanyGroupBean();

		GtnCompanyGrpInformationBean info = new GtnCompanyGrpInformationBean();
		info.setCompanyGrpSid(0);
		bean.setGtnCompanyGrpInformationBean(info);
		instance.updateCompanyGrpDetailsTable(getCGReq(), 1, bean);
	}

	@Test(expected = Exception.class)
	public void testGetCompanyGroupImtdCount() throws GtnFrameworkGeneralException {
		Session s = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);
		doReturn(s).when(sessionFactory).openSession();
		doReturn(tran).when(s).beginTransaction();
		GtnCompanyGrpValidationBean  val=new GtnCompanyGrpValidationBean();
		GtnUIFrameworkWebserviceRequest gtnWsRequest =getCGReq();
		gtnWsRequest.getGtnCompanyGroupRequest().setGtnCompanyGrpValidationBean(val);
		instance.getCompanyGroupImtdCount(gtnWsRequest, new GtnUIFrameworkWebserviceResponse());
	}
	@Test(expected = Exception.class)
	public void testGetCompanyGroupNameValidation() throws GtnFrameworkGeneralException {
		instance.getCompanyGroupNameValidation(getCGReq(), new GtnUIFrameworkWebserviceResponse());
	}

	public void testGetSelectedTableData() throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest gtnWsRequest =getCGReq();
		gtnWsRequest.setGtnWsSearchRequest(new GtnWsSearchRequest());
		instance.getSelectedTableData(gtnWsRequest);
	}

	@Test
	public void testClearCustomerGroupTempTable()throws GtnFrameworkGeneralException  {
		instance.clearCustomerGroupTempTable(getCGReq());
	}

}
