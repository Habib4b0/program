package com.stpl.gtn.gtn2o.ws.forecastnewarch;

import java.util.ArrayList;
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
		return fromPeriodItemValueList != null ? new ArrayList<>(fromPeriodItemValueList) : fromPeriodItemValueList;
	}

	public void setFromPeriodItemValueList(List<String> fromPeriodItemValueList) {
		this.fromPeriodItemValueList = fromPeriodItemValueList != null ? new ArrayList<>(fromPeriodItemValueList) : fromPeriodItemValueList;
	}

	public List<Integer> getFromPeriodItemCodeList() {
		return fromPeriodItemCodeList != null ? new ArrayList<>(fromPeriodItemCodeList) : fromPeriodItemCodeList;
	}

	public void setFromPeriodItemCodeList(List<Integer> fromPeriodItemCodeList) {
		this.fromPeriodItemCodeList = fromPeriodItemCodeList != null ? new ArrayList<>(fromPeriodItemCodeList) : fromPeriodItemCodeList;
	}

	public List<String> getToPeriodItemValueList() {
		return toPeriodItemValueList != null ? new ArrayList<>(toPeriodItemValueList) : toPeriodItemValueList;
	}

	public void setToPeriodItemValueList(List<String> toPeriodItemValueList) {
		this.toPeriodItemValueList = toPeriodItemValueList != null ? new ArrayList<>(toPeriodItemValueList) : toPeriodItemValueList;
	}

	public List<Integer> getToPeriodItemCodeList() {
		return toPeriodItemCodeList != null ? new ArrayList<>(toPeriodItemCodeList) : toPeriodItemCodeList;
	}

	public void setToPeriodItemCodeList(List<Integer> toPeriodItemCodeList) {
		this.toPeriodItemCodeList = toPeriodItemCodeList != null ? new ArrayList<>(toPeriodItemCodeList) : toPeriodItemCodeList;
	}

}
