package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.Collections;
import java.util.List;

public class GtnReportDataRefreshBean {

        public GtnReportDataRefreshBean() {
            super();
        }

	private int customViewMasterSid;
	private String frequencyName;
	private List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList;

	public int getCustomViewMasterSid() {
		return customViewMasterSid;
	}

	public void setCustomViewMasterSid(int customViewMasterSid) {
		this.customViewMasterSid = customViewMasterSid;
	}

	public String getFrequencyName() {
		return frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}

	public List<GtnReportComparisonProjectionBean> getComparisonProjectionBeanList() {
		return comparisonProjectionBeanList == null ? null : Collections.unmodifiableList(comparisonProjectionBeanList);
	}

	public void setComparisonProjectionBeanList(List<GtnReportComparisonProjectionBean> comparisonProjectionBeanList) {
		this.comparisonProjectionBeanList = comparisonProjectionBeanList == null ? null
				: Collections.unmodifiableList(comparisonProjectionBeanList);
	}

}
