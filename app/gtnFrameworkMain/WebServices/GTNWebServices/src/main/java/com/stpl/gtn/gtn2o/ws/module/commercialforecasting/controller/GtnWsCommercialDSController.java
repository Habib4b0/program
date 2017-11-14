package com.stpl.gtn.gtn2o.ws.module.commercialforecasting.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.controller.GtnWsGeneralController;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
@RequestMapping(value = "gtnCommercialForecasting/lookUp")
public class GtnWsCommercialDSController {

	public GtnWsCommercialDSController() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsCommercialDSController.class);

	@Autowired
	private GtnWsGeneralController gtnGeneralServiceController;

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	public GtnWsGeneralController getGtnGeneralServiceController() {
		return gtnGeneralServiceController;
	}

	public void setGtnGeneralServiceController(GtnWsGeneralController gtnGeneralServiceController) {
		this.gtnGeneralServiceController = gtnGeneralServiceController;
	}

	public org.hibernate.SessionFactory getSysSessionFactory() {
		return sysSessionFactory;
	}

	public void setSysSessionFactory(org.hibernate.SessionFactory sysSessionFactory) {
		this.sysSessionFactory = sysSessionFactory;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getProductHierarchyLookUp", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getProductHierarchyLookUp(
			@RequestBody GtnUIFrameworkWebserviceRequest productHierarchyRequest) {
		GtnUIFrameworkWebserviceResponse productHierarchyResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			LOGGER.info(" inside Product Hierarchy search ");
			GtnSerachResponse productHierarchySerachResponse = new GtnSerachResponse();
			String queryName = productHierarchyRequest.getGtnWsSearchRequest().isCount()
					? "getCountCommercialForecastingProductHierarchy" : "getDataCommercialForecastingProductHierarchy";

			List<Object[]> result = gtnGeneralServiceController.executeQuery(gtnGeneralServiceController
					.getGtnWsSqlService().getQuery(getProductHierarchyLookUpInput(productHierarchyRequest), queryName));
			if (productHierarchyRequest.getGtnWsSearchRequest().isCount()) {
				productHierarchySerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(result);
				productHierarchySerachResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			productHierarchyResponse.setGtnSerachResponse(productHierarchySerachResponse);
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error(ex.getMessage());
			productHierarchyResponse.getGtnWsGeneralResponse().setSucess(false);
			productHierarchyResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return productHierarchyResponse;
	}

	private List<Object> getProductHierarchyLookUpInput(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws ParseException {

		List<Object> productHierarchySearchResultList = new ArrayList<>();
		String hierarchyName = "%";
		String hierarchyType = "%";
		String hierarchyNameListVie = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String highestLevel = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String lowestLevel = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String createdDate = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String modifiedDate = GtnFrameworkCommonStringConstants.STRING_EMPTY;

		for (GtnWebServiceSearchCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				switch (searchCriteria.getFieldId()) {
				case "prodHierarchyLookupHierarchyType":
					hierarchyType = searchCriteria.getFilterValue1().replace("*", "%");
					break;
				case "prodHierarchyLookupHierarchyName":
					hierarchyName = searchCriteria.getFilterValue1().replace("*", "%");
					break;
				case "prodHierarchyLookupHierName":
					hierarchyNameListVie = "AND c.HIERARCHY_NAME like '%" + searchCriteria.getFilterValue1() + "%'";
					break;
				case "prodHierarchyLookupHighestLevel":
					highestLevel = getLevelFilter(searchCriteria);
					break;
				case "prodHierarchyLookupLowestLevel":
					lowestLevel = getLevelFilter(searchCriteria);
					break;
				case "prodHierarchyLookupCreatedDate":

					createdDate = getDateFilter(searchCriteria, GtnFrameworkCommonConstants.C_CREATED_DATE);
					break;
				case "prodHierarchyLookupModifiedDate":
					modifiedDate = getDateFilter(searchCriteria, GtnFrameworkCommonConstants.C_MODIFIED_DATE);
					break;
				default:
					break;
				}
			}

		}
		productHierarchySearchResultList.add(hierarchyName);
		productHierarchySearchResultList.add(hierarchyType);
		productHierarchySearchResultList.add(hierarchyNameListVie);
		productHierarchySearchResultList.add(highestLevel);
		productHierarchySearchResultList.add(lowestLevel);
		productHierarchySearchResultList.add(createdDate);
		productHierarchySearchResultList.add(modifiedDate);
		if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			productHierarchySearchResultList.addAll(getProductHierarchySortedInputs(
					gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
			productHierarchySearchResultList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
			productHierarchySearchResultList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
		}
		return productHierarchySearchResultList;
	}

	String getComparisonOperator(String expression) {
		if (expression.contains(GtnFrameworkWebserviceConstant.GREATER)) {
			return ">";
		} else if (expression.contains("LESS")) {
			return "<";
		}
		return "=";
	}

	String getLevelFilter(GtnWebServiceSearchCriteria searchCriteria) {
		String exp = getComparisonOperator(searchCriteria.getExpression());
		return "AND B.LEVEL_NO " + exp + " " + searchCriteria.getFilterValue1();
	}

	String getDateFilter(GtnWebServiceSearchCriteria searchCriteria, String column) throws ParseException {
		String dateFilter = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (searchCriteria.getFilterValue2() != null && !searchCriteria.getFilterValue2().isEmpty()) {
			dateFilter = "AND " + column + " BETWEEN '" + getDate(searchCriteria.getFilterValue1())
					+ GtnFrameworkWebserviceConstant.AND + getDate(searchCriteria.getFilterValue2()) + "'";
		}
		return dateFilter;
	}

	public String getDate(String input) throws ParseException {

		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = formatter.parse(input);
		SimpleDateFormat commonDate = new SimpleDateFormat("MM/dd/yyyy");
		return commonDate.format(date);
	}

	private List<Object> getProductHierarchySortedInputs(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> sortedProductHierarchyList = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			sortedProductHierarchyList
					.add(sortedProductHierarchyMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			sortedProductHierarchyList.add(sortedProductHierarchyMap().get("sid") + " ASC");
		}
		return sortedProductHierarchyList;
	}

	private Map<String, String> sortedProductHierarchyMap() {
		Map<String, String> sortedProductHierarchyMap = new HashMap<>();
		sortedProductHierarchyMap.put("sid", "c.HIERARCHY_DEFINITION_SID");
		sortedProductHierarchyMap.put("prodHierarchyLookupHierName", "c.HIERARCHY_NAME");
		sortedProductHierarchyMap.put("prodHierarchyLookupHighestLevel", "A.LEVEL_NO");
		sortedProductHierarchyMap.put("prodHierarchyLookupLowestLevel", "B.LEVEL_NO");
		sortedProductHierarchyMap.put("prodHierarchyLookupCreatedDate", GtnFrameworkCommonConstants.C_CREATED_DATE);
		sortedProductHierarchyMap.put("prodHierarchyLookupModifiedDate", GtnFrameworkCommonConstants.C_MODIFIED_DATE);
		return sortedProductHierarchyMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getCustomerHierarchyLookUp", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCustomerHierarchyLookUp(
			@RequestBody GtnUIFrameworkWebserviceRequest customerHierarchyWsRequest) {

		GtnUIFrameworkWebserviceResponse customerHierarchyResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnSerachResponse customerHierarchySerachResponse = new GtnSerachResponse();
			String queryName = customerHierarchyWsRequest.getGtnWsSearchRequest().isCount()
					? "getCountCommercialForecastingCustomerHierarchy"
					: "getDataCommercialForecastingCustomerHierarchy";

			List<Object[]> result = gtnGeneralServiceController
					.executeQuery(gtnGeneralServiceController.getGtnWsSqlService()
							.getQuery(getCustomerHierarchyLookUpInput(customerHierarchyWsRequest), queryName));
			if (customerHierarchyWsRequest.getGtnWsSearchRequest().isCount()) {
				customerHierarchySerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable customerHierarchyDataTable = new GtnUIFrameworkDataTable();
				customerHierarchyDataTable.addData(result);
				customerHierarchySerachResponse.setResultSet(customerHierarchyDataTable);
			}
			customerHierarchyResponse.setGtnSerachResponse(customerHierarchySerachResponse);
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error(ex.getMessage());
			customerHierarchyResponse.getGtnWsGeneralResponse().setSucess(false);
			customerHierarchyResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return customerHierarchyResponse;
	}

	private List<Object> getCustomerHierarchyLookUpInput(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws ParseException {

		List<Object> customerHierarchySearchResultList = new ArrayList<>();
		String hierarchyName = "%";
		String hierarchyType = "%";
		String hierarchyNameListVie = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String highestLevel = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String lowestLevel = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String createdDate = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String modifiedDate = GtnFrameworkCommonStringConstants.STRING_EMPTY;

		for (GtnWebServiceSearchCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			String exp = "=";
			if (searchCriteria.getExpression().contains(GtnFrameworkWebserviceConstant.GREATER)) {
				exp = ">";
			} else if (searchCriteria.getExpression().contains("LESS")) {
				exp = "<";
			}
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				switch (searchCriteria.getFieldId()) {
				case "custHierarchyLookupHierarchyType":
					hierarchyType = searchCriteria.getFilterValue1().replace("*", "%");
					break;
				case "custHierarchyLookupHierarchyName":
					hierarchyName = searchCriteria.getFilterValue1().replace("*", "%");
					break;
				case "custHierarchyLookupHierName":
					hierarchyNameListVie = "AND c.HIERARCHY_NAME like '%" + searchCriteria.getFilterValue1() + "%'";
					break;
				case "custHierarchyLookupHighestLevel":
					highestLevel = "AND A.LEVEL_NO " + exp + " " + searchCriteria.getFilterValue1();
					break;
				case "custHierarchyLookupLowestLevel":
					lowestLevel = "AND B.LEVEL_NO " + exp + " " + searchCriteria.getFilterValue1();
					break;
				case "custHierarchyLookupCreatedDate":
					getDateFilter(searchCriteria, GtnFrameworkCommonConstants.C_CREATED_DATE);
					break;
				case "custHierarchyLookupModifiedDate":
					getDateFilter(searchCriteria, GtnFrameworkCommonConstants.C_MODIFIED_DATE);
					break;
				default:
					break;
				}
			}

		}
		hierarchyName = !hierarchyName.isEmpty() ? hierarchyName : "%";
		customerHierarchySearchResultList.add(hierarchyName);
		customerHierarchySearchResultList.add(hierarchyType);
		customerHierarchySearchResultList.add(hierarchyNameListVie);
		customerHierarchySearchResultList.add(highestLevel);
		customerHierarchySearchResultList.add(lowestLevel);
		customerHierarchySearchResultList.add(createdDate);
		customerHierarchySearchResultList.add(modifiedDate);
		if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			customerHierarchySearchResultList.addAll(getCustomerHierarchySortedInputs(
					gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
			customerHierarchySearchResultList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
			customerHierarchySearchResultList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
		}
		return customerHierarchySearchResultList;
	}

	private List<Object> getCustomerHierarchySortedInputs(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> sortedCustomerHierarchyList = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			sortedCustomerHierarchyList
					.add(sortedCustomerHierarchyMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			sortedCustomerHierarchyList.add(sortedCustomerHierarchyMap().get("sid") + " ASC");
		}
		return sortedCustomerHierarchyList;
	}

	private Map<String, String> sortedCustomerHierarchyMap() {
		Map<String, String> sortedCustomerHierarchyMap = new HashMap<>();
		sortedCustomerHierarchyMap.put("sid", "c.HIERARCHY_DEFINITION_SID");
		sortedCustomerHierarchyMap.put("custHierarchyLookupHierName", "c.HIERARCHY_NAME");
		sortedCustomerHierarchyMap.put("custHierarchyLookupHighestLevel", "A.LEVEL_NO");
		sortedCustomerHierarchyMap.put("custHierarchyLookupLowestLevel", "B.LEVEL_NO");
		sortedCustomerHierarchyMap.put("custHierarchyLookupCreatedDate", GtnFrameworkCommonConstants.C_CREATED_DATE);
		sortedCustomerHierarchyMap.put("custHierarchyLookupModifiedDate", GtnFrameworkCommonConstants.C_MODIFIED_DATE);
		return sortedCustomerHierarchyMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getProductGroupLookUp", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getProductGroupLookUp(
			@RequestBody GtnUIFrameworkWebserviceRequest productGroupRequest) {
		GtnUIFrameworkWebserviceResponse productGroupResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnSerachResponse productGroupSerachResponse = new GtnSerachResponse();
			String queryName = productGroupRequest.getGtnWsSearchRequest().isCount()
					? "getCountCommercialForecastingProdGroupLookUp" : "getDataCommercialForecastingProdGroupLookUp";

			List<Object[]> result = gtnGeneralServiceController.executeQuery(gtnGeneralServiceController
					.getGtnWsSqlService().getQuery(getProductGroupLookUpInput(productGroupRequest), queryName));
			if (productGroupRequest.getGtnWsSearchRequest().isCount()) {
				productGroupSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable productGroupDataTable = new GtnUIFrameworkDataTable();
				productGroupDataTable.addData(result);
				productGroupSerachResponse.setResultSet(productGroupDataTable);
			}
			productGroupResponse.setGtnSerachResponse(productGroupSerachResponse);
		} catch (GtnFrameworkGeneralException ex) {
			productGroupResponse.getGtnWsGeneralResponse().setSucess(false);
			productGroupResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return productGroupResponse;

	}

	private List<Object> getProductGroupLookUpInput(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		List<Object> productGroupLookUpInputList = new ArrayList<>();
		String productGroupName = "%";
		String productGroupNo = "%";
		String productGroupNameFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String productGroupNoFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String productGroupDescFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String companyNameFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;

		for (GtnWebServiceSearchCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				switch (searchCriteria.getFieldId()) {
				case "prodGroupLookupProductGroupName":
					productGroupName = searchCriteria.getFilterValue1().replace("*", "%");
					break;
				case "prodGroupLookupProductGroupNo":
					productGroupNo = searchCriteria.getFilterValue1().replace("*", "%");
					break;
				case "prodGroupLookupProductGroupNameFilterView":

					productGroupNameFilterView = "AND (IG.ITEM_GROUP_NAME LIKE '%" + searchCriteria.getFilterValue1()
							+ "%')";
					break;
				case "prodGroupLookupProductGroupNoFilterView":
					productGroupNoFilterView = "AND (IG.ITEM_GROUP_NO LIKE '%" + searchCriteria.getFilterValue1()
							+ "%' OR IG.ITEM_GROUP_NO IS NULL )";
					break;
				case "prodGroupLookupProductGroupDescFilterView":
					productGroupDescFilterView = "AND (IG.ITEM_GROUP_DESCRIPTION LIKE '%"
							+ searchCriteria.getFilterValue1() + "%')";
					break;
				case "prodGroupLookupCompanyNameFilterView":
					companyNameFilterView = "AND (CM.COMPANY_NAME  LIKE '%" + searchCriteria.getFilterValue1()
							+ "%' OR CM.COMPANY_NAME IS NULL )";
					break;
				default:
					break;
				}
			}
		}

		productGroupNo = (!productGroupName.isEmpty() && productGroupNo.isEmpty()) ? "%" : productGroupNo;
		productGroupName = (!productGroupNo.isEmpty() && productGroupName.isEmpty()) ? "%" : productGroupName;

		productGroupLookUpInputList.add(productGroupName);
		productGroupLookUpInputList.add(productGroupNo);
		productGroupLookUpInputList.add(productGroupNameFilterView);
		productGroupLookUpInputList.add(productGroupNoFilterView);
		productGroupLookUpInputList.add(productGroupDescFilterView);
		productGroupLookUpInputList.add(companyNameFilterView);

		if (!gtnWsRequest.getGtnWsSearchRequest().isCount())

		{
			productGroupLookUpInputList.addAll(getProductGroupSortedInputs(
					gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
			productGroupLookUpInputList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
			productGroupLookUpInputList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
		}
		return productGroupLookUpInputList;
	}

	private List<Object> getProductGroupSortedInputs(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> productGroupSortedList = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			productGroupSortedList
					.add(sortedProductGroupMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			productGroupSortedList.add(sortedProductGroupMap().get("sid") + " ASC");
		}
		return productGroupSortedList;
	}

	private Map<String, String> sortedProductGroupMap() {

		Map<String, String> sortedProductGroupMap = new HashMap<>();
		sortedProductGroupMap.put("sid", "IG.ITEM_GROUP_SID");
		sortedProductGroupMap.put("prodGroupLookupProductGroupNameFilterView", "IG.ITEM_GROUP_NAME");
		sortedProductGroupMap.put("prodGroupLookupProductGroupNoFilterView", "IG.ITEM_GROUP_NO");
		sortedProductGroupMap.put("prodGroupLookupProductGroupDescFilterView", "IG.ITEM_GROUP_DESCRIPTION");
		sortedProductGroupMap.put("prodGroupLookupCompanyNameFilterView", " COMPANY_NAME");
		return sortedProductGroupMap;

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getCustomerGroupLookUp", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCustomerGroupLookUp(
			@RequestBody GtnUIFrameworkWebserviceRequest customerGroupRequest) {
		GtnUIFrameworkWebserviceResponse customerGroupResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnSerachResponse customerGroupSerachResponse = new GtnSerachResponse();
			String queryName = customerGroupRequest.getGtnWsSearchRequest().isCount()
					? "getCountCommercialForecastingCustGroupLookUp" : "getDataCommercialForecastingCustGroupLookUp";

			List<Object[]> result = gtnGeneralServiceController.executeQuery(gtnGeneralServiceController
					.getGtnWsSqlService().getQuery(getCustomerGroupLookUpInput(customerGroupRequest), queryName));
			if (customerGroupRequest.getGtnWsSearchRequest().isCount()) {
				customerGroupSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable customerGroupDataTable = new GtnUIFrameworkDataTable();
				customerGroupDataTable.addData(result);
				customerGroupSerachResponse.setResultSet(customerGroupDataTable);
			}
			customerGroupResponse.setGtnSerachResponse(customerGroupSerachResponse);
		} catch (GtnFrameworkGeneralException ex) {
			customerGroupResponse.getGtnWsGeneralResponse().setSucess(false);
			customerGroupResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		} catch (Exception ex) {
			LOGGER.error("Error in Customer Group Lookup", ex);
		}
		return customerGroupResponse;

	}

	private List<Object> getCustomerGroupLookUpInput(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		List<Object> customerGroupLookUpInputList = new ArrayList<>();
		String customerGroupName = "%";
		String customerGroupNo = "%";
		String customerGroupNameFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String customerGroupNoFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String customerGroupDescFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;

		for (GtnWebServiceSearchCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				switch (searchCriteria.getFieldId()) {
				case "customerGroupLookupName":
					customerGroupName = searchCriteria.getFilterValue1().replace("*", "%");
					break;
				case "customerGroupLookupNo":
					customerGroupNo = searchCriteria.getFilterValue1().replace("*", "%");
					break;
				case "custGroupLookupCustomerGroupNameFilterView":
					customerGroupNameFilterView = "AND (IG.COMPANY_GROUP_NAME LIKE '%"
							+ searchCriteria.getFilterValue1() + "%')";
					break;
				case "custGroupLookupCustomerGroupNoFilterView":
					customerGroupNoFilterView = "AND (IG.COMPANY_GROUP_NO LIKE '%" + searchCriteria.getFilterValue1()
							+ "%' OR IG.COMPANY_GROUP_NO IS NULL )";
					break;
				case "custGroupLookupCusomterDescFilterView":
					customerGroupDescFilterView = "AND (IG.COMPANY_GROUP_DESCRIPTION LIKE '%"
							+ searchCriteria.getFilterValue1() + "%')";
					break;
				default:
					break;
				}
			}

		}

		customerGroupNo = (!customerGroupName.isEmpty() && customerGroupNo.isEmpty()) ? "%" : customerGroupNo;
		customerGroupName = (!customerGroupNo.isEmpty() && customerGroupName.isEmpty()) ? "%" : customerGroupName;

		customerGroupLookUpInputList.add(customerGroupName);
		customerGroupLookUpInputList.add(customerGroupNo);
		customerGroupLookUpInputList.add(customerGroupNameFilterView);
		customerGroupLookUpInputList.add(customerGroupNoFilterView);
		customerGroupLookUpInputList.add(customerGroupDescFilterView);

		if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			customerGroupLookUpInputList.addAll(getCustomerGroupSortedInputs(
					gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
			customerGroupLookUpInputList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
			customerGroupLookUpInputList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
		}
		return customerGroupLookUpInputList;
	}

	private List<Object> getCustomerGroupSortedInputs(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> customerGroupSortedList = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			customerGroupSortedList
					.add(sortedCustomerGroupMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			customerGroupSortedList.add(sortedCustomerGroupMap().get("sid") + " ASC");
		}
		return customerGroupSortedList;
	}

	private Map<String, String> sortedCustomerGroupMap() {

		Map<String, String> sortedcustomerGroupMap = new HashMap<>();
		sortedcustomerGroupMap.put("sid", "IG.COMPANY_GROUP_SID");
		sortedcustomerGroupMap.put("custGroupLookupCustomerGroupNameFilterView", "IG.COMPANY_GROUP_NAME");
		sortedcustomerGroupMap.put("custGroupLookupCustomerGroupNoFilterView", "IG.COMPANY_GROUP_NO");
		sortedcustomerGroupMap.put("custGroupLookupCusomterDescFilterView", "IG.COMPANY_GROUP_DESCRIPTION");
		return sortedcustomerGroupMap;

	}

}
