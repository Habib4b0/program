/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.entity.companyfamilyplan.CfpModel;
import com.stpl.gtn.gtn2o.ws.entity.contract.CfpContract;
import com.stpl.gtn.gtn2o.ws.entity.contract.ContractMaster;
import com.stpl.gtn.gtn2o.ws.entity.contract.IfpContract;
import com.stpl.gtn.gtn2o.ws.entity.contract.PsContract;
import com.stpl.gtn.gtn2o.ws.entity.contract.RsContract;
import com.stpl.gtn.gtn2o.ws.entity.contract.RsContractDetails;
import com.stpl.gtn.gtn2o.ws.entity.itemfamilyplan.IfpModel;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemMaster;
import com.stpl.gtn.gtn2o.ws.entity.priceshedule.PsDetails;
import com.stpl.gtn.gtn2o.ws.entity.priceshedule.PsModel;
import com.stpl.gtn.gtn2o.ws.entity.rebateschedule.RsDetails;
import com.stpl.gtn.gtn2o.ws.entity.rebateschedule.RsModel;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractDashboardRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.contract.GtnWsContractDashboardResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnWsContractDashboardLogic {

	private static final String CM_COMPANY_NAME = "cm.COMPANY_NAME";
	private static final String CFP_CONTRACT = "cfpContract";
	private static final String CM_COMPANY_NO = "cm.COMPANY_NO";
	private static final String CONTRACT_MASTER = "contractMaster";
	private static final String GET_CDL = "getCDL";
	private static final String HT_DESCRIPTION = "ht.DESCRIPTION";
	private static final String HT_STATUS_DESCRIPTION = "htStatus.DESCRIPTION";
	private static final String IFP_CONTRACT = "ifpContract";
	private static final String IFP_MODEL = "ifpModel";
	private static final String IM_ITEM_NAME = "im.ITEM_NAME";
	private static final String IM_ITEM_NO = "im.ITEM_NO";
	private static final String INBOUND_STATUS = "inboundStatus";
	private static final String RS_MODEL = "rsModel";
	private static final String CON_CONTRACT_ID = "con.CONTRACT_ID";
	private static final String CON_CONTRACT_NO = "con.CONTRACT_NO";
	private static final String CON_CONTRACT_NAME = "con.CONTRACT_NAME";
	private static final String CF_CFP_ID = "cf.CFP_ID";
	private static final String CF_CFP_NO = "cf.CFP_NO";
	private static final String CF_CFP_NAME = "cf.CFP_NAME";
	private static final String IFM_IFP_ID = "ifm.IFP_ID";
	private static final String IFM_IFP_NO = "ifm.IFP_NO";
	private static final String IFM_IFP_NAME = "ifm.IFP_NAME";
	private static final String PS_PS_ID = "ps.PS_ID";
	private static final String PS_PS_NO = "ps.PS_NO";
	private static final String PS_PS_NAME = "ps.PS_NAME";
	private static final String RSM_RS_ID = "rsm.RS_ID";
	private static final String RSM_RS_NO = "rsm.RS_NO";
	private static final String RSM_RS_NAME = "rsm.RS_NAME";
	private static final String NO3 = "no3";
	private static final String NAME3 = "name3";
	private static final String STATUS3 = "status3";
	private static final String STARTDATE3 = "startDate3";
	private static final String ENDDATE3 = "endDate3";
	private static final String NO4 = "no4";
	private static final String NAME4 = "name4";
	private static final String STATUS4 = "status4";
	private static final String STARTDATE4 = "startDate4";
	private static final String ENDDATE4 = "endDate4";
	private static final String NO5 = "no5";
	private static final String NAME5 = "name5";
	private static final String STATUS5 = "status5";
	private static final String STARTDATE5 = "startDate5";
	private static final String ENDDATE5 = "endDate5";
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsContractDashboardLogic.class);
	private final GtnWsContractDashboardController controller;
	private final Map<String, String> filterAndSortingCriteriaMap = new HashMap<>();
	private final Map<String, String> searchCriteriaMap = new HashMap<>();
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private final GtnWsContractDashboardCommonLogic commonLogic;
	private final String currentDate = dateFormat.format(new Date());

	public GtnWsContractDashboardLogic(GtnWsContractDashboardController controller) {
		this.controller = controller;
		this.commonLogic = new GtnWsContractDashboardCommonLogic();
	}

	public GtnWsContractDashboardController getController() {
		return controller;
	}

	@SuppressWarnings("rawtypes")
	public String getQuery(List input, String queryName) {
		return getController().getQuery(input, queryName);
	}

	public String getQuery(String queryName) {
		return getController().getQuery(queryName);
	}

	public String getWhereClauseForAColumn(String expersion, String field, String value1, String value2)
			throws GtnFrameworkGeneralException {
		return getController().getWhereClauseForAColumn(expersion, field, value1, value2);
	}

	public GtnUIFrameworkWebserviceResponse getContractDashboardTableData(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse) throws GtnFrameworkGeneralException {
		try {
			GtnWebServiceSearchCriteria searchCriteria = gtnWsRequest.getGtnWsSearchRequest()
					.getGtnWebServiceSearchCriteriaList().get(0);
			String comp = GtnWsContractDashboardContants.getComponentMappedValue(searchCriteria.getFilterValue1());
			String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? GET_CDL + comp + "Count"
					: GET_CDL + comp + "Results";
			List<Object> inputlist = getSearchInput(gtnWsRequest.getGtnWsSearchRequest(), comp, true);
			commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
		} catch (GtnFrameworkGeneralException | NumberFormatException ex) {
			logger.error("Exception in getContractDashboardTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getContractDashboardTableData", ex);
		}
		return gtnResponse;
	}

	public GtnUIFrameworkWebserviceResponse getContractDashboardDetailsTableData(
			GtnUIFrameworkWebserviceRequest gtnWsRequest, GtnUIFrameworkWebserviceResponse gtnResponse)
			throws GtnFrameworkGeneralException {
		try {
			if (!gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria searchCriteria = gtnWsRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(0);
				String comp = GtnWsContractDashboardContants.getComponentMappedValue(searchCriteria.getFilterValue1());
				String queryName = gtnWsRequest.getGtnWsSearchRequest().isCount() ? GET_CDL + comp + "DetailsCount"
						: GET_CDL + comp + "DetailsResults";
				List<Object> inputlist = getSearchInput(gtnWsRequest.getGtnWsSearchRequest(), comp, true);
				commonLogic.setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName);
			}
			if (gtnResponse.getGtnSerachResponse() == null) {
				gtnResponse.setGtnSerachResponse(new GtnSerachResponse());
			}
		} catch (GtnFrameworkGeneralException | NumberFormatException ex) {
			logger.error("Exception in getContractDashboardDetailsTableData", ex);
			throw new GtnFrameworkGeneralException("Exception in getContractDashboardDetailsTableData", ex);
		}
		return gtnResponse;
	}

	public List<Object> getSearchInput(GtnWsSearchRequest searchRequest, String comp, boolean leftSearch)
			throws GtnFrameworkGeneralException {
		List<Object> list = new ArrayList<>();

		StringBuilder inputWhereConditions = new StringBuilder();
		String and = " AND ";
		String where = " ";
		boolean details = false;
		try {
			for (int i = 1; i < searchRequest.getGtnWebServiceSearchCriteriaList().size(); i++) {
				GtnWebServiceSearchCriteria searchCriteria = searchRequest.getGtnWebServiceSearchCriteriaList().get(i);

				if (searchCriteria.isFilter()) {
					StringBuilder value = new StringBuilder(searchCriteria.getFilterValue1());
					if ("LIKE".equalsIgnoreCase(searchCriteria.getExpression())) {
						value.append("%" + value + "%");
					}
					inputWhereConditions.append(where).append(and)
							.append(getWhereClauseForAColumn(searchCriteria.getExpression(),
									filterAndSortingCriteriaMap().get(searchCriteria.getFieldId() + comp),
									value.toString(), searchCriteria.getFilterValue2()));
				} else if (searchCriteria.getFieldId().contains("hiddenID")) {
					details = true;
					inputWhereConditions.append(where).append(and)
							.append(searchCriteriaMap().get(searchCriteria.getFieldId() + comp)).append(" = ")
							.append(searchCriteria.getFilterValue1());
				} else {
					addInputWhereConditions(inputWhereConditions, searchCriteria, comp, leftSearch, and, where);
				}
				and = " AND ";
				where = "";
			}

			list.add(inputWhereConditions);

			if (!searchRequest.isCount()) {
				list.addAll(getSortedInputs(searchRequest.getGtnWebServiceOrderByCriteriaList(), comp, details,
						leftSearch));
				list.add(searchRequest.getTableRecordStart());
				list.add(searchRequest.getTableRecordOffset());
			}
		} catch (Exception ex) {
			logger.error("Exception in executig query-", ex);
			throw new GtnFrameworkGeneralException("Error in executing query : ", ex);

		}
		return list;
	}

	void addInputWhereConditions(StringBuilder inputWhereConditions, GtnWebServiceSearchCriteria searchCriteria,
			String comp, boolean leftSearch, String and, String where) {
		String postFix = "";
		if (!leftSearch && Integer.parseInt(comp.trim()) > 3
				&& (searchCriteria.getFieldId().contains("ifpItemNo")
						|| searchCriteria.getFieldId().contains("ifpItemName")
						|| searchCriteria.getFieldId().contains("ifpTherapeuticClass")
						|| searchCriteria.getFieldId().contains("ifpBrandName"))) {
			postFix = comp;
		}
		String searchQuery = searchCriteriaMap().get(searchCriteria.getFieldId() + postFix);
		StringBuilder value = new StringBuilder(" '" + searchCriteria.getFilterValue1().replace('*', '%') + "' ");
		String expression = " " + searchCriteria.getExpression().replace("EQUALS", "=");
		if (searchQuery.contains("in (")) {
			value.append(value + ")");
		}
		inputWhereConditions.append(where).append(and).append(searchQuery).append(expression).append(value);
	}

	private List<Object> getSortedInputs(List<GtnWebServiceOrderByCriteria> gtnWebServiceOrderByCriteriaList,
			String comp, boolean details, boolean leftSearch) {
		List<Object> list = new ArrayList<>();
		String fieldId = "RIGHTORDERID";
		String order = " ASC";
		if (gtnWebServiceOrderByCriteriaList != null && !gtnWebServiceOrderByCriteriaList.isEmpty()) {
			GtnWebServiceOrderByCriteria dto = gtnWebServiceOrderByCriteriaList.get(0);
			fieldId = dto.getPropertyId() + comp;
			order = " " + dto.getOrderByCriteria();
		} else if (details) {
			fieldId = "ORDERID" + comp;
		} else if (leftSearch) {
			fieldId = GtnWsContractDashboardContants.MEMBER_ID + comp;
		}
		list.add(filterAndSortingCriteriaMap().get(fieldId) + order);
		return list;
	}

	private Map<String, String> filterAndSortingCriteriaMap() {
		if (filterAndSortingCriteriaMap.isEmpty()) {
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_ID + "1", CON_CONTRACT_ID);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_NO + "1", CON_CONTRACT_NO);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_NAME + "1", CON_CONTRACT_NAME);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_TYPE + "1", HT_DESCRIPTION);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_ID + "2", CF_CFP_ID);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_NO + "2", CF_CFP_NO);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_NAME + "2", CF_CFP_NAME);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_TYPE + "2", HT_DESCRIPTION);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_ID + "3", IFM_IFP_ID);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_NO + "3", IFM_IFP_NO);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_NAME + "3", IFM_IFP_NAME);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_TYPE + "3", HT_DESCRIPTION);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_ID + "4", PS_PS_ID);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_NO + "4", PS_PS_NO);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_NAME + "4", PS_PS_NAME);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_TYPE + "4", HT_DESCRIPTION);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_ID + "5", RSM_RS_ID);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_NO + "5", RSM_RS_NO);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_NAME + "5", RSM_RS_NAME);
			filterAndSortingCriteriaMap.put(GtnWsContractDashboardContants.MEMBER_TYPE + "5", HT_DESCRIPTION);
			filterAndSortingCriteriaMap.put("no2", CM_COMPANY_NO);
			filterAndSortingCriteriaMap.put("name2", CM_COMPANY_NAME);
			filterAndSortingCriteriaMap.put("type2", "htType.DESCRIPTION");
			filterAndSortingCriteriaMap.put("category2", "htCategory.DESCRIPTION");
			filterAndSortingCriteriaMap.put("status2", HT_STATUS_DESCRIPTION);
			filterAndSortingCriteriaMap.put(NO3, IM_ITEM_NO);
			filterAndSortingCriteriaMap.put(NAME3, IM_ITEM_NAME);
			filterAndSortingCriteriaMap.put(STATUS3, HT_STATUS_DESCRIPTION);
			filterAndSortingCriteriaMap.put(STARTDATE3, "ifm.IFP_START_DATE");
			filterAndSortingCriteriaMap.put(ENDDATE3, "ifm.IFP_END_DATE");
			filterAndSortingCriteriaMap.put(NO4, IM_ITEM_NO);
			filterAndSortingCriteriaMap.put(NAME4, IM_ITEM_NAME);
			filterAndSortingCriteriaMap.put(STATUS4, HT_STATUS_DESCRIPTION);
			filterAndSortingCriteriaMap.put(STARTDATE4, "ps.PS_START_DATE");
			filterAndSortingCriteriaMap.put(ENDDATE4, "ps.PS_END_DATE");
			filterAndSortingCriteriaMap.put(NO5, IM_ITEM_NO);
			filterAndSortingCriteriaMap.put(NAME5, IM_ITEM_NAME);
			filterAndSortingCriteriaMap.put(STATUS5, HT_STATUS_DESCRIPTION);
			filterAndSortingCriteriaMap.put(STARTDATE5, "rs.RS_START_DATE");
			filterAndSortingCriteriaMap.put(ENDDATE5, "rs.RS_END_DATE");

			filterAndSortingCriteriaMap.put("ORDERID2", "cfd.CFP_DETAILS_SID");
			filterAndSortingCriteriaMap.put("ORDERID3", "ifd.IFP_DETAILS_SID");
			filterAndSortingCriteriaMap.put("ORDERID4", "psd.PS_DETAILS_SID");
			filterAndSortingCriteriaMap.put("ORDERID5", "rsd.RS_DETAILS_SID");
			filterAndSortingCriteriaMap.put("RIGHTORDERID", "cm.CONTRACT_MASTER_SID");

		}
		return filterAndSortingCriteriaMap;
	}

	private Map<String, String> searchCriteriaMap() {
		if (searchCriteriaMap.isEmpty()) {
			searchCriteriaMap.put("CDMainView_L1S_L1D_R1S_R1D_Text_ContractID", CON_CONTRACT_ID);
			searchCriteriaMap.put("CDMainView_L1S_L1D_R1S_R1D_Text_ContractNo", CON_CONTRACT_NO);
			searchCriteriaMap.put("CDMainView_L1S_L1D_R1S_R1D_Text_ContractName", CON_CONTRACT_NAME);
			searchCriteriaMap.put("CDMainView_L1S_L1D_R1S_R1D_Combo_ContractType", "con.CONTRACT_TYPE");
			searchCriteriaMap.put("CDMainView_L1D_R1D_ContractStartDate", "con.START_DATE");
			searchCriteriaMap.put("CDMainView_L1D_R1D_ContractEndDate", "con.END_DATE");
			searchCriteriaMap.put("CDMainView_L1D_Text_ContractHolderNo", CM_COMPANY_NO);
			searchCriteriaMap.put("CDMainView_L1D_Text_ContractHolderName", CM_COMPANY_NAME);

			searchCriteriaMap.put("CDMainView_L2S_L2D_R2S_R2D_R1D_Text_cfpID", CF_CFP_ID);
			searchCriteriaMap.put("CDMainView_L2S_L2D_R2S_R2D_R1D_Text_cfpNo", CF_CFP_NO);
			searchCriteriaMap.put("CDMainView_L2S_L2D_R2S_R2D_R1D_Text_cfpName", CF_CFP_NAME);
			searchCriteriaMap.put("CDMainView_L2S_L2D_R2S_R2D_Combo_cfpType", "cf.CFP_TYPE");

			searchCriteriaMap.put("CDMainView_L2D_R2D_R1D_Text_cfpCompanyNo", CM_COMPANY_NO);
			searchCriteriaMap.put("CDMainView_L2D_R2D_R1D_Text_cfpCompanyName", CM_COMPANY_NAME);
			searchCriteriaMap.put("CDMainView_L2D_R2D_Combo_cfpCompanyType", "cm.COMPANY_TYPE");
			searchCriteriaMap.put("CDMainView_L2D_R2D_Text_cfpCompanyCategory", "cm.COMPANY_CATEGORY");

			searchCriteriaMap.put("CDMainView_L3S_L3D_R3S_R3D_R1D_Text_ifpID", IFM_IFP_ID);
			searchCriteriaMap.put("CDMainView_L3S_L3D_R3S_R3D_R1D_Text_ifpNo", IFM_IFP_NO);
			searchCriteriaMap.put("CDMainView_L3S_L3D_R3S_R3D_R1D_Text_ifpName", IFM_IFP_NAME);
			searchCriteriaMap.put("CDMainView_L3S_L3D_R3S_R3D_Combo_ifpType", "ifm.IFP_TYPE");

			searchCriteriaMap.put("CDMainView_L3D_L4D_L5D_R3D_R4D_R5D_R1D_Text_ifpItemNo", IM_ITEM_NO);
			searchCriteriaMap.put("CDMainView_L3D_L4D_L5D_R3D_R4D_R5D_R1D_Text_ifpItemName", IM_ITEM_NAME);
			searchCriteriaMap.put("CDMainView_L3D_L4D_R3D_R4D_Combo_ifpTherapeuticClass", "im.THERAPEUTIC_CLASS");
			searchCriteriaMap.put("CDMainView_L3D_L4D_L5D_R3D_R4D_R5D_Combo_ifpBrandName", "im.BRAND_MASTER_SID");

			searchCriteriaMap.put("CDMainView_L4S_L4D_R4S_R4D_R1D_Text_psID", PS_PS_ID);
			searchCriteriaMap.put("CDMainView_L4S_L4D_R4S_R4D_R1D_Text_psNo", PS_PS_NO);
			searchCriteriaMap.put("CDMainView_L4S_L4D_R4S_R4D_R1D_Text_psName", PS_PS_NAME);
			searchCriteriaMap.put("CDMainView_L4S_L4D_R4S_R4D_Combo_psType", "ps.PS_TYPE");

			searchCriteriaMap.put("CDMainView_L5S_L5D_R5S_R5D_R1D_Text_rsID", RSM_RS_ID);
			searchCriteriaMap.put("CDMainView_L5S_L5D_R5S_R5D_R1D_Text_rsNo", RSM_RS_NO);
			searchCriteriaMap.put("CDMainView_L5S_L5D_R5S_R5D_R1D_Text_rsName", RSM_RS_NAME);
			searchCriteriaMap.put("CDMainView_L5S_L5D_R5S_R5D_Combo_rsType", "rsm.RS_TYPE");

			searchCriteriaMap.put("CDMainView_L5D_R5D_Combo_rsProgramCategory",
					"rsm.RS_MODEL_SID in (select distinct master_sid from udcs where master_type='RS_MODEL' and udc2");
			searchCriteriaMap.put("CDMainView_hiddenID2", "cfd.CFP_MODEL_SID");
			searchCriteriaMap.put("CDMainView_hiddenID3", "ifd.IFP_MODEL_SID");
			searchCriteriaMap.put("CDMainView_hiddenID4", "psd.PS_MODEL_SID");
			searchCriteriaMap.put("CDMainView_hiddenID5", "rsd.RS_MODEL_SID");
			searchCriteriaMap.put("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractID", "cm.CONTRACT_ID");
			searchCriteriaMap.put("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractNo", "cm.CONTRACT_NO");
			searchCriteriaMap.put("RIGHTCDMainView_L1S_L1D_R1S_R1D_Text_ContractName", "cm.CONTRACT_NAME");
			searchCriteriaMap.put("RIGHTCDMainView_L1S_L1D_R1S_R1D_Combo_ContractType", "cm.CONTRACT_TYPE");
			searchCriteriaMap.put("RIGHTCDMainView_L1D_R1D_ContractStartDate", "cm.START_DATE");
			searchCriteriaMap.put("RIGHTCDMainView_L1D_R1D_ContractEndDate", "cm.END_DATE");
			searchCriteriaMap.put("RIGHTCDMainView_L2S_L2D_R2S_R2D_R1D_Text_cfpID",
					"cfc.CFP_MODEL_SID in (select cfid.CFP_MODEL_SID from CFP_MODEL cfid where cfid.CFP_ID");
			searchCriteriaMap.put("RIGHTCDMainView_L2S_L2D_R2S_R2D_R1D_Text_cfpNo",
					"cfc.CFP_MODEL_SID in (select cfno.CFP_MODEL_SID from CFP_MODEL cfno where cfno.CFP_NO");
			searchCriteriaMap.put("RIGHTCDMainView_L2S_L2D_R2S_R2D_R1D_Text_cfpName", "cfc.CFP_NAME");
			searchCriteriaMap.put("RIGHTCDMainView_R1D_Text_cfpCompanyID", "cfpcomp.COMPANY_ID");
			searchCriteriaMap.put("RIGHTCDMainView_L2D_R2D_R1D_Text_cfpCompanyNo", "cfpcomp.COMPANY_NO");
			searchCriteriaMap.put("RIGHTCDMainView_L2D_R2D_R1D_Text_cfpCompanyName", "cfpcomp.COMPANY_NAME");
			searchCriteriaMap.put("RIGHTCDMainView_L3S_L3D_R3S_R3D_R1D_Text_ifpID",
					"ifc.IFP_MODEL_SID in (select ifid.IFP_MODEL_SID from IFP_MODEL ifid where ifid.IFP_ID");
			searchCriteriaMap.put("RIGHTCDMainView_L3S_L3D_R3S_R3D_R1D_Text_ifpNo",
					"ifc.IFP_MODEL_SID in (select ifno.IFP_MODEL_SID from IFP_MODEL ifno where ifno.IFP_NO");
			searchCriteriaMap.put("RIGHTCDMainView_L3S_L3D_R3S_R3D_R1D_Text_ifpName", "ifc.IFP_NAME");
			searchCriteriaMap.put("RIGHTCDMainView_R1D_Text_ifpItemID", "ifpIm.ITEM_ID");
			searchCriteriaMap.put("RIGHTCDMainView_L4S_L4D_R4S_R4D_R1D_Text_psID",
					"psc.PS_MODEL_SID in (select psid.PS_MODEL_SID from PS_MODEL psid where psid.PS_ID");
			searchCriteriaMap.put("RIGHTCDMainView_L4S_L4D_R4S_R4D_R1D_Text_psNo",
					"psc.PS_MODEL_SID in (select psno.PS_MODEL_SID from PS_MODEL psno where psno.PS_ID");
			searchCriteriaMap.put("RIGHTCDMainView_L4S_L4D_R4S_R4D_R1D_Text_psName", "psc.PS_NAME");
			searchCriteriaMap.put("RIGHTCDMainView_L5S_L5D_R5S_R5D_R1D_Text_rsID", "rsc.RS_ID");
			searchCriteriaMap.put("RIGHTCDMainView_L5S_L5D_R5S_R5D_R1D_Text_rsNo", "rsc.RS_NO");
			searchCriteriaMap.put("RIGHTCDMainView_L5S_L5D_R5S_R5D_R1D_Text_rsName", "rsc.RS_NAME");
			searchCriteriaMap.put("RIGHTCDMainView_L3D_L4D_L5D_R3D_R4D_R5D_R1D_Text_ifpItemNo", "ifpIm.ITEM_NO");
			searchCriteriaMap.put("RIGHTCDMainView_L3D_L4D_L5D_R3D_R4D_R5D_R1D_Text_ifpItemName", "ifpIm.ITEM_NAME");

			searchCriteriaMap.put("RIGHTCDMainView_L2S_L2D_R2S_R2D_Combo_cfpType", "cfc.CFP_TYPE");
			searchCriteriaMap.put("RIGHTCDMainView_L2D_R2D_Combo_cfpCompanyType", "cfpcomp.COMPANY_TYPE");
			searchCriteriaMap.put("RIGHTCDMainView_L2D_R2D_Text_cfpCompanyCategory", "cfpcomp.COMPANY_CATEGORY");

			searchCriteriaMap.put("RIGHTCDMainView_L3S_L3D_R3S_R3D_Combo_ifpType", "ifc.IFP_TYPE");
			searchCriteriaMap.put("RIGHTCDMainView_L3D_L4D_R3D_R4D_Combo_ifpTherapeuticClass",
					"ifpIm.THERAPEUTIC_CLASS");
			searchCriteriaMap.put("RIGHTCDMainView_L3D_L4D_L5D_R3D_R4D_R5D_Combo_ifpBrandName",
					"ifpIm.BRAND_MASTER_SID");

			searchCriteriaMap.put("RIGHTCDMainView_L4S_L4D_R4S_R4D_Combo_psType", "psc.PS_TYPE");
			searchCriteriaMap.put("RIGHTCDMainView_L3D_L4D_L5D_R3D_R4D_R5D_R1D_Text_ifpItemNo4", "psIm.ITEM_NO");
			searchCriteriaMap.put("RIGHTCDMainView_L3D_L4D_L5D_R3D_R4D_R5D_R1D_Text_ifpItemName4", "psIm.ITEM_NAME");
			searchCriteriaMap.put("RIGHTCDMainView_L3D_L4D_R3D_R4D_Combo_ifpTherapeuticClass4",
					"psIm.THERAPEUTIC_CLASS");
			searchCriteriaMap.put("RIGHTCDMainView_L3D_L4D_L5D_R3D_R4D_R5D_Combo_ifpBrandName4",
					"psIm.BRAND_MASTER_SID");

			searchCriteriaMap.put("RIGHTCDMainView_L5S_L5D_R5S_R5D_Combo_rsType", "rsc.RS_TYPE");
			searchCriteriaMap.put("RIGHTCDMainView_L3D_L4D_L5D_R3D_R4D_R5D_R1D_Text_ifpItemNo5",
					"rsd.ITEM_MASTER_SID in (select rsdimid.ITEM_MASTER_SID from ITEM_MASTER rsdimid where rsdimid.ITEM_ID");
			searchCriteriaMap.put("RIGHTCDMainView_L3D_L4D_L5D_R3D_R4D_R5D_R1D_Text_ifpItemName5",
					"rsd.ITEM_MASTER_SID in (select rsdimid.ITEM_MASTER_SID from ITEM_MASTER rsdimid where rsdimid.ITEM_NAME");
			searchCriteriaMap.put("RIGHTCDMainView_L3D_L4D_L5D_R3D_R4D_R5D_Combo_ifpBrandName5",
					"rsd.ITEM_MASTER_SID in (select rsdBrnd.ITEM_MASTER_SID from ITEM_MASTER rsdBrnd where rsdBrnd.BRAND_MASTER_SID");
			searchCriteriaMap.put("RIGHTCDMainView_L5D_R5D_Combo_rsProgramCategory",
					"rsc.RS_CONTRACT_SID in (select distinct master_sid from udcs where master_type='RS_CONTRACT' and udc2");

		}
		return searchCriteriaMap;
	}

	private long getUpdatedLongDate(long time) {
		long timeInMilliSec = time;
		if (timeInMilliSec != 0L) {
			Calendar trCStDate = Calendar.getInstance();
			trCStDate.setTimeInMillis(timeInMilliSec);
			trCStDate.set(trCStDate.get(Calendar.YEAR), trCStDate.get(Calendar.MONTH), trCStDate.get(Calendar.DATE), 0,
					0, 0);
			timeInMilliSec = trCStDate.getTimeInMillis();
		}
		return timeInMilliSec;
	}

	public void checkAddToTree(GtnWsContractDashboardRequest cdRequest, GtnWsContractDashboardResponse cdResponse)
			throws GtnFrameworkGeneralException {
		cdResponse.setSuccess(true);
		cdResponse.setTableBean(cdRequest.getTableBean());
		cdResponse.setTreeBean(cdRequest.getTreeBean());
		String tableCategory = cdResponse.getTableBean().getStringPropertyByIndex(8);
		String treeCategory = cdResponse.getTreeBean().getStringPropertyByIndex(8);
		long treeEndDate = getUpdatedLongDate(cdResponse.getTreeBean().getLongPropertyByIndex(6));
		long treeStartDate = getUpdatedLongDate(cdResponse.getTreeBean().getLongPropertyByIndex(5));
		long tableEndDate = getUpdatedLongDate(cdResponse.getTableBean().getLongPropertyByIndex(6));
		long tableStartDate = getUpdatedLongDate(cdResponse.getTableBean().getLongPropertyByIndex(5));

		if (treeEndDate != 0L && tableStartDate > treeEndDate) {
			cdResponse.setSuccess(false);
			cdResponse.setMessage("The " + tableCategory + " Start and End Date must be within the " + treeCategory
					+ " Start and End Date.");
			cdResponse.setMessageHeader(GtnFrameworkCommonStringConstants.INFO);
			cdResponse.setMessageType(GtnFrameworkCommonStringConstants.WARNING);
			return;
		}
		if (tableEndDate != 0L && tableEndDate < treeStartDate) {
			cdResponse.setSuccess(false);
			cdResponse.setMessage("The " + tableCategory + " End Date is less than " + treeCategory + " Start Date.");
			cdResponse.setMessageHeader(GtnFrameworkCommonStringConstants.INFO);
			cdResponse.setMessageType(GtnFrameworkCommonStringConstants.WARNING);
			return;
		}

		if (tableStartDate < treeStartDate) {
			cdResponse.getTableBean().getProperties().set(5, cdResponse.getTreeBean().getProperties().get(5));
		}
		if (tableEndDate == 0L && treeEndDate != 0L) {
			tableEndDate = treeEndDate;
			cdResponse.getTableBean().getProperties().set(6, cdResponse.getTreeBean().getProperties().get(6));
		}
		if (treeEndDate != 0L && tableEndDate > treeEndDate) {
			cdResponse.setSuccess(false);
			cdResponse.setMessage(cdResponse.getTableBean().getStringPropertyByIndex(8)
					+ " end date is greater.\nDo you want to proceed?");
			cdResponse.setMessageHeader(GtnFrameworkCommonStringConstants.INFO);
			cdResponse.setMessageType(GtnFrameworkCommonStringConstants.CONFIRMATION);
			cdResponse.getTableBean().getProperties().set(6, cdResponse.getTreeBean().getProperties().get(6));
			return;
		}
		cdRequest.setTableBean(cdResponse.getTableBean());
		cdRequest.setTreeBean(cdResponse.getTreeBean());
		getTreeValidationValues(cdResponse);
		addToTreeValidation(cdRequest, cdResponse);
	}

	public void addToTreeValidation(GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse) {
		StringBuilder ids = new StringBuilder();
		String comma = "";
		for (Object value : cdResponse.getValues()) {
			ids.append(comma + String.valueOf(value));
			comma = ",";
		}
		if (!cdResponse.getValues().isEmpty()) {
			cdResponse.setSuccess(false);
			cdResponse.setMessage(ids.toString() + " will be removed from "
					+ cdRequest.getTableBean().getStringPropertyByIndex(8) + ".\nDo you want to proceed?");
			cdResponse.setMessageHeader(GtnFrameworkCommonStringConstants.INFO);
			cdResponse.setMessageType(GtnFrameworkCommonStringConstants.CONFIRMATION);
		}
	}

	@SuppressWarnings("unchecked")
	public GtnWsContractDashboardResponse getTreeValidationValues(GtnWsContractDashboardResponse cdResponse)
			throws GtnFrameworkGeneralException {
		SimpleDateFormat commonDate = new SimpleDateFormat("MM/dd/yyyy");
		cdResponse.setValues(new ArrayList<>());
		List<Object> inputlist = new ArrayList<>();
		inputlist.add(cdResponse.getTableBean().getStringPropertyByIndex(4));
		long tableEndDate = cdResponse.getTableBean().getLongPropertyByIndex(6);
		Date startDate = new Date(cdResponse.getTableBean().getLongPropertyByIndex(5));
		String formattedStartDate = commonDate.format(startDate);
		inputlist.add(formattedStartDate);
		inputlist.add(formattedStartDate);
		if (tableEndDate == 0L) {
			inputlist.add("NULL");
		} else {
			String formattedEndDate = commonDate.format(new Date(tableEndDate));
			inputlist.add("'" + formattedEndDate + "'");
		}
		int level = cdResponse.getTableBean().getIntegerPropertyByIndex(7);
		List<Object> result = getController().executeQuery(getQuery(inputlist, "getCD" + level + "Validation"));
		if (result != null) {
			cdResponse.setValues(result);
		}
		return cdResponse;
	}

	@SuppressWarnings("unchecked")
	public int getPsAssociatedIfpId(int psModelId) throws GtnFrameworkGeneralException {
		IfpModel psAssocaitedIfp = null;

		try (Session session = getController().getSessionFactory().openSession()) {
			PsModel psModel = session.load(PsModel.class, psModelId);
			Criteria cr = session.createCriteria(PsDetails.class).add(Restrictions.eq("psModel", psModel))
					.add(Restrictions.ne(INBOUND_STATUS, 'D'))
					.setProjection(Projections.distinct(Projections.property(IFP_MODEL)));
			List<IfpModel> results = cr.list();
			if (results != null && !results.isEmpty()) {
				psAssocaitedIfp = results.get(0);
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getPsDetails ", e);
		}
		return psAssocaitedIfp == null ? 0 : psAssocaitedIfp.getIfpModelSid();
	}

	@SuppressWarnings("unchecked")
	public int getRsAssociatedIfpId(int rsModelId) throws GtnFrameworkGeneralException {
		IfpModel rsAssociatedIfp = null;

		try (Session session = getController().getSessionFactory().openSession()) {
			RsModel rsModel = session.load(RsModel.class, rsModelId);
			Criteria cr = session.createCriteria(RsDetails.class).add(Restrictions.eq(RS_MODEL, rsModel))
					.add(Restrictions.ne(INBOUND_STATUS, 'D'))
					.setProjection(Projections.distinct(Projections.property(IFP_MODEL)));
			List<IfpModel> results = cr.list();
			if (results != null && !results.isEmpty()) {
				rsAssociatedIfp = results.get(0);
			}
		} catch (Exception e) {
			throw new GtnFrameworkGeneralException("Exception in getPsDetails ", e);
		}
		return rsAssociatedIfp == null ? 0 : rsAssociatedIfp.getIfpModelSid();
	}

	public void doValidationWithRsOrPs(GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse, int systemId, int ifpSystemId) {
		String tableCategory = cdResponse.getTableBean().getStringPropertyByIndex(8);
		String treeCategory = cdResponse.getTreeBean().getStringPropertyByIndex(8);
		if (systemId == 0) {
			cdResponse.setSuccess(false);
			cdResponse.setMessage("No items Exists in " + tableCategory);
			cdResponse.setMessageHeader("No Items");
			cdResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
			return;
		}
		if (systemId != ifpSystemId) {
			cdResponse.setSuccess(false);
			cdResponse.setMessage(tableCategory + " does not associate with  " + treeCategory);
			cdResponse.setMessageHeader(GtnFrameworkCommonStringConstants.ERROR);
			cdResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
			return;
		}
		if (isDuplicate(cdRequest.getRecordBeanList(), cdResponse.getTableBean())) {
			cdResponse.setSuccess(false);
			cdResponse.setMessage(tableCategory + " Already Added");
			cdResponse.setMessageHeader("Duplicate Criteria");
			cdResponse.setMessageType(GtnFrameworkCommonStringConstants.WARNING);
			return;
		}
		cdResponse.getTableBean().getProperties().set(9, cdResponse.getTreeBean().getIntegerPropertyByIndex(9));
	}

	public void addToTree(GtnWsContractDashboardRequest cdRequest, GtnWsContractDashboardResponse cdResponse)
			throws GtnFrameworkGeneralException {
		cdResponse.setSuccess(true);
		cdResponse.setTableBean(cdRequest.getTableBean());
		cdResponse.setTreeBean(cdRequest.getTreeBean());
		int tableLevel = cdResponse.getTableBean().getIntegerPropertyByIndex(7);
		String tableCategory = cdResponse.getTableBean().getStringPropertyByIndex(8);
		if (tableLevel == 1) {
			cdResponse.setSuccess(false);
			cdResponse.setMessage("Cannot make a " + tableCategory + " as child node");
			cdResponse.setMessageHeader("Criteria Mismatch");
			cdResponse.setMessageType(GtnFrameworkCommonStringConstants.WARNING);
			return;
		}
		int treeLevel = cdResponse.getTreeBean().getIntegerPropertyByIndex(7);
		if (tableLevel == treeLevel) {
			String treeCategory = cdResponse.getTreeBean().getStringPropertyByIndex(8);
			cdResponse.setSuccess(false);
			cdResponse.setMessage(tableCategory + " cannot be added to " + treeCategory);
			cdResponse.setMessageHeader("Criteria Mismatch");
			cdResponse.setMessageType(GtnFrameworkCommonStringConstants.WARNING);
			return;
		}
		int tableLevelId = cdResponse.getTableBean().getIntegerPropertyByIndex(4);
		int treeLevelId = cdResponse.getTreeBean().getIntegerPropertyByIndex(4);
		if (tableLevel == 4 && treeLevel == 3) {
			int systemId = getPsAssociatedIfpId(tableLevelId);
			doValidationWithRsOrPs(cdRequest, cdResponse, systemId, treeLevelId);
			return;
		}
		if (tableLevel == 5 && treeLevel == 3) {
			int systemId = getRsAssociatedIfpId(tableLevelId);
			doValidationWithRsOrPs(cdRequest, cdResponse, systemId, treeLevelId);
			return;
		}

		if (tableLevel == 5 && treeLevel == 4) {
			int systemId = getRsAssociatedIfpId(tableLevelId);
			int ifpSystemId = getPsAssociatedIfpId(treeLevelId);
			doValidationWithRsOrPs(cdRequest, cdResponse, systemId, ifpSystemId);
			return;
		}

		if (isDuplicate(cdRequest.getRecordBeanList(), cdResponse.getTableBean())) {
			cdResponse.setSuccess(false);
			cdResponse.setMessage(tableCategory + " Already Added");
			cdResponse.setMessageHeader("Duplicate Criteria");
			cdResponse.setMessageType(GtnFrameworkCommonStringConstants.WARNING);
			return;
		}
		cdResponse.getTableBean().getProperties().set(9, cdResponse.getTreeBean().getIntegerPropertyByIndex(9));
	}
        
        private boolean isDuplicate(List<GtnWsRecordBean> nodeList, GtnWsRecordBean tableBean) {

            if (nodeList != null && !nodeList.isEmpty()) {
                Iterator<GtnWsRecordBean> itr = nodeList.iterator();
                while (itr.hasNext()) {
                    GtnWsRecordBean recordBean = itr.next();
                    if (recordBean.getIntegerPropertyByIndex(4) == tableBean.getIntegerPropertyByIndex(4)) {
                        return true;
                    }
                }
            }
            return false;
        }

	public void saveContractTree(GtnWsContractDashboardRequest cdRequest, GtnWsContractDashboardResponse cdResponse)
			throws GtnFrameworkGeneralException {
		cdResponse.setSuccess(true);
		Session session = getController().getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		StringBuilder contractName = new StringBuilder();
		String comma = "";
		for (GtnWsRecordBean bean : cdRequest.getRecordBeanList()) {
			contractName.append(comma + bean.getStringPropertyByIndex(2));
			comma = ",";
		}
		try {
			Map<String, Object> contractMap = new HashMap<>();

			saveRecursiveContractTree(session, cdRequest, cdResponse, cdRequest.getRecordBeanList(), contractMap);
			Query query = session.createSQLQuery("{call PRC_CCP_POPULATION ()}");
			query.executeUpdate();
			tx.commit();
			getController().checkAndUpdateAllrelationShip();
			cdResponse.setMessageType("success");
			cdResponse.setMessage(contractName + " has been saved successfully.");
		} catch (Exception e) {
			tx.rollback();
			cdResponse.setSuccess(false);
			cdResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
			cdResponse.setMessageHeader(GtnFrameworkCommonStringConstants.ERROR);
			cdResponse.setMessage(contractName + " has not been saved.");
			logger.error("Exception in saveContractTree", e);
			throw new GtnFrameworkGeneralException("Exception in saveContractTree ", e);
		} finally {
			session.close();
		}
	}

	private void saveRecursiveContractTree(Session session, GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse, List<GtnWsRecordBean> recordBeanList,
			Map<String, Object> contractMap) throws GtnFrameworkGeneralException {
		session.flush();
		for (GtnWsRecordBean recordBean : recordBeanList) {
			int level = recordBean.getIntegerPropertyByIndex(7);
			switch (level) {
			case 1:
				updateContractTree(session, cdRequest, cdResponse, recordBean, contractMap);
				break;
			case 2:
				saveCFPTree(session, cdRequest, cdResponse, recordBean, contractMap);
				break;
			case 3:
				saveIFPTree(session, cdRequest, cdResponse, recordBean, contractMap);
				break;
			case 4:
				savePSTree(session, cdRequest, cdResponse, recordBean, contractMap);
				break;
			case 5:
				saveRSTree(session, cdRequest, recordBean, contractMap);
				break;
			default:
			}
		}
	}

	private void updateContractTree(Session session, GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse, GtnWsRecordBean recordBean, Map<String, Object> contractMap)
			throws GtnFrameworkGeneralException {
		int levelId = recordBean.getIntegerPropertyByIndex(4);
		ContractMaster contractMaster = session.load(ContractMaster.class, levelId);
		contractMaster.setProcessStatus(Boolean.TRUE);
		contractMaster.setModifiedBy(Integer.parseInt(cdRequest.getUserId()));
		contractMaster.setModifiedDate(new Date());
		contractMaster.setSource("GTN");
		contractMaster.setInboundStatus(GtnFrameworkCommonStringConstants.INBOUND_STATUS_C.charAt(0));
		session.saveOrUpdate(contractMaster);
		if (recordBean.getChildList() != null && !recordBean.getChildList().isEmpty()) {
			contractMap.put("1", contractMaster);
			saveRecursiveContractTree(session, cdRequest, cdResponse, recordBean.getChildList(), contractMap);
			contractMap.remove("1");
		}
	}

	@SuppressWarnings("unchecked")
	private void saveCFPTree(Session session, GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse, GtnWsRecordBean recordBean, Map<String, Object> contractMap)
			throws GtnFrameworkGeneralException {
		int levelId = recordBean.getIntegerPropertyByIndex(4);
		CfpModel cfpModel = session.load(CfpModel.class, levelId);
		ContractMaster contractMaster = (ContractMaster) contractMap.get("1");
		Criteria cr = session.createCriteria(CfpContract.class).add(Restrictions.eq(CONTRACT_MASTER, contractMaster))
				.add(Restrictions.eq("cfpModel", cfpModel));
		List<CfpContract> results = cr.list();
		CfpContract cfpContract = new CfpContract();
		if (!results.isEmpty()) {
			cfpContract = session.load(CfpContract.class, results.get(0).getCfpContractSid());
		}

		cfpContract.setContractMaster(contractMaster);
		cfpContract.setCfpModel(cfpModel);
		cfpContract.setCfpName(cfpModel.getCfpName());
		cfpContract.setCfpNo(cfpModel.getCfpNo());
		cfpContract.setHelperTableByCfpType(cfpModel.getHelperTableByCfpType());
		cfpContract.setHelperTableByCfpCategory(cfpModel.getHelperTableByCfpCategory());
		cfpContract.setCfpDesignation(cfpModel.getCfpDesignation());
		cfpContract.setParentCfpId(cfpModel.getParentCfpId());
		cfpContract.setParentCfpName(cfpModel.getParentCfpName());
		cfpContract.setHelperTableByCfpStatus(cfpModel.getHelperTableByCfpStatus());
		cfpContract.setHelperTableByCfpTradeClass(cfpModel.getHelperTableByCfpTradeClass());
		cfpContract.setCfpStartDate(new Date(recordBean.getLongPropertyByIndex(5)));
		long endDate = recordBean.getLongPropertyByIndex(6);
		if (endDate != 0L) {
			cfpContract.setCfpEndDate(new Date(endDate));
		}
		cfpContract.setCreatedBy(Integer.parseInt(cdRequest.getUserId()));
		cfpContract.setCreatedDate(new Date());
		cfpContract.setModifiedBy(Integer.parseInt(cdRequest.getUserId()));
		cfpContract.setModifiedDate(new Date());
		cfpContract.setCfpContractAttachedDate(new Date());
		cfpContract.setRecordLockStatus(false);
		cfpContract.setInboundStatus(GtnFrameworkCommonStringConstants.INBOUND_STATUS_A.charAt(0));
		cfpContract.setSource("GTN");
		cfpContract.setSalesInclusion(cfpModel.getSalesInclusion());
		session.saveOrUpdate(cfpContract);

		List<Object> inputlist = new ArrayList<>(6);
		inputlist.add(cfpContract.getCfpContractSid());
		inputlist.add(cdRequest.getUserId());
		inputlist.add(dateFormat.format(new Date()));
		inputlist.add(cdRequest.getUserId());
		inputlist.add(dateFormat.format(new Date()));
		inputlist.add(levelId);
		String saveCfpTreeQuery = getSqlService().getQuery("com.contractDashboard.saveCFP");
		Object[] saveCfpTreeQueryParams = { dateFormat.format(cfpContract.getCfpStartDate()),
				cfpContract.getCfpEndDate() == null ? null : dateFormat.format(cfpContract.getCfpEndDate()),
				cdRequest.getUserId(), cfpContract.getCfpContractSid(), currentDate, currentDate, levelId };
		GtnFrameworkDataType[] saveCfpTreeQueryTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.NULL_ALLOWED,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
		getSqlQueryEngine().executeInsertOrUpdateQuery(saveCfpTreeQuery, saveCfpTreeQueryParams, saveCfpTreeQueryTypes,
				session);

		if (recordBean.getChildList() != null && !recordBean.getChildList().isEmpty()) {
			contractMap.put("2", cfpContract);
			saveRecursiveContractTree(session, cdRequest, cdResponse, recordBean.getChildList(), contractMap);
			contractMap.remove("2");
		}
	}

	private void saveIFPTree(Session session, GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse, GtnWsRecordBean recordBean, Map<String, Object> contractMap)
			throws GtnFrameworkGeneralException {
		int levelId = recordBean.getIntegerPropertyByIndex(4);
		IfpModel ifpModel = session.load(IfpModel.class, levelId);
		ContractMaster contractMaster = (ContractMaster) contractMap.get("1");
		CfpContract cfpContract = null;
		Object cfpC = contractMap.get("2");
		if (cfpC != null) {
			cfpContract = (CfpContract) cfpC;
		}
		Criteria cr = session.createCriteria(IfpContract.class).add(Restrictions.eq(CONTRACT_MASTER, contractMaster))
				.add(Restrictions.eq(IFP_MODEL, ifpModel));
		if (cfpContract == null) {
			cr.add(Restrictions.isNull(CFP_CONTRACT));
		} else {
			cr.add(Restrictions.eq(CFP_CONTRACT, cfpContract));
		}
		@SuppressWarnings("unchecked")
		List<IfpContract> results = cr.list();
		IfpContract ifpContract = new IfpContract();
		if (!results.isEmpty()) {
			ifpContract = session.load(IfpContract.class, results.get(0).getIfpContractSid());
		}
		ifpContract.setContractMaster(contractMaster);
		ifpContract.setIfpModel(ifpModel);
		ifpContract.setIfpName(ifpModel.getIfpName());
		ifpContract.setIfpNo(ifpModel.getIfpNo());
		ifpContract.setHelperTableByIfpType(ifpModel.getHelperTableByIfpType());
		ifpContract.setHelperTableByIfpCategory(ifpModel.getHelperTableByIfpCategory());
		ifpContract.setHelperTableByIfpDesignation(ifpModel.getHelperTableByIfpDesignation());
		ifpContract.setParentIfpId(ifpModel.getParentIfpId());
		ifpContract.setParentIfpName(ifpModel.getParentIfpName());
		ifpContract.setHelperTableByIfpStatus(ifpModel.getHelperTableByIfpStatus());
		ifpContract.setIfpStartDate(new Date(recordBean.getLongPropertyByIndex(5)));
		long endDate = recordBean.getLongPropertyByIndex(6);
		if (endDate != 0L) {
			ifpContract.setIfpEndDate(new Date(endDate));
		}
		ifpContract.setCreatedBy(Integer.parseInt(cdRequest.getUserId()));
		ifpContract.setCreatedDate(new Date());
		ifpContract.setModifiedBy(Integer.parseInt(cdRequest.getUserId()));
		ifpContract.setModifiedDate(new Date());
		ifpContract.setIfpContractAttachedDate(new Date());
		ifpContract.setRecordLockStatus(false);
		ifpContract.setInboundStatus(GtnFrameworkCommonStringConstants.INBOUND_STATUS_A.charAt(0));
		ifpContract.setSource("GTN");
		ifpContract.setCfpContract(cfpContract);
		session.saveOrUpdate(ifpContract);

		List<Object> inputlist = new ArrayList<>(6);
		inputlist.add(ifpContract.getIfpContractSid());
		inputlist.add(cdRequest.getUserId());
		inputlist.add(dateFormat.format(new Date()));
		inputlist.add(cdRequest.getUserId());
		inputlist.add(dateFormat.format(new Date()));
		inputlist.add(levelId);
		String saveifpTreeQuery = getSqlService().getQuery("com.contractDashboard.saveIFP");

		Object[] saveifpTreeQueryParams = { dateFormat.format(ifpContract.getIfpStartDate()),
				ifpContract.getIfpEndDate() == null ? null : dateFormat.format(ifpContract.getIfpEndDate()),
				currentDate, cdRequest.getUserId(), ifpContract.getIfpContractSid(), currentDate, currentDate,
				levelId };
		GtnFrameworkDataType[] saveifpTreeQueryTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.NULL_ALLOWED,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
		getSqlQueryEngine().executeInsertOrUpdateQuery(saveifpTreeQuery, saveifpTreeQueryParams, saveifpTreeQueryTypes,
				session);

		if (recordBean.getChildList() != null && !recordBean.getChildList().isEmpty()) {
			contractMap.put("3", ifpContract);
			saveRecursiveContractTree(session, cdRequest, cdResponse, recordBean.getChildList(), contractMap);
			contractMap.remove("3");
		}
	}

	private void savePSTree(Session session, GtnWsContractDashboardRequest cdRequest,
			GtnWsContractDashboardResponse cdResponse, GtnWsRecordBean recordBean, Map<String, Object> contractMap)
			throws GtnFrameworkGeneralException {
		int levelId = recordBean.getIntegerPropertyByIndex(4);
		PsModel psModel = session.load(PsModel.class, levelId);
		ContractMaster contractMaster = (ContractMaster) contractMap.get("1");
		CfpContract cfpContract = null;
		Object cfpC = contractMap.get("2");
		if (cfpC != null) {
			cfpContract = (CfpContract) cfpC;
		}
		IfpContract ifpContract = null;
		Object ifpC = contractMap.get("3");
		if (ifpC != null) {
			ifpContract = (IfpContract) ifpC;
		}

		Criteria cr = session.createCriteria(PsContract.class).add(Restrictions.eq(CONTRACT_MASTER, contractMaster))
				.add(Restrictions.eq("psModel", psModel));
		if (cfpContract == null) {
			cr.add(Restrictions.isNull(CFP_CONTRACT));
		} else {
			cr.add(Restrictions.eq(CFP_CONTRACT, cfpContract));
		}
		if (ifpContract == null) {
			cr.add(Restrictions.isNull(IFP_CONTRACT));
		} else {
			cr.add(Restrictions.eq(IFP_CONTRACT, ifpContract));
		}
		@SuppressWarnings("unchecked")
		List<PsContract> results = cr.list();
		PsContract psContract = new PsContract();
		if (!results.isEmpty()) {
			psContract = session.load(PsContract.class, results.get(0).getPsContractSid());
		}
		psContract.setContractMaster(contractMaster);
		psContract.setPsModel(psModel);
		psContract.setCfpContract(cfpContract);
		psContract.setIfpContract(ifpContract);
		psContract.setPsName(psModel.getPsName());
		psContract.setPsNo(psModel.getPsNo());
		psContract.setHelperTableByPsType(psModel.getHelperTableByPsType());
		psContract.setHelperTableByPsCategory(psModel.getHelperTableByPsCategory());
		psContract.setHelperTableByPsDesignation(psModel.getHelperTableByPsDesignation());
		psContract.setHelperTableByPsStatus(psModel.getHelperTableByPsStatus());
		psContract.setParentPsId(psModel.getParentPsId());
		psContract.setParentPsName(psModel.getParentPsName());

		psContract.setPsStartDate(new Date(recordBean.getLongPropertyByIndex(5)));
		long endDate = recordBean.getLongPropertyByIndex(6);
		if (endDate != 0L) {
			psContract.setPsEndDate(new Date(endDate));
		}
		psContract.setCreatedBy(Integer.parseInt(cdRequest.getUserId()));
		psContract.setCreatedDate(new Date());
		psContract.setModifiedBy(Integer.parseInt(cdRequest.getUserId()));
		psContract.setModifiedDate(new Date());
		psContract.setPsContractAttachedDate(new Date());
		psContract.setRecordLockStatus(false);
		psContract.setInboundStatus(GtnFrameworkCommonStringConstants.INBOUND_STATUS_A.charAt(0));
		psContract.setSource("GTN");

		session.saveOrUpdate(psContract);

		List<Object> inputlist = new ArrayList<>(6);
		inputlist.add(psContract.getPsContractSid());
		inputlist.add(cdRequest.getUserId());
		inputlist.add(dateFormat.format(new Date()));
		inputlist.add(cdRequest.getUserId());
		inputlist.add(dateFormat.format(new Date()));
		inputlist.add(levelId);
		String savePsTreeQuery = getSqlService().getQuery("com.contractDashboard.savePS");
		Object[] savePsTreeQueryParams = { dateFormat.format(psContract.getPsStartDate()),
				psContract.getPsEndDate() == null ? null : dateFormat.format(psContract.getPsEndDate()), currentDate,
				cdRequest.getUserId(), psContract.getPsContractSid(), currentDate, currentDate, levelId };
		GtnFrameworkDataType[] savePsTreeQueryTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.NULL_ALLOWED,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
		getSqlQueryEngine().executeInsertOrUpdateQuery(savePsTreeQuery, savePsTreeQueryParams, savePsTreeQueryTypes,
				session);

		if (recordBean.getChildList() != null && !recordBean.getChildList().isEmpty()) {
			contractMap.put("4", psContract);
			saveRecursiveContractTree(session, cdRequest, cdResponse, recordBean.getChildList(), contractMap);
			contractMap.remove("4");
		}
	}

	@SuppressWarnings("unchecked")
	private void saveRSTree(Session session, GtnWsContractDashboardRequest cdRequest, GtnWsRecordBean recordBean,
			Map<String, Object> contractMap) throws GtnFrameworkGeneralException {
		int levelId = recordBean.getIntegerPropertyByIndex(4);
		RsModel rsModel = session.load(RsModel.class, levelId);
		ContractMaster contractMaster = (ContractMaster) contractMap.get("1");
		CfpContract cfpContract = null;
		Object cfpC = contractMap.get("2");
		if (cfpC != null) {
			cfpContract = (CfpContract) cfpC;
		}
		IfpContract ifpContract = null;
		Object ifpC = contractMap.get("3");
		if (ifpC != null) {
			ifpContract = (IfpContract) ifpC;
		}
		PsContract psContract = null;
		Object psC = contractMap.get("4");
		if (psC != null) {
			psContract = (PsContract) psC;
		}

		Criteria cr = session.createCriteria(RsContract.class).add(Restrictions.eq(CONTRACT_MASTER, contractMaster))
				.add(Restrictions.eq(RS_MODEL, rsModel));
		addContractSearchCriteria(cr, cfpContract, CFP_CONTRACT);
		addContractSearchCriteria(cr, ifpContract, IFP_CONTRACT);
		addContractSearchCriteria(cr, psContract, "psContract");
		List<RsContract> results = cr.list();
		RsContract rsContract = new RsContract();
		if (!results.isEmpty()) {
			rsContract = session.load(RsContract.class, results.get(0).getRsContractSid());
		}
		rsContract.setContractMaster(contractMaster);
		rsContract.setRsModel(rsModel);
		rsContract.setCfpContract(cfpContract);
		rsContract.setIfpContract(ifpContract);
		rsContract.setPsContract(psContract);
		rsContract.setRsId(rsModel.getRsId());
		rsContract.setRsName(rsModel.getRsName());
		rsContract.setRsNo(rsModel.getRsNo());
		rsContract.setHelperTableByRsType(rsModel.getHelperTableByRsType());
		rsContract.setHelperTableByRebateProgramType(rsModel.getHelperTableByRebateProgramType());
		rsContract.setHelperTableByRsTradeClass(rsModel.getHelperTableByRsTradeClass());
		rsContract.setHelperTableByRsCategory(rsModel.getHelperTableByRsCategory());
		rsContract.setHelperTableByRsDesignation(rsModel.getHelperTableByRsDesignation());
		rsContract.setRsStartDate(new Date(recordBean.getLongPropertyByIndex(5)));
		long endDate = recordBean.getLongPropertyByIndex(6);
		if (endDate != 0L && !rsModel.getRsStartDate().equals(rsModel.getRsEndDate())) {
			rsContract.setRsEndDate(new Date(endDate));
		}
		rsContract.setHelperTableByRsStatus(rsModel.getHelperTableByRsStatus());
		rsContract.setParentRsId(rsModel.getParentRsId());
		rsContract.setParentRsName(rsModel.getParentRsName());
		rsContract.setRsTransRefId(rsModel.getRsTransRefId());
		rsContract.setRsTransRefNo(rsModel.getRsTransRefNo());
		rsContract.setRsTransRefName(rsModel.getRsTransRefName());
		rsContract.setHelperTableByPaymentMethod(rsModel.getHelperTableByPaymentMethod());
		rsContract.setHelperTableByPaymentFrequency(rsModel.getHelperTableByPaymentFrequency());
		rsContract.setHelperTableByPaymentTerms(rsModel.getHelperTableByPaymentTerms());
		rsContract.setHelperTableByRebateFrequency(rsModel.getHelperTableByRebateFrequency());
		rsContract.setHelperTableByRebateProcessingType(rsModel.getHelperTableByRebateProcessingType());
		rsContract.setHelperTableByRebateRuleType(rsModel.getHelperTableByRebateRuleType());
		rsContract.setHelperTableByRebatePlanLevel(rsModel.getHelperTableByRebatePlanLevel());
		rsContract.setRebateRuleAssociation(rsModel.getRebateRuleAssociation());
		rsContract.setCreatedBy(Integer.parseInt(cdRequest.getUserId()));
		rsContract.setCreatedDate(new Date());
		rsContract.setModifiedBy(Integer.parseInt(cdRequest.getUserId()));
		rsContract.setModifiedDate(new Date());
		rsContract.setRecordLockStatus(false);
		rsContract.setInboundStatus(GtnFrameworkCommonStringConstants.INBOUND_STATUS_A.charAt(0));
		rsContract.setSource("GTN");
		rsContract.setHelperTableByRsCalendar(rsModel.getHelperTableByRsCalendar());
		rsContract.setHelperTableByRsValidationProfile(rsModel.getHelperTableByRsValidationProfile());
		rsContract.setHelperTableByInterestBearingIndicator(rsModel.getHelperTableByInterestBearingIndicator());
		rsContract.setHelperTableByInterestBearingBasis(rsModel.getHelperTableByInterestBearingBasis());
		rsContract.setRsContractAttachedDate(new Date());
		rsContract.setPaymentGracePeriod(rsModel.getPaymentGracePeriod());
		rsContract.setMakePayableTo(rsModel.getMakePayableTo());
		rsContract.setAddress1(rsModel.getAddress1());
		rsContract.setAddress2(rsModel.getAddress2());
		rsContract.setCity(rsModel.getCity());
		rsContract.setHelperTableByState(rsModel.getHelperTableByState());
		rsContract.setZipCode(rsModel.getZipCode());
		rsContract.setFormulaMethodId(rsModel.getFormulaMethodId());
		setRSContractValues(rsModel, rsContract);
		session.saveOrUpdate(rsContract);

		List<Object> inputlist = new ArrayList<>(6);
		inputlist.add(rsContract.getRsContractSid());
		inputlist.add(cdRequest.getUserId());
		inputlist.add(dateFormat.format(new Date()));
		inputlist.add(cdRequest.getUserId());
		inputlist.add(dateFormat.format(new Date()));
		inputlist.add(levelId);
		String saveRsTreeQuery = getSqlService().getQuery("com.contractDashboard.saveRS");
		Object[] saveRsTreeQueryParams = { dateFormat.format(rsContract.getRsStartDate()),
				rsContract.getRsEndDate() == null ? null : dateFormat.format(rsContract.getRsEndDate()), currentDate,
				cdRequest.getUserId(), rsContract.getRsContractSid(), currentDate, currentDate, levelId };
		GtnFrameworkDataType[] saveRsTreeQueryTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.NULL_ALLOWED,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER };
		getSqlQueryEngine().executeInsertOrUpdateQuery(saveRsTreeQuery, saveRsTreeQueryParams, saveRsTreeQueryTypes,
				session);

		Criteria crd = session.createCriteria(RsContractDetails.class).add(Restrictions.eq("rsContract", rsContract))
				.add(Restrictions.ne(INBOUND_STATUS, GtnFrameworkCommonStringConstants.INBOUND_STATUS_D.charAt(0)));
		List<RsContractDetails> rsContDetList = crd.list();
		for (int j = 0; j < rsContDetList.size(); j++) {
			Criteria rsDetailsQuery = session.createCriteria(RsDetails.class).add(Restrictions.eq(RS_MODEL, rsModel))
					.add(Restrictions.eq("itemMaster", rsContDetList.get(j).getItemMaster()))
					.add(Restrictions.ne(INBOUND_STATUS, GtnFrameworkCommonStringConstants.INBOUND_STATUS_D.charAt(0)));
			List<RsDetails> rsdList = rsDetailsQuery.list();
			if (rsdList != null && !rsdList.isEmpty()) {
				String formulaquery = getQuery("com.contractDashboard.saveFormulaRS");
				ItemMaster itemMaster = rsContDetList.get(j).getItemMaster();
				Object[] formulaQueryParams = { Integer.toString(rsContDetList.get(j).getRsContractDetailsSid()),
						Integer.toString(itemMaster.getItemMasterSid()),
						Integer.toString(rsdList.get(0).getRsDetailsSid()) };
				GtnFrameworkDataType[] formulaQueryTypes = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
						GtnFrameworkDataType.STRING };
				getSqlQueryEngine().executeInsertOrUpdateQuery(formulaquery, formulaQueryParams, formulaQueryTypes,
						session);

			}
		}

	}

	void addContractSearchCriteria(Criteria cr, Object object, String propertyId) {
		if (object == null) {
			cr.add(Restrictions.isNull(propertyId));
		} else {
			cr.add(Restrictions.eq(propertyId, object));
		}
	}

	void setRSContractValues(RsModel rsModel, RsContract rsContract) {
		rsContract.setCalculationType(rsModel.getHelperTableByCalculationType() == null ? null
				: rsModel.getHelperTableByCalculationType().getHelperTableSid());
		rsContract.setCalculationLevel(rsModel.getHelperTableByCalculationLevel() == null ? null
				: rsModel.getHelperTableByCalculationLevel().getHelperTableSid());
		rsContract.setCalculationRule(rsModel.getCdrModelByCalculationRule() == null ? null
				: rsModel.getCdrModelByCalculationRule().getCdrModelSid());
		rsContract.setCalculationRuleLevel(rsModel.getHelperTableByCalculationRuleLevel() == null ? null
				: rsModel.getHelperTableByCalculationRuleLevel().getHelperTableSid());
		rsContract.setEvaluationRuleType(rsModel.getHelperTableByEvaluationRuleType() == null ? null
				: rsModel.getHelperTableByEvaluationRuleType().getHelperTableSid());
		rsContract.setEvaluationRuleLevel(rsModel.getHelperTableByEvaluationRuleLevel() == null ? null
				: rsModel.getHelperTableByEvaluationRuleLevel().getHelperTableSid());
		rsContract.setEvaluationRuleOrAssociation(rsModel.getCdrModelByEvaluationRuleOrAssociation() == null ? null
				: rsModel.getCdrModelByEvaluationRuleOrAssociation().getCdrModelSid());
		rsContract.setDeductionInclusion(rsModel.getHelperTableByDeductionInclusion() == null ? null
				: rsModel.getHelperTableByDeductionInclusion().getHelperTableSid());
	}

	private GtnFrameworkSqlQueryEngine getSqlQueryEngine() {
		return getController().getGtnSqlQueryEngine();
	}

	private GtnWsSqlService getSqlService() {
		return getController().getGtnWsSqlService();
	}

}
