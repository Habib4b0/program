/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import org.springframework.stereotype.Service;

/**
 *
 * @author Abhiram.Giri
 */
@Service
public class GtnWsContractDashboardLookupLogic {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsContractDashboardLookupLogic.class);
	private final GtnWsContractDashboardController controller;
	private final GtnWsContractDashboardCommonLogic commonLogic;

	public GtnWsContractDashboardLookupLogic(GtnWsContractDashboardController controller) {
		this.controller = controller;
		this.commonLogic = new GtnWsContractDashboardCommonLogic();
	}

	public GtnWsContractDashboardController getController() {
		return controller;
	}

	public String getWhereClauseForAColumn(String expersion, String field, String value1, String value2)
			throws GtnFrameworkGeneralException {
		return getController().getWhereClauseForAColumn(expersion, field, value1, value2);
	}

	public GtnUIFrameworkWebserviceResponse getNSFLookupTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDNetSalesFormulaCount"
					: "getCDNetSalesFormulaResults";
			List<Object> inputlist = getNetSalesFormulaSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			String catalog = getController().getSysSchemaCatalog();
			inputlist.add(0, catalog);
			inputlist.add(0, catalog);
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getNSFLookupTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getNSFLookupTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getNetSalesFormulaSearchInput(GtnWsSearchRequest nsfSearchRequest)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder nsfWhereCondition = new StringBuilder();
		String where = GtnFrameworkWebserviceConstant.WHERE;
		String and = "";
		try {
			for (int i = 0; i < nsfSearchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria searchCriteria = nsfSearchRequest.getGtnWebServiceSearchCriteriaList().get(i);
				String value = searchCriteria.getFilterValue1();
				if (searchCriteria.isFilter() && "LIKE".equalsIgnoreCase(searchCriteria.getExpression())) {
					value = "%" + value + "%";
				}
				nsfWhereCondition.append(where).append(and)
						.append(getController().getWhereClauseForAColumn(searchCriteria.getExpression(),
								getNetSalesFormulaColumns(searchCriteria.getFieldId()), value,
								searchCriteria.getFilterValue2()));
				where = "";
				and = GtnFrameworkWebserviceConstant.AND_COLUMN;
			}
			list.add(nsfWhereCondition);
			if (!nsfSearchRequest.isCount()) {
				list.add(getNetSalesFormulaSortedInputs(nsfSearchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(nsfSearchRequest.getTableRecordStart());
				list.add(nsfSearchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getNetSalesFormulaSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getNetSalesFormulaSearchInput : ", ex);

		}
		return list;
	}

	private String getNetSalesFormulaSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = "formulaId";
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = dto.getPropertyId();
			order = " " + dto.getOrderByCriteria();
		}
		return getNetSalesFormulaColumns(property) + order;
	}

	private String getNetSalesFormulaColumns(String property) {
		String columnName = commonLogic.getNetSalesFormulaColumns(property);
		if (columnName == null) {
			columnName = "NSF.NET_SALES_FORMULA_ID";
		}
		return columnName;
	}

	public GtnUIFrameworkWebserviceResponse getCFPLookupTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDCFPPopupCount"
					: "getCDCFPPopupResults";
			List<Object> inputlist = getCFPSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			String catalog = getController().getSysSchemaCatalog();
			inputlist.add(0, catalog);
			inputlist.add(0, catalog);
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getCFPLookupTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getCFPLookupTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getCFPSearchInput(GtnWsSearchRequest cfpSearchRequest) throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder cfpWhereCondition = new StringBuilder();
		String where = GtnFrameworkWebserviceConstant.WHERE;
		String and = "";
		try {
			for (int i = 0; i < cfpSearchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria cfpSearchCriteria = cfpSearchRequest.getGtnWebServiceSearchCriteriaList().get(i);
				String value = cfpSearchCriteria.getFilterValue1();
				if (cfpSearchCriteria.isFilter() && "LIKE".equalsIgnoreCase(cfpSearchCriteria.getExpression())) {
					value = "%" + value + "%";
				}
				cfpWhereCondition.append(where).append(and)
						.append(getController().getWhereClauseForAColumn(cfpSearchCriteria.getExpression(),
								getCFPColumns(cfpSearchCriteria.getFieldId()), value, cfpSearchCriteria.getFilterValue2()));
				where = "";
				and = GtnFrameworkWebserviceConstant.AND_COLUMN;
			}
			list.add(cfpWhereCondition);
			if (!cfpSearchRequest.isCount()) {
				list.add(getCFPSortedInputs(cfpSearchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(cfpSearchRequest.getTableRecordStart());
				list.add(cfpSearchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getCFPSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getCFPSearchInput: ", ex);

		}
		return list;
	}

	private String getCFPSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = GtnFrameworkWebserviceConstant.SYS_ID;
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = changePropertyForSystemId(dto.getPropertyId());
			order = " " + dto.getOrderByCriteria();

		}
		return getCFPColumns(property) + order;
	}

	private String changePropertyForSystemId(String property) {
		if (property.equals(GtnFrameworkCommonConstants.SYSTEM_ID)) {
			return GtnFrameworkWebserviceConstant.SYS_ID;
		}
		return property;
	}

	private String getCFPColumns(String property) {
		String columnName = commonLogic.getCfpColumns(property);
		if (columnName == null) {
			columnName = "cf.CFP_MODEL_SID";
		}
		return columnName;
	}

	public GtnUIFrameworkWebserviceResponse getIFPLookupTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDIFPPopupCount"
					: "getCDIFPPopupResults";
			List<Object> inputlist = getIFPSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			String catalog = getController().getSysSchemaCatalog();
			inputlist.add(0, catalog);
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getIFPLookupTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getIFPLookupTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getIFPSearchInput(GtnWsSearchRequest searchRequest) throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		String where = GtnFrameworkWebserviceConstant.WHERE;
		String and = "";
		try {
			for (int i = 0; i < searchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria ifpSearchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(i);
				String value = ifpSearchCriteria.getFilterValue1();
				if (ifpSearchCriteria.isFilter() && "LIKE".equalsIgnoreCase(ifpSearchCriteria.getExpression())) {
					value = "%" + value + "%";
				}
				inputWhereConditions.append(where).append(and)
						.append(getController().getWhereClauseForAColumn(ifpSearchCriteria.getExpression(),
								getIFPColumns(ifpSearchCriteria.getFieldId()), value, ifpSearchCriteria.getFilterValue2()));
				where = "";
				and = GtnFrameworkWebserviceConstant.AND_COLUMN;
			}
			list.add(inputWhereConditions);
			if (!searchRequest.isCount()) {
				list.add(getIFPSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getIFPSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getIFPSearchInput : ", ex);

		}
		return list;
	}

	private String getIFPSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = GtnFrameworkWebserviceConstant.SYS_ID;
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = changePropertyForSystemId(dto.getPropertyId());
			order = " " + dto.getOrderByCriteria();
		}
		return getIFPColumns(property) + order;
	}

	private String getIFPColumns(String property) {
		String columnName = commonLogic.getIfpColumns(property);
		if (columnName == null) {
			columnName = "ifp.IFP_MODEL_SID";
		}
		return columnName;
	}

	public GtnUIFrameworkWebserviceResponse getTPLookupTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDTPPopupCount"
					: "getCDTPPopupResults";
			List<Object> inputlist = getTPSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getTPLookupTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getTPLookupTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getTPSearchInput(GtnWsSearchRequest searchRequest) throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		String where = GtnFrameworkWebserviceConstant.WHERE;
		String and = "";
		try {
			for (int i = 0; i < searchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria tpSearchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(i);
				String value = tpSearchCriteria.getFilterValue1();
				if (tpSearchCriteria.isFilter() && "LIKE".equalsIgnoreCase(tpSearchCriteria.getExpression())) {
					value = "%" + value + "%";
				}
				inputWhereConditions.append(where).append(and)
						.append(getController().getWhereClauseForAColumn(tpSearchCriteria.getExpression(),
								getTPColumns(tpSearchCriteria.getFieldId()), value, tpSearchCriteria.getFilterValue2()));
				where = "";
				and = GtnFrameworkWebserviceConstant.AND_COLUMN;
			}
			list.add(inputWhereConditions);
			if (!searchRequest.isCount()) {
				list.add(getTPSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getTPSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getTPSearchInput : ", ex);

		}
		return list;
	}

	private String getTPSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = "companyId";
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = dto.getPropertyId();
			order = " " + dto.getOrderByCriteria();
		}
		return getTPColumns(property) + order;
	}

	private String getTPColumns(String property) {
		String columnName = commonLogic.getTpColumns(property);
		if (columnName == null) {
			columnName = "cm.COMPANY_MASTER_SID";
		}
		return columnName;
	}

	public GtnUIFrameworkWebserviceResponse getPSLookupTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDPSPopupCount"
					: "getCDPSPopupResults";
			List<Object> inputlist = getPSSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getPSLookupTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getPSLookupTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getPSSearchInput(GtnWsSearchRequest searchRequest) throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		try {
			for (int i = 0; i < searchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria psSearchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(i);
				String value = psSearchCriteria.getFilterValue1();
				if (psSearchCriteria.isFilter() && "LIKE".equalsIgnoreCase(psSearchCriteria.getExpression())) {
					value = "%" + value + "%";
				}
				inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN)
						.append(getController().getWhereClauseForAColumn(psSearchCriteria.getExpression(),
								getPSColumns(psSearchCriteria.getFieldId()), value, psSearchCriteria.getFilterValue2()));
			}
			list.add(inputWhereConditions);
			if (!searchRequest.isCount()) {
				list.add(getPSSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getPSSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getPSSearchInput : ", ex);

		}
		return list;
	}

	private String getPSSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = GtnFrameworkWebserviceConstant.SYS_ID;
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = changePropertyForSystemId(dto.getPropertyId());
			order = " " + dto.getOrderByCriteria();
		}
		return getPSColumns(property) + order;
	}

	private String getPSColumns(String property) {
		String columnName = commonLogic.getPsColumns(property);
		if (columnName == null) {
			columnName = "ps.PS_MODEL_SID";
		}
		return columnName;
	}

	public GtnUIFrameworkWebserviceResponse getRSLookupTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDRSPopupCount"
					: "getCDRSPopupResults";
			List<Object> inputlist = getRSSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getRSLookupTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getRSLookupTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getRSSearchInput(GtnWsSearchRequest searchRequest) throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		try {
			for (int i = 0; i < searchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria rsSearchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(i);
				String value = rsSearchCriteria.getFilterValue1();
				if (rsSearchCriteria.isFilter() && "LIKE".equalsIgnoreCase(rsSearchCriteria.getExpression())) {
					value = "%" + value + "%";
				}
				inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN)
						.append(getController().getWhereClauseForAColumn(rsSearchCriteria.getExpression(),
								getRSColumns(rsSearchCriteria.getFieldId()), value, rsSearchCriteria.getFilterValue2()));

			}
			list.add(inputWhereConditions);
			if (!searchRequest.isCount()) {
				list.add(getRSSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getRSSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getRSSearchInput : ", ex);

		}
		return list;
	}

	private String getRSSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = GtnFrameworkWebserviceConstant.SYS_ID;
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = changePropertyForSystemId(dto.getPropertyId());
			order = " " + dto.getOrderByCriteria();
		}
		return getRSColumns(property) + order;
	}

	private String getRSColumns(String property) {
		String columnName = commonLogic.getRsColumns(property);
		if (columnName == null) {
			columnName = "rs.RS_MODEL_SID";
		}
		return columnName;
	}

	public GtnUIFrameworkWebserviceResponse getRulesLookupTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDRulesPopupCount"
					: "getCDRulesPopupResults";
			List<Object> inputlist = getRuleSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			String whereCond = inputlist.get(0).toString();
			whereCond = whereCond.trim().isEmpty() ? whereCond : GtnFrameworkWebserviceConstant.WHERE + whereCond;
			inputlist.set(0, whereCond);
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getRulesLookupTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getRulesLookupTableData", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse getRuleDetailsLookupTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDRuleDetailsPopupCount"
					: "getCDRuleDetailsPopupResults";
			List<Object> inputlist = getRuleSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			String whereCond = inputlist.get(0).toString();
			whereCond = whereCond.trim().isEmpty() ? whereCond : GtnFrameworkWebserviceConstant.AND_COLUMN + whereCond;
			whereCond = " WHERE cdrd.CDR_MODEL_SID='" + gtnWsRequest.getGtnWsGeneralRequest().getExtraParameter() + "'"
					+ whereCond;
			inputlist.set(0, whereCond);
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getRuleDetailsLookupTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getRuleDetailsLookupTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getRuleSearchInput(GtnWsSearchRequest searchRequest) throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		try {
			String and = "";
			for (int i = 0; i < searchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria ruleSearchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(i);
				String value = ruleSearchCriteria.getFilterValue1();
				if (ruleSearchCriteria.isFilter() && "LIKE".equalsIgnoreCase(ruleSearchCriteria.getExpression())) {
					value = "%" + value + "%";
				}
				inputWhereConditions.append(and)
						.append(getController().getWhereClauseForAColumn(ruleSearchCriteria.getExpression(),
								getRuleColumns(ruleSearchCriteria.getFieldId()), value, ruleSearchCriteria.getFilterValue2()));
				and = GtnFrameworkWebserviceConstant.AND_COLUMN;
			}
			list.add(inputWhereConditions);
			if (!searchRequest.isCount()) {
				list.add(getRuleSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getRuleSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getRuleSearchInput: ", ex);

		}
		return list;
	}

	private String getRuleSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = "ruleType";
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = dto.getPropertyId();
			order = " " + dto.getOrderByCriteria();
		}
		return getRuleColumns(property) + order;
	}

	private String getRuleColumns(String property) {
		String columnName = commonLogic.getRuleColumns(property);
		if (columnName == null) {
			columnName = GtnFrameworkWebserviceConstant.HT_DESCRIPTION;
		}
		return columnName;
	}

	public GtnUIFrameworkWebserviceResponse getRPLookupTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDRebatePlanPopupCount"
					: "getCDRebatePlanPopupResults";
			List<Object> inputlist = getRPSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			String catalog = getController().getSysSchemaCatalog();
			inputlist.add(0, catalog);
			inputlist.add(0, catalog);
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getRPLookupTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getRPLookupTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getRPSearchInput(GtnWsSearchRequest searchRequest) throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		String where = GtnFrameworkWebserviceConstant.WHERE;
		String and = "";
		try {
			for (int i = 0; i < searchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria rpSearchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(i);
				String value = rpSearchCriteria.getFilterValue1();
				if (rpSearchCriteria.isFilter() && "LIKE".equalsIgnoreCase(rpSearchCriteria.getExpression())) {
					value = "%" + value + "%";
				}
				inputWhereConditions.append(where).append(and)
						.append(getController().getWhereClauseForAColumn(rpSearchCriteria.getExpression(),
								getRPColumns(rpSearchCriteria.getFieldId()), value, rpSearchCriteria.getFilterValue2()));
				where = "";
				and = GtnFrameworkWebserviceConstant.AND_COLUMN;
			}
			list.add(inputWhereConditions);
			if (!searchRequest.isCount()) {
				list.add(getRPSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getRPSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getRPSearchInput : ", ex);

		}
		return list;
	}

	private String getRPSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = "CDRPNoView_RPType";
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = dto.getPropertyId();
			order = " " + dto.getOrderByCriteria();
		}
		return getRPColumns(property) + order;
	}

	private String getRPColumns(String property) {
		String columnName = commonLogic.getRpColumns(property);
		if (columnName == null) {
			columnName = "RP.REBATE_PLAN_TYPE";
		}
		return columnName;
	}

	public GtnUIFrameworkWebserviceResponse getDCLookupTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? "getCDDeductCalPopupCount"
					: "getCDDeductCalPopupResults";
			List<Object> inputlist = getDCSearchInput(gtnWsRequest.getGtnWsSearchRequest());
			String catalog = getController().getSysSchemaCatalog();
			inputlist.add(0, catalog);
			inputlist.add(0, catalog);
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (Exception ex) {
			logger.error("Exception in getDCLookupTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getDCLookupTableData", ex);
		}
		return gtnResponse;
	}

	private List<Object> getDCSearchInput(GtnWsSearchRequest searchRequest) throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();
		StringBuilder inputWhereConditions = new StringBuilder();
		try {
			for (int i = 0; i < searchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria dcSearchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(i);
				String value = dcSearchCriteria.getFilterValue1();
				if (dcSearchCriteria.isFilter() && "LIKE".equalsIgnoreCase(dcSearchCriteria.getExpression())) {
					value = "%" + value + "%";
				}
				inputWhereConditions.append(GtnFrameworkWebserviceConstant.AND_COLUMN)
						.append(getController().getWhereClauseForAColumn(dcSearchCriteria.getExpression(),
								getDCColumns(dcSearchCriteria.getFieldId()), value, dcSearchCriteria.getFilterValue2()));

			}
			list.add(inputWhereConditions);
			if (!searchRequest.isCount()) {
				list.add(getDCSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList()));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in getDCSearchInput", ex);
			throw new GtnFrameworkGeneralException("Error in getDCSearchInput : ", ex);

		}
		return list;
	}

	private String getDCSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList) {
		String property = "dcNo";
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			property = dto.getPropertyId();
			order = " " + dto.getOrderByCriteria();
		}
		return getDCColumns(property) + order;
	}

	private String getDCColumns(String property) {
		String columnName = commonLogic.getDcColumns(property);
		if (columnName == null) {
			columnName = "DCM.DEDUCTION_CALENDAR_NO";
		}
		return columnName;
	}

	public GtnUIFrameworkWebserviceResponse getFormulaLookupTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
			if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
				gtnSerachResponse.setCount(0);
			} else {
				GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
				gtnUIFrameworkDataTable.addData(new ArrayList<Object[]>());
				gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
			}
			gtnResponse.setGtnSerachResponse(gtnSerachResponse);
		} catch (Exception ex) {
			logger.error("Exception in getFormulaLookupTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getFormulaLookupTableData", ex);
		}
		return gtnResponse;
	}

}
