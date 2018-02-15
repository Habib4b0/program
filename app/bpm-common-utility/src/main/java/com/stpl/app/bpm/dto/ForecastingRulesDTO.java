package com.stpl.app.bpm.dto;

import java.io.Serializable;

public class ForecastingRulesDTO implements Serializable {

	public ForecastingRulesDTO() {
		// TODO Auto-generated constructor stub
	}

	public ForecastingRulesDTO(String variableName) {
		this.variableName = variableName;

		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String variableName = "";

	private double amountLowest;
	private double percentLowest;

	private double amountGreatest;
	private double percentGreatest;

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public double getAmountLowest() {
		return amountLowest;
	}

	public void setAmountLowest(double amountLowest) {
		this.amountLowest = amountLowest;
	}

	public double getPercentLowest() {
		return percentLowest;
	}

	public void setPercentLowest(double percentLowest) {
		this.percentLowest = percentLowest;
	}

	public double getAmountGreatest() {
		return amountGreatest;
	}

	public void setAmountGreatest(double amountGreatest) {
		this.amountGreatest = amountGreatest;
	}

	public double getPercentGreatest() {
		return percentGreatest;
	}

	public void setPercentGreatest(double percentGreatest) {
		this.percentGreatest = percentGreatest;
	}

}
