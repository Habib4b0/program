package com.stpl.gtn.gtn2o.ws.module.customview.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.Mockito.*;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.entity.customviewforecast.CustViewDetails;
import com.stpl.gtn.gtn2o.ws.entity.customviewforecast.CustViewMaster;
import com.stpl.gtn.gtn2o.ws.entity.customviewforecast.CustomViewVariables;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.HierarchyDefinition;
import com.stpl.gtn.gtn2o.ws.entity.relationshipbuilder.RelationshipBuilder;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.module.customview.service.GtnWsCustomViewService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsCustomViewControllerTest {
	@Spy
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Spy
	@Autowired
	private GtnWsCustomViewService logic;

	@InjectMocks
	@Autowired
	GtnWsCustomViewController ins;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetCustomViewTableData() throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		List<Object> queryInputList = new ArrayList<>();
		gtnWsSearchRequest.setQueryInputList(queryInputList);
		List<Object[]> queryInputList1 = new ArrayList<>();
		doReturn(queryInputList1).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString(), Mockito.anyList());

		ins.getCustomViewTableData(gtnWsRequest);
	}

	@Test
	public void testGetCustomViewTableData_Fail() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		List<Object> queryInputList = new ArrayList<>();
		gtnWsSearchRequest.setQueryInputList(queryInputList);

		ins.getCustomViewTableData(gtnWsRequest);
	}

	@Test
	public void testGetDeductionHierarchyTableData() throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		List<Object> queryInputList = new ArrayList<>();
		gtnWsSearchRequest.setQueryInputList(queryInputList);
		List<Object[]> queryInputList1 = new ArrayList<>();
		doReturn(queryInputList1).when(gtnSqlQueryEngine).executeSelectQuery(Mockito.anyString(), Mockito.anyList());

		ins.getDeductionHierarchyTableData(gtnWsRequest);
	}

	@Test
	public void testGetDeductionHierarchyTableData_Fail() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		List<Object> queryInputList = new ArrayList<>();
		gtnWsSearchRequest.setQueryInputList(queryInputList);

		ins.getDeductionHierarchyTableData(gtnWsRequest);
	}

	@Test
	public void testCheckCustomViewSave() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);
		gtnWsCustomViewRequest.setCvSysId(0);
		gtnWsCustomViewRequest.setCustomViewType("report");

		ins.checkCustomViewSave(gtnWsRequest);
	}

	@Test
	public void testCheckCustomViewSave_Fail() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);

		ins.checkCustomViewSave(gtnWsRequest);
	}

	@Test
	public void testCustomViewSaveLogic() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsCustomViewRequest.setCreatedBy("1");
		gtnWsCustomViewRequest.setModifiedBy("2");
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction tx = Mockito.mock(Transaction.class);

		doReturn(factory).when(logic).getSessionFactory();
		doNothing().when(tx).commit();
		doReturn(session).when(factory).openSession();
		doReturn(tx).when(session).beginTransaction();
		doReturn(1).when(session).save(Mockito.any());

		RelationshipBuilder customerRb = new RelationshipBuilder();
		customerRb.setHierarchyDefinition(new HierarchyDefinition());
		customerRb.setHierarchyVersion(1);
		doReturn(customerRb).when(session).get(RelationshipBuilder.class, 0);

		customerRb.setVersionNo(1);
		gtnWsCustomViewRequest.setCustomViewType("val");

//		GtnForecastHierarchyInputBean gtnForecastHierarchyInputBean=new GtnForecastHierarchyInputBean();
//		gtnForecastHierarchyInputBean.setSelectedProductRelationShipBuilderVersionNo(1);
//		gtnForecastHierarchyInputBean.setSelectedProductRelationShipBuilderSid(2);
//		gtnForecastHierarchyInputBean.setSelectedCustomerRelationShipBuilderSid(3);
//		gtnForecastHierarchyInputBean.setSelectedProductRelationShipBuilderSid(4);
		
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList=new ArrayList<>();
		hierarchyLevelDefinitionList.add(new HierarchyLevelDefinitionBean());
		HierarchyLevelDefinitionBean lastLevelDto = HierarchyLevelDefinitionBean
				.getLastLinkedLevel(hierarchyLevelDefinitionList);
		lastLevelDto.setLevelNo(5);
		
		ins.customViewSaveLogic(gtnWsRequest);
	}

	@Test
	public void testCustomViewSaveLogic_Fail() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		ins.customViewSaveLogic(gtnWsRequest);
	}

	 //saveCustViewMaster method service class // else block
	@Test
	public void testCustomViewSaveLogic_2() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction tx = Mockito.mock(Transaction.class);

		doReturn(factory).when(logic).getSessionFactory();
		doNothing().when(tx).commit();
		doReturn(session).when(factory).openSession();
		doReturn(tx).when(session).beginTransaction();
		
		gtnWsCustomViewRequest.setCvSysId(1);
		CustViewMaster master=new CustViewMaster();
		doReturn(master).when(session).get(CustViewMaster.class, 1);
		
		gtnWsCustomViewRequest.setModifiedBy("1");
		gtnWsCustomViewRequest.setCustomViewType("report");
		
		Criteria cr = Mockito.mock(Criteria.class);
		List<CustViewDetails> details= new ArrayList<>();
		CustViewDetails custViewDetails=new CustViewDetails();
		custViewDetails.setHierarchyIndicator('V');
		custViewDetails.setCustomViewDetailsSid(1);
		details.add(custViewDetails);
		
		doReturn(cr).when(session).createCriteria(CustViewDetails.class);
		doReturn(cr).when(cr).add( Mockito.any(Criterion.class));
		doReturn(details).when(cr).list();
		
		Criteria cr2 = Mockito.mock(Criteria.class);
		
		List<CustomViewVariables> variables = new ArrayList<>();
		variables.add(new CustomViewVariables());
		doReturn(cr2).when(session).createCriteria(CustomViewVariables.class);
		doReturn(cr2).when(cr2).add( Mockito.any(Criterion.class));
		doReturn(variables).when(cr2).list();
		
		List<GtnWsRecordBean> cvTreeNodeList=new ArrayList<>();
		GtnWsRecordBean gtnWsRecordBean=new GtnWsRecordBean();
		gtnWsRecordBean.setAdditionalProperties(Arrays.asList(1));
		gtnWsRecordBean.setPropertyValueByIndex(3, "value");
		gtnWsRecordBean.setRecordHeader(Arrays.asList(0,1,2,3));
		gtnWsRecordBean.setProperties(Arrays.asList("1","value","value","value"));
		
		cvTreeNodeList.add(gtnWsRecordBean);
		
		gtnWsCustomViewRequest.setCvTreeNodeList(cvTreeNodeList);
		
		doReturn(1).when(session).save(Mockito.any());
		
		ins.customViewSaveLogic(gtnWsRequest);
	}
	
	
	//saveVariableData method service class
	@Test
	public void testCustomViewSaveLogic_3() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction tx = Mockito.mock(Transaction.class);

		doReturn(factory).when(logic).getSessionFactory();
		doNothing().when(tx).commit();
		doReturn(session).when(factory).openSession();
		doReturn(tx).when(session).beginTransaction();
		
		gtnWsCustomViewRequest.setCvSysId(1);
		CustViewMaster master=new CustViewMaster();
		doReturn(master).when(session).get(CustViewMaster.class, 1);
		
		gtnWsCustomViewRequest.setModifiedBy("1");
		gtnWsCustomViewRequest.setCustomViewType("reportdstatic");
		
		Criteria cr = Mockito.mock(Criteria.class);
		List<CustViewDetails> details= new ArrayList<>();
		CustViewDetails custViewDetails=new CustViewDetails();
		custViewDetails.setHierarchyIndicator('V');
		custViewDetails.setCustomViewDetailsSid(1);
		details.add(custViewDetails);
		
		doReturn(cr).when(session).createCriteria(CustViewDetails.class);
		doReturn(cr).when(cr).add( Mockito.any(Criterion.class));
		doReturn(details).when(cr).list();
		
		Criteria cr2 = Mockito.mock(Criteria.class);
		
		List<CustomViewVariables> variables = new ArrayList<>();
		variables.add(new CustomViewVariables());
		doReturn(cr2).when(session).createCriteria(CustomViewVariables.class);
		doReturn(cr2).when(cr2).add( Mockito.any(Criterion.class));
		doReturn(variables).when(cr2).list();
		
		List<GtnWsRecordBean> cvTreeNodeList=new ArrayList<>();
		GtnWsRecordBean gtnWsRecordBean=new GtnWsRecordBean();
		gtnWsRecordBean.setAdditionalProperties(Arrays.asList(1));
		gtnWsRecordBean.setPropertyValueByIndex(3, "value");
		gtnWsRecordBean.setRecordHeader(Arrays.asList(0,1,2,3));
		gtnWsRecordBean.setProperties(Arrays.asList("1","value",1,"value"));
		
		cvTreeNodeList.add(gtnWsRecordBean);
		
		gtnWsCustomViewRequest.setCvTreeNodeList(cvTreeNodeList);
		
		doReturn(1).when(session).save(Mockito.any());	
		doReturn(custViewDetails).when(session).load(CustViewDetails.class,0);
		
		
		ins.customViewSaveLogic(gtnWsRequest);
	}
	
	
	@Test
	public void testDeleteCustomViewLogic() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Criteria cr = Mockito.mock(Criteria.class);

		doReturn(factory).when(logic).getSessionFactory();
		doNothing().when(tx).commit();
		doReturn(session).when(factory).openSession();
		doReturn(tx).when(session).beginTransaction();
		doReturn(cr).when(session).createCriteria(CustViewDetails.class);

		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);
		gtnWsCustomViewRequest.setCvSysId(1);

		ins.deleteCustomViewLogic(gtnWsRequest);
	}

	@Test
	public void testDeleteCustomViewLogic_Fail() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		ins.deleteCustomViewLogic(gtnWsRequest);
	}

	@Test
	public void testDeleteCustomViewUserValidationLogic_1() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Criteria cr = Mockito.mock(Criteria.class);

		doReturn(factory).when(logic).getSessionFactory();
		doNothing().when(tx).commit();
		doReturn(session).when(factory).openSession();
		doReturn(tx).when(session).beginTransaction();

		CustViewMaster master = new CustViewMaster();
		doReturn(master).when(session).get(CustViewMaster.class, 0);

		master.setCreatedBy(1);
		gtnWsCustomViewRequest.setCreatedBy("1");
		ins.deleteCustomViewUserValidationLogic(gtnWsRequest);
	}

	@Test
	public void testDeleteCustomViewUserValidationLogic_2() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction tx = Mockito.mock(Transaction.class);
		Criteria cr = Mockito.mock(Criteria.class);

		doReturn(factory).when(logic).getSessionFactory();
		doNothing().when(tx).commit();
		doReturn(session).when(factory).openSession();
		doReturn(tx).when(session).beginTransaction();

		CustViewMaster master = new CustViewMaster();
		doReturn(master).when(session).get(CustViewMaster.class, null);
		ins.deleteCustomViewUserValidationLogic(gtnWsRequest);
	}

	// fail in validateCustViewUser()
	@Test
	public void testDeleteCustomViewUserValidationLogicFail_1() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Transaction tx = Mockito.mock(Transaction.class);
		doReturn(factory).when(logic).getSessionFactory();

		doReturn(session).when(factory).openSession();
		doReturn(null).when(session).beginTransaction();

		ins.deleteCustomViewUserValidationLogic(gtnWsRequest);
	}

	@Test
	public void testDeleteCustomViewUserValidationLogicFail_2() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		ins.deleteCustomViewUserValidationLogic(gtnWsRequest);
	}

	@Test
	public void testCustomViewTreeData_1() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);
		String customViewType = "report";
		gtnWsCustomViewRequest.setCustomViewType(customViewType);

		ins.customViewTreeData(gtnWsRequest);
	}

	//getSavedTreeData service class method 
	@Test
	public void testCustomViewTreeData_2() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsCustomViewRequest.setCvSysId(1);
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);
		String customViewType = "Expandable value";
		gtnWsCustomViewRequest.setCustomViewType(customViewType);
	
		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Criteria cr = Mockito.mock(Criteria.class);
		List<CustViewDetails> gtnListOfData = new ArrayList<>();
		CustViewDetails custViewDetails = new CustViewDetails();
		custViewDetails.setHierarchyId(1);
		custViewDetails.setLevelName("name");
		gtnListOfData.add(custViewDetails);
	
		doReturn(factory).when(logic).getSessionFactory();
		doReturn(session).when(factory).openSession();
		doReturn(cr).when(session).createCriteria(CustViewDetails.class);
		doReturn(cr).when(cr).add( Mockito.any(Criterion.class));
		doReturn(gtnListOfData).when(cr).list();
		
		ins.customViewTreeData(gtnWsRequest);
	}
	
	
	//getSavedTreeData service class method 
	@Test
	public void testCustomViewTreeData_5() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsCustomViewRequest.setCvSysId(1);
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);
		String customViewType = "value";
		gtnWsCustomViewRequest.setCustomViewType(customViewType);
	
		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Criteria cr = Mockito.mock(Criteria.class);
		List<CustViewDetails> gtnListOfData = new ArrayList<>();
		CustViewDetails custViewDetails = new CustViewDetails();
		custViewDetails.setHierarchyId(0);
		custViewDetails.setLevelName("name");
		gtnListOfData.add(custViewDetails);
	
		doReturn(factory).when(logic).getSessionFactory();
		doReturn(session).when(factory).openSession();
		doReturn(cr).when(session).createCriteria(CustViewDetails.class);
		doReturn(cr).when(cr).add( Mockito.any(Criterion.class));
		doReturn(gtnListOfData).when(cr).list();
		
		ins.customViewTreeData(gtnWsRequest);
	}

	@Test
	public void testCustomViewTreeData_Fail() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		ins.customViewTreeData(gtnWsRequest);

		ins.customViewTreeData(gtnWsRequest);
	}



	
	
	// getCustViewMaster() in service classs
	@Test
	public void testCustomViewTreeData_3() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);
		String customViewType = "report";
		gtnWsCustomViewRequest.setCustomViewType(customViewType);

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		doReturn(factory).when(logic).getSessionFactory();
		doReturn(session).when(factory).openSession();

		CustViewMaster master = new CustViewMaster();
		doReturn(master).when(session).get(CustViewMaster.class, 0);

		ins.customViewTreeData(gtnWsRequest);
	}

	// getCustViewMaster() in service class
	@Test
	public void testCustomViewTreeData_4() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);
		String customViewType = "report";
		gtnWsCustomViewRequest.setCustomViewType(customViewType);

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		doReturn(factory).when(logic).getSessionFactory();
		doReturn(session).when(factory).openSession();

		CustViewMaster master = new CustViewMaster();
		doReturn(master).when(session).get(CustViewMaster.class, null);

		ins.customViewTreeData(gtnWsRequest);
	}

	@Test
	public void testCustomViewlevelData() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Criteria criteria = Mockito.mock(Criteria.class);

		doReturn(factory).when(logic).getSessionFactory();
		doReturn(session).when(factory).openSession();
		doReturn(criteria).when(session).createCriteria(CustViewDetails.class);

		GtnUIFrameworkWebserviceComboBoxResponse response = Mockito
				.mock(GtnUIFrameworkWebserviceComboBoxResponse.class);
		doNothing().when(response).addItemCodeList("1");

		List<CustViewDetails> gtnListOfData = new ArrayList<>();
		CustViewDetails custViewDetails = new CustViewDetails();
		custViewDetails.setLevelNo(1);
		custViewDetails.setLevelName("val");
		gtnListOfData.add(custViewDetails);

		doReturn(gtnListOfData).when(criteria).list();

		ins.customViewlevelData(gtnWsRequest);
	}

	// getCustomViewLevelData Fail in service
	@Test
	public void testCustomViewlevelData_Fail() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Criteria criteria = Mockito.mock(Criteria.class);

		doReturn(factory).when(logic).getSessionFactory();
		doReturn(session).when(factory).openSession();
		doReturn(criteria).when(session).createCriteria(CustViewDetails.class);

		GtnUIFrameworkWebserviceComboBoxResponse response = Mockito
				.mock(GtnUIFrameworkWebserviceComboBoxResponse.class);
		doNothing().when(response).addItemCodeList("1");

		List<CustViewDetails> gtnListOfData = new ArrayList<>();
		CustViewDetails custViewDetails = new CustViewDetails();
		gtnListOfData.add(custViewDetails);

		doReturn(gtnListOfData).when(criteria).list();

		ins.customViewlevelData(gtnWsRequest);
	}

	@Test
	public void testCustomViewlevelData_Fail2() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = Mockito.mock(GtnUIFrameworkWebserviceRequest.class);

		doReturn(null).when(gtnWsRequest).getGtnWsCustomViewRequest();
		ins.customViewlevelData(gtnWsRequest);
	}

	@Test
	public void testCustomViewData() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Criteria criteria = Mockito.mock(Criteria.class);

		doReturn(factory).when(logic).getSessionFactory();
		doReturn(session).when(factory).openSession();
		doReturn(criteria).when(session).createCriteria(CustViewMaster.class);

		GtnUIFrameworkWebserviceComboBoxResponse response = Mockito
				.mock(GtnUIFrameworkWebserviceComboBoxResponse.class);
		doNothing().when(response).addItemCodeList("1");

		List<CustViewMaster> gtnListOfData = new ArrayList<>();
		CustViewMaster custViewDetails = new CustViewMaster();
		custViewDetails.setCustViewMasterSid(1);
		gtnListOfData.add(custViewDetails);

		doReturn(gtnListOfData).when(criteria).list();

		ins.customViewData(gtnWsRequest);
	}

	// getCustomViewList catch block service class
	@Test
	public void testCustomViewData_Fail1() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsCustomViewRequest gtnWsCustomViewRequest = new GtnWsCustomViewRequest();
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);

		SessionFactory factory = Mockito.mock(SessionFactory.class);

		doReturn(null).when(logic).getSessionFactory();
		doReturn(null).when(factory).openSession();

		ins.customViewData(gtnWsRequest);
	}

	@Test
	public void testDeleteCustomView_1() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsCustomViewRequest gtnWsCustomViewRequest = Mockito.mock(GtnWsCustomViewRequest.class);
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);

		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Criteria criteria = Mockito.mock(Criteria.class);

		doReturn(1).when(gtnWsCustomViewRequest).getCvSysId();

		doReturn(factory).when(logic).getSessionFactory();
		doReturn(session).when(factory).openSession();
		doReturn(criteria).when(session).createCriteria(CustViewMaster.class);

		ins.deleteCustomView(gtnWsRequest);
	}

	@Test
	public void testDeleteCustomView_2() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		
		GtnWsCustomViewRequest gtnWsCustomViewRequest = Mockito.mock(GtnWsCustomViewRequest.class);
		gtnWsRequest.setGtnWsCustomViewRequest(gtnWsCustomViewRequest);
		
		SessionFactory factory = Mockito.mock(SessionFactory.class);
		Session session = Mockito.mock(Session.class);
		Criteria criteria = Mockito.mock(Criteria.class);
		
		
		ins.deleteCustomView(gtnWsRequest);
	}
	
}
