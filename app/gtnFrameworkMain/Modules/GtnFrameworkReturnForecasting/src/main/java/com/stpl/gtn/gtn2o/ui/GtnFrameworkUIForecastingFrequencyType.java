/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui;

/**
 *
 * @author Nimisha.Rakesh
 */
public enum GtnFrameworkUIForecastingFrequencyType {
	MONTHLY(new String[] { "36", "Months" }), QUARTERLY(new String[] { "12", "Quarters" }), SEMI_ANNUALLY(
			new String[] { "6", "Semi-Annual Periods" }), ANNUALLY(new String[] { "3", "Year" });

	private final String[] frequencyData;

	GtnFrameworkUIForecastingFrequencyType(String[] frequencyData) {
		this.frequencyData = frequencyData.clone();
	}

	public String[] getFrequencyData() {
		return frequencyData.clone();
	}

}
