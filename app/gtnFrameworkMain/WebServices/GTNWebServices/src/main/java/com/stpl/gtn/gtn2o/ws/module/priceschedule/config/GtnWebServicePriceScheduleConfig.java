/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.priceschedule.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;

/**
 *
 * @author Mahesh.James
 */
public class GtnWebServicePriceScheduleConfig implements GtnWsSearchQueryConfigLoader {

	private static Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();

	static {
		loadSearchQueryConfig();
		loadIFPSearchQueryConfig();
		loadPSPriceTabQueryConfig();
		loadPSPriceProtectionTabQueryConfig();
		loadPSNetSalesFormulaMapping();
		loadPSPriceTabViewQueryConfig();
		loadPSPriceProtectionTabViewQueryConfig();
		updatePSPriceProtectionTabQueryConfig();
	}

	public static void loadSearchQueryConfig() {

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> psSearchColumnDetailsMap = new HashMap<>();

		psSearchColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.PS_MODEL_SID, "ps"));
		psSearchColumnDetailsMap.put("priceScheduleId", configProvider.getColumnStringConfig("PS_ID", "ps"));
		psSearchColumnDetailsMap.put("priceScheduleNo", configProvider.getColumnStringConfig("PS_NO", "ps"));
		psSearchColumnDetailsMap.put("priceScheduleName", configProvider.getColumnStringConfig("PS_NAME", "ps"));
		psSearchColumnDetailsMap.put("priceScheduleType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "htype", "type",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psSearchColumnDetailsMap.put("priceScheduleStatus",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "hstatus", "status",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psSearchColumnDetailsMap.put("priceScheduleCategory",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "hcategory",
						"category", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psSearchColumnDetailsMap.put("startDate", configProvider.getColumnDateConfig("PS_START_DATE", "ps"));
		psSearchColumnDetailsMap.put("endDate", configProvider.getColumnDateConfig("PS_END_DATE", "ps"));
		psSearchColumnDetailsMap.put("priceScheduleDesignation",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "hdesign",
						"designation", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psSearchColumnDetailsMap.put("parentID", configProvider.getColumnStringConfig("PARENT_PS_ID", "ps"));
		psSearchColumnDetailsMap.put("parentName", configProvider.getColumnStringConfig("PARENT_PS_NAME", "ps"));
		psSearchColumnDetailsMap.put("tradeClass",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "htrade", "trade",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psSearchColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_ID_COLUMN, "ps"));
		psSearchColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_NO,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NO, "ps"));
		psSearchColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NAME, "ps"));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(psSearchColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("ps.PS_ID", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuerySelectClause(" Select count(distinct ps.PS_MODEL_SID)  ");

		gtnWebServiceSearchQueryContext
				.setCountQuery("  FROM PS_MODEL ps" + " JOIN PS_DETAILS psd on ps.PS_MODEL_SID=psd.PS_MODEL_SID"
						+ " JOIN ITEM_MASTER im on psd.ITEM_MASTER_SID=im.ITEM_MASTER_SID"
						+ " LEFT JOIN HELPER_TABLE htype on htype.HELPER_TABLE_SID=ps.PS_TYPE"
						+ " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID=ps.PS_STATUS"
						+ " LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID=ps.PS_CATEGORY"
						+ " LEFT JOIN HELPER_TABLE hdesign on hdesign.HELPER_TABLE_SID=ps.PS_DESIGNATION"
						+ " LEFT JOIN HELPER_TABLE htrade on htrade.HELPER_TABLE_SID=ps.PS_TRADE_CLASS ");

		gtnWebServiceSearchQueryContext
				.setSearchQuery("  FROM PS_MODEL ps" + " JOIN PS_DETAILS psd on ps.PS_MODEL_SID=psd.PS_MODEL_SID"
						+ " JOIN ITEM_MASTER im on psd.ITEM_MASTER_SID=im.ITEM_MASTER_SID"
						+ " LEFT JOIN HELPER_TABLE htype on htype.HELPER_TABLE_SID=ps.PS_TYPE"
						+ " LEFT JOIN HELPER_TABLE hstatus on hstatus.HELPER_TABLE_SID=ps.PS_STATUS"
						+ " LEFT JOIN HELPER_TABLE hcategory on hcategory.HELPER_TABLE_SID=ps.PS_CATEGORY"
						+ " LEFT JOIN HELPER_TABLE hdesign on hdesign.HELPER_TABLE_SID=ps.PS_DESIGNATION"
						+ " LEFT JOIN HELPER_TABLE htrade on htrade.HELPER_TABLE_SID=ps.PS_TRADE_CLASS ");

		searchQueryConfigMap.put("priceScheduleSearch", gtnWebServiceSearchQueryContext);

	}

	public static void loadIFPSearchQueryConfig() {

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> ifpItemAdditionColumnDetailsMap = new HashMap<>();

		ifpItemAdditionColumnDetailsMap.put("ifpId", configProvider.getColumnStringConfig("IFP_ID", "IFP"));
		ifpItemAdditionColumnDetailsMap.put("ifpNo", configProvider.getColumnStringConfig("IFP_NO", "IFP"));
		ifpItemAdditionColumnDetailsMap.put("ifpName", configProvider.getColumnStringConfig("IFP_NAME", "IFP"));
		ifpItemAdditionColumnDetailsMap.put("IFP ID", configProvider.getColumnStringConfig("IFP_ID", "IFP"));
		ifpItemAdditionColumnDetailsMap.put("IFP No", configProvider.getColumnStringConfig("IFP_NO", "IFP"));
		ifpItemAdditionColumnDetailsMap.put("IFP Name", configProvider.getColumnStringConfig("IFP_NAME", "IFP"));
		ifpItemAdditionColumnDetailsMap.put("IFP STATUS",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.IFP_STATUS, GtnFrameworkWebserviceConstant.IFP_STATUS,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		ifpItemAdditionColumnDetailsMap.put("ifpStatus",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.IFP_STATUS, GtnFrameworkWebserviceConstant.IFP_STATUS,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		ifpItemAdditionColumnDetailsMap.put("ifpEndDate", configProvider.getColumnDateConfig("IFP_END_DATE", "IFP"));
		ifpItemAdditionColumnDetailsMap.put("IFP END DATE", configProvider.getColumnDateConfig("IFP_END_DATE", "IFP"));
		ifpItemAdditionColumnDetailsMap.put("IFP START DATE",
				configProvider.getColumnDateConfig("IFP_START_DATE", "IFP"));
		ifpItemAdditionColumnDetailsMap.put("IFP TYPE",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.IFP_TYPE, GtnFrameworkWebserviceConstant.IFP_TYPE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		ifpItemAdditionColumnDetailsMap.put("ifpType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.IFP_TYPE, GtnFrameworkWebserviceConstant.IFP_TYPE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		ifpItemAdditionColumnDetailsMap.put("IFP CATEGORY",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.IFP_CATEGORY, GtnFrameworkWebserviceConstant.IFP_CATEGORY,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		ifpItemAdditionColumnDetailsMap.put("ifpCategory",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.IFP_CATEGORY, GtnFrameworkWebserviceConstant.IFP_CATEGORY,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		ifpItemAdditionColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnStringConfig("IFP_MODEL_SID", "IFP"));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(ifpItemAdditionColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("IFP.IFP_NO", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(" FROM IFP_MODEL  IFP "
				+ "LEFT JOIN HELPER_TABLE IFP_STATUS ON IFP_STATUS.HELPER_TABLE_SID=IFP.IFP_STATUS \n"
				+ "LEFT JOIN HELPER_TABLE IFP_TYPE ON IFP_TYPE.HELPER_TABLE_SID=IFP.IFP_TYPE \n"
				+ "LEFT JOIN HELPER_TABLE IFP_CATEGORY ON IFP_CATEGORY.HELPER_TABLE_SID=IFP.IFP_CATEGORY ");

		gtnWebServiceSearchQueryContext.setSearchQuery(" FROM IFP_MODEL  IFP "
				+ "LEFT JOIN HELPER_TABLE IFP_STATUS ON IFP_STATUS.HELPER_TABLE_SID=IFP.IFP_STATUS \n"
				+ "LEFT JOIN HELPER_TABLE IFP_TYPE ON IFP_TYPE.HELPER_TABLE_SID=IFP.IFP_TYPE \n"
				+ "LEFT JOIN HELPER_TABLE IFP_CATEGORY ON IFP_CATEGORY.HELPER_TABLE_SID=IFP.IFP_CATEGORY  ");

		gtnWebServiceSearchQueryContext.setWhereClauseList(Arrays.asList("IFP.INBOUND_STATUS <> 'D'"));

		searchQueryConfigMap.put("priceScheduleItemAddition", gtnWebServiceSearchQueryContext);

	}

	public static void loadPSPriceTabQueryConfig() {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> psPriceTabToColumnDetailsMap = new HashMap<>();

		psPriceTabToColumnDetailsMap.put("createdBy",
				configProvider.getColumnUserConfig("CREATED_BY", GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
				configProvider.getColumnBooleanConfig(GtnFrameworkWebserviceConstant.CHECK_RECORD_COLUMN,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.ITEM_ID_COLUMN, GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_NO, configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NO, GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_NAME, configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NAME, GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.BRAND_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BRAND_NAME_COLUMN, "BM"));
		psPriceTabToColumnDetailsMap.put("psStatus",
				configProvider.getColumnIntegerConfig("STATUS", GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put("cpStartDate", configProvider
				.getColumnDateConfig("PS_DTLS_CONT_PRICE_STARTDATE", GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put("cpEndDate",
				configProvider.getColumnDateConfig("PS_DTLS_CONT_PRICE_ENDDATE", GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put("priceType", configProvider.getColumnIntegerConfig("PS_DETAILS_PRICETYPE",
				GtnFrameworkWebserviceConstant.IMPSD, "PRICE_TYPE"));
		psPriceTabToColumnDetailsMap.put("psPrice",
				configProvider.getColumnStringConfig("PS_DETAILS_PRICE", GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put("suggestedPrice", configProvider
				.getColumnStringConfig("PS_DETAILS_SUGGESTED_PRICE", GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put("psCreatedDate",
				configProvider.getColumnDateConfig("PS_DETAILS_CREATED_DATE", GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_ATTACHED_DATE,
				configProvider.getColumnDateConfig(GtnFrameworkWebserviceConstant.PS_DETAILS_ATTACHED_DATE,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.IMTD_PS_DETAILS_SID, GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.USER_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.USERS_SID_COLUMN, GtnFrameworkWebserviceConstant.IMPSD));
		psPriceTabToColumnDetailsMap.put(GtnFrameworkCommonConstants.SESSION_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.SESSION_ID, GtnFrameworkWebserviceConstant.IMPSD));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(psPriceTabToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("IMPSD.ITEM_NO", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(" FROM IMTD_PS_DETAILS IMPSD \n"
				+ " JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IMPSD.BRAND_MASTER_SID \n");

		gtnWebServiceSearchQueryContext.setSearchQuery("  FROM IMTD_PS_DETAILS IMPSD \n"
				+ " JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IMPSD.BRAND_MASTER_SID \n");
		gtnWebServiceSearchQueryContext.setWhereClauseList(Arrays.asList("ADD_COPY_INDICATOR is null"));
		searchQueryConfigMap.put("priceSchedulePrice", gtnWebServiceSearchQueryContext);

	}

	public static void loadPSPriceTabViewQueryConfig() {

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> itemPriceTabColumnDetailsMap = new HashMap<>();
		itemPriceTabColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_ID_COLUMN, "im"));
		itemPriceTabColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_NO,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NO, "im"));
		itemPriceTabColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NAME, "im"));
		itemPriceTabColumnDetailsMap.put(GtnFrameworkWebserviceConstant.BRAND_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BRAND_NAME_COLUMN, "BM"));
		itemPriceTabColumnDetailsMap.put("psStatusDes",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "HTStatus"));
		itemPriceTabColumnDetailsMap.put("cpStartDate",
				configProvider.getColumnDateConfig("CONTRACT_PRICE_START_DATE", GtnFrameworkWebserviceConstant.IMPSD));
		itemPriceTabColumnDetailsMap.put("cpEndDate",
				configProvider.getColumnDateConfig("CONTRACT_PRICE_END_DATE", GtnFrameworkWebserviceConstant.IMPSD));
		itemPriceTabColumnDetailsMap.put("priceTypeDes", configProvider.getColumnStringConfig(
				GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER_NAME_CAPS, "HTPSTYPE", "PRICE_TYPE"));
		itemPriceTabColumnDetailsMap.put("psPrice",
				configProvider.getColumnStringConfig("PRICE", GtnFrameworkWebserviceConstant.IMPSD));
		itemPriceTabColumnDetailsMap.put("suggestedPrice",
				configProvider.getColumnStringConfig("SUGGESTED_PRICE", GtnFrameworkWebserviceConstant.IMPSD));
		itemPriceTabColumnDetailsMap.put("createdBy",
				configProvider.getColumnUserConfig("CREATED_BY", GtnFrameworkWebserviceConstant.IMPSD));
		itemPriceTabColumnDetailsMap.put("psCreatedDate",
				configProvider.getColumnDateConfig("CREATED_DATE", GtnFrameworkWebserviceConstant.IMPSD));
		itemPriceTabColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_ATTACHED_DATE,
				configProvider.getColumnDateConfig("ITEM_PS_ATTACHED_DATE", GtnFrameworkWebserviceConstant.IMPSD));
		itemPriceTabColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnStringConfig("PS_DETAILS_SID", GtnFrameworkWebserviceConstant.IMPSD));
		itemPriceTabColumnDetailsMap.put("IMPSD.PS_MODEL_SID", configProvider.getColumnIntegerConfig(
				GtnFrameworkWebserviceConstant.PS_MODEL_SID, GtnFrameworkWebserviceConstant.IMPSD));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(itemPriceTabColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("im.ITEM_NO", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(
				" FROM	PS_DETAILS IMPSD JOIN BRAND_MASTER BM ON	BM.BRAND_MASTER_SID = IMPSD.BRAND_MASTER_SID JOIN item_master im on 	im.ITEM_MASTER_SID = impsd.ITEM_MASTER_SID JOIN dbo.HELPER_TABLE HTStatus on	htstatus.HELPER_TABLE_SID = impsd.STATUS JOIN dbo.ITEM_PRICING_QUALIFIER HTPSTYPE on	HTPSTYPE.ITEM_PRICING_QUALIFIER_SID = impsd.ITEM_PRICING_QUALIFIER_SID  ");

		gtnWebServiceSearchQueryContext.setSearchQuery(
				"  FROM	PS_DETAILS IMPSD JOIN BRAND_MASTER BM ON	BM.BRAND_MASTER_SID = IMPSD.BRAND_MASTER_SID JOIN item_master im on 	im.ITEM_MASTER_SID = impsd.ITEM_MASTER_SID JOIN dbo.HELPER_TABLE HTStatus on	htstatus.HELPER_TABLE_SID = impsd.STATUS JOIN dbo.ITEM_PRICING_QUALIFIER HTPSTYPE on	HTPSTYPE.ITEM_PRICING_QUALIFIER_SID = impsd.ITEM_PRICING_QUALIFIER_SID  ");
		gtnWebServiceSearchQueryContext.setWhereClauseList(Arrays.asList("ADD_COPY_INDICATOR is null"));
		searchQueryConfigMap.put("priceSchedulePriceView", gtnWebServiceSearchQueryContext);

	}

	public static void updatePSPriceProtectionTabQueryConfig() {

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> updatePPColumnDetailsMap = new HashMap<>();

		// PRICE_PROTECTION_PRICE_TYPE
		updatePPColumnDetailsMap.put(GtnFrameworkCommonConstants.CHECK_RECORD_ID, configProvider.getColumnBooleanConfig(
				GtnFrameworkWebserviceConstant.CHECK_RECORD_COLUMN, GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_PP_STATUS,
				configProvider.getColumnIntegerConfig("PRICE_PROTECTION_STATUS", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_PP_START_DATE, configProvider
				.getColumnDateConfig("PS_DETAILS_PRIC_PRTCN_STDATE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_PP_END_DATE, configProvider
				.getColumnDateConfig("PS_DETAILS_PRIC_PRTCN_EDDATE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_PP_PRICE_TYPE, configProvider
				.getColumnIntegerConfig("PRICE_PROTECTION_PRICE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NEP,
				configProvider.getColumnStringConfig("NEP", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NEP_FORMULA,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.NEP_FORMULA, null));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_BASE_PRICE_TYPE,
				configProvider.getColumnIntegerConfig("BASE_PRICE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_BASE_PRICE_ENTRY,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BASE_PRICE_ENTRY,
						GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_BASE_PRICE,
				configProvider.getColumnStringConfig("NET_BASE_PRICE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_BASE_PRICE_FORMULA_ID, configProvider
				.getColumnStringConfig("NET_BASE_PRICE_FORMULA_ID", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_SUBSEQ_PERIOD_PRICE_TYPE, configProvider
				.getColumnIntegerConfig("SUBSEQUENT_PERIOD_PRICE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_B_SUBSEQ_PERIOD_PRICE, configProvider
				.getColumnIntegerConfig("NET_SUBSEQUENT_PERIOD_PRICE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_B_SUBSEQ_PRICE_FORMULA_ID, configProvider
				.getColumnStringConfig("NET_SUBSEQUENT_PRICE_FORMULA_ID", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_TOLERANCE_INTERVAL, configProvider
				.getColumnIntegerConfig("PS_DTLS_PRICE_TOLERANCE_INTRVL", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_TOLERANCE_FREQ, configProvider
				.getColumnStringConfig("PS_DTLS_PRICE_TOLERANCE_FREQ", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_TOLERANCE_TYPE, configProvider
				.getColumnIntegerConfig("PS_DTLS_PRICE_TOLERANCE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_DETAILS_PRICE_TOL, configProvider
				.getColumnStringConfig("PS_DETAILS_PRICE_TOLERANCE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_MAX_INCREMENTAL_CHANGE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.MAX_INCREMENTAL_CHANGE,
						GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_ELIGIBLE,
				configProvider.getColumnIntegerConfig("RESET_ELIGIBLE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_TYPE,
				configProvider.getColumnIntegerConfig("RESET_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_DATE, configProvider
				.getColumnDateConfig(GtnFrameworkWebserviceConstant.RESET_DATE, GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_INTERVAL,
				configProvider.getColumnIntegerConfig("RESET_INTERVAL", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_FREQUENCY,
				configProvider.getColumnIntegerConfig("RESET_FREQUENCY", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_PRICE_TYPE,
				configProvider.getColumnIntegerConfig("RESET_PRICE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_TYPE,
				configProvider.getColumnIntegerConfig("NET_RESET_PRICE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_PRICE_TYPE,
				configProvider.getColumnIntegerConfig("NET_PRICE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_FORMULA_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.NET_RESET_PRICE_FORMULA_ID, null));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_FORMULA_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.NET_RESET_PRICE_FORMULA_ID,
						"nptFormula", "psNetResetPriceFormula"));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_ATTACHED_DATE,
				configProvider.getColumnDateConfig(GtnFrameworkWebserviceConstant.PS_DETAILS_ATTACHED_DATE,
						GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.IMTD_PS_DETAILS_SID, GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.USER_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.USERS_SID_COLUMN, GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkCommonConstants.SESSION_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.SESSION_ID, GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_BASE_PRICE_DATE,
				configProvider.getColumnDateConfig(GtnFrameworkWebserviceConstant.BASE_PRICE_DATE,
						GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_BASE_PRICE_DDLB,
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.BASE_PRICE_DDLB,
						GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_FORMULA_ID_ONE,
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.NET_RESET_PRICE_FORMULA_ID,
						GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_SUBSEQUENT_PRICE_FORMULA_ID_ONE,
				configProvider.getColumnIntegerConfig(
						GtnFrameworkWebserviceConstant.PS_NET_B_SUBSEQ_PRICE_FORMULA_ID_COLUMN,
						GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_BASE_PRICE_FORMULA_ID_ONE,
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.PS_NET_B_RESET_PRICE_FORMULA_ID,
						GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NEP_FORMULA_ONE,
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.PS_NEP_FORMULA_ID_COLUMN,
						GtnFrameworkWebserviceConstant.IMPSD));
		updatePPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_PRICE_TYPE_FORMULA_ONE,
				configProvider.getColumnIntegerConfig(
						GtnFrameworkWebserviceConstant.PS_NET_PRICE_TYPE_FORMULA_ID_COLUMN,
						GtnFrameworkWebserviceConstant.IMPSD));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(updatePPColumnDetailsMap);
		searchQueryConfigMap.put("updatePriceSchedulePriceProtection", gtnWebServiceSearchQueryContext);
	}

	public static void loadPSPriceProtectionTabQueryConfig() {

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> psPPColumnDetailsMap = new HashMap<>();
		psPPColumnDetailsMap.put(GtnFrameworkCommonConstants.CHECK_RECORD_ID, configProvider.getColumnBooleanConfig(
				GtnFrameworkWebserviceConstant.CHECK_RECORD_COLUMN, GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.ITEM_ID_COLUMN, GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_NO, configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NO, GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_NAME, configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NAME, GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.BRAND_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BRAND_NAME_COLUMN, "BM"));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_PP_STATUS,
				configProvider.getColumnIntegerConfig("PRICE_PROTECTION_STATUS", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_PP_START_DATE, configProvider
				.getColumnDateConfig("PS_DETAILS_PRIC_PRTCN_STDATE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_PP_END_DATE, configProvider
				.getColumnDateConfig("PS_DETAILS_PRIC_PRTCN_EDDATE", GtnFrameworkWebserviceConstant.IMPSD));
		// PRICE_PROTECTION_PRICE_TYPE
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_PP_PRICE_TYPE, configProvider
				.getColumnIntegerConfig("PRICE_PROTECTION_PRICE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NEP,
				configProvider.getColumnStringConfig("NEP", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NEP_FORMULA,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.NET_SALES_FORMULA_NAME, "nep",
						GtnFrameworkWebserviceConstant.PS_NEP_FORMULA));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_BASE_PRICE_TYPE,
				configProvider.getColumnIntegerConfig("BASE_PRICE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_BASE_PRICE_ENTRY,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BASE_PRICE_ENTRY,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_BASE_PRICE,
				configProvider.getColumnIntegerConfig("NET_BASE_PRICE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_BASE_PRICE_FORMULA_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.NET_SALES_FORMULA_NAME,
						"nbpFormula", "psNetBasePriceFormula"));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_SUBSEQ_PERIOD_PRICE_TYPE, configProvider
				.getColumnIntegerConfig("SUBSEQUENT_PERIOD_PRICE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_B_SUBSEQ_PERIOD_PRICE, configProvider
				.getColumnIntegerConfig("NET_SUBSEQUENT_PERIOD_PRICE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_B_SUBSEQ_PRICE_FORMULA_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.NET_SALES_FORMULA_NAME,
						"nsppFormula", "psNetBSubseqPriceFormula"));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_TOLERANCE_INTERVAL, configProvider
				.getColumnIntegerConfig("PS_DTLS_PRICE_TOLERANCE_INTRVL", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_TOLERANCE_FREQ, configProvider
				.getColumnIntegerConfig("PS_DTLS_PRICE_TOLERANCE_FREQ", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_TOLERANCE_TYPE, configProvider
				.getColumnIntegerConfig("PS_DTLS_PRICE_TOLERANCE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_DETAILS_PRICE_TOL, configProvider
				.getColumnStringConfig("PS_DETAILS_PRICE_TOLERANCE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_MAX_INCREMENTAL_CHANGE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.MAX_INCREMENTAL_CHANGE,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_ELIGIBLE,
				configProvider.getColumnIntegerConfig("RESET_ELIGIBLE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_TYPE,
				configProvider.getColumnIntegerConfig("RESET_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_DATE, configProvider
				.getColumnDateConfig(GtnFrameworkWebserviceConstant.RESET_DATE, GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_INTERVAL,
				configProvider.getColumnIntegerConfig("RESET_INTERVAL", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_FREQUENCY,
				configProvider.getColumnIntegerConfig("RESET_FREQUENCY", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_PRICE_TYPE,
				configProvider.getColumnIntegerConfig("RESET_PRICE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_TYPE,
				configProvider.getColumnIntegerConfig("NET_RESET_PRICE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_PRICE_TYPE,
				configProvider.getColumnIntegerConfig("NET_PRICE_TYPE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_FORMULA_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.NET_SALES_FORMULA_NAME,
						"nrpFormula", GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_FORMULA_ID));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_FORMULA_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.NET_SALES_FORMULA_NAME,
						"nptFormula", "psNetResetPriceFormula"));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_ATTACHED_DATE, configProvider.getColumnDateConfig(
				GtnFrameworkWebserviceConstant.PS_DETAILS_ATTACHED_DATE, GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_BASE_PRICE_DATE, configProvider.getColumnDateConfig(
				GtnFrameworkWebserviceConstant.BASE_PRICE_DATE, GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_BASE_PRICE_DDLB,
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.BASE_PRICE_DDLB,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put("psBasePriceTypeDes", configProvider.getColumnStringConfig("DESCRIPTION", "baseType"));
		psPPColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.IMTD_PS_DETAILS_SID, GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.USER_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.USERS_SID_COLUMN, GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkCommonConstants.SESSION_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.SESSION_ID, GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_FORMULA_ID_ONE,
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.NET_RESET_PRICE_FORMULA_ID,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_SUBSEQUENT_PRICE_FORMULA_ID_ONE,
				configProvider.getColumnIntegerConfig(
						GtnFrameworkWebserviceConstant.PS_NET_B_SUBSEQ_PRICE_FORMULA_ID_COLUMN,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_BASE_PRICE_FORMULA_ID_ONE,
				configProvider.getColumnIntegerConfig(
						GtnFrameworkWebserviceConstant.PS_NET_BASE_PRICE_FORMULA_ID_COLUMN,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NEP_FORMULA_ONE,
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.PS_NEP_FORMULA_ID_COLUMN,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPPColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_PRICE_TYPE_FORMULA_ONE,
				configProvider.getColumnIntegerConfig(
						GtnFrameworkWebserviceConstant.PS_NET_PRICE_TYPE_FORMULA_ID_COLUMN,
						GtnFrameworkWebserviceConstant.IMPSD));
		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(psPPColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("IMPSD.ITEM_NO", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(" FROM IMTD_PS_DETAILS IMPSD\n"
				+ "LEFT JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IMPSD.BRAND_MASTER_SID\n"
				+ "LEFT JOIN HELPER_TABLE PP_STATUS ON PP_STATUS.HELPER_TABLE_SID=IMPSD.PRICE_PROTECTION_STATUS\n"
				+ "LEFT JOIN ITEM_PRICING_QUALIFIER PRICETYPE on PRICETYPE.ITEM_PRICING_QUALIFIER_SID=PS_DETAILS_PRICETYPE \n"
				+ "LEFT JOIN HELPER_TABLE htolInt on htolint.HELPER_TABLE_SID=IMPSD.PS_DTLS_PRICE_TOLERANCE_INTRVL \n"
				+ "LEFT JOIN HELPER_TABLE htoltyp on htoltyp.HELPER_TABLE_SID=IMPSD.PS_DTLS_PRICE_TOLERANCE_TYPE \n"
				+ "LEFT JOIN HELPER_TABLE htolfrq on htolfrq.HELPER_TABLE_SID=IMPSD.PS_DTLS_PRICE_TOLERANCE_FREQ "
				+ "LEFT JOIN NET_SALES_FORMULA_MASTER nep on nep.NET_SALES_FORMULA_MASTER_SID=IMPSD.NEP_FORMULA "
				+ "LEFT JOIN NET_SALES_FORMULA_MASTER nbpFormula on nbpFormula.NET_SALES_FORMULA_MASTER_SID=IMPSD.NET_BASE_PRICE_FORMULA_ID "
				+ "LEFT JOIN NET_SALES_FORMULA_MASTER nsppFormula on nsppFormula.NET_SALES_FORMULA_MASTER_SID=IMPSD.NET_SUBSEQUENT_PRICE_FORMULA_ID "
				+ "LEFT JOIN NET_SALES_FORMULA_MASTER nrpFormula on nrpFormula.NET_SALES_FORMULA_MASTER_SID=IMPSD.NET_RESET_PRICE_FORMULA_ID "
				+ "LEFT JOIN NET_SALES_FORMULA_MASTER nptFormula on nptFormula.NET_SALES_FORMULA_MASTER_SID=IMPSD.NET_PRICE_TYPE_FORMULA "
				+ "LEFT JOIN HELPER_TABLE baseType on baseType.HELPER_TABLE_SID = IMPSD.BASE_PRICE_TYPE");

		gtnWebServiceSearchQueryContext.setSearchQuery(" FROM IMTD_PS_DETAILS IMPSD\n"
				+ "LEFT JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IMPSD.BRAND_MASTER_SID\n"
				+ "LEFT JOIN HELPER_TABLE PP_STATUS ON PP_STATUS.HELPER_TABLE_SID=IMPSD.PRICE_PROTECTION_STATUS\n"
				+ "LEFT JOIN ITEM_PRICING_QUALIFIER PRICETYPE on PRICETYPE.ITEM_PRICING_QUALIFIER_SID=PS_DETAILS_PRICETYPE \n"
				+ "LEFT JOIN HELPER_TABLE htolInt on htolint.HELPER_TABLE_SID=IMPSD.PS_DTLS_PRICE_TOLERANCE_INTRVL \n"
				+ "LEFT JOIN HELPER_TABLE htoltyp on htoltyp.HELPER_TABLE_SID=IMPSD.PS_DTLS_PRICE_TOLERANCE_TYPE \n"
				+ "LEFT JOIN HELPER_TABLE htolfrq on htolfrq.HELPER_TABLE_SID=IMPSD.PS_DTLS_PRICE_TOLERANCE_FREQ "
				+ "LEFT JOIN NET_SALES_FORMULA_MASTER nep on nep.NET_SALES_FORMULA_MASTER_SID=IMPSD.NEP_FORMULA "
				+ "LEFT JOIN NET_SALES_FORMULA_MASTER nbpFormula on nbpFormula.NET_SALES_FORMULA_MASTER_SID=IMPSD.NET_BASE_PRICE_FORMULA_ID "
				+ "LEFT JOIN NET_SALES_FORMULA_MASTER nsppFormula on nsppFormula.NET_SALES_FORMULA_MASTER_SID=IMPSD.NET_SUBSEQUENT_PRICE_FORMULA_ID "
				+ "LEFT JOIN NET_SALES_FORMULA_MASTER nrpFormula on nrpFormula.NET_SALES_FORMULA_MASTER_SID=IMPSD.NET_RESET_PRICE_FORMULA_ID "
				+ "LEFT JOIN NET_SALES_FORMULA_MASTER nptFormula on nptFormula.NET_SALES_FORMULA_MASTER_SID=IMPSD.NET_PRICE_TYPE_FORMULA "
				+ "LEFT JOIN HELPER_TABLE baseType on baseType.HELPER_TABLE_SID = IMPSD.BASE_PRICE_TYPE");

		searchQueryConfigMap.put("priceSchedulePriceProtection", gtnWebServiceSearchQueryContext);

	}

	public static void loadPSPriceProtectionTabViewQueryConfig() {

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> psPPViewColumnDetailsMap = new HashMap<>();
		psPPViewColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_ID_COLUMN, "IM"));
		psPPViewColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_NO,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NO, "IM"));
		psPPViewColumnDetailsMap.put(GtnFrameworkCommonConstants.ITEM_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NAME, "IM"));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.BRAND_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BRAND_NAME_COLUMN, "BM"));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_PP_STATUS,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "PP_STATUS",
						GtnFrameworkWebserviceConstant.PS_PP_STATUS, GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_PP_START_DATE, configProvider
				.getColumnDateConfig("PRICE_PROTECTION_START_DATE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_PP_END_DATE,
				configProvider.getColumnDateConfig("PRICE_PROTECTION_END_DATE", GtnFrameworkWebserviceConstant.IMPSD));
		// PRICE_PROTECTION_PRICE_TYPE
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_PP_PRICE_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER_NAME_CAPS,
						"PP_TYPE", GtnFrameworkWebserviceConstant.PS_PP_PRICE_TYPE, "ITEM_PRICING_QUALIFIER_SID"));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NEP,
				configProvider.getColumnStringConfig("NEP", GtnFrameworkWebserviceConstant.IMPSD));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NEP_FORMULA,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.NEP_FORMULA,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_BASE_PRICE_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "bpriceType",
						GtnFrameworkWebserviceConstant.PS_BASE_PRICE_TYPE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_BASE_PRICE_ENTRY,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BASE_PRICE_ENTRY,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_BASE_PRICE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "netBasePrice",
						GtnFrameworkWebserviceConstant.PS_NET_BASE_PRICE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_BASE_PRICE_FORMULA_ID,
				configProvider.getColumnStringConfig("NET_BASE_PRICE_FORMULA_ID", GtnFrameworkWebserviceConstant.IMPSD,
						GtnFrameworkWebserviceConstant.PS_NET_BASE_PRICE_FORMULA_ID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_SUBSEQ_PERIOD_PRICE_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkCommonConstants.ITEM_PRICING_QUALIFIER_NAME_CAPS,
						"NEtSEBSEQTY", GtnFrameworkWebserviceConstant.PS_SUBSEQ_PERIOD_PRICE_TYPE,
						"ITEM_PRICING_QUALIFIER_SID"));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_B_SUBSEQ_PERIOD_PRICE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "netsubseqPeriodPr",
						GtnFrameworkWebserviceConstant.PS_NET_B_SUBSEQ_PERIOD_PRICE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_B_SUBSEQ_PRICE_FORMULA_ID, configProvider
				.getColumnStringConfig("NET_SUBSEQUENT_PRICE_FORMULA_ID", GtnFrameworkWebserviceConstant.IMPSD));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_TOLERANCE_INTERVAL,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "htolInt",
						GtnFrameworkWebserviceConstant.PS_TOLERANCE_INTERVAL,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_TOLERANCE_FREQ,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "htolfrq",
						GtnFrameworkWebserviceConstant.PS_TOLERANCE_FREQ));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_TOLERANCE_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "htoltyp",
						GtnFrameworkWebserviceConstant.PS_TOLERANCE_TYPE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_DETAILS_PRICE_TOL,
				configProvider.getColumnStringConfig("PRICE_TOLERANCE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_MAX_INCREMENTAL_CHANGE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.MAX_INCREMENTAL_CHANGE,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_ELIGIBLE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "resetEli",
						GtnFrameworkWebserviceConstant.PS_RESET_ELIGIBLE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "resetType",
						GtnFrameworkWebserviceConstant.PS_RESET_TYPE, GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_DATE, configProvider
				.getColumnDateConfig(GtnFrameworkWebserviceConstant.RESET_DATE, GtnFrameworkWebserviceConstant.IMPSD));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_INTERVAL,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "reseInter",
						GtnFrameworkWebserviceConstant.PS_RESET_INTERVAL,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_FREQUENCY,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "reseFreq",
						GtnFrameworkWebserviceConstant.PS_RESET_FREQUENCY,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_RESET_PRICE_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "reseprTy",
						GtnFrameworkWebserviceConstant.PS_RESET_PRICE_TYPE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "netResePrTy",
						GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_TYPE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_FORMULA_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.NET_RESET_PRICE_FORMULA_ID,
						GtnFrameworkWebserviceConstant.IMPSD,
						GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_FORMULA_ID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_PRICE_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "netPricTy",
						GtnFrameworkWebserviceConstant.PS_NET_PRICE_TYPE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_FORMULA_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.NET_RESET_PRICE_FORMULA_ID,
						GtnFrameworkWebserviceConstant.IMPSD,
						GtnFrameworkWebserviceConstant.PS_NET_RESET_PRICE_FORMULA_NAME,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_ATTACHED_DATE,
				configProvider.getColumnDateConfig("ITEM_PS_ATTACHED_DATE", GtnFrameworkWebserviceConstant.IMPSD));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_BASE_PRICE_DATE,
				configProvider.getColumnDateConfig(GtnFrameworkWebserviceConstant.BASE_PRICE_DATE,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPPViewColumnDetailsMap.put(GtnFrameworkWebserviceConstant.PS_BASE_PRICE_DDLB,
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.BASE_PRICE_DDLB,
						GtnFrameworkWebserviceConstant.IMPSD));
		psPPViewColumnDetailsMap.put("psBasePriceTypeDes",
				configProvider.getColumnStringConfig("DESCRIPTION", "baseType"));
		psPPViewColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnStringConfig("PS_DETAILS_SID", GtnFrameworkWebserviceConstant.IMPSD));
		psPPViewColumnDetailsMap.put("IMPSD.PS_MODEL_SID", configProvider.getColumnIntegerConfig(
				GtnFrameworkWebserviceConstant.PS_MODEL_SID, GtnFrameworkWebserviceConstant.IMPSD));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(psPPViewColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("IM.ITEM_NO", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(
				" FROM	PS_DETAILS IMPSD JOIn item_master im on	im.ITEM_MASTER_SID = IMPSD.ITEM_MASTER_SID LEFT JOIN BRAND_MASTER BM ON	BM.BRAND_MASTER_SID = IMPSD.BRAND_MASTER_SID LEFT JOIN HELPER_TABLE PP_STATUS ON	PP_STATUS.HELPER_TABLE_SID = IMPSD.PRICE_PROTECTION_STATUS LEFT JOIN dbo.ITEM_PRICING_QUALIFIER PP_TYPE ON	PP_TYPE.ITEM_PRICING_QUALIFIER_SID = IMPSD.PRICE_PROTECTION_PRICE_TYPE LEFT JOIN dbo.ITEM_PRICING_QUALIFIER NEtSEBSEQTY ON	NEtSEBSEQTY.ITEM_PRICING_QUALIFIER_SID = IMPSD.PRICE_PROTECTION_PRICE_TYPE LEFT JOIN HELPER_TABLE htolInt on	htolint.HELPER_TABLE_SID = IMPSD.PRICE_TOLERANCE_INTERVAL LEFT JOIN HELPER_TABLE htoltyp on	htoltyp.HELPER_TABLE_SID = IMPSD.PRICE_TOLERANCE_TYPE LEFT JOIN HELPER_TABLE htolfrq on	htolfrq.HELPER_TABLE_SID = IMPSD.PRICE_TOLERANCE_FREQUENCY LEFT JOIN HELPER_TABLE bpriceType on	bpriceType.HELPER_TABLE_SID = IMPSD.BASE_PRICE_TYPE LEFT JOIN HELPER_TABLE netBasePrice on	netBasePrice.HELPER_TABLE_SID = IMPSD.NET_BASE_PRICE LEFT JOIN HELPER_TABLE subseqPeriodPr on	subseqPeriodPr.HELPER_TABLE_SID = IMPSD.SUBSEQUENT_PERIOD_PRICE_TYPE LEFT JOIN HELPER_TABLE netsubseqPeriodPr on 	netsubseqPeriodPr.HELPER_TABLE_SID = IMPSD.NET_SUBSEQUENT_PERIOD_PRICE LEFT JOIN HELPER_TABLE resetEli on	resetEli.HELPER_TABLE_SID = IMPSD.RESET_ELIGIBLE LEFT JOIN HELPER_TABLE resetType on	resetType.HELPER_TABLE_SID = IMPSD.RESET_TYPE LEFT JOIN HELPER_TABLE reseInter on	reseInter.HELPER_TABLE_SID = IMPSD.RESET_INTERVAL LEFT JOIN HELPER_TABLE reseFreq on	reseFreq.HELPER_TABLE_SID = IMPSD.RESET_FREQUENCY LEFT JOIN HELPER_TABLE reseprTy on	reseprTy.HELPER_TABLE_SID = IMPSD.RESET_PRICE_TYPE LEFT JOIN HELPER_TABLE netResePrTy on	netResePrTy.HELPER_TABLE_SID = IMPSD.NET_RESET_PRICE_TYPE LEFT JOIN HELPER_TABLE netPricTy on	netPricTy.HELPER_TABLE_SID = IMPSD.NET_PRICE_TYPE"
						+ " LEFT JOIN HELPER_TABLE baseType on baseType.HELPER_TABLE_SID = IMPSD.BASE_PRICE_TYPE");

		gtnWebServiceSearchQueryContext.setSearchQuery(
				" FROM	PS_DETAILS IMPSD JOIn item_master im on	im.ITEM_MASTER_SID = IMPSD.ITEM_MASTER_SID LEFT JOIN BRAND_MASTER BM ON	BM.BRAND_MASTER_SID = IMPSD.BRAND_MASTER_SID LEFT JOIN HELPER_TABLE PP_STATUS ON	PP_STATUS.HELPER_TABLE_SID = IMPSD.PRICE_PROTECTION_STATUS LEFT JOIN dbo.ITEM_PRICING_QUALIFIER PP_TYPE ON	PP_TYPE.ITEM_PRICING_QUALIFIER_SID = IMPSD.PRICE_PROTECTION_PRICE_TYPE LEFT JOIN dbo.ITEM_PRICING_QUALIFIER NEtSEBSEQTY ON	NEtSEBSEQTY.ITEM_PRICING_QUALIFIER_SID = IMPSD.PRICE_PROTECTION_PRICE_TYPE LEFT JOIN HELPER_TABLE htolInt on	htolint.HELPER_TABLE_SID = IMPSD.PRICE_TOLERANCE_INTERVAL LEFT JOIN HELPER_TABLE htoltyp on	htoltyp.HELPER_TABLE_SID = IMPSD.PRICE_TOLERANCE_TYPE LEFT JOIN HELPER_TABLE htolfrq on	htolfrq.HELPER_TABLE_SID = IMPSD.PRICE_TOLERANCE_FREQUENCY LEFT JOIN HELPER_TABLE bpriceType on	bpriceType.HELPER_TABLE_SID = IMPSD.BASE_PRICE_TYPE LEFT JOIN HELPER_TABLE netBasePrice on	netBasePrice.HELPER_TABLE_SID = IMPSD.NET_BASE_PRICE LEFT JOIN HELPER_TABLE subseqPeriodPr on	subseqPeriodPr.HELPER_TABLE_SID = IMPSD.SUBSEQUENT_PERIOD_PRICE_TYPE LEFT JOIN HELPER_TABLE netsubseqPeriodPr on 	netsubseqPeriodPr.HELPER_TABLE_SID = IMPSD.NET_SUBSEQUENT_PERIOD_PRICE LEFT JOIN HELPER_TABLE resetEli on	resetEli.HELPER_TABLE_SID = IMPSD.RESET_ELIGIBLE LEFT JOIN HELPER_TABLE resetType on	resetType.HELPER_TABLE_SID = IMPSD.RESET_TYPE LEFT JOIN HELPER_TABLE reseInter on	reseInter.HELPER_TABLE_SID = IMPSD.RESET_INTERVAL LEFT JOIN HELPER_TABLE reseFreq on	reseFreq.HELPER_TABLE_SID = IMPSD.RESET_FREQUENCY LEFT JOIN HELPER_TABLE reseprTy on	reseprTy.HELPER_TABLE_SID = IMPSD.RESET_PRICE_TYPE LEFT JOIN HELPER_TABLE netResePrTy on	netResePrTy.HELPER_TABLE_SID = IMPSD.NET_RESET_PRICE_TYPE LEFT JOIN HELPER_TABLE netPricTy on	netPricTy.HELPER_TABLE_SID = IMPSD.NET_PRICE_TYPE"
						+ " LEFT JOIN HELPER_TABLE baseType on baseType.HELPER_TABLE_SID = IMPSD.BASE_PRICE_TYPE");

		searchQueryConfigMap.put("priceSchedulePriceProtectionView", gtnWebServiceSearchQueryContext);

	}

	public static void loadPSNetSalesFormulaMapping() {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.NEP_FORMULA, "IMTD_RSD"));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);
		searchQueryConfigMap.put("NEP Formula", gtnWebServiceSearchQueryContext);
	}

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		return searchQueryConfigMap;
	}
}
