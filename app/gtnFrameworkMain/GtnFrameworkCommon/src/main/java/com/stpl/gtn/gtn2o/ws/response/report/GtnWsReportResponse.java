package com.stpl.gtn.gtn2o.ws.response.report;

import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportBean;

public class GtnWsReportResponse {
	private GtnWsReportBean reportBean = new GtnWsReportBean();

	public GtnWsReportBean getReportBean() {
		return reportBean;
	}

	public void setReportBean(GtnWsReportBean reportBean) {
		this.reportBean = reportBean;
	}
}
