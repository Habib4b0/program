/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.itemmaster.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class GtnWsItemMasterInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public GtnWsItemMasterInfoBean() {
		super();
	}

	private int itemMasterSid;
	private String itemId;
	private String itemNo;
	private String itemName;
	private String itemDesc;
	private int itemStatus;
	private Date itemStartDate;
	private Date itemEndDate;
	private Integer itemType;
	private Integer therapeuticClass;
	private int brandMasterSid;
	private Integer itemClass;

	private int form;
	private int strength;
	private Date firstSaleDate;
	private String ndc9;
	private String ndc8;
	private Integer primaryUom;
	private Integer secondaryUom;
	private String labelerCode;
	private String itemCode;
	private Integer packageSize;
	private String packageSizeCode;
	private Integer itemTypeIndication;
	private Integer itemCategory;
	private Double upps;
	private Date packageSizeIntroDate;
	private Integer manufacturerId;
	private Integer udc1;
	private Integer udc2;
	private Integer udc3;
	private Integer udc4;
	private Integer udc5;
	private Integer udc6;
	private int companyMasterSid;

	private String dosesPerUnit;
	private String shelfLife;
	private Integer shelfLifeType;
	private Date lastLotExpirationDate;
	private String authorizedGeneric;
	private String pediatricExclusiveIndicator;
	private String clottingFactorIndicator;
	private String dualPricingIndicator;
	private Date authorizedGenericStartDate;
	private Date pediatricExclusiveStartDate;
	private Date clottingFactorStartDate;
	private Date discontinuationDate;
	private Date authorizedGenericEndDate;
	private Date pediatricExclusiveEndDate;
	private Date clottingFactorEndDate;
	private Date divestitureDate;
	private String newFormulationIndicator;
	private BigDecimal baselineAmp;
	private Date acquisitionDate;
	private Date nonFederalExpirationDate;
	private BigDecimal baseCpi;
	private Integer acquiredAmp;
	private Date marketTerminationDate;
	private Date newFormulationStartDate;
	private Date baseCpiPeriod;
	private Integer acquiredBamp;
	private Date newFormulationEndDate;
	private Integer dra;
	private Integer obraBamp;

	private String internalNotes;
	private char inboundStatus;
	private boolean recordLockStatus;
	private String batchId;
	private String source;
	private int createdBy;
	private Date createdDate;
	private int modifiedBy;
	private Date modifiedDate;

	/**
	 * Extra Columns
	 */
	private String newFormulation;
	private String newFormulationLookup;
	private String itemFamilyId;
	private Double altBaselineAmp;
	private Double altBaseCpi;
	private String createdByUserName;
	private String modifiedUserName;
	private String populateField;
	private String populateValue;
	private String populateIdentityId;

	public int getItemMasterSid() {
		return itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		this.itemMasterSid = itemMasterSid;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public int getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(int itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Date getItemStartDate() {
		return itemStartDate == null ? null : (Date) itemStartDate.clone();
	}

	public void setItemStartDate(Date itemStartDate) {
		this.itemStartDate = itemStartDate == null ? null : (Date) itemStartDate.clone();
	}

	public Date getItemEndDate() {
		return itemEndDate == null ? null : (Date) itemEndDate.clone();
	}

	public void setItemEndDate(Date itemEndDate) {
		this.itemEndDate = itemEndDate == null ? null : (Date) itemEndDate.clone();
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public Integer getTherapeuticClass() {
		return therapeuticClass;
	}

	public void setTherapeuticClass(Integer therapeuticClass) {
		this.therapeuticClass = therapeuticClass;
	}

	public int getBrandMasterSid() {
		return brandMasterSid;
	}

	public void setBrandMasterSid(int brandMasterSid) {
		this.brandMasterSid = brandMasterSid;
	}

	public Integer getItemClass() {
		return itemClass;
	}

	public void setItemClass(Integer itemClass) {
		this.itemClass = itemClass;
	}

	public int getForm() {
		return form;
	}

	public void setForm(int form) {
		this.form = form;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public Date getFirstSaleDate() {
		return firstSaleDate == null ? null : (Date) firstSaleDate.clone();
	}

	public void setFirstSaleDate(Date firstSaleDate) {
		this.firstSaleDate = firstSaleDate == null ? null : (Date) firstSaleDate.clone();
	}

	public String getNdc9() {
		return ndc9;
	}

	public void setNdc9(String ndc9) {
		this.ndc9 = ndc9;
	}

	public String getNdc8() {
		return ndc8;
	}

	public void setNdc8(String ndc8) {
		this.ndc8 = ndc8;
	}

	public Integer getPrimaryUom() {
		return primaryUom;
	}

	public void setPrimaryUom(Integer primaryUom) {
		this.primaryUom = primaryUom;
	}

	public Integer getSecondaryUom() {
		return secondaryUom;
	}

	public void setSecondaryUom(Integer secondaryUom) {
		this.secondaryUom = secondaryUom;
	}

	public String getLabelerCode() {
		return labelerCode;
	}

	public void setLabelerCode(String labelerCode) {
		this.labelerCode = labelerCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Integer getPackageSize() {
		return packageSize;
	}

	public void setPackageSize(Integer packageSize) {
		this.packageSize = packageSize;
	}

	public String getPackageSizeCode() {
		return packageSizeCode;
	}

	public void setPackageSizeCode(String packageSizeCode) {
		this.packageSizeCode = packageSizeCode;
	}

	public Integer getItemTypeIndication() {
		return itemTypeIndication;
	}

	public void setItemTypeIndication(Integer itemTypeIndication) {
		this.itemTypeIndication = itemTypeIndication;
	}

	public Integer getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(Integer itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Double getUpps() {
		return upps;
	}

	public void setUpps(Double upps) {
		this.upps = upps;
	}

	public Date getPackageSizeIntroDate() {
		return packageSizeIntroDate == null ? null : (Date) packageSizeIntroDate.clone();
	}

	public void setPackageSizeIntroDate(Date packageSizeIntroDate) {
		this.packageSizeIntroDate = packageSizeIntroDate == null ? null : (Date) packageSizeIntroDate.clone();
	}

	public Integer getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public Integer getUdc1() {
		return udc1;
	}

	public void setUdc1(Integer udc1) {
		this.udc1 = udc1;
	}

	public Integer getUdc2() {
		return udc2;
	}

	public void setUdc2(Integer udc2) {
		this.udc2 = udc2;
	}

	public Integer getUdc3() {
		return udc3;
	}

	public void setUdc3(Integer udc3) {
		this.udc3 = udc3;
	}

	public Integer getUdc4() {
		return udc4;
	}

	public void setUdc4(Integer udc4) {
		this.udc4 = udc4;
	}

	public Integer getUdc5() {
		return udc5;
	}

	public void setUdc5(Integer udc5) {
		this.udc5 = udc5;
	}

	public Integer getUdc6() {
		return udc6;
	}

	public void setUdc6(Integer udc6) {
		this.udc6 = udc6;
	}

	public int getCompanyMasterSid() {
		return companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		this.companyMasterSid = companyMasterSid;
	}

	public String getDosesPerUnit() {
		return dosesPerUnit;
	}

	public void setDosesPerUnit(String dosesPerUnit) {
		this.dosesPerUnit = dosesPerUnit;
	}

	public String getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}

	public Integer getShelfLifeType() {
		return shelfLifeType;
	}

	public void setShelfLifeType(Integer shelfLifeType) {
		this.shelfLifeType = shelfLifeType;
	}

	public Date getLastLotExpirationDate() {
		return lastLotExpirationDate == null ? null : (Date) lastLotExpirationDate.clone();
	}

	public void setLastLotExpirationDate(Date lastLotExpirationDate) {
		this.lastLotExpirationDate = lastLotExpirationDate == null ? null : (Date) lastLotExpirationDate.clone();
	}

	public String getAuthorizedGeneric() {
		return authorizedGeneric;
	}

	public void setAuthorizedGeneric(String authorizedGeneric) {
		this.authorizedGeneric = authorizedGeneric;
	}

	public String getPediatricExclusiveIndicator() {
		return pediatricExclusiveIndicator;
	}

	public void setPediatricExclusiveIndicator(String pediatricExclusiveIndicator) {
		this.pediatricExclusiveIndicator = pediatricExclusiveIndicator;
	}

	public String getClottingFactorIndicator() {
		return clottingFactorIndicator;
	}

	public void setClottingFactorIndicator(String clottingFactorIndicator) {
		this.clottingFactorIndicator = clottingFactorIndicator;
	}

	public String getDualPricingIndicator() {
		return dualPricingIndicator;
	}

	public void setDualPricingIndicator(String dualPricingIndicator) {
		this.dualPricingIndicator = dualPricingIndicator;
	}

	public Date getAuthorizedGenericStartDate() {
		return authorizedGenericStartDate == null ? null : (Date) authorizedGenericStartDate.clone();
	}

	public void setAuthorizedGenericStartDate(Date authorizedGenericStartDate) {
		this.authorizedGenericStartDate = authorizedGenericStartDate == null ? null
				: (Date) authorizedGenericStartDate.clone();
	}

	public Date getPediatricExclusiveStartDate() {
		return pediatricExclusiveStartDate == null ? null : (Date) pediatricExclusiveStartDate.clone();
	}

	public void setPediatricExclusiveStartDate(Date pediatricExclusiveStartDate) {
		this.pediatricExclusiveStartDate = pediatricExclusiveStartDate == null ? null
				: (Date) pediatricExclusiveStartDate.clone();
	}

	public Date getClottingFactorStartDate() {
		return clottingFactorStartDate == null ? null : (Date) clottingFactorStartDate.clone();
	}

	public void setClottingFactorStartDate(Date clottingFactorStartDate) {
		this.clottingFactorStartDate = clottingFactorStartDate == null ? null : (Date) clottingFactorStartDate.clone();
	}

	public Date getDiscontinuationDate() {
		return discontinuationDate == null ? null : (Date) discontinuationDate.clone();
	}

	public void setDiscontinuationDate(Date discontinuationDate) {
		this.discontinuationDate = discontinuationDate == null ? null : (Date) discontinuationDate.clone();
	}

	public Date getAuthorizedGenericEndDate() {
		return authorizedGenericEndDate == null ? null : (Date) authorizedGenericEndDate.clone();
	}

	public void setAuthorizedGenericEndDate(Date authorizedGenericEndDate) {
		this.authorizedGenericEndDate = authorizedGenericEndDate == null ? null
				: (Date) authorizedGenericEndDate.clone();
	}

	public Date getPediatricExclusiveEndDate() {
		return pediatricExclusiveEndDate == null ? null : (Date) pediatricExclusiveEndDate.clone();
	}

	public void setPediatricExclusiveEndDate(Date pediatricExclusiveEndDate) {
		this.pediatricExclusiveEndDate = pediatricExclusiveEndDate == null ? null
				: (Date) pediatricExclusiveEndDate.clone();
	}

	public Date getClottingFactorEndDate() {
		return clottingFactorEndDate == null ? null : (Date) clottingFactorEndDate.clone();
	}

	public void setClottingFactorEndDate(Date clottingFactorEndDate) {
		this.clottingFactorEndDate = clottingFactorEndDate == null ? null : (Date) clottingFactorEndDate.clone();
	}

	public Date getDivestitureDate() {
		return divestitureDate == null ? null : (Date) divestitureDate.clone();
	}

	public void setDivestitureDate(Date divestitureDate) {
		this.divestitureDate = divestitureDate == null ? null : (Date) divestitureDate.clone();
	}

	public String getNewFormulationIndicator() {
		return newFormulationIndicator;
	}

	public void setNewFormulationIndicator(String newFormulationIndicator) {
		this.newFormulationIndicator = newFormulationIndicator;
	}

	public BigDecimal getBaselineAmp() {
		return baselineAmp;
	}

	public void setBaselineAmp(BigDecimal baselineAmp) {
		this.baselineAmp = baselineAmp;
	}

	public Date getAcquisitionDate() {
		return acquisitionDate == null ? null : (Date) acquisitionDate.clone();
	}

	public void setAcquisitionDate(Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate == null ? null : (Date) acquisitionDate.clone();
	}

	public Date getNonFederalExpirationDate() {
		return nonFederalExpirationDate == null ? null : (Date) nonFederalExpirationDate.clone();
	}

	public void setNonFederalExpirationDate(Date nonFederalExpirationDate) {
		this.nonFederalExpirationDate = nonFederalExpirationDate == null ? null
				: (Date) nonFederalExpirationDate.clone();
	}

	public BigDecimal getBaseCpi() {
		return baseCpi;
	}

	public void setBaseCpi(BigDecimal baseCpi) {
		this.baseCpi = baseCpi;
	}

	public Integer getAcquiredAmp() {
		return acquiredAmp;
	}

	public void setAcquiredAmp(Integer acquiredAmp) {
		this.acquiredAmp = acquiredAmp;
	}

	public Date getMarketTerminationDate() {
		return marketTerminationDate == null ? null : (Date) marketTerminationDate.clone();
	}

	public void setMarketTerminationDate(Date marketTerminationDate) {
		this.marketTerminationDate = marketTerminationDate == null ? null : (Date) marketTerminationDate.clone();
	}

	public Date getNewFormulationStartDate() {
		return newFormulationStartDate == null ? null : (Date) newFormulationStartDate.clone();
	}

	public void setNewFormulationStartDate(Date newFormulationStartDate) {
		this.newFormulationStartDate = newFormulationStartDate == null ? null : (Date) newFormulationStartDate.clone();
	}

	public Date getBaseCpiPeriod() {
		return baseCpiPeriod != null ? (Date) baseCpiPeriod.clone() : baseCpiPeriod;
	}

	public void setBaseCpiPeriod(Date baseCpiPeriod) {
		this.baseCpiPeriod = baseCpiPeriod != null ? (Date) baseCpiPeriod.clone() : baseCpiPeriod;
	}

	public Integer getAcquiredBamp() {
		return acquiredBamp;
	}

	public void setAcquiredBamp(Integer acquiredBamp) {
		this.acquiredBamp = acquiredBamp;
	}

	public Integer getDra() {
		return dra;
	}

	public void setDra(Integer dra) {
		this.dra = dra;
	}

	public String getInternalNotes() {
		return internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		this.internalNotes = internalNotes;
	}

	public boolean isRecordLockStatus() {
		return recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		this.recordLockStatus = recordLockStatus;
	}

	public Integer getObraBamp() {
		return obraBamp;
	}

	public void setObraBamp(Integer obraBamp) {
		this.obraBamp = obraBamp;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public char getInboundStatus() {
		return inboundStatus;
	}

	public void setInboundStatus(char inboundStatus) {
		this.inboundStatus = inboundStatus;
	}

	public String getNewFormulationLookup() {
		return newFormulationLookup;
	}

	public void setNewFormulationLookup(String newFormulationLookup) {
		this.newFormulationLookup = newFormulationLookup;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public Date getNewFormulationEndDate() {
		return newFormulationEndDate == null ? null : (Date) newFormulationEndDate.clone();
	}

	public void setNewFormulationEndDate(Date newFormulationEndDate) {
		this.newFormulationEndDate = newFormulationEndDate == null ? null : (Date) newFormulationEndDate.clone();
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate == null ? null : (Date) modifiedDate.clone();
	}

	public String getNewFormulation() {
		return newFormulation;
	}

	public void setNewFormulation(String newFormulation) {
		this.newFormulation = newFormulation;
	}

	public String getItemFamilyId() {
		return itemFamilyId;
	}

	public void setItemFamilyId(String itemFamilyId) {
		this.itemFamilyId = itemFamilyId;
	}

	public Double getAltBaselineAmp() {
		return altBaselineAmp;
	}

	public void setAltBaselineAmp(Double altBaselineAmp) {
		this.altBaselineAmp = altBaselineAmp;
	}

	public Double getAltBaseCpi() {
		return altBaseCpi;
	}

	public void setAltBaseCpi(Double altBaseCpi) {
		this.altBaseCpi = altBaseCpi;
	}

	public String getCreatedByUserName() {
		return createdByUserName;
	}

	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}

	public String getModifiedUserName() {
		return modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		this.modifiedUserName = modifiedUserName;
	}

	public String getPopulateField() {
		return populateField;
	}

	public void setPopulateField(String populateField) {
		this.populateField = populateField;
	}

	public String getPopulateValue() {
		return populateValue;
	}

	public void setPopulateValue(String populateValue) {
		this.populateValue = populateValue;
	}

	public String getPopulateIdentityId() {
		return populateIdentityId;
	}

	public void setPopulateIdentityId(String populateIdentityId) {
		this.populateIdentityId = populateIdentityId;
	}

	@Override
	public String toString() {
		return "GtnWsItemMasterInfoBean{" + "itemMasterSid=" + itemMasterSid + ", itemId=" + itemId + ", itemNo="
				+ itemNo + ", itemName=" + itemName + ", itemDesc=" + itemDesc + ", itemStatus=" + itemStatus
				+ ", itemStartDate=" + itemStartDate + ", itemEndDate=" + itemEndDate + ", itemType=" + itemType
				+ ", therapeuticClass=" + therapeuticClass + ", brandMasterSid=" + brandMasterSid + ", itemClass="
				+ itemClass + ", form=" + form + ", strength=" + strength + ", firstSaleDate=" + firstSaleDate
				+ ", ndc9=" + ndc9 + ", ndc8=" + ndc8 + ", primaryUom=" + primaryUom + ", secondaryUom=" + secondaryUom
				+ ", labelerCode=" + labelerCode + ", itemCode=" + itemCode + ", packageSize=" + packageSize
				+ ", packageSizeCode=" + packageSizeCode + ", itemTypeIndication=" + itemTypeIndication
				+ ", itemCategory=" + itemCategory + ", upps=" + upps + ", packageSizeIntroDate=" + packageSizeIntroDate
				+ ", manufacturerId=" + manufacturerId + ", udc1=" + udc1 + ", udc2=" + udc2 + ", udc3=" + udc3
				+ ", udc4=" + udc4 + ", udc5=" + udc5 + ", udc6=" + udc6 + ", companyMasterSid=" + companyMasterSid
				+ ", dosesPerUnit=" + dosesPerUnit + ", shelfLife=" + shelfLife + ", shelfLifeType=" + shelfLifeType
				+ ", lastLotExpirationDate=" + lastLotExpirationDate + ", authorizedGeneric=" + authorizedGeneric
				+ ", pediatricExclusiveIndicator=" + pediatricExclusiveIndicator + ", clottingFactorIndicator="
				+ clottingFactorIndicator + ", dualPricingIndicator=" + dualPricingIndicator
				+ ", authorizedGenericStartDate=" + authorizedGenericStartDate + ", pediatricExclusiveStartDate="
				+ pediatricExclusiveStartDate + ", clottingFactorStartDate=" + clottingFactorStartDate
				+ ", discontinuationDate=" + discontinuationDate + ", authorizedGenericEndDate="
				+ authorizedGenericEndDate + ", pediatricExclusiveEndDate=" + pediatricExclusiveEndDate
				+ ", clottingFactorEndDate=" + clottingFactorEndDate + ", divestitureDate=" + divestitureDate
				+ ", newFormulationIndicator=" + newFormulationIndicator + ", baselineAmp=" + baselineAmp
				+ ", acquisitionDate=" + acquisitionDate + ", nonFederalExpirationDate=" + nonFederalExpirationDate
				+ ", baseCpi=" + baseCpi + ", acquiredAmp=" + acquiredAmp + ", marketTerminationDate="
				+ marketTerminationDate + ", newFormulationStartDate=" + newFormulationStartDate + ", baseCpiPeriod="
				+ baseCpiPeriod + ", acquiredBamp=" + acquiredBamp + ", newFormulationEndDate=" + newFormulationEndDate
				+ ", dra=" + dra + ", obraBamp=" + obraBamp + ", internalNotes=" + internalNotes + ", inboundStatus="
				+ inboundStatus + ", recordLockStatus=" + recordLockStatus + ", batchId=" + batchId + ", source="
				+ source + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", newFormulation=" + newFormulation + ", itemFamilyId="
				+ itemFamilyId + ", altBaselineAmp=" + altBaselineAmp + ", altBaseCpi=" + altBaseCpi + '}';
	}

}
