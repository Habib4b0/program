package com.stpl.gtn.gtn2o.ws.hierarchyrelationship.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.singleton.bean.GtnFrameworkSingletonObjectBean;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.sqlservice.GtnWsHierarchyAndRelationshipSqlService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service
public class GtnWsRelationshipLevelValueService extends GtnCommonWebServiceImplClass {

	@Autowired
	private GtnWsHierarchyAndRelationshipSqlService gtnWsHierarchyAndRelationshipSqlService;

	@PostConstruct
	private void initializeLogger() {
		super.logInformation(GtnWsRelationshipLevelValueService.class);
	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		return null;
	}

	private GtnWsRelationshipLevelValueService(){
		super();
	}
	
	private GtnFrameworkSingletonObjectBean singletonObjectBean = GtnFrameworkSingletonObjectBean.getInstance();

	public void getRelationshipLevelValues(GtnUIFrameworkWebserviceRequest request) {

		if (singletonObjectBean.getRelationshipLevelValuesResultList() != null
				&& isRelationshipLevelValueListDistinct(singletonObjectBean.getRelationshipLevelValuesResultList())) {
			return;
		}
		int relationshipbuildersid = 674;
		String relationshipLevelLoadQuery = "SELECT * FROM DBO.RELATIONSHIP_LEVEL_DEFINITION WHERE RELATIONSHIP_BUILDER_SID = ?";
		// gtnWsHierarchyAndRelationshipSqlService.getQuery("loadRelationshipLevelValues");

		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		Object[] params = new Object[1];
		params[0] = relationshipbuildersid;

		GtnFrameworkDataType[] dataType = new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER };

		queryExecutorBean.setSqlQuery(relationshipLevelLoadQuery);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(dataType);
		queryExecutorBean.setQueryType("SELECTWITHPARAMS");

		GtnQueryEngineWebServiceRequest queryEngineRequest = new GtnQueryEngineWebServiceRequest();
		queryEngineRequest.setQueryExecutorBean(queryExecutorBean);

		GtnCommonWebServiceImplClass webServiceImpl = new GtnWsRelationshipLevelValueService();

		GtnQueryEngineWebServiceResponse response = webServiceImpl.callQueryEngineWithoutSecurityToken("/executeQuery",
				queryEngineRequest);

		List<Object[]> resultList = response.getQueryResponseBean().getResultList();

		singletonObjectBean.setRelationshipLevelValuesResultList(resultList);
	}

	private boolean isRelationshipLevelValueListDistinct(List<Object[]> list) {
		for (int i = 0; i < list.size(); i++) {
			if ((int) list.get(i)[1] == 674) {
				return true;
			}
		}
		return false;
	}

}
