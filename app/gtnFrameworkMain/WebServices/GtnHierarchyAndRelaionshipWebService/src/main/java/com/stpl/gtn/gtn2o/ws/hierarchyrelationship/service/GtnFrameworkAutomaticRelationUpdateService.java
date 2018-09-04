package com.stpl.gtn.gtn2o.ws.hierarchyrelationship.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.hierarchyrelationship.sqlservice.GtnWsHierarchyAndRelationshipSqlService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service(value = "gtnFrameworkAutomaticRelationUpdateService")
public class GtnFrameworkAutomaticRelationUpdateService extends GtnCommonWebServiceImplClass {

	@Autowired
	private GtnWsHierarchyAndRelationshipSqlService gtnWsHierarchyAndRelationshipSqlService;

	public List<HierarchyLevelDefinitionBean> getHierarchyBuilder(Integer hierarchyBuilderSid, int hierarchyVersionNo)
			throws GtnFrameworkGeneralException {
		List<Object> input = new ArrayList<>();
		input.add(hierarchyBuilderSid);
		input.add(hierarchyVersionNo);
		String finalQuery = gtnWsHierarchyAndRelationshipSqlService.getQuery(input, "hierarchyByidandVersionNo");
		return executeAndGetCustomizedResult(finalQuery);
	}

	private List<HierarchyLevelDefinitionBean> executeAndGetCustomizedResult(String finalQuery)
			throws GtnFrameworkGeneralException {
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setSqlQuery(finalQuery);
		queryExecutorBean.setQueryType("SELECT");
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		GtnCommonWebServiceImplClass webServiceImpl = new GtnFrameworkAutomaticRelationUpdateService();
		GtnQueryEngineWebServiceResponse response = webServiceImpl.callQueryEngineWithoutSecurityToken("/executeQuery",
				gtnQueryEngineWebServiceRequest);
		List<Object[]> resultList = (List<Object[]>) response.getQueryResponseBean().getResultList();
		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = new ArrayList<>();
		for (Object[] objects : resultList) {
			HierarchyLevelDefinitionBean hierarchyBean = new HierarchyLevelDefinitionBean();
			getIntegerValue(0, objects);
			hierarchyBean.setHierarchyLevelDefinitionSid(getIntegerValue(0, objects));
			hierarchyBean.setHierarchyDefinitionSid(getIntegerValue(1, objects));
			hierarchyBean.setLevelName(objects[2] == null ? "" : objects[2].toString());
			hierarchyBean.setLevelValueReference(objects[3] == null ? "" : objects[3].toString());
			hierarchyBean.setLevelNo(getIntegerValue(4, objects));
			hierarchyBean.setTableName(objects[5] == null ? "" : objects[5].toString());
			hierarchyBean.setFieldName(objects[6] == null ? "" : objects[6].toString());
			hierarchyBean.setVersionNo(getIntegerValue(7, objects));
			hierarchyBean.setDefaultVlaue(getIntegerValue(8, objects));
			hierarchyDefinitionList.add(hierarchyBean);
		}
		return hierarchyDefinitionList;
	}

	public int getIntegerValue(int index, Object[] objects) {
		return objects[index] == null ? index : Integer.parseInt(objects[index].toString());
	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		return null;
	}

}
