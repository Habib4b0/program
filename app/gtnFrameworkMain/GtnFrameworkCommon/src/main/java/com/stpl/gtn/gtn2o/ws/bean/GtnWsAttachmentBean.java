package com.stpl.gtn.gtn2o.ws.bean;

import java.sql.Date;

public class GtnWsAttachmentBean {

	private int id;
	private Integer attachmentTableSid;
	private String fileName;
	private byte[] fileData;
	private String masterTableName;
	private Date createdDate;
	private Integer createdBy;

	public GtnWsAttachmentBean() {
		super();
	}

	public void attachment(int id, String fileName, byte[] fileData, String masterTableName) {
		this.id = id;
		this.fileName = fileName;
		this.fileData = fileData;
		this.masterTableName = masterTableName;
	}

	public void attachment(int id, Integer attachmentTableSid, String fileName, byte[] fileData, String masterTableName,
			Date createdDate, Integer createdBy) {
		this.id = id;
		this.attachmentTableSid = attachmentTableSid;
		this.fileName = fileName;
		this.fileData = fileData;
		this.masterTableName = masterTableName;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getAttachmentTableSid() {
		return this.attachmentTableSid;
	}

	public void setAttachmentTableSid(Integer attachmentTableSid) {
		this.attachmentTableSid = attachmentTableSid;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return this.fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getMasterTableName() {
		return this.masterTableName;
	}

	public void setMasterTableName(String masterTableName) {
		this.masterTableName = masterTableName;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

}

