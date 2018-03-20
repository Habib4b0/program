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
		return (fileData==null) ? null : fileData.clone();
	}

	public void setFileData(byte[] fileData) {
		this.fileData  = (fileData==null) ? null : fileData.clone();
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

