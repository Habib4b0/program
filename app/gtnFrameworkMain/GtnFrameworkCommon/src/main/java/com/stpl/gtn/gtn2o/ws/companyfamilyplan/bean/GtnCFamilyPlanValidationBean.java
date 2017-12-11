/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.companyfamilyplan.bean;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnCFamilyPlanValidationBean {

	public GtnCFamilyPlanValidationBean() {
		super();
	}

	private int count;
	private int startDateNullCount;
	private int statusNullCount;
	private int startDateGreaterThanEndCount;
	private int duplicateCompanyCount;
	private int checkedCount;
	private String startDateNullCompanyId;
	private String statusNullCompanyId;
	private String startDateGreaterThanEndCompanyId;
	private String duplicateCompanyId;
	private int startDateEqualsEndCount;
	private String startDateEqualsCompanyId;

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

	public int getStartDateGreaterThanEndCount() {
		return startDateGreaterThanEndCount;
	}

	public void setStartDateGreaterThanEndCount(int startDateGreaterThanEndCount) {
		this.startDateGreaterThanEndCount = startDateGreaterThanEndCount;
	}

	public int getDuplicateCompanyCount() {
		return duplicateCompanyCount;
	}

	public void setDuplicateCompanyCount(int duplicateCompanyCount) {
		this.duplicateCompanyCount = duplicateCompanyCount;
	}

	public int getCheckedCount() {
		return checkedCount;
	}

	public void setCheckedCount(int checkedCount) {
		this.checkedCount = checkedCount;
	}

	public String getStartDateNullCompanyId() {
		return startDateNullCompanyId;
	}

	public void setStartDateNullCompanyId(String startDateNullCompanyId) {
		this.startDateNullCompanyId = startDateNullCompanyId;
	}

	public String getStatusNullCompanyId() {
		return statusNullCompanyId;
	}

	public void setStatusNullCompanyId(String statusNullCompanyId) {
		this.statusNullCompanyId = statusNullCompanyId;
	}

	public String getStartDateGreaterThanEndCompanyId() {
		return startDateGreaterThanEndCompanyId;
	}

	public void setStartDateGreaterThanEndCompanyId(String startDateGreaterThanEndCompanyId) {
		this.startDateGreaterThanEndCompanyId = startDateGreaterThanEndCompanyId;
	}

	public String getDuplicateCompanyId() {
		return duplicateCompanyId;
	}

	public void setDuplicateCompanyId(String duplicateCompanyId) {
		this.duplicateCompanyId = duplicateCompanyId;
	}

	public int getStatusNullCount() {
		return statusNullCount;
	}

	public void setStatusNullCount(int statusNullCount) {
		this.statusNullCount = statusNullCount;
	}
	
	

	public int getStartDateEqualsEndCount() {
		return startDateEqualsEndCount;
	}

	public void setStartDateEqualsEndCount(int startDateEqualsEndCount) {
		this.startDateEqualsEndCount = startDateEqualsEndCount;
	}

	public String getStartDateEqualsCompanyId() {
		return startDateEqualsCompanyId;
	}

	public void setStartDateEqualsCompanyId(String startDateEqualsCompanyId) {
		this.startDateEqualsCompanyId = startDateEqualsCompanyId;
	}

	@Override
	public String toString() {
		return "GtnCFamilyPlanValidationBean{" + "count=" + count + ", startDateNullCount=" + startDateNullCount
				+ ", statusNullCount=" + statusNullCount + ", startDateGreaterThanEndCount="
				+ startDateGreaterThanEndCount + ", duplicateCompanyCount=" + duplicateCompanyCount + ", checkedCount="
				+ checkedCount + ", startDateNullCompanyId=" + startDateNullCompanyId + ", statusNullCompanyId="
				+ statusNullCompanyId + ", startDateGreaterThanEndCompanyId=" + startDateGreaterThanEndCompanyId
				+ ", duplicateCompanyId=" + duplicateCompanyId + ",startDateEqualsEndCount="
				+ startDateEqualsEndCount + " ,startDateEqualsCompanyId=" + startDateEqualsCompanyId + '}';
	}

}
