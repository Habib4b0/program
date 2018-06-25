package com.stpl.gtn.gtn2o.ws.report.service;

import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

public interface GtnWsReportDataSelectionGenerate {

	public void dataSelectionGenerateLogic(GtnUIFrameworkWebserviceRequest gtnWsRequest);

	public void dataSelectionRegenerateLogic(GtnUIFrameworkWebserviceRequest gtnWsRequest);

	public void regenerateTreeAndData(GtnUIFrameworkWebserviceRequest gtnWsRequest);

}
