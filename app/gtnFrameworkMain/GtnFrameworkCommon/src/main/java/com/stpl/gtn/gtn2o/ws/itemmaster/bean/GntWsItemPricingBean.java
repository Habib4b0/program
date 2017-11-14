package com.stpl.gtn.gtn2o.ws.itemmaster.bean;
// Generated Feb 18, 2017 12:08:27 PM by Hibernate Tools 3.6.0

import java.util.Date;

public class GntWsItemPricingBean implements java.io.Serializable {

	public GntWsItemPricingBean() {
		super();
	}

	private static final long serialVersionUID = 1L;
	private int itemPricingSid;
	private int itemUom;
	private int itemPricingQualifierSid;
	private int itemMasterSid;
	private int pricingCodeStatus;
	private Double itemPrice;
	private String entityCode;
	private String itemPricingQualifierName;
	private Date startDate;
	private Date endDate;
	private char inboundStatus;
	private boolean recordLockStatus;
	private String batchId;
	private String source;
	private int createdBy;
	private String createdByAsName;
	private Date createdDate;
	private int modifiedBy;
	private String modifiedByAsName;
	private Date modifiedDate;
	private String pricingCodeStatusDes;
	private String itemUomDes;

	public int getItemPricingSid() {
		return itemPricingSid;
	}

	public void setItemPricingSid(int itemPricingSid) {
		this.itemPricingSid = itemPricingSid;
	}

	public int getItemUom() {
		return itemUom;
	}

	public void setItemUom(int itemUom) {
		this.itemUom = itemUom;
	}

	public int getItemPricingQualifierSid() {
		return itemPricingQualifierSid;
	}

	public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
		this.itemPricingQualifierSid = itemPricingQualifierSid;
	}

	public int getItemMasterSid() {
		return itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		this.itemMasterSid = itemMasterSid;
	}

	public int getPricingCodeStatus() {
		return pricingCodeStatus;
	}

	public void setPricingCodeStatus(int pricingCodeStatus) {
		this.pricingCodeStatus = pricingCodeStatus;
	}

	public Date getStartDate() {
		return startDate == null ? null : (Date) startDate.clone();
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate == null ? null : (Date) startDate.clone();
	}

	public Date getEndDate() {
		return endDate == null ? null : (Date) endDate.clone();
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate == null ? null : (Date) endDate.clone();
	}

	public char getInboundStatus() {
		return inboundStatus;
	}

	public void setInboundStatus(char inboundStatus) {
		this.inboundStatus = inboundStatus;
	}

	public boolean isRecordLockStatus() {
		return recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate == null ? null : (Date) createdDate.clone();
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate == null ? null : (Date) createdDate.clone();
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public String getItemPricingQualifierName() {
		return itemPricingQualifierName;
	}

	public void setItemPricingQualifierName(String itemPricingQualifierName) {
		this.itemPricingQualifierName = itemPricingQualifierName;
	}

	public String getCreatedByAsName() {
		return createdByAsName;
	}

	public void setCreatedByAsName(String createdByAsName) {
		this.createdByAsName = createdByAsName;
	}

	public String getModifiedByAsName() {
		return modifiedByAsName;
	}

	public void setModifiedByAsName(String modifiedByAsName) {
		this.modifiedByAsName = modifiedByAsName;
	}

	public String getPricingCodeStatusDes() {
		return pricingCodeStatusDes;
	}

	public void setPricingCodeStatusDes(String pricingCodeStatusDes) {
		this.pricingCodeStatusDes = pricingCodeStatusDes;
	}

	public String getItemUomDes() {
		return itemUomDes;
	}

	public void setItemUomDes(String itemUomDes) {
		this.itemUomDes = itemUomDes;
	}

	@Override
	public String toString() {
		return "GntWsItemPricingBean{" + "itemPricingSid=" + itemPricingSid + ", itemUom=" + itemUom
				+ ", itemPricingQualifierSid=" + itemPricingQualifierSid + ", itemMasterSid=" + itemMasterSid
				+ ", pricingCodeStatus=" + pricingCodeStatus + ", itemPrice=" + itemPrice + ", entityCode=" + entityCode
				+ ", itemPricingQualifierName=" + itemPricingQualifierName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", inboundStatus=" + inboundStatus + ", recordLockStatus=" + recordLockStatus + ", batchId="
				+ batchId + ", source=" + source + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + '}';
	}

}