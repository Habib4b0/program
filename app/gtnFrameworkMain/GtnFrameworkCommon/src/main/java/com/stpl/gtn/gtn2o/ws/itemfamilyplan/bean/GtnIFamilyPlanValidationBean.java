/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.itemfamilyplan.bean;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnIFamilyPlanValidationBean {

	public GtnIFamilyPlanValidationBean() {
		super();
	}

	private int count;
	private int startDateNullCount;
	private int statusNullCount;
	private int startDateGreaterThanEndCount;
	private int checkedCount;
	private String startDateNullItemId;
	private String statusNullItemId;
	private String startDateGreaterThanEndItemId;
	private int startDateEqualCount;
	private String startDateEqualItemId;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStartDateNullCount() {
		return startDateNullCount;
	}

	public void setStartDateNullCount(int startDateNullCount) {
		this.startDateNullCount = startDateNullCount;
	}

	public int getStatusNullCount() {
		return statusNullCount;
	}

	public void setStatusNullCount(int statusNullCount) {
		this.statusNullCount = statusNullCount;
	}

	public int getStartDateGreaterThanEndCount() {
		return startDateGreaterThanEndCount;
	}

	public void setStartDateGreaterThanEndCount(int startDateGreaterThanEndCount) {
		this.startDateGreaterThanEndCount = startDateGreaterThanEndCount;
	}

	public int getCheckedCount() {
		return checkedCount;
	}

	public void setCheckedCount(int checkedCount) {
		this.checkedCount = checkedCount;
	}

	public String getStartDateNullItemId() {
		return startDateNullItemId;
	}

	public void setStartDateNullItemId(String startDateNullItemId) {
		this.startDateNullItemId = startDateNullItemId;
	}

	public String getStatusNullItemId() {
		return statusNullItemId;
	}

	public void setStatusNullItemId(String statusNullItemId) {
		this.statusNullItemId = statusNullItemId;
	}

	public String getStartDateGreaterThanEndItemId() {
		return startDateGreaterThanEndItemId;
	}

	public void setStartDateGreaterThanEndItemId(String startDateGreaterThanEndItemId) {
		this.startDateGreaterThanEndItemId = startDateGreaterThanEndItemId;
	}

	public int getStartDateEqualCount() {
		return startDateEqualCount;
	}

	public void setStartDateEqualCount(int startDateEqualCount) {
		this.startDateEqualCount = startDateEqualCount;
	}

	public String getStartDateEqualItemId() {
		return startDateEqualItemId;
	}

	public void setStartDateEqualItemId(String startDateEqualItemId) {
		this.startDateEqualItemId = startDateEqualItemId;
	}

}
