package com.stpl.gtn.gtn2o.ws.module.processscheduler.config;

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
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.constant.GtnWsCffQueryConstants;

public class GtnWebServiceCffOutBoundSearchConfig implements GtnWsSearchQueryConfigLoader {
	
	private Map<String, GtnWsSearchQueryConfig> cffOutboundSearchQueryConfigMap = null;
	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWebServiceCffOutBoundSearchConfig.class);

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if(cffOutboundSearchQueryConfigMap == null) {
			cffOutboundSearchQueryConfigMap = new HashMap<>();
			loadSearchQueryConfig();
		}
		return cffOutboundSearchQueryConfigMap;
	}
	
	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.cffOutboundSearchQueryConfigMap = searchQueryConfigMap;
	}

	private Map<String, GtnWsSearchQueryConfig> loadSearchQueryConfig() {
		GtnWsSearchQueryConfig gtnWsSearchQueryConfig = new GtnWsSearchQueryConfig();
		try {
			gtnWsSearchQueryConfig.setCountQuerySelectClause(GtnWsCffQueryConstants.GTN_CFF_SEARCH_QUERY_SELECT);
			gtnWsSearchQueryConfig.setCountQuery(GtnWsCffQueryConstants.GTN_IFP_SEARCH_QUERY);
			gtnWsSearchQueryConfig.setSearchQuery(GtnWsCffQueryConstants.GTN_IFP_SEARCH_QUERY);
			
			Map<String, GtnWsColumnDetailsConfig> cffColumnDetailsMap = new HashMap<>(25);
			GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
			cffColumnDetailsMap.put("CffOutBound_ProjectionId", configProvider.getColumnIntegerConfig("FINANCIAL_FORECAST_ID", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_CffId", configProvider.getColumnIntegerConfig("CFF_DETAILS_SID", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_CffName", configProvider.getColumnStringConfig("PROJECTION_NAME", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_ProjectionName", configProvider.getColumnStringConfig("FINANCIAL_FORECAST_NAME", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_CustomerNo", configProvider.getColumnStringConfig("CUSTOMER_NO", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_CustomerName", configProvider.getColumnStringConfig("CUSTOMER_NAME", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_CompanyNo", configProvider.getColumnStringConfig("COMPANY_NO", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_CompanyName", configProvider.getColumnStringConfig("COMPANY_NAME", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_contarctNo", configProvider.getColumnStringConfig("CONTRACT_HOLDER_NO", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_contractName", configProvider.getColumnStringConfig("CONTRACT_HOLDER_NAME", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_itemNo", configProvider.getColumnStringConfig("ITEM_NO", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_itemName", configProvider.getColumnStringConfig("ITEM_NAME", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_businessUnitNo", configProvider.getColumnStringConfig("BUSINESS_UNIT_NO", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_businessUnitName", configProvider.getColumnStringConfig("BUSINESS_UNIT_NAME", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_type", configProvider.getColumnStringConfig("DESCRIPTION", "CFT"));
			cffColumnDetailsMap.put( "CffOutBound_cffCreationDateFrom", configProvider.getColumnDateConfig("FINANCIAL_FORECAST_CREATION_DATE >", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_cffApprovalDateFrom", configProvider.getColumnDateConfig("FINANCIAL_FORECAST_APPROVAL_DATE >", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_cffApprovalDateTo", configProvider.getColumnDateConfig("FINANCIAL_FORECAST_APPROVAL_DATE <", "CFFOM"));
			cffColumnDetailsMap.put("CffOutBound_cffCreationDateTo", configProvider.getColumnDateConfig("FINANCIAL_FORECAST_CREATION_DATE <", "CFFOM"));
			
			cffColumnDetailsMap.put("contractType", configProvider.getColumnDateConfig
					(" CASE " 
							+ "WHEN HT1.DESCRIPTION = '-Select One-' THEN NULL " 
							+ "ELSE HT1.DESCRIPTION " 
							+ "END ", ""));
			
			cffColumnDetailsMap.put("sessionId", configProvider.getColumnStringConfig("SESSION_ID", "ST"));
			cffColumnDetailsMap.put("userId", configProvider.getColumnStringConfig("USER_ID", "ST"));
			
			cffOutboundSearchQueryConfigMap.put("cffOutBoundSearchQuery", gtnWsSearchQueryConfig);
			gtnWsSearchQueryConfig.setFieldToColumnDetailsMap(cffColumnDetailsMap);
			
			List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
			orderByClauseList.add(new GtnWebServiceOrderByCriteria("CFFOM.FINANCIAL_FORECAST_ID", "ASC"));
			gtnWsSearchQueryConfig.setOrderByClause(orderByClauseList);
			//gtnWsSearchQueryConfig.setWhereClauseList(Arrays.asList(" CFFOM.FINANCIAL_FORECAST_ID LIKE '%' "));
			return cffOutboundSearchQueryConfigMap;
		
		}
		catch(Exception exp) {
			logger.info("Exception in loadSearchQuery config of Process Schedular CFF OUTBOUND "+exp);
		}
		return cffOutboundSearchQueryConfigMap; 
		
	}

}
