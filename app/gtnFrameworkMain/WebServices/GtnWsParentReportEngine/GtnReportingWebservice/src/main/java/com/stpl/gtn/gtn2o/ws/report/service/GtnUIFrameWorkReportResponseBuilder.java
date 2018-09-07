package com.stpl.gtn.gtn2o.ws.report.service;

import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.report.GtnWsReportResponse;

public class GtnUIFrameWorkReportResponseBuilder {
	private GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
	private GtnWsReportResponse reportResponse = new GtnWsReportResponse();

	public GtnUIFrameWorkReportResponseBuilder withDataSelectionBean() {
		GtnWsReportDataSelectionBean bean = new GtnWsReportDataSelectionBean();
		reportResponse.getReportBean().setDataSelectionBean(bean);
		return this;
	}

	public GtnUIFrameWorkReportResponseBuilder withCustomViewBean() {
		GtnWsReportCustomViewBean bean = new GtnWsReportCustomViewBean();
		reportResponse.getReportBean().setCustomViewBean(bean);
		return this;
	}

	public GtnUIFrameworkWebserviceResponse build() {
		response.setGtnWsReportResponse(reportResponse);
		return response;
	}

}
