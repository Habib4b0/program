package com.stpl.gtn.gtn2o.ws.entity.transaction;

import java.util.Date;

@SuppressWarnings("serial")
public class AverageShelfLifeMaster implements java.io.Serializable {

	private int averageShelfLifeMasterSid;
	private String itemIdType;
	private String itemId;
	private Double avgShelfLife;
	private Character inboundStatus;
	private boolean recordLockStatus;
	private String batchId;
	private String source;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;

	public AverageShelfLifeMaster() {
	}

	public AverageShelfLifeMaster(int averageShelfLifeMasterSid, String itemIdType, String itemId, Double avgShelfLife,
			boolean recordLockStatus, String batchId, String createdBy, Date createdDate, String modifiedBy,
			Date modifiedDate) {
		this.averageShelfLifeMasterSid = averageShelfLifeMasterSid;
		this.itemIdType = itemIdType;
		this.itemId = itemId;
		this.avgShelfLife = avgShelfLife;
		this.recordLockStatus = recordLockStatus;
		this.batchId = batchId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public AverageShelfLifeMaster(int averageShelfLifeMasterSid, String itemIdType, String itemId, Double avgShelfLife,
			Character inboundStatus, boolean recordLockStatus, String batchId, String source, String createdBy,
			Date createdDate, String modifiedBy, Date modifiedDate) {
		this.averageShelfLifeMasterSid = averageShelfLifeMasterSid;
		this.itemIdType = itemIdType;
		this.itemId = itemId;
		this.avgShelfLife = avgShelfLife;
		this.inboundStatus = inboundStatus;
		this.recordLockStatus = recordLockStatus;
		this.batchId = batchId;
		this.source = source;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public int getAverageShelfLifeMasterSid() {
		return this.averageShelfLifeMasterSid;
	}

	public void setAverageShelfLifeMasterSid(int averageShelfLifeMasterSid) {
		this.averageShelfLifeMasterSid = averageShelfLifeMasterSid;
	}

	public String getItemIdType() {
		return this.itemIdType;
	}

	public void setItemIdType(String itemIdType) {
		this.itemIdType = itemIdType;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Double getAvgShelfLife() {
		return this.avgShelfLife;
	}

	public void setAvgShelfLife(Double avgShelfLife) {
		this.avgShelfLife = avgShelfLife;
	}

	public Character getInboundStatus() {
		return this.inboundStatus;
	}

	public void setInboundStatus(Character inboundStatus) {
		this.inboundStatus = inboundStatus;
	}

	public boolean isRecordLockStatus() {
		return this.recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}

	public String getBatchId() {
		return this.batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
