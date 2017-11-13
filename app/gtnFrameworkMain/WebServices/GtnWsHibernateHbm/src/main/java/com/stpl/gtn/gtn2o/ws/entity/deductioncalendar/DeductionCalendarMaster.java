package com.stpl.gtn.gtn2o.ws.entity.deductioncalendar;
// Generated Mar 30, 2017 9:06:03 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;

/**
 * DeductionCalendarMaster generated by hbm2java
 */
public class DeductionCalendarMaster implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deductionCalendarMasterSid;
	private HelperTable helperTable;
	private String deductionCalendarNo;
	private String deductionCalendarName;
	private String deductionCalendarDesc;
	private Integer userId;
	private String additionalNotes;
	private char inboundStatus;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;
	private Set rsContractDetailses = new HashSet(0);

	public DeductionCalendarMaster() {
	}

	public DeductionCalendarMaster(int deductionCalendarMasterSid, char inboundStatus, int createdBy, Date createdDate,
			int modifiedBy, Date modifiedDate) {
		this.deductionCalendarMasterSid = deductionCalendarMasterSid;
		this.inboundStatus = inboundStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public DeductionCalendarMaster(int deductionCalendarMasterSid, HelperTable helperTable, String deductionCalendarNo,
			String deductionCalendarName, String deductionCalendarDesc, Integer userId, String additionalNotes,
			char inboundStatus, int createdBy, Date createdDate, int modifiedBy, Date modifiedDate,
			Set rsContractDetailses) {
		this.deductionCalendarMasterSid = deductionCalendarMasterSid;
		this.helperTable = helperTable;
		this.deductionCalendarNo = deductionCalendarNo;
		this.deductionCalendarName = deductionCalendarName;
		this.deductionCalendarDesc = deductionCalendarDesc;
		this.userId = userId;
		this.additionalNotes = additionalNotes;
		this.inboundStatus = inboundStatus;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.rsContractDetailses = rsContractDetailses;
	}

	public int getDeductionCalendarMasterSid() {
		return this.deductionCalendarMasterSid;
	}

	public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
		this.deductionCalendarMasterSid = deductionCalendarMasterSid;
	}

	public HelperTable getHelperTable() {
		return this.helperTable;
	}

	public void setHelperTable(HelperTable helperTable) {
		this.helperTable = helperTable;
	}

	public String getDeductionCalendarNo() {
		return this.deductionCalendarNo;
	}

	public void setDeductionCalendarNo(String deductionCalendarNo) {
		this.deductionCalendarNo = deductionCalendarNo;
	}

	public String getDeductionCalendarName() {
		return this.deductionCalendarName;
	}

	public void setDeductionCalendarName(String deductionCalendarName) {
		this.deductionCalendarName = deductionCalendarName;
	}

	public String getDeductionCalendarDesc() {
		return this.deductionCalendarDesc;
	}

	public void setDeductionCalendarDesc(String deductionCalendarDesc) {
		this.deductionCalendarDesc = deductionCalendarDesc;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAdditionalNotes() {
		return this.additionalNotes;
	}

	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	public char getInboundStatus() {
		return this.inboundStatus;
	}

	public void setInboundStatus(char inboundStatus) {
		this.inboundStatus = inboundStatus;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Set getRsContractDetailses() {
		return this.rsContractDetailses;
	}

	public void setRsContractDetailses(Set rsContractDetailses) {
		this.rsContractDetailses = rsContractDetailses;
	}

}
