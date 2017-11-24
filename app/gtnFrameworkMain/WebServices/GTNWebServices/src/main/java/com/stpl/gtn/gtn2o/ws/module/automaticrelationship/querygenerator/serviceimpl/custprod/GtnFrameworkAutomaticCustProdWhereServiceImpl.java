package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.custprod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkWhereQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;

@Component("CustProdWhere")
@Scope(value = "singleton")
public class GtnFrameworkAutomaticCustProdWhereServiceImpl implements GtnFrameworkWhereQueryGeneratorService {

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;
	@Override
	public void addWhereClause(GtnFrameworkQueryGeneratorBean querygeneratorBean,
			GtnWsRelationshipBuilderBean relationBean) {
		hierarchyService.getInboundRestrictionQueryForAutoUpdate(querygeneratorBean);

	}

}
