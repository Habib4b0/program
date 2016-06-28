package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.PsDetailsLocalServiceUtil;

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


public class PsDetailsClp extends BaseModelImpl<PsDetails> implements PsDetails {
    private int _nepFormula;
    private double _price;
    private int _itemMasterSid;
    private int _resetType;
    private Date _priceProtectionStartDate;
    private Date _resetDate;
    private double _basePrice;
    private Date _itemPsAttachedDate;
    private String _brandMasterSid;
    private int _status;
    private Date _modifiedDate;
    private int _itemPsAttachedStatus;
    private Date _revisionDate;
    private double _priceTolerance;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private int _psDetailsSid;
    private int _psModelSid;
    private double _suggestedPrice;
    private String _netPriceTypeFormula;
    private int _priceProtectionPriceType;
    private int _modifiedBy;
    private String _inboundStatus;
    private double _contractPrice;
    private int _ifpModelSid;
    private int _priceToleranceType;
    private double _maxIncrementalChange;
    private int _itemPricingQualifierSid;
    private Date _contractPriceEndDate;
    private double _nep;
    private Date _contractPriceStartDate;
    private int _priceToleranceFrequency;
    private Date _priceProtectionEndDate;
    private int _priceProtectionStatus;
    private boolean _recordLockStatus;
    private int _resetEligible;
    private String _batchId;
    private int _priceToleranceInterval;
    private int _netPriceType;
    private double _priceRevision;
    private int _resetFrequency;
    private int _resetInterval;
    private int _basePriceType;
    private double _basePriceEntry;
    private Date _basePriceDate;
    private int _netBasePrice;
    private int _basePriceDdlb;
    private int _subsequentPeriodPriceType;
    private int _netSubsequentPeriodPrice;
    private int _netSubsequentPriceFormulaId;
    private int _resetPriceType;
    private int _netResetPriceType;
    private int _netResetPriceFormulaId;
    private int _netBasePriceFormulaId;
    private BaseModel<?> _psDetailsRemoteModel;

    public PsDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PsDetails.class;
    }

    @Override
    public String getModelClassName() {
        return PsDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _psDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setPsDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _psDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("nepFormula", getNepFormula());
        attributes.put("price", getPrice());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("resetType", getResetType());
        attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
        attributes.put("resetDate", getResetDate());
        attributes.put("basePrice", getBasePrice());
        attributes.put("itemPsAttachedDate", getItemPsAttachedDate());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("status", getStatus());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("itemPsAttachedStatus", getItemPsAttachedStatus());
        attributes.put("revisionDate", getRevisionDate());
        attributes.put("priceTolerance", getPriceTolerance());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("psDetailsSid", getPsDetailsSid());
        attributes.put("psModelSid", getPsModelSid());
        attributes.put("suggestedPrice", getSuggestedPrice());
        attributes.put("netPriceTypeFormula", getNetPriceTypeFormula());
        attributes.put("priceProtectionPriceType", getPriceProtectionPriceType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("contractPrice", getContractPrice());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("priceToleranceType", getPriceToleranceType());
        attributes.put("maxIncrementalChange", getMaxIncrementalChange());
        attributes.put("itemPricingQualifierSid", getItemPricingQualifierSid());
        attributes.put("contractPriceEndDate", getContractPriceEndDate());
        attributes.put("nep", getNep());
        attributes.put("contractPriceStartDate", getContractPriceStartDate());
        attributes.put("priceToleranceFrequency", getPriceToleranceFrequency());
        attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
        attributes.put("priceProtectionStatus", getPriceProtectionStatus());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("resetEligible", getResetEligible());
        attributes.put("batchId", getBatchId());
        attributes.put("priceToleranceInterval", getPriceToleranceInterval());
        attributes.put("netPriceType", getNetPriceType());
        attributes.put("priceRevision", getPriceRevision());
        attributes.put("resetFrequency", getResetFrequency());
        attributes.put("resetInterval", getResetInterval());
        attributes.put("basePriceType", getBasePriceType());
        attributes.put("basePriceEntry", getBasePriceEntry());
        attributes.put("basePriceDate", getBasePriceDate());
        attributes.put("netBasePrice", getNetBasePrice());
        attributes.put("basePriceDdlb", getBasePriceDdlb());
        attributes.put("subsequentPeriodPriceType",
            getSubsequentPeriodPriceType());
        attributes.put("netSubsequentPeriodPrice", getNetSubsequentPeriodPrice());
        attributes.put("netSubsequentPriceFormulaId",
            getNetSubsequentPriceFormulaId());
        attributes.put("resetPriceType", getResetPriceType());
        attributes.put("netResetPriceType", getNetResetPriceType());
        attributes.put("netResetPriceFormulaId", getNetResetPriceFormulaId());
        attributes.put("netBasePriceFormulaId", getNetBasePriceFormulaId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer nepFormula = (Integer) attributes.get("nepFormula");

        if (nepFormula != null) {
            setNepFormula(nepFormula);
        }

        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer resetType = (Integer) attributes.get("resetType");

        if (resetType != null) {
            setResetType(resetType);
        }

        Date priceProtectionStartDate = (Date) attributes.get(
                "priceProtectionStartDate");

        if (priceProtectionStartDate != null) {
            setPriceProtectionStartDate(priceProtectionStartDate);
        }

        Date resetDate = (Date) attributes.get("resetDate");

        if (resetDate != null) {
            setResetDate(resetDate);
        }

        Double basePrice = (Double) attributes.get("basePrice");

        if (basePrice != null) {
            setBasePrice(basePrice);
        }

        Date itemPsAttachedDate = (Date) attributes.get("itemPsAttachedDate");

        if (itemPsAttachedDate != null) {
            setItemPsAttachedDate(itemPsAttachedDate);
        }

        String brandMasterSid = (String) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        Integer status = (Integer) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer itemPsAttachedStatus = (Integer) attributes.get(
                "itemPsAttachedStatus");

        if (itemPsAttachedStatus != null) {
            setItemPsAttachedStatus(itemPsAttachedStatus);
        }

        Date revisionDate = (Date) attributes.get("revisionDate");

        if (revisionDate != null) {
            setRevisionDate(revisionDate);
        }

        Double priceTolerance = (Double) attributes.get("priceTolerance");

        if (priceTolerance != null) {
            setPriceTolerance(priceTolerance);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer psDetailsSid = (Integer) attributes.get("psDetailsSid");

        if (psDetailsSid != null) {
            setPsDetailsSid(psDetailsSid);
        }

        Integer psModelSid = (Integer) attributes.get("psModelSid");

        if (psModelSid != null) {
            setPsModelSid(psModelSid);
        }

        Double suggestedPrice = (Double) attributes.get("suggestedPrice");

        if (suggestedPrice != null) {
            setSuggestedPrice(suggestedPrice);
        }

        String netPriceTypeFormula = (String) attributes.get(
                "netPriceTypeFormula");

        if (netPriceTypeFormula != null) {
            setNetPriceTypeFormula(netPriceTypeFormula);
        }

        Integer priceProtectionPriceType = (Integer) attributes.get(
                "priceProtectionPriceType");

        if (priceProtectionPriceType != null) {
            setPriceProtectionPriceType(priceProtectionPriceType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Double contractPrice = (Double) attributes.get("contractPrice");

        if (contractPrice != null) {
            setContractPrice(contractPrice);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        Integer priceToleranceType = (Integer) attributes.get(
                "priceToleranceType");

        if (priceToleranceType != null) {
            setPriceToleranceType(priceToleranceType);
        }

        Double maxIncrementalChange = (Double) attributes.get(
                "maxIncrementalChange");

        if (maxIncrementalChange != null) {
            setMaxIncrementalChange(maxIncrementalChange);
        }

        Integer itemPricingQualifierSid = (Integer) attributes.get(
                "itemPricingQualifierSid");

        if (itemPricingQualifierSid != null) {
            setItemPricingQualifierSid(itemPricingQualifierSid);
        }

        Date contractPriceEndDate = (Date) attributes.get(
                "contractPriceEndDate");

        if (contractPriceEndDate != null) {
            setContractPriceEndDate(contractPriceEndDate);
        }

        Double nep = (Double) attributes.get("nep");

        if (nep != null) {
            setNep(nep);
        }

        Date contractPriceStartDate = (Date) attributes.get(
                "contractPriceStartDate");

        if (contractPriceStartDate != null) {
            setContractPriceStartDate(contractPriceStartDate);
        }

        Integer priceToleranceFrequency = (Integer) attributes.get(
                "priceToleranceFrequency");

        if (priceToleranceFrequency != null) {
            setPriceToleranceFrequency(priceToleranceFrequency);
        }

        Date priceProtectionEndDate = (Date) attributes.get(
                "priceProtectionEndDate");

        if (priceProtectionEndDate != null) {
            setPriceProtectionEndDate(priceProtectionEndDate);
        }

        Integer priceProtectionStatus = (Integer) attributes.get(
                "priceProtectionStatus");

        if (priceProtectionStatus != null) {
            setPriceProtectionStatus(priceProtectionStatus);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Integer resetEligible = (Integer) attributes.get("resetEligible");

        if (resetEligible != null) {
            setResetEligible(resetEligible);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer priceToleranceInterval = (Integer) attributes.get(
                "priceToleranceInterval");

        if (priceToleranceInterval != null) {
            setPriceToleranceInterval(priceToleranceInterval);
        }

        Integer netPriceType = (Integer) attributes.get("netPriceType");

        if (netPriceType != null) {
            setNetPriceType(netPriceType);
        }

        Double priceRevision = (Double) attributes.get("priceRevision");

        if (priceRevision != null) {
            setPriceRevision(priceRevision);
        }

        Integer resetFrequency = (Integer) attributes.get("resetFrequency");

        if (resetFrequency != null) {
            setResetFrequency(resetFrequency);
        }

        Integer resetInterval = (Integer) attributes.get("resetInterval");

        if (resetInterval != null) {
            setResetInterval(resetInterval);
        }

        Integer basePriceType = (Integer) attributes.get("basePriceType");

        if (basePriceType != null) {
            setBasePriceType(basePriceType);
        }

        Double basePriceEntry = (Double) attributes.get("basePriceEntry");

        if (basePriceEntry != null) {
            setBasePriceEntry(basePriceEntry);
        }

        Date basePriceDate = (Date) attributes.get("basePriceDate");

        if (basePriceDate != null) {
            setBasePriceDate(basePriceDate);
        }

        Integer netBasePrice = (Integer) attributes.get("netBasePrice");

        if (netBasePrice != null) {
            setNetBasePrice(netBasePrice);
        }

        Integer basePriceDdlb = (Integer) attributes.get("basePriceDdlb");

        if (basePriceDdlb != null) {
            setBasePriceDdlb(basePriceDdlb);
        }

        Integer subsequentPeriodPriceType = (Integer) attributes.get(
                "subsequentPeriodPriceType");

        if (subsequentPeriodPriceType != null) {
            setSubsequentPeriodPriceType(subsequentPeriodPriceType);
        }

        Integer netSubsequentPeriodPrice = (Integer) attributes.get(
                "netSubsequentPeriodPrice");

        if (netSubsequentPeriodPrice != null) {
            setNetSubsequentPeriodPrice(netSubsequentPeriodPrice);
        }

        Integer netSubsequentPriceFormulaId = (Integer) attributes.get(
                "netSubsequentPriceFormulaId");

        if (netSubsequentPriceFormulaId != null) {
            setNetSubsequentPriceFormulaId(netSubsequentPriceFormulaId);
        }

        Integer resetPriceType = (Integer) attributes.get("resetPriceType");

        if (resetPriceType != null) {
            setResetPriceType(resetPriceType);
        }

        Integer netResetPriceType = (Integer) attributes.get(
                "netResetPriceType");

        if (netResetPriceType != null) {
            setNetResetPriceType(netResetPriceType);
        }

        Integer netResetPriceFormulaId = (Integer) attributes.get(
                "netResetPriceFormulaId");

        if (netResetPriceFormulaId != null) {
            setNetResetPriceFormulaId(netResetPriceFormulaId);
        }

        Integer netBasePriceFormulaId = (Integer) attributes.get(
                "netBasePriceFormulaId");

        if (netBasePriceFormulaId != null) {
            setNetBasePriceFormulaId(netBasePriceFormulaId);
        }
    }

    @Override
    public int getNepFormula() {
        return _nepFormula;
    }

    @Override
    public void setNepFormula(int nepFormula) {
        _nepFormula = nepFormula;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNepFormula", int.class);

                method.invoke(_psDetailsRemoteModel, nepFormula);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPrice() {
        return _price;
    }

    @Override
    public void setPrice(double price) {
        _price = price;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", double.class);

                method.invoke(_psDetailsRemoteModel, price);
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

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_psDetailsRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getResetType() {
        return _resetType;
    }

    @Override
    public void setResetType(int resetType) {
        _resetType = resetType;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetType", int.class);

                method.invoke(_psDetailsRemoteModel, resetType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriceProtectionStartDate() {
        return _priceProtectionStartDate;
    }

    @Override
    public void setPriceProtectionStartDate(Date priceProtectionStartDate) {
        _priceProtectionStartDate = priceProtectionStartDate;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionStartDate",
                        Date.class);

                method.invoke(_psDetailsRemoteModel, priceProtectionStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getResetDate() {
        return _resetDate;
    }

    @Override
    public void setResetDate(Date resetDate) {
        _resetDate = resetDate;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetDate", Date.class);

                method.invoke(_psDetailsRemoteModel, resetDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getBasePrice() {
        return _basePrice;
    }

    @Override
    public void setBasePrice(double basePrice) {
        _basePrice = basePrice;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePrice", double.class);

                method.invoke(_psDetailsRemoteModel, basePrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemPsAttachedDate() {
        return _itemPsAttachedDate;
    }

    @Override
    public void setItemPsAttachedDate(Date itemPsAttachedDate) {
        _itemPsAttachedDate = itemPsAttachedDate;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPsAttachedDate",
                        Date.class);

                method.invoke(_psDetailsRemoteModel, itemPsAttachedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandMasterSid() {
        return _brandMasterSid;
    }

    @Override
    public void setBrandMasterSid(String brandMasterSid) {
        _brandMasterSid = brandMasterSid;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid",
                        String.class);

                method.invoke(_psDetailsRemoteModel, brandMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getStatus() {
        return _status;
    }

    @Override
    public void setStatus(int status) {
        _status = status;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setStatus", int.class);

                method.invoke(_psDetailsRemoteModel, status);
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

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_psDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemPsAttachedStatus() {
        return _itemPsAttachedStatus;
    }

    @Override
    public void setItemPsAttachedStatus(int itemPsAttachedStatus) {
        _itemPsAttachedStatus = itemPsAttachedStatus;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPsAttachedStatus",
                        int.class);

                method.invoke(_psDetailsRemoteModel, itemPsAttachedStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRevisionDate() {
        return _revisionDate;
    }

    @Override
    public void setRevisionDate(Date revisionDate) {
        _revisionDate = revisionDate;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRevisionDate", Date.class);

                method.invoke(_psDetailsRemoteModel, revisionDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPriceTolerance() {
        return _priceTolerance;
    }

    @Override
    public void setPriceTolerance(double priceTolerance) {
        _priceTolerance = priceTolerance;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceTolerance",
                        double.class);

                method.invoke(_psDetailsRemoteModel, priceTolerance);
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

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_psDetailsRemoteModel, createdDate);
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

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_psDetailsRemoteModel, createdBy);
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

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_psDetailsRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsDetailsSid() {
        return _psDetailsSid;
    }

    @Override
    public void setPsDetailsSid(int psDetailsSid) {
        _psDetailsSid = psDetailsSid;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsSid", int.class);

                method.invoke(_psDetailsRemoteModel, psDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsModelSid() {
        return _psModelSid;
    }

    @Override
    public void setPsModelSid(int psModelSid) {
        _psModelSid = psModelSid;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsModelSid", int.class);

                method.invoke(_psDetailsRemoteModel, psModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getSuggestedPrice() {
        return _suggestedPrice;
    }

    @Override
    public void setSuggestedPrice(double suggestedPrice) {
        _suggestedPrice = suggestedPrice;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSuggestedPrice",
                        double.class);

                method.invoke(_psDetailsRemoteModel, suggestedPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetPriceTypeFormula() {
        return _netPriceTypeFormula;
    }

    @Override
    public void setNetPriceTypeFormula(String netPriceTypeFormula) {
        _netPriceTypeFormula = netPriceTypeFormula;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetPriceTypeFormula",
                        String.class);

                method.invoke(_psDetailsRemoteModel, netPriceTypeFormula);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPriceProtectionPriceType() {
        return _priceProtectionPriceType;
    }

    @Override
    public void setPriceProtectionPriceType(int priceProtectionPriceType) {
        _priceProtectionPriceType = priceProtectionPriceType;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionPriceType",
                        int.class);

                method.invoke(_psDetailsRemoteModel, priceProtectionPriceType);
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

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_psDetailsRemoteModel, modifiedBy);
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

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_psDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getContractPrice() {
        return _contractPrice;
    }

    @Override
    public void setContractPrice(double contractPrice) {
        _contractPrice = contractPrice;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPrice", double.class);

                method.invoke(_psDetailsRemoteModel, contractPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpModelSid() {
        return _ifpModelSid;
    }

    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _ifpModelSid = ifpModelSid;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpModelSid", int.class);

                method.invoke(_psDetailsRemoteModel, ifpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPriceToleranceType() {
        return _priceToleranceType;
    }

    @Override
    public void setPriceToleranceType(int priceToleranceType) {
        _priceToleranceType = priceToleranceType;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceToleranceType",
                        int.class);

                method.invoke(_psDetailsRemoteModel, priceToleranceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getMaxIncrementalChange() {
        return _maxIncrementalChange;
    }

    @Override
    public void setMaxIncrementalChange(double maxIncrementalChange) {
        _maxIncrementalChange = maxIncrementalChange;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setMaxIncrementalChange",
                        double.class);

                method.invoke(_psDetailsRemoteModel, maxIncrementalChange);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemPricingQualifierSid() {
        return _itemPricingQualifierSid;
    }

    @Override
    public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
        _itemPricingQualifierSid = itemPricingQualifierSid;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPricingQualifierSid",
                        int.class);

                method.invoke(_psDetailsRemoteModel, itemPricingQualifierSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getContractPriceEndDate() {
        return _contractPriceEndDate;
    }

    @Override
    public void setContractPriceEndDate(Date contractPriceEndDate) {
        _contractPriceEndDate = contractPriceEndDate;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPriceEndDate",
                        Date.class);

                method.invoke(_psDetailsRemoteModel, contractPriceEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNep() {
        return _nep;
    }

    @Override
    public void setNep(double nep) {
        _nep = nep;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNep", double.class);

                method.invoke(_psDetailsRemoteModel, nep);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getContractPriceStartDate() {
        return _contractPriceStartDate;
    }

    @Override
    public void setContractPriceStartDate(Date contractPriceStartDate) {
        _contractPriceStartDate = contractPriceStartDate;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPriceStartDate",
                        Date.class);

                method.invoke(_psDetailsRemoteModel, contractPriceStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPriceToleranceFrequency() {
        return _priceToleranceFrequency;
    }

    @Override
    public void setPriceToleranceFrequency(int priceToleranceFrequency) {
        _priceToleranceFrequency = priceToleranceFrequency;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceToleranceFrequency",
                        int.class);

                method.invoke(_psDetailsRemoteModel, priceToleranceFrequency);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriceProtectionEndDate() {
        return _priceProtectionEndDate;
    }

    @Override
    public void setPriceProtectionEndDate(Date priceProtectionEndDate) {
        _priceProtectionEndDate = priceProtectionEndDate;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionEndDate",
                        Date.class);

                method.invoke(_psDetailsRemoteModel, priceProtectionEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPriceProtectionStatus() {
        return _priceProtectionStatus;
    }

    @Override
    public void setPriceProtectionStatus(int priceProtectionStatus) {
        _priceProtectionStatus = priceProtectionStatus;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionStatus",
                        int.class);

                method.invoke(_psDetailsRemoteModel, priceProtectionStatus);
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

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_psDetailsRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getResetEligible() {
        return _resetEligible;
    }

    @Override
    public void setResetEligible(int resetEligible) {
        _resetEligible = resetEligible;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetEligible", int.class);

                method.invoke(_psDetailsRemoteModel, resetEligible);
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

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_psDetailsRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPriceToleranceInterval() {
        return _priceToleranceInterval;
    }

    @Override
    public void setPriceToleranceInterval(int priceToleranceInterval) {
        _priceToleranceInterval = priceToleranceInterval;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceToleranceInterval",
                        int.class);

                method.invoke(_psDetailsRemoteModel, priceToleranceInterval);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetPriceType() {
        return _netPriceType;
    }

    @Override
    public void setNetPriceType(int netPriceType) {
        _netPriceType = netPriceType;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetPriceType", int.class);

                method.invoke(_psDetailsRemoteModel, netPriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPriceRevision() {
        return _priceRevision;
    }

    @Override
    public void setPriceRevision(double priceRevision) {
        _priceRevision = priceRevision;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceRevision", double.class);

                method.invoke(_psDetailsRemoteModel, priceRevision);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getResetFrequency() {
        return _resetFrequency;
    }

    @Override
    public void setResetFrequency(int resetFrequency) {
        _resetFrequency = resetFrequency;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetFrequency", int.class);

                method.invoke(_psDetailsRemoteModel, resetFrequency);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getResetInterval() {
        return _resetInterval;
    }

    @Override
    public void setResetInterval(int resetInterval) {
        _resetInterval = resetInterval;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetInterval", int.class);

                method.invoke(_psDetailsRemoteModel, resetInterval);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBasePriceType() {
        return _basePriceType;
    }

    @Override
    public void setBasePriceType(int basePriceType) {
        _basePriceType = basePriceType;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceType", int.class);

                method.invoke(_psDetailsRemoteModel, basePriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getBasePriceEntry() {
        return _basePriceEntry;
    }

    @Override
    public void setBasePriceEntry(double basePriceEntry) {
        _basePriceEntry = basePriceEntry;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceEntry",
                        double.class);

                method.invoke(_psDetailsRemoteModel, basePriceEntry);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getBasePriceDate() {
        return _basePriceDate;
    }

    @Override
    public void setBasePriceDate(Date basePriceDate) {
        _basePriceDate = basePriceDate;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceDate", Date.class);

                method.invoke(_psDetailsRemoteModel, basePriceDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetBasePrice() {
        return _netBasePrice;
    }

    @Override
    public void setNetBasePrice(int netBasePrice) {
        _netBasePrice = netBasePrice;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetBasePrice", int.class);

                method.invoke(_psDetailsRemoteModel, netBasePrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getBasePriceDdlb() {
        return _basePriceDdlb;
    }

    @Override
    public void setBasePriceDdlb(int basePriceDdlb) {
        _basePriceDdlb = basePriceDdlb;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceDdlb", int.class);

                method.invoke(_psDetailsRemoteModel, basePriceDdlb);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSubsequentPeriodPriceType() {
        return _subsequentPeriodPriceType;
    }

    @Override
    public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType) {
        _subsequentPeriodPriceType = subsequentPeriodPriceType;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSubsequentPeriodPriceType",
                        int.class);

                method.invoke(_psDetailsRemoteModel, subsequentPeriodPriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetSubsequentPeriodPrice() {
        return _netSubsequentPeriodPrice;
    }

    @Override
    public void setNetSubsequentPeriodPrice(int netSubsequentPeriodPrice) {
        _netSubsequentPeriodPrice = netSubsequentPeriodPrice;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSubsequentPeriodPrice",
                        int.class);

                method.invoke(_psDetailsRemoteModel, netSubsequentPeriodPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetSubsequentPriceFormulaId() {
        return _netSubsequentPriceFormulaId;
    }

    @Override
    public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId) {
        _netSubsequentPriceFormulaId = netSubsequentPriceFormulaId;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSubsequentPriceFormulaId",
                        int.class);

                method.invoke(_psDetailsRemoteModel, netSubsequentPriceFormulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getResetPriceType() {
        return _resetPriceType;
    }

    @Override
    public void setResetPriceType(int resetPriceType) {
        _resetPriceType = resetPriceType;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetPriceType", int.class);

                method.invoke(_psDetailsRemoteModel, resetPriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetResetPriceType() {
        return _netResetPriceType;
    }

    @Override
    public void setNetResetPriceType(int netResetPriceType) {
        _netResetPriceType = netResetPriceType;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetResetPriceType",
                        int.class);

                method.invoke(_psDetailsRemoteModel, netResetPriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetResetPriceFormulaId() {
        return _netResetPriceFormulaId;
    }

    @Override
    public void setNetResetPriceFormulaId(int netResetPriceFormulaId) {
        _netResetPriceFormulaId = netResetPriceFormulaId;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetResetPriceFormulaId",
                        int.class);

                method.invoke(_psDetailsRemoteModel, netResetPriceFormulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetBasePriceFormulaId() {
        return _netBasePriceFormulaId;
    }

    @Override
    public void setNetBasePriceFormulaId(int netBasePriceFormulaId) {
        _netBasePriceFormulaId = netBasePriceFormulaId;

        if (_psDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetBasePriceFormulaId",
                        int.class);

                method.invoke(_psDetailsRemoteModel, netBasePriceFormulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPsDetailsRemoteModel() {
        return _psDetailsRemoteModel;
    }

    public void setPsDetailsRemoteModel(BaseModel<?> psDetailsRemoteModel) {
        _psDetailsRemoteModel = psDetailsRemoteModel;
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

        Class<?> remoteModelClass = _psDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_psDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PsDetailsLocalServiceUtil.addPsDetails(this);
        } else {
            PsDetailsLocalServiceUtil.updatePsDetails(this);
        }
    }

    @Override
    public PsDetails toEscapedModel() {
        return (PsDetails) ProxyUtil.newProxyInstance(PsDetails.class.getClassLoader(),
            new Class[] { PsDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PsDetailsClp clone = new PsDetailsClp();

        clone.setNepFormula(getNepFormula());
        clone.setPrice(getPrice());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setResetType(getResetType());
        clone.setPriceProtectionStartDate(getPriceProtectionStartDate());
        clone.setResetDate(getResetDate());
        clone.setBasePrice(getBasePrice());
        clone.setItemPsAttachedDate(getItemPsAttachedDate());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setStatus(getStatus());
        clone.setModifiedDate(getModifiedDate());
        clone.setItemPsAttachedStatus(getItemPsAttachedStatus());
        clone.setRevisionDate(getRevisionDate());
        clone.setPriceTolerance(getPriceTolerance());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setPsDetailsSid(getPsDetailsSid());
        clone.setPsModelSid(getPsModelSid());
        clone.setSuggestedPrice(getSuggestedPrice());
        clone.setNetPriceTypeFormula(getNetPriceTypeFormula());
        clone.setPriceProtectionPriceType(getPriceProtectionPriceType());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setContractPrice(getContractPrice());
        clone.setIfpModelSid(getIfpModelSid());
        clone.setPriceToleranceType(getPriceToleranceType());
        clone.setMaxIncrementalChange(getMaxIncrementalChange());
        clone.setItemPricingQualifierSid(getItemPricingQualifierSid());
        clone.setContractPriceEndDate(getContractPriceEndDate());
        clone.setNep(getNep());
        clone.setContractPriceStartDate(getContractPriceStartDate());
        clone.setPriceToleranceFrequency(getPriceToleranceFrequency());
        clone.setPriceProtectionEndDate(getPriceProtectionEndDate());
        clone.setPriceProtectionStatus(getPriceProtectionStatus());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setResetEligible(getResetEligible());
        clone.setBatchId(getBatchId());
        clone.setPriceToleranceInterval(getPriceToleranceInterval());
        clone.setNetPriceType(getNetPriceType());
        clone.setPriceRevision(getPriceRevision());
        clone.setResetFrequency(getResetFrequency());
        clone.setResetInterval(getResetInterval());
        clone.setBasePriceType(getBasePriceType());
        clone.setBasePriceEntry(getBasePriceEntry());
        clone.setBasePriceDate(getBasePriceDate());
        clone.setNetBasePrice(getNetBasePrice());
        clone.setBasePriceDdlb(getBasePriceDdlb());
        clone.setSubsequentPeriodPriceType(getSubsequentPeriodPriceType());
        clone.setNetSubsequentPeriodPrice(getNetSubsequentPeriodPrice());
        clone.setNetSubsequentPriceFormulaId(getNetSubsequentPriceFormulaId());
        clone.setResetPriceType(getResetPriceType());
        clone.setNetResetPriceType(getNetResetPriceType());
        clone.setNetResetPriceFormulaId(getNetResetPriceFormulaId());
        clone.setNetBasePriceFormulaId(getNetBasePriceFormulaId());

        return clone;
    }

    @Override
    public int compareTo(PsDetails psDetails) {
        int primaryKey = psDetails.getPrimaryKey();

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

        if (!(obj instanceof PsDetailsClp)) {
            return false;
        }

        PsDetailsClp psDetails = (PsDetailsClp) obj;

        int primaryKey = psDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(111);

        sb.append("{nepFormula=");
        sb.append(getNepFormula());
        sb.append(", price=");
        sb.append(getPrice());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", resetType=");
        sb.append(getResetType());
        sb.append(", priceProtectionStartDate=");
        sb.append(getPriceProtectionStartDate());
        sb.append(", resetDate=");
        sb.append(getResetDate());
        sb.append(", basePrice=");
        sb.append(getBasePrice());
        sb.append(", itemPsAttachedDate=");
        sb.append(getItemPsAttachedDate());
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", itemPsAttachedStatus=");
        sb.append(getItemPsAttachedStatus());
        sb.append(", revisionDate=");
        sb.append(getRevisionDate());
        sb.append(", priceTolerance=");
        sb.append(getPriceTolerance());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", psDetailsSid=");
        sb.append(getPsDetailsSid());
        sb.append(", psModelSid=");
        sb.append(getPsModelSid());
        sb.append(", suggestedPrice=");
        sb.append(getSuggestedPrice());
        sb.append(", netPriceTypeFormula=");
        sb.append(getNetPriceTypeFormula());
        sb.append(", priceProtectionPriceType=");
        sb.append(getPriceProtectionPriceType());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", contractPrice=");
        sb.append(getContractPrice());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append(", priceToleranceType=");
        sb.append(getPriceToleranceType());
        sb.append(", maxIncrementalChange=");
        sb.append(getMaxIncrementalChange());
        sb.append(", itemPricingQualifierSid=");
        sb.append(getItemPricingQualifierSid());
        sb.append(", contractPriceEndDate=");
        sb.append(getContractPriceEndDate());
        sb.append(", nep=");
        sb.append(getNep());
        sb.append(", contractPriceStartDate=");
        sb.append(getContractPriceStartDate());
        sb.append(", priceToleranceFrequency=");
        sb.append(getPriceToleranceFrequency());
        sb.append(", priceProtectionEndDate=");
        sb.append(getPriceProtectionEndDate());
        sb.append(", priceProtectionStatus=");
        sb.append(getPriceProtectionStatus());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", resetEligible=");
        sb.append(getResetEligible());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", priceToleranceInterval=");
        sb.append(getPriceToleranceInterval());
        sb.append(", netPriceType=");
        sb.append(getNetPriceType());
        sb.append(", priceRevision=");
        sb.append(getPriceRevision());
        sb.append(", resetFrequency=");
        sb.append(getResetFrequency());
        sb.append(", resetInterval=");
        sb.append(getResetInterval());
        sb.append(", basePriceType=");
        sb.append(getBasePriceType());
        sb.append(", basePriceEntry=");
        sb.append(getBasePriceEntry());
        sb.append(", basePriceDate=");
        sb.append(getBasePriceDate());
        sb.append(", netBasePrice=");
        sb.append(getNetBasePrice());
        sb.append(", basePriceDdlb=");
        sb.append(getBasePriceDdlb());
        sb.append(", subsequentPeriodPriceType=");
        sb.append(getSubsequentPeriodPriceType());
        sb.append(", netSubsequentPeriodPrice=");
        sb.append(getNetSubsequentPeriodPrice());
        sb.append(", netSubsequentPriceFormulaId=");
        sb.append(getNetSubsequentPriceFormulaId());
        sb.append(", resetPriceType=");
        sb.append(getResetPriceType());
        sb.append(", netResetPriceType=");
        sb.append(getNetResetPriceType());
        sb.append(", netResetPriceFormulaId=");
        sb.append(getNetResetPriceFormulaId());
        sb.append(", netBasePriceFormulaId=");
        sb.append(getNetBasePriceFormulaId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(169);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.PsDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>nepFormula</column-name><column-value><![CDATA[");
        sb.append(getNepFormula());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetType</column-name><column-value><![CDATA[");
        sb.append(getResetType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetDate</column-name><column-value><![CDATA[");
        sb.append(getResetDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>basePrice</column-name><column-value><![CDATA[");
        sb.append(getBasePrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPsAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getItemPsAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBrandMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPsAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getItemPsAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>revisionDate</column-name><column-value><![CDATA[");
        sb.append(getRevisionDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceTolerance</column-name><column-value><![CDATA[");
        sb.append(getPriceTolerance());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psModelSid</column-name><column-value><![CDATA[");
        sb.append(getPsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>suggestedPrice</column-name><column-value><![CDATA[");
        sb.append(getSuggestedPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netPriceTypeFormula</column-name><column-value><![CDATA[");
        sb.append(getNetPriceTypeFormula());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionPriceType</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionPriceType());
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
            "<column><column-name>contractPrice</column-name><column-value><![CDATA[");
        sb.append(getContractPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceToleranceType</column-name><column-value><![CDATA[");
        sb.append(getPriceToleranceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>maxIncrementalChange</column-name><column-value><![CDATA[");
        sb.append(getMaxIncrementalChange());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPricingQualifierSid</column-name><column-value><![CDATA[");
        sb.append(getItemPricingQualifierSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPriceEndDate</column-name><column-value><![CDATA[");
        sb.append(getContractPriceEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nep</column-name><column-value><![CDATA[");
        sb.append(getNep());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPriceStartDate</column-name><column-value><![CDATA[");
        sb.append(getContractPriceStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceToleranceFrequency</column-name><column-value><![CDATA[");
        sb.append(getPriceToleranceFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionEndDate</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionStatus</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetEligible</column-name><column-value><![CDATA[");
        sb.append(getResetEligible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceToleranceInterval</column-name><column-value><![CDATA[");
        sb.append(getPriceToleranceInterval());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netPriceType</column-name><column-value><![CDATA[");
        sb.append(getNetPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceRevision</column-name><column-value><![CDATA[");
        sb.append(getPriceRevision());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetFrequency</column-name><column-value><![CDATA[");
        sb.append(getResetFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetInterval</column-name><column-value><![CDATA[");
        sb.append(getResetInterval());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>basePriceType</column-name><column-value><![CDATA[");
        sb.append(getBasePriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>basePriceEntry</column-name><column-value><![CDATA[");
        sb.append(getBasePriceEntry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>basePriceDate</column-name><column-value><![CDATA[");
        sb.append(getBasePriceDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netBasePrice</column-name><column-value><![CDATA[");
        sb.append(getNetBasePrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>basePriceDdlb</column-name><column-value><![CDATA[");
        sb.append(getBasePriceDdlb());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subsequentPeriodPriceType</column-name><column-value><![CDATA[");
        sb.append(getSubsequentPeriodPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSubsequentPeriodPrice</column-name><column-value><![CDATA[");
        sb.append(getNetSubsequentPeriodPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSubsequentPriceFormulaId</column-name><column-value><![CDATA[");
        sb.append(getNetSubsequentPriceFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetPriceType</column-name><column-value><![CDATA[");
        sb.append(getResetPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netResetPriceType</column-name><column-value><![CDATA[");
        sb.append(getNetResetPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netResetPriceFormulaId</column-name><column-value><![CDATA[");
        sb.append(getNetResetPriceFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netBasePriceFormulaId</column-name><column-value><![CDATA[");
        sb.append(getNetBasePriceFormulaId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
