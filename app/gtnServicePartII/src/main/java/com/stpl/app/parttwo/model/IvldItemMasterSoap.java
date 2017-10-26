package com.stpl.app.parttwo.model;

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
public class IvldItemMasterSoap implements Serializable {
    private String _itemNo;
    private String _udc6;
    private Date _createdDate;
    private String _newFormulationIndicator;
    private String _udc5;
    private String _newFormulationEndDate;
    private String _udc4;
    private String _clottingFactorStartDate;
    private String _secondaryUom;
    private String _itemDesc;
    private String _authorizedGenericEndDate;
    private String _manufacturerName;
    private String _itemName;
    private String _reprocessedFlag;
    private String _status;
    private String _baseCpi;
    private String _baselineAmp;
    private String _authorizedGeneric;
    private String _therapeuticClass;
    private String _itemFamilyId;
    private String _pediatricExclusiveStartDate;
    private String _createdBy;
    private String _primaryUom;
    private String _ndc9;
    private String _itemId;
    private String _lastLotExpirationDate;
    private String _errorField;
    private String _itemCode;
    private String _strength;
    private Date _modifiedDate;
    private String _brand;
    private String _ndc8;
    private String _labelerCode;
    private String _udc3;
    private String _source;
    private String _udc2;
    private String _addChgDelIndicator;
    private String _udc1;
    private String _acquiredAmp;
    private String _discontinuationDate;
    private String _itemMasterIntfid;
    private Date _intfInsertedDate;
    private String _divestitureDate;
    private String _modifiedBy;
    private String _baseCpiPeriod;
    private String _clottingFactorEndDate;
    private String _dosesPerUnit;
    private String _manufacturerId;
    private String _clottingFactorIndicator;
    private String _batchId;
    private String _acquisitionDate;
    private String _dualPricingIndicator;
    private String _nonFederalExpirationDate;
    private String _errorCode;
    private String _newFormulation;
    private String _obraBamp;
    private String _brandId;
    private String _itemStatus;
    private String _authorizedGenericStartDate;
    private String _newFormulationStartDate;
    private String _itemCategory;
    private String _itemEndDate;
    private String _itemType;
    private String _pediatricExclusiveEndDate;
    private String _organizationKey;
    private String _firstSaleDate;
    private String _shelfLifeType;
    private String _itemStartDate;
    private String _itemTypeIndication;
    private String _acquiredBamp;
    private String _form;
    private String _itemClass;
    private String _manufacturerNo;
    private String _pediatricExclusiveIndicator;
    private String _packageSizeCode;
    private String _displayBrand;
    private String _dra;
    private String _reasonForFailure;
    private String _packageSizeIntroDate;
    private String _upps;
    private int _ivldItemMasterSid;
    private String _packageSize;
    private String _shelfLife;
    private String _marketTerminationDate;
    private boolean _checkRecord;

    public IvldItemMasterSoap() {
    }

    public static IvldItemMasterSoap toSoapModel(IvldItemMaster model) {
        IvldItemMasterSoap soapModel = new IvldItemMasterSoap();

        soapModel.setItemNo(model.getItemNo());
        soapModel.setUdc6(model.getUdc6());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setNewFormulationIndicator(model.getNewFormulationIndicator());
        soapModel.setUdc5(model.getUdc5());
        soapModel.setNewFormulationEndDate(model.getNewFormulationEndDate());
        soapModel.setUdc4(model.getUdc4());
        soapModel.setClottingFactorStartDate(model.getClottingFactorStartDate());
        soapModel.setSecondaryUom(model.getSecondaryUom());
        soapModel.setItemDesc(model.getItemDesc());
        soapModel.setAuthorizedGenericEndDate(model.getAuthorizedGenericEndDate());
        soapModel.setManufacturerName(model.getManufacturerName());
        soapModel.setItemName(model.getItemName());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setStatus(model.getStatus());
        soapModel.setBaseCpi(model.getBaseCpi());
        soapModel.setBaselineAmp(model.getBaselineAmp());
        soapModel.setAuthorizedGeneric(model.getAuthorizedGeneric());
        soapModel.setTherapeuticClass(model.getTherapeuticClass());
        soapModel.setItemFamilyId(model.getItemFamilyId());
        soapModel.setPediatricExclusiveStartDate(model.getPediatricExclusiveStartDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setPrimaryUom(model.getPrimaryUom());
        soapModel.setNdc9(model.getNdc9());
        soapModel.setItemId(model.getItemId());
        soapModel.setLastLotExpirationDate(model.getLastLotExpirationDate());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setItemCode(model.getItemCode());
        soapModel.setStrength(model.getStrength());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setBrand(model.getBrand());
        soapModel.setNdc8(model.getNdc8());
        soapModel.setLabelerCode(model.getLabelerCode());
        soapModel.setUdc3(model.getUdc3());
        soapModel.setSource(model.getSource());
        soapModel.setUdc2(model.getUdc2());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setUdc1(model.getUdc1());
        soapModel.setAcquiredAmp(model.getAcquiredAmp());
        soapModel.setDiscontinuationDate(model.getDiscontinuationDate());
        soapModel.setItemMasterIntfid(model.getItemMasterIntfid());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setDivestitureDate(model.getDivestitureDate());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setBaseCpiPeriod(model.getBaseCpiPeriod());
        soapModel.setClottingFactorEndDate(model.getClottingFactorEndDate());
        soapModel.setDosesPerUnit(model.getDosesPerUnit());
        soapModel.setManufacturerId(model.getManufacturerId());
        soapModel.setClottingFactorIndicator(model.getClottingFactorIndicator());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setAcquisitionDate(model.getAcquisitionDate());
        soapModel.setDualPricingIndicator(model.getDualPricingIndicator());
        soapModel.setNonFederalExpirationDate(model.getNonFederalExpirationDate());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setNewFormulation(model.getNewFormulation());
        soapModel.setObraBamp(model.getObraBamp());
        soapModel.setBrandId(model.getBrandId());
        soapModel.setItemStatus(model.getItemStatus());
        soapModel.setAuthorizedGenericStartDate(model.getAuthorizedGenericStartDate());
        soapModel.setNewFormulationStartDate(model.getNewFormulationStartDate());
        soapModel.setItemCategory(model.getItemCategory());
        soapModel.setItemEndDate(model.getItemEndDate());
        soapModel.setItemType(model.getItemType());
        soapModel.setPediatricExclusiveEndDate(model.getPediatricExclusiveEndDate());
        soapModel.setOrganizationKey(model.getOrganizationKey());
        soapModel.setFirstSaleDate(model.getFirstSaleDate());
        soapModel.setShelfLifeType(model.getShelfLifeType());
        soapModel.setItemStartDate(model.getItemStartDate());
        soapModel.setItemTypeIndication(model.getItemTypeIndication());
        soapModel.setAcquiredBamp(model.getAcquiredBamp());
        soapModel.setForm(model.getForm());
        soapModel.setItemClass(model.getItemClass());
        soapModel.setManufacturerNo(model.getManufacturerNo());
        soapModel.setPediatricExclusiveIndicator(model.getPediatricExclusiveIndicator());
        soapModel.setPackageSizeCode(model.getPackageSizeCode());
        soapModel.setDisplayBrand(model.getDisplayBrand());
        soapModel.setDra(model.getDra());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setPackageSizeIntroDate(model.getPackageSizeIntroDate());
        soapModel.setUpps(model.getUpps());
        soapModel.setIvldItemMasterSid(model.getIvldItemMasterSid());
        soapModel.setPackageSize(model.getPackageSize());
        soapModel.setShelfLife(model.getShelfLife());
        soapModel.setMarketTerminationDate(model.getMarketTerminationDate());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static IvldItemMasterSoap[] toSoapModels(IvldItemMaster[] models) {
        IvldItemMasterSoap[] soapModels = new IvldItemMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldItemMasterSoap[][] toSoapModels(IvldItemMaster[][] models) {
        IvldItemMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldItemMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldItemMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldItemMasterSoap[] toSoapModels(List<IvldItemMaster> models) {
        List<IvldItemMasterSoap> soapModels = new ArrayList<IvldItemMasterSoap>(models.size());

        for (IvldItemMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldItemMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldItemMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldItemMasterSid(pk);
    }

    public String getItemNo() {
        return _itemNo;
    }

    public void setItemNo(String itemNo) {
        _itemNo = itemNo;
    }

    public String getUdc6() {
        return _udc6;
    }

    public void setUdc6(String udc6) {
        _udc6 = udc6;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getNewFormulationIndicator() {
        return _newFormulationIndicator;
    }

    public void setNewFormulationIndicator(String newFormulationIndicator) {
        _newFormulationIndicator = newFormulationIndicator;
    }

    public String getUdc5() {
        return _udc5;
    }

    public void setUdc5(String udc5) {
        _udc5 = udc5;
    }

    public String getNewFormulationEndDate() {
        return _newFormulationEndDate;
    }

    public void setNewFormulationEndDate(String newFormulationEndDate) {
        _newFormulationEndDate = newFormulationEndDate;
    }

    public String getUdc4() {
        return _udc4;
    }

    public void setUdc4(String udc4) {
        _udc4 = udc4;
    }

    public String getClottingFactorStartDate() {
        return _clottingFactorStartDate;
    }

    public void setClottingFactorStartDate(String clottingFactorStartDate) {
        _clottingFactorStartDate = clottingFactorStartDate;
    }

    public String getSecondaryUom() {
        return _secondaryUom;
    }

    public void setSecondaryUom(String secondaryUom) {
        _secondaryUom = secondaryUom;
    }

    public String getItemDesc() {
        return _itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        _itemDesc = itemDesc;
    }

    public String getAuthorizedGenericEndDate() {
        return _authorizedGenericEndDate;
    }

    public void setAuthorizedGenericEndDate(String authorizedGenericEndDate) {
        _authorizedGenericEndDate = authorizedGenericEndDate;
    }

    public String getManufacturerName() {
        return _manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        _manufacturerName = manufacturerName;
    }

    public String getItemName() {
        return _itemName;
    }

    public void setItemName(String itemName) {
        _itemName = itemName;
    }

    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }

    public String getBaseCpi() {
        return _baseCpi;
    }

    public void setBaseCpi(String baseCpi) {
        _baseCpi = baseCpi;
    }

    public String getBaselineAmp() {
        return _baselineAmp;
    }

    public void setBaselineAmp(String baselineAmp) {
        _baselineAmp = baselineAmp;
    }

    public String getAuthorizedGeneric() {
        return _authorizedGeneric;
    }

    public void setAuthorizedGeneric(String authorizedGeneric) {
        _authorizedGeneric = authorizedGeneric;
    }

    public String getTherapeuticClass() {
        return _therapeuticClass;
    }

    public void setTherapeuticClass(String therapeuticClass) {
        _therapeuticClass = therapeuticClass;
    }

    public String getItemFamilyId() {
        return _itemFamilyId;
    }

    public void setItemFamilyId(String itemFamilyId) {
        _itemFamilyId = itemFamilyId;
    }

    public String getPediatricExclusiveStartDate() {
        return _pediatricExclusiveStartDate;
    }

    public void setPediatricExclusiveStartDate(
        String pediatricExclusiveStartDate) {
        _pediatricExclusiveStartDate = pediatricExclusiveStartDate;
    }

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    public String getPrimaryUom() {
        return _primaryUom;
    }

    public void setPrimaryUom(String primaryUom) {
        _primaryUom = primaryUom;
    }

    public String getNdc9() {
        return _ndc9;
    }

    public void setNdc9(String ndc9) {
        _ndc9 = ndc9;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public String getLastLotExpirationDate() {
        return _lastLotExpirationDate;
    }

    public void setLastLotExpirationDate(String lastLotExpirationDate) {
        _lastLotExpirationDate = lastLotExpirationDate;
    }

    public String getErrorField() {
        return _errorField;
    }

    public void setErrorField(String errorField) {
        _errorField = errorField;
    }

    public String getItemCode() {
        return _itemCode;
    }

    public void setItemCode(String itemCode) {
        _itemCode = itemCode;
    }

    public String getStrength() {
        return _strength;
    }

    public void setStrength(String strength) {
        _strength = strength;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getBrand() {
        return _brand;
    }

    public void setBrand(String brand) {
        _brand = brand;
    }

    public String getNdc8() {
        return _ndc8;
    }

    public void setNdc8(String ndc8) {
        _ndc8 = ndc8;
    }

    public String getLabelerCode() {
        return _labelerCode;
    }

    public void setLabelerCode(String labelerCode) {
        _labelerCode = labelerCode;
    }

    public String getUdc3() {
        return _udc3;
    }

    public void setUdc3(String udc3) {
        _udc3 = udc3;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getUdc2() {
        return _udc2;
    }

    public void setUdc2(String udc2) {
        _udc2 = udc2;
    }

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    public String getUdc1() {
        return _udc1;
    }

    public void setUdc1(String udc1) {
        _udc1 = udc1;
    }

    public String getAcquiredAmp() {
        return _acquiredAmp;
    }

    public void setAcquiredAmp(String acquiredAmp) {
        _acquiredAmp = acquiredAmp;
    }

    public String getDiscontinuationDate() {
        return _discontinuationDate;
    }

    public void setDiscontinuationDate(String discontinuationDate) {
        _discontinuationDate = discontinuationDate;
    }

    public String getItemMasterIntfid() {
        return _itemMasterIntfid;
    }

    public void setItemMasterIntfid(String itemMasterIntfid) {
        _itemMasterIntfid = itemMasterIntfid;
    }

    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;
    }

    public String getDivestitureDate() {
        return _divestitureDate;
    }

    public void setDivestitureDate(String divestitureDate) {
        _divestitureDate = divestitureDate;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getBaseCpiPeriod() {
        return _baseCpiPeriod;
    }

    public void setBaseCpiPeriod(String baseCpiPeriod) {
        _baseCpiPeriod = baseCpiPeriod;
    }

    public String getClottingFactorEndDate() {
        return _clottingFactorEndDate;
    }

    public void setClottingFactorEndDate(String clottingFactorEndDate) {
        _clottingFactorEndDate = clottingFactorEndDate;
    }

    public String getDosesPerUnit() {
        return _dosesPerUnit;
    }

    public void setDosesPerUnit(String dosesPerUnit) {
        _dosesPerUnit = dosesPerUnit;
    }

    public String getManufacturerId() {
        return _manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        _manufacturerId = manufacturerId;
    }

    public String getClottingFactorIndicator() {
        return _clottingFactorIndicator;
    }

    public void setClottingFactorIndicator(String clottingFactorIndicator) {
        _clottingFactorIndicator = clottingFactorIndicator;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getAcquisitionDate() {
        return _acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        _acquisitionDate = acquisitionDate;
    }

    public String getDualPricingIndicator() {
        return _dualPricingIndicator;
    }

    public void setDualPricingIndicator(String dualPricingIndicator) {
        _dualPricingIndicator = dualPricingIndicator;
    }

    public String getNonFederalExpirationDate() {
        return _nonFederalExpirationDate;
    }

    public void setNonFederalExpirationDate(String nonFederalExpirationDate) {
        _nonFederalExpirationDate = nonFederalExpirationDate;
    }

    public String getErrorCode() {
        return _errorCode;
    }

    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;
    }

    public String getNewFormulation() {
        return _newFormulation;
    }

    public void setNewFormulation(String newFormulation) {
        _newFormulation = newFormulation;
    }

    public String getObraBamp() {
        return _obraBamp;
    }

    public void setObraBamp(String obraBamp) {
        _obraBamp = obraBamp;
    }

    public String getBrandId() {
        return _brandId;
    }

    public void setBrandId(String brandId) {
        _brandId = brandId;
    }

    public String getItemStatus() {
        return _itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        _itemStatus = itemStatus;
    }

    public String getAuthorizedGenericStartDate() {
        return _authorizedGenericStartDate;
    }

    public void setAuthorizedGenericStartDate(String authorizedGenericStartDate) {
        _authorizedGenericStartDate = authorizedGenericStartDate;
    }

    public String getNewFormulationStartDate() {
        return _newFormulationStartDate;
    }

    public void setNewFormulationStartDate(String newFormulationStartDate) {
        _newFormulationStartDate = newFormulationStartDate;
    }

    public String getItemCategory() {
        return _itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        _itemCategory = itemCategory;
    }

    public String getItemEndDate() {
        return _itemEndDate;
    }

    public void setItemEndDate(String itemEndDate) {
        _itemEndDate = itemEndDate;
    }

    public String getItemType() {
        return _itemType;
    }

    public void setItemType(String itemType) {
        _itemType = itemType;
    }

    public String getPediatricExclusiveEndDate() {
        return _pediatricExclusiveEndDate;
    }

    public void setPediatricExclusiveEndDate(String pediatricExclusiveEndDate) {
        _pediatricExclusiveEndDate = pediatricExclusiveEndDate;
    }

    public String getOrganizationKey() {
        return _organizationKey;
    }

    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;
    }

    public String getFirstSaleDate() {
        return _firstSaleDate;
    }

    public void setFirstSaleDate(String firstSaleDate) {
        _firstSaleDate = firstSaleDate;
    }

    public String getShelfLifeType() {
        return _shelfLifeType;
    }

    public void setShelfLifeType(String shelfLifeType) {
        _shelfLifeType = shelfLifeType;
    }

    public String getItemStartDate() {
        return _itemStartDate;
    }

    public void setItemStartDate(String itemStartDate) {
        _itemStartDate = itemStartDate;
    }

    public String getItemTypeIndication() {
        return _itemTypeIndication;
    }

    public void setItemTypeIndication(String itemTypeIndication) {
        _itemTypeIndication = itemTypeIndication;
    }

    public String getAcquiredBamp() {
        return _acquiredBamp;
    }

    public void setAcquiredBamp(String acquiredBamp) {
        _acquiredBamp = acquiredBamp;
    }

    public String getForm() {
        return _form;
    }

    public void setForm(String form) {
        _form = form;
    }

    public String getItemClass() {
        return _itemClass;
    }

    public void setItemClass(String itemClass) {
        _itemClass = itemClass;
    }

    public String getManufacturerNo() {
        return _manufacturerNo;
    }

    public void setManufacturerNo(String manufacturerNo) {
        _manufacturerNo = manufacturerNo;
    }

    public String getPediatricExclusiveIndicator() {
        return _pediatricExclusiveIndicator;
    }

    public void setPediatricExclusiveIndicator(
        String pediatricExclusiveIndicator) {
        _pediatricExclusiveIndicator = pediatricExclusiveIndicator;
    }

    public String getPackageSizeCode() {
        return _packageSizeCode;
    }

    public void setPackageSizeCode(String packageSizeCode) {
        _packageSizeCode = packageSizeCode;
    }

    public String getDisplayBrand() {
        return _displayBrand;
    }

    public void setDisplayBrand(String displayBrand) {
        _displayBrand = displayBrand;
    }

    public String getDra() {
        return _dra;
    }

    public void setDra(String dra) {
        _dra = dra;
    }

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public String getPackageSizeIntroDate() {
        return _packageSizeIntroDate;
    }

    public void setPackageSizeIntroDate(String packageSizeIntroDate) {
        _packageSizeIntroDate = packageSizeIntroDate;
    }

    public String getUpps() {
        return _upps;
    }

    public void setUpps(String upps) {
        _upps = upps;
    }

    public int getIvldItemMasterSid() {
        return _ivldItemMasterSid;
    }

    public void setIvldItemMasterSid(int ivldItemMasterSid) {
        _ivldItemMasterSid = ivldItemMasterSid;
    }

    public String getPackageSize() {
        return _packageSize;
    }

    public void setPackageSize(String packageSize) {
        _packageSize = packageSize;
    }

    public String getShelfLife() {
        return _shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        _shelfLife = shelfLife;
    }

    public String getMarketTerminationDate() {
        return _marketTerminationDate;
    }

    public void setMarketTerminationDate(String marketTerminationDate) {
        _marketTerminationDate = marketTerminationDate;
    }

    public boolean getCheckRecord() {
        return _checkRecord;
    }

    public boolean isCheckRecord() {
        return _checkRecord;
    }

    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;
    }
}
