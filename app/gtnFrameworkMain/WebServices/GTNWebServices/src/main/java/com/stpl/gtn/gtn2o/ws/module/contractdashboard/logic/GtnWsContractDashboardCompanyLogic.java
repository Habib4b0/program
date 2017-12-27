/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.entity.contract.ImtdCfpDetails;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.util.GtnCommonUtil;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsContractDashboardCompanyLogic {

	private static final String POPULATE_COMPANIES_VALUE = "populateCompaniesValue";
	private static final String RECORD_TYPE = "recordType";
	private static final String COMPANY_NO2 = "companyNo";
	private static final String COMPANY_NO = "COMPANY_NO";
	private static final String AND = " AND ";
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsContractDashboardCompanyLogic.class);
	private final GtnWsContractDashboardController controller;
	private final GtnWsContractDashboardCommonLogic commonLogic;

	public GtnWsContractDashboardCompanyLogic(GtnWsContractDashboardController controller) {
		this.controller = controller;
		this.commonLogic = new GtnWsContractDashboardCommonLogic();
	}

	public GtnWsContractDashboardController getController() {
		return controller;
	}

	public String getQuery(String queryName) {
		return getController().getQuery(queryName);
	}

	public String getWhereClauseForAColumn(String expersion, String field, String value1, String value2)
			throws GtnFrameworkGeneralException {
		return getController().getWhereClauseForAColumn(expersion, field, value1, value2);
	}

	public GtnUIFrameworkWebserviceResponse getCDCompanyAdditionLeftTableData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDCATLCount" : "getCDCATLResults";
			List<Object> inputlist = getCompanyAdditionSearchInput(gtnWsRequest.getGtnWsSearchRequest(), "");
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getCDCompanyAdditionLeftTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDCompanyAdditionLeftTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getCompanyAdditionSearchInput(GtnWsSearchRequest searchRequest, String postfix)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		try {
			for (int i = 0; i < searchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria searchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(i);

				if (searchCriteria.isFilter()) {
					String value = "%" + searchCriteria.getFilterValue1() + "%";
					inputWhereConditions.append(AND).append(getWhereClauseForAColumn("LIKE",
							getCmAdditionTabColumns(searchCriteria.getFieldId()), value, ""));
				} else if (i != 0) {
					String field = searchRequest.getGtnWebServiceSearchCriteriaList().get(i - 1).getFilterValue1()
							.trim();
					String value = searchCriteria.getFilterValue1().replace("*", "%");

					inputWhereConditions.append(getWhereClauseForCmAddition(field, value));
				}
			}
			list.add(inputWhereConditions);

			if (!searchRequest.isCount()) {
				list.add(getCompanyAdditionSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList(), postfix));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in executig query-", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);

		}
		return list;
	}

	private String getCompanyAdditionSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList,
			String postfix) {
		String property = COMPANY_NO2 + postfix;
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = dto.getPropertyId();
			order = " " + dto.getOrderByCriteria();
		}
		String columnName = commonLogic.getCompanyTabColumns(property);
		if (columnName == null) {
			columnName = COMPANY_NO;
		}
		return columnName + order;
	}

	private String getCmAdditionTabColumns(String property) {
		String columnName = commonLogic.getCompanyAdditionTabColumns(property);
		if (columnName == null) {
			return GtnFrameworkCommonStringConstants.STRING_EMPTY;
		}
		return columnName;
	}

	private String getWhereClauseForCmAddition(String field, String value) {
		StringBuilder sql = new StringBuilder();
		sql.append("  AND  ").append(getCmAdditionTabColumns(field)).append(" LIKE '").append(value).append("' ");
		return sql.toString();
	}

	public String getCompanyMoveAllLeftQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {

		StringBuilder sql = new StringBuilder();
		gtnWsRequest.getGtnWsSearchRequest().setCount(true);
		List<Object> inputlist = getCompanyAdditionSearchInput(gtnWsRequest.getGtnWsSearchRequest(), "");
		String query = getQuery("getCDCompanyAdditionMoveAllLeftQuery");
		String searchFilter = inputlist.get(0).toString();
		sql.append(query);
		if (!searchFilter.isEmpty()) {
			sql.append(searchFilter);
		}

		return sql.toString();

	}

	public String getCompanyMoveAllRightQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest)
			throws GtnFrameworkGeneralException {
		gtnWsRequest.getGtnWsSearchRequest().setCount(true);
		GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
		List<Object> inputlist = getCompanyAdditionSearchInput(gtnWsRequest.getGtnWsSearchRequest(), "");
		inputlist.add(0, cdRequest.getCfpContractId());
		inputlist.add(0, gtnWsRequest.getGtnWsGeneralRequest().getSessionId());
		inputlist.add(0, gtnWsRequest.getGtnWsGeneralRequest().getUserId());
		return controller.getQuery(inputlist, "getCDCompanyAdditionMoveAllRightQuery");

	}

	public GtnUIFrameworkWebserviceResponse getCDCompanyAdditionViewTableData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDCompanyAdditionViewCountQuery"
					: "getCDCompanyAdditionViewResultsQuery";
			List<Object> inputlist = getCompanyAdditionSearchInput(gtnWsRequest.getGtnWsSearchRequest(), "1");
			commonLogic.addUserIdSessionId(gtnWsRequest, inputlist);
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getCDCompanyAdditionViewTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDCompanyAdditionViewTableData", ex);
		}
		return gtnResponse;
	}

	@SuppressWarnings("unchecked")
	public GtnUIFrameworkWebserviceResponse companyAdditionMoveRight(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		Session session = getController().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			GtnWsGeneralRequest getRequest = gtnWsRequest.getGtnWsGeneralRequest();
			GtnWsRecordBean recordBean = gtnWsRequest.getGtnWsContractDashboardRequest().getTableBean();
			int companyMasterSid = recordBean.getProperties().get(8) == null ? 0
					: recordBean.getIntegerPropertyByIndex(8);

			Criteria cr = session.createCriteria(ImtdCfpDetails.class)
					.add(Restrictions.eq("companyMasterSid", companyMasterSid))
					.add(Restrictions.eq("usersSid", getRequest.getUserId()))
					.add(Restrictions.eq("sessionId", getRequest.getSessionId()))
					.add(Restrictions.ne("operation", "D"));
			List<ImtdCfpDetails> results = cr.list();
			if (results.isEmpty()) {
				SimpleDateFormat tempFromat = new SimpleDateFormat(GtnWsContractDashboardContants.MMDDYYYY);
				ImtdCfpDetails imtdCompany = new ImtdCfpDetails();
				imtdCompany.setCfpDetailsAttachedDate(new Date());
				imtdCompany.setCfpModelSid(cdRequest.getCfpContractId());
				imtdCompany.setCompanyId(recordBean.getStringPropertyByIndex(0));
				imtdCompany.setCompanyNo(recordBean.getStringPropertyByIndex(1));
				imtdCompany.setCompanyType(recordBean.getStringPropertyByIndex(10));
				imtdCompany.setCompanyName(recordBean.getStringPropertyByIndex(2));
				imtdCompany.setCompanyStatus(recordBean.getIntegerPropertyByIndex(9));
				String startDate = tempFromat.format(new Date(recordBean.getLongPropertyByIndex(11)));
				String endDate = tempFromat.format(new Date(recordBean.getLongPropertyByIndex(12)));
				imtdCompany.setCompanyStartDate(tempFromat.parse(startDate));
				imtdCompany.setCompanyEndDate(tempFromat.parse(endDate));
				imtdCompany.setCompanyMasterSid(companyMasterSid);
				imtdCompany.setOperation("A");
				imtdCompany.setSessionId(getRequest.getSessionId());
				imtdCompany.setUsersSid(getRequest.getUserId());
				imtdCompany.setCreatedBy(Integer.valueOf(getRequest.getUserId()));
				imtdCompany.setCreatedDate(new Date());
				imtdCompany.setModifiedBy(Integer.valueOf(getRequest.getUserId()));
				imtdCompany.setModifiedDate(new Date());
				imtdCompany.setImtdCreatedDate(new Date());
				imtdCompany.setTradingPartnerContractNo(recordBean.getStringPropertyByIndex(13));
				session.saveOrUpdate(imtdCompany);
				tx.commit();
			}

		} catch (Exception ex) {
			tx.rollback();
			logger.error("Exception in companyAdditionMoveRight", ex);
			throw new GtnFrameworkGeneralException("Exception in companyAdditionMoveRight", ex);
		} finally {
			session.close();
		}
		return gtnResponse;
	}

	private List<Object> getCompaniesSearchInput(GtnWsSearchRequest searchRequest) throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		String recordType = "";
		try {
			if (!searchRequest.isCount() && !searchRequest.getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria searchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(0);
				if (!searchCriteria.isFilter()) {
					recordType = searchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}
			}

			StringBuilder whereBuilder = returnWhereCluase(searchRequest);
			String timeFilter = getCurrentHistoryFilter(recordType);
			if (!timeFilter.isEmpty()) {
				whereBuilder.append(timeFilter);
			}
			list.add(whereBuilder.toString());
			if (!searchRequest.isCount()) {
				list.add(getCompniesDetailSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getCompaniesSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getCompaniesSearchInput : ", ex);

		}
		return list;
	}

	private String getCurrentHistoryFilter(String recordType) {
		String defaultJavaDateFormat = "EEE MMM dd HH:mm:ss z yyyy";
		String sqlDateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
		String date = GtnCommonUtil.convertStringToDate(new Date().toString(), defaultJavaDateFormat, sqlDateFormat);
		boolean history = false;
		boolean current = false;
		boolean future = false;
		if (!recordType.equals(GtnFrameworkCommonStringConstants.STRING_EMPTY)) {
			current = recordType.contains("Current");
			history = recordType.contains("History");
			future = recordType.contains("Future");
		}
		if (!history && !current && !future) {
			return GtnFrameworkCommonStringConstants.STRING_EMPTY;
		}
		String betweenDate = " BETWEEN CAST(CFP_DETAILS_START_DATE AS DATE) AND CAST(ISNULL(CFP_DETAILS_END_DATE,";
		String startDate = " CAST(CFP_DETAILS_START_DATE AS DATE) > ";
		String endDate = " CAST(CFP_DETAILS_END_DATE AS DATE) < ";
		String and = " AND ( ";
		date = " CAST('" + date + "' AS DATE) ";

		if (history && current && future) {
			String sql = and + date + betweenDate + date + ")  AS DATE) ";
			sql += " OR " + endDate + date + " ";
			sql += " OR " + startDate + date + " )";
			return sql;
		} else if (history && current) {
			return and + date + betweenDate + date + ") AS DATE) OR CAST(CFP_DETAILS_END_DATE AS DATE) < " + date
					+ ") ";
		} else if (history && future) {
			return " AND (" + endDate + date + " OR CAST(CFP_DETAILS_START_DATE AS DATE) > " + date + ") ";
		} else if (current && future) {
			return and + date + betweenDate + date + ")  AS DATE) OR CAST(CFP_DETAILS_START_DATE AS DATE) > " + date
					+ ") ";
		} else if (current) {
			return " AND " + date + betweenDate + date + ")  AS DATE)  ";
		} else if (history) {
			return AND + endDate + date + " ";
		} else {
			return AND + startDate + date + " ";
		}

	}

	private boolean skipCriteria(String field) {
		boolean ret = false;
		switch (field) {
		case RECORD_TYPE:
		case "BasePrice":
			ret = true;
			break;
		default:
			ret = false;
		}
		return ret;
	}

	private String getCompniesDetailSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = COMPANY_NO2;
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = dto.getPropertyId();
			order = " " + dto.getOrderByCriteria();
		}
		return getCmAdditionTabColumns(property) + order;
	}

	public GtnUIFrameworkWebserviceResponse getCDCompniesDetailTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCompaniesDetailsCount"
					: "getCompaniesDetailsResults";
			companyProcess(gtnWsRequest, gtnResponse, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getCDCompniesDetailTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDCompniesDetailTableData", ex);
		}
		return gtnResponse;
	}

	private void companyProcess(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse, String queryName) throws GtnFrameworkGeneralException {
		List<Object> inputlist = getCompaniesSearchInput(gtnWsRequest.getGtnWsSearchRequest());
		String catalog = getController().getSysSchemaCatalog();
		commonLogic.addUserIdSessionId(gtnWsRequest, inputlist);
		inputlist.add(0, catalog);
		inputlist.add(0, catalog);
		commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
	}

	public GtnUIFrameworkWebserviceResponse getCDCompniesDetailViewTableData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCompaniesDetailsCount"
					: "getCompaniesDetailsViewResults";
			companyProcess(gtnWsRequest, gtnResponse, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getCDCompniesDetailViewTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDCompniesDetailViewTableData", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse populateAllCompanies(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			GtnWsGeneralRequest genRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<String> inputlist = Arrays.asList(getCmAdditionTabColumns(cdRequest.getPopulateField()),
					getController().getPopulateValue(cdRequest.getPopulateField(), cdRequest.getPopulateValue()),
					genRequest.getUserId(), genRequest.getSessionId());
			getController().executeUpdateQuery(controller.getQuery(inputlist, POPULATE_COMPANIES_VALUE));
		} catch (Exception ex) {
			logger.error("Exception in populateAllCompanies", ex);
			throw new GtnFrameworkGeneralException("Exception in populateAllCompanies", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse populateCompany(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			GtnWsGeneralRequest genRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<String> inputlist = Arrays.asList(getCmAdditionTabColumns(cdRequest.getPopulateField()),
					getController().getPopulateValue(cdRequest.getPopulateField(), cdRequest.getPopulateValue()),
					genRequest.getUserId(), genRequest.getSessionId());
			getController().executeUpdateQuery(
					controller.getQuery(inputlist, POPULATE_COMPANIES_VALUE) + " AND CHECK_RECORD='1'");
		} catch (Exception ex) {
			logger.error("Exception in populateCompany", ex);
			throw new GtnFrameworkGeneralException("Exception in populateCompany", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse populateCompanyField(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWsContractDashboardRequest cdRequest = gtnWsRequest.getGtnWsContractDashboardRequest();
			GtnWsGeneralRequest genRequest = gtnWsRequest.getGtnWsGeneralRequest();
			List<String> inputlist = Arrays.asList(getCmAdditionTabColumns(cdRequest.getPopulateField()),
					getController().getPopulateValue(cdRequest.getPopulateField(), cdRequest.getPopulateValue()),
					genRequest.getUserId(), genRequest.getSessionId());
			getController().executeUpdateQuery(controller.getQuery(inputlist, POPULATE_COMPANIES_VALUE)
					+ " AND IMTD_CFP_DETAILS_SID='" + cdRequest.getSystemId() + "'");
		} catch (Exception ex) {
			logger.error("Exception in populateCompanyField", ex);
			throw new GtnFrameworkGeneralException("Exception in populateCompanyField", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse getCDCompniessDetailTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCompaniessDetailsPendingCount"
					: "getCompaniessDetailsPendingResults";
			List<Object> inputlist = getCompaniessSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			String catalog = getController().getSysSchemaCatalog();
			inputlist.add(0, catalog);
			inputlist.add(0, catalog);
			String recordType = "";
			String contractId = "";

			if (!gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria searchCriteria = gtnWsRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(0);
				GtnWebServiceSearchCriteria searchCriteriaforId = gtnWsRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(1);

				if (!searchCriteria.isFilter()) {
					recordType = searchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}
				if (!searchCriteriaforId.isFilter()) {
					contractId = searchCriteriaforId.getFilterValue1();
				}
			}
			inputlist.add(0, recordType);

			inputlist.add(0, contractId);
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getCDCompniessDetailTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDCompniessDetailTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getCompaniessSearchInput(GtnWsSearchRequest searchRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		try {
			if (!searchRequest.isCount()) {
				list.add(returnWhereCluase(searchRequest).toString());
				list.add(getCompniesDetailSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getCompaniesSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getCompaniesSearchInput: ", ex);

		}
		return list;
	}

	private StringBuilder returnWhereCluase(GtnWsSearchRequest searchRequest) throws GtnFrameworkGeneralException {
		StringBuilder whereBuilder = new StringBuilder("");
		for (int i = 0; i < searchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
			GtnWebServiceSearchCriteria searchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(i);
			if (searchCriteria.isFilter() && !skipCriteria(searchCriteria.getFieldId())) {
				String value = searchCriteria.getFilterValue1();
				if ("LIKE".equalsIgnoreCase(searchCriteria.getExpression())) {
					StringBuilder valueBuilder = new StringBuilder();
					valueBuilder.append("%");
					valueBuilder.append(value);
					valueBuilder.append("%");
					value = valueBuilder.toString();
				}
				whereBuilder.append(AND);
				whereBuilder.append(getWhereClauseForAColumn(searchCriteria.getExpression(),
						getCmAdditionTabColumns(searchCriteria.getFieldId()), value, searchCriteria.getFilterValue2()));

			}
		}

		return whereBuilder;
	}

	public GtnUIFrameworkWebserviceResponse getCDCompniessViewDetailTableData(
			GtnUIFrameworkWebserviceRequest cdcompaniesviewgtnWsRequest,
			GtnUIFrameworkWebserviceResponse cdcompaniesgtnResponse) throws GtnFrameworkGeneralException {
		try {
			String cdcompaniesqueryName = cdcompaniesviewgtnWsRequest.getGtnWsSearchRequest().isCount()
					? "getCompaniessViewDetailsPendingCount" : "getCompaniessDetailsViewPendingResults";
			List<Object> cdcompaniesinputlist = getCompaniessSearchInput(
					cdcompaniesviewgtnWsRequest.getGtnWsSearchRequest());
			String cdcompaniesCatalog = getController().getSysSchemaCatalog();
			cdcompaniesinputlist.add(0, cdcompaniesCatalog);
			cdcompaniesinputlist.add(0, cdcompaniesCatalog);
			String cdcompaniescontractId = "";
			if (!cdcompaniesviewgtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria cdcompaniessearchCriteriaforId = cdcompaniesviewgtnWsRequest
						.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().get(1);

				if (!cdcompaniessearchCriteriaforId.isFilter()) {
					cdcompaniescontractId = cdcompaniessearchCriteriaforId.getFilterValue1();
				}
			}

			cdcompaniesinputlist.add(0, cdcompaniescontractId);
			commonLogic.setGtnSearchResponse(cdcompaniesviewgtnWsRequest, cdcompaniesgtnResponse, controller,
					cdcompaniesinputlist, cdcompaniesqueryName);
		} catch (

		Exception ex) {
			logger.error("Exception in getCDCompniessViewDetailTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCDCompniessViewDetailTableData", ex);
		}
		return cdcompaniesgtnResponse;
	}

}
