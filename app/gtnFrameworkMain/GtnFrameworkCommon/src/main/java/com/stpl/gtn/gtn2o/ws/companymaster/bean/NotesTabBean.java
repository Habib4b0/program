package com.stpl.gtn.gtn2o.ws.companymaster.bean;

import java.io.Serializable;
import java.util.Date;

public class NotesTabBean implements Serializable {

	private static final long serialVersionUID = 3908224499045075125L;

	public NotesTabBean() {
		super();
	}

	private Integer masterTableSystemId;

	private String masterTableName;
        
 	private String filePath;

	private Date createdDate;

	private Integer createdBy;

	private String createdByName;

	public Integer getMasterTableSystemId() {
		return masterTableSystemId;
	}

	public void setMasterTableSystemId(Integer masterTableSystemId) {
		this.masterTableSystemId = masterTableSystemId;
	}

	public String getMasterTableName() {
		return masterTableName;
	}

	public void setMasterTableName(String masterTableName) {
		this.masterTableName = masterTableName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getCreatedDate() {
		return  createdDate==null ? null :(Date)createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate==null ? null :(Date)createdDate.clone();
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

}
