package com.stpl.dependency.queryengine.bean;

import java.util.List;

public class GtnFrameworkQueryResponseBean {

	private String resultString;
	private int resultInteger;
	private List<Object[]> resultList;

	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

	public int getResultInteger() {
		return resultInteger;
	}

	public void setResultInteger(int resultInteger) {
		this.resultInteger = resultInteger;
	}

	public List<Object[]> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object[]> resultList) {
		this.resultList = resultList;
	}

}
