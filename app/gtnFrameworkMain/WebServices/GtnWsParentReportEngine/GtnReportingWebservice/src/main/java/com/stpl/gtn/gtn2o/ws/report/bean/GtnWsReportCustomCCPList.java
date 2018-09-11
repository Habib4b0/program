package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class GtnWsReportCustomCCPList {

	public GtnWsReportCustomCCPList() {
		super();
	}

	private List<GtnWsReportCustomCCPListDetails> gtnWsReportCustomCCPListDetails;

	public List<GtnWsReportCustomCCPListDetails> getGtnWsReportCustomCCPListDetails() {
		return gtnWsReportCustomCCPListDetails != null ? new ArrayList<>(gtnWsReportCustomCCPListDetails) : null;
	}

	public void setGtnWsReportCustomCCPListDetails(
			List<GtnWsReportCustomCCPListDetails> gtnWsReportCustomCCPListDetails) {
		this.gtnWsReportCustomCCPListDetails = gtnWsReportCustomCCPListDetails != null
				? new ArrayList<>(gtnWsReportCustomCCPListDetails)
				: null;
	}

}
