package com.stpl.gtn.gtn2o.serviceregistry.webservices;

import java.util.List;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.serviceregistry.constants.GtnWsServiceRegistryConstants;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

public class GtnValidateWsServiceRegistryService extends GtnCommonWebServiceImplClass{
	
	public boolean serviceRegistryServiceToValidateWsIsRegistered(GtnWsServiceRegistryBean serviceRegistryBean){
		
		GtnQueryEngineWebServiceRequest queryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		
		queryExecutorBean.setSqlQuery(GtnWsServiceRegistryConstants.SELECT_QUERY);
		queryExecutorBean.setParams(new Object[]{serviceRegistryBean.getRegisteredWebContext()});
		queryExecutorBean.setDataType(new GtnFrameworkDataType[]{GtnFrameworkDataType.STRING});
		queryExecutorBean.setQueryType("SELECTWITHPARAMS");
		queryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
				
		GtnCommonWebServiceImplClass gtnCommonWebServiceImplClass = new GtnValidateWsServiceRegistryService();
		GtnQueryEngineWebServiceResponse response = gtnCommonWebServiceImplClass.callQueryEngineWithoutSecurityToken("/executeQuery", queryEngineWebServiceRequest);
		List<Object[]> resultList = response.getQueryResponseBean().getResultList();
		for(int i=0;i<resultList.size();i++){
			Object[] obj = resultList.get(i);
			for(int j=0;j<obj.length;j++){
				if(obj[j].equals(serviceRegistryBean.getRegisteredWebContext())){
					return true;
				}
			}
		}
	
		return false;
	}
	
	public String getWebServiceEndpointBasedOnModule(String url, String moduleName) {
		return GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointUrl")
				+ GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointServiceName")
				+ url;

	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		return null;
	}
	
}
