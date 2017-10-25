package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.IvldItemMasterLocalServiceUtil;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class IvldItemMasterClp extends BaseModelImpl<IvldItemMaster>
    implements IvldItemMaster {
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
    private BaseModel<?> _ivldItemMasterRemoteModel;

    public IvldItemMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldItemMaster.class;
    }

    @Override
    public String getModelClassName() {
        return IvldItemMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldItemMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldItemMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldItemMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemNo", getItemNo());
        attributes.put("udc6", getUdc6());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("newFormulationIndicator", getNewFormulationIndicator());
        attributes.put("udc5", getUdc5());
        attributes.put("newFormulationEndDate", getNewFormulationEndDate());
        attributes.put("udc4", getUdc4());
        attributes.put("clottingFactorStartDate", getClottingFactorStartDate());
        attributes.put("secondaryUom", getSecondaryUom());
        attributes.put("itemDesc", getItemDesc());
        attributes.put("authorizedGenericEndDate", getAuthorizedGenericEndDate());
        attributes.put("manufacturerName", getManufacturerName());
        attributes.put("itemName", getItemName());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("status", getStatus());
        attributes.put("baseCpi", getBaseCpi());
        attributes.put("baselineAmp", getBaselineAmp());
        attributes.put("authorizedGeneric", getAuthorizedGeneric());
        attributes.put("therapeuticClass", getTherapeuticClass());
        attributes.put("itemFamilyId", getItemFamilyId());
        attributes.put("pediatricExclusiveStartDate",
            getPediatricExclusiveStartDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("primaryUom", getPrimaryUom());
        attributes.put("ndc9", getNdc9());
        attributes.put("itemId", getItemId());
        attributes.put("lastLotExpirationDate", getLastLotExpirationDate());
        attributes.put("errorField", getErrorField());
        attributes.put("itemCode", getItemCode());
        attributes.put("strength", getStrength());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("brand", getBrand());
        attributes.put("ndc8", getNdc8());
        attributes.put("labelerCode", getLabelerCode());
        attributes.put("udc3", getUdc3());
        attributes.put("source", getSource());
        attributes.put("udc2", getUdc2());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("udc1", getUdc1());
        attributes.put("acquiredAmp", getAcquiredAmp());
        attributes.put("discontinuationDate", getDiscontinuationDate());
        attributes.put("itemMasterIntfid", getItemMasterIntfid());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("divestitureDate", getDivestitureDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("baseCpiPeriod", getBaseCpiPeriod());
        attributes.put("clottingFactorEndDate", getClottingFactorEndDate());
        attributes.put("dosesPerUnit", getDosesPerUnit());
        attributes.put("manufacturerId", getManufacturerId());
        attributes.put("clottingFactorIndicator", getClottingFactorIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("acquisitionDate", getAcquisitionDate());
        attributes.put("dualPricingIndicator", getDualPricingIndicator());
        attributes.put("nonFederalExpirationDate", getNonFederalExpirationDate());
        attributes.put("errorCode", getErrorCode());
        attributes.put("newFormulation", getNewFormulation());
        attributes.put("obraBamp", getObraBamp());
        attributes.put("brandId", getBrandId());
        attributes.put("itemStatus", getItemStatus());
        attributes.put("authorizedGenericStartDate",
            getAuthorizedGenericStartDate());
        attributes.put("newFormulationStartDate", getNewFormulationStartDate());
        attributes.put("itemCategory", getItemCategory());
        attributes.put("itemEndDate", getItemEndDate());
        attributes.put("itemType", getItemType());
        attributes.put("pediatricExclusiveEndDate",
            getPediatricExclusiveEndDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("firstSaleDate", getFirstSaleDate());
        attributes.put("shelfLifeType", getShelfLifeType());
        attributes.put("itemStartDate", getItemStartDate());
        attributes.put("itemTypeIndication", getItemTypeIndication());
        attributes.put("acquiredBamp", getAcquiredBamp());
        attributes.put("form", getForm());
        attributes.put("itemClass", getItemClass());
        attributes.put("manufacturerNo", getManufacturerNo());
        attributes.put("pediatricExclusiveIndicator",
            getPediatricExclusiveIndicator());
        attributes.put("packageSizeCode", getPackageSizeCode());
        attributes.put("displayBrand", getDisplayBrand());
        attributes.put("dra", getDra());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("packageSizeIntroDate", getPackageSizeIntroDate());
        attributes.put("upps", getUpps());
        attributes.put("ivldItemMasterSid", getIvldItemMasterSid());
        attributes.put("packageSize", getPackageSize());
        attributes.put("shelfLife", getShelfLife());
        attributes.put("marketTerminationDate", getMarketTerminationDate());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String newFormulationIndicator = (String) attributes.get(
                "newFormulationIndicator");

        if (newFormulationIndicator != null) {
            setNewFormulationIndicator(newFormulationIndicator);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        String newFormulationEndDate = (String) attributes.get(
                "newFormulationEndDate");

        if (newFormulationEndDate != null) {
            setNewFormulationEndDate(newFormulationEndDate);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String clottingFactorStartDate = (String) attributes.get(
                "clottingFactorStartDate");

        if (clottingFactorStartDate != null) {
            setClottingFactorStartDate(clottingFactorStartDate);
        }

        String secondaryUom = (String) attributes.get("secondaryUom");

        if (secondaryUom != null) {
            setSecondaryUom(secondaryUom);
        }

        String itemDesc = (String) attributes.get("itemDesc");

        if (itemDesc != null) {
            setItemDesc(itemDesc);
        }

        String authorizedGenericEndDate = (String) attributes.get(
                "authorizedGenericEndDate");

        if (authorizedGenericEndDate != null) {
            setAuthorizedGenericEndDate(authorizedGenericEndDate);
        }

        String manufacturerName = (String) attributes.get("manufacturerName");

        if (manufacturerName != null) {
            setManufacturerName(manufacturerName);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        String baseCpi = (String) attributes.get("baseCpi");

        if (baseCpi != null) {
            setBaseCpi(baseCpi);
        }

        String baselineAmp = (String) attributes.get("baselineAmp");

        if (baselineAmp != null) {
            setBaselineAmp(baselineAmp);
        }

        String authorizedGeneric = (String) attributes.get("authorizedGeneric");

        if (authorizedGeneric != null) {
            setAuthorizedGeneric(authorizedGeneric);
        }

        String therapeuticClass = (String) attributes.get("therapeuticClass");

        if (therapeuticClass != null) {
            setTherapeuticClass(therapeuticClass);
        }

        String itemFamilyId = (String) attributes.get("itemFamilyId");

        if (itemFamilyId != null) {
            setItemFamilyId(itemFamilyId);
        }

        String pediatricExclusiveStartDate = (String) attributes.get(
                "pediatricExclusiveStartDate");

        if (pediatricExclusiveStartDate != null) {
            setPediatricExclusiveStartDate(pediatricExclusiveStartDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String primaryUom = (String) attributes.get("primaryUom");

        if (primaryUom != null) {
            setPrimaryUom(primaryUom);
        }

        String ndc9 = (String) attributes.get("ndc9");

        if (ndc9 != null) {
            setNdc9(ndc9);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String lastLotExpirationDate = (String) attributes.get(
                "lastLotExpirationDate");

        if (lastLotExpirationDate != null) {
            setLastLotExpirationDate(lastLotExpirationDate);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String itemCode = (String) attributes.get("itemCode");

        if (itemCode != null) {
            setItemCode(itemCode);
        }

        String strength = (String) attributes.get("strength");

        if (strength != null) {
            setStrength(strength);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String brand = (String) attributes.get("brand");

        if (brand != null) {
            setBrand(brand);
        }

        String ndc8 = (String) attributes.get("ndc8");

        if (ndc8 != null) {
            setNdc8(ndc8);
        }

        String labelerCode = (String) attributes.get("labelerCode");

        if (labelerCode != null) {
            setLabelerCode(labelerCode);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        String acquiredAmp = (String) attributes.get("acquiredAmp");

        if (acquiredAmp != null) {
            setAcquiredAmp(acquiredAmp);
        }

        String discontinuationDate = (String) attributes.get(
                "discontinuationDate");

        if (discontinuationDate != null) {
            setDiscontinuationDate(discontinuationDate);
        }

        String itemMasterIntfid = (String) attributes.get("itemMasterIntfid");

        if (itemMasterIntfid != null) {
            setItemMasterIntfid(itemMasterIntfid);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String divestitureDate = (String) attributes.get("divestitureDate");

        if (divestitureDate != null) {
            setDivestitureDate(divestitureDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String baseCpiPeriod = (String) attributes.get("baseCpiPeriod");

        if (baseCpiPeriod != null) {
            setBaseCpiPeriod(baseCpiPeriod);
        }

        String clottingFactorEndDate = (String) attributes.get(
                "clottingFactorEndDate");

        if (clottingFactorEndDate != null) {
            setClottingFactorEndDate(clottingFactorEndDate);
        }

        String dosesPerUnit = (String) attributes.get("dosesPerUnit");

        if (dosesPerUnit != null) {
            setDosesPerUnit(dosesPerUnit);
        }

        String manufacturerId = (String) attributes.get("manufacturerId");

        if (manufacturerId != null) {
            setManufacturerId(manufacturerId);
        }

        String clottingFactorIndicator = (String) attributes.get(
                "clottingFactorIndicator");

        if (clottingFactorIndicator != null) {
            setClottingFactorIndicator(clottingFactorIndicator);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String acquisitionDate = (String) attributes.get("acquisitionDate");

        if (acquisitionDate != null) {
            setAcquisitionDate(acquisitionDate);
        }

        String dualPricingIndicator = (String) attributes.get(
                "dualPricingIndicator");

        if (dualPricingIndicator != null) {
            setDualPricingIndicator(dualPricingIndicator);
        }

        String nonFederalExpirationDate = (String) attributes.get(
                "nonFederalExpirationDate");

        if (nonFederalExpirationDate != null) {
            setNonFederalExpirationDate(nonFederalExpirationDate);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String newFormulation = (String) attributes.get("newFormulation");

        if (newFormulation != null) {
            setNewFormulation(newFormulation);
        }

        String obraBamp = (String) attributes.get("obraBamp");

        if (obraBamp != null) {
            setObraBamp(obraBamp);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String itemStatus = (String) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        String authorizedGenericStartDate = (String) attributes.get(
                "authorizedGenericStartDate");

        if (authorizedGenericStartDate != null) {
            setAuthorizedGenericStartDate(authorizedGenericStartDate);
        }

        String newFormulationStartDate = (String) attributes.get(
                "newFormulationStartDate");

        if (newFormulationStartDate != null) {
            setNewFormulationStartDate(newFormulationStartDate);
        }

        String itemCategory = (String) attributes.get("itemCategory");

        if (itemCategory != null) {
            setItemCategory(itemCategory);
        }

        String itemEndDate = (String) attributes.get("itemEndDate");

        if (itemEndDate != null) {
            setItemEndDate(itemEndDate);
        }

        String itemType = (String) attributes.get("itemType");

        if (itemType != null) {
            setItemType(itemType);
        }

        String pediatricExclusiveEndDate = (String) attributes.get(
                "pediatricExclusiveEndDate");

        if (pediatricExclusiveEndDate != null) {
            setPediatricExclusiveEndDate(pediatricExclusiveEndDate);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String firstSaleDate = (String) attributes.get("firstSaleDate");

        if (firstSaleDate != null) {
            setFirstSaleDate(firstSaleDate);
        }

        String shelfLifeType = (String) attributes.get("shelfLifeType");

        if (shelfLifeType != null) {
            setShelfLifeType(shelfLifeType);
        }

        String itemStartDate = (String) attributes.get("itemStartDate");

        if (itemStartDate != null) {
            setItemStartDate(itemStartDate);
        }

        String itemTypeIndication = (String) attributes.get(
                "itemTypeIndication");

        if (itemTypeIndication != null) {
            setItemTypeIndication(itemTypeIndication);
        }

        String acquiredBamp = (String) attributes.get("acquiredBamp");

        if (acquiredBamp != null) {
            setAcquiredBamp(acquiredBamp);
        }

        String form = (String) attributes.get("form");

        if (form != null) {
            setForm(form);
        }

        String itemClass = (String) attributes.get("itemClass");

        if (itemClass != null) {
            setItemClass(itemClass);
        }

        String manufacturerNo = (String) attributes.get("manufacturerNo");

        if (manufacturerNo != null) {
            setManufacturerNo(manufacturerNo);
        }

        String pediatricExclusiveIndicator = (String) attributes.get(
                "pediatricExclusiveIndicator");

        if (pediatricExclusiveIndicator != null) {
            setPediatricExclusiveIndicator(pediatricExclusiveIndicator);
        }

        String packageSizeCode = (String) attributes.get("packageSizeCode");

        if (packageSizeCode != null) {
            setPackageSizeCode(packageSizeCode);
        }

        String displayBrand = (String) attributes.get("displayBrand");

        if (displayBrand != null) {
            setDisplayBrand(displayBrand);
        }

        String dra = (String) attributes.get("dra");

        if (dra != null) {
            setDra(dra);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String packageSizeIntroDate = (String) attributes.get(
                "packageSizeIntroDate");

        if (packageSizeIntroDate != null) {
            setPackageSizeIntroDate(packageSizeIntroDate);
        }

        String upps = (String) attributes.get("upps");

        if (upps != null) {
            setUpps(upps);
        }

        Integer ivldItemMasterSid = (Integer) attributes.get(
                "ivldItemMasterSid");

        if (ivldItemMasterSid != null) {
            setIvldItemMasterSid(ivldItemMasterSid);
        }

        String packageSize = (String) attributes.get("packageSize");

        if (packageSize != null) {
            setPackageSize(packageSize);
        }

        String shelfLife = (String) attributes.get("shelfLife");

        if (shelfLife != null) {
            setShelfLife(shelfLife);
        }

        String marketTerminationDate = (String) attributes.get(
                "marketTerminationDate");

        if (marketTerminationDate != null) {
            setMarketTerminationDate(marketTerminationDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getItemNo() {
        return _itemNo;
    }

    @Override
    public void setItemNo(String itemNo) {
        _itemNo = itemNo;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc6() {
        return _udc6;
    }

    @Override
    public void setUdc6(String udc6) {
        _udc6 = udc6;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc6", String.class);

                method.invoke(_ivldItemMasterRemoteModel, udc6);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldItemMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNewFormulationIndicator() {
        return _newFormulationIndicator;
    }

    @Override
    public void setNewFormulationIndicator(String newFormulationIndicator) {
        _newFormulationIndicator = newFormulationIndicator;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNewFormulationIndicator",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel,
                    newFormulationIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc5() {
        return _udc5;
    }

    @Override
    public void setUdc5(String udc5) {
        _udc5 = udc5;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc5", String.class);

                method.invoke(_ivldItemMasterRemoteModel, udc5);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNewFormulationEndDate() {
        return _newFormulationEndDate;
    }

    @Override
    public void setNewFormulationEndDate(String newFormulationEndDate) {
        _newFormulationEndDate = newFormulationEndDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNewFormulationEndDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, newFormulationEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc4() {
        return _udc4;
    }

    @Override
    public void setUdc4(String udc4) {
        _udc4 = udc4;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc4", String.class);

                method.invoke(_ivldItemMasterRemoteModel, udc4);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getClottingFactorStartDate() {
        return _clottingFactorStartDate;
    }

    @Override
    public void setClottingFactorStartDate(String clottingFactorStartDate) {
        _clottingFactorStartDate = clottingFactorStartDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setClottingFactorStartDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel,
                    clottingFactorStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSecondaryUom() {
        return _secondaryUom;
    }

    @Override
    public void setSecondaryUom(String secondaryUom) {
        _secondaryUom = secondaryUom;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSecondaryUom", String.class);

                method.invoke(_ivldItemMasterRemoteModel, secondaryUom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemDesc() {
        return _itemDesc;
    }

    @Override
    public void setItemDesc(String itemDesc) {
        _itemDesc = itemDesc;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemDesc", String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemDesc);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAuthorizedGenericEndDate() {
        return _authorizedGenericEndDate;
    }

    @Override
    public void setAuthorizedGenericEndDate(String authorizedGenericEndDate) {
        _authorizedGenericEndDate = authorizedGenericEndDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorizedGenericEndDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel,
                    authorizedGenericEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getManufacturerName() {
        return _manufacturerName;
    }

    @Override
    public void setManufacturerName(String manufacturerName) {
        _manufacturerName = manufacturerName;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setManufacturerName",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, manufacturerName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemName() {
        return _itemName;
    }

    @Override
    public void setItemName(String itemName) {
        _itemName = itemName;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    @Override
    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStatus() {
        return _status;
    }

    @Override
    public void setStatus(String status) {
        _status = status;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setStatus", String.class);

                method.invoke(_ivldItemMasterRemoteModel, status);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBaseCpi() {
        return _baseCpi;
    }

    @Override
    public void setBaseCpi(String baseCpi) {
        _baseCpi = baseCpi;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseCpi", String.class);

                method.invoke(_ivldItemMasterRemoteModel, baseCpi);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBaselineAmp() {
        return _baselineAmp;
    }

    @Override
    public void setBaselineAmp(String baselineAmp) {
        _baselineAmp = baselineAmp;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaselineAmp", String.class);

                method.invoke(_ivldItemMasterRemoteModel, baselineAmp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAuthorizedGeneric() {
        return _authorizedGeneric;
    }

    @Override
    public void setAuthorizedGeneric(String authorizedGeneric) {
        _authorizedGeneric = authorizedGeneric;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorizedGeneric",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, authorizedGeneric);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTherapeuticClass() {
        return _therapeuticClass;
    }

    @Override
    public void setTherapeuticClass(String therapeuticClass) {
        _therapeuticClass = therapeuticClass;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setTherapeuticClass",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, therapeuticClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemFamilyId() {
        return _itemFamilyId;
    }

    @Override
    public void setItemFamilyId(String itemFamilyId) {
        _itemFamilyId = itemFamilyId;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemFamilyId", String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemFamilyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPediatricExclusiveStartDate() {
        return _pediatricExclusiveStartDate;
    }

    @Override
    public void setPediatricExclusiveStartDate(
        String pediatricExclusiveStartDate) {
        _pediatricExclusiveStartDate = pediatricExclusiveStartDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPediatricExclusiveStartDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel,
                    pediatricExclusiveStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldItemMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPrimaryUom() {
        return _primaryUom;
    }

    @Override
    public void setPrimaryUom(String primaryUom) {
        _primaryUom = primaryUom;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPrimaryUom", String.class);

                method.invoke(_ivldItemMasterRemoteModel, primaryUom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNdc9() {
        return _ndc9;
    }

    @Override
    public void setNdc9(String ndc9) {
        _ndc9 = ndc9;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc9", String.class);

                method.invoke(_ivldItemMasterRemoteModel, ndc9);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemId() {
        return _itemId;
    }

    @Override
    public void setItemId(String itemId) {
        _itemId = itemId;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLastLotExpirationDate() {
        return _lastLotExpirationDate;
    }

    @Override
    public void setLastLotExpirationDate(String lastLotExpirationDate) {
        _lastLotExpirationDate = lastLotExpirationDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastLotExpirationDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, lastLotExpirationDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorField() {
        return _errorField;
    }

    @Override
    public void setErrorField(String errorField) {
        _errorField = errorField;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldItemMasterRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemCode() {
        return _itemCode;
    }

    @Override
    public void setItemCode(String itemCode) {
        _itemCode = itemCode;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemCode", String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStrength() {
        return _strength;
    }

    @Override
    public void setStrength(String strength) {
        _strength = strength;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setStrength", String.class);

                method.invoke(_ivldItemMasterRemoteModel, strength);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldItemMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrand() {
        return _brand;
    }

    @Override
    public void setBrand(String brand) {
        _brand = brand;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrand", String.class);

                method.invoke(_ivldItemMasterRemoteModel, brand);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNdc8() {
        return _ndc8;
    }

    @Override
    public void setNdc8(String ndc8) {
        _ndc8 = ndc8;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc8", String.class);

                method.invoke(_ivldItemMasterRemoteModel, ndc8);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLabelerCode() {
        return _labelerCode;
    }

    @Override
    public void setLabelerCode(String labelerCode) {
        _labelerCode = labelerCode;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLabelerCode", String.class);

                method.invoke(_ivldItemMasterRemoteModel, labelerCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc3() {
        return _udc3;
    }

    @Override
    public void setUdc3(String udc3) {
        _udc3 = udc3;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc3", String.class);

                method.invoke(_ivldItemMasterRemoteModel, udc3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSource() {
        return _source;
    }

    @Override
    public void setSource(String source) {
        _source = source;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldItemMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc2() {
        return _udc2;
    }

    @Override
    public void setUdc2(String udc2) {
        _udc2 = udc2;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc2", String.class);

                method.invoke(_ivldItemMasterRemoteModel, udc2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    @Override
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc1() {
        return _udc1;
    }

    @Override
    public void setUdc1(String udc1) {
        _udc1 = udc1;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc1", String.class);

                method.invoke(_ivldItemMasterRemoteModel, udc1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAcquiredAmp() {
        return _acquiredAmp;
    }

    @Override
    public void setAcquiredAmp(String acquiredAmp) {
        _acquiredAmp = acquiredAmp;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAcquiredAmp", String.class);

                method.invoke(_ivldItemMasterRemoteModel, acquiredAmp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDiscontinuationDate() {
        return _discontinuationDate;
    }

    @Override
    public void setDiscontinuationDate(String discontinuationDate) {
        _discontinuationDate = discontinuationDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscontinuationDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, discontinuationDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemMasterIntfid() {
        return _itemMasterIntfid;
    }

    @Override
    public void setItemMasterIntfid(String itemMasterIntfid) {
        _itemMasterIntfid = itemMasterIntfid;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterIntfid",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemMasterIntfid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    @Override
    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldItemMasterRemoteModel, intfInsertedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDivestitureDate() {
        return _divestitureDate;
    }

    @Override
    public void setDivestitureDate(String divestitureDate) {
        _divestitureDate = divestitureDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDivestitureDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, divestitureDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldItemMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBaseCpiPeriod() {
        return _baseCpiPeriod;
    }

    @Override
    public void setBaseCpiPeriod(String baseCpiPeriod) {
        _baseCpiPeriod = baseCpiPeriod;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseCpiPeriod", String.class);

                method.invoke(_ivldItemMasterRemoteModel, baseCpiPeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getClottingFactorEndDate() {
        return _clottingFactorEndDate;
    }

    @Override
    public void setClottingFactorEndDate(String clottingFactorEndDate) {
        _clottingFactorEndDate = clottingFactorEndDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setClottingFactorEndDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, clottingFactorEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDosesPerUnit() {
        return _dosesPerUnit;
    }

    @Override
    public void setDosesPerUnit(String dosesPerUnit) {
        _dosesPerUnit = dosesPerUnit;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDosesPerUnit", String.class);

                method.invoke(_ivldItemMasterRemoteModel, dosesPerUnit);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getManufacturerId() {
        return _manufacturerId;
    }

    @Override
    public void setManufacturerId(String manufacturerId) {
        _manufacturerId = manufacturerId;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setManufacturerId",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, manufacturerId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getClottingFactorIndicator() {
        return _clottingFactorIndicator;
    }

    @Override
    public void setClottingFactorIndicator(String clottingFactorIndicator) {
        _clottingFactorIndicator = clottingFactorIndicator;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setClottingFactorIndicator",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel,
                    clottingFactorIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBatchId() {
        return _batchId;
    }

    @Override
    public void setBatchId(String batchId) {
        _batchId = batchId;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldItemMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAcquisitionDate() {
        return _acquisitionDate;
    }

    @Override
    public void setAcquisitionDate(String acquisitionDate) {
        _acquisitionDate = acquisitionDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAcquisitionDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, acquisitionDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDualPricingIndicator() {
        return _dualPricingIndicator;
    }

    @Override
    public void setDualPricingIndicator(String dualPricingIndicator) {
        _dualPricingIndicator = dualPricingIndicator;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDualPricingIndicator",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, dualPricingIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNonFederalExpirationDate() {
        return _nonFederalExpirationDate;
    }

    @Override
    public void setNonFederalExpirationDate(String nonFederalExpirationDate) {
        _nonFederalExpirationDate = nonFederalExpirationDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNonFederalExpirationDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel,
                    nonFederalExpirationDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorCode() {
        return _errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldItemMasterRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNewFormulation() {
        return _newFormulation;
    }

    @Override
    public void setNewFormulation(String newFormulation) {
        _newFormulation = newFormulation;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNewFormulation",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, newFormulation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getObraBamp() {
        return _obraBamp;
    }

    @Override
    public void setObraBamp(String obraBamp) {
        _obraBamp = obraBamp;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setObraBamp", String.class);

                method.invoke(_ivldItemMasterRemoteModel, obraBamp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandId() {
        return _brandId;
    }

    @Override
    public void setBrandId(String brandId) {
        _brandId = brandId;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_ivldItemMasterRemoteModel, brandId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemStatus() {
        return _itemStatus;
    }

    @Override
    public void setItemStatus(String itemStatus) {
        _itemStatus = itemStatus;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStatus", String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAuthorizedGenericStartDate() {
        return _authorizedGenericStartDate;
    }

    @Override
    public void setAuthorizedGenericStartDate(String authorizedGenericStartDate) {
        _authorizedGenericStartDate = authorizedGenericStartDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorizedGenericStartDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel,
                    authorizedGenericStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNewFormulationStartDate() {
        return _newFormulationStartDate;
    }

    @Override
    public void setNewFormulationStartDate(String newFormulationStartDate) {
        _newFormulationStartDate = newFormulationStartDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNewFormulationStartDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel,
                    newFormulationStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemCategory() {
        return _itemCategory;
    }

    @Override
    public void setItemCategory(String itemCategory) {
        _itemCategory = itemCategory;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemCategory", String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemEndDate() {
        return _itemEndDate;
    }

    @Override
    public void setItemEndDate(String itemEndDate) {
        _itemEndDate = itemEndDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemEndDate", String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemType() {
        return _itemType;
    }

    @Override
    public void setItemType(String itemType) {
        _itemType = itemType;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemType", String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPediatricExclusiveEndDate() {
        return _pediatricExclusiveEndDate;
    }

    @Override
    public void setPediatricExclusiveEndDate(String pediatricExclusiveEndDate) {
        _pediatricExclusiveEndDate = pediatricExclusiveEndDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPediatricExclusiveEndDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel,
                    pediatricExclusiveEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrganizationKey() {
        return _organizationKey;
    }

    @Override
    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, organizationKey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFirstSaleDate() {
        return _firstSaleDate;
    }

    @Override
    public void setFirstSaleDate(String firstSaleDate) {
        _firstSaleDate = firstSaleDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFirstSaleDate", String.class);

                method.invoke(_ivldItemMasterRemoteModel, firstSaleDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getShelfLifeType() {
        return _shelfLifeType;
    }

    @Override
    public void setShelfLifeType(String shelfLifeType) {
        _shelfLifeType = shelfLifeType;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setShelfLifeType", String.class);

                method.invoke(_ivldItemMasterRemoteModel, shelfLifeType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemStartDate() {
        return _itemStartDate;
    }

    @Override
    public void setItemStartDate(String itemStartDate) {
        _itemStartDate = itemStartDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStartDate", String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemTypeIndication() {
        return _itemTypeIndication;
    }

    @Override
    public void setItemTypeIndication(String itemTypeIndication) {
        _itemTypeIndication = itemTypeIndication;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemTypeIndication",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemTypeIndication);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAcquiredBamp() {
        return _acquiredBamp;
    }

    @Override
    public void setAcquiredBamp(String acquiredBamp) {
        _acquiredBamp = acquiredBamp;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAcquiredBamp", String.class);

                method.invoke(_ivldItemMasterRemoteModel, acquiredBamp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForm() {
        return _form;
    }

    @Override
    public void setForm(String form) {
        _form = form;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForm", String.class);

                method.invoke(_ivldItemMasterRemoteModel, form);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemClass() {
        return _itemClass;
    }

    @Override
    public void setItemClass(String itemClass) {
        _itemClass = itemClass;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemClass", String.class);

                method.invoke(_ivldItemMasterRemoteModel, itemClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getManufacturerNo() {
        return _manufacturerNo;
    }

    @Override
    public void setManufacturerNo(String manufacturerNo) {
        _manufacturerNo = manufacturerNo;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setManufacturerNo",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, manufacturerNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPediatricExclusiveIndicator() {
        return _pediatricExclusiveIndicator;
    }

    @Override
    public void setPediatricExclusiveIndicator(
        String pediatricExclusiveIndicator) {
        _pediatricExclusiveIndicator = pediatricExclusiveIndicator;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPediatricExclusiveIndicator",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel,
                    pediatricExclusiveIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPackageSizeCode() {
        return _packageSizeCode;
    }

    @Override
    public void setPackageSizeCode(String packageSizeCode) {
        _packageSizeCode = packageSizeCode;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPackageSizeCode",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, packageSizeCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDisplayBrand() {
        return _displayBrand;
    }

    @Override
    public void setDisplayBrand(String displayBrand) {
        _displayBrand = displayBrand;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDisplayBrand", String.class);

                method.invoke(_ivldItemMasterRemoteModel, displayBrand);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDra() {
        return _dra;
    }

    @Override
    public void setDra(String dra) {
        _dra = dra;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDra", String.class);

                method.invoke(_ivldItemMasterRemoteModel, dra);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, reasonForFailure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPackageSizeIntroDate() {
        return _packageSizeIntroDate;
    }

    @Override
    public void setPackageSizeIntroDate(String packageSizeIntroDate) {
        _packageSizeIntroDate = packageSizeIntroDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPackageSizeIntroDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, packageSizeIntroDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUpps() {
        return _upps;
    }

    @Override
    public void setUpps(String upps) {
        _upps = upps;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUpps", String.class);

                method.invoke(_ivldItemMasterRemoteModel, upps);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldItemMasterSid() {
        return _ivldItemMasterSid;
    }

    @Override
    public void setIvldItemMasterSid(int ivldItemMasterSid) {
        _ivldItemMasterSid = ivldItemMasterSid;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldItemMasterSid",
                        int.class);

                method.invoke(_ivldItemMasterRemoteModel, ivldItemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPackageSize() {
        return _packageSize;
    }

    @Override
    public void setPackageSize(String packageSize) {
        _packageSize = packageSize;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPackageSize", String.class);

                method.invoke(_ivldItemMasterRemoteModel, packageSize);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getShelfLife() {
        return _shelfLife;
    }

    @Override
    public void setShelfLife(String shelfLife) {
        _shelfLife = shelfLife;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setShelfLife", String.class);

                method.invoke(_ivldItemMasterRemoteModel, shelfLife);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMarketTerminationDate() {
        return _marketTerminationDate;
    }

    @Override
    public void setMarketTerminationDate(String marketTerminationDate) {
        _marketTerminationDate = marketTerminationDate;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketTerminationDate",
                        String.class);

                method.invoke(_ivldItemMasterRemoteModel, marketTerminationDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;

        if (_ivldItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldItemMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldItemMasterRemoteModel() {
        return _ivldItemMasterRemoteModel;
    }

    public void setIvldItemMasterRemoteModel(
        BaseModel<?> ivldItemMasterRemoteModel) {
        _ivldItemMasterRemoteModel = ivldItemMasterRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _ivldItemMasterRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_ivldItemMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldItemMasterLocalServiceUtil.addIvldItemMaster(this);
        } else {
            IvldItemMasterLocalServiceUtil.updateIvldItemMaster(this);
        }
    }

    @Override
    public IvldItemMaster toEscapedModel() {
        return (IvldItemMaster) ProxyUtil.newProxyInstance(IvldItemMaster.class.getClassLoader(),
            new Class[] { IvldItemMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldItemMasterClp clone = new IvldItemMasterClp();

        clone.setItemNo(getItemNo());
        clone.setUdc6(getUdc6());
        clone.setCreatedDate(getCreatedDate());
        clone.setNewFormulationIndicator(getNewFormulationIndicator());
        clone.setUdc5(getUdc5());
        clone.setNewFormulationEndDate(getNewFormulationEndDate());
        clone.setUdc4(getUdc4());
        clone.setClottingFactorStartDate(getClottingFactorStartDate());
        clone.setSecondaryUom(getSecondaryUom());
        clone.setItemDesc(getItemDesc());
        clone.setAuthorizedGenericEndDate(getAuthorizedGenericEndDate());
        clone.setManufacturerName(getManufacturerName());
        clone.setItemName(getItemName());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setStatus(getStatus());
        clone.setBaseCpi(getBaseCpi());
        clone.setBaselineAmp(getBaselineAmp());
        clone.setAuthorizedGeneric(getAuthorizedGeneric());
        clone.setTherapeuticClass(getTherapeuticClass());
        clone.setItemFamilyId(getItemFamilyId());
        clone.setPediatricExclusiveStartDate(getPediatricExclusiveStartDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setPrimaryUom(getPrimaryUom());
        clone.setNdc9(getNdc9());
        clone.setItemId(getItemId());
        clone.setLastLotExpirationDate(getLastLotExpirationDate());
        clone.setErrorField(getErrorField());
        clone.setItemCode(getItemCode());
        clone.setStrength(getStrength());
        clone.setModifiedDate(getModifiedDate());
        clone.setBrand(getBrand());
        clone.setNdc8(getNdc8());
        clone.setLabelerCode(getLabelerCode());
        clone.setUdc3(getUdc3());
        clone.setSource(getSource());
        clone.setUdc2(getUdc2());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setUdc1(getUdc1());
        clone.setAcquiredAmp(getAcquiredAmp());
        clone.setDiscontinuationDate(getDiscontinuationDate());
        clone.setItemMasterIntfid(getItemMasterIntfid());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setDivestitureDate(getDivestitureDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setBaseCpiPeriod(getBaseCpiPeriod());
        clone.setClottingFactorEndDate(getClottingFactorEndDate());
        clone.setDosesPerUnit(getDosesPerUnit());
        clone.setManufacturerId(getManufacturerId());
        clone.setClottingFactorIndicator(getClottingFactorIndicator());
        clone.setBatchId(getBatchId());
        clone.setAcquisitionDate(getAcquisitionDate());
        clone.setDualPricingIndicator(getDualPricingIndicator());
        clone.setNonFederalExpirationDate(getNonFederalExpirationDate());
        clone.setErrorCode(getErrorCode());
        clone.setNewFormulation(getNewFormulation());
        clone.setObraBamp(getObraBamp());
        clone.setBrandId(getBrandId());
        clone.setItemStatus(getItemStatus());
        clone.setAuthorizedGenericStartDate(getAuthorizedGenericStartDate());
        clone.setNewFormulationStartDate(getNewFormulationStartDate());
        clone.setItemCategory(getItemCategory());
        clone.setItemEndDate(getItemEndDate());
        clone.setItemType(getItemType());
        clone.setPediatricExclusiveEndDate(getPediatricExclusiveEndDate());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setFirstSaleDate(getFirstSaleDate());
        clone.setShelfLifeType(getShelfLifeType());
        clone.setItemStartDate(getItemStartDate());
        clone.setItemTypeIndication(getItemTypeIndication());
        clone.setAcquiredBamp(getAcquiredBamp());
        clone.setForm(getForm());
        clone.setItemClass(getItemClass());
        clone.setManufacturerNo(getManufacturerNo());
        clone.setPediatricExclusiveIndicator(getPediatricExclusiveIndicator());
        clone.setPackageSizeCode(getPackageSizeCode());
        clone.setDisplayBrand(getDisplayBrand());
        clone.setDra(getDra());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setPackageSizeIntroDate(getPackageSizeIntroDate());
        clone.setUpps(getUpps());
        clone.setIvldItemMasterSid(getIvldItemMasterSid());
        clone.setPackageSize(getPackageSize());
        clone.setShelfLife(getShelfLife());
        clone.setMarketTerminationDate(getMarketTerminationDate());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldItemMaster ivldItemMaster) {
        int primaryKey = ivldItemMaster.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldItemMasterClp)) {
            return false;
        }

        IvldItemMasterClp ivldItemMaster = (IvldItemMasterClp) obj;

        int primaryKey = ivldItemMaster.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(171);

        sb.append("{itemNo=");
        sb.append(getItemNo());
        sb.append(", udc6=");
        sb.append(getUdc6());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", newFormulationIndicator=");
        sb.append(getNewFormulationIndicator());
        sb.append(", udc5=");
        sb.append(getUdc5());
        sb.append(", newFormulationEndDate=");
        sb.append(getNewFormulationEndDate());
        sb.append(", udc4=");
        sb.append(getUdc4());
        sb.append(", clottingFactorStartDate=");
        sb.append(getClottingFactorStartDate());
        sb.append(", secondaryUom=");
        sb.append(getSecondaryUom());
        sb.append(", itemDesc=");
        sb.append(getItemDesc());
        sb.append(", authorizedGenericEndDate=");
        sb.append(getAuthorizedGenericEndDate());
        sb.append(", manufacturerName=");
        sb.append(getManufacturerName());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", baseCpi=");
        sb.append(getBaseCpi());
        sb.append(", baselineAmp=");
        sb.append(getBaselineAmp());
        sb.append(", authorizedGeneric=");
        sb.append(getAuthorizedGeneric());
        sb.append(", therapeuticClass=");
        sb.append(getTherapeuticClass());
        sb.append(", itemFamilyId=");
        sb.append(getItemFamilyId());
        sb.append(", pediatricExclusiveStartDate=");
        sb.append(getPediatricExclusiveStartDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", primaryUom=");
        sb.append(getPrimaryUom());
        sb.append(", ndc9=");
        sb.append(getNdc9());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", lastLotExpirationDate=");
        sb.append(getLastLotExpirationDate());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", itemCode=");
        sb.append(getItemCode());
        sb.append(", strength=");
        sb.append(getStrength());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", brand=");
        sb.append(getBrand());
        sb.append(", ndc8=");
        sb.append(getNdc8());
        sb.append(", labelerCode=");
        sb.append(getLabelerCode());
        sb.append(", udc3=");
        sb.append(getUdc3());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", udc2=");
        sb.append(getUdc2());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", udc1=");
        sb.append(getUdc1());
        sb.append(", acquiredAmp=");
        sb.append(getAcquiredAmp());
        sb.append(", discontinuationDate=");
        sb.append(getDiscontinuationDate());
        sb.append(", itemMasterIntfid=");
        sb.append(getItemMasterIntfid());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", divestitureDate=");
        sb.append(getDivestitureDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", baseCpiPeriod=");
        sb.append(getBaseCpiPeriod());
        sb.append(", clottingFactorEndDate=");
        sb.append(getClottingFactorEndDate());
        sb.append(", dosesPerUnit=");
        sb.append(getDosesPerUnit());
        sb.append(", manufacturerId=");
        sb.append(getManufacturerId());
        sb.append(", clottingFactorIndicator=");
        sb.append(getClottingFactorIndicator());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", acquisitionDate=");
        sb.append(getAcquisitionDate());
        sb.append(", dualPricingIndicator=");
        sb.append(getDualPricingIndicator());
        sb.append(", nonFederalExpirationDate=");
        sb.append(getNonFederalExpirationDate());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", newFormulation=");
        sb.append(getNewFormulation());
        sb.append(", obraBamp=");
        sb.append(getObraBamp());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", itemStatus=");
        sb.append(getItemStatus());
        sb.append(", authorizedGenericStartDate=");
        sb.append(getAuthorizedGenericStartDate());
        sb.append(", newFormulationStartDate=");
        sb.append(getNewFormulationStartDate());
        sb.append(", itemCategory=");
        sb.append(getItemCategory());
        sb.append(", itemEndDate=");
        sb.append(getItemEndDate());
        sb.append(", itemType=");
        sb.append(getItemType());
        sb.append(", pediatricExclusiveEndDate=");
        sb.append(getPediatricExclusiveEndDate());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", firstSaleDate=");
        sb.append(getFirstSaleDate());
        sb.append(", shelfLifeType=");
        sb.append(getShelfLifeType());
        sb.append(", itemStartDate=");
        sb.append(getItemStartDate());
        sb.append(", itemTypeIndication=");
        sb.append(getItemTypeIndication());
        sb.append(", acquiredBamp=");
        sb.append(getAcquiredBamp());
        sb.append(", form=");
        sb.append(getForm());
        sb.append(", itemClass=");
        sb.append(getItemClass());
        sb.append(", manufacturerNo=");
        sb.append(getManufacturerNo());
        sb.append(", pediatricExclusiveIndicator=");
        sb.append(getPediatricExclusiveIndicator());
        sb.append(", packageSizeCode=");
        sb.append(getPackageSizeCode());
        sb.append(", displayBrand=");
        sb.append(getDisplayBrand());
        sb.append(", dra=");
        sb.append(getDra());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", packageSizeIntroDate=");
        sb.append(getPackageSizeIntroDate());
        sb.append(", upps=");
        sb.append(getUpps());
        sb.append(", ivldItemMasterSid=");
        sb.append(getIvldItemMasterSid());
        sb.append(", packageSize=");
        sb.append(getPackageSize());
        sb.append(", shelfLife=");
        sb.append(getShelfLife());
        sb.append(", marketTerminationDate=");
        sb.append(getMarketTerminationDate());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(259);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.IvldItemMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc6</column-name><column-value><![CDATA[");
        sb.append(getUdc6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>newFormulationIndicator</column-name><column-value><![CDATA[");
        sb.append(getNewFormulationIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc5</column-name><column-value><![CDATA[");
        sb.append(getUdc5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>newFormulationEndDate</column-name><column-value><![CDATA[");
        sb.append(getNewFormulationEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc4</column-name><column-value><![CDATA[");
        sb.append(getUdc4());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>clottingFactorStartDate</column-name><column-value><![CDATA[");
        sb.append(getClottingFactorStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>secondaryUom</column-name><column-value><![CDATA[");
        sb.append(getSecondaryUom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemDesc</column-name><column-value><![CDATA[");
        sb.append(getItemDesc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorizedGenericEndDate</column-name><column-value><![CDATA[");
        sb.append(getAuthorizedGenericEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>manufacturerName</column-name><column-value><![CDATA[");
        sb.append(getManufacturerName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseCpi</column-name><column-value><![CDATA[");
        sb.append(getBaseCpi());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baselineAmp</column-name><column-value><![CDATA[");
        sb.append(getBaselineAmp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorizedGeneric</column-name><column-value><![CDATA[");
        sb.append(getAuthorizedGeneric());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>therapeuticClass</column-name><column-value><![CDATA[");
        sb.append(getTherapeuticClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemFamilyId</column-name><column-value><![CDATA[");
        sb.append(getItemFamilyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pediatricExclusiveStartDate</column-name><column-value><![CDATA[");
        sb.append(getPediatricExclusiveStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>primaryUom</column-name><column-value><![CDATA[");
        sb.append(getPrimaryUom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ndc9</column-name><column-value><![CDATA[");
        sb.append(getNdc9());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastLotExpirationDate</column-name><column-value><![CDATA[");
        sb.append(getLastLotExpirationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemCode</column-name><column-value><![CDATA[");
        sb.append(getItemCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>strength</column-name><column-value><![CDATA[");
        sb.append(getStrength());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brand</column-name><column-value><![CDATA[");
        sb.append(getBrand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ndc8</column-name><column-value><![CDATA[");
        sb.append(getNdc8());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>labelerCode</column-name><column-value><![CDATA[");
        sb.append(getLabelerCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc3</column-name><column-value><![CDATA[");
        sb.append(getUdc3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc2</column-name><column-value><![CDATA[");
        sb.append(getUdc2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc1</column-name><column-value><![CDATA[");
        sb.append(getUdc1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acquiredAmp</column-name><column-value><![CDATA[");
        sb.append(getAcquiredAmp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discontinuationDate</column-name><column-value><![CDATA[");
        sb.append(getDiscontinuationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterIntfid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>divestitureDate</column-name><column-value><![CDATA[");
        sb.append(getDivestitureDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseCpiPeriod</column-name><column-value><![CDATA[");
        sb.append(getBaseCpiPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>clottingFactorEndDate</column-name><column-value><![CDATA[");
        sb.append(getClottingFactorEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dosesPerUnit</column-name><column-value><![CDATA[");
        sb.append(getDosesPerUnit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>manufacturerId</column-name><column-value><![CDATA[");
        sb.append(getManufacturerId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>clottingFactorIndicator</column-name><column-value><![CDATA[");
        sb.append(getClottingFactorIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acquisitionDate</column-name><column-value><![CDATA[");
        sb.append(getAcquisitionDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dualPricingIndicator</column-name><column-value><![CDATA[");
        sb.append(getDualPricingIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nonFederalExpirationDate</column-name><column-value><![CDATA[");
        sb.append(getNonFederalExpirationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>newFormulation</column-name><column-value><![CDATA[");
        sb.append(getNewFormulation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>obraBamp</column-name><column-value><![CDATA[");
        sb.append(getObraBamp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemStatus</column-name><column-value><![CDATA[");
        sb.append(getItemStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorizedGenericStartDate</column-name><column-value><![CDATA[");
        sb.append(getAuthorizedGenericStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>newFormulationStartDate</column-name><column-value><![CDATA[");
        sb.append(getNewFormulationStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemCategory</column-name><column-value><![CDATA[");
        sb.append(getItemCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemEndDate</column-name><column-value><![CDATA[");
        sb.append(getItemEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemType</column-name><column-value><![CDATA[");
        sb.append(getItemType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pediatricExclusiveEndDate</column-name><column-value><![CDATA[");
        sb.append(getPediatricExclusiveEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>firstSaleDate</column-name><column-value><![CDATA[");
        sb.append(getFirstSaleDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>shelfLifeType</column-name><column-value><![CDATA[");
        sb.append(getShelfLifeType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemStartDate</column-name><column-value><![CDATA[");
        sb.append(getItemStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemTypeIndication</column-name><column-value><![CDATA[");
        sb.append(getItemTypeIndication());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acquiredBamp</column-name><column-value><![CDATA[");
        sb.append(getAcquiredBamp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>form</column-name><column-value><![CDATA[");
        sb.append(getForm());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemClass</column-name><column-value><![CDATA[");
        sb.append(getItemClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>manufacturerNo</column-name><column-value><![CDATA[");
        sb.append(getManufacturerNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pediatricExclusiveIndicator</column-name><column-value><![CDATA[");
        sb.append(getPediatricExclusiveIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>packageSizeCode</column-name><column-value><![CDATA[");
        sb.append(getPackageSizeCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>displayBrand</column-name><column-value><![CDATA[");
        sb.append(getDisplayBrand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dra</column-name><column-value><![CDATA[");
        sb.append(getDra());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>packageSizeIntroDate</column-name><column-value><![CDATA[");
        sb.append(getPackageSizeIntroDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>upps</column-name><column-value><![CDATA[");
        sb.append(getUpps());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldItemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getIvldItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>packageSize</column-name><column-value><![CDATA[");
        sb.append(getPackageSize());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>shelfLife</column-name><column-value><![CDATA[");
        sb.append(getShelfLife());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketTerminationDate</column-name><column-value><![CDATA[");
        sb.append(getMarketTerminationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
