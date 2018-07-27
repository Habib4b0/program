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
	private GtnWsGeneralController gtnWSGeneralServiceController;

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	public GtnWsGeneralController getGtnWSGeneralServiceController() {
		return gtnWSGeneralServiceController;
	}

	public void setGtnWSGeneralServiceController(GtnWsGeneralController gtnWSGeneralServiceController) {
		this.gtnWSGeneralServiceController = gtnWSGeneralServiceController;
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
			@RequestBody GtnUIFrameworkWebserviceRequest productHierarchyWSRequest) {
		GtnUIFrameworkWebserviceResponse productHierarchyResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			LOGGER.info(" inside Product Hierarchy search ");
			GtnSerachResponse productHierarchySearchResponse = new GtnSerachResponse();
			String queryName = productHierarchyWSRequest.getGtnWsSearchRequest().isCount()
					? "getCountCommercialForecastingProductHierarchy" : "getDataCommercialForecastingProductHierarchy";

			List<Object[]> result = gtnWSGeneralServiceController.executeQuery(gtnWSGeneralServiceController
					.getGtnWsSqlService().getQuery(getProductHierarchyLookUpInput(productHierarchyWSRequest), queryName));
			if (productHierarchyWSRequest.getGtnWsSearchRequest().isCount()) {
				productHierarchySearchResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(result);
				productHierarchySearchResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			productHierarchyResponse.setGtnSerachResponse(productHierarchySearchResponse);
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

		List<Object> productHierarchySearchResultListForCF = new ArrayList<>();
		String hierarchyNameForCF = "%";
		String hierarchyTypeForCF = "%";
		String hierarchyNameList = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String highestLevelValue = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String lowestLevelValue = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String createdDateOfPH = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String modifiedDateOfPH = GtnFrameworkCommonStringConstants.STRING_EMPTY;

		for (GtnWebServiceSearchCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				switch (searchCriteria.getFieldId()) {
				case "prodHierarchyLookupHierarchyType":
					hierarchyTypeForCF = searchCriteria.getFilterValue1().replace('*', '%');
					break;
				case "prodHierarchyLookupHierarchyName":
					hierarchyNameForCF = searchCriteria.getFilterValue1().replace('*', '%');
					break;
				case "prodHierarchyLookupHierName":
					hierarchyNameList = "AND c.HIERARCHY_NAME like '%" + searchCriteria.getFilterValue1() + "%'";
					break;
				case "prodHierarchyLookupHighestLevel":
					highestLevelValue = getLevelFilter(searchCriteria);
					break;
				case "prodHierarchyLookupLowestLevel":
					lowestLevelValue = getLevelFilter(searchCriteria);
					break;
				case "prodHierarchyLookupCreatedDate":

					createdDateOfPH = getDateFilter(searchCriteria, GtnFrameworkCommonConstants.C_CREATED_DATE);
					break;
				case "prodHierarchyLookupModifiedDate":
					modifiedDateOfPH = getDateFilter(searchCriteria, GtnFrameworkCommonConstants.C_MODIFIED_DATE);
					break;
				default:
					break;
				}
			}

		}
		productHierarchySearchResultListForCF.add(hierarchyNameForCF);
		productHierarchySearchResultListForCF.add(hierarchyTypeForCF);
		productHierarchySearchResultListForCF.add(hierarchyNameList);
		productHierarchySearchResultListForCF.add(highestLevelValue);
		productHierarchySearchResultListForCF.add(lowestLevelValue);
		productHierarchySearchResultListForCF.add(createdDateOfPH);
		productHierarchySearchResultListForCF.add(modifiedDateOfPH);
		if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			productHierarchySearchResultListForCF.addAll(getProductHierarchySortedInputs(
					gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
			productHierarchySearchResultListForCF.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
			productHierarchySearchResultListForCF.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
		}
		return productHierarchySearchResultListForCF;
	}

	String getComparisonOperator(String compExpression) {
		if (compExpression.contains(GtnFrameworkWebserviceConstant.GREATER)) {
			return ">";
		} else if (compExpression.contains("LESS")) {
			return "<";
		}
		return "=";
	}

	String getLevelFilter(GtnWebServiceSearchCriteria wsSearchCriteria) {
		String exp = getComparisonOperator(wsSearchCriteria.getExpression());
		return "AND B.LEVEL_NO " + exp + " " + wsSearchCriteria.getFilterValue1();
	}

	String getDateFilter(GtnWebServiceSearchCriteria wsDFSearchCriteria, String column) throws ParseException {
		String dateFilter = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		if (wsDFSearchCriteria.getFilterValue2() != null && !wsDFSearchCriteria.getFilterValue2().isEmpty()) {
			dateFilter = "AND " + column + " BETWEEN '" + getDate(wsDFSearchCriteria.getFilterValue1())
					+ GtnFrameworkWebserviceConstant.AND + getDate(wsDFSearchCriteria.getFilterValue2()) + "'";
		}
		return dateFilter;
	}

	public String getDate(String dateInput) throws ParseException {

		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = formatter.parse(dateInput);
		SimpleDateFormat commonDate = new SimpleDateFormat("MM/dd/yyyy");
		return commonDate.format(date);
	}

	private List<Object> getProductHierarchySortedInputs(
			List<GtnWebServiceOrderByCriteria> gtnPHWebServiceOrderByCriteriaList) {
		List<Object> sortedProductHierList = new ArrayList<>();
		if (!gtnPHWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnPHWebServiceOrderByCriteriaList.get(0);
			sortedProductHierList
					.add(sortedProductHierarchyMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			sortedProductHierList.add(sortedProductHierarchyMap().get("sid") + " ASC");
		}
		return sortedProductHierList;
	}

	private Map<String, String> sortedProductHierarchyMap() {
		Map<String, String> sortedProductHierMap = new HashMap<>();
		sortedProductHierMap.put("sid", "c.HIERARCHY_DEFINITION_SID");
		sortedProductHierMap.put("prodHierarchyLookupHierName", "c.HIERARCHY_NAME");
		sortedProductHierMap.put("prodHierarchyLookupHighestLevel", "A.LEVEL_NO");
		sortedProductHierMap.put("prodHierarchyLookupLowestLevel", "B.LEVEL_NO");
		sortedProductHierMap.put("prodHierarchyLookupCreatedDate", GtnFrameworkCommonConstants.C_CREATED_DATE);
		sortedProductHierMap.put("prodHierarchyLookupModifiedDate", GtnFrameworkCommonConstants.C_MODIFIED_DATE);
		return sortedProductHierMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getCustomerHierarchyLookUp", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCustomerHierarchyLookUp(
			@RequestBody GtnUIFrameworkWebserviceRequest customerHierarchyWSRequest) {

		GtnUIFrameworkWebserviceResponse customerHierLookupResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnSerachResponse customerHierarchySerachResponse = new GtnSerachResponse();
			String queryName = customerHierarchyWSRequest.getGtnWsSearchRequest().isCount()
					? "getCountCommercialForecastingCustomerHierarchy"
					: "getDataCommercialForecastingCustomerHierarchy";

			List<Object[]> result = gtnWSGeneralServiceController
					.executeQuery(gtnWSGeneralServiceController.getGtnWsSqlService()
							.getQuery(getCustomerHierarchyLookUpInput(customerHierarchyWSRequest), queryName));
			if (customerHierarchyWSRequest.getGtnWsSearchRequest().isCount()) {
				customerHierarchySerachResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable customerHierarchyDataTable = new GtnUIFrameworkDataTable();
				customerHierarchyDataTable.addData(result);
				customerHierarchySerachResponse.setResultSet(customerHierarchyDataTable);
			}
			customerHierLookupResponse.setGtnSerachResponse(customerHierarchySerachResponse);
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error(ex.getMessage());
			customerHierLookupResponse.getGtnWsGeneralResponse().setSucess(false);
			customerHierLookupResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return customerHierLookupResponse;
	}

	private List<Object> getCustomerHierarchyLookUpInput(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws ParseException {

		List<Object> customerHierSearchResultList = new ArrayList<>();
		String hierarchyNameForCH = "%";
		String hierarchyTypeForCH = "%";
		String hierarchyNameListForCH = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String highestLevelForCH = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String lowestLevelForCH = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String createdDateOfCH = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String modifiedDateOfCH = GtnFrameworkCommonStringConstants.STRING_EMPTY;

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
					hierarchyTypeForCH = searchCriteria.getFilterValue1().replace('*', '%');
					break;
				case "custHierarchyLookupHierarchyName":
					hierarchyNameForCH = searchCriteria.getFilterValue1().replace('*', '%');
					break;
				case "custHierarchyLookupHierName":
					hierarchyNameListForCH = "AND c.HIERARCHY_NAME like '%" + searchCriteria.getFilterValue1() + "%'";
					break;
				case "custHierarchyLookupHighestLevel":
					highestLevelForCH = "AND A.LEVEL_NO " + exp + " " + searchCriteria.getFilterValue1();
					break;
				case "custHierarchyLookupLowestLevel":
					lowestLevelForCH = "AND B.LEVEL_NO " + exp + " " + searchCriteria.getFilterValue1();
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
		hierarchyNameForCH = !hierarchyNameForCH.isEmpty() ? hierarchyNameForCH : "%";
		customerHierSearchResultList.add(hierarchyNameForCH);
		customerHierSearchResultList.add(hierarchyTypeForCH);
		customerHierSearchResultList.add(hierarchyNameListForCH);
		customerHierSearchResultList.add(highestLevelForCH);
		customerHierSearchResultList.add(lowestLevelForCH);
		customerHierSearchResultList.add(createdDateOfCH);
		customerHierSearchResultList.add(modifiedDateOfCH);
		if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			customerHierSearchResultList.addAll(getCustomerHierarchySortedInputs(
					gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
			customerHierSearchResultList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
			customerHierSearchResultList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
		}
		return customerHierSearchResultList;
	}

	private List<Object> getCustomerHierarchySortedInputs(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> sortedCustomerHierarList = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			sortedCustomerHierarList
					.add(sortedCustomerHierarchyMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			sortedCustomerHierarList.add(sortedCustomerHierarchyMap().get("sid") + " ASC");
		}
		return sortedCustomerHierarList;
	}

	private Map<String, String> sortedCustomerHierarchyMap() {
		Map<String, String> sortedCustomerHierMap = new HashMap<>();
		sortedCustomerHierMap.put("sid", "c.HIERARCHY_DEFINITION_SID");
		sortedCustomerHierMap.put("custHierarchyLookupHierName", "c.HIERARCHY_NAME");
		sortedCustomerHierMap.put("custHierarchyLookupHighestLevel", "A.LEVEL_NO");
		sortedCustomerHierMap.put("custHierarchyLookupLowestLevel", "B.LEVEL_NO");
		sortedCustomerHierMap.put("custHierarchyLookupCreatedDate", GtnFrameworkCommonConstants.C_CREATED_DATE);
		sortedCustomerHierMap.put("custHierarchyLookupModifiedDate", GtnFrameworkCommonConstants.C_MODIFIED_DATE);
		return sortedCustomerHierMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getProductGroupLookUp", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getProductGroupLookUp(
			@RequestBody GtnUIFrameworkWebserviceRequest productGroupWSRequest) {
		GtnUIFrameworkWebserviceResponse productGroupWSResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnSerachResponse productGroupSearchResponse = new GtnSerachResponse();
			String queryName = productGroupWSRequest.getGtnWsSearchRequest().isCount()
					? "getCountCommercialForecastingProdGroupLookUp" : "getDataCommercialForecastingProdGroupLookUp";

			List<Object[]> result = gtnWSGeneralServiceController.executeQuery(gtnWSGeneralServiceController
					.getGtnWsSqlService().getQuery(getProductGroupLookUpInput(productGroupWSRequest), queryName));
			if (productGroupWSRequest.getGtnWsSearchRequest().isCount()) {
				productGroupSearchResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable productGroupDataTable = new GtnUIFrameworkDataTable();
				productGroupDataTable.addData(result);
				productGroupSearchResponse.setResultSet(productGroupDataTable);
			}
			productGroupWSResponse.setGtnSerachResponse(productGroupSearchResponse);
		} catch (GtnFrameworkGeneralException ex) {
			productGroupWSResponse.getGtnWsGeneralResponse().setSucess(false);
			productGroupWSResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return productGroupWSResponse;

	}

	private List<Object> getProductGroupLookUpInput(GtnUIFrameworkWebserviceRequest gtnWsProdGroupRequest) {

		List<Object> prodGroupLookUpInputList = new ArrayList<>();
		String prodGroupName = "%";
		String prodGroupNo = "%";
		String prodGroupNameFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String prodGroupNoFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String prodGroupDescFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String compNameFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;

		for (GtnWebServiceSearchCriteria searchCriteria : gtnWsProdGroupRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				switch (searchCriteria.getFieldId()) {
				case "prodGroupLookupProductGroupName":
					prodGroupName = searchCriteria.getFilterValue1().replace('*', '%');
					break;
				case "prodGroupLookupProductGroupNo":
					prodGroupNo = searchCriteria.getFilterValue1().replace('*', '%');
					break;
				case "prodGroupLookupProductGroupNameFilterView":

					prodGroupNameFilterView = "AND (IG.ITEM_GROUP_NAME LIKE '%" + searchCriteria.getFilterValue1()
							+ "%')";
					break;
				case "prodGroupLookupProductGroupNoFilterView":
					prodGroupNoFilterView = "AND (IG.ITEM_GROUP_NO LIKE '%" + searchCriteria.getFilterValue1()
							+ "%' OR IG.ITEM_GROUP_NO IS NULL )";
					break;
				case "prodGroupLookupProductGroupDescFilterView":
					prodGroupDescFilterView = "AND (IG.ITEM_GROUP_DESCRIPTION LIKE '%"
							+ searchCriteria.getFilterValue1() + "%')";
					break;
				case "prodGroupLookupCompanyNameFilterView":
					compNameFilterView = "AND (CM.COMPANY_NAME  LIKE '%" + searchCriteria.getFilterValue1()
							+ "%' OR CM.COMPANY_NAME IS NULL )";
					break;
				default:
					break;
				}
			}
		}

		prodGroupNo = (!prodGroupName.isEmpty() && prodGroupNo.isEmpty()) ? "%" : prodGroupNo;
		prodGroupName = (!prodGroupNo.isEmpty() && prodGroupName.isEmpty()) ? "%" : prodGroupName;

		prodGroupLookUpInputList.add(prodGroupName);
		prodGroupLookUpInputList.add(prodGroupNo);
		prodGroupLookUpInputList.add(prodGroupNameFilterView);
		prodGroupLookUpInputList.add(prodGroupNoFilterView);
		prodGroupLookUpInputList.add(prodGroupDescFilterView);
		prodGroupLookUpInputList.add(compNameFilterView);

		if (!gtnWsProdGroupRequest.getGtnWsSearchRequest().isCount())

		{
			prodGroupLookUpInputList.addAll(getProductGroupSortedInputs(
					gtnWsProdGroupRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
			prodGroupLookUpInputList.add(gtnWsProdGroupRequest.getGtnWsSearchRequest().getTableRecordStart());
			prodGroupLookUpInputList.add(gtnWsProdGroupRequest.getGtnWsSearchRequest().getTableRecordOffset());
		}
		return prodGroupLookUpInputList;
	}

	private List<Object> getProductGroupSortedInputs(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> productGroupSortedList = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria orderByCriteriaDto = gtnWebServiceOrderByCriteriaList.get(0);
			productGroupSortedList
					.add(sortedProductGroupMap().get(orderByCriteriaDto.getPropertyId()) + " " + orderByCriteriaDto.getOrderByCriteria());
		} else {
			productGroupSortedList.add(sortedProductGroupMap().get("sid") + " ASC");
		}
		return productGroupSortedList;
	}

	private Map<String, String> sortedProductGroupMap() {

		Map<String, String> sortedProdGroupMap = new HashMap<>();
		sortedProdGroupMap.put("sid", "IG.ITEM_GROUP_SID");
		sortedProdGroupMap.put("prodGroupLookupProductGroupNameFilterView", "IG.ITEM_GROUP_NAME");
		sortedProdGroupMap.put("prodGroupLookupProductGroupNoFilterView", "IG.ITEM_GROUP_NO");
		sortedProdGroupMap.put("prodGroupLookupProductGroupDescFilterView", "IG.ITEM_GROUP_DESCRIPTION");
		sortedProdGroupMap.put("prodGroupLookupCompanyNameFilterView", " COMPANY_NAME");
		return sortedProdGroupMap;

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getCustomerGroupLookUp", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getCustomerGroupLookUp(
			@RequestBody GtnUIFrameworkWebserviceRequest customerGroupRequest) {
		GtnUIFrameworkWebserviceResponse customerGroupResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnSerachResponse customerGroupSearchResponse = new GtnSerachResponse();
			String queryName = customerGroupRequest.getGtnWsSearchRequest().isCount()
					? "getCountCommercialForecastingCustGroupLookUp" : "getDataCommercialForecastingCustGroupLookUp";

			List<Object[]> result = gtnWSGeneralServiceController.executeQuery(gtnWSGeneralServiceController
					.getGtnWsSqlService().getQuery(getCustomerGroupLookUpInput(customerGroupRequest), queryName));
			if (customerGroupRequest.getGtnWsSearchRequest().isCount()) {
				customerGroupSearchResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable customerGroupDataTable = new GtnUIFrameworkDataTable();
				customerGroupDataTable.addData(result);
				customerGroupSearchResponse.setResultSet(customerGroupDataTable);
			}
			customerGroupResponse.setGtnSerachResponse(customerGroupSearchResponse);
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
		String customerGroupNameForLookup = "%";
		String customerGroupNoForLookup = "%";
		String customerGroupNameForFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String customerGroupNoForFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String customerGroupDescForFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;

		for (GtnWebServiceSearchCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				switch (searchCriteria.getFieldId()) {
				case "customerGroupLookupName":
					customerGroupNameForLookup = searchCriteria.getFilterValue1().replace('*', '%');
					break;
				case "customerGroupLookupNo":
					customerGroupNoForLookup = searchCriteria.getFilterValue1().replace('*', '%');
					break;
				case "custGroupLookupCustomerGroupNameFilterView":
					customerGroupNameForFilterView = "AND (IG.COMPANY_GROUP_NAME LIKE '%"
							+ searchCriteria.getFilterValue1() + "%')";
					break;
				case "custGroupLookupCustomerGroupNoFilterView":
					customerGroupNoForFilterView = "AND (IG.COMPANY_GROUP_NO LIKE '%" + searchCriteria.getFilterValue1()
							+ "%' OR IG.COMPANY_GROUP_NO IS NULL )";
					break;
				case "custGroupLookupCusomterDescFilterView":
					customerGroupDescForFilterView = "AND (IG.COMPANY_GROUP_DESCRIPTION LIKE '%"
							+ searchCriteria.getFilterValue1() + "%')";
					break;
				default:
					break;
				}
			}

		}

		customerGroupNoForLookup = (!customerGroupNameForLookup.isEmpty() && customerGroupNoForLookup.isEmpty()) ? "%" : customerGroupNoForLookup;
		customerGroupNameForLookup = (!customerGroupNoForLookup.isEmpty() && customerGroupNameForLookup.isEmpty()) ? "%" : customerGroupNameForLookup;

		customerGroupLookUpInputList.add(customerGroupNameForLookup);
		customerGroupLookUpInputList.add(customerGroupNoForLookup);
		customerGroupLookUpInputList.add(customerGroupNameForFilterView);
		customerGroupLookUpInputList.add(customerGroupNoForFilterView);
		customerGroupLookUpInputList.add(customerGroupDescForFilterView);

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
		List<Object> customerGroupSortedInputList = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			customerGroupSortedInputList
					.add(sortedCustomerGroupMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			customerGroupSortedInputList.add(sortedCustomerGroupMap().get("sid") + " ASC");
		}
		return customerGroupSortedInputList;
	}

	private Map<String, String> sortedCustomerGroupMap() {

		Map<String, String> sortedCustomerGroupMap = new HashMap<>();
		sortedCustomerGroupMap.put("sid", "IG.COMPANY_GROUP_SID");
		sortedCustomerGroupMap.put("custGroupLookupCustomerGroupNameFilterView", "IG.COMPANY_GROUP_NAME");
		sortedCustomerGroupMap.put("custGroupLookupCustomerGroupNoFilterView", "IG.COMPANY_GROUP_NO");
		sortedCustomerGroupMap.put("custGroupLookupCusomterDescFilterView", "IG.COMPANY_GROUP_DESCRIPTION");
		return sortedCustomerGroupMap;

	}

}
