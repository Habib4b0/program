package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ItemMasterLocalServiceUtil;

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


public class ItemMasterClp extends BaseModelImpl<ItemMaster>
    implements ItemMaster {
    private int _itemStatus;
    private String _itemDesc;
    private Date _authorizedGenericStartDate;
    private double _acquiredAmp;
    private Date _newFormulationStartDate;
    private Date _marketTerminationDate;
    private double _obraBamp;
    private Date _modifiedDate;
    private int _therapeuticClass;
    private int _organizationKey;
    private double _acquiredBamp;
    private Date _pediatricExclusiveEndDate;
    private String _source;
    private String _newFormulation;
    private Date _divestitureDate;
    private int _primaryUom;
    private Date _newFormulationEndDate;
    private int _modifiedBy;
    private String _inboundStatus;
    private String _packageSizeCode;
    private int _secondaryUom;
    private Date _discontinuationDate;
    private Date _packageSizeIntroDate;
    private String _manufacturerId;
    private Date _itemEndDate;
    private String _itemFamilyId;
    private int _strength;
    private int _itemCategory;
    private double _upps;
    private int _shelfLifeType;
    private String _pediatricExclusiveIndicator;
    private boolean _recordLockStatus;
    private String _itemTypeIndication;
    private Date _acquisitionDate;
    private String _clottingFactorIndicator;
    private int _form;
    private String _itemName;
    private String _shelfLife;
    private Date _pediatricExclusiveStartDate;
    private Date _firstSaleDate;
    private int _itemMasterSid;
    private int _itemType;
    private String _itemId;
    private int _brandMasterSid;
    private double _baselineAmp;
    private String _dualPricingIndicator;
    private String _dosesPerUnit;
    private Date _createdDate;
    private int _createdBy;
    private String _authorizedGeneric;
    private Date _itemStartDate;
    private String _ndc9;
    private Date _authorizedGenericEndDate;
    private String _itemNo;
    private String _packageSize;
    private String _ndc8;
    private double _baseCpi;
    private String _labelerCode;
    private int _itemClass;
    private Date _clottingFactorEndDate;
    private double _dra;
    private Date _baseCpiPeriod;
    private String _newFormulationIndicator;
    private Date _lastLotExpirationDate;
    private String _batchId;
    private String _itemCode;
    private Date _clottingFactorStartDate;
    private Date _nonFederalExpirationDate;
    private String _internalNotes;
    private int _baseCpiPrecision;
    private int _baselineAmpPrecision;
    private BaseModel<?> _itemMasterRemoteModel;

    public ItemMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ItemMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ItemMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _itemMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setItemMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _itemMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemStatus", getItemStatus());
        attributes.put("itemDesc", getItemDesc());
        attributes.put("authorizedGenericStartDate",
            getAuthorizedGenericStartDate());
        attributes.put("acquiredAmp", getAcquiredAmp());
        attributes.put("newFormulationStartDate", getNewFormulationStartDate());
        attributes.put("marketTerminationDate", getMarketTerminationDate());
        attributes.put("obraBamp", getObraBamp());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("therapeuticClass", getTherapeuticClass());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("acquiredBamp", getAcquiredBamp());
        attributes.put("pediatricExclusiveEndDate",
            getPediatricExclusiveEndDate());
        attributes.put("source", getSource());
        attributes.put("newFormulation", getNewFormulation());
        attributes.put("divestitureDate", getDivestitureDate());
        attributes.put("primaryUom", getPrimaryUom());
        attributes.put("newFormulationEndDate", getNewFormulationEndDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("packageSizeCode", getPackageSizeCode());
        attributes.put("secondaryUom", getSecondaryUom());
        attributes.put("discontinuationDate", getDiscontinuationDate());
        attributes.put("packageSizeIntroDate", getPackageSizeIntroDate());
        attributes.put("manufacturerId", getManufacturerId());
        attributes.put("itemEndDate", getItemEndDate());
        attributes.put("itemFamilyId", getItemFamilyId());
        attributes.put("strength", getStrength());
        attributes.put("itemCategory", getItemCategory());
        attributes.put("upps", getUpps());
        attributes.put("shelfLifeType", getShelfLifeType());
        attributes.put("pediatricExclusiveIndicator",
            getPediatricExclusiveIndicator());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("itemTypeIndication", getItemTypeIndication());
        attributes.put("acquisitionDate", getAcquisitionDate());
        attributes.put("clottingFactorIndicator", getClottingFactorIndicator());
        attributes.put("form", getForm());
        attributes.put("itemName", getItemName());
        attributes.put("shelfLife", getShelfLife());
        attributes.put("pediatricExclusiveStartDate",
            getPediatricExclusiveStartDate());
        attributes.put("firstSaleDate", getFirstSaleDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemType", getItemType());
        attributes.put("itemId", getItemId());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("baselineAmp", getBaselineAmp());
        attributes.put("dualPricingIndicator", getDualPricingIndicator());
        attributes.put("dosesPerUnit", getDosesPerUnit());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("authorizedGeneric", getAuthorizedGeneric());
        attributes.put("itemStartDate", getItemStartDate());
        attributes.put("ndc9", getNdc9());
        attributes.put("authorizedGenericEndDate", getAuthorizedGenericEndDate());
        attributes.put("itemNo", getItemNo());
        attributes.put("packageSize", getPackageSize());
        attributes.put("ndc8", getNdc8());
        attributes.put("baseCpi", getBaseCpi());
        attributes.put("labelerCode", getLabelerCode());
        attributes.put("itemClass", getItemClass());
        attributes.put("clottingFactorEndDate", getClottingFactorEndDate());
        attributes.put("dra", getDra());
        attributes.put("baseCpiPeriod", getBaseCpiPeriod());
        attributes.put("newFormulationIndicator", getNewFormulationIndicator());
        attributes.put("lastLotExpirationDate", getLastLotExpirationDate());
        attributes.put("batchId", getBatchId());
        attributes.put("itemCode", getItemCode());
        attributes.put("clottingFactorStartDate", getClottingFactorStartDate());
        attributes.put("nonFederalExpirationDate", getNonFederalExpirationDate());
        attributes.put("internalNotes", getInternalNotes());
        attributes.put("baseCpiPrecision", getBaseCpiPrecision());
        attributes.put("baselineAmpPrecision", getBaselineAmpPrecision());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemStatus = (Integer) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        String itemDesc = (String) attributes.get("itemDesc");

        if (itemDesc != null) {
            setItemDesc(itemDesc);
        }

        Date authorizedGenericStartDate = (Date) attributes.get(
                "authorizedGenericStartDate");

        if (authorizedGenericStartDate != null) {
            setAuthorizedGenericStartDate(authorizedGenericStartDate);
        }

        Double acquiredAmp = (Double) attributes.get("acquiredAmp");

        if (acquiredAmp != null) {
            setAcquiredAmp(acquiredAmp);
        }

        Date newFormulationStartDate = (Date) attributes.get(
                "newFormulationStartDate");

        if (newFormulationStartDate != null) {
            setNewFormulationStartDate(newFormulationStartDate);
        }

        Date marketTerminationDate = (Date) attributes.get(
                "marketTerminationDate");

        if (marketTerminationDate != null) {
            setMarketTerminationDate(marketTerminationDate);
        }

        Double obraBamp = (Double) attributes.get("obraBamp");

        if (obraBamp != null) {
            setObraBamp(obraBamp);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer therapeuticClass = (Integer) attributes.get("therapeuticClass");

        if (therapeuticClass != null) {
            setTherapeuticClass(therapeuticClass);
        }

        Integer organizationKey = (Integer) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        Double acquiredBamp = (Double) attributes.get("acquiredBamp");

        if (acquiredBamp != null) {
            setAcquiredBamp(acquiredBamp);
        }

        Date pediatricExclusiveEndDate = (Date) attributes.get(
                "pediatricExclusiveEndDate");

        if (pediatricExclusiveEndDate != null) {
            setPediatricExclusiveEndDate(pediatricExclusiveEndDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String newFormulation = (String) attributes.get("newFormulation");

        if (newFormulation != null) {
            setNewFormulation(newFormulation);
        }

        Date divestitureDate = (Date) attributes.get("divestitureDate");

        if (divestitureDate != null) {
            setDivestitureDate(divestitureDate);
        }

        Integer primaryUom = (Integer) attributes.get("primaryUom");

        if (primaryUom != null) {
            setPrimaryUom(primaryUom);
        }

        Date newFormulationEndDate = (Date) attributes.get(
                "newFormulationEndDate");

        if (newFormulationEndDate != null) {
            setNewFormulationEndDate(newFormulationEndDate);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String packageSizeCode = (String) attributes.get("packageSizeCode");

        if (packageSizeCode != null) {
            setPackageSizeCode(packageSizeCode);
        }

        Integer secondaryUom = (Integer) attributes.get("secondaryUom");

        if (secondaryUom != null) {
            setSecondaryUom(secondaryUom);
        }

        Date discontinuationDate = (Date) attributes.get("discontinuationDate");

        if (discontinuationDate != null) {
            setDiscontinuationDate(discontinuationDate);
        }

        Date packageSizeIntroDate = (Date) attributes.get(
                "packageSizeIntroDate");

        if (packageSizeIntroDate != null) {
            setPackageSizeIntroDate(packageSizeIntroDate);
        }

        String manufacturerId = (String) attributes.get("manufacturerId");

        if (manufacturerId != null) {
            setManufacturerId(manufacturerId);
        }

        Date itemEndDate = (Date) attributes.get("itemEndDate");

        if (itemEndDate != null) {
            setItemEndDate(itemEndDate);
        }

        String itemFamilyId = (String) attributes.get("itemFamilyId");

        if (itemFamilyId != null) {
            setItemFamilyId(itemFamilyId);
        }

        Integer strength = (Integer) attributes.get("strength");

        if (strength != null) {
            setStrength(strength);
        }

        Integer itemCategory = (Integer) attributes.get("itemCategory");

        if (itemCategory != null) {
            setItemCategory(itemCategory);
        }

        Double upps = (Double) attributes.get("upps");

        if (upps != null) {
            setUpps(upps);
        }

        Integer shelfLifeType = (Integer) attributes.get("shelfLifeType");

        if (shelfLifeType != null) {
            setShelfLifeType(shelfLifeType);
        }

        String pediatricExclusiveIndicator = (String) attributes.get(
                "pediatricExclusiveIndicator");

        if (pediatricExclusiveIndicator != null) {
            setPediatricExclusiveIndicator(pediatricExclusiveIndicator);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String itemTypeIndication = (String) attributes.get(
                "itemTypeIndication");

        if (itemTypeIndication != null) {
            setItemTypeIndication(itemTypeIndication);
        }

        Date acquisitionDate = (Date) attributes.get("acquisitionDate");

        if (acquisitionDate != null) {
            setAcquisitionDate(acquisitionDate);
        }

        String clottingFactorIndicator = (String) attributes.get(
                "clottingFactorIndicator");

        if (clottingFactorIndicator != null) {
            setClottingFactorIndicator(clottingFactorIndicator);
        }

        Integer form = (Integer) attributes.get("form");

        if (form != null) {
            setForm(form);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String shelfLife = (String) attributes.get("shelfLife");

        if (shelfLife != null) {
            setShelfLife(shelfLife);
        }

        Date pediatricExclusiveStartDate = (Date) attributes.get(
                "pediatricExclusiveStartDate");

        if (pediatricExclusiveStartDate != null) {
            setPediatricExclusiveStartDate(pediatricExclusiveStartDate);
        }

        Date firstSaleDate = (Date) attributes.get("firstSaleDate");

        if (firstSaleDate != null) {
            setFirstSaleDate(firstSaleDate);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer itemType = (Integer) attributes.get("itemType");

        if (itemType != null) {
            setItemType(itemType);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Integer brandMasterSid = (Integer) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        Double baselineAmp = (Double) attributes.get("baselineAmp");

        if (baselineAmp != null) {
            setBaselineAmp(baselineAmp);
        }

        String dualPricingIndicator = (String) attributes.get(
                "dualPricingIndicator");

        if (dualPricingIndicator != null) {
            setDualPricingIndicator(dualPricingIndicator);
        }

        String dosesPerUnit = (String) attributes.get("dosesPerUnit");

        if (dosesPerUnit != null) {
            setDosesPerUnit(dosesPerUnit);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String authorizedGeneric = (String) attributes.get("authorizedGeneric");

        if (authorizedGeneric != null) {
            setAuthorizedGeneric(authorizedGeneric);
        }

        Date itemStartDate = (Date) attributes.get("itemStartDate");

        if (itemStartDate != null) {
            setItemStartDate(itemStartDate);
        }

        String ndc9 = (String) attributes.get("ndc9");

        if (ndc9 != null) {
            setNdc9(ndc9);
        }

        Date authorizedGenericEndDate = (Date) attributes.get(
                "authorizedGenericEndDate");

        if (authorizedGenericEndDate != null) {
            setAuthorizedGenericEndDate(authorizedGenericEndDate);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String packageSize = (String) attributes.get("packageSize");

        if (packageSize != null) {
            setPackageSize(packageSize);
        }

        String ndc8 = (String) attributes.get("ndc8");

        if (ndc8 != null) {
            setNdc8(ndc8);
        }

        Double baseCpi = (Double) attributes.get("baseCpi");

        if (baseCpi != null) {
            setBaseCpi(baseCpi);
        }

        String labelerCode = (String) attributes.get("labelerCode");

        if (labelerCode != null) {
            setLabelerCode(labelerCode);
        }

        Integer itemClass = (Integer) attributes.get("itemClass");

        if (itemClass != null) {
            setItemClass(itemClass);
        }

        Date clottingFactorEndDate = (Date) attributes.get(
                "clottingFactorEndDate");

        if (clottingFactorEndDate != null) {
            setClottingFactorEndDate(clottingFactorEndDate);
        }

        Double dra = (Double) attributes.get("dra");

        if (dra != null) {
            setDra(dra);
        }

        Date baseCpiPeriod = (Date) attributes.get("baseCpiPeriod");

        if (baseCpiPeriod != null) {
            setBaseCpiPeriod(baseCpiPeriod);
        }

        String newFormulationIndicator = (String) attributes.get(
                "newFormulationIndicator");

        if (newFormulationIndicator != null) {
            setNewFormulationIndicator(newFormulationIndicator);
        }

        Date lastLotExpirationDate = (Date) attributes.get(
                "lastLotExpirationDate");

        if (lastLotExpirationDate != null) {
            setLastLotExpirationDate(lastLotExpirationDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemCode = (String) attributes.get("itemCode");

        if (itemCode != null) {
            setItemCode(itemCode);
        }

        Date clottingFactorStartDate = (Date) attributes.get(
                "clottingFactorStartDate");

        if (clottingFactorStartDate != null) {
            setClottingFactorStartDate(clottingFactorStartDate);
        }

        Date nonFederalExpirationDate = (Date) attributes.get(
                "nonFederalExpirationDate");

        if (nonFederalExpirationDate != null) {
            setNonFederalExpirationDate(nonFederalExpirationDate);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }

        Integer baseCpiPrecision = (Integer) attributes.get("baseCpiPrecision");

        if (baseCpiPrecision != null) {
            setBaseCpiPrecision(baseCpiPrecision);
        }

        Integer baselineAmpPrecision = (Integer) attributes.get(
                "baselineAmpPrecision");

        if (baselineAmpPrecision != null) {
            setBaselineAmpPrecision(baselineAmpPrecision);
        }
    }

    @Override
    public int getItemStatus() {
        return _itemStatus;
    }

    @Override
    public void setItemStatus(int itemStatus) {
        _itemStatus = itemStatus;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStatus", int.class);

                method.invoke(_itemMasterRemoteModel, itemStatus);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemDesc", String.class);

                method.invoke(_itemMasterRemoteModel, itemDesc);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getAuthorizedGenericStartDate() {
        return _authorizedGenericStartDate;
    }

    @Override
    public void setAuthorizedGenericStartDate(Date authorizedGenericStartDate) {
        _authorizedGenericStartDate = authorizedGenericStartDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorizedGenericStartDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel, authorizedGenericStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAcquiredAmp() {
        return _acquiredAmp;
    }

    @Override
    public void setAcquiredAmp(double acquiredAmp) {
        _acquiredAmp = acquiredAmp;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAcquiredAmp", double.class);

                method.invoke(_itemMasterRemoteModel, acquiredAmp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getNewFormulationStartDate() {
        return _newFormulationStartDate;
    }

    @Override
    public void setNewFormulationStartDate(Date newFormulationStartDate) {
        _newFormulationStartDate = newFormulationStartDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNewFormulationStartDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel, newFormulationStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getMarketTerminationDate() {
        return _marketTerminationDate;
    }

    @Override
    public void setMarketTerminationDate(Date marketTerminationDate) {
        _marketTerminationDate = marketTerminationDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketTerminationDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel, marketTerminationDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getObraBamp() {
        return _obraBamp;
    }

    @Override
    public void setObraBamp(double obraBamp) {
        _obraBamp = obraBamp;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setObraBamp", double.class);

                method.invoke(_itemMasterRemoteModel, obraBamp);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_itemMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getTherapeuticClass() {
        return _therapeuticClass;
    }

    @Override
    public void setTherapeuticClass(int therapeuticClass) {
        _therapeuticClass = therapeuticClass;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setTherapeuticClass", int.class);

                method.invoke(_itemMasterRemoteModel, therapeuticClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getOrganizationKey() {
        return _organizationKey;
    }

    @Override
    public void setOrganizationKey(int organizationKey) {
        _organizationKey = organizationKey;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey", int.class);

                method.invoke(_itemMasterRemoteModel, organizationKey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAcquiredBamp() {
        return _acquiredBamp;
    }

    @Override
    public void setAcquiredBamp(double acquiredBamp) {
        _acquiredBamp = acquiredBamp;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAcquiredBamp", double.class);

                method.invoke(_itemMasterRemoteModel, acquiredBamp);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPediatricExclusiveEndDate() {
        return _pediatricExclusiveEndDate;
    }

    @Override
    public void setPediatricExclusiveEndDate(Date pediatricExclusiveEndDate) {
        _pediatricExclusiveEndDate = pediatricExclusiveEndDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPediatricExclusiveEndDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel, pediatricExclusiveEndDate);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_itemMasterRemoteModel, source);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNewFormulation",
                        String.class);

                method.invoke(_itemMasterRemoteModel, newFormulation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getDivestitureDate() {
        return _divestitureDate;
    }

    @Override
    public void setDivestitureDate(Date divestitureDate) {
        _divestitureDate = divestitureDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDivestitureDate", Date.class);

                method.invoke(_itemMasterRemoteModel, divestitureDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPrimaryUom() {
        return _primaryUom;
    }

    @Override
    public void setPrimaryUom(int primaryUom) {
        _primaryUom = primaryUom;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPrimaryUom", int.class);

                method.invoke(_itemMasterRemoteModel, primaryUom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getNewFormulationEndDate() {
        return _newFormulationEndDate;
    }

    @Override
    public void setNewFormulationEndDate(Date newFormulationEndDate) {
        _newFormulationEndDate = newFormulationEndDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNewFormulationEndDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel, newFormulationEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_itemMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInboundStatus() {
        return _inboundStatus;
    }

    @Override
    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_itemMasterRemoteModel, inboundStatus);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPackageSizeCode",
                        String.class);

                method.invoke(_itemMasterRemoteModel, packageSizeCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSecondaryUom() {
        return _secondaryUom;
    }

    @Override
    public void setSecondaryUom(int secondaryUom) {
        _secondaryUom = secondaryUom;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSecondaryUom", int.class);

                method.invoke(_itemMasterRemoteModel, secondaryUom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getDiscontinuationDate() {
        return _discontinuationDate;
    }

    @Override
    public void setDiscontinuationDate(Date discontinuationDate) {
        _discontinuationDate = discontinuationDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscontinuationDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel, discontinuationDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPackageSizeIntroDate() {
        return _packageSizeIntroDate;
    }

    @Override
    public void setPackageSizeIntroDate(Date packageSizeIntroDate) {
        _packageSizeIntroDate = packageSizeIntroDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPackageSizeIntroDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel, packageSizeIntroDate);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setManufacturerId",
                        String.class);

                method.invoke(_itemMasterRemoteModel, manufacturerId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemEndDate() {
        return _itemEndDate;
    }

    @Override
    public void setItemEndDate(Date itemEndDate) {
        _itemEndDate = itemEndDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemEndDate", Date.class);

                method.invoke(_itemMasterRemoteModel, itemEndDate);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemFamilyId", String.class);

                method.invoke(_itemMasterRemoteModel, itemFamilyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getStrength() {
        return _strength;
    }

    @Override
    public void setStrength(int strength) {
        _strength = strength;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setStrength", int.class);

                method.invoke(_itemMasterRemoteModel, strength);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemCategory() {
        return _itemCategory;
    }

    @Override
    public void setItemCategory(int itemCategory) {
        _itemCategory = itemCategory;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemCategory", int.class);

                method.invoke(_itemMasterRemoteModel, itemCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getUpps() {
        return _upps;
    }

    @Override
    public void setUpps(double upps) {
        _upps = upps;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUpps", double.class);

                method.invoke(_itemMasterRemoteModel, upps);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getShelfLifeType() {
        return _shelfLifeType;
    }

    @Override
    public void setShelfLifeType(int shelfLifeType) {
        _shelfLifeType = shelfLifeType;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setShelfLifeType", int.class);

                method.invoke(_itemMasterRemoteModel, shelfLifeType);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPediatricExclusiveIndicator",
                        String.class);

                method.invoke(_itemMasterRemoteModel,
                    pediatricExclusiveIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public boolean isRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _recordLockStatus = recordLockStatus;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_itemMasterRemoteModel, recordLockStatus);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemTypeIndication",
                        String.class);

                method.invoke(_itemMasterRemoteModel, itemTypeIndication);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getAcquisitionDate() {
        return _acquisitionDate;
    }

    @Override
    public void setAcquisitionDate(Date acquisitionDate) {
        _acquisitionDate = acquisitionDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAcquisitionDate", Date.class);

                method.invoke(_itemMasterRemoteModel, acquisitionDate);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setClottingFactorIndicator",
                        String.class);

                method.invoke(_itemMasterRemoteModel, clottingFactorIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getForm() {
        return _form;
    }

    @Override
    public void setForm(int form) {
        _form = form;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForm", int.class);

                method.invoke(_itemMasterRemoteModel, form);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_itemMasterRemoteModel, itemName);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setShelfLife", String.class);

                method.invoke(_itemMasterRemoteModel, shelfLife);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPediatricExclusiveStartDate() {
        return _pediatricExclusiveStartDate;
    }

    @Override
    public void setPediatricExclusiveStartDate(Date pediatricExclusiveStartDate) {
        _pediatricExclusiveStartDate = pediatricExclusiveStartDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPediatricExclusiveStartDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel,
                    pediatricExclusiveStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getFirstSaleDate() {
        return _firstSaleDate;
    }

    @Override
    public void setFirstSaleDate(Date firstSaleDate) {
        _firstSaleDate = firstSaleDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFirstSaleDate", Date.class);

                method.invoke(_itemMasterRemoteModel, firstSaleDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_itemMasterRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemType() {
        return _itemType;
    }

    @Override
    public void setItemType(int itemType) {
        _itemType = itemType;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemType", int.class);

                method.invoke(_itemMasterRemoteModel, itemType);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_itemMasterRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBrandMasterSid() {
        return _brandMasterSid;
    }

    @Override
    public void setBrandMasterSid(int brandMasterSid) {
        _brandMasterSid = brandMasterSid;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid", int.class);

                method.invoke(_itemMasterRemoteModel, brandMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getBaselineAmp() {
        return _baselineAmp;
    }

    @Override
    public void setBaselineAmp(double baselineAmp) {
        _baselineAmp = baselineAmp;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaselineAmp", double.class);

                method.invoke(_itemMasterRemoteModel, baselineAmp);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDualPricingIndicator",
                        String.class);

                method.invoke(_itemMasterRemoteModel, dualPricingIndicator);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDosesPerUnit", String.class);

                method.invoke(_itemMasterRemoteModel, dosesPerUnit);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_itemMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_itemMasterRemoteModel, createdBy);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorizedGeneric",
                        String.class);

                method.invoke(_itemMasterRemoteModel, authorizedGeneric);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemStartDate() {
        return _itemStartDate;
    }

    @Override
    public void setItemStartDate(Date itemStartDate) {
        _itemStartDate = itemStartDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStartDate", Date.class);

                method.invoke(_itemMasterRemoteModel, itemStartDate);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc9", String.class);

                method.invoke(_itemMasterRemoteModel, ndc9);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getAuthorizedGenericEndDate() {
        return _authorizedGenericEndDate;
    }

    @Override
    public void setAuthorizedGenericEndDate(Date authorizedGenericEndDate) {
        _authorizedGenericEndDate = authorizedGenericEndDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorizedGenericEndDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel, authorizedGenericEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemNo() {
        return _itemNo;
    }

    @Override
    public void setItemNo(String itemNo) {
        _itemNo = itemNo;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_itemMasterRemoteModel, itemNo);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPackageSize", String.class);

                method.invoke(_itemMasterRemoteModel, packageSize);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc8", String.class);

                method.invoke(_itemMasterRemoteModel, ndc8);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getBaseCpi() {
        return _baseCpi;
    }

    @Override
    public void setBaseCpi(double baseCpi) {
        _baseCpi = baseCpi;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseCpi", double.class);

                method.invoke(_itemMasterRemoteModel, baseCpi);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLabelerCode", String.class);

                method.invoke(_itemMasterRemoteModel, labelerCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemClass() {
        return _itemClass;
    }

    @Override
    public void setItemClass(int itemClass) {
        _itemClass = itemClass;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemClass", int.class);

                method.invoke(_itemMasterRemoteModel, itemClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getClottingFactorEndDate() {
        return _clottingFactorEndDate;
    }

    @Override
    public void setClottingFactorEndDate(Date clottingFactorEndDate) {
        _clottingFactorEndDate = clottingFactorEndDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setClottingFactorEndDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel, clottingFactorEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDra() {
        return _dra;
    }

    @Override
    public void setDra(double dra) {
        _dra = dra;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDra", double.class);

                method.invoke(_itemMasterRemoteModel, dra);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getBaseCpiPeriod() {
        return _baseCpiPeriod;
    }

    @Override
    public void setBaseCpiPeriod(Date baseCpiPeriod) {
        _baseCpiPeriod = baseCpiPeriod;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseCpiPeriod", Date.class);

                method.invoke(_itemMasterRemoteModel, baseCpiPeriod);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNewFormulationIndicator",
                        String.class);

                method.invoke(_itemMasterRemoteModel, newFormulationIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastLotExpirationDate() {
        return _lastLotExpirationDate;
    }

    @Override
    public void setLastLotExpirationDate(Date lastLotExpirationDate) {
        _lastLotExpirationDate = lastLotExpirationDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastLotExpirationDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel, lastLotExpirationDate);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_itemMasterRemoteModel, batchId);
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

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemCode", String.class);

                method.invoke(_itemMasterRemoteModel, itemCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getClottingFactorStartDate() {
        return _clottingFactorStartDate;
    }

    @Override
    public void setClottingFactorStartDate(Date clottingFactorStartDate) {
        _clottingFactorStartDate = clottingFactorStartDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setClottingFactorStartDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel, clottingFactorStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getNonFederalExpirationDate() {
        return _nonFederalExpirationDate;
    }

    @Override
    public void setNonFederalExpirationDate(Date nonFederalExpirationDate) {
        _nonFederalExpirationDate = nonFederalExpirationDate;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNonFederalExpirationDate",
                        Date.class);

                method.invoke(_itemMasterRemoteModel, nonFederalExpirationDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInternalNotes() {
        return _internalNotes;
    }

    @Override
    public void setInternalNotes(String internalNotes) {
        _internalNotes = internalNotes;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInternalNotes", String.class);

                method.invoke(_itemMasterRemoteModel, internalNotes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBaseCpiPrecision() {
        return _baseCpiPrecision;
    }

    @Override
    public void setBaseCpiPrecision(int baseCpiPrecision) {
        _baseCpiPrecision = baseCpiPrecision;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseCpiPrecision", int.class);

                method.invoke(_itemMasterRemoteModel, baseCpiPrecision);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBaselineAmpPrecision() {
        return _baselineAmpPrecision;
    }

    @Override
    public void setBaselineAmpPrecision(int baselineAmpPrecision) {
        _baselineAmpPrecision = baselineAmpPrecision;

        if (_itemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _itemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaselineAmpPrecision",
                        int.class);

                method.invoke(_itemMasterRemoteModel, baselineAmpPrecision);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getItemMasterRemoteModel() {
        return _itemMasterRemoteModel;
    }

    public void setItemMasterRemoteModel(BaseModel<?> itemMasterRemoteModel) {
        _itemMasterRemoteModel = itemMasterRemoteModel;
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

        Class<?> remoteModelClass = _itemMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_itemMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ItemMasterLocalServiceUtil.addItemMaster(this);
        } else {
            ItemMasterLocalServiceUtil.updateItemMaster(this);
        }
    }

    @Override
    public ItemMaster toEscapedModel() {
        return (ItemMaster) ProxyUtil.newProxyInstance(ItemMaster.class.getClassLoader(),
            new Class[] { ItemMaster.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ItemMasterClp clone = new ItemMasterClp();

        clone.setItemStatus(getItemStatus());
        clone.setItemDesc(getItemDesc());
        clone.setAuthorizedGenericStartDate(getAuthorizedGenericStartDate());
        clone.setAcquiredAmp(getAcquiredAmp());
        clone.setNewFormulationStartDate(getNewFormulationStartDate());
        clone.setMarketTerminationDate(getMarketTerminationDate());
        clone.setObraBamp(getObraBamp());
        clone.setModifiedDate(getModifiedDate());
        clone.setTherapeuticClass(getTherapeuticClass());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setAcquiredBamp(getAcquiredBamp());
        clone.setPediatricExclusiveEndDate(getPediatricExclusiveEndDate());
        clone.setSource(getSource());
        clone.setNewFormulation(getNewFormulation());
        clone.setDivestitureDate(getDivestitureDate());
        clone.setPrimaryUom(getPrimaryUom());
        clone.setNewFormulationEndDate(getNewFormulationEndDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setPackageSizeCode(getPackageSizeCode());
        clone.setSecondaryUom(getSecondaryUom());
        clone.setDiscontinuationDate(getDiscontinuationDate());
        clone.setPackageSizeIntroDate(getPackageSizeIntroDate());
        clone.setManufacturerId(getManufacturerId());
        clone.setItemEndDate(getItemEndDate());
        clone.setItemFamilyId(getItemFamilyId());
        clone.setStrength(getStrength());
        clone.setItemCategory(getItemCategory());
        clone.setUpps(getUpps());
        clone.setShelfLifeType(getShelfLifeType());
        clone.setPediatricExclusiveIndicator(getPediatricExclusiveIndicator());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setItemTypeIndication(getItemTypeIndication());
        clone.setAcquisitionDate(getAcquisitionDate());
        clone.setClottingFactorIndicator(getClottingFactorIndicator());
        clone.setForm(getForm());
        clone.setItemName(getItemName());
        clone.setShelfLife(getShelfLife());
        clone.setPediatricExclusiveStartDate(getPediatricExclusiveStartDate());
        clone.setFirstSaleDate(getFirstSaleDate());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setItemType(getItemType());
        clone.setItemId(getItemId());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setBaselineAmp(getBaselineAmp());
        clone.setDualPricingIndicator(getDualPricingIndicator());
        clone.setDosesPerUnit(getDosesPerUnit());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setAuthorizedGeneric(getAuthorizedGeneric());
        clone.setItemStartDate(getItemStartDate());
        clone.setNdc9(getNdc9());
        clone.setAuthorizedGenericEndDate(getAuthorizedGenericEndDate());
        clone.setItemNo(getItemNo());
        clone.setPackageSize(getPackageSize());
        clone.setNdc8(getNdc8());
        clone.setBaseCpi(getBaseCpi());
        clone.setLabelerCode(getLabelerCode());
        clone.setItemClass(getItemClass());
        clone.setClottingFactorEndDate(getClottingFactorEndDate());
        clone.setDra(getDra());
        clone.setBaseCpiPeriod(getBaseCpiPeriod());
        clone.setNewFormulationIndicator(getNewFormulationIndicator());
        clone.setLastLotExpirationDate(getLastLotExpirationDate());
        clone.setBatchId(getBatchId());
        clone.setItemCode(getItemCode());
        clone.setClottingFactorStartDate(getClottingFactorStartDate());
        clone.setNonFederalExpirationDate(getNonFederalExpirationDate());
        clone.setInternalNotes(getInternalNotes());
        clone.setBaseCpiPrecision(getBaseCpiPrecision());
        clone.setBaselineAmpPrecision(getBaselineAmpPrecision());

        return clone;
    }

    @Override
    public int compareTo(ItemMaster itemMaster) {
        int primaryKey = itemMaster.getPrimaryKey();

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

        if (!(obj instanceof ItemMasterClp)) {
            return false;
        }

        ItemMasterClp itemMaster = (ItemMasterClp) obj;

        int primaryKey = itemMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(143);

        sb.append("{itemStatus=");
        sb.append(getItemStatus());
        sb.append(", itemDesc=");
        sb.append(getItemDesc());
        sb.append(", authorizedGenericStartDate=");
        sb.append(getAuthorizedGenericStartDate());
        sb.append(", acquiredAmp=");
        sb.append(getAcquiredAmp());
        sb.append(", newFormulationStartDate=");
        sb.append(getNewFormulationStartDate());
        sb.append(", marketTerminationDate=");
        sb.append(getMarketTerminationDate());
        sb.append(", obraBamp=");
        sb.append(getObraBamp());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", therapeuticClass=");
        sb.append(getTherapeuticClass());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", acquiredBamp=");
        sb.append(getAcquiredBamp());
        sb.append(", pediatricExclusiveEndDate=");
        sb.append(getPediatricExclusiveEndDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", newFormulation=");
        sb.append(getNewFormulation());
        sb.append(", divestitureDate=");
        sb.append(getDivestitureDate());
        sb.append(", primaryUom=");
        sb.append(getPrimaryUom());
        sb.append(", newFormulationEndDate=");
        sb.append(getNewFormulationEndDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", packageSizeCode=");
        sb.append(getPackageSizeCode());
        sb.append(", secondaryUom=");
        sb.append(getSecondaryUom());
        sb.append(", discontinuationDate=");
        sb.append(getDiscontinuationDate());
        sb.append(", packageSizeIntroDate=");
        sb.append(getPackageSizeIntroDate());
        sb.append(", manufacturerId=");
        sb.append(getManufacturerId());
        sb.append(", itemEndDate=");
        sb.append(getItemEndDate());
        sb.append(", itemFamilyId=");
        sb.append(getItemFamilyId());
        sb.append(", strength=");
        sb.append(getStrength());
        sb.append(", itemCategory=");
        sb.append(getItemCategory());
        sb.append(", upps=");
        sb.append(getUpps());
        sb.append(", shelfLifeType=");
        sb.append(getShelfLifeType());
        sb.append(", pediatricExclusiveIndicator=");
        sb.append(getPediatricExclusiveIndicator());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", itemTypeIndication=");
        sb.append(getItemTypeIndication());
        sb.append(", acquisitionDate=");
        sb.append(getAcquisitionDate());
        sb.append(", clottingFactorIndicator=");
        sb.append(getClottingFactorIndicator());
        sb.append(", form=");
        sb.append(getForm());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", shelfLife=");
        sb.append(getShelfLife());
        sb.append(", pediatricExclusiveStartDate=");
        sb.append(getPediatricExclusiveStartDate());
        sb.append(", firstSaleDate=");
        sb.append(getFirstSaleDate());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", itemType=");
        sb.append(getItemType());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
        sb.append(", baselineAmp=");
        sb.append(getBaselineAmp());
        sb.append(", dualPricingIndicator=");
        sb.append(getDualPricingIndicator());
        sb.append(", dosesPerUnit=");
        sb.append(getDosesPerUnit());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", authorizedGeneric=");
        sb.append(getAuthorizedGeneric());
        sb.append(", itemStartDate=");
        sb.append(getItemStartDate());
        sb.append(", ndc9=");
        sb.append(getNdc9());
        sb.append(", authorizedGenericEndDate=");
        sb.append(getAuthorizedGenericEndDate());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", packageSize=");
        sb.append(getPackageSize());
        sb.append(", ndc8=");
        sb.append(getNdc8());
        sb.append(", baseCpi=");
        sb.append(getBaseCpi());
        sb.append(", labelerCode=");
        sb.append(getLabelerCode());
        sb.append(", itemClass=");
        sb.append(getItemClass());
        sb.append(", clottingFactorEndDate=");
        sb.append(getClottingFactorEndDate());
        sb.append(", dra=");
        sb.append(getDra());
        sb.append(", baseCpiPeriod=");
        sb.append(getBaseCpiPeriod());
        sb.append(", newFormulationIndicator=");
        sb.append(getNewFormulationIndicator());
        sb.append(", lastLotExpirationDate=");
        sb.append(getLastLotExpirationDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemCode=");
        sb.append(getItemCode());
        sb.append(", clottingFactorStartDate=");
        sb.append(getClottingFactorStartDate());
        sb.append(", nonFederalExpirationDate=");
        sb.append(getNonFederalExpirationDate());
        sb.append(", internalNotes=");
        sb.append(getInternalNotes());
        sb.append(", baseCpiPrecision=");
        sb.append(getBaseCpiPrecision());
        sb.append(", baselineAmpPrecision=");
        sb.append(getBaselineAmpPrecision());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(217);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ItemMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemStatus</column-name><column-value><![CDATA[");
        sb.append(getItemStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemDesc</column-name><column-value><![CDATA[");
        sb.append(getItemDesc());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorizedGenericStartDate</column-name><column-value><![CDATA[");
        sb.append(getAuthorizedGenericStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acquiredAmp</column-name><column-value><![CDATA[");
        sb.append(getAcquiredAmp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>newFormulationStartDate</column-name><column-value><![CDATA[");
        sb.append(getNewFormulationStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketTerminationDate</column-name><column-value><![CDATA[");
        sb.append(getMarketTerminationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>obraBamp</column-name><column-value><![CDATA[");
        sb.append(getObraBamp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>therapeuticClass</column-name><column-value><![CDATA[");
        sb.append(getTherapeuticClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acquiredBamp</column-name><column-value><![CDATA[");
        sb.append(getAcquiredBamp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pediatricExclusiveEndDate</column-name><column-value><![CDATA[");
        sb.append(getPediatricExclusiveEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>newFormulation</column-name><column-value><![CDATA[");
        sb.append(getNewFormulation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>divestitureDate</column-name><column-value><![CDATA[");
        sb.append(getDivestitureDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>primaryUom</column-name><column-value><![CDATA[");
        sb.append(getPrimaryUom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>newFormulationEndDate</column-name><column-value><![CDATA[");
        sb.append(getNewFormulationEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>packageSizeCode</column-name><column-value><![CDATA[");
        sb.append(getPackageSizeCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>secondaryUom</column-name><column-value><![CDATA[");
        sb.append(getSecondaryUom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discontinuationDate</column-name><column-value><![CDATA[");
        sb.append(getDiscontinuationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>packageSizeIntroDate</column-name><column-value><![CDATA[");
        sb.append(getPackageSizeIntroDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>manufacturerId</column-name><column-value><![CDATA[");
        sb.append(getManufacturerId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemEndDate</column-name><column-value><![CDATA[");
        sb.append(getItemEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemFamilyId</column-name><column-value><![CDATA[");
        sb.append(getItemFamilyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>strength</column-name><column-value><![CDATA[");
        sb.append(getStrength());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemCategory</column-name><column-value><![CDATA[");
        sb.append(getItemCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>upps</column-name><column-value><![CDATA[");
        sb.append(getUpps());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>shelfLifeType</column-name><column-value><![CDATA[");
        sb.append(getShelfLifeType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pediatricExclusiveIndicator</column-name><column-value><![CDATA[");
        sb.append(getPediatricExclusiveIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemTypeIndication</column-name><column-value><![CDATA[");
        sb.append(getItemTypeIndication());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acquisitionDate</column-name><column-value><![CDATA[");
        sb.append(getAcquisitionDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>clottingFactorIndicator</column-name><column-value><![CDATA[");
        sb.append(getClottingFactorIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>form</column-name><column-value><![CDATA[");
        sb.append(getForm());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>shelfLife</column-name><column-value><![CDATA[");
        sb.append(getShelfLife());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pediatricExclusiveStartDate</column-name><column-value><![CDATA[");
        sb.append(getPediatricExclusiveStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>firstSaleDate</column-name><column-value><![CDATA[");
        sb.append(getFirstSaleDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemType</column-name><column-value><![CDATA[");
        sb.append(getItemType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBrandMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baselineAmp</column-name><column-value><![CDATA[");
        sb.append(getBaselineAmp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dualPricingIndicator</column-name><column-value><![CDATA[");
        sb.append(getDualPricingIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dosesPerUnit</column-name><column-value><![CDATA[");
        sb.append(getDosesPerUnit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorizedGeneric</column-name><column-value><![CDATA[");
        sb.append(getAuthorizedGeneric());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemStartDate</column-name><column-value><![CDATA[");
        sb.append(getItemStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ndc9</column-name><column-value><![CDATA[");
        sb.append(getNdc9());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorizedGenericEndDate</column-name><column-value><![CDATA[");
        sb.append(getAuthorizedGenericEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>packageSize</column-name><column-value><![CDATA[");
        sb.append(getPackageSize());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ndc8</column-name><column-value><![CDATA[");
        sb.append(getNdc8());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseCpi</column-name><column-value><![CDATA[");
        sb.append(getBaseCpi());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>labelerCode</column-name><column-value><![CDATA[");
        sb.append(getLabelerCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemClass</column-name><column-value><![CDATA[");
        sb.append(getItemClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>clottingFactorEndDate</column-name><column-value><![CDATA[");
        sb.append(getClottingFactorEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dra</column-name><column-value><![CDATA[");
        sb.append(getDra());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseCpiPeriod</column-name><column-value><![CDATA[");
        sb.append(getBaseCpiPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>newFormulationIndicator</column-name><column-value><![CDATA[");
        sb.append(getNewFormulationIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastLotExpirationDate</column-name><column-value><![CDATA[");
        sb.append(getLastLotExpirationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemCode</column-name><column-value><![CDATA[");
        sb.append(getItemCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>clottingFactorStartDate</column-name><column-value><![CDATA[");
        sb.append(getClottingFactorStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nonFederalExpirationDate</column-name><column-value><![CDATA[");
        sb.append(getNonFederalExpirationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>internalNotes</column-name><column-value><![CDATA[");
        sb.append(getInternalNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseCpiPrecision</column-name><column-value><![CDATA[");
        sb.append(getBaseCpiPrecision());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baselineAmpPrecision</column-name><column-value><![CDATA[");
        sb.append(getBaselineAmpPrecision());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
