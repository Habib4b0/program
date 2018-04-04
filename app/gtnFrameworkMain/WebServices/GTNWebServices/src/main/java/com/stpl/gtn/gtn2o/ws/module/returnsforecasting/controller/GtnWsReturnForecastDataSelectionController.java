package com.stpl.gtn.gtn2o.ws.module.returnsforecasting.controller;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
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
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 * @author Kalpana.Ramanana
 *
 */
@RestController
@RequestMapping("gtnReturnsForecasting/lookUp")
public class GtnWsReturnForecastDataSelectionController {

	public GtnWsReturnForecastDataSelectionController() {
		/*
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger
			.getGTNLogger(GtnWsReturnForecastDataSelectionController.class);

	@Autowired
	private GtnWsGeneralController gtnGeneralServiceController;
        @Autowired
	private GtnWsSqlService gtnWsSqlService;
	public GtnWsGeneralController getGtnGeneralServiceController() {
		return gtnGeneralServiceController;
	}

	public void setGtnGeneralServiceController(GtnWsGeneralController gtnGeneralServiceController) {
		this.gtnGeneralServiceController = gtnGeneralServiceController;
	}

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	public org.hibernate.SessionFactory getSysSessionFactory() {
		return sysSessionFactory;
	}

	public void setSysSessionFactory(org.hibernate.SessionFactory sysSessionFactory) {
		this.sysSessionFactory = sysSessionFactory;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getPublicViewSearchLookUp", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getPublicViewSearchLookUp(
			@RequestBody GtnUIFrameworkWebserviceRequest returnsPublicViewRequest) {
		GtnUIFrameworkWebserviceResponse returnsPublicViewResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnSerachResponse returnsPublicViewSerachResponse = new GtnSerachResponse();

			String queryName = returnsPublicViewRequest.getGtnWsSearchRequest().isCount()
					? "getCountForecastingReturnsPublicView" : "getDataForecastingReturnsPublicView";

			List<Object[]> result = gtnGeneralServiceController.executeQuery(gtnWsSqlService.getQuery(getPublicLookUpInput(returnsPublicViewRequest), queryName));

			if (returnsPublicViewRequest.getGtnWsSearchRequest().isCount()) {
				returnsPublicViewSerachResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable returnsPublicViewDataTable = new GtnUIFrameworkDataTable();
				returnsPublicViewDataTable.addData(result);
				returnsPublicViewSerachResponse.setResultSet(returnsPublicViewDataTable);
			}
			returnsPublicViewResponse.setGtnSerachResponse(returnsPublicViewSerachResponse);
		} catch (GtnFrameworkGeneralException ex) {

			LOGGER.error(ex.getMessage());
			returnsPublicViewResponse.getGtnWsGeneralResponse().setSucess(false);
			returnsPublicViewResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return returnsPublicViewResponse;
	}

	private List<Object> getPublicLookUpInput(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		return privatePublicView(gtnWsRequest, false);
	}

	private List<Object> getPublicViewSortedInputs(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> list = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			list.add(publicViewSortedInputs().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			list.add(publicViewSortedInputs().get("sid") + " ASC");
		}
		return list;
	}

	private Map<String, String> publicViewSortedInputs() {

		Map<String, String> sortedMap = new HashMap<>();
		sortedMap.put("sid", "PM.PROJECTION_MASTER_SID");
		sortedMap.put(GtnFrameworkWebserviceConstant.VIEW_NAME_FILTER, "FVM.VIEW_NAME");
		sortedMap.put(GtnFrameworkWebserviceConstant.VIEW_DESCRIPTION_FILTER, "PM.PROJECTION_DESCRIPTION");
		sortedMap.put(GtnFrameworkWebserviceConstant.FROM_TIME_PERIOD_FILTER,
				"concat( 'Q', datepart( qq, pm.FROM_DATE ), '-', year( pm.FROM_DATE ))");
		sortedMap.put(GtnFrameworkWebserviceConstant.TO_TIME_PERIOD_FILTER,
				"concat( 'Q', datepart( qq,PM.TO_DATE ), '-', year( PM.TO_DATE ))");
		sortedMap.put(GtnFrameworkWebserviceConstant.COMPANY_FILTER, "CM.COMPANY_NAME");
		sortedMap.put(GtnFrameworkWebserviceConstant.PRODUCT_HIERARCHY_FILTER, "PROD_HD.HIERARCHY_NAME");
		sortedMap.put(GtnFrameworkWebserviceConstant.PRODUCT_LEVEL_FILTER, "PM.PRODUCT_HIERARCHY_LEVEL");
		sortedMap.put(GtnFrameworkWebserviceConstant.PRODUCT_GROUP_FILTER,
				GtnFrameworkWebserviceConstant.IGITEM_GROUP_NAME);
		sortedMap.put(GtnFrameworkWebserviceConstant.CREATED_DATE_FILTER, GtnFrameworkCommonConstants.PM_CREATED_DATE);
		sortedMap.put(GtnFrameworkWebserviceConstant.MODIFIED_BY_FILTER, GtnFrameworkCommonConstants.PM_MODIFIED_DATE);
		sortedMap.put(GtnFrameworkWebserviceConstant.CREATED_BY_FILTER, "CONCAT(usr.LastName ,', ',usr.firstName)");
		sortedMap.put(GtnFrameworkWebserviceConstant.BUSINESS_UNIT_FILTER, "CM1.COMPANY_NAME");

		return sortedMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getPrivateViewSearchLookUp", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getPrivateViewSearchLookUp(
			@RequestBody GtnUIFrameworkWebserviceRequest privateViewSearchRequest) {
		GtnUIFrameworkWebserviceResponse privateViewResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnSerachResponse privateViewSearchResponse = new GtnSerachResponse();

			String queryName = privateViewSearchRequest.getGtnWsSearchRequest().isCount()
					? "getCountForecastingReturnsPrivateView" : "getDataForecastingReturnsPrivateView";

			List<Object[]> result = gtnGeneralServiceController.executeQuery(gtnWsSqlService.getQuery(getPrivateLookUpInput(privateViewSearchRequest), queryName));

			if (privateViewSearchRequest.getGtnWsSearchRequest().isCount()) {
				privateViewSearchResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable privateViewSearchDataTable = new GtnUIFrameworkDataTable();
				privateViewSearchDataTable.addData(result);
				privateViewSearchResponse.setResultSet(privateViewSearchDataTable);
			}
			privateViewResponse.setGtnSerachResponse(privateViewSearchResponse);
		} catch (GtnFrameworkGeneralException ex) {
			LOGGER.error(ex.getMessage());
			privateViewResponse.getGtnWsGeneralResponse().setSucess(false);
			privateViewResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return privateViewResponse;
	}

	private List<Object> getPrivateLookUpInput(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		return privatePublicView(gtnWsRequest, true);
	}

	private List<Object> getPrivateViewSortedInputs(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> list = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			list.add(publicViewSortedInputs().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			list.add(publicViewSortedInputs().get("sid") + " ASC");
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getHierarchyLookUp", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getHierarchyLookUp(
			@RequestBody GtnUIFrameworkWebserviceRequest productHierarchyRequest) {
		GtnUIFrameworkWebserviceResponse productHierarchyResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnSerachResponse productHierarchySerachResponse = new GtnSerachResponse();
			String queryName = productHierarchyRequest.getGtnWsSearchRequest().isCount() ? "getCountProductHierarchy"
					: "getDataProductHierarchy";
			List<Object[]> result = gtnGeneralServiceController.executeQuery(gtnWsSqlService.getQuery(getLookUpInput(productHierarchyRequest), queryName));
			if (productHierarchyRequest.getGtnWsSearchRequest().isCount()) {
				productHierarchySerachResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable productHierarchyDataTable = new GtnUIFrameworkDataTable();
				productHierarchyDataTable.addData(result);
				productHierarchySerachResponse.setResultSet(productHierarchyDataTable);
			}
			productHierarchyResponse.setGtnSerachResponse(productHierarchySerachResponse);
		} catch (GtnFrameworkGeneralException ex) {
			productHierarchyResponse.getGtnWsGeneralResponse().setSucess(false);
			productHierarchyResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return productHierarchyResponse;
	}

	private List<Object> getLookUpInput(GtnUIFrameworkWebserviceRequest highestLookupRequest) throws ParseException {
	
		List<Object> list = new ArrayList<>();
                try {
		String hierarchyLookupName = "%";
		String hierarchyLookupType = "%";
		String hierarchyLookupNameListVie = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String highestLookupLevel = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String highestLookupLowestLevel = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String highestLookupCreatedDate = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String highestLookupModifiedDate = GtnFrameworkCommonStringConstants.STRING_EMPTY;

		for (GtnWebServiceSearchCriteria searchCriteria : highestLookupRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				String expressionString = searchCriteria.getExpression().contains("LESS") ? "<" : "=";

				String exp = searchCriteria.getExpression().contains(GtnFrameworkWebserviceConstant.GREATER) ? ">"
						: expressionString;

				switch (searchCriteria.getFieldId()) {
				case "hierarchyType":
					hierarchyLookupType = searchCriteria.getFilterValue1().replace('*', '%');
					break;
				case "hierarchyName":
					hierarchyLookupName = searchCriteria.getFilterValue1().replace('*', '%');
					break;
				case "hierName":
					hierarchyLookupNameListVie = "AND c.HIERARCHY_NAME like '%" + searchCriteria.getFilterValue1()
							+ "%'";
					break;
				case "highestLevel":
					highestLookupLevel = "AND A.LEVEL_NO " + exp + " " + searchCriteria.getFilterValue1();
					break;
				case "lowestLevel":
					highestLookupLowestLevel = "AND B.LEVEL_NO " + exp + " " + searchCriteria.getFilterValue1();
					break;
				case "createdDate":
					getDateFilter(searchCriteria, "c.CREATED_DATE");
					break;
				case "modifiedDate":
					getDateFilter(searchCriteria, "c.MODIFIED_DATE");
					break;
				default:
					break;
				}
			}
		}
		list.add(hierarchyLookupName);
		list.add(hierarchyLookupType);
		list.add(hierarchyLookupNameListVie);
		list.add(highestLookupLevel);
		list.add(highestLookupLowestLevel);
		list.add(highestLookupCreatedDate);
		list.add(highestLookupModifiedDate);
		if (!highestLookupRequest.getGtnWsSearchRequest().isCount()) {
			list.addAll(getSortedInputs(
					highestLookupRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
			list.add(highestLookupRequest.getGtnWsSearchRequest().getTableRecordStart());
			list.add(highestLookupRequest.getGtnWsSearchRequest().getTableRecordOffset());
		}
                } catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return list;
	}

	String getDateFilter(GtnWebServiceSearchCriteria searchCriteria, String column) throws ParseException {
		if (searchCriteria.getFilterValue2() != null && !searchCriteria.getFilterValue2().isEmpty()) {
			return "AND " + column + " BETWEEN '" + getDate(searchCriteria.getFilterValue1())
					+ GtnFrameworkWebserviceConstant.AND + getDate(searchCriteria.getFilterValue2()) + "'";
		}
		return GtnFrameworkCommonStringConstants.STRING_EMPTY;
	}

	public String getDate(String input) throws ParseException {

		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = formatter.parse(input);
		SimpleDateFormat commonDate = new SimpleDateFormat("MM/dd/yyyy");
		return commonDate.format(date);
	}

	private List<Object> getSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> list = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			list.add(sortedMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			list.add(sortedMap().get("sid") + " ASC");
		}
		return list;
	}

	private Map<String, String> sortedMap() {
		Map<String, String> sortedMap = new HashMap<>();
		sortedMap.put("sid", "c.HIERARCHY_DEFINITION_SID");
		sortedMap.put("hierName", "c.HIERARCHY_NAME");
		sortedMap.put("highestLevel", "A.LEVEL_NO");
		sortedMap.put("lowestLevel", "B.LEVEL_NO");
		sortedMap.put("createdDate", "c.CREATED_DATE");
		sortedMap.put("modifiedDate", "c.MODIFIED_DATE");
		return sortedMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "getProductGroupLookUp", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getProductGroupLookUp(
			@RequestBody GtnUIFrameworkWebserviceRequest returnProductGroupRequest) {
		GtnUIFrameworkWebserviceResponse returnProductGroupResponse = new GtnUIFrameworkWebserviceResponse();
		try {
			GtnSerachResponse returnProductGroupSerachResponse = new GtnSerachResponse();
			String queryName = returnProductGroupRequest.getGtnWsSearchRequest().isCount() ? "getCountProdGroupLookUp"
					: "getDataProdGroupLookUp";

			List<Object[]> returnProductGroupResult = gtnGeneralServiceController
					.executeQuery(gtnWsSqlService.getQuery(getProductGroupLookUpInput(returnProductGroupRequest), queryName));
			if (returnProductGroupRequest.getGtnWsSearchRequest().isCount()) {
				returnProductGroupSerachResponse
						.setCount(Integer.parseInt(String.valueOf(returnProductGroupResult.get(0))));
			} else {
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(returnProductGroupResult);
				returnProductGroupSerachResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			returnProductGroupResponse.setGtnSerachResponse(returnProductGroupSerachResponse);
		} catch (GtnFrameworkGeneralException ex) {
			returnProductGroupResponse.getGtnWsGeneralResponse().setSucess(false);
			returnProductGroupResponse.getGtnWsGeneralResponse().setGtnGeneralException(ex);
		} catch (Exception ex) {
			LOGGER.error("Error in Return forecast Product Group LookUp", ex);
		}
		return returnProductGroupResponse;

	}

	private List<Object> getProductGroupLookUpInput(GtnUIFrameworkWebserviceRequest gtnWsRequest) {

		List<Object> list = new ArrayList<>();
		String returnsProductGroupName = "%";
		String returnsProductGroupNo = "%";
		String returnsProductGroupNameFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String returnsProductGroupNoFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String returnsCompanyNameFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;
		String returnsProductGroupDescFilterView = GtnFrameworkCommonStringConstants.STRING_EMPTY;

		for (GtnWebServiceSearchCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
				.getGtnWebServiceSearchCriteriaList()) {
			if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
				switch (searchCriteria.getFieldId()) {
				case "productGroupName":
					returnsProductGroupName = searchCriteria.getFilterValue1().replace('*', '%');
					break;
				case "productGroupNo":
					returnsProductGroupNo = searchCriteria.getFilterValue1().replace('*', '%');
					break;
				case "productGroupNameFilterView":
					returnsProductGroupNameFilterView = "AND (IG.ITEM_GROUP_NAME LIKE '%"
							+ searchCriteria.getFilterValue1() + "%')";
					break;
				case "productGroupNoFilterView":
					returnsProductGroupNoFilterView = "AND (IG.ITEM_GROUP_NO LIKE '%" + searchCriteria.getFilterValue1()
							+ "%' OR IG.ITEM_GROUP_NO IS NULL )";
					break;
				case "companyNameFilterView":
					returnsCompanyNameFilterView = "AND (CM.COMPANY_NAME  LIKE '%" + searchCriteria.getFilterValue1()
							+ "%' OR CM.COMPANY_NAME IS NULL )";
					break;
				case "productGroupDescFilterView":
					returnsCompanyNameFilterView = "AND (IG.ITEM_GROUP_DESCRIPTION  LIKE '%"
							+ searchCriteria.getFilterValue1() + "%' OR IG.ITEM_GROUP_DESCRIPTION IS NULL )";
					break;
				default:
					break;
				}
			}
		}
		list.add(returnsProductGroupName);
		list.add(returnsProductGroupNo);
		list.add(returnsProductGroupNameFilterView);
		list.add(returnsProductGroupNoFilterView);
		list.add(returnsCompanyNameFilterView);
		list.add(returnsProductGroupDescFilterView);

		if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			list.addAll(getProductGroupSortedInputs(
					gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
			list.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
			list.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
		}
		return list;
	}

	private List<Object> getProductGroupSortedInputs(
			List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> list = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			list.add(sortedProductGroupMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			list.add(sortedProductGroupMap().get("sid") + " ASC");
		}
		return list;
	}

	private Map<String, String> sortedProductGroupMap() {

		Map<String, String> sortedProductGroupMap = new HashMap<>();
		sortedProductGroupMap.put("sid", "IG.ITEM_GROUP_SID");
		sortedProductGroupMap.put("productGroupNameFilterView", GtnFrameworkWebserviceConstant.IGITEM_GROUP_NAME);
		sortedProductGroupMap.put("productGroupNoFilterView", "IG.ITEM_GROUP_NO");
		sortedProductGroupMap.put("companyNameFilterView", " COMPANY_NAME");
		sortedProductGroupMap.put("productGroupDescFilterView", "IG.ITEM_GROUP_DESCRIPTION");
		return sortedProductGroupMap;

	}

	@RequestMapping(value = "/getReturnsForecastViewDetailsService", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse getReturnsForecastViewDetailsService(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		LOGGER.info("Enter details *****");

		GtnForecastBean gtnForecastBean = gtnWsRequest.getGtnWsForecastRequest().getGtnForecastBean();

		LOGGER.info("Exit ********* ");
		return configureResponseForCompanyMaster(gtnForecastBean);
	}

	private GtnUIFrameworkWebserviceResponse configureResponseForCompanyMaster(GtnForecastBean gtnForecastBean) {

		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsForecastResponse gtnWsForecastResponse = new GtnWsForecastResponse();
		GtnWsGeneralResponse generalWSResponse = new GtnWsGeneralResponse();

		try {

			GtnForecastBean gtnForecastBeanInfo = getPublicPrivateInfoDetails(gtnForecastBean);
			if (gtnForecastBeanInfo != null) {
				gtnForecastBeanInfo.setCompanyId(gtnForecastBeanInfo.getCompanyId());
				gtnForecastBeanInfo.setBusinessUnitId(gtnForecastBeanInfo.getBusinessUnitId());
				gtnForecastBeanInfo.setProjectionName(gtnForecastBeanInfo.getProjectionName());
				gtnForecastBeanInfo.setProjectionDescription(gtnForecastBeanInfo.getProjectionDescription());
				gtnForecastBeanInfo.setFromPeriod(gtnForecastBeanInfo.getFromPeriod());
				gtnForecastBeanInfo.setToPeriod(gtnForecastBeanInfo.getToPeriod());
				gtnForecastBeanInfo.setProductHirerachy(gtnForecastBeanInfo.getProductHirerachy());
				gtnForecastBeanInfo.setProductRelationship(gtnForecastBeanInfo.getProductRelationship());
				gtnForecastBeanInfo.setProductForecastLevel(gtnForecastBeanInfo.getProductForecastLevel());
				gtnForecastBeanInfo.setProductGroup(gtnForecastBeanInfo.getProductGroup());
				gtnForecastBeanInfo.setProductInnerLevel(gtnForecastBeanInfo.getProductInnerLevel());
				gtnForecastBeanInfo.setViewName(gtnForecastBeanInfo.getViewName());
				gtnForecastBeanInfo.setViewCreatedBy(gtnForecastBeanInfo.getViewCreatedBy());
				gtnForecastBeanInfo.setProjectionMasterSid(gtnForecastBeanInfo.getProjectionMasterSid());
			}
			gtnWsForecastResponse.setGtnForecastBean(gtnForecastBeanInfo);
			generalWSResponse.setSucess(true);

		} catch (GtnFrameworkGeneralException exception) {
			LOGGER.error("Exception in getting data -", exception);
			generalWSResponse.setSucess(false);
			generalWSResponse.setGtnGeneralException(exception);
		}

		gtnResponse.setGtnWsGeneralResponse(generalWSResponse);
		gtnResponse.setGtnWsForecastResponse(gtnWsForecastResponse);
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	private GtnForecastBean getPublicPrivateInfoDetails(GtnForecastBean gtnForecastBean)
			throws GtnFrameworkGeneralException {

		try {
			LOGGER.info("Enter *************");

			List<Object> list = new ArrayList<>();
			list.add(gtnForecastBean.getViewId());

			List<Object[]> resultList = gtnGeneralServiceController.executeQuery(gtnWsSqlService.getQuery(list, "getPublicPrivateInput"));

			return setPublicPrivateViewInfoBean(resultList);
		} catch (Exception e) {
			LOGGER.error("Error in Return forecast Info Details", e);
		}
		return gtnForecastBean;
	}

	private GtnForecastBean setPublicPrivateViewInfoBean(final List<Object[]> viewResultList) {
		GtnForecastBean returnPublicPrivateForecastViewBean = null;
		if (viewResultList != null && !viewResultList.isEmpty()) {
			int viewResultListSize = viewResultList.size();
			for (int i = 0; i < viewResultListSize; i++) {
				returnPublicPrivateForecastViewBean = new GtnForecastBean();
				final Object[] obj = viewResultList.get(i);
				returnPublicPrivateForecastViewBean.setCompanyId((int) obj[0]);
				returnPublicPrivateForecastViewBean.setBusinessUnitId((int) obj[1]);
				returnPublicPrivateForecastViewBean.setProjectionName(String.valueOf(obj[2]));
				returnPublicPrivateForecastViewBean.setProjectionDescription(String.valueOf(obj[3]));
				returnPublicPrivateForecastViewBean.setFromPeriod((short) obj[4]);
				returnPublicPrivateForecastViewBean.setToPeriod((short) obj[5]);
				returnPublicPrivateForecastViewBean.setProductHirerachy(String.valueOf(obj[6]));
				returnPublicPrivateForecastViewBean.setProductRelationship((int) obj[7]);
				returnPublicPrivateForecastViewBean.setProductForecastLevel((short) obj[8]);
				returnPublicPrivateForecastViewBean.setProductGroup(String.valueOf(obj[9]));
				returnPublicPrivateForecastViewBean.setProductInnerLevel((short) obj[10]);
				returnPublicPrivateForecastViewBean.setViewName(String.valueOf(obj[11]));
				returnPublicPrivateForecastViewBean.setViewCreatedBy(Integer.parseInt(String.valueOf(obj[12])));
				returnPublicPrivateForecastViewBean.setProjectionMasterSid(String.valueOf(obj[13]));
			}
		}
		return returnPublicPrivateForecastViewBean;
	}

	private List<Object> privatePublicView(GtnUIFrameworkWebserviceRequest gtnWsRequest, boolean viewMode)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		String userId = gtnWsRequest.getGtnWsGeneralRequest().getUserId();
		String viewType = gtnWsRequest.getGtnWsSearchRequest().getSearchQueryName();
		String forecastType = gtnWsRequest.getGtnWsSearchRequest().getSearchModuleName();
		Map<String, String> criteriaMap = new HashMap<>();
		try (Connection connection = sysSessionFactory.getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection();) {
			list.add(connection.getCatalog());

			list.add(viewType);
			list.add(forecastType);

			for (GtnWebServiceSearchCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
					.getGtnWebServiceSearchCriteriaList()) {
				if (searchCriteria.getFilterValue1() != null && !searchCriteria.getFilterValue1().isEmpty()) {
					criteriaMap.put(searchCriteria.getFieldId(), getCriteria(searchCriteria));
				}
			}

			if (viewMode) {
				list.add(userId);
				String viewNameCritera = criteriaMap.get("privateViewName");
				list.add(viewNameCritera == null ? "%" : viewNameCritera);
			} else {
				String viewNameCritera = criteriaMap.get("viewName");
				list.add(viewNameCritera == null ? "%" : viewNameCritera);
			}
			addCriteria(list, criteriaMap);
			if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				if (viewMode) {
					list.addAll(getPrivateViewSortedInputs(
							gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
				} else {
					list.addAll(getPublicViewSortedInputs(
							gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
				}
				list.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
				list.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in executig query-", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);

		}
		return list;
	}

	private void addCriteria(List<Object> list, Map<String, String> criteriaMap) {
		String viewNameFilter = criteriaMap.get(GtnFrameworkWebserviceConstant.VIEW_NAME_FILTER);
		list.add(viewNameFilter == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : viewNameFilter);
		String viewDescriptionFilter = criteriaMap.get(GtnFrameworkWebserviceConstant.VIEW_DESCRIPTION_FILTER);
		list.add(
				viewDescriptionFilter == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : viewDescriptionFilter);
		String fromTimePeriodFilter = criteriaMap.get(GtnFrameworkWebserviceConstant.FROM_TIME_PERIOD_FILTER);
		list.add(fromTimePeriodFilter == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : fromTimePeriodFilter);
		String toTimePeriodFilter = criteriaMap.get(GtnFrameworkWebserviceConstant.TO_TIME_PERIOD_FILTER);
		list.add(toTimePeriodFilter == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : toTimePeriodFilter);
		String companyFilter = criteriaMap.get(GtnFrameworkWebserviceConstant.COMPANY_FILTER);
		list.add(companyFilter == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : companyFilter);
		String productHierarchyFilter = criteriaMap.get(GtnFrameworkWebserviceConstant.PRODUCT_HIERARCHY_FILTER);
		list.add(productHierarchyFilter == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY
				: productHierarchyFilter);
		String productLevelFilter = criteriaMap.get(GtnFrameworkWebserviceConstant.PRODUCT_LEVEL_FILTER);
		list.add(productLevelFilter == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : productLevelFilter);
		String productGroupFilter = criteriaMap.get(GtnFrameworkWebserviceConstant.PRODUCT_GROUP_FILTER);
		list.add(productGroupFilter == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : productGroupFilter);
		String createdDateFilter = criteriaMap.get(GtnFrameworkWebserviceConstant.CREATED_DATE_FILTER);
		list.add(createdDateFilter == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : createdDateFilter);
		String modifiedByFilter = criteriaMap.get(GtnFrameworkWebserviceConstant.MODIFIED_BY_FILTER);
		list.add(modifiedByFilter == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : modifiedByFilter);
		String createdByFilter = criteriaMap.get(GtnFrameworkWebserviceConstant.CREATED_BY_FILTER);
		list.add(createdByFilter == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : createdByFilter);
		String businessUnitFilter = criteriaMap.get(GtnFrameworkWebserviceConstant.BUSINESS_UNIT_FILTER);
		list.add(businessUnitFilter == null ? GtnFrameworkCommonStringConstants.STRING_EMPTY : businessUnitFilter);
	}

	private String getCriteria(GtnWebServiceSearchCriteria searchCriteria) throws ParseException {

		String expressionString = searchCriteria.getExpression().contains("LESS") ? "<" : "=";
		String exp = searchCriteria.getExpression().contains(GtnFrameworkWebserviceConstant.GREATER) ? ">"
				: expressionString;
		switch (searchCriteria.getFieldId()) {
		case "privateViewName":
			return searchCriteria.getFilterValue1().replace('*', '%');
		case "viewName":
			return searchCriteria.getFilterValue1().replace('*', '%').trim();
		case GtnFrameworkWebserviceConstant.VIEW_NAME_FILTER:
			return "AND FVM.VIEW_NAME like '%" + searchCriteria.getFilterValue1() + "%'";
		case GtnFrameworkWebserviceConstant.VIEW_DESCRIPTION_FILTER:
			return "AND PM.PROJECTION_DESCRIPTION like '%" + searchCriteria.getFilterValue1() + "%'";
		case GtnFrameworkWebserviceConstant.FROM_TIME_PERIOD_FILTER:
			return "AND  concat( 'Q', datepart( qq, pm.FROM_DATE ), '-', year( pm.FROM_DATE )) like '%"
					+ searchCriteria.getFilterValue1() + "%'";
		case GtnFrameworkWebserviceConstant.TO_TIME_PERIOD_FILTER:
			return "AND concat( 'Q', datepart( qq, PM.TO_DATE ), '-', year( PM.TO_DATE )) like '%"
					+ searchCriteria.getFilterValue1() + "%'";
		case GtnFrameworkWebserviceConstant.COMPANY_FILTER:
			return "AND  CM.COMPANY_NAME like '%" + searchCriteria.getFilterValue1() + "%'";
		case GtnFrameworkWebserviceConstant.PRODUCT_HIERARCHY_FILTER:
			return "AND PROD_HD.HIERARCHY_NAME like '%" + searchCriteria.getFilterValue1() + "%'";
		case GtnFrameworkWebserviceConstant.PRODUCT_LEVEL_FILTER:
			return "AND PM.PRODUCT_HIERARCHY_LEVEL " + exp + " " + searchCriteria.getFilterValue1();
		case GtnFrameworkWebserviceConstant.PRODUCT_GROUP_FILTER:
			return "AND IG.ITEM_GROUP_NAME like '%" + searchCriteria.getFilterValue1() + "%'";
		case GtnFrameworkWebserviceConstant.CREATED_DATE_FILTER:
			return getDateFilter(searchCriteria, GtnFrameworkCommonConstants.PM_CREATED_DATE);
		case GtnFrameworkWebserviceConstant.MODIFIED_BY_FILTER:
			return getDateFilter(searchCriteria, GtnFrameworkCommonConstants.PM_MODIFIED_DATE);
		case GtnFrameworkWebserviceConstant.CREATED_BY_FILTER:
			return "AND CONCAT(usr.LastName ,', ',  usr.firstName) like '%" + searchCriteria.getFilterValue1() + "%'";
		case GtnFrameworkWebserviceConstant.BUSINESS_UNIT_FILTER:
			return "AND CM1.COMPANY_NAME like '%" + searchCriteria.getFilterValue1() + "%'";
		default:
			return null;
		}
	}

}
