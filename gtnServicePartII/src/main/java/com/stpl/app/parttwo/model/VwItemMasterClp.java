package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.VwItemMasterLocalServiceUtil;

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


public class VwItemMasterClp extends BaseModelImpl<VwItemMaster>
    implements VwItemMaster {
    private String _itemStatus;
    private String _itemDesc;
    private String _acquiredAmp;
    private Date _authorizedGenericStartDate;
    private Date _newFormulationStartDate;
    private Date _marketTerminationDate;
    private String _obraBamp;
    private String _brand;
    private String _manufacturerNo;
    private Date _modifiedDate;
    private String _therapeuticClass;
    private String _organizationKey;
    private String _acquiredBamp;
    private Date _pediatricExclusiveEndDate;
    private String _source;
    private String _newFormulation;
    private String _addChgDelIndicator;
    private Date _divestitureDate;
    private String _shelfLife;
    private String _primaryUom;
    private Date _newFormulationEndDate;
    private String _modifiedBy;
    private String _packageSizeCode;
    private String _secondaryUom;
    private String _udc6;
    private String _udc5;
    private Date _discontinuationDate;
    private String _udc4;
    private String _udc1;
    private String _udc2;
    private Date _packageSizeIntroDate;
    private String _udc3;
    private Date _itemEndDate;
    private String _manufacturerId;
    private String _itemFamilyId;
    private String _strength;
    private String _itemCategory;
    private double _upps;
    private String _shelfLifeType;
    private String _pediatricExclusiveIndicator;
    private String _itemTypeIndication;
    private Date _acquisitionDate;
    private String _clottingFactorIndicator;
    private String _form;
    private String _itemName;
    private String _manufacturerName;
    private Date _pediatricExclusiveStartDate;
    private Date _firstSaleDate;
    private int _itemMasterSid;
    private String _itemType;
    private String _itemId;
    private String _baselineAmp;
    private String _dosesPerUnit;
    private String _dualPricingIndicator;
    private String _baseCpi;
    private Date _createdDate;
    private String _createdBy;
    private Date _itemStartDate;
    private String _authorizedGeneric;
    private String _ndc9;
    private Date _authorizedGenericEndDate;
    private String _itemNo;
    private String _packageSize;
    private String _ndc8;
    private String _itemClass;
    private String _labelerCode;
    private String _displayBrand;
    private Date _clottingFactorEndDate;
    private String _dra;
    private String _brandId;
    private Date _baseCpiPeriod;
    private String _newFormulationIndicator;
    private Date _lastLotExpirationDate;
    private String _batchId;
    private String _itemCode;
    private Date _clottingFactorStartDate;
    private Date _nonFederalExpirationDate;
    private BaseModel<?> _vwItemMasterRemoteModel;

    public VwItemMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwItemMaster.class;
    }

    @Override
    public String getModelClassName() {
        return VwItemMaster.class.getName();
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
        attributes.put("acquiredAmp", getAcquiredAmp());
        attributes.put("authorizedGenericStartDate",
            getAuthorizedGenericStartDate());
        attributes.put("newFormulationStartDate", getNewFormulationStartDate());
        attributes.put("marketTerminationDate", getMarketTerminationDate());
        attributes.put("obraBamp", getObraBamp());
        attributes.put("brand", getBrand());
        attributes.put("manufacturerNo", getManufacturerNo());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("therapeuticClass", getTherapeuticClass());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("acquiredBamp", getAcquiredBamp());
        attributes.put("pediatricExclusiveEndDate",
            getPediatricExclusiveEndDate());
        attributes.put("source", getSource());
        attributes.put("newFormulation", getNewFormulation());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("divestitureDate", getDivestitureDate());
        attributes.put("shelfLife", getShelfLife());
        attributes.put("primaryUom", getPrimaryUom());
        attributes.put("newFormulationEndDate", getNewFormulationEndDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("packageSizeCode", getPackageSizeCode());
        attributes.put("secondaryUom", getSecondaryUom());
        attributes.put("udc6", getUdc6());
        attributes.put("udc5", getUdc5());
        attributes.put("discontinuationDate", getDiscontinuationDate());
        attributes.put("udc4", getUdc4());
        attributes.put("udc1", getUdc1());
        attributes.put("udc2", getUdc2());
        attributes.put("packageSizeIntroDate", getPackageSizeIntroDate());
        attributes.put("udc3", getUdc3());
        attributes.put("itemEndDate", getItemEndDate());
        attributes.put("manufacturerId", getManufacturerId());
        attributes.put("itemFamilyId", getItemFamilyId());
        attributes.put("strength", getStrength());
        attributes.put("itemCategory", getItemCategory());
        attributes.put("upps", getUpps());
        attributes.put("shelfLifeType", getShelfLifeType());
        attributes.put("pediatricExclusiveIndicator",
            getPediatricExclusiveIndicator());
        attributes.put("itemTypeIndication", getItemTypeIndication());
        attributes.put("acquisitionDate", getAcquisitionDate());
        attributes.put("clottingFactorIndicator", getClottingFactorIndicator());
        attributes.put("form", getForm());
        attributes.put("itemName", getItemName());
        attributes.put("manufacturerName", getManufacturerName());
        attributes.put("pediatricExclusiveStartDate",
            getPediatricExclusiveStartDate());
        attributes.put("firstSaleDate", getFirstSaleDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemType", getItemType());
        attributes.put("itemId", getItemId());
        attributes.put("baselineAmp", getBaselineAmp());
        attributes.put("dosesPerUnit", getDosesPerUnit());
        attributes.put("dualPricingIndicator", getDualPricingIndicator());
        attributes.put("baseCpi", getBaseCpi());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemStartDate", getItemStartDate());
        attributes.put("authorizedGeneric", getAuthorizedGeneric());
        attributes.put("ndc9", getNdc9());
        attributes.put("authorizedGenericEndDate", getAuthorizedGenericEndDate());
        attributes.put("itemNo", getItemNo());
        attributes.put("packageSize", getPackageSize());
        attributes.put("ndc8", getNdc8());
        attributes.put("itemClass", getItemClass());
        attributes.put("labelerCode", getLabelerCode());
        attributes.put("displayBrand", getDisplayBrand());
        attributes.put("clottingFactorEndDate", getClottingFactorEndDate());
        attributes.put("dra", getDra());
        attributes.put("brandId", getBrandId());
        attributes.put("baseCpiPeriod", getBaseCpiPeriod());
        attributes.put("newFormulationIndicator", getNewFormulationIndicator());
        attributes.put("lastLotExpirationDate", getLastLotExpirationDate());
        attributes.put("batchId", getBatchId());
        attributes.put("itemCode", getItemCode());
        attributes.put("clottingFactorStartDate", getClottingFactorStartDate());
        attributes.put("nonFederalExpirationDate", getNonFederalExpirationDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemStatus = (String) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        String itemDesc = (String) attributes.get("itemDesc");

        if (itemDesc != null) {
            setItemDesc(itemDesc);
        }

        String acquiredAmp = (String) attributes.get("acquiredAmp");

        if (acquiredAmp != null) {
            setAcquiredAmp(acquiredAmp);
        }

        Date authorizedGenericStartDate = (Date) attributes.get(
                "authorizedGenericStartDate");

        if (authorizedGenericStartDate != null) {
            setAuthorizedGenericStartDate(authorizedGenericStartDate);
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

        String obraBamp = (String) attributes.get("obraBamp");

        if (obraBamp != null) {
            setObraBamp(obraBamp);
        }

        String brand = (String) attributes.get("brand");

        if (brand != null) {
            setBrand(brand);
        }

        String manufacturerNo = (String) attributes.get("manufacturerNo");

        if (manufacturerNo != null) {
            setManufacturerNo(manufacturerNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String therapeuticClass = (String) attributes.get("therapeuticClass");

        if (therapeuticClass != null) {
            setTherapeuticClass(therapeuticClass);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String acquiredBamp = (String) attributes.get("acquiredBamp");

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

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        Date divestitureDate = (Date) attributes.get("divestitureDate");

        if (divestitureDate != null) {
            setDivestitureDate(divestitureDate);
        }

        String shelfLife = (String) attributes.get("shelfLife");

        if (shelfLife != null) {
            setShelfLife(shelfLife);
        }

        String primaryUom = (String) attributes.get("primaryUom");

        if (primaryUom != null) {
            setPrimaryUom(primaryUom);
        }

        Date newFormulationEndDate = (Date) attributes.get(
                "newFormulationEndDate");

        if (newFormulationEndDate != null) {
            setNewFormulationEndDate(newFormulationEndDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String packageSizeCode = (String) attributes.get("packageSizeCode");

        if (packageSizeCode != null) {
            setPackageSizeCode(packageSizeCode);
        }

        String secondaryUom = (String) attributes.get("secondaryUom");

        if (secondaryUom != null) {
            setSecondaryUom(secondaryUom);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        Date discontinuationDate = (Date) attributes.get("discontinuationDate");

        if (discontinuationDate != null) {
            setDiscontinuationDate(discontinuationDate);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        Date packageSizeIntroDate = (Date) attributes.get(
                "packageSizeIntroDate");

        if (packageSizeIntroDate != null) {
            setPackageSizeIntroDate(packageSizeIntroDate);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        Date itemEndDate = (Date) attributes.get("itemEndDate");

        if (itemEndDate != null) {
            setItemEndDate(itemEndDate);
        }

        String manufacturerId = (String) attributes.get("manufacturerId");

        if (manufacturerId != null) {
            setManufacturerId(manufacturerId);
        }

        String itemFamilyId = (String) attributes.get("itemFamilyId");

        if (itemFamilyId != null) {
            setItemFamilyId(itemFamilyId);
        }

        String strength = (String) attributes.get("strength");

        if (strength != null) {
            setStrength(strength);
        }

        String itemCategory = (String) attributes.get("itemCategory");

        if (itemCategory != null) {
            setItemCategory(itemCategory);
        }

        Double upps = (Double) attributes.get("upps");

        if (upps != null) {
            setUpps(upps);
        }

        String shelfLifeType = (String) attributes.get("shelfLifeType");

        if (shelfLifeType != null) {
            setShelfLifeType(shelfLifeType);
        }

        String pediatricExclusiveIndicator = (String) attributes.get(
                "pediatricExclusiveIndicator");

        if (pediatricExclusiveIndicator != null) {
            setPediatricExclusiveIndicator(pediatricExclusiveIndicator);
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

        String form = (String) attributes.get("form");

        if (form != null) {
            setForm(form);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String manufacturerName = (String) attributes.get("manufacturerName");

        if (manufacturerName != null) {
            setManufacturerName(manufacturerName);
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

        String itemType = (String) attributes.get("itemType");

        if (itemType != null) {
            setItemType(itemType);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String baselineAmp = (String) attributes.get("baselineAmp");

        if (baselineAmp != null) {
            setBaselineAmp(baselineAmp);
        }

        String dosesPerUnit = (String) attributes.get("dosesPerUnit");

        if (dosesPerUnit != null) {
            setDosesPerUnit(dosesPerUnit);
        }

        String dualPricingIndicator = (String) attributes.get(
                "dualPricingIndicator");

        if (dualPricingIndicator != null) {
            setDualPricingIndicator(dualPricingIndicator);
        }

        String baseCpi = (String) attributes.get("baseCpi");

        if (baseCpi != null) {
            setBaseCpi(baseCpi);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date itemStartDate = (Date) attributes.get("itemStartDate");

        if (itemStartDate != null) {
            setItemStartDate(itemStartDate);
        }

        String authorizedGeneric = (String) attributes.get("authorizedGeneric");

        if (authorizedGeneric != null) {
            setAuthorizedGeneric(authorizedGeneric);
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

        String itemClass = (String) attributes.get("itemClass");

        if (itemClass != null) {
            setItemClass(itemClass);
        }

        String labelerCode = (String) attributes.get("labelerCode");

        if (labelerCode != null) {
            setLabelerCode(labelerCode);
        }

        String displayBrand = (String) attributes.get("displayBrand");

        if (displayBrand != null) {
            setDisplayBrand(displayBrand);
        }

        Date clottingFactorEndDate = (Date) attributes.get(
                "clottingFactorEndDate");

        if (clottingFactorEndDate != null) {
            setClottingFactorEndDate(clottingFactorEndDate);
        }

        String dra = (String) attributes.get("dra");

        if (dra != null) {
            setDra(dra);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
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
    }

    @Override
    public String getItemStatus() {
        return _itemStatus;
    }

    @Override
    public void setItemStatus(String itemStatus) {
        _itemStatus = itemStatus;

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStatus", String.class);

                method.invoke(_vwItemMasterRemoteModel, itemStatus);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemDesc", String.class);

                method.invoke(_vwItemMasterRemoteModel, itemDesc);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAcquiredAmp", String.class);

                method.invoke(_vwItemMasterRemoteModel, acquiredAmp);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorizedGenericStartDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel,
                    authorizedGenericStartDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNewFormulationStartDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel, newFormulationStartDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketTerminationDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel, marketTerminationDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setObraBamp", String.class);

                method.invoke(_vwItemMasterRemoteModel, obraBamp);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrand", String.class);

                method.invoke(_vwItemMasterRemoteModel, brand);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setManufacturerNo",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, manufacturerNo);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_vwItemMasterRemoteModel, modifiedDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setTherapeuticClass",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, therapeuticClass);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, organizationKey);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAcquiredBamp", String.class);

                method.invoke(_vwItemMasterRemoteModel, acquiredBamp);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPediatricExclusiveEndDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel,
                    pediatricExclusiveEndDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwItemMasterRemoteModel, source);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNewFormulation",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, newFormulation);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, addChgDelIndicator);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDivestitureDate", Date.class);

                method.invoke(_vwItemMasterRemoteModel, divestitureDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setShelfLife", String.class);

                method.invoke(_vwItemMasterRemoteModel, shelfLife);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPrimaryUom", String.class);

                method.invoke(_vwItemMasterRemoteModel, primaryUom);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNewFormulationEndDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel, newFormulationEndDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_vwItemMasterRemoteModel, modifiedBy);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPackageSizeCode",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, packageSizeCode);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSecondaryUom", String.class);

                method.invoke(_vwItemMasterRemoteModel, secondaryUom);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc6", String.class);

                method.invoke(_vwItemMasterRemoteModel, udc6);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc5", String.class);

                method.invoke(_vwItemMasterRemoteModel, udc5);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDiscontinuationDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel, discontinuationDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc4", String.class);

                method.invoke(_vwItemMasterRemoteModel, udc4);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc1", String.class);

                method.invoke(_vwItemMasterRemoteModel, udc1);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc2", String.class);

                method.invoke(_vwItemMasterRemoteModel, udc2);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPackageSizeIntroDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel, packageSizeIntroDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc3", String.class);

                method.invoke(_vwItemMasterRemoteModel, udc3);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemEndDate", Date.class);

                method.invoke(_vwItemMasterRemoteModel, itemEndDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setManufacturerId",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, manufacturerId);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemFamilyId", String.class);

                method.invoke(_vwItemMasterRemoteModel, itemFamilyId);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setStrength", String.class);

                method.invoke(_vwItemMasterRemoteModel, strength);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemCategory", String.class);

                method.invoke(_vwItemMasterRemoteModel, itemCategory);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUpps", double.class);

                method.invoke(_vwItemMasterRemoteModel, upps);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setShelfLifeType", String.class);

                method.invoke(_vwItemMasterRemoteModel, shelfLifeType);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPediatricExclusiveIndicator",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel,
                    pediatricExclusiveIndicator);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemTypeIndication",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, itemTypeIndication);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAcquisitionDate", Date.class);

                method.invoke(_vwItemMasterRemoteModel, acquisitionDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setClottingFactorIndicator",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, clottingFactorIndicator);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setForm", String.class);

                method.invoke(_vwItemMasterRemoteModel, form);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_vwItemMasterRemoteModel, itemName);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setManufacturerName",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, manufacturerName);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPediatricExclusiveStartDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel,
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFirstSaleDate", Date.class);

                method.invoke(_vwItemMasterRemoteModel, firstSaleDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_vwItemMasterRemoteModel, itemMasterSid);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemType", String.class);

                method.invoke(_vwItemMasterRemoteModel, itemType);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_vwItemMasterRemoteModel, itemId);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaselineAmp", String.class);

                method.invoke(_vwItemMasterRemoteModel, baselineAmp);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDosesPerUnit", String.class);

                method.invoke(_vwItemMasterRemoteModel, dosesPerUnit);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDualPricingIndicator",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, dualPricingIndicator);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseCpi", String.class);

                method.invoke(_vwItemMasterRemoteModel, baseCpi);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwItemMasterRemoteModel, createdDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_vwItemMasterRemoteModel, createdBy);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStartDate", Date.class);

                method.invoke(_vwItemMasterRemoteModel, itemStartDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorizedGeneric",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, authorizedGeneric);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc9", String.class);

                method.invoke(_vwItemMasterRemoteModel, ndc9);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAuthorizedGenericEndDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel, authorizedGenericEndDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_vwItemMasterRemoteModel, itemNo);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPackageSize", String.class);

                method.invoke(_vwItemMasterRemoteModel, packageSize);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc8", String.class);

                method.invoke(_vwItemMasterRemoteModel, ndc8);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemClass", String.class);

                method.invoke(_vwItemMasterRemoteModel, itemClass);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLabelerCode", String.class);

                method.invoke(_vwItemMasterRemoteModel, labelerCode);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDisplayBrand", String.class);

                method.invoke(_vwItemMasterRemoteModel, displayBrand);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setClottingFactorEndDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel, clottingFactorEndDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDra", String.class);

                method.invoke(_vwItemMasterRemoteModel, dra);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_vwItemMasterRemoteModel, brandId);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBaseCpiPeriod", Date.class);

                method.invoke(_vwItemMasterRemoteModel, baseCpiPeriod);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNewFormulationIndicator",
                        String.class);

                method.invoke(_vwItemMasterRemoteModel, newFormulationIndicator);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastLotExpirationDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel, lastLotExpirationDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwItemMasterRemoteModel, batchId);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemCode", String.class);

                method.invoke(_vwItemMasterRemoteModel, itemCode);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setClottingFactorStartDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel, clottingFactorStartDate);
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

        if (_vwItemMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNonFederalExpirationDate",
                        Date.class);

                method.invoke(_vwItemMasterRemoteModel, nonFederalExpirationDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwItemMasterRemoteModel() {
        return _vwItemMasterRemoteModel;
    }

    public void setVwItemMasterRemoteModel(BaseModel<?> vwItemMasterRemoteModel) {
        _vwItemMasterRemoteModel = vwItemMasterRemoteModel;
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

        Class<?> remoteModelClass = _vwItemMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwItemMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwItemMasterLocalServiceUtil.addVwItemMaster(this);
        } else {
            VwItemMasterLocalServiceUtil.updateVwItemMaster(this);
        }
    }

    @Override
    public VwItemMaster toEscapedModel() {
        return (VwItemMaster) ProxyUtil.newProxyInstance(VwItemMaster.class.getClassLoader(),
            new Class[] { VwItemMaster.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwItemMasterClp clone = new VwItemMasterClp();

        clone.setItemStatus(getItemStatus());
        clone.setItemDesc(getItemDesc());
        clone.setAcquiredAmp(getAcquiredAmp());
        clone.setAuthorizedGenericStartDate(getAuthorizedGenericStartDate());
        clone.setNewFormulationStartDate(getNewFormulationStartDate());
        clone.setMarketTerminationDate(getMarketTerminationDate());
        clone.setObraBamp(getObraBamp());
        clone.setBrand(getBrand());
        clone.setManufacturerNo(getManufacturerNo());
        clone.setModifiedDate(getModifiedDate());
        clone.setTherapeuticClass(getTherapeuticClass());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setAcquiredBamp(getAcquiredBamp());
        clone.setPediatricExclusiveEndDate(getPediatricExclusiveEndDate());
        clone.setSource(getSource());
        clone.setNewFormulation(getNewFormulation());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setDivestitureDate(getDivestitureDate());
        clone.setShelfLife(getShelfLife());
        clone.setPrimaryUom(getPrimaryUom());
        clone.setNewFormulationEndDate(getNewFormulationEndDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setPackageSizeCode(getPackageSizeCode());
        clone.setSecondaryUom(getSecondaryUom());
        clone.setUdc6(getUdc6());
        clone.setUdc5(getUdc5());
        clone.setDiscontinuationDate(getDiscontinuationDate());
        clone.setUdc4(getUdc4());
        clone.setUdc1(getUdc1());
        clone.setUdc2(getUdc2());
        clone.setPackageSizeIntroDate(getPackageSizeIntroDate());
        clone.setUdc3(getUdc3());
        clone.setItemEndDate(getItemEndDate());
        clone.setManufacturerId(getManufacturerId());
        clone.setItemFamilyId(getItemFamilyId());
        clone.setStrength(getStrength());
        clone.setItemCategory(getItemCategory());
        clone.setUpps(getUpps());
        clone.setShelfLifeType(getShelfLifeType());
        clone.setPediatricExclusiveIndicator(getPediatricExclusiveIndicator());
        clone.setItemTypeIndication(getItemTypeIndication());
        clone.setAcquisitionDate(getAcquisitionDate());
        clone.setClottingFactorIndicator(getClottingFactorIndicator());
        clone.setForm(getForm());
        clone.setItemName(getItemName());
        clone.setManufacturerName(getManufacturerName());
        clone.setPediatricExclusiveStartDate(getPediatricExclusiveStartDate());
        clone.setFirstSaleDate(getFirstSaleDate());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setItemType(getItemType());
        clone.setItemId(getItemId());
        clone.setBaselineAmp(getBaselineAmp());
        clone.setDosesPerUnit(getDosesPerUnit());
        clone.setDualPricingIndicator(getDualPricingIndicator());
        clone.setBaseCpi(getBaseCpi());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setItemStartDate(getItemStartDate());
        clone.setAuthorizedGeneric(getAuthorizedGeneric());
        clone.setNdc9(getNdc9());
        clone.setAuthorizedGenericEndDate(getAuthorizedGenericEndDate());
        clone.setItemNo(getItemNo());
        clone.setPackageSize(getPackageSize());
        clone.setNdc8(getNdc8());
        clone.setItemClass(getItemClass());
        clone.setLabelerCode(getLabelerCode());
        clone.setDisplayBrand(getDisplayBrand());
        clone.setClottingFactorEndDate(getClottingFactorEndDate());
        clone.setDra(getDra());
        clone.setBrandId(getBrandId());
        clone.setBaseCpiPeriod(getBaseCpiPeriod());
        clone.setNewFormulationIndicator(getNewFormulationIndicator());
        clone.setLastLotExpirationDate(getLastLotExpirationDate());
        clone.setBatchId(getBatchId());
        clone.setItemCode(getItemCode());
        clone.setClottingFactorStartDate(getClottingFactorStartDate());
        clone.setNonFederalExpirationDate(getNonFederalExpirationDate());

        return clone;
    }

    @Override
    public int compareTo(VwItemMaster vwItemMaster) {
        int primaryKey = vwItemMaster.getPrimaryKey();

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

        if (!(obj instanceof VwItemMasterClp)) {
            return false;
        }

        VwItemMasterClp vwItemMaster = (VwItemMasterClp) obj;

        int primaryKey = vwItemMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(155);

        sb.append("{itemStatus=");
        sb.append(getItemStatus());
        sb.append(", itemDesc=");
        sb.append(getItemDesc());
        sb.append(", acquiredAmp=");
        sb.append(getAcquiredAmp());
        sb.append(", authorizedGenericStartDate=");
        sb.append(getAuthorizedGenericStartDate());
        sb.append(", newFormulationStartDate=");
        sb.append(getNewFormulationStartDate());
        sb.append(", marketTerminationDate=");
        sb.append(getMarketTerminationDate());
        sb.append(", obraBamp=");
        sb.append(getObraBamp());
        sb.append(", brand=");
        sb.append(getBrand());
        sb.append(", manufacturerNo=");
        sb.append(getManufacturerNo());
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
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", divestitureDate=");
        sb.append(getDivestitureDate());
        sb.append(", shelfLife=");
        sb.append(getShelfLife());
        sb.append(", primaryUom=");
        sb.append(getPrimaryUom());
        sb.append(", newFormulationEndDate=");
        sb.append(getNewFormulationEndDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", packageSizeCode=");
        sb.append(getPackageSizeCode());
        sb.append(", secondaryUom=");
        sb.append(getSecondaryUom());
        sb.append(", udc6=");
        sb.append(getUdc6());
        sb.append(", udc5=");
        sb.append(getUdc5());
        sb.append(", discontinuationDate=");
        sb.append(getDiscontinuationDate());
        sb.append(", udc4=");
        sb.append(getUdc4());
        sb.append(", udc1=");
        sb.append(getUdc1());
        sb.append(", udc2=");
        sb.append(getUdc2());
        sb.append(", packageSizeIntroDate=");
        sb.append(getPackageSizeIntroDate());
        sb.append(", udc3=");
        sb.append(getUdc3());
        sb.append(", itemEndDate=");
        sb.append(getItemEndDate());
        sb.append(", manufacturerId=");
        sb.append(getManufacturerId());
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
        sb.append(", manufacturerName=");
        sb.append(getManufacturerName());
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
        sb.append(", baselineAmp=");
        sb.append(getBaselineAmp());
        sb.append(", dosesPerUnit=");
        sb.append(getDosesPerUnit());
        sb.append(", dualPricingIndicator=");
        sb.append(getDualPricingIndicator());
        sb.append(", baseCpi=");
        sb.append(getBaseCpi());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", itemStartDate=");
        sb.append(getItemStartDate());
        sb.append(", authorizedGeneric=");
        sb.append(getAuthorizedGeneric());
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
        sb.append(", itemClass=");
        sb.append(getItemClass());
        sb.append(", labelerCode=");
        sb.append(getLabelerCode());
        sb.append(", displayBrand=");
        sb.append(getDisplayBrand());
        sb.append(", clottingFactorEndDate=");
        sb.append(getClottingFactorEndDate());
        sb.append(", dra=");
        sb.append(getDra());
        sb.append(", brandId=");
        sb.append(getBrandId());
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
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(235);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.VwItemMaster");
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
            "<column><column-name>acquiredAmp</column-name><column-value><![CDATA[");
        sb.append(getAcquiredAmp());
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
            "<column><column-name>marketTerminationDate</column-name><column-value><![CDATA[");
        sb.append(getMarketTerminationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>obraBamp</column-name><column-value><![CDATA[");
        sb.append(getObraBamp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brand</column-name><column-value><![CDATA[");
        sb.append(getBrand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>manufacturerNo</column-name><column-value><![CDATA[");
        sb.append(getManufacturerNo());
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
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>divestitureDate</column-name><column-value><![CDATA[");
        sb.append(getDivestitureDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>shelfLife</column-name><column-value><![CDATA[");
        sb.append(getShelfLife());
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
            "<column><column-name>packageSizeCode</column-name><column-value><![CDATA[");
        sb.append(getPackageSizeCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>secondaryUom</column-name><column-value><![CDATA[");
        sb.append(getSecondaryUom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc6</column-name><column-value><![CDATA[");
        sb.append(getUdc6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc5</column-name><column-value><![CDATA[");
        sb.append(getUdc5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discontinuationDate</column-name><column-value><![CDATA[");
        sb.append(getDiscontinuationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc4</column-name><column-value><![CDATA[");
        sb.append(getUdc4());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc1</column-name><column-value><![CDATA[");
        sb.append(getUdc1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc2</column-name><column-value><![CDATA[");
        sb.append(getUdc2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>packageSizeIntroDate</column-name><column-value><![CDATA[");
        sb.append(getPackageSizeIntroDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc3</column-name><column-value><![CDATA[");
        sb.append(getUdc3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemEndDate</column-name><column-value><![CDATA[");
        sb.append(getItemEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>manufacturerId</column-name><column-value><![CDATA[");
        sb.append(getManufacturerId());
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
            "<column><column-name>manufacturerName</column-name><column-value><![CDATA[");
        sb.append(getManufacturerName());
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
            "<column><column-name>baselineAmp</column-name><column-value><![CDATA[");
        sb.append(getBaselineAmp());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dosesPerUnit</column-name><column-value><![CDATA[");
        sb.append(getDosesPerUnit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dualPricingIndicator</column-name><column-value><![CDATA[");
        sb.append(getDualPricingIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>baseCpi</column-name><column-value><![CDATA[");
        sb.append(getBaseCpi());
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
            "<column><column-name>itemStartDate</column-name><column-value><![CDATA[");
        sb.append(getItemStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorizedGeneric</column-name><column-value><![CDATA[");
        sb.append(getAuthorizedGeneric());
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
            "<column><column-name>itemClass</column-name><column-value><![CDATA[");
        sb.append(getItemClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>labelerCode</column-name><column-value><![CDATA[");
        sb.append(getLabelerCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>displayBrand</column-name><column-value><![CDATA[");
        sb.append(getDisplayBrand());
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
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
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

        sb.append("</model>");

        return sb.toString();
    }
}
