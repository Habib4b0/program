/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.calendarfield;

import java.util.Calendar;

import org.asi.calendarfield.CalendarField.Matrix;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkCalendarConfig {

	private Calendar rangeStartDate = Calendar.getInstance();
	private Calendar rangeEndDate = Calendar.getInstance();
	private boolean immediate = true;
	private boolean yearResolution = true;
	private Matrix displayMatrix = Matrix.ROW_2xCOL_6;

	public GtnUIFrameworkCalendarConfig() {
		rangeStartDate.set(rangeStartDate.get(Calendar.YEAR), 0, 1, 0, 0, 0);
		rangeEndDate.set(rangeEndDate.get(Calendar.YEAR), 11, 31, 0, 0, 0);
	}

	public Calendar getRangeStartDate() {
		return rangeStartDate;
	}

	public void setRangeStartDate(Calendar rangeStartDate) {
		this.rangeStartDate = rangeStartDate;
	}

	public Calendar getRangeEndDate() {
		return rangeEndDate;
	}

	public void setRangeEndDate(Calendar rangeEndDate) {
		this.rangeEndDate = rangeEndDate;
	}

	public boolean isImmediate() {
		return immediate;
	}

	public void setImmediate(boolean immediate) {
		this.immediate = immediate;
	}

	public boolean isYearResolution() {
		return yearResolution;
	}

	public void setYearResolution(boolean yearResolution) {
		this.yearResolution = yearResolution;
	}

	public Matrix getDisplayMatrix() {
		return displayMatrix;
	}

	public void setDisplayMatrix(Matrix displayMatrix) {
		this.displayMatrix = displayMatrix;
	}

}
