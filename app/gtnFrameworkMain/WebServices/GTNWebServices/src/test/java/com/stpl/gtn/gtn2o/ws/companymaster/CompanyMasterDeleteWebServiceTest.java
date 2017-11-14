package com.stpl.gtn.gtn2o.ws.companymaster;

import org.junit.Ignore;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.module.companymaster.controller.GtnWsCMasterDelete;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.ServiceContextUtil;

@Ignore
public class CompanyMasterDeleteWebServiceTest {
	@Test
	public void testcompanyMasterDeleteService() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCMasterDelete instance = (GtnWsCMasterDelete) ServiceContextUtil.instance().getBean("cmDeleteService");
		GtnCMasterBean cmBean = new GtnCMasterBean();
		GtnCMasterInformationBean companyInformationBean = new GtnCMasterInformationBean();
		companyInformationBean.setCompanyMasterSystemId(519639);
		cmBean.setGtnCMasterInformationBean(companyInformationBean);
		gtnWsRequest.getGtnCMasterRequest().setGtnCMasterBean(cmBean);
		GtnUIFrameworkWebserviceResponse response = instance.companyMasterDeleteService(gtnWsRequest);
		System.out.println("Response from WS::::::::::::" + response.getGtnWsGeneralResponse().isSucess());
	}
}
