package com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.itemfamilyplan.IfpModel;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanCommonUpdateBean;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean.GtnIFamilyPlanInformationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.ifprequest.GtnWsIfpRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.*;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The class <code>GtnWsIfpAddServiceTest</code> contains tests for the class
 * <code>{@link GtnWsIfpAddService}</code>.
 *
 * 
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsIfpAddServiceTest {

	@InjectMocks
	@Autowired
	GtnWsIfpAddService fixture;

	@Mock
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	/**
	 * Run the GtnWsIfpAddService() constructor test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
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

	/**
	 * Run the String companiesUpdateColumnQuery(GtnUIFrameworkWebserviceRequest)
	 * method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
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

	/**
	 * Run the boolean deleteIfpModel(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testDeleteIfpModel_1() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsIfpRequest req = new GtnWsIfpRequest();

		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();

		Session s = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);
		IfpModel ifpModel = new IfpModel();

		doReturn(s).when(sessionFactory).openSession();
		doReturn(ifpModel).when(s).get(IfpModel.class, 1);
		doReturn(tran).when(s).beginTransaction();
		req.setGtnIFamilyPlan(gtnIFamilyPlan);
		gtnWsRequest.setGtnWsIfpRequest(req);

		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpSid(1);
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		boolean result = fixture.deleteIfpModel(gtnWsRequest);

	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetDbColumnForFilterField_1() throws Exception {

		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item ID";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("IM.ITEM_ID", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
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

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetDbColumnForFilterField_4() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item Name";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("IM.ITEM_NAME", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetDbColumnForFilterField_5() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Item Status";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("STA.DESCRIPTION", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetDbColumnForFilterField_6() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Strength";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("STR.DESCRIPTION", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetDbColumnForFilterField_7() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Therapeutic Class";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("TC.DESCRIPTION", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetDbColumnForFilterField_8() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Form";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("FO.DESCRIPTION", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetDbColumnForFilterField_9() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "Brand";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("BM.BRAND_NAME", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetDbColumnForFilterField_10() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "UOM";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("UOM.DESCRIPTION", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetDbColumnForFilterField_11() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = "";

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("", result);
	}

	/**
	 * Run the String getDbColumnForFilterField(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetDbColumnForFilterField_12() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String filterValue1 = null;

		String result = fixture.getDbColumnForFilterField(filterValue1);

		assertEquals("", result);
	}
	//

	@Test
	public void testGetIfpFetchQuery_1() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsIfpRequest req = new GtnWsIfpRequest();

		GtnIFamilyPlanBean gtnIFamilyPlan = new GtnIFamilyPlanBean();

		Session s = Mockito.mock(Session.class);
		Transaction tran = Mockito.mock(Transaction.class);
		IfpModel ifpModel = new IfpModel();

		doReturn(s).when(sessionFactory).openSession();
		doReturn(ifpModel).when(s).get(IfpModel.class, 1);
		doReturn(tran).when(s).beginTransaction();
		req.setGtnIFamilyPlan(gtnIFamilyPlan);
		gtnWsRequest.setGtnWsIfpRequest(req);

		GtnIFamilyPlanInformationBean ifpInfo = new GtnIFamilyPlanInformationBean();
		ifpInfo.setIfpSid(1);
		gtnIFamilyPlan.setIfpInfo(ifpInfo);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		fixture.getIfpFetchQuery(gtnWsRequest, response);

	}

	/**
	 * Run the String getIfpMoveAllLeftQuery(GtnUIFrameworkWebserviceRequest) method
	 * test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetIfpMoveAllLeftQuery_1() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = getSampleWsRequest("1", "2");

		String result = fixture.getIfpMoveAllLeftQuery(gtnWsRequest);

		assertNotNull(result);
	}

	private GtnUIFrameworkWebserviceRequest getSampleWsRequest(String filtervalue1, String filtervalue2) {

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

	/**
	 * Run the String getIfpMoveAllRightQuery(GtnUIFrameworkWebserviceRequest)
	 * method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetIfpMoveAllRightQuery_1() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = getSampleWsRequest("1", "2");

		String result = fixture.getIfpMoveAllRightQuery(gtnWsRequest);

		assertNotNull(result);
	}
	//
	// /**
	// * Run the int getIfpTabDeleteQuery(GtnUIFrameworkWebserviceRequest) method
	// test.
	// *
	// * @throws Exception
	// *
	//
	// */
	// @Test
	// public void testGetIfpTabDeleteQuery_1()
	// throws Exception {
	// GtnWsIfpAddService fixture = new GtnWsIfpAddService();
	// GtnUIFrameworkWebserviceRequest gtnWsRequest = new
	// GtnUIFrameworkWebserviceRequest();
	//
	// int result = fixture.getIfpTabDeleteQuery(gtnWsRequest);
	//
	//
	// // An unexpected exception was thrown in user code while executing this test:
	// // java.lang.NullPointerException
	// // at
	// com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.getIfpTabDeleteQuery(GtnWsIfpAddService.java:589)
	// assertEquals(0, result);
	// }
	//

	/**
	 * Run the String getImAdditionTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetImAdditionTabColumns_1() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemId";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("IM.ITEM_ID", result);
	}

	/**
	 * Run the String getImAdditionTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetImAdditionTabColumns_2() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemNo";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("IM.ITEM_NO", result);
	}

	/**
	 * Run the String getImAdditionTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetImAdditionTabColumns_3() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemName";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("IM.ITEM_NAME", result);
	}

	/**
	 * Run the String getImAdditionTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetImAdditionTabColumns_4() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemDesc";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("IM.ITEM_DESC", result);
	}

	/**
	 * Run the String getImAdditionTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetImAdditionTabColumns_5() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemStatus";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("STA.DESCRIPTION", result);
	}

	/**
	 * Run the String getImAdditionTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetImAdditionTabColumns_6() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "form";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("FO.DESCRIPTION", result);
	}

	/**
	 * Run the String getImAdditionTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetImAdditionTabColumns_7() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "strength";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("STR.DESCRIPTION", result);
	}

	/**
	 * Run the String getImAdditionTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetImAdditionTabColumns_8() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "therapeuticClass";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("TC.DESCRIPTION", result);
	}

	/**
	 * Run the String getImAdditionTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetImAdditionTabColumns_9() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "brand";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("BM.BRAND_NAME", result);
	}

	/**
	 * Run the String getImAdditionTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetImAdditionTabColumns_10() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "UOM";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("UOM.DESCRIPTION", result);
	}

	/**
	 * Run the String getImAdditionTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetImAdditionTabColumns_11() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "therapeutic Class";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("TC.DESCRIPTION", result);
	}

	/**
	 * Run the String getImAdditionTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetImAdditionTabColumns_12() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "";

		String result = fixture.getImAdditionTabColumns(property);

		assertEquals("", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_1() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemNo";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.ITEM_NO", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_2() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemName";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.ITEM_NAME", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_3() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemDesc";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.ITEM_DESC", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_4() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemFamilyPlanStatus";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_ATTACHED_STATUS", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_5() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemFamilyPlanStartDate";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_START_DATE", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_6() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemFamilyPlanEndDate";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_END_DATE", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_7() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "itemStatusValue";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("STA.DESCRIPTION", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_8() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "from";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("FO.DESCRIPTION", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_9() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "strength";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("STR.DESCRIPTION", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_10() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "therapeuticClass";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("TC.DESCRIPTION", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_11() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "brand";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("BM.BRAND_NAME", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_12() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "ifpAttachedDate";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_ATTACHED_DATE", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_13() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "modifiedDate";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_MODIFIED_DATE", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_14() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "modifiedBy";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_MODIFIED_BY", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_15() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "createdDate";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("ICD.IFP_DETAILS_CREATED_DATE", result);
	}

	/**
	 * Run the String getItemsTabColumns(String) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGetItemsTabColumns_16() throws Exception {
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();
		String property = "";

		String result = fixture.getItemsTabColumns(property);

		assertEquals("", result);
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
		GtnWsIfpAddService fixture = new GtnWsIfpAddService();

		SessionFactory result = fixture.getSessionFactory();

		assertEquals(null, result);
	}

	// /**
	// * Run the String getWhereClauseForAColumn(String,String,String,String) method
	// test.
	// *
	// * @throws Exception
	// *
	//
	// */
	// @Test
	// public void testGetWhereClauseForAColumn_1()
	// throws Exception {
	// GtnWsIfpAddService fixture = new GtnWsIfpAddService();
	// String expersion = "BETWEEN";
	// String field = "";
	// String field2 = "";
	// String value = "";
	//
	// String result = fixture.getWhereClauseForAColumn(expersion, field, field2,
	// value);
	//
	//
	// // An unexpected exception was thrown in user code while executing this test:
	// // java.lang.IllegalArgumentException: Cannot format given Object as a Date
	// // at java.text.DateFormat.format(DateFormat.java:310)
	// // at java.text.Format.format(Format.java:157)
	// // at
	// com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.getWhereClauseForAColumn(GtnWsIfpAddService.java:293)
	// assertNotNull(result);
	// }

	/**
	 * Run the String getWhereClauseForAColumn(String,String,String,String) method
	 * test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
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

	/**
	 * Run the String getWhereClauseForAColumn(String,String,String,String) method
	 * test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
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

	/**
	 * Run the String getWhereClauseForAColumn(String,String,String,String) method
	 * test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
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

	/**
	 * Run the String getWhereClauseForAColumn(String,String,String,String) method
	 * test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
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

	/**
	 * Run the String getWhereClauseForAColumn(String,String,String,String) method
	 * test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
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

	/**
	 * Run the String getWhereClauseForAColumn(String,String,String,String) method
	 * test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
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

	// /**
	// * Run the String ifpLeftTableSearchQuery(GtnUIFrameworkWebserviceRequest)
	// method test.
	// *
	// * @throws Exception
	// *
	//
	// */
	// @Test
	// public void testIfpLeftTableSearchQuery_1()
	// throws Exception {
	// GtnWsIfpAddService fixture = new GtnWsIfpAddService();
	// GtnUIFrameworkWebserviceRequest ifpLeftTableRequest = new
	// GtnUIFrameworkWebserviceRequest();
	//
	// String result = fixture.ifpLeftTableSearchQuery(ifpLeftTableRequest);
	//
	//
	// // An unexpected exception was thrown in user code while executing this test:
	// // java.lang.NullPointerException
	// // at
	// com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.ifpLeftTableSearchQuery(GtnWsIfpAddService.java:62)
	// assertNotNull(result);
	// }
	//

	// /**
	// * Run the String ifpRightTableSearchQuery(GtnUIFrameworkWebserviceRequest)
	// method test.
	// *
	// * @throws Exception
	// *
	//
	// */
	// @Test
	// public void testIfpRightTableSearchQuery_1()
	// throws Exception {
	// GtnWsIfpAddService fixture = new GtnWsIfpAddService();
	// GtnUIFrameworkWebserviceRequest ifpRightTableRequest = new
	// GtnUIFrameworkWebserviceRequest();
	//
	// String result = fixture.ifpRightTableSearchQuery(ifpRightTableRequest);
	//
	//
	// // An unexpected exception was thrown in user code while executing this test:
	// // java.lang.NullPointerException
	// // at
	// com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.ifpRightTableSearchQuery(GtnWsIfpAddService.java:83)
	// assertNotNull(result);
	// }
	//

	//
	// /**
	// * Run the String itemsTabResultTable(GtnUIFrameworkWebserviceRequest) method
	// test.
	// *
	// * @throws Exception
	// *
	//
	// */
	// @Test
	// public void testItemsTabResultTable_1()
	// throws Exception {
	// GtnWsIfpAddService fixture = new GtnWsIfpAddService();
	// GtnUIFrameworkWebserviceRequest itemsTabResultRequest = new
	// GtnUIFrameworkWebserviceRequest();
	//
	// String result = fixture.itemsTabResultTable(itemsTabResultRequest);
	//
	//
	// // An unexpected exception was thrown in user code while executing this test:
	// // java.lang.NullPointerException
	// // at
	// com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.itemsTabResultTable(GtnWsIfpAddService.java:322)
	// assertNotNull(result);
	// }

	// /**
	// * Run the int updateIfpCompaniesTabQuery(GtnUIFrameworkWebserviceRequest)
	// method test.
	// *
	// * @throws Exception
	// *
	//
	// */
	// @Test
	// public void testUpdateIfpCompaniesTabQuery_1()
	// throws Exception {
	// GtnWsIfpAddService fixture = new GtnWsIfpAddService();
	// GtnUIFrameworkWebserviceRequest gtnWsRequest = new
	// GtnUIFrameworkWebserviceRequest();
	//
	// int result = fixture.updateIfpCompaniesTabQuery(gtnWsRequest);
	//
	//
	// // An unexpected exception was thrown in user code while executing this test:
	// // java.lang.NullPointerException
	// // at
	// com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.service.GtnWsIfpAddService.updateIfpCompaniesTabQuery(GtnWsIfpAddService.java:577)
	// assertEquals(0, result);
	// }
	//

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception if the initialization fails for some reason
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
	 * @throws Exception if the clean-up fails for some reason
	 *
	 * 
	 */
	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

}