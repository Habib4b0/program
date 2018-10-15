package com.stpl.gtn.gtn2o.ws.search.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastInputBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.newarch.GtnWsForecastNewArchRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastingsearch.GtnGeneralSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.controller.GtnSearchController;
import com.stpl.gtn.gtn2o.ws.search.service.GtnGeneralSearchService;
import com.stpl.gtn.gtn2o.ws.search.sqlservice.GtnSearchwebServiceSqlService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GtnSearchWebService-test.xml" })
public class GtnSearchWebServiceTest {

	private GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger
			.getGTNLogger(GtnSearchWebServiceTest.class);
	
	@Autowired
	private GtnSearchController gtnSearchController;
	
	@Autowired
	private GtnGeneralSearchService gtnGeneralSearchService;
	
	@Autowired
	private GtnSearchwebServiceSqlService gtnSearchwebServiceSqlService;
	
	@Ignore
	@Before
	public void propertyTest() {
		System.setProperty("com.stpl.gtnframework.base.path", "H:\\GTN Server Setup\\conf");
		gtnLogger.info(System.getProperty("com.stpl.gtnframework.base.path"));
	}

	
	@Test
	public void sampleTest()
	{
		boolean value =gtnSearchController.test();
		gtnLogger.info("" + value);
		
	}
	
	@Test
	public void loadDdlb() {
		GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
		searchRequest.setSearchQueryName("BusinessUnitGLcomp");
		gtnUiFrameworkWebservicerequest.setGtnWsSearchRequest(searchRequest);
		GtnUIFrameworkWebserviceResponse response = gtnSearchController.gtnGeneralSearch(gtnUiFrameworkWebservicerequest);
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = response.getGtnUIFrameworkWebserviceComboBoxResponse();
		
		for(String value : comboBoxResponse.getItemValueList() ) {
			gtnLogger.info("value----->" + value);
		}
	}
	
	@Test
	public void loadBusinessComboBox() 
	{
		
		GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
		searchRequest.setSearchQueryName("CompanyMasterGLcomp");
		gtnUiFrameworkWebservicerequest.setGtnWsSearchRequest(searchRequest);
		GtnUIFrameworkWebserviceResponse response = gtnGeneralSearchService.commonMethod(gtnUiFrameworkWebservicerequest);
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = response.getGtnUIFrameworkWebserviceComboBoxResponse();
		
		for(String value : comboBoxResponse.getItemValueList() ) {
			gtnLogger.info("value----->" + value);
		}
		
		
	}
	
	@Test
	public void privateAndPublicTest()
	{
	GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
	GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
	gtnWsSearchRequest.setSearchQueryName("privatePublic");
	GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
	criteria.setFieldId("Commercial Forecasting_privateViewLookup");
	criteria.setFilterValue1("*");
	GtnWebServiceSearchCriteria criteria1 = new GtnWebServiceSearchCriteria();
	criteria1.setFieldId("Private_lookup");
	criteria1.setFilterValue1("private");
	List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList =new ArrayList<>() ;
	gtnWebServiceSearchCriteriaList.add(criteria);
	gtnWebServiceSearchCriteriaList.add(criteria1);
	gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList );
	gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
	 GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
     generalRequest.setUserId("189858");
     gtnUIFrameworkWebserviceRequest.setGtnWsGeneralRequest(generalRequest);
	gtnGeneralSearchService.commonMethod(gtnUIFrameworkWebserviceRequest);
	
	}
	@Test
	public void customViewTest() 
	{
		
		GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest searchRequest = new GtnWsSearchRequest();
		GtnGeneralSearchRequest gtnGeneralSearchRequest = new GtnGeneralSearchRequest();
		searchRequest.setSearchQueryName("salesCustomView");
		gtnUiFrameworkWebservicerequest.setGtnWsSearchRequest(searchRequest);
		 GtnFrameworkForecastInputBean inputBean = new GtnFrameworkForecastInputBean();
		 inputBean.setCustomerRelationSid(0);
		 inputBean.setProductRelationSid(0);
		 inputBean.setCustomerRelationVersionNo(0);
		 inputBean.setProductRelationVersionNo(0);
		 gtnGeneralSearchRequest.setInputBean(inputBean);
		 gtnUiFrameworkWebservicerequest.setGtnGeneralSearchReq(gtnGeneralSearchRequest);
		GtnUIFrameworkWebserviceResponse response = gtnSearchController.gtnGeneralSearch(gtnUiFrameworkWebservicerequest); 
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = response.getGtnUIFrameworkWebserviceComboBoxResponse();
		gtnLogger.info( "" +comboBoxResponse);
		
		
	} 
	
	
	@Test
	public void tableTest()
	{
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
		criteria.setFieldId("description");
		criteria.setFilterValue1("test");
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList =new ArrayList<>() ;
		gtnWebServiceSearchCriteriaList.add(criteria);
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList );
		gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		gtnSearchController.gtnPagedTableSearch(gtnUIFrameworkWebserviceRequest);
		
	}
	
	
	@Test
	public void customerGroupTest()
	{
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest gtnWsSearchRequest = new GtnWsSearchRequest();
		gtnWsSearchRequest.setSearchQueryName("CustomerGroup");
		GtnWebServiceSearchCriteria criteria = new GtnWebServiceSearchCriteria();
		criteria.setFieldId("Name");
		criteria.setFilterValue1("test");
		GtnWebServiceSearchCriteria criteria1 = new GtnWebServiceSearchCriteria();
		criteria1.setFieldId("No");
		criteria1.setFilterValue2("test1");
		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteriaList =new ArrayList<>() ;
		gtnWebServiceSearchCriteriaList.add(criteria);
		gtnWebServiceSearchCriteriaList.add(criteria1);
		gtnWsSearchRequest.setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteriaList );
		gtnUIFrameworkWebserviceRequest.setGtnWsSearchRequest(gtnWsSearchRequest);
		gtnGeneralSearchService.commonMethod(gtnUIFrameworkWebserviceRequest);
		
	}
	
	
	@Test
	public void saveViewTest()
	{
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastNewArchRequest gtnWsForecastNewArchRequest = new GtnWsForecastNewArchRequest();
		 GtnFrameworkForecastDataSelectionBean dataSelectionBean =new GtnFrameworkForecastDataSelectionBean();
		 dataSelectionBean.setViewId(100);
		 dataSelectionBean.setViewName("testView");
		 dataSelectionBean.setViewType("Private");
		 dataSelectionBean.setUserId("189858");
		 dataSelectionBean.setSessionId("");
		 dataSelectionBean.setResultCount(0);
		 dataSelectionBean.setBusinessUnit(100);
		 dataSelectionBean.setCustomerHierarchyVersionNo(4);
		 dataSelectionBean.setProductHierarchyVersionNo(4);
		 Date date = new Date();
		 dataSelectionBean.setForecastEligibleDate(date);
		 dataSelectionBean.setCompany(100);
		 Date fromPeriod = new Date();
		dataSelectionBean.setFromPeriod(fromPeriod );
		dataSelectionBean.setFromPeriodForecastName("Q1-2017");
		 dataSelectionBean.setToPeriodForecastName("Q4-2019");
		 dataSelectionBean.setCompanyName("GL_1 - GL_1");
		 dataSelectionBean.setBusinessUnitName("BU_1 - BU_1");
		 dataSelectionBean.setBusinessUnit(100);
		 dataSelectionBean.setFromPeriodForecast(100);
		 dataSelectionBean.setToPeriod(100);
		 dataSelectionBean.setFrequency(100);
		 dataSelectionBean.setProjectionName("test");
		 dataSelectionBean.setProjectionDescription("test");
		 dataSelectionBean.setCustomerHierarchySid(100);
		 dataSelectionBean.setProductHierarchySid(100);
		 dataSelectionBean.setCustomerHierarchyInnerLevel(100);
		 dataSelectionBean.setProductHierarchyInnerLevel(100);
		 dataSelectionBean.setCustomerHierarchyLevel(100);
		 dataSelectionBean.setProductHierarchyLevel(100);
		 dataSelectionBean.setCustomerRelationshipBuilderSid(100);
		 dataSelectionBean.setProductRelationshipBuilderSid(100);
		 GtnWsRecordBean customerHierarchyRecordBean= new GtnWsRecordBean();
		 customerHierarchyRecordBean.addProperties(100);
		 customerHierarchyRecordBean.setPropertyValueByIndex(0, "Customer");
		 customerHierarchyRecordBean.setPropertyValueByIndex(1, 4);
		 customerHierarchyRecordBean.setPropertyValueByIndex(2, 2);
		 customerHierarchyRecordBean.setPropertyValueByIndex(3, date);
		 customerHierarchyRecordBean.setPropertyValueByIndex(4, date);
		 customerHierarchyRecordBean.setPropertyValueByIndex(5, "Trading Partner");
		 customerHierarchyRecordBean.setPropertyValueByIndex(6, 3);
		 customerHierarchyRecordBean.setPropertyValueByIndex(7,11);
		 customerHierarchyRecordBean.setPropertyValueByIndex(8, "Customer Hierarchy");
		 customerHierarchyRecordBean.setPropertyValueByIndex(9, "Primary");
		 customerHierarchyRecordBean.setPropertyValueByIndex(10,null);
		dataSelectionBean.setCustomerHierarchyRecordBean(customerHierarchyRecordBean);
		GtnWsRecordBean productHierarchyRecordBean = new GtnWsRecordBean();
		productHierarchyRecordBean.addProperties(100);
		dataSelectionBean.setProductHierarchyRecordBean(productHierarchyRecordBean );
		 Integer frequency = 100;
			dataSelectionBean.setFrequency(frequency );
			 dataSelectionBean.setBusinessUnitName("testUnit");
	        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
	        generalRequest.setUserId("189858");
	        gtnUIFrameworkWebserviceRequest.setGtnWsGeneralRequest(generalRequest);
		 gtnWsForecastNewArchRequest.setDataSelectionBean(dataSelectionBean);
		 gtnUIFrameworkWebserviceRequest.setGtnWsForecastNewArchRequest(gtnWsForecastNewArchRequest);
		 
		 GtnUIFrameworkWebserviceResponse response = gtnSearchController.gtnForecastSaveView(gtnUIFrameworkWebserviceRequest);
		System.out.println("COUNT--------->" + response.getGtnSerachResponse());
		deleteTest();
	}
	
	@Test
	public void deleteTest()
	{
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastNewArchRequest gtnWsForecastNewArchRequest = new GtnWsForecastNewArchRequest();
		GtnFrameworkForecastDataSelectionBean dataSelectionBean = new GtnFrameworkForecastDataSelectionBean();
		dataSelectionBean.setUserId("189858");
		dataSelectionBean.setViewId(100);
		gtnWsForecastNewArchRequest.setDataSelectionBean(dataSelectionBean);
		gtnUIFrameworkWebserviceRequest.setGtnWsForecastNewArchRequest(gtnWsForecastNewArchRequest);
		gtnSearchController.gtnForecastDeleteView(gtnUIFrameworkWebserviceRequest);
	}
	@Test
	public void initTest()
	{
		gtnGeneralSearchService.init();
	}
	
	@Test
	public void exceptionTest()
	{
		String queryId = "";
		gtnSearchwebServiceSqlService.getQuery(queryId);
	}
	
}
