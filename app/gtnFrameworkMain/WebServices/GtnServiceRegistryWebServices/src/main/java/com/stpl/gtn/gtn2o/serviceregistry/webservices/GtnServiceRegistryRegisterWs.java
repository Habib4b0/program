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

	private GtnServiceRegistryRegisterWs() {
		super(GtnServiceRegistryRegisterWs.class);
		}

	public void serviceRegistryRegisterWebServices(GtnUIFrameworkWebserviceRequest request) {
		logger.info("Registering Webservices in Service Registry");
		GtnQueryEngineWebServiceRequest queryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();

		GtnWsServiceRegistryBean serviceRegistryBean = request.getGtnServiceRegistryWsRequest()
				.getGtnWsServiceRegistryBean();

		Object[] params = new Object[3];
		params[0] = serviceRegistryBean.getRegisteredWebContext();
		params[1] = serviceRegistryBean.getWebserviceEndPointUrl();
		params[2] = serviceRegistryBean.getRegisteredWebContext();

		GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };

		String registerQuery = GtnWsServiceRegistryConstants.INSERT_QUERY;

		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(dataType);
		queryExecutorBean.setSqlQuery(registerQuery);
		queryExecutorBean.setQueryType("INSERTORUPDATEWITHPARAMS");

		queryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);

		GtnCommonWebServiceImplClass gtnCommonWebServiceImplClass = new GtnServiceRegistryRegisterWs();

		gtnCommonWebServiceImplClass.callQueryEngineWithoutSecurityToken("/executeQuery", queryEngineWebServiceRequest);

		logger.info("Webservices Registered in Service Registry");

	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		return null;
	}
}
