package com.stpl.gtn.gtn2o.ws.forecastnewarch;

import java.util.List;



public class GtnFrameworkFromAndToLoadBean {

	private static GtnFrameworkFromAndToLoadBean instance = null;
	private List<String> fromPeriodItemValueList;
	private List<Integer> fromPeriodItemCodeList;
	private List<String> toPeriodItemValueList;
	private List<Integer> toPeriodItemCodeList;
	private GtnFrameworkFromAndToLoadBean() {

	}

	public static GtnFrameworkFromAndToLoadBean getInstance() {
		if (instance == null) {
			instance = new GtnFrameworkFromAndToLoadBean();
		}
		return instance;
	}
	public List<String> getFromPeriodItemValueList() {
		return fromPeriodItemValueList;
	}
	public void setFromPeriodItemValueList(List<String> fromPeriodItemValueList) {
		this.fromPeriodItemValueList = fromPeriodItemValueList;
	}
	public List<Integer> getFromPeriodItemCodeList() {
		return fromPeriodItemCodeList;
	}
	public void setFromPeriodItemCodeList(List<Integer> fromPeriodItemCodeList) {
		this.fromPeriodItemCodeList = fromPeriodItemCodeList;
	}
	public List<String> getToPeriodItemValueList() {
		return toPeriodItemValueList;
	}
	public void setToPeriodItemValueList(List<String> toPeriodItemValueList) {
		this.toPeriodItemValueList = toPeriodItemValueList;
	}
	public List<Integer> getToPeriodItemCodeList() {
		return toPeriodItemCodeList;
	}
	public void setToPeriodItemCodeList(List<Integer> toPeriodItemCodeList) {
		this.toPeriodItemCodeList = toPeriodItemCodeList;
	}

}
