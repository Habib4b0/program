package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.PsContractDetailsLocalServiceUtil;

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


public class PsContractDetailsClp extends BaseModelImpl<PsContractDetails>
    implements PsContractDetails {
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
    private BaseModel<?> _psContractDetailsRemoteModel;

    public PsContractDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PsContractDetails.class;
    }

    @Override
    public String getModelClassName() {
        return PsContractDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _psContractDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setPsContractDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _psContractDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("price", getPrice());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
        attributes.put("basePrice", getBasePrice());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("revisionDate", getRevisionDate());
        attributes.put("priceTolerance", getPriceTolerance());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("suggestedPrice", getSuggestedPrice());
        attributes.put("psContractAttachedDate", getPsContractAttachedDate());
        attributes.put("psContractDetailsSid", getPsContractDetailsSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("contractPrice", getContractPrice());
        attributes.put("priceToleranceType", getPriceToleranceType());
        attributes.put("itemPricingQualifierSid", getItemPricingQualifierSid());
        attributes.put("contractPriceEndDate", getContractPriceEndDate());
        attributes.put("priceToleranceFrequency", getPriceToleranceFrequency());
        attributes.put("contractPriceStartDate", getContractPriceStartDate());
        attributes.put("psContractSid", getPsContractSid());
        attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
        attributes.put("psContractAttachedStatus", getPsContractAttachedStatus());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("batchId", getBatchId());
        attributes.put("priceToleranceInterval", getPriceToleranceInterval());
        attributes.put("priceRevision", getPriceRevision());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("nep", getNep());
        attributes.put("priceProtectionStatus", getPriceProtectionStatus());
        attributes.put("priceProtectionPriceType", getPriceProtectionPriceType());
        attributes.put("nepFormula", getNepFormula());
        attributes.put("maxIncrementalChange", getMaxIncrementalChange());
        attributes.put("resetEligible", getResetEligible());
        attributes.put("resetType", getResetType());
        attributes.put("resetDate", getResetDate());
        attributes.put("resetInterval", getResetInterval());
        attributes.put("resetFrequency", getResetFrequency());
        attributes.put("netPriceType", getNetPriceType());
        attributes.put("netPriceTypeFormula", getNetPriceTypeFormula());
        attributes.put("basePriceType", getBasePriceType());
        attributes.put("basePriceEntry", getBasePriceEntry());
        attributes.put("basePriceDate", getBasePriceDate());
        attributes.put("basePriceDdlb", getBasePriceDdlb());
        attributes.put("netBasePrice", getNetBasePrice());
        attributes.put("netBasePriceFormulaId", getNetBasePriceFormulaId());
        attributes.put("subsequentPeriodPriceType",
            getSubsequentPeriodPriceType());
        attributes.put("netSubsequentPeriodPrice", getNetSubsequentPeriodPrice());
        attributes.put("netSubsequentPriceFormulaId",
            getNetSubsequentPriceFormulaId());
        attributes.put("resetPriceType", getResetPriceType());
        attributes.put("netResetPriceType", getNetResetPriceType());
        attributes.put("netResetPriceFormulaId", getNetResetPriceFormulaId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date priceProtectionStartDate = (Date) attributes.get(
                "priceProtectionStartDate");

        if (priceProtectionStartDate != null) {
            setPriceProtectionStartDate(priceProtectionStartDate);
        }

        Double basePrice = (Double) attributes.get("basePrice");

        if (basePrice != null) {
            setBasePrice(basePrice);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
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

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Double suggestedPrice = (Double) attributes.get("suggestedPrice");

        if (suggestedPrice != null) {
            setSuggestedPrice(suggestedPrice);
        }

        Date psContractAttachedDate = (Date) attributes.get(
                "psContractAttachedDate");

        if (psContractAttachedDate != null) {
            setPsContractAttachedDate(psContractAttachedDate);
        }

        Integer psContractDetailsSid = (Integer) attributes.get(
                "psContractDetailsSid");

        if (psContractDetailsSid != null) {
            setPsContractDetailsSid(psContractDetailsSid);
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

        Integer priceToleranceType = (Integer) attributes.get(
                "priceToleranceType");

        if (priceToleranceType != null) {
            setPriceToleranceType(priceToleranceType);
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

        Integer priceToleranceFrequency = (Integer) attributes.get(
                "priceToleranceFrequency");

        if (priceToleranceFrequency != null) {
            setPriceToleranceFrequency(priceToleranceFrequency);
        }

        Date contractPriceStartDate = (Date) attributes.get(
                "contractPriceStartDate");

        if (contractPriceStartDate != null) {
            setContractPriceStartDate(contractPriceStartDate);
        }

        Integer psContractSid = (Integer) attributes.get("psContractSid");

        if (psContractSid != null) {
            setPsContractSid(psContractSid);
        }

        Date priceProtectionEndDate = (Date) attributes.get(
                "priceProtectionEndDate");

        if (priceProtectionEndDate != null) {
            setPriceProtectionEndDate(priceProtectionEndDate);
        }

        Integer psContractAttachedStatus = (Integer) attributes.get(
                "psContractAttachedStatus");

        if (psContractAttachedStatus != null) {
            setPsContractAttachedStatus(psContractAttachedStatus);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
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

        Double priceRevision = (Double) attributes.get("priceRevision");

        if (priceRevision != null) {
            setPriceRevision(priceRevision);
        }

        String brandMasterSid = (String) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        Double nep = (Double) attributes.get("nep");

        if (nep != null) {
            setNep(nep);
        }

        Integer priceProtectionStatus = (Integer) attributes.get(
                "priceProtectionStatus");

        if (priceProtectionStatus != null) {
            setPriceProtectionStatus(priceProtectionStatus);
        }

        Integer priceProtectionPriceType = (Integer) attributes.get(
                "priceProtectionPriceType");

        if (priceProtectionPriceType != null) {
            setPriceProtectionPriceType(priceProtectionPriceType);
        }

        Integer nepFormula = (Integer) attributes.get("nepFormula");

        if (nepFormula != null) {
            setNepFormula(nepFormula);
        }

        Double maxIncrementalChange = (Double) attributes.get(
                "maxIncrementalChange");

        if (maxIncrementalChange != null) {
            setMaxIncrementalChange(maxIncrementalChange);
        }

        Integer resetEligible = (Integer) attributes.get("resetEligible");

        if (resetEligible != null) {
            setResetEligible(resetEligible);
        }

        Integer resetType = (Integer) attributes.get("resetType");

        if (resetType != null) {
            setResetType(resetType);
        }

        Date resetDate = (Date) attributes.get("resetDate");

        if (resetDate != null) {
            setResetDate(resetDate);
        }

        Integer resetInterval = (Integer) attributes.get("resetInterval");

        if (resetInterval != null) {
            setResetInterval(resetInterval);
        }

        Integer resetFrequency = (Integer) attributes.get("resetFrequency");

        if (resetFrequency != null) {
            setResetFrequency(resetFrequency);
        }

        Integer netPriceType = (Integer) attributes.get("netPriceType");

        if (netPriceType != null) {
            setNetPriceType(netPriceType);
        }

        String netPriceTypeFormula = (String) attributes.get(
                "netPriceTypeFormula");

        if (netPriceTypeFormula != null) {
            setNetPriceTypeFormula(netPriceTypeFormula);
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

        Integer basePriceDdlb = (Integer) attributes.get("basePriceDdlb");

        if (basePriceDdlb != null) {
            setBasePriceDdlb(basePriceDdlb);
        }

        Integer netBasePrice = (Integer) attributes.get("netBasePrice");

        if (netBasePrice != null) {
            setNetBasePrice(netBasePrice);
        }

        Integer netBasePriceFormulaId = (Integer) attributes.get(
                "netBasePriceFormulaId");

        if (netBasePriceFormulaId != null) {
            setNetBasePriceFormulaId(netBasePriceFormulaId);
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
    }

    @Override
    public double getPrice() {
        return _price;
    }

    @Override
    public void setPrice(double price) {
        _price = price;

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", double.class);

                method.invoke(_psContractDetailsRemoteModel, price);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_psContractDetailsRemoteModel, itemMasterSid);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionStartDate",
                        Date.class);

                method.invoke(_psContractDetailsRemoteModel,
                    priceProtectionStartDate);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePrice", double.class);

                method.invoke(_psContractDetailsRemoteModel, basePrice);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_psContractDetailsRemoteModel, modifiedDate);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRevisionDate", Date.class);

                method.invoke(_psContractDetailsRemoteModel, revisionDate);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceTolerance",
                        double.class);

                method.invoke(_psContractDetailsRemoteModel, priceTolerance);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_psContractDetailsRemoteModel, createdDate);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_psContractDetailsRemoteModel, source);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_psContractDetailsRemoteModel, createdBy);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSuggestedPrice",
                        double.class);

                method.invoke(_psContractDetailsRemoteModel, suggestedPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsContractAttachedDate() {
        return _psContractAttachedDate;
    }

    @Override
    public void setPsContractAttachedDate(Date psContractAttachedDate) {
        _psContractAttachedDate = psContractAttachedDate;

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsContractAttachedDate",
                        Date.class);

                method.invoke(_psContractDetailsRemoteModel,
                    psContractAttachedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsContractDetailsSid() {
        return _psContractDetailsSid;
    }

    @Override
    public void setPsContractDetailsSid(int psContractDetailsSid) {
        _psContractDetailsSid = psContractDetailsSid;

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsContractDetailsSid",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel,
                    psContractDetailsSid);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_psContractDetailsRemoteModel, modifiedBy);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_psContractDetailsRemoteModel, inboundStatus);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPrice", double.class);

                method.invoke(_psContractDetailsRemoteModel, contractPrice);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceToleranceType",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel, priceToleranceType);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPricingQualifierSid",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel,
                    itemPricingQualifierSid);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPriceEndDate",
                        Date.class);

                method.invoke(_psContractDetailsRemoteModel,
                    contractPriceEndDate);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceToleranceFrequency",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel,
                    priceToleranceFrequency);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractPriceStartDate",
                        Date.class);

                method.invoke(_psContractDetailsRemoteModel,
                    contractPriceStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsContractSid() {
        return _psContractSid;
    }

    @Override
    public void setPsContractSid(int psContractSid) {
        _psContractSid = psContractSid;

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsContractSid", int.class);

                method.invoke(_psContractDetailsRemoteModel, psContractSid);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionEndDate",
                        Date.class);

                method.invoke(_psContractDetailsRemoteModel,
                    priceProtectionEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsContractAttachedStatus() {
        return _psContractAttachedStatus;
    }

    @Override
    public void setPsContractAttachedStatus(int psContractAttachedStatus) {
        _psContractAttachedStatus = psContractAttachedStatus;

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsContractAttachedStatus",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel,
                    psContractAttachedStatus);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_psContractDetailsRemoteModel, recordLockStatus);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_psContractDetailsRemoteModel, batchId);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceToleranceInterval",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel,
                    priceToleranceInterval);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceRevision", double.class);

                method.invoke(_psContractDetailsRemoteModel, priceRevision);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid",
                        String.class);

                method.invoke(_psContractDetailsRemoteModel, brandMasterSid);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNep", double.class);

                method.invoke(_psContractDetailsRemoteModel, nep);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionStatus",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel,
                    priceProtectionStatus);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionPriceType",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel,
                    priceProtectionPriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNepFormula() {
        return _nepFormula;
    }

    @Override
    public void setNepFormula(int nepFormula) {
        _nepFormula = nepFormula;

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNepFormula", int.class);

                method.invoke(_psContractDetailsRemoteModel, nepFormula);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setMaxIncrementalChange",
                        double.class);

                method.invoke(_psContractDetailsRemoteModel,
                    maxIncrementalChange);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetEligible", int.class);

                method.invoke(_psContractDetailsRemoteModel, resetEligible);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetType", int.class);

                method.invoke(_psContractDetailsRemoteModel, resetType);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetDate", Date.class);

                method.invoke(_psContractDetailsRemoteModel, resetDate);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetInterval", int.class);

                method.invoke(_psContractDetailsRemoteModel, resetInterval);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetFrequency", int.class);

                method.invoke(_psContractDetailsRemoteModel, resetFrequency);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetPriceType", int.class);

                method.invoke(_psContractDetailsRemoteModel, netPriceType);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetPriceTypeFormula",
                        String.class);

                method.invoke(_psContractDetailsRemoteModel, netPriceTypeFormula);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceType", int.class);

                method.invoke(_psContractDetailsRemoteModel, basePriceType);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceEntry",
                        double.class);

                method.invoke(_psContractDetailsRemoteModel, basePriceEntry);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceDate", Date.class);

                method.invoke(_psContractDetailsRemoteModel, basePriceDate);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceDdlb", int.class);

                method.invoke(_psContractDetailsRemoteModel, basePriceDdlb);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetBasePrice", int.class);

                method.invoke(_psContractDetailsRemoteModel, netBasePrice);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetBasePriceFormulaId",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel,
                    netBasePriceFormulaId);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSubsequentPeriodPriceType",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel,
                    subsequentPeriodPriceType);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSubsequentPeriodPrice",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel,
                    netSubsequentPeriodPrice);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSubsequentPriceFormulaId",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel,
                    netSubsequentPriceFormulaId);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetPriceType", int.class);

                method.invoke(_psContractDetailsRemoteModel, resetPriceType);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetResetPriceType",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel, netResetPriceType);
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

        if (_psContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _psContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetResetPriceFormulaId",
                        int.class);

                method.invoke(_psContractDetailsRemoteModel,
                    netResetPriceFormulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPsContractDetailsRemoteModel() {
        return _psContractDetailsRemoteModel;
    }

    public void setPsContractDetailsRemoteModel(
        BaseModel<?> psContractDetailsRemoteModel) {
        _psContractDetailsRemoteModel = psContractDetailsRemoteModel;
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

        Class<?> remoteModelClass = _psContractDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_psContractDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PsContractDetailsLocalServiceUtil.addPsContractDetails(this);
        } else {
            PsContractDetailsLocalServiceUtil.updatePsContractDetails(this);
        }
    }

    @Override
    public PsContractDetails toEscapedModel() {
        return (PsContractDetails) ProxyUtil.newProxyInstance(PsContractDetails.class.getClassLoader(),
            new Class[] { PsContractDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PsContractDetailsClp clone = new PsContractDetailsClp();

        clone.setPrice(getPrice());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setPriceProtectionStartDate(getPriceProtectionStartDate());
        clone.setBasePrice(getBasePrice());
        clone.setModifiedDate(getModifiedDate());
        clone.setRevisionDate(getRevisionDate());
        clone.setPriceTolerance(getPriceTolerance());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setSuggestedPrice(getSuggestedPrice());
        clone.setPsContractAttachedDate(getPsContractAttachedDate());
        clone.setPsContractDetailsSid(getPsContractDetailsSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setContractPrice(getContractPrice());
        clone.setPriceToleranceType(getPriceToleranceType());
        clone.setItemPricingQualifierSid(getItemPricingQualifierSid());
        clone.setContractPriceEndDate(getContractPriceEndDate());
        clone.setPriceToleranceFrequency(getPriceToleranceFrequency());
        clone.setContractPriceStartDate(getContractPriceStartDate());
        clone.setPsContractSid(getPsContractSid());
        clone.setPriceProtectionEndDate(getPriceProtectionEndDate());
        clone.setPsContractAttachedStatus(getPsContractAttachedStatus());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setBatchId(getBatchId());
        clone.setPriceToleranceInterval(getPriceToleranceInterval());
        clone.setPriceRevision(getPriceRevision());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setNep(getNep());
        clone.setPriceProtectionStatus(getPriceProtectionStatus());
        clone.setPriceProtectionPriceType(getPriceProtectionPriceType());
        clone.setNepFormula(getNepFormula());
        clone.setMaxIncrementalChange(getMaxIncrementalChange());
        clone.setResetEligible(getResetEligible());
        clone.setResetType(getResetType());
        clone.setResetDate(getResetDate());
        clone.setResetInterval(getResetInterval());
        clone.setResetFrequency(getResetFrequency());
        clone.setNetPriceType(getNetPriceType());
        clone.setNetPriceTypeFormula(getNetPriceTypeFormula());
        clone.setBasePriceType(getBasePriceType());
        clone.setBasePriceEntry(getBasePriceEntry());
        clone.setBasePriceDate(getBasePriceDate());
        clone.setBasePriceDdlb(getBasePriceDdlb());
        clone.setNetBasePrice(getNetBasePrice());
        clone.setNetBasePriceFormulaId(getNetBasePriceFormulaId());
        clone.setSubsequentPeriodPriceType(getSubsequentPeriodPriceType());
        clone.setNetSubsequentPeriodPrice(getNetSubsequentPeriodPrice());
        clone.setNetSubsequentPriceFormulaId(getNetSubsequentPriceFormulaId());
        clone.setResetPriceType(getResetPriceType());
        clone.setNetResetPriceType(getNetResetPriceType());
        clone.setNetResetPriceFormulaId(getNetResetPriceFormulaId());

        return clone;
    }

    @Override
    public int compareTo(PsContractDetails psContractDetails) {
        int primaryKey = psContractDetails.getPrimaryKey();

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

        if (!(obj instanceof PsContractDetailsClp)) {
            return false;
        }

        PsContractDetailsClp psContractDetails = (PsContractDetailsClp) obj;

        int primaryKey = psContractDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(107);

        sb.append("{price=");
        sb.append(getPrice());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", priceProtectionStartDate=");
        sb.append(getPriceProtectionStartDate());
        sb.append(", basePrice=");
        sb.append(getBasePrice());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", revisionDate=");
        sb.append(getRevisionDate());
        sb.append(", priceTolerance=");
        sb.append(getPriceTolerance());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", suggestedPrice=");
        sb.append(getSuggestedPrice());
        sb.append(", psContractAttachedDate=");
        sb.append(getPsContractAttachedDate());
        sb.append(", psContractDetailsSid=");
        sb.append(getPsContractDetailsSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", contractPrice=");
        sb.append(getContractPrice());
        sb.append(", priceToleranceType=");
        sb.append(getPriceToleranceType());
        sb.append(", itemPricingQualifierSid=");
        sb.append(getItemPricingQualifierSid());
        sb.append(", contractPriceEndDate=");
        sb.append(getContractPriceEndDate());
        sb.append(", priceToleranceFrequency=");
        sb.append(getPriceToleranceFrequency());
        sb.append(", contractPriceStartDate=");
        sb.append(getContractPriceStartDate());
        sb.append(", psContractSid=");
        sb.append(getPsContractSid());
        sb.append(", priceProtectionEndDate=");
        sb.append(getPriceProtectionEndDate());
        sb.append(", psContractAttachedStatus=");
        sb.append(getPsContractAttachedStatus());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", priceToleranceInterval=");
        sb.append(getPriceToleranceInterval());
        sb.append(", priceRevision=");
        sb.append(getPriceRevision());
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
        sb.append(", nep=");
        sb.append(getNep());
        sb.append(", priceProtectionStatus=");
        sb.append(getPriceProtectionStatus());
        sb.append(", priceProtectionPriceType=");
        sb.append(getPriceProtectionPriceType());
        sb.append(", nepFormula=");
        sb.append(getNepFormula());
        sb.append(", maxIncrementalChange=");
        sb.append(getMaxIncrementalChange());
        sb.append(", resetEligible=");
        sb.append(getResetEligible());
        sb.append(", resetType=");
        sb.append(getResetType());
        sb.append(", resetDate=");
        sb.append(getResetDate());
        sb.append(", resetInterval=");
        sb.append(getResetInterval());
        sb.append(", resetFrequency=");
        sb.append(getResetFrequency());
        sb.append(", netPriceType=");
        sb.append(getNetPriceType());
        sb.append(", netPriceTypeFormula=");
        sb.append(getNetPriceTypeFormula());
        sb.append(", basePriceType=");
        sb.append(getBasePriceType());
        sb.append(", basePriceEntry=");
        sb.append(getBasePriceEntry());
        sb.append(", basePriceDate=");
        sb.append(getBasePriceDate());
        sb.append(", basePriceDdlb=");
        sb.append(getBasePriceDdlb());
        sb.append(", netBasePrice=");
        sb.append(getNetBasePrice());
        sb.append(", netBasePriceFormulaId=");
        sb.append(getNetBasePriceFormulaId());
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
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(163);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.PsContractDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>basePrice</column-name><column-value><![CDATA[");
        sb.append(getBasePrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>suggestedPrice</column-name><column-value><![CDATA[");
        sb.append(getSuggestedPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psContractAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getPsContractAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psContractDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getPsContractDetailsSid());
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
            "<column><column-name>priceToleranceType</column-name><column-value><![CDATA[");
        sb.append(getPriceToleranceType());
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
            "<column><column-name>priceToleranceFrequency</column-name><column-value><![CDATA[");
        sb.append(getPriceToleranceFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractPriceStartDate</column-name><column-value><![CDATA[");
        sb.append(getContractPriceStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psContractSid</column-name><column-value><![CDATA[");
        sb.append(getPsContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionEndDate</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psContractAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getPsContractAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
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
            "<column><column-name>priceRevision</column-name><column-value><![CDATA[");
        sb.append(getPriceRevision());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBrandMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nep</column-name><column-value><![CDATA[");
        sb.append(getNep());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionStatus</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionPriceType</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nepFormula</column-name><column-value><![CDATA[");
        sb.append(getNepFormula());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>maxIncrementalChange</column-name><column-value><![CDATA[");
        sb.append(getMaxIncrementalChange());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetEligible</column-name><column-value><![CDATA[");
        sb.append(getResetEligible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetType</column-name><column-value><![CDATA[");
        sb.append(getResetType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetDate</column-name><column-value><![CDATA[");
        sb.append(getResetDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetInterval</column-name><column-value><![CDATA[");
        sb.append(getResetInterval());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetFrequency</column-name><column-value><![CDATA[");
        sb.append(getResetFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netPriceType</column-name><column-value><![CDATA[");
        sb.append(getNetPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netPriceTypeFormula</column-name><column-value><![CDATA[");
        sb.append(getNetPriceTypeFormula());
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
            "<column><column-name>basePriceDdlb</column-name><column-value><![CDATA[");
        sb.append(getBasePriceDdlb());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netBasePrice</column-name><column-value><![CDATA[");
        sb.append(getNetBasePrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netBasePriceFormulaId</column-name><column-value><![CDATA[");
        sb.append(getNetBasePriceFormulaId());
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

        sb.append("</model>");

        return sb.toString();
    }
}
