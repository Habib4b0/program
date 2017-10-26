package com.stpl.app.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class PsContractDetailsSoap implements Serializable {
    private double _price;
    private int _itemMasterSid;
    private Date _priceProtectionStartDate;
    private double _basePrice;
    private Date _modifiedDate;
    private Date _revisionDate;
    private double _priceTolerance;
    private Date _createdDate;
    private String _source;
    private int _createdBy;
    private double _suggestedPrice;
    private Date _psContractAttachedDate;
    private int _psContractDetailsSid;
    private int _modifiedBy;
    private String _inboundStatus;
    private double _contractPrice;
    private int _priceToleranceType;
    private int _itemPricingQualifierSid;
    private Date _contractPriceEndDate;
    private int _priceToleranceFrequency;
    private Date _contractPriceStartDate;
    private int _psContractSid;
    private Date _priceProtectionEndDate;
    private int _psContractAttachedStatus;
    private boolean _recordLockStatus;
    private String _batchId;
    private int _priceToleranceInterval;
    private double _priceRevision;
    private String _brandMasterSid;
    private double _nep;
    private int _priceProtectionStatus;
    private int _priceProtectionPriceType;
    private int _nepFormula;
    private double _maxIncrementalChange;
    private int _resetEligible;
    private int _resetType;
    private Date _resetDate;
    private int _resetInterval;
    private int _resetFrequency;
    private int _netPriceType;
    private String _netPriceTypeFormula;
    private int _basePriceType;
    private double _basePriceEntry;
    private Date _basePriceDate;
    private int _basePriceDdlb;
    private int _netBasePrice;
    private int _netBasePriceFormulaId;
    private int _subsequentPeriodPriceType;
    private int _netSubsequentPeriodPrice;
    private int _netSubsequentPriceFormulaId;
    private int _resetPriceType;
    private int _netResetPriceType;
    private int _netResetPriceFormulaId;

    public PsContractDetailsSoap() {
    }

    public static PsContractDetailsSoap toSoapModel(PsContractDetails model) {
        PsContractDetailsSoap soapModel = new PsContractDetailsSoap();

        soapModel.setPrice(model.getPrice());
        soapModel.setItemMasterSid(model.getItemMasterSid());
        soapModel.setPriceProtectionStartDate(model.getPriceProtectionStartDate());
        soapModel.setBasePrice(model.getBasePrice());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setRevisionDate(model.getRevisionDate());
        soapModel.setPriceTolerance(model.getPriceTolerance());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setSource(model.getSource());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setSuggestedPrice(model.getSuggestedPrice());
        soapModel.setPsContractAttachedDate(model.getPsContractAttachedDate());
        soapModel.setPsContractDetailsSid(model.getPsContractDetailsSid());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setInboundStatus(model.getInboundStatus());
        soapModel.setContractPrice(model.getContractPrice());
        soapModel.setPriceToleranceType(model.getPriceToleranceType());
        soapModel.setItemPricingQualifierSid(model.getItemPricingQualifierSid());
        soapModel.setContractPriceEndDate(model.getContractPriceEndDate());
        soapModel.setPriceToleranceFrequency(model.getPriceToleranceFrequency());
        soapModel.setContractPriceStartDate(model.getContractPriceStartDate());
        soapModel.setPsContractSid(model.getPsContractSid());
        soapModel.setPriceProtectionEndDate(model.getPriceProtectionEndDate());
        soapModel.setPsContractAttachedStatus(model.getPsContractAttachedStatus());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setPriceToleranceInterval(model.getPriceToleranceInterval());
        soapModel.setPriceRevision(model.getPriceRevision());
        soapModel.setBrandMasterSid(model.getBrandMasterSid());
        soapModel.setNep(model.getNep());
        soapModel.setPriceProtectionStatus(model.getPriceProtectionStatus());
        soapModel.setPriceProtectionPriceType(model.getPriceProtectionPriceType());
        soapModel.setNepFormula(model.getNepFormula());
        soapModel.setMaxIncrementalChange(model.getMaxIncrementalChange());
        soapModel.setResetEligible(model.getResetEligible());
        soapModel.setResetType(model.getResetType());
        soapModel.setResetDate(model.getResetDate());
        soapModel.setResetInterval(model.getResetInterval());
        soapModel.setResetFrequency(model.getResetFrequency());
        soapModel.setNetPriceType(model.getNetPriceType());
        soapModel.setNetPriceTypeFormula(model.getNetPriceTypeFormula());
        soapModel.setBasePriceType(model.getBasePriceType());
        soapModel.setBasePriceEntry(model.getBasePriceEntry());
        soapModel.setBasePriceDate(model.getBasePriceDate());
        soapModel.setBasePriceDdlb(model.getBasePriceDdlb());
        soapModel.setNetBasePrice(model.getNetBasePrice());
        soapModel.setNetBasePriceFormulaId(model.getNetBasePriceFormulaId());
        soapModel.setSubsequentPeriodPriceType(model.getSubsequentPeriodPriceType());
        soapModel.setNetSubsequentPeriodPrice(model.getNetSubsequentPeriodPrice());
        soapModel.setNetSubsequentPriceFormulaId(model.getNetSubsequentPriceFormulaId());
        soapModel.setResetPriceType(model.getResetPriceType());
        soapModel.setNetResetPriceType(model.getNetResetPriceType());
        soapModel.setNetResetPriceFormulaId(model.getNetResetPriceFormulaId());

        return soapModel;
    }

    public static PsContractDetailsSoap[] toSoapModels(
        PsContractDetails[] models) {
        PsContractDetailsSoap[] soapModels = new PsContractDetailsSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PsContractDetailsSoap[][] toSoapModels(
        PsContractDetails[][] models) {
        PsContractDetailsSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PsContractDetailsSoap[models.length][models[0].length];
        } else {
            soapModels = new PsContractDetailsSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PsContractDetailsSoap[] toSoapModels(
        List<PsContractDetails> models) {
        List<PsContractDetailsSoap> soapModels = new ArrayList<PsContractDetailsSoap>(models.size());

        for (PsContractDetails model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PsContractDetailsSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _psContractDetailsSid;
    }

    public void setPrimaryKey(int pk) {
        setPsContractDetailsSid(pk);
    }

    public double getPrice() {
        return _price;
    }

    public void setPrice(double price) {
        _price = price;
    }

    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;
    }

    public Date getPriceProtectionStartDate() {
        return _priceProtectionStartDate;
    }

    public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
        _priceProtectionStartDate = priceProtectionStartDate;
    }

    public double getBasePrice() {
        return _basePrice;
    }

    public void setBasePrice(double basePrice) {
        _basePrice = basePrice;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public Date getRevisionDate() {
        return _revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        _revisionDate = revisionDate;
    }

    public double getPriceTolerance() {
        return _priceTolerance;
    }

    public void setPriceTolerance(double priceTolerance) {
        _priceTolerance = priceTolerance;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public double getSuggestedPrice() {
        return _suggestedPrice;
    }

    public void setSuggestedPrice(double suggestedPrice) {
        _suggestedPrice = suggestedPrice;
    }

    public Date getPsContractAttachedDate() {
        return _psContractAttachedDate;
    }

    public void setPsContractAttachedDate(Date psContractAttachedDate) {
        _psContractAttachedDate = psContractAttachedDate;
    }

    public int getPsContractDetailsSid() {
        return _psContractDetailsSid;
    }

    public void setPsContractDetailsSid(int psContractDetailsSid) {
        _psContractDetailsSid = psContractDetailsSid;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }

    public double getContractPrice() {
        return _contractPrice;
    }

    public void setContractPrice(double contractPrice) {
        _contractPrice = contractPrice;
    }

    public int getPriceToleranceType() {
        return _priceToleranceType;
    }

    public void setPriceToleranceType(int priceToleranceType) {
        _priceToleranceType = priceToleranceType;
    }

    public int getItemPricingQualifierSid() {
        return _itemPricingQualifierSid;
    }

    public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
        _itemPricingQualifierSid = itemPricingQualifierSid;
    }

    public Date getContractPriceEndDate() {
        return _contractPriceEndDate;
    }

    public void setContractPriceEndDate(Date contractPriceEndDate) {
        _contractPriceEndDate = contractPriceEndDate;
    }

    public int getPriceToleranceFrequency() {
        return _priceToleranceFrequency;
    }

    public void setPriceToleranceFrequency(int priceToleranceFrequency) {
        _priceToleranceFrequency = priceToleranceFrequency;
    }

    public Date getContractPriceStartDate() {
        return _contractPriceStartDate;
    }

    public void setContractPriceStartDate(Date contractPriceStartDate) {
        _contractPriceStartDate = contractPriceStartDate;
    }

    public int getPsContractSid() {
        return _psContractSid;
    }

    public void setPsContractSid(int psContractSid) {
        _psContractSid = psContractSid;
    }

    public Date getPriceProtectionEndDate() {
        return _priceProtectionEndDate;
    }

    public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
        _priceProtectionEndDate = priceProtectionEndDate;
    }

    public int getPsContractAttachedStatus() {
        return _psContractAttachedStatus;
    }

    public void setPsContractAttachedStatus(int psContractAttachedStatus) {
        _psContractAttachedStatus = psContractAttachedStatus;
    }

    public boolean getRecordLockStatus() {
        return _recordLockStatus;
    }

    public boolean isRecordLockStatus() {
        return _recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
        _recordLockStatus = recordLockStatus;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public int getPriceToleranceInterval() {
        return _priceToleranceInterval;
    }

    public void setPriceToleranceInterval(int priceToleranceInterval) {
        _priceToleranceInterval = priceToleranceInterval;
    }

    public double getPriceRevision() {
        return _priceRevision;
    }

    public void setPriceRevision(double priceRevision) {
        _priceRevision = priceRevision;
    }

    public String getBrandMasterSid() {
        return _brandMasterSid;
    }

    public void setBrandMasterSid(String brandMasterSid) {
        _brandMasterSid = brandMasterSid;
    }

    public double getNep() {
        return _nep;
    }

    public void setNep(double nep) {
        _nep = nep;
    }

    public int getPriceProtectionStatus() {
        return _priceProtectionStatus;
    }

    public void setPriceProtectionStatus(int priceProtectionStatus) {
        _priceProtectionStatus = priceProtectionStatus;
    }

    public int getPriceProtectionPriceType() {
        return _priceProtectionPriceType;
    }

    public void setPriceProtectionPriceType(int priceProtectionPriceType) {
        _priceProtectionPriceType = priceProtectionPriceType;
    }

    public int getNepFormula() {
        return _nepFormula;
    }

    public void setNepFormula(int nepFormula) {
        _nepFormula = nepFormula;
    }

    public double getMaxIncrementalChange() {
        return _maxIncrementalChange;
    }

    public void setMaxIncrementalChange(double maxIncrementalChange) {
        _maxIncrementalChange = maxIncrementalChange;
    }

    public int getResetEligible() {
        return _resetEligible;
    }

    public void setResetEligible(int resetEligible) {
        _resetEligible = resetEligible;
    }

    public int getResetType() {
        return _resetType;
    }

    public void setResetType(int resetType) {
        _resetType = resetType;
    }

    public Date getResetDate() {
        return _resetDate;
    }

    public void setResetDate(Date resetDate) {
        _resetDate = resetDate;
    }

    public int getResetInterval() {
        return _resetInterval;
    }

    public void setResetInterval(int resetInterval) {
        _resetInterval = resetInterval;
    }

    public int getResetFrequency() {
        return _resetFrequency;
    }

    public void setResetFrequency(int resetFrequency) {
        _resetFrequency = resetFrequency;
    }

    public int getNetPriceType() {
        return _netPriceType;
    }

    public void setNetPriceType(int netPriceType) {
        _netPriceType = netPriceType;
    }

    public String getNetPriceTypeFormula() {
        return _netPriceTypeFormula;
    }

    public void setNetPriceTypeFormula(String netPriceTypeFormula) {
        _netPriceTypeFormula = netPriceTypeFormula;
    }

    public int getBasePriceType() {
        return _basePriceType;
    }

    public void setBasePriceType(int basePriceType) {
        _basePriceType = basePriceType;
    }

    public double getBasePriceEntry() {
        return _basePriceEntry;
    }

    public void setBasePriceEntry(double basePriceEntry) {
        _basePriceEntry = basePriceEntry;
    }

    public Date getBasePriceDate() {
        return _basePriceDate;
    }

    public void setBasePriceDate(Date basePriceDate) {
        _basePriceDate = basePriceDate;
    }

    public int getBasePriceDdlb() {
        return _basePriceDdlb;
    }

    public void setBasePriceDdlb(int basePriceDdlb) {
        _basePriceDdlb = basePriceDdlb;
    }

    public int getNetBasePrice() {
        return _netBasePrice;
    }

    public void setNetBasePrice(int netBasePrice) {
        _netBasePrice = netBasePrice;
    }

    public int getNetBasePriceFormulaId() {
        return _netBasePriceFormulaId;
    }

    public void setNetBasePriceFormulaId(int netBasePriceFormulaId) {
        _netBasePriceFormulaId = netBasePriceFormulaId;
    }

    public int getSubsequentPeriodPriceType() {
        return _subsequentPeriodPriceType;
    }

    public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType) {
        _subsequentPeriodPriceType = subsequentPeriodPriceType;
    }

    public int getNetSubsequentPeriodPrice() {
        return _netSubsequentPeriodPrice;
    }

    public void setNetSubsequentPeriodPrice(int netSubsequentPeriodPrice) {
        _netSubsequentPeriodPrice = netSubsequentPeriodPrice;
    }

    public int getNetSubsequentPriceFormulaId() {
        return _netSubsequentPriceFormulaId;
    }

    public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId) {
        _netSubsequentPriceFormulaId = netSubsequentPriceFormulaId;
    }

    public int getResetPriceType() {
        return _resetPriceType;
    }

    public void setResetPriceType(int resetPriceType) {
        _resetPriceType = resetPriceType;
    }

    public int getNetResetPriceType() {
        return _netResetPriceType;
    }

    public void setNetResetPriceType(int netResetPriceType) {
        _netResetPriceType = netResetPriceType;
    }

    public int getNetResetPriceFormulaId() {
        return _netResetPriceFormulaId;
    }

    public void setNetResetPriceFormulaId(int netResetPriceFormulaId) {
        _netResetPriceFormulaId = netResetPriceFormulaId;
    }
}
