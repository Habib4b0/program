package com.stpl.gtn.gtn2o.ws.entity.transaction;

import java.util.Date;

@SuppressWarnings("serial")
public class VwItemPricing implements java.io.Serializable {

	private int itemPricingSid;
	private String itemUom;
	private Double itemPrice;
	private String pricingCodeStatus;
	private String entityCode;
	private Date startDate;
	private Date endDate;
	private String batchId;
	private String source;
	private String createdBy;
	private Date createdDate;
	private String modifiedBy;
	private Date modifiedDate;
	private String itemId;
	private String itemName;
	private String itemNo;
	private String pricingCodeQualifier;
	private String pricingCodeQualifierName;
	private String addChgDelIndicator;

	public VwItemPricing() {
            //constructor
	}

	public int getItemPricingSid() {
		return this.itemPricingSid;
	}

	public void setItemPricingSid(int itemPricingSid) {
		this.itemPricingSid = itemPricingSid;
	}

	public String getItemUom() {
		return this.itemUom;
	}

	public void setItemUom(String itemUom) {
		this.itemUom = itemUom;
	}

	public Double getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getPricingCodeStatus() {
		return this.pricingCodeStatus;
	}

	public void setPricingCodeStatus(String pricingCodeStatus) {
		this.pricingCodeStatus = pricingCodeStatus;
	}

	public String getEntityCode() {
		return this.entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getPricingCodeQualifier() {
		return pricingCodeQualifier;
	}

	public void setPricingCodeQualifier(String pricingCodeQualifier) {
		this.pricingCodeQualifier = pricingCodeQualifier;
	}

	public String getPricingCodeQualifierName() {
		return pricingCodeQualifierName;
	}

	public void setPricingCodeQualifierName(String pricingCodeQualifierName) {
		this.pricingCodeQualifierName = pricingCodeQualifierName;
	}

	public String getAddChgDelIndicator() {
		return addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		this.addChgDelIndicator = addChgDelIndicator;
	}

}
