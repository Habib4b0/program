package com.stpl.gtn.gtn2o.ws.returnsForecasting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.module.returnsforecasting.controller.GtnWsReturnForecastDataSelectionController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.GtnWsForecastRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.ServiceContextUtil;

@Ignore
public class ReturnsForecastingWebServiceTest {

	@Test
	public void getSearchCountPublicView() {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		gtnWsRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());

		GtnWsSearchRequest gtnWSSearchRequest = new GtnWsSearchRequest();
		gtnWSSearchRequest.setCount(true);
		gtnWSSearchRequest.setSearchQueryName("private");
		gtnWSSearchRequest.setSearchModuleName("returns");
		gtnWsRequest.setGtnWsSearchRequest(gtnWSSearchRequest);

		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceSearchCriteriaList(Arrays
				.asList(new GtnWebServiceSearchCriteria[] { new GtnWebServiceSearchCriteria("viewName", "*", ""), }));

		GtnWsReturnForecastDataSelectionController instance = (GtnWsReturnForecastDataSelectionController) ServiceContextUtil
				.instance().getBean("returnsForecastingWebService");

		GtnUIFrameworkWebserviceResponse response = instance.getPublicViewSearchLookUp(gtnWsRequest);

		System.out.println("Get data count   === " + response.getGtnSerachResponse().getCount());

	}

	@Test
	public void getSearchCountPrivateView() {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();

		GtnWsSearchRequest gtnWSSearchRequest = new GtnWsSearchRequest();
		gtnWSSearchRequest.setCount(true);
		gtnWSSearchRequest.setSearchQueryName("private");
		gtnWSSearchRequest.setSearchModuleName("returns");

		gtnWsGeneralRequest.setUserId("13330");
		gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		gtnWsRequest.setGtnWsSearchRequest(gtnWSSearchRequest);

		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceSearchCriteriaList(Arrays
				.asList(new GtnWebServiceSearchCriteria[] { new GtnWebServiceSearchCriteria("viewName", "*", ""), }));

		GtnWsReturnForecastDataSelectionController instance = (GtnWsReturnForecastDataSelectionController) ServiceContextUtil
				.instance().getBean("returnsForecastingWebService");

		GtnUIFrameworkWebserviceResponse response = instance.getPrivateViewSearchLookUp(gtnWsRequest);

		System.out.println("Get data count   === " + response.getGtnSerachResponse().getCount());

	}

	@Test
	public void getSearchHireachyLookUpForDataCount() {

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteria = new ArrayList<>();

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		gtnWsRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());

		GtnWsSearchRequest gtnWSSearchRequest = new GtnWsSearchRequest();
		gtnWSSearchRequest.setCount(true);
		gtnWsRequest.setGtnWsSearchRequest(gtnWSSearchRequest);

		GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
		searchCriteria.setFieldId("hierarchyType");
		searchCriteria.setFilterValue1("Primary");
		gtnWebServiceSearchCriteria.add(searchCriteria);

		GtnWebServiceSearchCriteria searchCriteria1 = new GtnWebServiceSearchCriteria();
		searchCriteria1.setFieldId("hierarchyName");
		searchCriteria1.setFilterValue1("*");
		gtnWebServiceSearchCriteria.add(searchCriteria1);

		gtnWsRequest.getGtnWsSearchRequest().setQueryInputList(Arrays.asList(new Object[] { "Product Hierarchy" }));

		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteria);

		System.out.println("gtnWebServiceSearchCriteria.size() " + gtnWebServiceSearchCriteria.size());

		GtnWsReturnForecastDataSelectionController instance = (GtnWsReturnForecastDataSelectionController) ServiceContextUtil
				.instance().getBean("returnsForecastingWebService");

		GtnUIFrameworkWebserviceResponse response = instance.getHierarchyLookUp(gtnWsRequest);

		System.out.println("Get data count   === " + response.getGtnSerachResponse().getCount());
	}

	@Test
	public void getSearchHireachyLookUpbyDefault() {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		List<GtnWebServiceSearchCriteria> gtnWebServiceSearchCriteria = new ArrayList<>();
		List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList = new ArrayList<>();

		gtnWsRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());

		GtnWsSearchRequest gtnWSSearchRequest = new GtnWsSearchRequest();
		gtnWSSearchRequest.setCount(false);
		gtnWsRequest.setGtnWsSearchRequest(gtnWSSearchRequest);

		GtnWebServiceSearchCriteria searchCriteria = new GtnWebServiceSearchCriteria();
		searchCriteria.setFieldId("hierarchyType");
		searchCriteria.setFilterValue1("Primary");
		gtnWebServiceSearchCriteria.add(searchCriteria);

		GtnWebServiceSearchCriteria searchCriteria1 = new GtnWebServiceSearchCriteria();
		searchCriteria1.setFieldId("hierarchyName");
		searchCriteria1.setFilterValue1("*");
		gtnWebServiceSearchCriteria.add(searchCriteria1);

		gtnWsRequest.getGtnWsSearchRequest().setQueryInputList(Arrays.asList(new Object[] { "Product Hierarchy" }));

		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceSearchCriteriaList(gtnWebServiceSearchCriteria);

		GtnWebServiceOrderByCriteria orderByCriteria = new GtnWebServiceOrderByCriteria();
		orderByCriteria.setPropertyId("hierName");
		orderByCriteria.setOrderByCriteria("DESC");
		gtnWebServiceOrderByCriteriaList.add(orderByCriteria);
		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceOrderByCriteriaList(gtnWebServiceOrderByCriteriaList);

		gtnWsRequest.getGtnWsSearchRequest().setTableRecordStart(1);

		gtnWsRequest.getGtnWsSearchRequest().setTableRecordOffset(4);

		GtnWsReturnForecastDataSelectionController instance = (GtnWsReturnForecastDataSelectionController) ServiceContextUtil
				.instance().getBean("returnsForecastingWebService");

		GtnUIFrameworkWebserviceResponse response = instance.getHierarchyLookUp(gtnWsRequest);

		System.out.println(
				"Get data count   === " + response.getGtnSerachResponse().getResultSet().getDataTable().size());

		for (int i = 0; i < response.getGtnSerachResponse().getResultSet().getDataTable().size(); i++) {
			System.out.println("Data " + i);
			for (Object currentResponse : response.getGtnSerachResponse().getResultSet().getDataTable().get(i)
					.getColList()) {
				System.out.println(currentResponse);
			}
		}
	}

	@Test
	public void getSearchCountHireachyLookUpByHireachyNameHighestAndLowestLevel() {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		gtnWsRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());

		GtnWsSearchRequest gtnWSSearchRequest = new GtnWsSearchRequest();
		gtnWSSearchRequest.setCount(true);
		gtnWsRequest.setGtnWsSearchRequest(gtnWSSearchRequest);

		gtnWsRequest.getGtnWsSearchRequest()
				.setGtnWebServiceSearchCriteriaList(Arrays.asList(new GtnWebServiceSearchCriteria[] {
						new GtnWebServiceSearchCriteria("hierarchyType", "Primary", ""),
						new GtnWebServiceSearchCriteria("hierarchyName", "*", ""),
						new GtnWebServiceSearchCriteria("hierName", "U", ""),
						new GtnWebServiceSearchCriteria("highestLevel", "3", ""),
						new GtnWebServiceSearchCriteria("lowestLevel", "1", "") }));

		gtnWsRequest.getGtnWsSearchRequest().setQueryInputList(Arrays.asList(new Object[] { "Product Hierarchy" }));

		GtnWsReturnForecastDataSelectionController instance = (GtnWsReturnForecastDataSelectionController) ServiceContextUtil
				.instance().getBean("returnsForecastingWebService");

		GtnUIFrameworkWebserviceResponse response = instance.getHierarchyLookUp(gtnWsRequest);

		System.out.println("Get data count   === " + response.getGtnSerachResponse().getCount());

	}

	@Test
	public void getSearchDataHireachyLookUpByHireachyNameHighestAndLowestLevel() {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		gtnWsRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());

		GtnWsSearchRequest gtnWSSearchRequest = new GtnWsSearchRequest();
		gtnWSSearchRequest.setCount(false);
		gtnWsRequest.setGtnWsSearchRequest(gtnWSSearchRequest);

		gtnWsRequest.getGtnWsSearchRequest()
				.setGtnWebServiceSearchCriteriaList(Arrays.asList(new GtnWebServiceSearchCriteria[] {
						new GtnWebServiceSearchCriteria("hierarchyType", "Primary", ""),
						new GtnWebServiceSearchCriteria("hierarchyName", "*", ""),
						new GtnWebServiceSearchCriteria("hierName", "U", ""),
						new GtnWebServiceSearchCriteria("highestLevel", "3", ""),
						new GtnWebServiceSearchCriteria("lowestLevel", "1", "") }));

		gtnWsRequest.getGtnWsSearchRequest().setQueryInputList(Arrays.asList(new Object[] { "Product Hierarchy" }));

		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceOrderByCriteriaList(Arrays
				.asList(new GtnWebServiceOrderByCriteria[] { new GtnWebServiceOrderByCriteria("hierName", "ASC") }));

		gtnWsRequest.getGtnWsSearchRequest().setTableRecordStart(0);

		gtnWsRequest.getGtnWsSearchRequest().setTableRecordOffset(5);

		GtnWsReturnForecastDataSelectionController instance = (GtnWsReturnForecastDataSelectionController) ServiceContextUtil
				.instance().getBean("returnsForecastingWebService");

		GtnUIFrameworkWebserviceResponse response = instance.getHierarchyLookUp(gtnWsRequest);

		System.out.println(
				"Get data count   === " + response.getGtnSerachResponse().getResultSet().getDataTable().size());

		for (int i = 0; i < response.getGtnSerachResponse().getResultSet().getDataTable().size(); i++) {
			System.out.println("Data " + i);
			for (Object currentResponse : response.getGtnSerachResponse().getResultSet().getDataTable().get(i)
					.getColList()) {
				System.out.println(currentResponse);
			}
		}
	}

	@Test
	public void getSearchCountHireachyLookUpByCreatedDate() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		gtnWsRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());

		GtnWsSearchRequest gtnWSSearchRequest = new GtnWsSearchRequest();
		gtnWSSearchRequest.setCount(false);
		gtnWsRequest.setGtnWsSearchRequest(gtnWSSearchRequest);

		gtnWsRequest.getGtnWsSearchRequest()
				.setGtnWebServiceSearchCriteriaList(Arrays.asList(new GtnWebServiceSearchCriteria[] {
						new GtnWebServiceSearchCriteria("hierarchyType", "Primary", ""),
						new GtnWebServiceSearchCriteria("hierarchyName", "*", ""), new GtnWebServiceSearchCriteria(
								"createdDate", "Sat Dec 01 11:40:35 IST 2016", "Sat Dec 31 12:56:40 IST 2016", "") }));

		gtnWsRequest.getGtnWsSearchRequest().setQueryInputList(Arrays.asList(new Object[] { "Product Hierarchy" }));

		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceOrderByCriteriaList(Arrays
				.asList(new GtnWebServiceOrderByCriteria[] { new GtnWebServiceOrderByCriteria("hierName", "ASC") }));

		gtnWsRequest.getGtnWsSearchRequest().setTableRecordStart(0);

		gtnWsRequest.getGtnWsSearchRequest().setTableRecordOffset(5);

		GtnWsReturnForecastDataSelectionController instance = (GtnWsReturnForecastDataSelectionController) ServiceContextUtil
				.instance().getBean("returnsForecastingWebService");

		GtnUIFrameworkWebserviceResponse response = instance.getHierarchyLookUp(gtnWsRequest);

		System.out.println(
				"Get data count   === " + response.getGtnSerachResponse().getResultSet().getDataTable().size());

		for (int i = 0; i < response.getGtnSerachResponse().getResultSet().getDataTable().size(); i++) {
			System.out.println("Data " + i);
			for (Object currentResponse : response.getGtnSerachResponse().getResultSet().getDataTable().get(i)
					.getColList()) {
				System.out.println(currentResponse);
			}
		}
	}

	@Test
	public void getSearchCountHireachyLookUpByModifiedDate() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		gtnWsRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());

		GtnWsSearchRequest gtnWSSearchRequest = new GtnWsSearchRequest();
		gtnWSSearchRequest.setCount(false);
		gtnWsRequest.setGtnWsSearchRequest(gtnWSSearchRequest);

		gtnWsRequest.getGtnWsSearchRequest()
				.setGtnWebServiceSearchCriteriaList(Arrays.asList(new GtnWebServiceSearchCriteria[] {
						new GtnWebServiceSearchCriteria("hierarchyType", "Primary", ""),
						new GtnWebServiceSearchCriteria("hierarchyName", "*", ""), new GtnWebServiceSearchCriteria(
								"modifiedDate", "Sat Mar 01 11:40:35 IST 2016", "Sat Apr 31 12:56:40 IST 2016", "") }));

		gtnWsRequest.getGtnWsSearchRequest().setQueryInputList(Arrays.asList(new Object[] { "Product Hierarchy" }));

		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceOrderByCriteriaList(Arrays
				.asList(new GtnWebServiceOrderByCriteria[] { new GtnWebServiceOrderByCriteria("hierName", "ASC") }));

		gtnWsRequest.getGtnWsSearchRequest().setTableRecordStart(0);

		gtnWsRequest.getGtnWsSearchRequest().setTableRecordOffset(5);

		GtnWsReturnForecastDataSelectionController instance = (GtnWsReturnForecastDataSelectionController) ServiceContextUtil
				.instance().getBean("returnsForecastingWebService");

		GtnUIFrameworkWebserviceResponse response = instance.getHierarchyLookUp(gtnWsRequest);

		System.out.println(
				"Get data count   === " + response.getGtnSerachResponse().getResultSet().getDataTable().size());

		for (int i = 0; i < response.getGtnSerachResponse().getResultSet().getDataTable().size(); i++) {
			System.out.println("Data " + i);
			for (Object currentResponse : response.getGtnSerachResponse().getResultSet().getDataTable().get(i)
					.getColList()) {
				System.out.println(currentResponse);
			}
		}
	}

	@Test
	public void getSearchCountProducGrouptLookUpByProducGroupName() {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		gtnWsRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());

		GtnWsSearchRequest gtnWSSearchRequest = new GtnWsSearchRequest();
		gtnWSSearchRequest.setCount(true);
		gtnWsRequest.setGtnWsSearchRequest(gtnWSSearchRequest);

		String viewProdId = "prodGroupLookupView";
		String temp = viewProdId + "productGroupName";

		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceSearchCriteriaList(
				Arrays.asList(new GtnWebServiceSearchCriteria[] { new GtnWebServiceSearchCriteria(temp, "*", ""),
						new GtnWebServiceSearchCriteria("productGroupNo", "*", "") }));

		GtnWsReturnForecastDataSelectionController instance = (GtnWsReturnForecastDataSelectionController) ServiceContextUtil
				.instance().getBean("returnsForecastingWebService");

		GtnUIFrameworkWebserviceResponse response = instance.getProductGroupLookUp(gtnWsRequest);

		System.out.println("Get data count   === " + response.getGtnSerachResponse().getCount());

	}

	@Test
	public void getSearchDataProducGrouptLookUpByProducGroupName() {

		String viewProdId = "prodGroupLookupView";

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		gtnWsRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());

		GtnWsSearchRequest gtnWSSearchRequest = new GtnWsSearchRequest();
		gtnWSSearchRequest.setCount(false);

		gtnWsRequest.setGtnWsSearchRequest(gtnWSSearchRequest);

		String temp = viewProdId + "productGroupName";
		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceSearchCriteriaList(
				Arrays.asList(new GtnWebServiceSearchCriteria[] { new GtnWebServiceSearchCriteria(temp, "*", ""),
						new GtnWebServiceSearchCriteria("productGroupNo", "*", "") }));

		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceOrderByCriteriaList(
				Arrays.asList(new GtnWebServiceOrderByCriteria[] { new GtnWebServiceOrderByCriteria("sid", "ASC") }));

		gtnWsRequest.getGtnWsSearchRequest().setTableRecordStart(0);

		gtnWsRequest.getGtnWsSearchRequest().setTableRecordOffset(10);

		GtnWsReturnForecastDataSelectionController instance = (GtnWsReturnForecastDataSelectionController) ServiceContextUtil
				.instance().getBean("returnsForecastingWebService");

		GtnUIFrameworkWebserviceResponse response = instance.getProductGroupLookUp(gtnWsRequest);

		for (int i = 0; i < response.getGtnSerachResponse().getResultSet().getDataTable().size(); i++) {
			System.out.println("Data " + i);
			for (Object currentResponse : response.getGtnSerachResponse().getResultSet().getDataTable().get(i)
					.getColList()) {
				System.out.println(currentResponse);
			}
		}
	}

	@Test
	public void getSearchDataProducGrouptLookUpByProducGroupNameAndFilterView() {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		gtnWsRequest.setGtnWsGeneralRequest(new GtnWsGeneralRequest());

		GtnWsSearchRequest gtnWSSearchRequest = new GtnWsSearchRequest();
		gtnWSSearchRequest.setCount(false);
		gtnWsRequest.setGtnWsSearchRequest(gtnWSSearchRequest);

		gtnWsRequest.getGtnWsSearchRequest()
				.setGtnWebServiceSearchCriteriaList(Arrays.asList(new GtnWebServiceSearchCriteria[] {
						new GtnWebServiceSearchCriteria("productGroupName", "*", ""),
						new GtnWebServiceSearchCriteria("productGroupNo", "*", ""),
				// new GtnWebServiceSearchCriteria("productGroupNameFilterView",
				// "qas", ""),
				// new GtnWebServiceSearchCriteria("productGroupNoFilterView",
				// "qas", ""),
				// new GtnWebServiceSearchCriteria("companyNameFilterView",
				// "ga", "")
		}));

		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceOrderByCriteriaList(
				Arrays.asList(new GtnWebServiceOrderByCriteria[] { new GtnWebServiceOrderByCriteria("sid", "ASC") }));

		gtnWsRequest.getGtnWsSearchRequest().setTableRecordStart(0);

		gtnWsRequest.getGtnWsSearchRequest().setTableRecordOffset(2);

		GtnWsReturnForecastDataSelectionController instance = (GtnWsReturnForecastDataSelectionController) ServiceContextUtil
				.instance().getBean("returnsForecastingWebService");

		GtnUIFrameworkWebserviceResponse response = instance.getProductGroupLookUp(gtnWsRequest);

		for (int i = 0; i < response.getGtnSerachResponse().getResultSet().getDataTable().size(); i++) {
			System.out.println("Data " + i);
			for (Object currentResponse : response.getGtnSerachResponse().getResultSet().getDataTable().get(i)
					.getColList()) {
				System.out.println(currentResponse);
			}
		}
	}

	@Test
	public void getPrivateViewDetails() {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastRequest generalWSRequest = new GtnWsForecastRequest();

		GtnForecastBean gtnForecastBean = new GtnForecastBean();
		gtnForecastBean.setViewId(16398);

		generalWSRequest.setGtnForecastBean(gtnForecastBean);
		gtnWsRequest.setGtnWsForecastRequest(generalWSRequest);

		GtnWsReturnForecastDataSelectionController instance = (GtnWsReturnForecastDataSelectionController) ServiceContextUtil
				.instance().getBean("returnsForecastingWebService");
		GtnUIFrameworkWebserviceResponse gtnResponse = instance.getReturnsForecastViewDetailsService(gtnWsRequest);

		System.out.println(gtnResponse);
	}
}