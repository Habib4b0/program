package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.serviceimpl.deduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.service.GtnFrameworkWhereQueryGeneratorService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;

@Component("DeductionWhere")
@Scope(value = "singleton")
public class GtnFrameworkDedutionWhereServiceImpl implements GtnFrameworkWhereQueryGeneratorService {

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	public GtnFrameworkDedutionWhereServiceImpl() {
		super();
	}

	public void addWhereClause(GtnFrameworkQueryGeneratorBean querygeneratorBean,
			GtnWsRelationshipBuilderBean relationBean) throws GtnFrameworkGeneralException {
		querygeneratorBean.removeWhereClauseConfigListByIndex(1,
				querygeneratorBean.getWhereClauseConfigList().size() - 1);
		hierarchyService.getInboundRestrictionQueryForAutoUpdate(querygeneratorBean);
		querygeneratorBean.addWhereClauseBean("RS_CONTRACT_DETAILS.ITEM_MASTER_SID", null, GtnFrameworkOperatorType.IN,
				GtnFrameworkDataType.NULL_ALLOWED, null);
	}

}
