package com.stpl.gtn.gtn2o.ws.periodconf.model;

import java.util.ArrayList;
import java.util.List;

public class PeriodConfData {

	private String fromDate;
	private String toDate;
	private String description;
	private List<Object[]> quaterInfo = new ArrayList<>();
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public List<Object[]> getQuaterInfo() {
		return quaterInfo;
	}
	public void setQuaterInfo(List<Object[]> quaterInfo) {
		this.quaterInfo = quaterInfo;
	}
	
	
}
