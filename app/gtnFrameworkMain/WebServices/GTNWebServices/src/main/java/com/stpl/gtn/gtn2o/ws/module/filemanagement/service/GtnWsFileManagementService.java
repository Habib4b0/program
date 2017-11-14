/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.filemanagement.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.emailconfig.constants.GtnWsEMailConfigurationConstants;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.filemanagement.FileManagement;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.filemanagement.bean.GtnWsFileManagementBean;
import com.stpl.gtn.gtn2o.ws.filemanagement.constants.GtnWsFileManagementConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.filemanagement.GtnWsFileManagementResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service()
@Scope(value = "singleton")
public class GtnWsFileManagementService {

	public GtnWsFileManagementService() {
		/**
		 * empty constructor
		 */
	}

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsFileManagementService.class);
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private org.hibernate.SessionFactory sysSessionFactory;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public org.hibernate.SessionFactory getSysSessionFactory() {
		return sysSessionFactory;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse historyTableLoad(GtnUIFrameworkWebserviceRequest wsRequest,
			GtnUIFrameworkWebserviceResponse wsResponse, String fileTypeArray, String fileTypeDataLoad) {
		try {
			String fileType;
			String country;

			String[] fileSplit = fileTypeArray.split(",");

			fileType = fileSplit[0];
			String company = getValueFromArray(fileSplit, 1);
			String businessUnit = getValueFromArray(fileSplit, 2);
			country = getValueFromArray(fileSplit, 3);
			logger.info(company);
			logger.info(businessUnit);

			Session session = getSessionFactory().openSession();

			if ("DataPopulate".equals(fileTypeDataLoad)) {

				Criteria queryForData = session.createCriteria(FileManagement.class);

				GtnWsFileManagementResponse fileManagementResponse = new GtnWsFileManagementResponse();
				GtnWsFileManagementBean fileManagementBean = new GtnWsFileManagementBean();
				ProjectionList selectList = Projections.projectionList();

				selectList.add(Projections.property(GtnWsFileManagementConstants.FORECAST_NAME));
				selectList.add(Projections.property(GtnWsFileManagementConstants.CREATED_DATE));
				queryForData.setProjection(selectList);
				queryForData.add(Restrictions.eq(GtnWsFileManagementConstants.FILE_TYPE, Integer.valueOf(fileType)));

				List<Object[]> dataForLoad = queryForData.list();
				fileManagementBean.setCurrentFile(String.valueOf(dataForLoad.get(0)));
				fileManagementBean.setEffectiveDate(String.valueOf(dataForLoad.get(1)));
				fileManagementResponse.setGtnWsFileManagementBean(fileManagementBean);
				wsResponse.setGtnWsFileManagementResponse(fileManagementResponse);
			} else {

				GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();

				Criterion criteriaMain = null;
				Criteria selectQueryForCount = session.createCriteria(FileManagement.class);

				ProjectionList selectList = Projections.projectionList();

				selectList.add(Projections.property(GtnWsFileManagementConstants.FORECAST_NAME));
				selectList.add(Projections.property("company"));
				selectList.add(Projections.property("companyMaster"));
				selectList.add(Projections.property(GtnWsFileManagementConstants.CREATED_DATE));
				selectList.add(Projections.property(GtnWsFileManagementConstants.FORECAST_SOURCE));
				selectList.add(Projections.property(GtnWsFileManagementConstants.VERSION));
				selectList.add(Projections.property("fromPeriod"));
				selectList.add(Projections.property("toPeriod"));
				selectQueryForCount.setProjection(selectList);

				String fileTypeDescription = getDescription(fileType, session);
				List<String> descriptionList = new ArrayList<>();
				descriptionList.add("Demand");
				descriptionList.add("Inventory Withdrawal - Forecast Summary");
				descriptionList.add("Inventory Withdrawal - Forecast Detail");
				descriptionList.add("Adjusted Demand");
				descriptionList.add("Customer Sales");
				Criterion criteria = Restrictions.eq(GtnWsFileManagementConstants.FILE_TYPE, Integer.valueOf(fileType));
				if (GtnWsFileManagementConstants.EX_FACTORY_SALES.equals(fileTypeDescription)) {
					if (GtnWsFileManagementConstants.COUNTRY_PR.equals(getDescription(country, session))) {
						criteriaMain = Restrictions.and(
								Restrictions.ilike(GtnWsFileManagementConstants.FORECAST_SOURCE, "FF_SALES"), criteria);
					} else {
						Criterion criteria1 = Restrictions.ilike(GtnWsFileManagementConstants.FORECAST_SOURCE,
								GtnWsFileManagementConstants.FORESIGHT);
						criteriaMain = Restrictions.and(Restrictions.or(criteria1,
								Restrictions.ilike(GtnWsFileManagementConstants.FORECAST_SOURCE,
										GtnWsFileManagementConstants.LE_FORESIGHT)),
								criteria);
					}
				} else if (descriptionList.contains(fileTypeDescription)) {
					criteriaMain = criteria;
				}

				selectQueryForCount.add(criteriaMain);
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				List<Object[]> fileTypeList = selectQueryForCount.list();
				if (wsRequest.getGtnWsSearchRequest().isCount()) {
					gtnSerachResponse.setCount(fileTypeList.size());

				} else {
					gtnUIFrameworkDataTable.addData(fileTypeList);
					gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
				}

				wsResponse.setGtnSerachResponse(gtnSerachResponse);

			}
		} catch (Exception ex) {

			logger.error("Error in " + GtnWsEMailConfigurationConstants.GET_DEFAULT_VALUE, ex);
		}
		return wsResponse;
	}

	public String getDescription(String helperIdStr, Session session) {
		int helperId = Integer.parseInt(helperIdStr);

		Criteria query = session.createCriteria(HelperTable.class);
		query.setProjection(Projections.property("description"));
		query.add(Restrictions.eq("helperTableSid", helperId));

		List<?> queryList = query.list();

		return String.valueOf(queryList.get(0));

	}

	String getValueFromArray(String[] value, int index) {
		return value.length > index ? value[index] : "";
	}

	public GtnUIFrameworkWebserviceResponse resultTableLoad(GtnUIFrameworkWebserviceRequest wsRequest,
			GtnUIFrameworkWebserviceResponse wsResponse, String fileTypeArray) {

		String type;
		String business;
		String country;
		String fileName;
		String fromDate;
		String toDate;

		String[] fileSplit = fileTypeArray.split(",");

		type = fileSplit[0];
		String company = getValueFromArray(fileSplit, 1);
		String version = getValueFromArray(fileSplit, 2);
		business = getValueFromArray(fileSplit, 3);
		country = getValueFromArray(fileSplit, 4);
		fileName = getValueFromArray(fileSplit, 5);
		fromDate = getValueFromArray(fileSplit, 6);
		toDate = getValueFromArray(fileSplit, 7);

		Session session = getSessionFactory().openSession();

		String searchQuery;
		String mainSearchQuery = "";
		boolean isCount = wsRequest.getGtnWsSearchRequest().isCount();
		GtnWsFileManagementBean fileManagementBean = wsRequest.getGtnWsFileManagementRequest().getFileManagementBean();
		String fileType = fileManagementBean.getFiletype();
		String queryType = isCount ? "count" : "search";
		try {

			if (GtnWsFileManagementConstants.EX_FACTORY_SALES.equals((getDescription(fileType, session)))
					&& GtnWsFileManagementConstants.COUNTRY_PR.equals(fileManagementBean.getCountry())) {
				searchQuery = "ex-factory-" + queryType + GtnFrameworkCommonConstants.QUERY;
				mainSearchQuery = searchQuery.replace("[?SOURCE]", "'FF_SALES'");

			} else if (GtnWsFileManagementConstants.COUNTRY_US.equals(fileManagementBean.getCountry())) {
				switch ((getDescription(fileType, session))) {
				case GtnWsFileManagementConstants.EX_FACTORY_SALES:
					searchQuery = "ex-factory-" + queryType + GtnFrameworkCommonConstants.QUERY;
					mainSearchQuery = searchQuery.replace("[?SOURCE]", "'FORESIGHT','LE_FORESIGHT'");
					break;
				case GtnWsFileManagementConstants.DEMAND:
					mainSearchQuery = "demand-" + queryType + GtnFrameworkCommonConstants.QUERY;
					break;
				case GtnWsFileManagementConstants.ADJUSTED_DEMAND:
					mainSearchQuery = "adjusted-demand-" + queryType + GtnFrameworkCommonConstants.QUERY;
					break;
				case GtnWsFileManagementConstants.INVENTORY_WITHDRAWAL_SUMMARY:
					mainSearchQuery = "inventory-withdrawl-summary-" + queryType + GtnFrameworkCommonConstants.QUERY;
					break;
				case GtnWsFileManagementConstants.INVENTORY_WITHDRAWAL_DETAIL:
					mainSearchQuery = "inventory-with-det-" + queryType + GtnFrameworkCommonConstants.QUERY;
					break;
				case GtnWsFileManagementConstants.CUSTOMERGTS:
					mainSearchQuery = "customer-gts-" + queryType + GtnFrameworkCommonConstants.QUERY;
					break;
				default:
					break;
				}
			}

			String condition = "";
			mainSearchQuery = mainSearchQuery.replace("[?COMPANY_MASTER_SID]", company).replace("[?COUNTRY]", country)
					.replace("[?BUSINESS_UNIT]", business);
			condition = addWhereCondition(fileName, condition, "FM.FORECAST_NAME");
			condition = addWhereCondition(type, condition, GtnFrameworkCommonConstants.FM_SOURCE);
			condition = addWhereCondition(version, condition, "FM.FORECAST_VER");
			condition = addWhereCondition(fromDate, condition, GtnFrameworkCommonConstants.FM_FORECAST_DATE);
			condition = addWhereCondition(toDate, condition, GtnFrameworkCommonConstants.FM_FORECAST_DATE);
			mainSearchQuery = mainSearchQuery.replace("[?CONDITION]", condition);
			wsResponse.setResponseStatus(mainSearchQuery);

		} catch (Exception ex) {

			logger.error("Error", ex);
		}

		return wsResponse;
	}

	String addWhereCondition(String input, String condition, String column) {
		if (!"".equals(input)) {
			String inputValue = input.replace("*", "%");
			return condition + " AND " + column + " like '" + inputValue + "'";
		}
		return condition;
	}

	public String getSysSchemaCatalog() throws GtnFrameworkGeneralException {
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

}
