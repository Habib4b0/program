package com.stpl.gtn.gtn2o.ui.config;

import org.apache.commons.lang3.builder.Builder;

import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

public class GtnUIFrameworkWebServiceReportRequestBuilder implements Builder<GtnUIFrameworkWebserviceRequest> {

	GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
	GtnWsReportRequest reportRequest = new GtnWsReportRequest();
	

	public GtnUIFrameworkWebServiceReportRequestBuilder withDataSelectionBean() {
		GtnWsReportDataSelectionBean bean = new GtnWsReportDataSelectionBean();
		reportRequest.getReportBean().setDataSelectionBean(bean);
		return this;
	}

	public GtnUIFrameworkWebServiceReportRequestBuilder withCustomViewBean() {
		GtnWsReportCustomViewBean bean = new GtnWsReportCustomViewBean();
		reportRequest.getReportBean().setCustomViewBean(bean);
		return this;
	}

	@Override
	public GtnUIFrameworkWebserviceRequest build() {
		request.setGtnReportRequest(reportRequest);
		return request;
	}
}
