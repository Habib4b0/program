package com.stpl.gtn.gtn2o.ws.response;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsCsvExportBean;

public class GtnWsCsvExportResponse {

	public GtnWsCsvExportResponse() {
		super();
	}

	private String fileName;
	private GtnWsCsvExportBean gtnWsCsvExportBean;

	public GtnWsCsvExportBean getGtnWsCsvExportBean() {
		return gtnWsCsvExportBean;
	}

	public void setGtnWsCsvExportBean(GtnWsCsvExportBean gtnWsCsvExportBean) {
		this.gtnWsCsvExportBean = gtnWsCsvExportBean;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
