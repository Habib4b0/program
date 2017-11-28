package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;

public interface GtnFrameworkWhereQueryGeneratorService {

	void addWhereClause(GtnFrameworkQueryGeneratorBean querygeneratorBean, GtnWsRelationshipBuilderBean relationBean)
			throws GtnFrameworkGeneralException;

}
