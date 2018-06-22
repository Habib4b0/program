/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.rebateplan.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.util.GtnWsConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnWebServiceRebatePlanConfig implements GtnWsSearchQueryConfigLoader {

	private static Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();

	static {
		loadSearchQueryConfig();
	}

	public static void loadSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>(50);
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("systemId", configProvider.getColumnStringConfig("REBATE_PLAN_MASTER_SID", "RP"));
		fieldToColumnDetailsMap.put("rebatePlanId", configProvider.getColumnStringConfig("REBATE_PLAN_ID", "RP"));
		fieldToColumnDetailsMap.put("rebatePlanNo", configProvider.getColumnStringConfig("REBATE_PLAN_NO", "RP"));
		fieldToColumnDetailsMap.put("rebatePlanName", configProvider.getColumnStringConfig("REBATE_PLAN_NAME", "RP"));
		fieldToColumnDetailsMap.put("secondaryRebatePlanNo",
				configProvider.getColumnStringConfig("REBATE_PLAN_NO", "RP"));
		fieldToColumnDetailsMap.put("secondaryRebatePlanName",
				configProvider.getColumnStringConfig("REBATE_PLAN_NAME", "RP"));
		fieldToColumnDetailsMap.put("rebatePlanStatus",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "STATUS", "RP_STATUS",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebatePlanType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "TYPE", "RP_TYPE",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rpPopUpRebatePlanType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "TYPE", "RP_TYPE",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebatePlanBasedOn",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "RP_BASED", "RP_BASED",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));

		GtnWsColumnDetailsConfig rpColumnDetailsMap = configProvider.getColumnHelperConfig("REBATE_STRUCTURE", "RP");
		rpColumnDetailsMap.setHelperTableAliasName("rpStructureHelper");
		rpColumnDetailsMap.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("rebateStructure", rpColumnDetailsMap);

		fieldToColumnDetailsMap.put("rangeBasedOn",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "RP_RANGE_BASED",
						"RP_RANGE_BASED", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("netSalesFormula",
				configProvider.getColumnStringConfig("NET_SALES_FORMULA_NAME", "NSFM"));
		fieldToColumnDetailsMap.put("netSalesRule", configProvider.getColumnStringConfig("RULE_NAME", "CDRM"));
		fieldToColumnDetailsMap.put("creationDate", configProvider.getColumnDateConfig("CREATED_DATE", "RP"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnStringConfig("firstName+' '+userCreated.lastName", "userCreated", "createdByCol"));
                
		fieldToColumnDetailsMap.put("modifiedDate", configProvider.getColumnDateConfig("MODIFIED_DATE", "RP"));
                fieldToColumnDetailsMap.put("modifiedBy", configProvider
					.getColumnStringConfig("firstName+' '+userModifiedBy.lastName", "userModifiedBy", "modifiedByCol")); 
		fieldToColumnDetailsMap.put("recordLockStatus",
				configProvider.getColumnStringConfig("RECORD_LOCK_STATUS", "RP"));
		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		// Financial Close Table Load Data Starts
		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("RP.REBATE_PLAN_Id", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery("  FROM REBATE_PLAN_MASTER RP\n"
				+ "JOIN HELPER_TABLE STATUS ON STATUS.HELPER_TABLE_SID=RP.REBATE_PLAN_STATUS\n"
				+ "JOIN HELPER_TABLE TYPE ON TYPE.HELPER_TABLE_SID=RP.REBATE_PLAN_TYPE\n"
				+ "JOIN HELPER_TABLE RP_BASED ON RP_BASED.HELPER_TABLE_SID=RP.REBATE_BASED_ON\n"
				+ "JOIN HELPER_TABLE RP_STRUCTURE ON RP_STRUCTURE.HELPER_TABLE_SID=RP.REBATE_STRUCTURE\n"
				+ "JOIN HELPER_TABLE RP_RANGE_BASED ON RP_RANGE_BASED.HELPER_TABLE_SID=RP.REBATE_RANGE_BASED_ON\n"
				+ "LEFT JOIN NET_SALES_FORMULA_MASTER NSFM ON NSFM.NET_SALES_FORMULA_MASTER_SID=RP.NET_SALES_FORMULA_MASTER_SID\n"
				+ "LEFT JOIN CDR_MODEL CDRM ON CDRM.CDR_MODEL_SID=RP.CDR_MODEL_SID "
				+ "LEFT JOIN HELPER_TABLE rpStructureHelper on RP.REBATE_STRUCTURE = rpStructureHelper.HELPER_TABLE_SID\n"
                                + " JOIN @SYS.dbo.User_ userCreated on userCreated.userId = RP.CREATED_BY\n"
                                + " JOIN @SYS.dbo.User_ userModifiedBy on userCreated.userId = RP.MODIFIED_BY");

		gtnWebServiceSearchQueryContext.setSearchQuery("  FROM REBATE_PLAN_MASTER RP\n"
				+ "JOIN HELPER_TABLE STATUS ON STATUS.HELPER_TABLE_SID=RP.REBATE_PLAN_STATUS\n"
				+ "JOIN HELPER_TABLE TYPE ON TYPE.HELPER_TABLE_SID=RP.REBATE_PLAN_TYPE\n"
				+ "JOIN HELPER_TABLE RP_BASED ON RP_BASED.HELPER_TABLE_SID=RP.REBATE_BASED_ON\n"
				+ "JOIN HELPER_TABLE RP_STRUCTURE ON RP_STRUCTURE.HELPER_TABLE_SID=RP.REBATE_STRUCTURE\n"
				+ "JOIN HELPER_TABLE RP_RANGE_BASED ON RP_RANGE_BASED.HELPER_TABLE_SID=RP.REBATE_RANGE_BASED_ON\n"
				+ "LEFT JOIN NET_SALES_FORMULA_MASTER NSFM ON NSFM.NET_SALES_FORMULA_MASTER_SID=RP.NET_SALES_FORMULA_MASTER_SID\n"
				+ "LEFT JOIN CDR_MODEL CDRM ON CDRM.CDR_MODEL_SID=RP.CDR_MODEL_SID "
				+ "LEFT JOIN HELPER_TABLE rpStructureHelper on RP.REBATE_STRUCTURE = rpStructureHelper.HELPER_TABLE_SID"
                                + " JOIN @SYS.dbo.User_ userCreated on userCreated.userId = RP.CREATED_BY\n"
                                + " JOIN @SYS.dbo.User_ userModifiedBy on userCreated.userId = RP.MODIFIED_BY\n");

		searchQueryConfigMap.put("rebateplanSearch", gtnWebServiceSearchQueryContext);

	}

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		return searchQueryConfigMap;
	}
}
