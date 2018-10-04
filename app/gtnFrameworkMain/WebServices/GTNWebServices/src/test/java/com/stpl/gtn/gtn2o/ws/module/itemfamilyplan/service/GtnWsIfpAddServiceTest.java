package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.entity.itemfamilyplan.IfpModel;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsIfpAddServiceTest {

	@InjectMocks
	@Autowired
	GtnWsIfpAddService fixture;

	@Mock
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Test
	public void testGtnWsIfpAddService_1() throws Exception {

		GtnWsIfpAddService result = new GtnWsIfpAddService();

		assertNotNull(result);
		assertEquals(null, result.getSessionFactory());
	}

	@Test
	public void testCheckAllItems_1() throws Exception {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);

		GtnWsGeneralRequest getGtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsRequest.setGtnWsGeneralRequest(getGtnWsGeneralRequest);
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
		updateBean.setColumnName("itemFamilyPlanStatus");
		updateBean.setValue(true);
		updateBean.setClassType(String.class.getName());
		gtnIFamilyPlan.setUpdateBean(updateBean);

		int result = fixture.checkAllItems(gtnWsRequest);

		assertEquals(0, result);
	}

	@Test
	public void testCompaniesUpdateColumnQuery_1() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);

		GtnWsGeneralRequest getGtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsRequest.setGtnWsGeneralRequest(getGtnWsGeneralRequest);
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
		updateBean.setColumnName("itemFamilyPlanStatus");
		updateBean.setValue(true);
		updateBean.setClassType(String.class.getName());
		gtnIFamilyPlan.setUpdateBean(updateBean);

		String result = fixture.companiesUpdateColumnQuery(gtnWsRequest);

		assertNotNull(result);
	}

	@Test
	public void testCompaniesUpdateColumnQuery_2() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);

		GtnWsGeneralRequest getGtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsRequest.setGtnWsGeneralRequest(getGtnWsGeneralRequest);
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
		updateBean.setColumnName("IFP Start Date");
		updateBean.setValue(true);
		updateBean.setClassType(String.class.getName());
		gtnIFamilyPlan.setUpdateBean(updateBean);

		String result = fixture.companiesUpdateColumnQuery(gtnWsRequest);

		assertNotNull(result);
	}

	@Test
	public void testCompaniesUpdateColumnQuery_3() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);

		GtnWsGeneralRequest getGtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsRequest.setGtnWsGeneralRequest(getGtnWsGeneralRequest);
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
		updateBean.setColumnName("IFP End Date");
		updateBean.setImtdIfpDetailsSid("1");
		updateBean.setPopulateType("populate");
		updateBean.setValue(true);
		updateBean.setClassType(String.class.getName());
		gtnIFamilyPlan.setUpdateBean(updateBean);

		String result = fixture.companiesUpdateColumnQuery(gtnWsRequest);

		assertNotNull(result);
	}

	@Test
	public void testCompaniesUpdateColumnQuery_4() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsIfpRequest gtnWsIfpRequest = new GtnWsIfpRequest();
		gtnWsRequest.setGtnWsIfpRequest(gtnWsIfpRequest);

		GtnWsGeneralRequest getGtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsRequest.setGtnWsGeneralRequest(getGtnWsGeneralRequest);
		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();
		gtnWsIfpRequest.setGtnIFamilyPlan(gtnIFamilyPlan);
		GtnIFamilyPlanCommonUpdateBean updateBean = new GtnIFamilyPlanCommonUpdateBean();
		updateBean.setColumnName("IFP End Date");
		updateBean.setImtdIfpDetailsSid("");
		updateBean.setPopulateType("populate");
		updateBean.setValue(true);
		updateBean.setClassType(String.class.getName());
		gtnIFamilyPlan.setUpdateBean(updateBean);

		String result = fixture.companiesUpdateColumnQuery(gtnWsRequest);

		assertNotNull(result);
	}

	@Test
	public void testgetFrequencyQuery_1() throws Exception {

		boolean cuurent = true;
		boolean history = true;
		boolean future = true;

		String result = fixture.getFrequencyQuery(cuurent, history, future);
		assertNotNull(result);
	}

	@Test
	public void testgetFrequencyQuery_2() throws Exception {

		boolean cuurent = true;
		boolean history = true;
		boolean future = false;

		String result = fixture.getFrequencyQuery(cuurent, history, future);
		assertNotNull(result);
	}

	@Test
	public void testgetFrequencyQuery_3() throws Exception {

		boolean cuurent = false;
		boolean history = true;
		boolean future = true;

		String result = fixture.getFrequencyQuery(cuurent, history, future);
		assertNotNull(result);
	}

	@Test
	public void testgetFrequencyQuery_4() throws Exception {

		boolean cuurent = true;
		boolean history = false;
		boolean future = false;

		String result = fixture.getFrequencyQuery(cuurent, history, future);
		assertNotNull(result);
	}

	@Test
	public void testgetFrequencyQuery_5() throws Exception {

		boolean cuurent = false;
		boolean history = true;
		boolean future = false;

		String result = fixture.getFrequencyQuery(cuurent, history, future);
		assertNotNull(result);
	}

	@Test
	public void testgetFrequencyQuery_6() throws Exception {

		boolean cuurent = false;
		boolean history = false;
		boolean future = true;

		String result = fixture.getFrequencyQuery(cuurent, history, future);
		assertNotNull(result);
	}

	@Test
	public void testgetFrequencyQuery_7() throws Exception {

		boolean cuurent = true;
		boolean history = false;
		boolean future = true;

		String result = fixture.getFrequencyQuery(cuurent, history, future);
		assertNotNull(result);
	}

	@Test
	public void testgetFrequencyQuery_8() throws Exception {

		boolean cuurent = false;
		boolean history = false;
		boolean future = false;

		String result = fixture.getFrequencyQuery(cuurent, history, future);
		assertNotNull(result);
	}

	@Test
	public void testDeleteIfpModel_1() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsIfpRequest req = new GtnWsIfpRequest();

		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();

		Session session = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);
		IfpModel ifpModel = new IfpModel();

		SessionFactory sessionFactory1 = Mockito.mock(SessionFactory.class);

		doReturn(session).when(sessionFactory1).openSession();
		doReturn(tran).when(session).beginTransaction();
		req.setGtnIFamilyPlan(gtnIFamilyPlan);
		gtnWsRequest.setGtnWsIfpRequest(req);

		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpSid(1);
		gtnIFamilyPlan.setIfpInfo(ifpInfo);

		doReturn(ifpModel).when(session).get(IfpModel.class, 1);

		fixture.deleteIfpModel(gtnWsRequest);

	}

	@Test
	public void testGetDbColumnForFilterField_1() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item ID";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("IM.ITEM_ID", result);
	}

	@Test
	public void testGetDbColumnForFilterField_2() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item Desc";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("IM.ITEM_DESC", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetDbColumnForFilterField_3() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item No";
		String result = fixture.getDbColumnForFilterField(filterValue1);
		assertEquals("IM.ITEM_NO", result);
	}

	@Test
	public void testGetDbColumnForFilterField_4() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item Name";
		String result = fixture.getDbColumnForFilterField(filterValue1);
		assertEquals("IM.ITEM_NAME", result);
	}

	@Test
	public void testGetDbColumnForFilterField_5() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item Status";
		String result = fixture.getDbColumnForFilterField(filterValue1);
		assertEquals("STA.DESCRIPTION", result);
	}

	@Test
	public void testGetDbColumnForFilterField_6() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Strength";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("STR.DESCRIPTION", result);
	}

	@Test
	public void testGetDbColumnForFilterField_7() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Therapeutic Class";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("TC.DESCRIPTION", result);
	}

	@Test
	public void testGetDbColumnForFilterField_8() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Form";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("FO.DESCRIPTION", result);
	}

	@Test
	public void testGetDbColumnForFilterField_9() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Brand";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("BM.BRAND_NAME", result);
	}

	@Test
	public void testGetDbColumnForFilterField_10() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "UOM";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("UOM.DESCRIPTION", result);
	}

	@Test
	public void testGetDbColumnForFilterField_11() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("", result);
	}

	@Test
	public void testGetDbColumnForFilterField_12() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = null;

		try {
			String result = fixture.getDbColumnForFilterField(filterValue1);
			assertEquals("", result);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testGetIfpFetchQuery_1() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsIfpRequest req = new GtnWsIfpRequest();

		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();

		Session s = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);
		IfpModel ifpModel = new IfpModel();

		SessionFactory sessionFactory1 = Mockito.mock(SessionFactory.class);
		doReturn(s).when(sessionFactory1).openSession();
		doReturn(ifpModel).when(s).get(IfpModel.class, 1);
		doReturn(tran).when(s).beginTransaction();
		req.setGtnIFamilyPlan(gtnIFamilyPlan);
		gtnWsRequest.setGtnWsIfpRequest(req);

		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpSid(1);
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
  
		try {
			fixture.getIfpFetchQuery(gtnWsRequest, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testGetIfpMoveAllLeftQuery_1() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = getSampleWsRequest_1("1", "2");

		try {
			String result = fixture.getIfpMoveAllLeftQuery(gtnWsRequest);
			assertNotNull(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGetIfpMoveAllLeftQuery_2() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = getSampleWsRequest_2("1", "2");

		try {
			String result = fixture.getIfpMoveAllLeftQuery(gtnWsRequest);
			assertNotNull(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testGetIfpMoveAllLeftQuery_3() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = getSampleWsRequest_3("1", "2");

		String result = fixture.getIfpMoveAllLeftQuery(gtnWsRequest);

		assertNotNull(result);
	}

	@Test
	public void testGetIfpMoveAllLeftQuery_4() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = getSampleWsRequest_4("1", "2");

		String result = fixture.getIfpMoveAllLeftQuery(gtnWsRequest);

		assertNotNull(result);
	}

	@Test
	public void testGetIfpMoveAllLeftQuery_5() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = getSampleWsRequest_4("1", "2");

		String result = fixture.getIfpMoveAllLeftQuery(gtnWsRequest);

		assertNotNull(result);
	}

	private GtnUIFrameworkWebserviceRequest getSampleWsRequest_1(String filtervalue1, String filtervalue2) {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest request = new GtnWsSearchRequest();
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria in1 = new GtnWebServiceSearchCriteria();
		in1.setFieldId("Field");
		in1.setFilterValue1(filtervalue1);
		GtnWebServiceSearchCriteria in2 = new GtnWebServiceSearchCriteria();
		in2.setFilterValue2(filtervalue2);
		in2.setFieldId("Value");
		gtnWebServiceSearchCriteriaList.add(in1);
		gtnWebServiceSearchCriteriaList.add(in2);
		request.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
		GtnWebServiceOrderByCriteria orderby = new GtnWebServiceOrderByCriteria();
		orderby.setPropertyId("test");
		orderby.setOrderByCriteria("ASC");
		gtnWebServiceOrderByCriteriaList.add(orderby);
		request.setTableRecordOffset(1);
		request.setTableRecordStart(0);
		request.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
		gtnWsRequest.setGtnWsSearchRequest(request);
		return gtnWsRequest;
	}

	private GtnUIFrameworkWebserviceRequest getSampleWsRequest_2(String filtervalue1, String filtervalue2) {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest request = new GtnWsSearchRequest();

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();

		GtnWebServiceSearchCriteria in1 = new GtnWebServiceSearchCriteria();
		in1.setFieldId("Field");
		in1.setFilterValue1(filtervalue1);
		in1.setExpression("");

		GtnWebServiceSearchCriteria in2 = new GtnWebServiceSearchCriteria();
		in2.setFilterValue2(filtervalue2);
		in2.setFieldId("Value");
		in2.setExpression("");

		GtnWebServiceSearchCriteria in3 = new GtnWebServiceSearchCriteria();
		in2.setFilterValue2(filtervalue2);
		in2.setFieldId("else");
		in3.setExpression("");

		gtnWebServiceSearchCriteriaList.add(in1);
		gtnWebServiceSearchCriteriaList.add(in2);
		gtnWebServiceSearchCriteriaList.add(in3);

		request.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
		GtnWebServiceOrderByCriteria orderby = new GtnWebServiceOrderByCriteria();
		orderby.setPropertyId("test");
		orderby.setOrderByCriteria("ASC");
		gtnWebServiceOrderByCriteriaList.add(orderby);
		request.setTableRecordOffset(1);
		request.setTableRecordStart(0);
		request.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
		gtnWsRequest.setGtnWsSearchRequest(request);
		return gtnWsRequest;
	}

	private GtnUIFrameworkWebserviceRequest getSampleWsRequest_3(String filtervalue1, String filtervalue2) {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest request = new GtnWsSearchRequest();

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = null;

		request.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
		GtnWebServiceOrderByCriteria orderby = new GtnWebServiceOrderByCriteria();
		orderby.setPropertyId("test");
		orderby.setOrderByCriteria("ASC");
		gtnWebServiceOrderByCriteriaList.add(orderby);
		request.setTableRecordOffset(1);
		request.setTableRecordStart(0);
		request.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
		gtnWsRequest.setGtnWsSearchRequest(request);
		return gtnWsRequest;
	}

	private GtnUIFrameworkWebserviceRequest getSampleWsRequest_4(String filtervalue1, String filtervalue2) {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest request = new GtnWsSearchRequest();

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();

		request.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
		GtnWebServiceOrderByCriteria orderby = new GtnWebServiceOrderByCriteria();
		orderby.setPropertyId("test");
		orderby.setOrderByCriteria("ASC");
		gtnWebServiceOrderByCriteriaList.add(orderby);
		request.setTableRecordOffset(1);
		request.setTableRecordStart(0);
		request.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
		gtnWsRequest.setGtnWsSearchRequest(request);
		return gtnWsRequest;
	}

	private GtnUIFrameworkWebserviceRequest getSampleWsRequest_5(String filtervalue1, String filtervalue2) {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest request = new GtnWsSearchRequest();
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria in1 = new GtnWebServiceSearchCriteria();
		in1.setFieldId("Field");
		in1.setFilterValue1(filtervalue1);
		GtnWebServiceSearchCriteria in2 = new GtnWebServiceSearchCriteria();
		in2.setFilterValue2(filtervalue2);
		in2.setFieldId("Value");
		in2.setExpression("");
		gtnWebServiceSearchCriteriaList.add(in1);
		gtnWebServiceSearchCriteriaList.add(in2);
		request.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
		GtnWebServiceOrderByCriteria orderby = new GtnWebServiceOrderByCriteria();
		orderby.setPropertyId("test");
		orderby.setOrderByCriteria("ASC");
		gtnWebServiceOrderByCriteriaList.add(orderby);
		request.setTableRecordOffset(1);
		request.setTableRecordStart(0);
		request.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
		gtnWsRequest.setGtnWsSearchRequest(request);
		return gtnWsRequest;
	}

	@Test
	public void testGetIfpMoveAllRightQuery_1() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = getSampleWsRequest_1("1", "2");

		String result = fixture.getIfpMoveAllRightQuery(gtnWsRequest);

		assertNotNull(result);
	}

	@Test
	public void testGetImAdditionTabColumns_1() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemId";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("IM.ITEM_ID", result);
	}

	@Test
	public void testGetImAdditionTabColumns_2() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemNo";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("IM.ITEM_NO", result);
	}

	@Test
	public void testGetImAdditionTabColumns_3() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemName";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("IM.ITEM_NAME", result);
	}

	@Test
	public void testGetImAdditionTabColumns_4() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemDesc";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("IM.ITEM_DESC", result);
	}

	@Test
	public void testGetImAdditionTabColumns_5() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemStatus";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("STA.DESCRIPTION", result);
	}

	@Test
	public void testGetImAdditionTabColumns_6() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "form";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("FO.DESCRIPTION", result);
	}

	@Test
	public void testGetImAdditionTabColumns_7() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "strength";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("STR.DESCRIPTION", result);
	}

	@Test
	public void testGetImAdditionTabColumns_8() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "therapeuticClass";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("TC.DESCRIPTION", result);
	}

	@Test
	public void testGetImAdditionTabColumns_9() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "brand";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("BM.BRAND_NAME", result);
	}

	@Test
	public void testGetImAdditionTabColumns_10() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "UOM";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("UOM.DESCRIPTION", result);
	}

	@Test
	public void testGetImAdditionTabColumns_11() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "therapeutic Class";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("TC.DESCRIPTION", result);
	}

	@Test
	public void testGetImAdditionTabColumns_12() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("", result);
	}

	@Test
	public void testGetItemsTabColumns_1() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemNo";
		String result = fixture.getItemsTabColumns(property);
		assertEquals("ICD.ITEM_NO", result);
	}

	@Test
	public void testGetItemsTabColumns_2() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemName";
		String result = fixture.getItemsTabColumns(property);
		assertEquals("ICD.ITEM_NAME", result);
	}

	@Test
	public void testGetItemsTabColumns_3() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemDesc";
		String result = fixture.getItemsTabColumns(property);
		assertEquals("ICD.ITEM_DESC", result);
	}

	@Test
	public void testGetItemsTabColumns_4() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemFamilyPlanStatus";
		String result = fixture.getItemsTabColumns(property);
		assertEquals("ICD.IFP_DETAILS_ATTACHED_STATUS", result);
	}

	@Test
	public void testGetItemsTabColumns_5() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemFamilyPlanStartDate";
		String result = fixture.getItemsTabColumns(property);
		assertEquals("ICD.IFP_DETAILS_START_DATE", result);
	}

	@Test
	public void testGetItemsTabColumns_6() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemFamilyPlanEndDate";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_END_DATE", result);
	}

	@Test
	public void testGetItemsTabColumns_7() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemStatusValue";
		String result = fixture.getItemsTabColumns(property);
		assertEquals("STA.DESCRIPTION", result);
	}

	@Test
	public void testGetItemsTabColumns_8() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "from";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("FO.DESCRIPTION", result);
	}

	@Test
	public void testGetItemsTabColumns_9() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "strength";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("STR.DESCRIPTION", result);
	}

	@Test
	public void testGetItemsTabColumns_10() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "therapeuticClass";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("TC.DESCRIPTION", result);
	}

	@Test
	public void testGetItemsTabColumns_11() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "brand";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("BM.BRAND_NAME", result);
	}

	@Test
	public void testGetItemsTabColumns_12() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "ifpAttachedDate";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_ATTACHED_DATE", result);
	}

	@Test
	public void testGetItemsTabColumns_13() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "modifiedDate";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_MODIFIED_DATE", result);
	}

	@Test
	public void testGetItemsTabColumns_14() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "modifiedBy";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_MODIFIED_BY", result);
	}

	@Test
	public void testGetItemsTabColumns_15() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "createdDate";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_CREATED_DATE", result);
	}

	@Test
	public void testGetItemsTabColumns_16() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("", result);
	}

	@Test
	public void testGetItemsTabColumns_17() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "createdBy";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_CREATED_BY", result);
	}

	@Test
	public void testGetItemsTabColumns_18() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "checkRecordId";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.CHECK_BOX", result);
	}

	@Test
	public void testGetSessionFactory_1() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();

		SessionFactory result = fixture.getSessionFactory();

		assertEquals(null, result);
	}

	@Test
	public void testGetWhereClauseForAColumn_2() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String expersion = "LIKE";
		String field = "";
		String field2 = "";
		String value = "";

		String result = fixture.getWhereClauseForAColumn(expersion, field, field2, value);

		assertEquals(" LIKE '%%' ", result);
	}

	@Test
	public void testGetWhereClauseForAColumn_3() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String expersion = "EQUAL";
		String field = "";
		String field2 = "";
		String value = "";

		String result = fixture.getWhereClauseForAColumn(expersion, field, field2, value);

		assertEquals(" = '' ", result);
	}

	@Test
	public void testGetWhereClauseForAColumn_4() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String expersion = "EQUALS";
		String field = "";
		String field2 = "";
		String value = "";

		String result = fixture.getWhereClauseForAColumn(expersion, field, field2, value);

		assertEquals(" ='' ", result);
	}

	@Test
	public void testGetWhereClauseForAColumn_5() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String expersion = "GREATER";
		String field = "";
		String field2 = "";
		String value = "";

		String result = fixture.getWhereClauseForAColumn(expersion, field, field2, value);

		assertEquals(" > '' ", result);
	}

	@Test
	public void testGetWhereClauseForAColumn_6() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String expersion = "LESS";
		String field = "";
		String field2 = "";
		String value = "";

		String result = fixture.getWhereClauseForAColumn(expersion, field, field2, value);

		assertEquals(" < '' ", result);
	}

	@Test
	public void testGetWhereClauseForAColumn_7() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String expersion = "";
		String field = "";
		String field2 = "";
		String value = "";

		String result = fixture.getWhereClauseForAColumn(expersion, field, field2, value);
		assertEquals("  '' ", result);
	}

	@Test
	public void testIfpLeftTableSearchQuery_1() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		GtnUIFrameworkWebserviceRequest ifpLeftTableRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsSearchRequest request = new GtnWsSearchRequest();

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria in1 = new GtnWebServiceSearchCriteria();
		in1.setFieldId("Field");
		in1.setFilterValue1("f1");
		GtnWebServiceSearchCriteria in2 = new GtnWebServiceSearchCriteria();
		in2.setFilterValue2("f2");
		in2.setFieldId("Value");
		gtnWebServiceSearchCriteriaList.add(in1);
		gtnWebServiceSearchCriteriaList.add(in2);
		request.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
		GtnWebServiceOrderByCriteria orderby = new GtnWebServiceOrderByCriteria();
		orderby.setPropertyId("test");
		orderby.setOrderByCriteria("ASC");
		gtnWebServiceOrderByCriteriaList.add(orderby);
		request.setTableRecordOffset(1);
		request.setTableRecordStart(0);
		request.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
		request.setCount(true);
		ifpLeftTableRequest.setGtnWsSearchRequest(request);

		try {
			fixture.ifpLeftTableSearchQuery(ifpLeftTableRequest);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testIfpRightTableSearchQuery_1() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		GtnUIFrameworkWebserviceRequest ifpLeftTableRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsSearchRequest request = new GtnWsSearchRequest();

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList = new ArrayList<>();
		GtnWebServiceSearchCriteria in1 = new GtnWebServiceSearchCriteria();
		in1.setFieldId("Field");
		in1.setFilterValue1("f1");
		GtnWebServiceSearchCriteria in2 = new GtnWebServiceSearchCriteria();
		in2.setFilterValue2("f2");
		in2.setFieldId("Value");
		gtnWebServiceSearchCriteriaList.add(in1);
		gtnWebServiceSearchCriteriaList.add(in2);
		request.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList);
		List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();
		GtnWebServiceOrderByCriteria orderby = new GtnWebServiceOrderByCriteria();
		orderby.setPropertyId("test");
		orderby.setOrderByCriteria("ASC");
		gtnWebServiceOrderByCriteriaList.add(orderby);
		request.setTableRecordOffset(1);
		request.setTableRecordStart(0);
		request.setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);
		request.setCount(true);
		ifpLeftTableRequest.setGtnWsSearchRequest(request);

		try {
			fixture.ifpRightTableSearchQuery(ifpLeftTableRequest);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// @Test
	// public void testGetWhereClauseForAColumn_BETWEEN() throws Exception {
	// GtnWsIfpAddService fixture = new GtnWsIfpAddService();
	// String expersion = "BETWEEN";
	// String field = "";
	// String field2 = new Date().toString();
	// String value = new Date().toString();
	//
	// String result = fixture.getWhereClauseForAColumn(expersion, field,
	// field2, value);
	//
	// assertEquals(" LIKE '%%' ", result);
	// }

}