package com.stpl.gtn.gtn2o.serviceregistry.webservices;

import org.springframework.stereotype.Component;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.serviceregistry.constants.GtnWsServiceRegistryConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

@Component
public class GtnServiceRegistryRegisterWs extends GtnCommonWebServiceImplClass {

	public void serviceRegistryRegisterWebServices(GtnUIFrameworkWebserviceRequest request){
		
		GtnQueryEngineWebServiceRequest queryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();

		GtnWsServiceRegistryBean serviceRegistryBean = request.getGtnServiceRegistryWsRequest()
				.getGtnWsServiceRegistryBean();

		Object[] params = new Object[4];
		params[0] = serviceRegistryBean.getRegisteredWebContext();
		params[1] = serviceRegistryBean.getHostName();
		params[2] = serviceRegistryBean.getPort();
		params[3] = serviceRegistryBean.getRegisteredWebContext();

		GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };

		String registerQuery = GtnWsServiceRegistryConstants.INSERT_QUERY;

		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(dataType);
		queryExecutorBean.setSqlQuery(registerQuery);
		queryExecutorBean.setQueryType("INSERTORUPDATEWITHPARAMS");

		queryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);

		GtnCommonWebServiceImplClass gtnCommonWebServiceImplClass = new GtnServiceRegistryRegisterWs();
		
		gtnCommonWebServiceImplClass.callQueryEngineWithoutSecurityToken("/executeQuery", queryEngineWebServiceRequest);

	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		return null;
	}
}
