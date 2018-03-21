package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.forecasting.discount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Service
@Scope(value = "singleton")
public class GtnFrameworkDiscountQueryGenerator {

	@Autowired
	private GtnFrameworkFileReadWriteService fileReadWriteService;

	public String getQueryForLoadingDiscount(GtnForecastHierarchyInputBean inputBean,
			HierarchyLevelDefinitionBean selectedHierarchyLevelDto) {

		GtnFrameworkQueryGeneratorBean queryBaen = getQueryForLinkedLevel(selectedHierarchyLevelDto);
		queryBaen.removeAllWhereClauseConfigList();
		GtnFrameworkJoinClauseBean ccpJoin = queryBaen.addJoinClauseBean("CCP_DETAILS", "CCP_DETAILS",
				GtnFrameworkJoinType.INNER_JOIN);
		if (inputBean.getHierarchyIndicator().equals("C")) {
			ccpJoin.addConditionBean("CCP_DETAILS.COMPANY_MASTER_SID", "COMPANY_MASTER.COMPANY_MASTER_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			ccpJoin.addConditionBean("CCP_DETAILS.CONTRACT_MASTER_SID", "CONTRACT_MASTER.CONTRACT_MASTER_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			} else {
			ccpJoin.addConditionBean("CCP_DETAILS.ITEM_MASTER_SID", "ITEM_MASTER.ITEM_MASTER_SID",
					GtnFrameworkOperatorType.EQUAL_TO);
			}
		GtnFrameworkJoinClauseBean tempJOin = queryBaen.addJoinClauseBean("ST_CCP_HIERARCHY", "ST_CCP_HIERARCHY",
				GtnFrameworkJoinType.INNER_JOIN);
		tempJOin.addConditionBean(" ST_CCP_HIERARCHY.CCP_DETAILS_SID", "CCP_DETAILS.CCP_DETAILS_SID",
				GtnFrameworkOperatorType.EQUAL_TO);
		return queryBaen.generateQuery();
	}

	private GtnFrameworkQueryGeneratorBean getQueryForLinkedLevel(
			HierarchyLevelDefinitionBean selectedHierarchyLevelDto) {
		GtnFrameworkHierarchyQueryBean queryBaen = fileReadWriteService.getQueryFromFile(
				selectedHierarchyLevelDto.getHierarchyDefinitionSid(),
				selectedHierarchyLevelDto.getHierarchyLevelDefinitionSid(), selectedHierarchyLevelDto.getVersionNo());
		return queryBaen.getQuery();

	}
}
