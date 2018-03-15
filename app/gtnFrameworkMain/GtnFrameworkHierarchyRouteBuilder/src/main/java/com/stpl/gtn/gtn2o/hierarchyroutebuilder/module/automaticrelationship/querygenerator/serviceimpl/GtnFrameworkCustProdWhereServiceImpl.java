package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.service.GtnFrameworkWhereQueryGeneratorService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;

@Component("CustProdWhere")
@Scope(value = "singleton")
public class GtnFrameworkCustProdWhereServiceImpl implements GtnFrameworkWhereQueryGeneratorService {

	public GtnFrameworkCustProdWhereServiceImpl() {
		super();
	}

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	@Override
	public void addWhereClause(GtnFrameworkQueryGeneratorBean querygeneratorBean,
			GtnWsRelationshipBuilderBean relationBean) {
		querygeneratorBean.removeAllWhereClauseConfigList();
		hierarchyService.getInboundRestrictionQueryForAutoUpdate(querygeneratorBean);

	}

}
