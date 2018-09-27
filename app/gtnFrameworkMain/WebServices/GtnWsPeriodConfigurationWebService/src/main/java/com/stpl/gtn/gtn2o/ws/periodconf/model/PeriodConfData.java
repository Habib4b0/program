package com.stpl.gtn.gtn2o.ws.periodconf.model;

import java.util.ArrayList;
import java.util.List;

public class PeriodConfData {

	private String fromDate;
	private String toDate;
	private String description;
	private List<Object[]> quaterInfo = new ArrayList<>();

	public PeriodConfData() {
		super();
	}

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
		 List<Object[]> quaterInfoCopy = new ArrayList<>();
		 quaterInfoCopy.addAll(quaterInfo);
		 return quaterInfoCopy;
	}

	public void setQuaterInfo(List<Object[]> quaterInfo) {
		List<Object[]> quaterInfoCopy = new ArrayList<>(quaterInfo);
		this.quaterInfo = quaterInfoCopy;
	}

}
