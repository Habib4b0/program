package com.stpl.gtn.gtn2o.ws.module.contractheader.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;
import com.stpl.gtn.gtn2o.ws.module.contractheader.constants.GtnWsContractHeaderQueryContants;

@Service
public class GtnWsContractHeaderConfig implements GtnWsSearchQueryConfigLoader {

	private volatile Map<String, GtnWsSearchQueryConfig> contractHeaderConfigMap = null;

	@Override
	public synchronized Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (contractHeaderConfigMap == null) {
			contractHeaderConfigMap = new HashMap<>();
			loadSearchQueryConfig();
		}
		return contractHeaderConfigMap;
	}

	public synchronized void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.contractHeaderConfigMap = searchQueryConfigMap;
	}

	public Map<String, GtnWsSearchQueryConfig> loadSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();

		gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsContractHeaderQueryContants.GTN_CONTRACT_HEADER_SEARCH_QUERY);
		gtnWebServiceSearchQueryConfig.setSearchQuery(gtnWebServiceSearchQueryConfig.getCountQuery());
		gtnWebServiceSearchQueryConfig
				.setCountQuerySelectClause(GtnWsContractHeaderQueryContants.GTN_CONTRACT_HEADER_SELECT_CLAUSE);

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put("contractMasterSId",
				configProvider.getColumnIntegerConfig("CONTRACT_MASTER_SID", "CON"));
		fieldToColumnDetailsMap.put("contractId", configProvider.getColumnStringConfig("CONTRACT_ID", "CON"));
		fieldToColumnDetailsMap.put("contractHeaderSearchCriteriaContractId",
				configProvider.getColumnStringConfig("CONTRACT_ID", "CON"));
		fieldToColumnDetailsMap.put("contractNo", configProvider.getColumnStringConfig("CONTRACT_NO", "CON"));
		fieldToColumnDetailsMap.put("contractHeaderSearchCriteriaContractNo",
				configProvider.getColumnStringConfig("CONTRACT_NO", "CON"));
		fieldToColumnDetailsMap.put("contractName", configProvider.getColumnStringConfig("CONTRACT_NAME", "CON"));
		fieldToColumnDetailsMap.put("contractHeaderSearchCriteriaContractName",
				configProvider.getColumnStringConfig("CONTRACT_NAME", "CON"));
		fieldToColumnDetailsMap.put("contractStatus", configProvider.getColumnHelperConfig("CONTRACT_STATUS", "CON"));
		fieldToColumnDetailsMap.put("contractHeaderSearchCriteriaContractStatus",
				configProvider.getColumnHelperConfig("CONTRACT_STATUS", "CON"));
		fieldToColumnDetailsMap.put("contractType", configProvider.getColumnHelperConfig("CONTRACT_TYPE", "CON"));
		fieldToColumnDetailsMap.put("contractHeaderSearchCriteriaContractType",
				configProvider.getColumnHelperConfig("CONTRACT_TYPE", "CON"));
		fieldToColumnDetailsMap.put("tradeClass", configProvider.getColumnHelperConfig("CONTRACT_TRADE_CLASS", "CON"));
		fieldToColumnDetailsMap.put("contractHeaderSearchCriteriaTradeClass",
				configProvider.getColumnStringConfig("CONTRACT_TRADE_CLASS", "CON"));
		fieldToColumnDetailsMap.put("tradingPartnerName",
				configProvider.getColumnStringConfig("COMPANY_NO", "COMPTRADE"));
		fieldToColumnDetailsMap.put("landingScreenTradingPartner",
				configProvider.getColumnStringConfig("COMPANY_NO", "COMPTRADE"));

		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("CON.CONTRACT_ID", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);
		gtnWebServiceSearchQueryConfig
				.setWhereClauseList(Arrays.asList(GtnWsContractHeaderQueryContants.GTN_CONTRACT_HEADER_WHERE_CLAUSE));

		contractHeaderConfigMap.put("contractHeaderSearchQuery", gtnWebServiceSearchQueryConfig);

		return contractHeaderConfigMap;
	}
}
