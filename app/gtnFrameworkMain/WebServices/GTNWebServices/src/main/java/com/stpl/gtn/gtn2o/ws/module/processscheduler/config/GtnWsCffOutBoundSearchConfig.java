package com.stpl.gtn.gtn2o.ws.module.processscheduler.config;

import java.util.ArrayList;
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
import org.springframework.stereotype.Service;

@Service()
public class GtnWsCffOutBoundSearchConfig implements GtnWsSearchQueryConfigLoader {
	
	private Map<String, GtnWsSearchQueryConfig> cffOutboundSearchQueryConfigMap = null;
	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsCffOutBoundSearchConfig.class);

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
			gtnWsSearchQueryConfig.setCountQuery(GtnWsCffQueryConstants.GTN_CFF_SEARCH_QUERY);
			gtnWsSearchQueryConfig.setSearchQuery(GtnWsCffQueryConstants.GTN_CFF_SEARCH_QUERY);
			
			Map<String, GtnWsColumnDetailsConfig> cffColumnDetailsMap = new HashMap<>(70);
			GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
			cffColumnDetailsMap.put("CffOutBound_ProjectionId", configProvider.getColumnStringConfig("FINANCIAL_FORECAST_ID", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_CffId", configProvider.getColumnIntegerConfig("CFF_DETAILS_SID", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_CffName", configProvider.getColumnStringConfig("PROJECTION_NAME", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_ProjectionName", configProvider.getColumnStringConfig("FINANCIAL_FORECAST_NAME", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_CustomerNo", configProvider.getColumnStringConfig("CUSTOMER_NO", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_CustomerName", configProvider.getColumnStringConfig("CUSTOMER_NAME", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_CompanyNo", configProvider.getColumnStringConfig("COMPANY_NO", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_CompanyName", configProvider.getColumnStringConfig("COMPANY_NAME", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_contractNo", configProvider.getColumnStringConfig("CONTRACT_HOLDER_NO", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_contractName", configProvider.getColumnStringConfig("CONTRACT_HOLDER_NAME", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_itemNo", configProvider.getColumnStringConfig("ITEM_NO", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_itemName", configProvider.getColumnStringConfig("ITEM_NAME", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_businessUnitNo", configProvider.getColumnStringConfig("BUSINESS_UNIT_NO", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_businessUnitName", configProvider.getColumnStringConfig("BUSINESS_UNIT_NAME", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_type", configProvider.getColumnStringConfig("DESCRIPTION", "CFT"));
			cffColumnDetailsMap.put( "CffOutBound_cffCreationDateFrom", configProvider.getColumnDateConfig("FINANCIAL_FORECAST_CREATION_DATE >", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_cffApprovalDateFrom", configProvider.getColumnDateConfig("FINANCIAL_FORECAST_APPROVAL_DATE >", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_cffApprovalDateTo", configProvider.getColumnDateConfig("FINANCIAL_FORECAST_APPROVAL_DATE <", GtnWsCffQueryConstants.CFFOM));
			cffColumnDetailsMap.put("CffOutBound_cffCreationDateTo", configProvider.getColumnDateConfig("FINANCIAL_FORECAST_CREATION_DATE <", GtnWsCffQueryConstants.CFFOM));
			
			cffColumnDetailsMap.put("contractType", configProvider.getColumnStringConfig
					(" CASE WHEN HT1.DESCRIPTION = '-Select One-' THEN NULL " 
							+ "ELSE HT1.DESCRIPTION END ", ""));
			
			cffColumnDetailsMap.put("year", configProvider.getColumnStringConfig("YEAR", ""));
			cffColumnDetailsMap.put("month", configProvider.getColumnStringConfig("MONTH", ""));
			cffColumnDetailsMap.put("contractId", configProvider.getColumnStringConfig("CONTRACT_ID", ""));
			cffColumnDetailsMap.put("contractHolderId", configProvider.getColumnStringConfig("CONTRACT_HOLDER_ID", ""));
			cffColumnDetailsMap.put("contractHolderNo", configProvider.getColumnStringConfig("CONTRACT_HOLDER_NO", ""));
			cffColumnDetailsMap.put("contractHolderName", configProvider.getColumnStringConfig("CONTRACT_HOLDER_NAME", ""));
			cffColumnDetailsMap.put("customerId", configProvider.getColumnStringConfig("CUSTOMER_ID", ""));
			cffColumnDetailsMap.put("itemId", configProvider.getColumnStringConfig("ITEM_ID", ""));
			cffColumnDetailsMap.put("salesDollars", configProvider.getColumnStringConfig("SALES_DOLLARS", ""));
			cffColumnDetailsMap.put("salesUnits", configProvider.getColumnStringConfig("SALES_UNITS", ""));
			cffColumnDetailsMap.put("salesInclusion", configProvider.getColumnStringConfig("SALES_INCLUSION", ""));
			
			cffColumnDetailsMap.put("deductionId", configProvider.getColumnStringConfig("DEDUCTION_ID", ""));
			cffColumnDetailsMap.put("deductionNo", configProvider.getColumnStringConfig("DEDUCTION_NO", ""));
			cffColumnDetailsMap.put("deductionName", configProvider.getColumnStringConfig("DEDUCTION_NAME", ""));
			
			cffColumnDetailsMap.put("deductionCategory", configProvider.getColumnStringConfig
					(" CASE WHEN HT2.DESCRIPTION = '-Select One-' THEN NULL " 
							+ "ELSE HT2.DESCRIPTION END ", ""));
			cffColumnDetailsMap.put("deductionType", configProvider.getColumnStringConfig
					(" CASE WHEN HT3.DESCRIPTION = '-Select One-' THEN NULL " 
							+ "ELSE HT3.DESCRIPTION END ", ""));
			cffColumnDetailsMap.put("deductionProgram", configProvider.getColumnStringConfig
					(" CASE WHEN HT4.DESCRIPTION = '-Select One-' THEN NULL " 
							+ "ELSE HT4.DESCRIPTION END ", ""));
			cffColumnDetailsMap.put("deductionInclusion", configProvider.getColumnStringConfig
					(" CASE WHEN HT5.DESCRIPTION = '-Select One-' THEN NULL " 
							+ "ELSE HT5.DESCRIPTION END ", ""));
			cffColumnDetailsMap.put("deductionCategoryOne", configProvider.getColumnStringConfig
					(" CASE WHEN HT6.DESCRIPTION = '-Select One-' THEN NULL " 
							+ "ELSE HT6.DESCRIPTION END ", ""));
			cffColumnDetailsMap.put("deductionCategoryTwo", configProvider.getColumnStringConfig
					(" CASE WHEN HT7.DESCRIPTION = '-Select One-' THEN NULL " 
							+ "ELSE HT7.DESCRIPTION END ", ""));
			cffColumnDetailsMap.put("deductionCategoryThree", configProvider.getColumnStringConfig
					(" CASE WHEN HT8.DESCRIPTION = '-Select One-' THEN NULL " 
							+ "ELSE HT8.DESCRIPTION END ", ""));
			cffColumnDetailsMap.put("deductionCategoryFour", configProvider.getColumnStringConfig
					(" CASE WHEN HT9.DESCRIPTION = '-Select One-' THEN NULL " 
							+ "ELSE HT9.DESCRIPTION END ", ""));
			cffColumnDetailsMap.put("deductionCategoryFive", configProvider.getColumnStringConfig
					(" CASE WHEN HT10.DESCRIPTION = '-Select One-' THEN NULL " 
							+ "ELSE HT10.DESCRIPTION END ", ""));
			cffColumnDetailsMap.put("deductionCategorySix", configProvider.getColumnStringConfig
					(" CASE WHEN HT11.DESCRIPTION = '-Select One-' THEN NULL " 
							+ "ELSE HT11.DESCRIPTION END ", ""));
			
			cffColumnDetailsMap.put("deductionDollars", configProvider.getColumnStringConfig("DEDUCTION_DOLLARS", ""));
			cffColumnDetailsMap.put("deductionRate", configProvider.getColumnStringConfig("DEDUCTION_RATE", ""));
			cffColumnDetailsMap.put("deductionPerUnit", configProvider.getColumnStringConfig("DEDUCTION_PER_UNIT", ""));
			
			cffColumnDetailsMap.put("netSalesDollar", configProvider.getColumnStringConfig("NET_SALES_DOLLAR", ""));
			cffColumnDetailsMap.put("cogsAmount", configProvider.getColumnStringConfig("COGS_AMOUNT", ""));
			cffColumnDetailsMap.put("cogsPerUnit", configProvider.getColumnStringConfig("COGS_PER_UNIT", ""));
			cffColumnDetailsMap.put("netProfitDollars", configProvider.getColumnStringConfig("NET_PROFIT_DOLLARS", ""));
			cffColumnDetailsMap.put("netProfitPerUnit", configProvider.getColumnStringConfig("NET_PROFIT_PER_UNIT", ""));
			cffColumnDetailsMap.put("companyId", configProvider.getColumnStringConfig("COMPANY_ID", ""));
			
			cffColumnDetailsMap.put("businessUnitId", configProvider.getColumnStringConfig("BUSINESS_UNIT_ID", ""));
			cffColumnDetailsMap.put("financialForecastCreationDate", configProvider.getColumnDateConfig("FINANCIAL_FORECAST_CREATION_DATE", ""));
			cffColumnDetailsMap.put("financialForecastApprovalDate", configProvider.getColumnDateConfig("FINANCIAL_FORECAST_APPROVAL_DATE", ""));
			cffColumnDetailsMap.put("outboundStatus", configProvider.getColumnStringConfig("OUTBOUND_STATUS", ""));
			cffColumnDetailsMap.put("originalBatchId", configProvider.getColumnStringConfig("ORIGINAL_BATCH_ID", ""));
			
			cffColumnDetailsMap.put("checkRecordId", configProvider.getColumnBooleanConfig("CHECK_RECORD", GtnWsCffQueryConstants.ST));
			
			cffColumnDetailsMap.put("rsModelSid", configProvider.getColumnStringConfig("RS_MODEL_SID",GtnWsCffQueryConstants.ST));
			cffColumnDetailsMap.put("periodSid", configProvider.getColumnStringConfig("PERIOD_SID", GtnWsCffQueryConstants.ST));
						
			cffColumnDetailsMap.put("sessionId", configProvider.getColumnStringConfig("SESSION_ID", GtnWsCffQueryConstants.ST));
			cffColumnDetailsMap.put("userId", configProvider.getColumnStringConfig("USER_ID", GtnWsCffQueryConstants.ST));
			
			cffOutboundSearchQueryConfigMap.put("cffOutBoundSearchQuery", gtnWsSearchQueryConfig);
			gtnWsSearchQueryConfig.setFieldToColumnDetailsMap(cffColumnDetailsMap);
			
			
			
			List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
			orderByClauseList.add(new GtnWebServiceOrderByCriteria("CFFOM.FINANCIAL_FORECAST_ID", "ASC"));
			gtnWsSearchQueryConfig.setOrderByClause(orderByClauseList);
			return cffOutboundSearchQueryConfigMap;
		
		}
		catch(Exception exp) {
			logger.error("Exception in loadSearchQuery config of Process Schedular CFF OUTBOUND "+exp);
		}
		return cffOutboundSearchQueryConfigMap; 
		
	}

}
