package com.stpl.app.bpm.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RelatioshipBuilderDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fieldName;
	private String udc1;
	private String udc2;
	private String udc3;
	private String udc4;
	private Date startDate;
	private Date endDate;
	private List<String> fieldNames;
	private List<Date> startDates;
	private List<String> status;
	private List<String> conditions;
	
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
		if(fieldNames==null){
			fieldNames = new ArrayList<String>();
		}
		fieldNames.add(fieldName);
	}
	public String getUdc1() {
		return udc1;
	}
	public void setUdc1(String udc1) {
		this.udc1 = udc1;
		if(status==null){
			status = new ArrayList<String>();
		}
		status.add(udc1);
	}
	public String getUdc2() {
		return udc2;
	}
	public void setUdc2(String udc2) {
		this.udc2 = udc2;
		if(conditions==null){
			conditions = new ArrayList<String>();
		}
		conditions.add(udc2);
	}
	public String getUdc3() {
		return udc3;
	}
	public void setUdc3(String udc3) {
		this.udc3 = udc3;
	}
	public String getUdc4() {
		return udc4;
	}
	public void setUdc4(String udc4) {
		this.udc4 = udc4;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		if(startDates==null){
			startDates = new ArrayList<Date>();
		}
		startDates.add(startDate);
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String getQuery(){
		return "";
	}
	
}
