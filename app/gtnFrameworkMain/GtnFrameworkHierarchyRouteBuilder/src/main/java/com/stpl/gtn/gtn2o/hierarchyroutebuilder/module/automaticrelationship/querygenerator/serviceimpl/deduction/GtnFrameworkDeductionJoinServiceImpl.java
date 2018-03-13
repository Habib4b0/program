package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.serviceimpl.deduction;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.service.GtnFrameworkJoinQueryGeneratorService;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Component("DeductionJoin")
@Scope(value = "singleton")
public class GtnFrameworkDeductionJoinServiceImpl implements GtnFrameworkJoinQueryGeneratorService {

	public GtnFrameworkDeductionJoinServiceImpl() {
		super();
	}
	@Override
	public void addJoinClause(GtnFrameworkQueryGeneratorBean querygeneratorBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList, int levelNo) {
		GtnFrameworkJoinClauseBean rsDetailsJoin = querygeneratorBean.addJoinClauseBean("RS_CONTRACT_DETAILS",
				"RS_CONTRACT_DETAILS", GtnFrameworkJoinType.JOIN);
		rsDetailsJoin.addConditionBean("RS_CONTRACT.RS_CONTRACT_SID", "RS_CONTRACT_DETAILS.RS_CONTRACT_SID",
				GtnFrameworkOperatorType.EQUAL_TO);
	}


}
