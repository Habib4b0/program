package com.stpl.gtn.gtn2o.serviceregistry.webservices;

import java.util.List;


import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.singleton.bean.GtnFrameworkSingletonObjectBean;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.serviceregistry.constants.GtnWsServiceRegistryConstants;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

public class GtnValidateWsServiceRegistryService extends GtnCommonWebServiceImplClass {

	private GtnValidateWsServiceRegistryService() {
		super(GtnValidateWsServiceRegistryService.class);

	}

	public boolean serviceRegistryServiceToValidateWsIsRegistered(GtnWsServiceRegistryBean serviceRegistryBean) {
		logger.info("Entered into Validating isWebserviceRegistered");
		GtnFrameworkSingletonObjectBean singletonObjectBean = GtnFrameworkSingletonObjectBean.getInstance();

		List<Object[]> resultList = null;

		if (singletonObjectBean.getValidateServiceRegistryRegisteredWsResultList() == null
				|| !isRegisteredWs(serviceRegistryBean,
						singletonObjectBean.getValidateServiceRegistryRegisteredWsResultList())) {
			resultList = getRegisteredWs(serviceRegistryBean);
			singletonObjectBean.setValidateServiceRegistryRegisteredWsResultList(resultList);
		}
		logger.info("Returning Response for Webservice Register:" + isRegisteredWs(serviceRegistryBean,
				singletonObjectBean.getValidateServiceRegistryRegisteredWsResultList()));
		return isRegisteredWs(serviceRegistryBean,
				singletonObjectBean.getValidateServiceRegistryRegisteredWsResultList());
	}

	private List<Object[]> getRegisteredWs(GtnWsServiceRegistryBean serviceRegistryBean) {
		GtnQueryEngineWebServiceRequest queryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		logger.queryLog("Query:" + GtnWsServiceRegistryConstants.SELECT_QUERY);
		logger.queryLog("Query Parameters:" + serviceRegistryBean.getRegisteredWebContext());
		queryExecutorBean.setSqlQuery(GtnWsServiceRegistryConstants.SELECT_QUERY);
		queryExecutorBean.setParams(new Object[] { serviceRegistryBean.getRegisteredWebContext() });
		queryExecutorBean.setDataType(new GtnFrameworkDataType[] { GtnFrameworkDataType.STRING });
		queryExecutorBean.setQueryType("SELECTWITHPARAMS");
		queryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);

		GtnCommonWebServiceImplClass gtnCommonWebServiceImplClass = new GtnValidateWsServiceRegistryService();
		GtnQueryEngineWebServiceResponse response = gtnCommonWebServiceImplClass
				.callQueryEngineWithoutSecurityToken("/executeQuery", queryEngineWebServiceRequest);
		List<Object[]> resultList = response.getQueryResponseBean().getResultList();
		logger.debug("Response:" + resultList.get(0));
		return resultList;
	}

	private boolean isRegisteredWs(GtnWsServiceRegistryBean serviceRegistryBean, List<Object[]> resultList) {
		for (int i = 0; i < resultList.size(); i++) {
			Object[] obj = resultList.get(i);
			for (int j = 0; j < obj.length; j++) {
				if (obj[j].equals(serviceRegistryBean.getRegisteredWebContext())) {
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
