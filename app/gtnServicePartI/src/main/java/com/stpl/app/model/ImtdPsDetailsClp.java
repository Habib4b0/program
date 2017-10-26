package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ImtdPsDetailsLocalServiceUtil;

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


public class ImtdPsDetailsClp extends BaseModelImpl<ImtdPsDetails>
    implements ImtdPsDetails {
    private Date _psDetailsModifiedDate;
    private double _psDetailsSuggestedPrice;
    private double _psDetailsContractPrice;
    private Date _resetDate;
    private int _psDetailsAttachedStatus;
    private int _imtdPsDetailsSid;
    private Date _modifiedDate;
    private int _psDetailsCreatedBy;
    private int _contractMasterSid;
    private Date _psDtlsContPriceEnddate;
    private Date _psDetailsPricPrtcnStdate;
    private Date _imtdCreatedDate;
    private String _netPriceTypeFormula;
    private int _modifiedBy;
    private double _maxIncrementalChange;
    private String _psDetailsPricePlanId;
    private boolean _checkRecord;
    private int _psDtlsPriceToleranceFreq;
    private String _itemName;
    private String _sessionId;
    private int _resetFrequency;
    private int _psDtlsPriceToleranceType;
    private int _psDetailsPricetype;
    private double _psDetailsPriceRevision;
    private int _resetInterval;
    private String _ifpNo;
    private Date _psDetailsAttachedDate;
    private int _nepFormula;
    private int _psDetailsModifiedBy;
    private int _psDtlsPriceToleranceIntrvl;
    private int _itemMasterSid;
    private int _resetType;
    private String _itemId;
    private int _status;
    private int _brandMasterSid;
    private double _psDetailsPrice;
    private Date _psDetailsCreatedDate;
    private int _usersSid;
    private int _createdBy;
    private Date _createdDate;
    private int _psDetailsSid;
    private int _psModelSid;
    private int _priceProtectionPriceType;
    private double _psDetailsBasePrice;
    private String _itemNo;
    private int _ifpModelSid;
    private Date _psDetailsRevisionDate;
    private double _nep;
    private double _psDetailsPriceTolerance;
    private int _priceProtectionStatus;
    private Date _psDtlsContPriceStartdate;
    private int _resetEligible;
    private int _netPriceType;
    private String _operation;
    private int _cfpModelSid;
    private Date _psDetailsPricPrtcnEddate;
    private int _basePriceType;
    private double _basePriceEntry;
    private Date _basePriceDate;
    private int _basePriceDdlb;
    private int _netBasePrice;
    private int _netBasePriceFormulaId;
    private String _netBasePriceFormulaNo;
    private String _netBasePriceFormulaName;
    private int _subsequentPeriodPriceType;
    private int _netSubsequentPeriodPrice;
    private int _netSubsequentPriceFormulaId;
    private String _netSubsequentPriceFormulaNo;
    private String _netSubsequentPriceFormulaName;
    private int _resetPriceType;
    private int _netResetPriceType;
    private int _netResetPriceFormulaId;
    private String _netResetPriceFormulaNo;
    private String _netResetPriceFormulaName;
    private BaseModel<?> _imtdPsDetailsRemoteModel;

    public ImtdPsDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdPsDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdPsDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _imtdPsDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setImtdPsDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _imtdPsDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("psDetailsModifiedDate", getPsDetailsModifiedDate());
        attributes.put("psDetailsSuggestedPrice", getPsDetailsSuggestedPrice());
        attributes.put("psDetailsContractPrice", getPsDetailsContractPrice());
        attributes.put("resetDate", getResetDate());
        attributes.put("psDetailsAttachedStatus", getPsDetailsAttachedStatus());
        attributes.put("imtdPsDetailsSid", getImtdPsDetailsSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("psDetailsCreatedBy", getPsDetailsCreatedBy());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("psDtlsContPriceEnddate", getPsDtlsContPriceEnddate());
        attributes.put("psDetailsPricPrtcnStdate", getPsDetailsPricPrtcnStdate());
        attributes.put("imtdCreatedDate", getImtdCreatedDate());
        attributes.put("netPriceTypeFormula", getNetPriceTypeFormula());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("maxIncrementalChange", getMaxIncrementalChange());
        attributes.put("psDetailsPricePlanId", getPsDetailsPricePlanId());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("psDtlsPriceToleranceFreq", getPsDtlsPriceToleranceFreq());
        attributes.put("itemName", getItemName());
        attributes.put("sessionId", getSessionId());
        attributes.put("resetFrequency", getResetFrequency());
        attributes.put("psDtlsPriceToleranceType", getPsDtlsPriceToleranceType());
        attributes.put("psDetailsPricetype", getPsDetailsPricetype());
        attributes.put("psDetailsPriceRevision", getPsDetailsPriceRevision());
        attributes.put("resetInterval", getResetInterval());
        attributes.put("ifpNo", getIfpNo());
        attributes.put("psDetailsAttachedDate", getPsDetailsAttachedDate());
        attributes.put("nepFormula", getNepFormula());
        attributes.put("psDetailsModifiedBy", getPsDetailsModifiedBy());
        attributes.put("psDtlsPriceToleranceIntrvl",
            getPsDtlsPriceToleranceIntrvl());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("resetType", getResetType());
        attributes.put("itemId", getItemId());
        attributes.put("status", getStatus());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("psDetailsPrice", getPsDetailsPrice());
        attributes.put("psDetailsCreatedDate", getPsDetailsCreatedDate());
        attributes.put("usersSid", getUsersSid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("psDetailsSid", getPsDetailsSid());
        attributes.put("psModelSid", getPsModelSid());
        attributes.put("priceProtectionPriceType", getPriceProtectionPriceType());
        attributes.put("psDetailsBasePrice", getPsDetailsBasePrice());
        attributes.put("itemNo", getItemNo());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("psDetailsRevisionDate", getPsDetailsRevisionDate());
        attributes.put("nep", getNep());
        attributes.put("psDetailsPriceTolerance", getPsDetailsPriceTolerance());
        attributes.put("priceProtectionStatus", getPriceProtectionStatus());
        attributes.put("psDtlsContPriceStartdate", getPsDtlsContPriceStartdate());
        attributes.put("resetEligible", getResetEligible());
        attributes.put("netPriceType", getNetPriceType());
        attributes.put("operation", getOperation());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("psDetailsPricPrtcnEddate", getPsDetailsPricPrtcnEddate());
        attributes.put("basePriceType", getBasePriceType());
        attributes.put("basePriceEntry", getBasePriceEntry());
        attributes.put("basePriceDate", getBasePriceDate());
        attributes.put("basePriceDdlb", getBasePriceDdlb());
        attributes.put("netBasePrice", getNetBasePrice());
        attributes.put("netBasePriceFormulaId", getNetBasePriceFormulaId());
        attributes.put("netBasePriceFormulaNo", getNetBasePriceFormulaNo());
        attributes.put("netBasePriceFormulaName", getNetBasePriceFormulaName());
        attributes.put("subsequentPeriodPriceType",
            getSubsequentPeriodPriceType());
        attributes.put("netSubsequentPeriodPrice", getNetSubsequentPeriodPrice());
        attributes.put("netSubsequentPriceFormulaId",
            getNetSubsequentPriceFormulaId());
        attributes.put("netSubsequentPriceFormulaNo",
            getNetSubsequentPriceFormulaNo());
        attributes.put("netSubsequentPriceFormulaName",
            getNetSubsequentPriceFormulaName());
        attributes.put("resetPriceType", getResetPriceType());
        attributes.put("netResetPriceType", getNetResetPriceType());
        attributes.put("netResetPriceFormulaId", getNetResetPriceFormulaId());
        attributes.put("netResetPriceFormulaNo", getNetResetPriceFormulaNo());
        attributes.put("netResetPriceFormulaName", getNetResetPriceFormulaName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date psDetailsModifiedDate = (Date) attributes.get(
                "psDetailsModifiedDate");

        if (psDetailsModifiedDate != null) {
            setPsDetailsModifiedDate(psDetailsModifiedDate);
        }

        Double psDetailsSuggestedPrice = (Double) attributes.get(
                "psDetailsSuggestedPrice");

        if (psDetailsSuggestedPrice != null) {
            setPsDetailsSuggestedPrice(psDetailsSuggestedPrice);
        }

        Double psDetailsContractPrice = (Double) attributes.get(
                "psDetailsContractPrice");

        if (psDetailsContractPrice != null) {
            setPsDetailsContractPrice(psDetailsContractPrice);
        }

        Date resetDate = (Date) attributes.get("resetDate");

        if (resetDate != null) {
            setResetDate(resetDate);
        }

        Integer psDetailsAttachedStatus = (Integer) attributes.get(
                "psDetailsAttachedStatus");

        if (psDetailsAttachedStatus != null) {
            setPsDetailsAttachedStatus(psDetailsAttachedStatus);
        }

        Integer imtdPsDetailsSid = (Integer) attributes.get("imtdPsDetailsSid");

        if (imtdPsDetailsSid != null) {
            setImtdPsDetailsSid(imtdPsDetailsSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer psDetailsCreatedBy = (Integer) attributes.get(
                "psDetailsCreatedBy");

        if (psDetailsCreatedBy != null) {
            setPsDetailsCreatedBy(psDetailsCreatedBy);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Date psDtlsContPriceEnddate = (Date) attributes.get(
                "psDtlsContPriceEnddate");

        if (psDtlsContPriceEnddate != null) {
            setPsDtlsContPriceEnddate(psDtlsContPriceEnddate);
        }

        Date psDetailsPricPrtcnStdate = (Date) attributes.get(
                "psDetailsPricPrtcnStdate");

        if (psDetailsPricPrtcnStdate != null) {
            setPsDetailsPricPrtcnStdate(psDetailsPricPrtcnStdate);
        }

        Date imtdCreatedDate = (Date) attributes.get("imtdCreatedDate");

        if (imtdCreatedDate != null) {
            setImtdCreatedDate(imtdCreatedDate);
        }

        String netPriceTypeFormula = (String) attributes.get(
                "netPriceTypeFormula");

        if (netPriceTypeFormula != null) {
            setNetPriceTypeFormula(netPriceTypeFormula);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Double maxIncrementalChange = (Double) attributes.get(
                "maxIncrementalChange");

        if (maxIncrementalChange != null) {
            setMaxIncrementalChange(maxIncrementalChange);
        }

        String psDetailsPricePlanId = (String) attributes.get(
                "psDetailsPricePlanId");

        if (psDetailsPricePlanId != null) {
            setPsDetailsPricePlanId(psDetailsPricePlanId);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        Integer psDtlsPriceToleranceFreq = (Integer) attributes.get(
                "psDtlsPriceToleranceFreq");

        if (psDtlsPriceToleranceFreq != null) {
            setPsDtlsPriceToleranceFreq(psDtlsPriceToleranceFreq);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        Integer resetFrequency = (Integer) attributes.get("resetFrequency");

        if (resetFrequency != null) {
            setResetFrequency(resetFrequency);
        }

        Integer psDtlsPriceToleranceType = (Integer) attributes.get(
                "psDtlsPriceToleranceType");

        if (psDtlsPriceToleranceType != null) {
            setPsDtlsPriceToleranceType(psDtlsPriceToleranceType);
        }

        Integer psDetailsPricetype = (Integer) attributes.get(
                "psDetailsPricetype");

        if (psDetailsPricetype != null) {
            setPsDetailsPricetype(psDetailsPricetype);
        }

        Double psDetailsPriceRevision = (Double) attributes.get(
                "psDetailsPriceRevision");

        if (psDetailsPriceRevision != null) {
            setPsDetailsPriceRevision(psDetailsPriceRevision);
        }

        Integer resetInterval = (Integer) attributes.get("resetInterval");

        if (resetInterval != null) {
            setResetInterval(resetInterval);
        }

        String ifpNo = (String) attributes.get("ifpNo");

        if (ifpNo != null) {
            setIfpNo(ifpNo);
        }

        Date psDetailsAttachedDate = (Date) attributes.get(
                "psDetailsAttachedDate");

        if (psDetailsAttachedDate != null) {
            setPsDetailsAttachedDate(psDetailsAttachedDate);
        }

        Integer nepFormula = (Integer) attributes.get("nepFormula");

        if (nepFormula != null) {
            setNepFormula(nepFormula);
        }

        Integer psDetailsModifiedBy = (Integer) attributes.get(
                "psDetailsModifiedBy");

        if (psDetailsModifiedBy != null) {
            setPsDetailsModifiedBy(psDetailsModifiedBy);
        }

        Integer psDtlsPriceToleranceIntrvl = (Integer) attributes.get(
                "psDtlsPriceToleranceIntrvl");

        if (psDtlsPriceToleranceIntrvl != null) {
            setPsDtlsPriceToleranceIntrvl(psDtlsPriceToleranceIntrvl);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer resetType = (Integer) attributes.get("resetType");

        if (resetType != null) {
            setResetType(resetType);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Integer status = (Integer) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Integer brandMasterSid = (Integer) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        Double psDetailsPrice = (Double) attributes.get("psDetailsPrice");

        if (psDetailsPrice != null) {
            setPsDetailsPrice(psDetailsPrice);
        }

        Date psDetailsCreatedDate = (Date) attributes.get(
                "psDetailsCreatedDate");

        if (psDetailsCreatedDate != null) {
            setPsDetailsCreatedDate(psDetailsCreatedDate);
        }

        Integer usersSid = (Integer) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer psDetailsSid = (Integer) attributes.get("psDetailsSid");

        if (psDetailsSid != null) {
            setPsDetailsSid(psDetailsSid);
        }

        Integer psModelSid = (Integer) attributes.get("psModelSid");

        if (psModelSid != null) {
            setPsModelSid(psModelSid);
        }

        Integer priceProtectionPriceType = (Integer) attributes.get(
                "priceProtectionPriceType");

        if (priceProtectionPriceType != null) {
            setPriceProtectionPriceType(priceProtectionPriceType);
        }

        Double psDetailsBasePrice = (Double) attributes.get(
                "psDetailsBasePrice");

        if (psDetailsBasePrice != null) {
            setPsDetailsBasePrice(psDetailsBasePrice);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        Date psDetailsRevisionDate = (Date) attributes.get(
                "psDetailsRevisionDate");

        if (psDetailsRevisionDate != null) {
            setPsDetailsRevisionDate(psDetailsRevisionDate);
        }

        Double nep = (Double) attributes.get("nep");

        if (nep != null) {
            setNep(nep);
        }

        Double psDetailsPriceTolerance = (Double) attributes.get(
                "psDetailsPriceTolerance");

        if (psDetailsPriceTolerance != null) {
            setPsDetailsPriceTolerance(psDetailsPriceTolerance);
        }

        Integer priceProtectionStatus = (Integer) attributes.get(
                "priceProtectionStatus");

        if (priceProtectionStatus != null) {
            setPriceProtectionStatus(priceProtectionStatus);
        }

        Date psDtlsContPriceStartdate = (Date) attributes.get(
                "psDtlsContPriceStartdate");

        if (psDtlsContPriceStartdate != null) {
            setPsDtlsContPriceStartdate(psDtlsContPriceStartdate);
        }

        Integer resetEligible = (Integer) attributes.get("resetEligible");

        if (resetEligible != null) {
            setResetEligible(resetEligible);
        }

        Integer netPriceType = (Integer) attributes.get("netPriceType");

        if (netPriceType != null) {
            setNetPriceType(netPriceType);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        Date psDetailsPricPrtcnEddate = (Date) attributes.get(
                "psDetailsPricPrtcnEddate");

        if (psDetailsPricPrtcnEddate != null) {
            setPsDetailsPricPrtcnEddate(psDetailsPricPrtcnEddate);
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

        String netBasePriceFormulaNo = (String) attributes.get(
                "netBasePriceFormulaNo");

        if (netBasePriceFormulaNo != null) {
            setNetBasePriceFormulaNo(netBasePriceFormulaNo);
        }

        String netBasePriceFormulaName = (String) attributes.get(
                "netBasePriceFormulaName");

        if (netBasePriceFormulaName != null) {
            setNetBasePriceFormulaName(netBasePriceFormulaName);
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

        String netSubsequentPriceFormulaNo = (String) attributes.get(
                "netSubsequentPriceFormulaNo");

        if (netSubsequentPriceFormulaNo != null) {
            setNetSubsequentPriceFormulaNo(netSubsequentPriceFormulaNo);
        }

        String netSubsequentPriceFormulaName = (String) attributes.get(
                "netSubsequentPriceFormulaName");

        if (netSubsequentPriceFormulaName != null) {
            setNetSubsequentPriceFormulaName(netSubsequentPriceFormulaName);
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

        String netResetPriceFormulaNo = (String) attributes.get(
                "netResetPriceFormulaNo");

        if (netResetPriceFormulaNo != null) {
            setNetResetPriceFormulaNo(netResetPriceFormulaNo);
        }

        String netResetPriceFormulaName = (String) attributes.get(
                "netResetPriceFormulaName");

        if (netResetPriceFormulaName != null) {
            setNetResetPriceFormulaName(netResetPriceFormulaName);
        }
    }

    @Override
    public Date getPsDetailsModifiedDate() {
        return _psDetailsModifiedDate;
    }

    @Override
    public void setPsDetailsModifiedDate(Date psDetailsModifiedDate) {
        _psDetailsModifiedDate = psDetailsModifiedDate;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsModifiedDate",
                        Date.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsModifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPsDetailsSuggestedPrice() {
        return _psDetailsSuggestedPrice;
    }

    @Override
    public void setPsDetailsSuggestedPrice(double psDetailsSuggestedPrice) {
        _psDetailsSuggestedPrice = psDetailsSuggestedPrice;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsSuggestedPrice",
                        double.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsSuggestedPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPsDetailsContractPrice() {
        return _psDetailsContractPrice;
    }

    @Override
    public void setPsDetailsContractPrice(double psDetailsContractPrice) {
        _psDetailsContractPrice = psDetailsContractPrice;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsContractPrice",
                        double.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsContractPrice);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetDate", Date.class);

                method.invoke(_imtdPsDetailsRemoteModel, resetDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsDetailsAttachedStatus() {
        return _psDetailsAttachedStatus;
    }

    @Override
    public void setPsDetailsAttachedStatus(int psDetailsAttachedStatus) {
        _psDetailsAttachedStatus = psDetailsAttachedStatus;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsAttachedStatus",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsAttachedStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getImtdPsDetailsSid() {
        return _imtdPsDetailsSid;
    }

    @Override
    public void setImtdPsDetailsSid(int imtdPsDetailsSid) {
        _imtdPsDetailsSid = imtdPsDetailsSid;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdPsDetailsSid", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, imtdPsDetailsSid);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_imtdPsDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsDetailsCreatedBy() {
        return _psDetailsCreatedBy;
    }

    @Override
    public void setPsDetailsCreatedBy(int psDetailsCreatedBy) {
        _psDetailsCreatedBy = psDetailsCreatedBy;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsCreatedBy",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsCreatedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsDtlsContPriceEnddate() {
        return _psDtlsContPriceEnddate;
    }

    @Override
    public void setPsDtlsContPriceEnddate(Date psDtlsContPriceEnddate) {
        _psDtlsContPriceEnddate = psDtlsContPriceEnddate;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDtlsContPriceEnddate",
                        Date.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDtlsContPriceEnddate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsDetailsPricPrtcnStdate() {
        return _psDetailsPricPrtcnStdate;
    }

    @Override
    public void setPsDetailsPricPrtcnStdate(Date psDetailsPricPrtcnStdate) {
        _psDetailsPricPrtcnStdate = psDetailsPricPrtcnStdate;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsPricPrtcnStdate",
                        Date.class);

                method.invoke(_imtdPsDetailsRemoteModel,
                    psDetailsPricPrtcnStdate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getImtdCreatedDate() {
        return _imtdCreatedDate;
    }

    @Override
    public void setImtdCreatedDate(Date imtdCreatedDate) {
        _imtdCreatedDate = imtdCreatedDate;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdCreatedDate", Date.class);

                method.invoke(_imtdPsDetailsRemoteModel, imtdCreatedDate);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetPriceTypeFormula",
                        String.class);

                method.invoke(_imtdPsDetailsRemoteModel, netPriceTypeFormula);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, modifiedBy);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setMaxIncrementalChange",
                        double.class);

                method.invoke(_imtdPsDetailsRemoteModel, maxIncrementalChange);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPsDetailsPricePlanId() {
        return _psDetailsPricePlanId;
    }

    @Override
    public void setPsDetailsPricePlanId(String psDetailsPricePlanId) {
        _psDetailsPricePlanId = psDetailsPricePlanId;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsPricePlanId",
                        String.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsPricePlanId);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_imtdPsDetailsRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsDtlsPriceToleranceFreq() {
        return _psDtlsPriceToleranceFreq;
    }

    @Override
    public void setPsDtlsPriceToleranceFreq(int psDtlsPriceToleranceFreq) {
        _psDtlsPriceToleranceFreq = psDtlsPriceToleranceFreq;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDtlsPriceToleranceFreq",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel,
                    psDtlsPriceToleranceFreq);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_imtdPsDetailsRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(String sessionId) {
        _sessionId = sessionId;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_imtdPsDetailsRemoteModel, sessionId);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetFrequency", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, resetFrequency);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsDtlsPriceToleranceType() {
        return _psDtlsPriceToleranceType;
    }

    @Override
    public void setPsDtlsPriceToleranceType(int psDtlsPriceToleranceType) {
        _psDtlsPriceToleranceType = psDtlsPriceToleranceType;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDtlsPriceToleranceType",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel,
                    psDtlsPriceToleranceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsDetailsPricetype() {
        return _psDetailsPricetype;
    }

    @Override
    public void setPsDetailsPricetype(int psDetailsPricetype) {
        _psDetailsPricetype = psDetailsPricetype;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsPricetype",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsPricetype);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPsDetailsPriceRevision() {
        return _psDetailsPriceRevision;
    }

    @Override
    public void setPsDetailsPriceRevision(double psDetailsPriceRevision) {
        _psDetailsPriceRevision = psDetailsPriceRevision;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsPriceRevision",
                        double.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsPriceRevision);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetInterval", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, resetInterval);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIfpNo() {
        return _ifpNo;
    }

    @Override
    public void setIfpNo(String ifpNo) {
        _ifpNo = ifpNo;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpNo", String.class);

                method.invoke(_imtdPsDetailsRemoteModel, ifpNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsDetailsAttachedDate() {
        return _psDetailsAttachedDate;
    }

    @Override
    public void setPsDetailsAttachedDate(Date psDetailsAttachedDate) {
        _psDetailsAttachedDate = psDetailsAttachedDate;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsAttachedDate",
                        Date.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsAttachedDate);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNepFormula", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, nepFormula);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsDetailsModifiedBy() {
        return _psDetailsModifiedBy;
    }

    @Override
    public void setPsDetailsModifiedBy(int psDetailsModifiedBy) {
        _psDetailsModifiedBy = psDetailsModifiedBy;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsModifiedBy",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsModifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsDtlsPriceToleranceIntrvl() {
        return _psDtlsPriceToleranceIntrvl;
    }

    @Override
    public void setPsDtlsPriceToleranceIntrvl(int psDtlsPriceToleranceIntrvl) {
        _psDtlsPriceToleranceIntrvl = psDtlsPriceToleranceIntrvl;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDtlsPriceToleranceIntrvl",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel,
                    psDtlsPriceToleranceIntrvl);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, itemMasterSid);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetType", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, resetType);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_imtdPsDetailsRemoteModel, itemId);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setStatus", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, status);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandMasterSid", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, brandMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPsDetailsPrice() {
        return _psDetailsPrice;
    }

    @Override
    public void setPsDetailsPrice(double psDetailsPrice) {
        _psDetailsPrice = psDetailsPrice;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsPrice",
                        double.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsPrice);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsDetailsCreatedDate() {
        return _psDetailsCreatedDate;
    }

    @Override
    public void setPsDetailsCreatedDate(Date psDetailsCreatedDate) {
        _psDetailsCreatedDate = psDetailsCreatedDate;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsCreatedDate",
                        Date.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsCreatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getUsersSid() {
        return _usersSid;
    }

    @Override
    public void setUsersSid(int usersSid) {
        _usersSid = usersSid;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setUsersSid", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, usersSid);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, createdBy);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_imtdPsDetailsRemoteModel, createdDate);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsSid", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsSid);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsModelSid", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, psModelSid);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionPriceType",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel,
                    priceProtectionPriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPsDetailsBasePrice() {
        return _psDetailsBasePrice;
    }

    @Override
    public void setPsDetailsBasePrice(double psDetailsBasePrice) {
        _psDetailsBasePrice = psDetailsBasePrice;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsBasePrice",
                        double.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsBasePrice);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_imtdPsDetailsRemoteModel, itemNo);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpModelSid", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, ifpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsDetailsRevisionDate() {
        return _psDetailsRevisionDate;
    }

    @Override
    public void setPsDetailsRevisionDate(Date psDetailsRevisionDate) {
        _psDetailsRevisionDate = psDetailsRevisionDate;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsRevisionDate",
                        Date.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsRevisionDate);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNep", double.class);

                method.invoke(_imtdPsDetailsRemoteModel, nep);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPsDetailsPriceTolerance() {
        return _psDetailsPriceTolerance;
    }

    @Override
    public void setPsDetailsPriceTolerance(double psDetailsPriceTolerance) {
        _psDetailsPriceTolerance = psDetailsPriceTolerance;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsPriceTolerance",
                        double.class);

                method.invoke(_imtdPsDetailsRemoteModel, psDetailsPriceTolerance);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceProtectionStatus",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel, priceProtectionStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsDtlsContPriceStartdate() {
        return _psDtlsContPriceStartdate;
    }

    @Override
    public void setPsDtlsContPriceStartdate(Date psDtlsContPriceStartdate) {
        _psDtlsContPriceStartdate = psDtlsContPriceStartdate;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDtlsContPriceStartdate",
                        Date.class);

                method.invoke(_imtdPsDetailsRemoteModel,
                    psDtlsContPriceStartdate);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetEligible", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, resetEligible);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetPriceType", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, netPriceType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOperation() {
        return _operation;
    }

    @Override
    public void setOperation(String operation) {
        _operation = operation;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setOperation", String.class);

                method.invoke(_imtdPsDetailsRemoteModel, operation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpModelSid() {
        return _cfpModelSid;
    }

    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _cfpModelSid = cfpModelSid;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpModelSid", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, cfpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsDetailsPricPrtcnEddate() {
        return _psDetailsPricPrtcnEddate;
    }

    @Override
    public void setPsDetailsPricPrtcnEddate(Date psDetailsPricPrtcnEddate) {
        _psDetailsPricPrtcnEddate = psDetailsPricPrtcnEddate;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDetailsPricPrtcnEddate",
                        Date.class);

                method.invoke(_imtdPsDetailsRemoteModel,
                    psDetailsPricPrtcnEddate);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceType", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, basePriceType);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceEntry",
                        double.class);

                method.invoke(_imtdPsDetailsRemoteModel, basePriceEntry);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceDate", Date.class);

                method.invoke(_imtdPsDetailsRemoteModel, basePriceDate);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBasePriceDdlb", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, basePriceDdlb);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetBasePrice", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, netBasePrice);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetBasePriceFormulaId",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel, netBasePriceFormulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetBasePriceFormulaNo() {
        return _netBasePriceFormulaNo;
    }

    @Override
    public void setNetBasePriceFormulaNo(String netBasePriceFormulaNo) {
        _netBasePriceFormulaNo = netBasePriceFormulaNo;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetBasePriceFormulaNo",
                        String.class);

                method.invoke(_imtdPsDetailsRemoteModel, netBasePriceFormulaNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetBasePriceFormulaName() {
        return _netBasePriceFormulaName;
    }

    @Override
    public void setNetBasePriceFormulaName(String netBasePriceFormulaName) {
        _netBasePriceFormulaName = netBasePriceFormulaName;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetBasePriceFormulaName",
                        String.class);

                method.invoke(_imtdPsDetailsRemoteModel, netBasePriceFormulaName);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSubsequentPeriodPriceType",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel,
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSubsequentPeriodPrice",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel,
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSubsequentPriceFormulaId",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel,
                    netSubsequentPriceFormulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetSubsequentPriceFormulaNo() {
        return _netSubsequentPriceFormulaNo;
    }

    @Override
    public void setNetSubsequentPriceFormulaNo(
        String netSubsequentPriceFormulaNo) {
        _netSubsequentPriceFormulaNo = netSubsequentPriceFormulaNo;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSubsequentPriceFormulaNo",
                        String.class);

                method.invoke(_imtdPsDetailsRemoteModel,
                    netSubsequentPriceFormulaNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetSubsequentPriceFormulaName() {
        return _netSubsequentPriceFormulaName;
    }

    @Override
    public void setNetSubsequentPriceFormulaName(
        String netSubsequentPriceFormulaName) {
        _netSubsequentPriceFormulaName = netSubsequentPriceFormulaName;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSubsequentPriceFormulaName",
                        String.class);

                method.invoke(_imtdPsDetailsRemoteModel,
                    netSubsequentPriceFormulaName);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setResetPriceType", int.class);

                method.invoke(_imtdPsDetailsRemoteModel, resetPriceType);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetResetPriceType",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel, netResetPriceType);
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

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetResetPriceFormulaId",
                        int.class);

                method.invoke(_imtdPsDetailsRemoteModel, netResetPriceFormulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetResetPriceFormulaNo() {
        return _netResetPriceFormulaNo;
    }

    @Override
    public void setNetResetPriceFormulaNo(String netResetPriceFormulaNo) {
        _netResetPriceFormulaNo = netResetPriceFormulaNo;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetResetPriceFormulaNo",
                        String.class);

                method.invoke(_imtdPsDetailsRemoteModel, netResetPriceFormulaNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetResetPriceFormulaName() {
        return _netResetPriceFormulaName;
    }

    @Override
    public void setNetResetPriceFormulaName(String netResetPriceFormulaName) {
        _netResetPriceFormulaName = netResetPriceFormulaName;

        if (_imtdPsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _imtdPsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetResetPriceFormulaName",
                        String.class);

                method.invoke(_imtdPsDetailsRemoteModel,
                    netResetPriceFormulaName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImtdPsDetailsRemoteModel() {
        return _imtdPsDetailsRemoteModel;
    }

    public void setImtdPsDetailsRemoteModel(
        BaseModel<?> imtdPsDetailsRemoteModel) {
        _imtdPsDetailsRemoteModel = imtdPsDetailsRemoteModel;
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

        Class<?> remoteModelClass = _imtdPsDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_imtdPsDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImtdPsDetailsLocalServiceUtil.addImtdPsDetails(this);
        } else {
            ImtdPsDetailsLocalServiceUtil.updateImtdPsDetails(this);
        }
    }

    @Override
    public ImtdPsDetails toEscapedModel() {
        return (ImtdPsDetails) ProxyUtil.newProxyInstance(ImtdPsDetails.class.getClassLoader(),
            new Class[] { ImtdPsDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImtdPsDetailsClp clone = new ImtdPsDetailsClp();

        clone.setPsDetailsModifiedDate(getPsDetailsModifiedDate());
        clone.setPsDetailsSuggestedPrice(getPsDetailsSuggestedPrice());
        clone.setPsDetailsContractPrice(getPsDetailsContractPrice());
        clone.setResetDate(getResetDate());
        clone.setPsDetailsAttachedStatus(getPsDetailsAttachedStatus());
        clone.setImtdPsDetailsSid(getImtdPsDetailsSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setPsDetailsCreatedBy(getPsDetailsCreatedBy());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setPsDtlsContPriceEnddate(getPsDtlsContPriceEnddate());
        clone.setPsDetailsPricPrtcnStdate(getPsDetailsPricPrtcnStdate());
        clone.setImtdCreatedDate(getImtdCreatedDate());
        clone.setNetPriceTypeFormula(getNetPriceTypeFormula());
        clone.setModifiedBy(getModifiedBy());
        clone.setMaxIncrementalChange(getMaxIncrementalChange());
        clone.setPsDetailsPricePlanId(getPsDetailsPricePlanId());
        clone.setCheckRecord(getCheckRecord());
        clone.setPsDtlsPriceToleranceFreq(getPsDtlsPriceToleranceFreq());
        clone.setItemName(getItemName());
        clone.setSessionId(getSessionId());
        clone.setResetFrequency(getResetFrequency());
        clone.setPsDtlsPriceToleranceType(getPsDtlsPriceToleranceType());
        clone.setPsDetailsPricetype(getPsDetailsPricetype());
        clone.setPsDetailsPriceRevision(getPsDetailsPriceRevision());
        clone.setResetInterval(getResetInterval());
        clone.setIfpNo(getIfpNo());
        clone.setPsDetailsAttachedDate(getPsDetailsAttachedDate());
        clone.setNepFormula(getNepFormula());
        clone.setPsDetailsModifiedBy(getPsDetailsModifiedBy());
        clone.setPsDtlsPriceToleranceIntrvl(getPsDtlsPriceToleranceIntrvl());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setResetType(getResetType());
        clone.setItemId(getItemId());
        clone.setStatus(getStatus());
        clone.setBrandMasterSid(getBrandMasterSid());
        clone.setPsDetailsPrice(getPsDetailsPrice());
        clone.setPsDetailsCreatedDate(getPsDetailsCreatedDate());
        clone.setUsersSid(getUsersSid());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setPsDetailsSid(getPsDetailsSid());
        clone.setPsModelSid(getPsModelSid());
        clone.setPriceProtectionPriceType(getPriceProtectionPriceType());
        clone.setPsDetailsBasePrice(getPsDetailsBasePrice());
        clone.setItemNo(getItemNo());
        clone.setIfpModelSid(getIfpModelSid());
        clone.setPsDetailsRevisionDate(getPsDetailsRevisionDate());
        clone.setNep(getNep());
        clone.setPsDetailsPriceTolerance(getPsDetailsPriceTolerance());
        clone.setPriceProtectionStatus(getPriceProtectionStatus());
        clone.setPsDtlsContPriceStartdate(getPsDtlsContPriceStartdate());
        clone.setResetEligible(getResetEligible());
        clone.setNetPriceType(getNetPriceType());
        clone.setOperation(getOperation());
        clone.setCfpModelSid(getCfpModelSid());
        clone.setPsDetailsPricPrtcnEddate(getPsDetailsPricPrtcnEddate());
        clone.setBasePriceType(getBasePriceType());
        clone.setBasePriceEntry(getBasePriceEntry());
        clone.setBasePriceDate(getBasePriceDate());
        clone.setBasePriceDdlb(getBasePriceDdlb());
        clone.setNetBasePrice(getNetBasePrice());
        clone.setNetBasePriceFormulaId(getNetBasePriceFormulaId());
        clone.setNetBasePriceFormulaNo(getNetBasePriceFormulaNo());
        clone.setNetBasePriceFormulaName(getNetBasePriceFormulaName());
        clone.setSubsequentPeriodPriceType(getSubsequentPeriodPriceType());
        clone.setNetSubsequentPeriodPrice(getNetSubsequentPeriodPrice());
        clone.setNetSubsequentPriceFormulaId(getNetSubsequentPriceFormulaId());
        clone.setNetSubsequentPriceFormulaNo(getNetSubsequentPriceFormulaNo());
        clone.setNetSubsequentPriceFormulaName(getNetSubsequentPriceFormulaName());
        clone.setResetPriceType(getResetPriceType());
        clone.setNetResetPriceType(getNetResetPriceType());
        clone.setNetResetPriceFormulaId(getNetResetPriceFormulaId());
        clone.setNetResetPriceFormulaNo(getNetResetPriceFormulaNo());
        clone.setNetResetPriceFormulaName(getNetResetPriceFormulaName());

        return clone;
    }

    @Override
    public int compareTo(ImtdPsDetails imtdPsDetails) {
        int primaryKey = imtdPsDetails.getPrimaryKey();

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

        if (!(obj instanceof ImtdPsDetailsClp)) {
            return false;
        }

        ImtdPsDetailsClp imtdPsDetails = (ImtdPsDetailsClp) obj;

        int primaryKey = imtdPsDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(149);

        sb.append("{psDetailsModifiedDate=");
        sb.append(getPsDetailsModifiedDate());
        sb.append(", psDetailsSuggestedPrice=");
        sb.append(getPsDetailsSuggestedPrice());
        sb.append(", psDetailsContractPrice=");
        sb.append(getPsDetailsContractPrice());
        sb.append(", resetDate=");
        sb.append(getResetDate());
        sb.append(", psDetailsAttachedStatus=");
        sb.append(getPsDetailsAttachedStatus());
        sb.append(", imtdPsDetailsSid=");
        sb.append(getImtdPsDetailsSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", psDetailsCreatedBy=");
        sb.append(getPsDetailsCreatedBy());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", psDtlsContPriceEnddate=");
        sb.append(getPsDtlsContPriceEnddate());
        sb.append(", psDetailsPricPrtcnStdate=");
        sb.append(getPsDetailsPricPrtcnStdate());
        sb.append(", imtdCreatedDate=");
        sb.append(getImtdCreatedDate());
        sb.append(", netPriceTypeFormula=");
        sb.append(getNetPriceTypeFormula());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", maxIncrementalChange=");
        sb.append(getMaxIncrementalChange());
        sb.append(", psDetailsPricePlanId=");
        sb.append(getPsDetailsPricePlanId());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", psDtlsPriceToleranceFreq=");
        sb.append(getPsDtlsPriceToleranceFreq());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", resetFrequency=");
        sb.append(getResetFrequency());
        sb.append(", psDtlsPriceToleranceType=");
        sb.append(getPsDtlsPriceToleranceType());
        sb.append(", psDetailsPricetype=");
        sb.append(getPsDetailsPricetype());
        sb.append(", psDetailsPriceRevision=");
        sb.append(getPsDetailsPriceRevision());
        sb.append(", resetInterval=");
        sb.append(getResetInterval());
        sb.append(", ifpNo=");
        sb.append(getIfpNo());
        sb.append(", psDetailsAttachedDate=");
        sb.append(getPsDetailsAttachedDate());
        sb.append(", nepFormula=");
        sb.append(getNepFormula());
        sb.append(", psDetailsModifiedBy=");
        sb.append(getPsDetailsModifiedBy());
        sb.append(", psDtlsPriceToleranceIntrvl=");
        sb.append(getPsDtlsPriceToleranceIntrvl());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", resetType=");
        sb.append(getResetType());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", brandMasterSid=");
        sb.append(getBrandMasterSid());
        sb.append(", psDetailsPrice=");
        sb.append(getPsDetailsPrice());
        sb.append(", psDetailsCreatedDate=");
        sb.append(getPsDetailsCreatedDate());
        sb.append(", usersSid=");
        sb.append(getUsersSid());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", psDetailsSid=");
        sb.append(getPsDetailsSid());
        sb.append(", psModelSid=");
        sb.append(getPsModelSid());
        sb.append(", priceProtectionPriceType=");
        sb.append(getPriceProtectionPriceType());
        sb.append(", psDetailsBasePrice=");
        sb.append(getPsDetailsBasePrice());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append(", psDetailsRevisionDate=");
        sb.append(getPsDetailsRevisionDate());
        sb.append(", nep=");
        sb.append(getNep());
        sb.append(", psDetailsPriceTolerance=");
        sb.append(getPsDetailsPriceTolerance());
        sb.append(", priceProtectionStatus=");
        sb.append(getPriceProtectionStatus());
        sb.append(", psDtlsContPriceStartdate=");
        sb.append(getPsDtlsContPriceStartdate());
        sb.append(", resetEligible=");
        sb.append(getResetEligible());
        sb.append(", netPriceType=");
        sb.append(getNetPriceType());
        sb.append(", operation=");
        sb.append(getOperation());
        sb.append(", cfpModelSid=");
        sb.append(getCfpModelSid());
        sb.append(", psDetailsPricPrtcnEddate=");
        sb.append(getPsDetailsPricPrtcnEddate());
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
        sb.append(", netBasePriceFormulaNo=");
        sb.append(getNetBasePriceFormulaNo());
        sb.append(", netBasePriceFormulaName=");
        sb.append(getNetBasePriceFormulaName());
        sb.append(", subsequentPeriodPriceType=");
        sb.append(getSubsequentPeriodPriceType());
        sb.append(", netSubsequentPeriodPrice=");
        sb.append(getNetSubsequentPeriodPrice());
        sb.append(", netSubsequentPriceFormulaId=");
        sb.append(getNetSubsequentPriceFormulaId());
        sb.append(", netSubsequentPriceFormulaNo=");
        sb.append(getNetSubsequentPriceFormulaNo());
        sb.append(", netSubsequentPriceFormulaName=");
        sb.append(getNetSubsequentPriceFormulaName());
        sb.append(", resetPriceType=");
        sb.append(getResetPriceType());
        sb.append(", netResetPriceType=");
        sb.append(getNetResetPriceType());
        sb.append(", netResetPriceFormulaId=");
        sb.append(getNetResetPriceFormulaId());
        sb.append(", netResetPriceFormulaNo=");
        sb.append(getNetResetPriceFormulaNo());
        sb.append(", netResetPriceFormulaName=");
        sb.append(getNetResetPriceFormulaName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(226);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ImtdPsDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>psDetailsModifiedDate</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsSuggestedPrice</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsSuggestedPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsContractPrice</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsContractPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetDate</column-name><column-value><![CDATA[");
        sb.append(getResetDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdPsDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getImtdPsDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsCreatedBy</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDtlsContPriceEnddate</column-name><column-value><![CDATA[");
        sb.append(getPsDtlsContPriceEnddate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsPricPrtcnStdate</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsPricPrtcnStdate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getImtdCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netPriceTypeFormula</column-name><column-value><![CDATA[");
        sb.append(getNetPriceTypeFormula());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>maxIncrementalChange</column-name><column-value><![CDATA[");
        sb.append(getMaxIncrementalChange());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsPricePlanId</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsPricePlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDtlsPriceToleranceFreq</column-name><column-value><![CDATA[");
        sb.append(getPsDtlsPriceToleranceFreq());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetFrequency</column-name><column-value><![CDATA[");
        sb.append(getResetFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDtlsPriceToleranceType</column-name><column-value><![CDATA[");
        sb.append(getPsDtlsPriceToleranceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsPricetype</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsPricetype());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsPriceRevision</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsPriceRevision());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetInterval</column-name><column-value><![CDATA[");
        sb.append(getResetInterval());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpNo</column-name><column-value><![CDATA[");
        sb.append(getIfpNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nepFormula</column-name><column-value><![CDATA[");
        sb.append(getNepFormula());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsModifiedBy</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDtlsPriceToleranceIntrvl</column-name><column-value><![CDATA[");
        sb.append(getPsDtlsPriceToleranceIntrvl());
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
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandMasterSid</column-name><column-value><![CDATA[");
        sb.append(getBrandMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsPrice</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>usersSid</column-name><column-value><![CDATA[");
        sb.append(getUsersSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
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
            "<column><column-name>priceProtectionPriceType</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsBasePrice</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsBasePrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsRevisionDate</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsRevisionDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nep</column-name><column-value><![CDATA[");
        sb.append(getNep());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsPriceTolerance</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsPriceTolerance());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceProtectionStatus</column-name><column-value><![CDATA[");
        sb.append(getPriceProtectionStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDtlsContPriceStartdate</column-name><column-value><![CDATA[");
        sb.append(getPsDtlsContPriceStartdate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resetEligible</column-name><column-value><![CDATA[");
        sb.append(getResetEligible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netPriceType</column-name><column-value><![CDATA[");
        sb.append(getNetPriceType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>operation</column-name><column-value><![CDATA[");
        sb.append(getOperation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpModelSid</column-name><column-value><![CDATA[");
        sb.append(getCfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDetailsPricPrtcnEddate</column-name><column-value><![CDATA[");
        sb.append(getPsDetailsPricPrtcnEddate());
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
            "<column><column-name>netBasePriceFormulaNo</column-name><column-value><![CDATA[");
        sb.append(getNetBasePriceFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netBasePriceFormulaName</column-name><column-value><![CDATA[");
        sb.append(getNetBasePriceFormulaName());
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
            "<column><column-name>netSubsequentPriceFormulaNo</column-name><column-value><![CDATA[");
        sb.append(getNetSubsequentPriceFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSubsequentPriceFormulaName</column-name><column-value><![CDATA[");
        sb.append(getNetSubsequentPriceFormulaName());
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
            "<column><column-name>netResetPriceFormulaNo</column-name><column-value><![CDATA[");
        sb.append(getNetResetPriceFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netResetPriceFormulaName</column-name><column-value><![CDATA[");
        sb.append(getNetResetPriceFormulaName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
