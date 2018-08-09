package com.stpl.gtn.gtn2o.serviceregistry.webservices;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.queryengine.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.GtnWsQueryType;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.serviceregistry.bean.GtnWsServiceRegistryBean;
import com.stpl.gtn.gtn2o.serviceregistry.constants.GtnWsServiceRegistryConstants;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;

public class GtnValidateWsServiceRegistryService {
	
	public boolean serviceRegistryServiceToValidateWsIsRegistered(GtnWsServiceRegistryBean serviceRegistryBean){
		
		GtnQueryEngineWebServiceRequest queryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		
		queryExecutorBean.setSqlQuery(GtnWsServiceRegistryConstants.SELECT_QUERY);
		queryExecutorBean.setParams(new Object[]{serviceRegistryBean.getRegisteredWebContext()});
		queryExecutorBean.setDataType(new GtnFrameworkDataType[]{GtnFrameworkDataType.STRING});
		queryExecutorBean.setQueryType(GtnWsQueryType.SELECTWITHPARAMS);
		queryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		
		RestTemplate restTemplate = new RestTemplate();
		GtnQueryEngineWebServiceResponse response = restTemplate.postForObject(getWebServiceEndpointBasedOnModule("/executeQuery","queryEngine"), queryEngineWebServiceRequest,
				GtnQueryEngineWebServiceResponse.class);
		List resultList = (List) response.getResult();
		for(int i=0;i<resultList.size();i++){
			
			if(((List)resultList.get(i)).contains(serviceRegistryBean.getRegisteredWebContext())){
				return true;
			}
		}
	
		return false;
	}
	
	public String getWebServiceEndpointBasedOnModule(String url, String moduleName) {
		return GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointUrl")
				+ GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointServiceName")
				+ url;

	}
	
}
