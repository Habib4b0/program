/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.periodconfiguration.controller;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.periodconfiguration.PeriodConfigDetails;
import com.stpl.gtn.gtn2o.ws.entity.periodconfiguration.PeriodConfigMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.filemanagement.constants.GtnWsFileManagementConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.periodconfig.constants.GtnWsPeriodConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.periodconfig.GtnWsPeriodConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.stpl.gtn.gtn2o.ws.response.periodconfig.GtnWsPeriodConfigurationResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

@RestController
@RequestMapping(value = GtnWsPeriodConfigurationConstants.PERIOD_CONFIG_ONSCREEN_LOAD_SAVE_COUNT)
public class GtnWsPeriodConfigurationController {
	private GtnWsPeriodConfigurationController() {
		/**
		 * empty constructor
		 */
	}

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	private GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	private org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private org.hibernate.SessionFactory getSysSessionFactory() {
		return sysSessionFactory;
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsPeriodConfigurationController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWsPeriodConfigurationConstants.PERIOD_CONFIG_ONSCREEN_LOAD_COUNT, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse periodConfigLoad(
			@RequestBody GtnUIFrameworkWebserviceRequest periodConfigRequest) {
		GtnUIFrameworkWebserviceResponse periodConfigResponse = new GtnUIFrameworkWebserviceResponse();

		GtnWsGeneralResponse periodConfiLoadResponse = new GtnWsGeneralResponse();
		try {

			GtnSerachResponse periodConfigSerachResponse = new GtnSerachResponse();
			String queryName = periodConfigRequest.getGtnWsSearchRequest().isCount() ? "getPeriodConfigurationCount"
					: "getPeriodConfigurationResults";
			List<Object> inputlist = getSearchInput(periodConfigRequest);

			List<Object[]> result = executeQuery(getQuery(inputlist, queryName));
			if (periodConfigRequest.getGtnWsSearchRequest().isCount()) {
				periodConfigSerachResponse.setCount(Integer.parseInt(String.valueOf(result.get(0))));
			} else {
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(result);
				periodConfigSerachResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			periodConfigResponse.setGtnSerachResponse(periodConfigSerachResponse);
			periodConfiLoadResponse.setSucess(true);
		} catch (GtnFrameworkGeneralException ex) {
			periodConfiLoadResponse.setSucess(false);
			periodConfiLoadResponse.setGtnGeneralException(ex);
		}
		periodConfigResponse.setGtnWsGeneralResponse(periodConfiLoadResponse);
		return periodConfigResponse;
	}

	@SuppressWarnings("rawtypes")
	private String getQuery(List input, String queryName) {
		StringBuilder sql = new StringBuilder();
		try {

			sql = new StringBuilder(getQuery(queryName));
			if (input != null) {
				for (Object temp : input) {
					sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
				}
			}
		} catch (Exception ex) {
			logger.error("Error in forming Query", ex);
		}
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = GtnWsPeriodConfigurationConstants.PERIOD_CONFIG_ONSCREEN_LOAD_SAVE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse savePeriodConfig(@RequestBody GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException, ParseException {

		GtnWsPeriodConfigurationRequest gtnRequest = gtnWsRequest.getGtnWsPeriodConfigurationRequest();
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();

		/*
		 * To Check whether the data is already present in periodConfig Master table
		 */

		int sysId = 0;

		List<Object> selectData = (List<Object>) getPeriodConfigMaster(gtnRequest);

		if (selectData.isEmpty()) {

			sysId = savePeriodConfigMaster(gtnRequest);

		} else {
			sysId = (int) ((selectData).get(0));
		}

		savePeriodConfigDetailsData(gtnRequest, sysId);

		gtnResponse.setGtnWsPeriodConfigurationResponse(new GtnWsPeriodConfigurationResponse());
		gtnResponse.getGtnWsPeriodConfigurationResponse().setSuccess(true);
		gtnResponse.getGtnWsPeriodConfigurationResponse().setMessage("");

		return gtnResponse;
	}

	private boolean isValidString(String sourceString) {

		return sourceString != null && !sourceString.isEmpty();
	}

	private void savePeriodConfigDetailsData(GtnWsPeriodConfigurationRequest gtnRequest, int sysId)
			throws ParseException, GtnFrameworkGeneralException {
		DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		try (Session session = getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();

			PeriodConfigDetails configDetails = new PeriodConfigDetails();

			configDetails.setPeriodConfigMaster(getPeriodConfig(sysId, session));
			configDetails.setHelperTableByFromMode(getHelperTable(gtnRequest.getModeFrom(), session));
			configDetails.setHelperTableByFromFrequency(getHelperTable(gtnRequest.getFrequencyFrom(), session));
			configDetails
					.setFromPeriod(isValidString(gtnRequest.getPeriodFromTextBox()) ? gtnRequest.getPeriodFromTextBox()
							: dateFormatter.format(gtnRequest.getPeriodFrom()));
			configDetails.setFromPeriodDate(dateFormatter.parse(gtnRequest.getDateFrom()));

			configDetails.setHelperTableByFromDefaultMode(getHelperTable(gtnRequest.getDefaultModeFrom(), session));
			configDetails.setHelperTableByFromDefaultFrequerncy(
					getHelperTable(gtnRequest.getDefaultFrequencyFrom(), session));
			configDetails.setFromDefaultPeriod(
					isValidString(gtnRequest.getDefaultPeriodFromTextBox()) ? gtnRequest.getDefaultPeriodFromTextBox()
							: dateFormatter.format(gtnRequest.getDefaultPeriodFrom()));
			configDetails.setFromDefaultPeriodDate(dateFormatter.parse(gtnRequest.getDefaultDateFrom()));

			if (!gtnRequest.getPeriodView().equalsIgnoreCase("Single")) {
				configDetails.setHelperTableByToMode(getHelperTable(gtnRequest.getModeTo(), session));
				configDetails.setHelperTableByToFrequency(getHelperTable(gtnRequest.getFrequencyTo(), session));
				configDetails.setHelperTableByToDefaultMode(getHelperTable(gtnRequest.getDefaultModeTo(), session));
				configDetails.setHelperTableByToDefaultFrequerncy(
						getHelperTable(gtnRequest.getDefaultFrequencyTo(), session));
				configDetails.setToDefaultPeriod(
						isValidString(gtnRequest.getDefaultPeriodToTextBox()) ? gtnRequest.getDefaultPeriodToTextBox()
								: dateFormatter.format(gtnRequest.getDefaultPeriodTo()));
				configDetails.setToDefualtPeriodDate(dateFormatter.parse(gtnRequest.getDefaultDateTo()));
				configDetails
						.setToPeriod(isValidString(gtnRequest.getPeriodToTextBox()) ? gtnRequest.getPeriodToTextBox()
								: dateFormatter.format(gtnRequest.getPeriodTo()));
				configDetails.setToPeriodDate(dateFormatter.parse(gtnRequest.getDateTo()));
			}
			configDetails.setHelperTableByPeriodView(getHelperTable(
					getPeriodViewMap().get(gtnRequest.getPeriodView().toLowerCase(Locale.ENGLISH)), session));
			configDetails.setVersionNo(getVersionNo(sysId, session) + 1);
			configDetails.setCreatedBy(gtnRequest.getUserId());
			try {
				session.saveOrUpdate(configDetails);
				tx.commit();
			} catch (Exception e) {
				logger.error("Sorry, The Update failed due to ", e);
				tx.rollback();
			}
		}
	}

	private Integer savePeriodConfigMaster(GtnWsPeriodConfigurationRequest gtnRequest) {
		try (Session session = getSessionFactory().openSession()) {
			CompanyMaster company = session.load(CompanyMaster.class, gtnRequest.getCompany());
			CompanyMaster buCompany = session.load(CompanyMaster.class, gtnRequest.getBusinessUnit());
			HelperTable moduleType = session.load(HelperTable.class, gtnRequest.getModule());

			PeriodConfigMaster pcMaster = new PeriodConfigMaster(company, buCompany, moduleType,
					gtnRequest.getBusinessProcess());

			Transaction transaction = session.beginTransaction();

			try {
				session.save(pcMaster);
				transaction.commit();

			} catch (Exception e) {
				transaction.rollback();
			}
			return pcMaster.getPeriodConfigMasterSid();
		}
	}

	private List<?> getPeriodConfigMaster(GtnWsPeriodConfigurationRequest gtnRequest)
			throws GtnFrameworkGeneralException {

		try (Session session = getSessionFactory().openSession();) {
			ProjectionList selectList = Projections.projectionList();
			selectList.add(Projections.property("periodConfigMasterSid"));
			Criterion companyMasterSid = Restrictions.eq("companyMasterByCompanyMasterSid.companyMasterSid",
					gtnRequest.getCompany());
			Criterion companyBuMasterSid = Restrictions.eq("companyMasterByBuCompanyMasterSid.companyMasterSid",
					gtnRequest.getBusinessUnit());
			Criterion processType = Restrictions.eq("businessProcessType", gtnRequest.getBusinessProcess());
			Criterion companyModule = Restrictions.eq("helperTable.helperTableSid", gtnRequest.getModule());

			return gtnSqlQueryEngine.executeSelectQuery(PeriodConfigMaster.class,
					Arrays.asList(companyMasterSid, companyBuMasterSid, processType, companyModule), selectList,
					session);
		}

	}

	private PeriodConfigMaster getPeriodConfig(int systemId, Session session) {
		return session.load(PeriodConfigMaster.class, systemId);
	}

	@SuppressWarnings("unchecked")
	private int getVersionNo(Integer systemId, Session session) throws GtnFrameworkGeneralException {

		int versionNo = 0;
		Criterion masterId = Restrictions.eq("periodConfigMaster", getPeriodConfig(systemId, session));

		List<Object> resultList = (List<Object>) gtnSqlQueryEngine.executeSelectQuery(PeriodConfigDetails.class,
				Arrays.asList(masterId), Projections.max(GtnWsFileManagementConstants.VERSION_NO), session);
		if (!resultList.isEmpty()) {
			versionNo = resultList.get(0) == null ? 0 : (int) resultList.get(0);
		}
		return versionNo;
	}

	private Map<String, Integer> getPeriodViewMap() {
		Map<String, Integer> idValueMap = new HashMap<>();

		try (Session session = getSessionFactory().openSession()) {
			Criteria selectQuery = session.createCriteria(HelperTable.class);
			ProjectionList selectList = Projections.projectionList();
			selectList.add(Projections.property("description"));
			selectList.add(Projections.property("helperTableSid"));
			selectQuery.add(Restrictions.eq("listName", "PERIODCONFIG_VIEW"));
			selectQuery.setProjection(selectList);
			List<?> resultList = selectQuery.list();
			if (resultList != null) {
				for (int i = 0; i < resultList.size(); i++) {
					Object[] obj = (Object[]) resultList.get(i);
					idValueMap.put(String.valueOf(obj[0]).toLowerCase(Locale.ENGLISH),
							Integer.valueOf(String.valueOf(obj[1])));
				}
			}
			return idValueMap;
		}

	}

	private HelperTable getHelperTable(int sID, Session session) {
		return session.load(HelperTable.class, sID);
	}

	private GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	@SuppressWarnings("rawtypes")
	private List executeQuery(String sqlQuery) throws GtnFrameworkGeneralException {
		return getGtnSqlQueryEngine().executeSelectQuery(sqlQuery);
	}

	private String getQuery(String sqlId) {
		return getGtnWsSqlService().getQuery(sqlId);
	}

	private List<Object> getSearchInput(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		List<Object> periodConfigurationInputList = new ArrayList<>();

		StringBuilder inputWhereConditions = new StringBuilder("");
		String and = "AND ";
		String where = " ";
		try {
			for (GtnWebServiceSearchCriteria searchCriteria : gtnWsRequest.getGtnWsSearchRequest()
					.getGtnWebServiceSearchCriteriaList()) {

				if (searchCriteria.isFilter()) {

					String value = searchCriteria.getFilterValue1();
					if ("LIKE".equalsIgnoreCase(searchCriteria.getExpression())) {
						StringBuilder valueBuilder = new StringBuilder();
						valueBuilder.append('%');
						valueBuilder.append(value);
						valueBuilder.append('%');
						value = valueBuilder.toString();
					}
					inputWhereConditions.append(where).append(and)
							.append(getWhereClauseForAColumn(searchCriteria.getExpression(),
									filterAndSortingCriteriaMap().get(searchCriteria.getFieldId()), value,
									searchCriteria.getFilterValue2()));
					and = " AND ";
					where = "";
				}
			}
			periodConfigurationInputList.add(getSysSchemaCatalog());

			periodConfigurationInputList.add(inputWhereConditions);

			if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				periodConfigurationInputList.add(getOrderByActiveFlag());
				periodConfigurationInputList.addAll(
						getSortedInputs(gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceOrderByCriteriaList()));
				periodConfigurationInputList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordStart());
				periodConfigurationInputList.add(gtnWsRequest.getGtnWsSearchRequest().getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in executig query-", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);

		}
		return periodConfigurationInputList;
	}

	private List<Object> getSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		List<Object> list = new ArrayList<>();
		if (!gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			list.add(filterAndSortingCriteriaMap().get(dto.getPropertyId()) + " " + dto.getOrderByCriteria());
		} else {
			list.add(filterAndSortingCriteriaMap().get(GtnWsFileManagementConstants.VERSION_NO) + " DESC");
		}
		return list;
	}

	public String getWhereClauseForAColumn(String expersion, String field, String value1, String value2)
			throws GtnFrameworkGeneralException {
		try {
			StringBuilder sql = new StringBuilder();
			switch (expersion) {
			case "BETWEEN":
				sql.append(GtnFrameworkWebserviceConstant.CASTFLOORCAST).append(field)
						.append(" as float)) as datetime) >= '").append(GtnCommonUtil.getDate(value1)).append("' AND ");
				sql.append(GtnFrameworkWebserviceConstant.CASTFLOORCAST).append(field)
						.append(" as float)) as datetime) <= '").append(GtnCommonUtil.getDate(value2)).append("' ");
				break;
			case "AND":
				sql.append(field).append(" < '").append(value1).append("' AND ");
				sql.append(field).append(" > '").append(value2).append("' ");
				break;
			case "GREATER_OR_EQUAL":
				sql.append(GtnFrameworkWebserviceConstant.CASTFLOORCAST).append(field)
						.append(" as float)) as datetime) >= '").append(GtnCommonUtil.getDate(value1)).append("' ");
				break;
			case "LESS_OR_EQUAL":
				sql.append(GtnFrameworkWebserviceConstant.CASTFLOORCAST).append(field)
						.append(" as float)) as datetime) <= '").append(GtnCommonUtil.getDate(value1)).append("' ");
				break;
			case "LIKE":
				sql.append(field).append(' ').append(expersion).append(" '")
						.append(value1.replace('*', '%').replaceAll("\\s+", "%")).append("' ");
				break;
			case "EQUAL":
			case "EQUALS":
				sql.append(field).append(" = '").append(value1).append("' ");
				break;
			case "GREATER":
				sql.append(field).append(" > '").append(value1).append("' ");
				break;
			case "LESS":
				sql.append(field).append(" < '").append(value1).append("' ");
				break;
			default:
				sql.append(field).append(' ').append(expersion).append(" '").append(value1).append("' ");
				break;
			}
			return sql.toString();
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception in getWhereClauseForAColumn", ex);
		}
	}

	public String getDate(String input) throws ParseException {

		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = formatter.parse(input);
		SimpleDateFormat commonDate = new SimpleDateFormat("MM/dd/yyyy");
		return commonDate.format(date);
	}

	private Map<String, String> filterAndSortingCriteriaMap() {
		Map<String, String> filterAndSortingCriteriaMap = new HashMap<>();
		filterAndSortingCriteriaMap.put("moduleName", "A.MODULES");
		filterAndSortingCriteriaMap.put("buscinessProcessName", "A.TRANSACTION_NAME");
		filterAndSortingCriteriaMap.put("activeFlag", "A.active_flag");

		filterAndSortingCriteriaMap.put("companyName", "A.Company_NAME");
		filterAndSortingCriteriaMap.put("bucinsessUnitName", "A.BUSCINESS_UNIT");
		filterAndSortingCriteriaMap.put("periodViewName", "A.PV");
		filterAndSortingCriteriaMap.put(GtnWsFileManagementConstants.VERSION_NO, "A.v_NO");
		filterAndSortingCriteriaMap.put("fromModeName", "A.FROM_MODE");
		filterAndSortingCriteriaMap.put("fromFrequencyName", "A.FROM_FRE");
		filterAndSortingCriteriaMap.put("fromPeriodValue", "A.FROM_PERIOD");
		filterAndSortingCriteriaMap.put("fromPeriodDate", "A.FROM_PERIOD_DATE");
		filterAndSortingCriteriaMap.put("fromDefModeName", "A.FROM_DEF_MODE");
		filterAndSortingCriteriaMap.put("fromDefFrequencyName", "A.FROM_DEF_FRE");
		filterAndSortingCriteriaMap.put("fromDefPeriodValue", "A.FROM_DEFAULT_PERIOD");
		filterAndSortingCriteriaMap.put("fromDefPeriodDate", "A.FROM_DEFAULT_PERIOD_DATE");

		filterAndSortingCriteriaMap.put("toModeName", "A.TO_MODE");
		filterAndSortingCriteriaMap.put("toFrequencyName", "A.TO_FRE ");
		filterAndSortingCriteriaMap.put("toPeriodValue", "A.TO_PERIOD");
		filterAndSortingCriteriaMap.put("toPeriodDate", "A.TO_PERIOD_DATE");
		filterAndSortingCriteriaMap.put("toDefModeName", "A.TO_DEF_MODE");
		filterAndSortingCriteriaMap.put("toDefFrequencyName", "A.TO_DEF_FRE");
		filterAndSortingCriteriaMap.put("toDefPeriodValue", "A.TO_DEFAULT_PERIOD");
		filterAndSortingCriteriaMap.put("toDefPeriodDate", "A.TO_DEFUALT_PERIOD_DATE");
		filterAndSortingCriteriaMap.put("createdBy", "A.CREATED_BY");
		return filterAndSortingCriteriaMap;
	}

	private String getSysSchemaCatalog() throws GtnFrameworkGeneralException {
		String catalog = "";
		try (Connection connection = getSysSessionFactory().getSessionFactoryOptions().getServiceRegistry()
				.getService(ConnectionProvider.class).getConnection()) {
			catalog = connection.getCatalog();
		} catch (Exception ex) {
			logger.error("Error in getting DB name", ex);
			throw new GtnFrameworkGeneralException("Exception in getSysSchemaCatalog", ex);
		}
		return catalog;
	}

	private String getOrderByActiveFlag() {
		String orderBy = "";
		orderBy = " ORDER BY " + filterAndSortingCriteriaMap().get("activeFlag") + " , ";

		return orderBy;
	}

}
