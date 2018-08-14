package com.stpl.gtn.gtn2o.ws.module.processscheduler.config;

import java.util.HashMap;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
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

	private void loadSearchQueryConfig() {
		GtnWsSearchQueryConfig gtnWsSearchQueryConfig = new GtnWsSearchQueryConfig();
		try {
			gtnWsSearchQueryConfig.setCountQuerySelectClause(GtnWsCffQueryConstants.GTN_CFF_SEARCH_QUERY_SELECT);
			gtnWsSearchQueryConfig.setCountQuery(GtnWsCffQueryConstants.GTN_IFP_SEARCH_QUERY);
			gtnWsSearchQueryConfig.setSearchQuery(GtnWsCffQueryConstants.GTN_IFP_SEARCH_QUERY);
			
			Map<String, GtnWsColumnDetailsConfig> cffColumnDetailsMap = new HashMap<>(25);
			GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
			cffColumnDetailsMap.put("financialForecastId", configProvider.getColumnStringConfig("FINANCIAL_FORECAST_ID", "CFFOM"));
		}
		catch(Exception exp) {
			logger.info("Exception in loadSearchQuery config of Process Schedular CFF OUTBOUND "+exp);
		} 
		
	}

}
