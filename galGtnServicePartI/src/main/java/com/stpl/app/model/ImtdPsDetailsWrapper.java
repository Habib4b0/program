package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImtdPsDetails}.
 * </p>
 *
 * @author
 * @see ImtdPsDetails
 * @generated
 */
public class ImtdPsDetailsWrapper implements ImtdPsDetails,
    ModelWrapper<ImtdPsDetails> {
    private ImtdPsDetails _imtdPsDetails;

    public ImtdPsDetailsWrapper(ImtdPsDetails imtdPsDetails) {
        _imtdPsDetails = imtdPsDetails;
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

    /**
    * Returns the primary key of this imtd ps details.
    *
    * @return the primary key of this imtd ps details
    */
    @Override
    public int getPrimaryKey() {
        return _imtdPsDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this imtd ps details.
    *
    * @param primaryKey the primary key of this imtd ps details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _imtdPsDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ps details modified date of this imtd ps details.
    *
    * @return the ps details modified date of this imtd ps details
    */
    @Override
    public java.util.Date getPsDetailsModifiedDate() {
        return _imtdPsDetails.getPsDetailsModifiedDate();
    }

    /**
    * Sets the ps details modified date of this imtd ps details.
    *
    * @param psDetailsModifiedDate the ps details modified date of this imtd ps details
    */
    @Override
    public void setPsDetailsModifiedDate(java.util.Date psDetailsModifiedDate) {
        _imtdPsDetails.setPsDetailsModifiedDate(psDetailsModifiedDate);
    }

    /**
    * Returns the ps details suggested price of this imtd ps details.
    *
    * @return the ps details suggested price of this imtd ps details
    */
    @Override
    public double getPsDetailsSuggestedPrice() {
        return _imtdPsDetails.getPsDetailsSuggestedPrice();
    }

    /**
    * Sets the ps details suggested price of this imtd ps details.
    *
    * @param psDetailsSuggestedPrice the ps details suggested price of this imtd ps details
    */
    @Override
    public void setPsDetailsSuggestedPrice(double psDetailsSuggestedPrice) {
        _imtdPsDetails.setPsDetailsSuggestedPrice(psDetailsSuggestedPrice);
    }

    /**
    * Returns the ps details contract price of this imtd ps details.
    *
    * @return the ps details contract price of this imtd ps details
    */
    @Override
    public double getPsDetailsContractPrice() {
        return _imtdPsDetails.getPsDetailsContractPrice();
    }

    /**
    * Sets the ps details contract price of this imtd ps details.
    *
    * @param psDetailsContractPrice the ps details contract price of this imtd ps details
    */
    @Override
    public void setPsDetailsContractPrice(double psDetailsContractPrice) {
        _imtdPsDetails.setPsDetailsContractPrice(psDetailsContractPrice);
    }

    /**
    * Returns the reset date of this imtd ps details.
    *
    * @return the reset date of this imtd ps details
    */
    @Override
    public java.util.Date getResetDate() {
        return _imtdPsDetails.getResetDate();
    }

    /**
    * Sets the reset date of this imtd ps details.
    *
    * @param resetDate the reset date of this imtd ps details
    */
    @Override
    public void setResetDate(java.util.Date resetDate) {
        _imtdPsDetails.setResetDate(resetDate);
    }

    /**
    * Returns the ps details attached status of this imtd ps details.
    *
    * @return the ps details attached status of this imtd ps details
    */
    @Override
    public int getPsDetailsAttachedStatus() {
        return _imtdPsDetails.getPsDetailsAttachedStatus();
    }

    /**
    * Sets the ps details attached status of this imtd ps details.
    *
    * @param psDetailsAttachedStatus the ps details attached status of this imtd ps details
    */
    @Override
    public void setPsDetailsAttachedStatus(int psDetailsAttachedStatus) {
        _imtdPsDetails.setPsDetailsAttachedStatus(psDetailsAttachedStatus);
    }

    /**
    * Returns the imtd ps details sid of this imtd ps details.
    *
    * @return the imtd ps details sid of this imtd ps details
    */
    @Override
    public int getImtdPsDetailsSid() {
        return _imtdPsDetails.getImtdPsDetailsSid();
    }

    /**
    * Sets the imtd ps details sid of this imtd ps details.
    *
    * @param imtdPsDetailsSid the imtd ps details sid of this imtd ps details
    */
    @Override
    public void setImtdPsDetailsSid(int imtdPsDetailsSid) {
        _imtdPsDetails.setImtdPsDetailsSid(imtdPsDetailsSid);
    }

    /**
    * Returns the modified date of this imtd ps details.
    *
    * @return the modified date of this imtd ps details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _imtdPsDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this imtd ps details.
    *
    * @param modifiedDate the modified date of this imtd ps details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _imtdPsDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the ps details created by of this imtd ps details.
    *
    * @return the ps details created by of this imtd ps details
    */
    @Override
    public int getPsDetailsCreatedBy() {
        return _imtdPsDetails.getPsDetailsCreatedBy();
    }

    /**
    * Sets the ps details created by of this imtd ps details.
    *
    * @param psDetailsCreatedBy the ps details created by of this imtd ps details
    */
    @Override
    public void setPsDetailsCreatedBy(int psDetailsCreatedBy) {
        _imtdPsDetails.setPsDetailsCreatedBy(psDetailsCreatedBy);
    }

    /**
    * Returns the contract master sid of this imtd ps details.
    *
    * @return the contract master sid of this imtd ps details
    */
    @Override
    public int getContractMasterSid() {
        return _imtdPsDetails.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this imtd ps details.
    *
    * @param contractMasterSid the contract master sid of this imtd ps details
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _imtdPsDetails.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the ps dtls cont price enddate of this imtd ps details.
    *
    * @return the ps dtls cont price enddate of this imtd ps details
    */
    @Override
    public java.util.Date getPsDtlsContPriceEnddate() {
        return _imtdPsDetails.getPsDtlsContPriceEnddate();
    }

    /**
    * Sets the ps dtls cont price enddate of this imtd ps details.
    *
    * @param psDtlsContPriceEnddate the ps dtls cont price enddate of this imtd ps details
    */
    @Override
    public void setPsDtlsContPriceEnddate(java.util.Date psDtlsContPriceEnddate) {
        _imtdPsDetails.setPsDtlsContPriceEnddate(psDtlsContPriceEnddate);
    }

    /**
    * Returns the ps details pric prtcn stdate of this imtd ps details.
    *
    * @return the ps details pric prtcn stdate of this imtd ps details
    */
    @Override
    public java.util.Date getPsDetailsPricPrtcnStdate() {
        return _imtdPsDetails.getPsDetailsPricPrtcnStdate();
    }

    /**
    * Sets the ps details pric prtcn stdate of this imtd ps details.
    *
    * @param psDetailsPricPrtcnStdate the ps details pric prtcn stdate of this imtd ps details
    */
    @Override
    public void setPsDetailsPricPrtcnStdate(
        java.util.Date psDetailsPricPrtcnStdate) {
        _imtdPsDetails.setPsDetailsPricPrtcnStdate(psDetailsPricPrtcnStdate);
    }

    /**
    * Returns the imtd created date of this imtd ps details.
    *
    * @return the imtd created date of this imtd ps details
    */
    @Override
    public java.util.Date getImtdCreatedDate() {
        return _imtdPsDetails.getImtdCreatedDate();
    }

    /**
    * Sets the imtd created date of this imtd ps details.
    *
    * @param imtdCreatedDate the imtd created date of this imtd ps details
    */
    @Override
    public void setImtdCreatedDate(java.util.Date imtdCreatedDate) {
        _imtdPsDetails.setImtdCreatedDate(imtdCreatedDate);
    }

    /**
    * Returns the net price type formula of this imtd ps details.
    *
    * @return the net price type formula of this imtd ps details
    */
    @Override
    public java.lang.String getNetPriceTypeFormula() {
        return _imtdPsDetails.getNetPriceTypeFormula();
    }

    /**
    * Sets the net price type formula of this imtd ps details.
    *
    * @param netPriceTypeFormula the net price type formula of this imtd ps details
    */
    @Override
    public void setNetPriceTypeFormula(java.lang.String netPriceTypeFormula) {
        _imtdPsDetails.setNetPriceTypeFormula(netPriceTypeFormula);
    }

    /**
    * Returns the modified by of this imtd ps details.
    *
    * @return the modified by of this imtd ps details
    */
    @Override
    public int getModifiedBy() {
        return _imtdPsDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this imtd ps details.
    *
    * @param modifiedBy the modified by of this imtd ps details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _imtdPsDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the max incremental change of this imtd ps details.
    *
    * @return the max incremental change of this imtd ps details
    */
    @Override
    public double getMaxIncrementalChange() {
        return _imtdPsDetails.getMaxIncrementalChange();
    }

    /**
    * Sets the max incremental change of this imtd ps details.
    *
    * @param maxIncrementalChange the max incremental change of this imtd ps details
    */
    @Override
    public void setMaxIncrementalChange(double maxIncrementalChange) {
        _imtdPsDetails.setMaxIncrementalChange(maxIncrementalChange);
    }

    /**
    * Returns the ps details price plan ID of this imtd ps details.
    *
    * @return the ps details price plan ID of this imtd ps details
    */
    @Override
    public java.lang.String getPsDetailsPricePlanId() {
        return _imtdPsDetails.getPsDetailsPricePlanId();
    }

    /**
    * Sets the ps details price plan ID of this imtd ps details.
    *
    * @param psDetailsPricePlanId the ps details price plan ID of this imtd ps details
    */
    @Override
    public void setPsDetailsPricePlanId(java.lang.String psDetailsPricePlanId) {
        _imtdPsDetails.setPsDetailsPricePlanId(psDetailsPricePlanId);
    }

    /**
    * Returns the check record of this imtd ps details.
    *
    * @return the check record of this imtd ps details
    */
    @Override
    public boolean getCheckRecord() {
        return _imtdPsDetails.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this imtd ps details is check record.
    *
    * @return <code>true</code> if this imtd ps details is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _imtdPsDetails.isCheckRecord();
    }

    /**
    * Sets whether this imtd ps details is check record.
    *
    * @param checkRecord the check record of this imtd ps details
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _imtdPsDetails.setCheckRecord(checkRecord);
    }

    /**
    * Returns the ps dtls price tolerance freq of this imtd ps details.
    *
    * @return the ps dtls price tolerance freq of this imtd ps details
    */
    @Override
    public int getPsDtlsPriceToleranceFreq() {
        return _imtdPsDetails.getPsDtlsPriceToleranceFreq();
    }

    /**
    * Sets the ps dtls price tolerance freq of this imtd ps details.
    *
    * @param psDtlsPriceToleranceFreq the ps dtls price tolerance freq of this imtd ps details
    */
    @Override
    public void setPsDtlsPriceToleranceFreq(int psDtlsPriceToleranceFreq) {
        _imtdPsDetails.setPsDtlsPriceToleranceFreq(psDtlsPriceToleranceFreq);
    }

    /**
    * Returns the item name of this imtd ps details.
    *
    * @return the item name of this imtd ps details
    */
    @Override
    public java.lang.String getItemName() {
        return _imtdPsDetails.getItemName();
    }

    /**
    * Sets the item name of this imtd ps details.
    *
    * @param itemName the item name of this imtd ps details
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _imtdPsDetails.setItemName(itemName);
    }

    /**
    * Returns the session ID of this imtd ps details.
    *
    * @return the session ID of this imtd ps details
    */
    @Override
    public java.lang.String getSessionId() {
        return _imtdPsDetails.getSessionId();
    }

    /**
    * Sets the session ID of this imtd ps details.
    *
    * @param sessionId the session ID of this imtd ps details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _imtdPsDetails.setSessionId(sessionId);
    }

    /**
    * Returns the reset frequency of this imtd ps details.
    *
    * @return the reset frequency of this imtd ps details
    */
    @Override
    public int getResetFrequency() {
        return _imtdPsDetails.getResetFrequency();
    }

    /**
    * Sets the reset frequency of this imtd ps details.
    *
    * @param resetFrequency the reset frequency of this imtd ps details
    */
    @Override
    public void setResetFrequency(int resetFrequency) {
        _imtdPsDetails.setResetFrequency(resetFrequency);
    }

    /**
    * Returns the ps dtls price tolerance type of this imtd ps details.
    *
    * @return the ps dtls price tolerance type of this imtd ps details
    */
    @Override
    public int getPsDtlsPriceToleranceType() {
        return _imtdPsDetails.getPsDtlsPriceToleranceType();
    }

    /**
    * Sets the ps dtls price tolerance type of this imtd ps details.
    *
    * @param psDtlsPriceToleranceType the ps dtls price tolerance type of this imtd ps details
    */
    @Override
    public void setPsDtlsPriceToleranceType(int psDtlsPriceToleranceType) {
        _imtdPsDetails.setPsDtlsPriceToleranceType(psDtlsPriceToleranceType);
    }

    /**
    * Returns the ps details pricetype of this imtd ps details.
    *
    * @return the ps details pricetype of this imtd ps details
    */
    @Override
    public int getPsDetailsPricetype() {
        return _imtdPsDetails.getPsDetailsPricetype();
    }

    /**
    * Sets the ps details pricetype of this imtd ps details.
    *
    * @param psDetailsPricetype the ps details pricetype of this imtd ps details
    */
    @Override
    public void setPsDetailsPricetype(int psDetailsPricetype) {
        _imtdPsDetails.setPsDetailsPricetype(psDetailsPricetype);
    }

    /**
    * Returns the ps details price revision of this imtd ps details.
    *
    * @return the ps details price revision of this imtd ps details
    */
    @Override
    public double getPsDetailsPriceRevision() {
        return _imtdPsDetails.getPsDetailsPriceRevision();
    }

    /**
    * Sets the ps details price revision of this imtd ps details.
    *
    * @param psDetailsPriceRevision the ps details price revision of this imtd ps details
    */
    @Override
    public void setPsDetailsPriceRevision(double psDetailsPriceRevision) {
        _imtdPsDetails.setPsDetailsPriceRevision(psDetailsPriceRevision);
    }

    /**
    * Returns the reset interval of this imtd ps details.
    *
    * @return the reset interval of this imtd ps details
    */
    @Override
    public int getResetInterval() {
        return _imtdPsDetails.getResetInterval();
    }

    /**
    * Sets the reset interval of this imtd ps details.
    *
    * @param resetInterval the reset interval of this imtd ps details
    */
    @Override
    public void setResetInterval(int resetInterval) {
        _imtdPsDetails.setResetInterval(resetInterval);
    }

    /**
    * Returns the ifp no of this imtd ps details.
    *
    * @return the ifp no of this imtd ps details
    */
    @Override
    public java.lang.String getIfpNo() {
        return _imtdPsDetails.getIfpNo();
    }

    /**
    * Sets the ifp no of this imtd ps details.
    *
    * @param ifpNo the ifp no of this imtd ps details
    */
    @Override
    public void setIfpNo(java.lang.String ifpNo) {
        _imtdPsDetails.setIfpNo(ifpNo);
    }

    /**
    * Returns the ps details attached date of this imtd ps details.
    *
    * @return the ps details attached date of this imtd ps details
    */
    @Override
    public java.util.Date getPsDetailsAttachedDate() {
        return _imtdPsDetails.getPsDetailsAttachedDate();
    }

    /**
    * Sets the ps details attached date of this imtd ps details.
    *
    * @param psDetailsAttachedDate the ps details attached date of this imtd ps details
    */
    @Override
    public void setPsDetailsAttachedDate(java.util.Date psDetailsAttachedDate) {
        _imtdPsDetails.setPsDetailsAttachedDate(psDetailsAttachedDate);
    }

    /**
    * Returns the nep formula of this imtd ps details.
    *
    * @return the nep formula of this imtd ps details
    */
    @Override
    public int getNepFormula() {
        return _imtdPsDetails.getNepFormula();
    }

    /**
    * Sets the nep formula of this imtd ps details.
    *
    * @param nepFormula the nep formula of this imtd ps details
    */
    @Override
    public void setNepFormula(int nepFormula) {
        _imtdPsDetails.setNepFormula(nepFormula);
    }

    /**
    * Returns the ps details modified by of this imtd ps details.
    *
    * @return the ps details modified by of this imtd ps details
    */
    @Override
    public int getPsDetailsModifiedBy() {
        return _imtdPsDetails.getPsDetailsModifiedBy();
    }

    /**
    * Sets the ps details modified by of this imtd ps details.
    *
    * @param psDetailsModifiedBy the ps details modified by of this imtd ps details
    */
    @Override
    public void setPsDetailsModifiedBy(int psDetailsModifiedBy) {
        _imtdPsDetails.setPsDetailsModifiedBy(psDetailsModifiedBy);
    }

    /**
    * Returns the ps dtls price tolerance intrvl of this imtd ps details.
    *
    * @return the ps dtls price tolerance intrvl of this imtd ps details
    */
    @Override
    public int getPsDtlsPriceToleranceIntrvl() {
        return _imtdPsDetails.getPsDtlsPriceToleranceIntrvl();
    }

    /**
    * Sets the ps dtls price tolerance intrvl of this imtd ps details.
    *
    * @param psDtlsPriceToleranceIntrvl the ps dtls price tolerance intrvl of this imtd ps details
    */
    @Override
    public void setPsDtlsPriceToleranceIntrvl(int psDtlsPriceToleranceIntrvl) {
        _imtdPsDetails.setPsDtlsPriceToleranceIntrvl(psDtlsPriceToleranceIntrvl);
    }

    /**
    * Returns the item master sid of this imtd ps details.
    *
    * @return the item master sid of this imtd ps details
    */
    @Override
    public int getItemMasterSid() {
        return _imtdPsDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this imtd ps details.
    *
    * @param itemMasterSid the item master sid of this imtd ps details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _imtdPsDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the reset type of this imtd ps details.
    *
    * @return the reset type of this imtd ps details
    */
    @Override
    public int getResetType() {
        return _imtdPsDetails.getResetType();
    }

    /**
    * Sets the reset type of this imtd ps details.
    *
    * @param resetType the reset type of this imtd ps details
    */
    @Override
    public void setResetType(int resetType) {
        _imtdPsDetails.setResetType(resetType);
    }

    /**
    * Returns the item ID of this imtd ps details.
    *
    * @return the item ID of this imtd ps details
    */
    @Override
    public java.lang.String getItemId() {
        return _imtdPsDetails.getItemId();
    }

    /**
    * Sets the item ID of this imtd ps details.
    *
    * @param itemId the item ID of this imtd ps details
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _imtdPsDetails.setItemId(itemId);
    }

    /**
    * Returns the status of this imtd ps details.
    *
    * @return the status of this imtd ps details
    */
    @Override
    public int getStatus() {
        return _imtdPsDetails.getStatus();
    }

    /**
    * Sets the status of this imtd ps details.
    *
    * @param status the status of this imtd ps details
    */
    @Override
    public void setStatus(int status) {
        _imtdPsDetails.setStatus(status);
    }

    /**
    * Returns the brand master sid of this imtd ps details.
    *
    * @return the brand master sid of this imtd ps details
    */
    @Override
    public int getBrandMasterSid() {
        return _imtdPsDetails.getBrandMasterSid();
    }

    /**
    * Sets the brand master sid of this imtd ps details.
    *
    * @param brandMasterSid the brand master sid of this imtd ps details
    */
    @Override
    public void setBrandMasterSid(int brandMasterSid) {
        _imtdPsDetails.setBrandMasterSid(brandMasterSid);
    }

    /**
    * Returns the ps details price of this imtd ps details.
    *
    * @return the ps details price of this imtd ps details
    */
    @Override
    public double getPsDetailsPrice() {
        return _imtdPsDetails.getPsDetailsPrice();
    }

    /**
    * Sets the ps details price of this imtd ps details.
    *
    * @param psDetailsPrice the ps details price of this imtd ps details
    */
    @Override
    public void setPsDetailsPrice(double psDetailsPrice) {
        _imtdPsDetails.setPsDetailsPrice(psDetailsPrice);
    }

    /**
    * Returns the ps details created date of this imtd ps details.
    *
    * @return the ps details created date of this imtd ps details
    */
    @Override
    public java.util.Date getPsDetailsCreatedDate() {
        return _imtdPsDetails.getPsDetailsCreatedDate();
    }

    /**
    * Sets the ps details created date of this imtd ps details.
    *
    * @param psDetailsCreatedDate the ps details created date of this imtd ps details
    */
    @Override
    public void setPsDetailsCreatedDate(java.util.Date psDetailsCreatedDate) {
        _imtdPsDetails.setPsDetailsCreatedDate(psDetailsCreatedDate);
    }

    /**
    * Returns the users sid of this imtd ps details.
    *
    * @return the users sid of this imtd ps details
    */
    @Override
    public int getUsersSid() {
        return _imtdPsDetails.getUsersSid();
    }

    /**
    * Sets the users sid of this imtd ps details.
    *
    * @param usersSid the users sid of this imtd ps details
    */
    @Override
    public void setUsersSid(int usersSid) {
        _imtdPsDetails.setUsersSid(usersSid);
    }

    /**
    * Returns the created by of this imtd ps details.
    *
    * @return the created by of this imtd ps details
    */
    @Override
    public int getCreatedBy() {
        return _imtdPsDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this imtd ps details.
    *
    * @param createdBy the created by of this imtd ps details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _imtdPsDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this imtd ps details.
    *
    * @return the created date of this imtd ps details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _imtdPsDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this imtd ps details.
    *
    * @param createdDate the created date of this imtd ps details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _imtdPsDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the ps details sid of this imtd ps details.
    *
    * @return the ps details sid of this imtd ps details
    */
    @Override
    public int getPsDetailsSid() {
        return _imtdPsDetails.getPsDetailsSid();
    }

    /**
    * Sets the ps details sid of this imtd ps details.
    *
    * @param psDetailsSid the ps details sid of this imtd ps details
    */
    @Override
    public void setPsDetailsSid(int psDetailsSid) {
        _imtdPsDetails.setPsDetailsSid(psDetailsSid);
    }

    /**
    * Returns the ps model sid of this imtd ps details.
    *
    * @return the ps model sid of this imtd ps details
    */
    @Override
    public int getPsModelSid() {
        return _imtdPsDetails.getPsModelSid();
    }

    /**
    * Sets the ps model sid of this imtd ps details.
    *
    * @param psModelSid the ps model sid of this imtd ps details
    */
    @Override
    public void setPsModelSid(int psModelSid) {
        _imtdPsDetails.setPsModelSid(psModelSid);
    }

    /**
    * Returns the price protection price type of this imtd ps details.
    *
    * @return the price protection price type of this imtd ps details
    */
    @Override
    public int getPriceProtectionPriceType() {
        return _imtdPsDetails.getPriceProtectionPriceType();
    }

    /**
    * Sets the price protection price type of this imtd ps details.
    *
    * @param priceProtectionPriceType the price protection price type of this imtd ps details
    */
    @Override
    public void setPriceProtectionPriceType(int priceProtectionPriceType) {
        _imtdPsDetails.setPriceProtectionPriceType(priceProtectionPriceType);
    }

    /**
    * Returns the ps details base price of this imtd ps details.
    *
    * @return the ps details base price of this imtd ps details
    */
    @Override
    public double getPsDetailsBasePrice() {
        return _imtdPsDetails.getPsDetailsBasePrice();
    }

    /**
    * Sets the ps details base price of this imtd ps details.
    *
    * @param psDetailsBasePrice the ps details base price of this imtd ps details
    */
    @Override
    public void setPsDetailsBasePrice(double psDetailsBasePrice) {
        _imtdPsDetails.setPsDetailsBasePrice(psDetailsBasePrice);
    }

    /**
    * Returns the item no of this imtd ps details.
    *
    * @return the item no of this imtd ps details
    */
    @Override
    public java.lang.String getItemNo() {
        return _imtdPsDetails.getItemNo();
    }

    /**
    * Sets the item no of this imtd ps details.
    *
    * @param itemNo the item no of this imtd ps details
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _imtdPsDetails.setItemNo(itemNo);
    }

    /**
    * Returns the ifp model sid of this imtd ps details.
    *
    * @return the ifp model sid of this imtd ps details
    */
    @Override
    public int getIfpModelSid() {
        return _imtdPsDetails.getIfpModelSid();
    }

    /**
    * Sets the ifp model sid of this imtd ps details.
    *
    * @param ifpModelSid the ifp model sid of this imtd ps details
    */
    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _imtdPsDetails.setIfpModelSid(ifpModelSid);
    }

    /**
    * Returns the ps details revision date of this imtd ps details.
    *
    * @return the ps details revision date of this imtd ps details
    */
    @Override
    public java.util.Date getPsDetailsRevisionDate() {
        return _imtdPsDetails.getPsDetailsRevisionDate();
    }

    /**
    * Sets the ps details revision date of this imtd ps details.
    *
    * @param psDetailsRevisionDate the ps details revision date of this imtd ps details
    */
    @Override
    public void setPsDetailsRevisionDate(java.util.Date psDetailsRevisionDate) {
        _imtdPsDetails.setPsDetailsRevisionDate(psDetailsRevisionDate);
    }

    /**
    * Returns the nep of this imtd ps details.
    *
    * @return the nep of this imtd ps details
    */
    @Override
    public double getNep() {
        return _imtdPsDetails.getNep();
    }

    /**
    * Sets the nep of this imtd ps details.
    *
    * @param nep the nep of this imtd ps details
    */
    @Override
    public void setNep(double nep) {
        _imtdPsDetails.setNep(nep);
    }

    /**
    * Returns the ps details price tolerance of this imtd ps details.
    *
    * @return the ps details price tolerance of this imtd ps details
    */
    @Override
    public double getPsDetailsPriceTolerance() {
        return _imtdPsDetails.getPsDetailsPriceTolerance();
    }

    /**
    * Sets the ps details price tolerance of this imtd ps details.
    *
    * @param psDetailsPriceTolerance the ps details price tolerance of this imtd ps details
    */
    @Override
    public void setPsDetailsPriceTolerance(double psDetailsPriceTolerance) {
        _imtdPsDetails.setPsDetailsPriceTolerance(psDetailsPriceTolerance);
    }

    /**
    * Returns the price protection status of this imtd ps details.
    *
    * @return the price protection status of this imtd ps details
    */
    @Override
    public int getPriceProtectionStatus() {
        return _imtdPsDetails.getPriceProtectionStatus();
    }

    /**
    * Sets the price protection status of this imtd ps details.
    *
    * @param priceProtectionStatus the price protection status of this imtd ps details
    */
    @Override
    public void setPriceProtectionStatus(int priceProtectionStatus) {
        _imtdPsDetails.setPriceProtectionStatus(priceProtectionStatus);
    }

    /**
    * Returns the ps dtls cont price startdate of this imtd ps details.
    *
    * @return the ps dtls cont price startdate of this imtd ps details
    */
    @Override
    public java.util.Date getPsDtlsContPriceStartdate() {
        return _imtdPsDetails.getPsDtlsContPriceStartdate();
    }

    /**
    * Sets the ps dtls cont price startdate of this imtd ps details.
    *
    * @param psDtlsContPriceStartdate the ps dtls cont price startdate of this imtd ps details
    */
    @Override
    public void setPsDtlsContPriceStartdate(
        java.util.Date psDtlsContPriceStartdate) {
        _imtdPsDetails.setPsDtlsContPriceStartdate(psDtlsContPriceStartdate);
    }

    /**
    * Returns the reset eligible of this imtd ps details.
    *
    * @return the reset eligible of this imtd ps details
    */
    @Override
    public int getResetEligible() {
        return _imtdPsDetails.getResetEligible();
    }

    /**
    * Sets the reset eligible of this imtd ps details.
    *
    * @param resetEligible the reset eligible of this imtd ps details
    */
    @Override
    public void setResetEligible(int resetEligible) {
        _imtdPsDetails.setResetEligible(resetEligible);
    }

    /**
    * Returns the net price type of this imtd ps details.
    *
    * @return the net price type of this imtd ps details
    */
    @Override
    public int getNetPriceType() {
        return _imtdPsDetails.getNetPriceType();
    }

    /**
    * Sets the net price type of this imtd ps details.
    *
    * @param netPriceType the net price type of this imtd ps details
    */
    @Override
    public void setNetPriceType(int netPriceType) {
        _imtdPsDetails.setNetPriceType(netPriceType);
    }

    /**
    * Returns the operation of this imtd ps details.
    *
    * @return the operation of this imtd ps details
    */
    @Override
    public java.lang.String getOperation() {
        return _imtdPsDetails.getOperation();
    }

    /**
    * Sets the operation of this imtd ps details.
    *
    * @param operation the operation of this imtd ps details
    */
    @Override
    public void setOperation(java.lang.String operation) {
        _imtdPsDetails.setOperation(operation);
    }

    /**
    * Returns the cfp model sid of this imtd ps details.
    *
    * @return the cfp model sid of this imtd ps details
    */
    @Override
    public int getCfpModelSid() {
        return _imtdPsDetails.getCfpModelSid();
    }

    /**
    * Sets the cfp model sid of this imtd ps details.
    *
    * @param cfpModelSid the cfp model sid of this imtd ps details
    */
    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _imtdPsDetails.setCfpModelSid(cfpModelSid);
    }

    /**
    * Returns the ps details pric prtcn eddate of this imtd ps details.
    *
    * @return the ps details pric prtcn eddate of this imtd ps details
    */
    @Override
    public java.util.Date getPsDetailsPricPrtcnEddate() {
        return _imtdPsDetails.getPsDetailsPricPrtcnEddate();
    }

    /**
    * Sets the ps details pric prtcn eddate of this imtd ps details.
    *
    * @param psDetailsPricPrtcnEddate the ps details pric prtcn eddate of this imtd ps details
    */
    @Override
    public void setPsDetailsPricPrtcnEddate(
        java.util.Date psDetailsPricPrtcnEddate) {
        _imtdPsDetails.setPsDetailsPricPrtcnEddate(psDetailsPricPrtcnEddate);
    }

    /**
    * Returns the base price type of this imtd ps details.
    *
    * @return the base price type of this imtd ps details
    */
    @Override
    public int getBasePriceType() {
        return _imtdPsDetails.getBasePriceType();
    }

    /**
    * Sets the base price type of this imtd ps details.
    *
    * @param basePriceType the base price type of this imtd ps details
    */
    @Override
    public void setBasePriceType(int basePriceType) {
        _imtdPsDetails.setBasePriceType(basePriceType);
    }

    /**
    * Returns the base price entry of this imtd ps details.
    *
    * @return the base price entry of this imtd ps details
    */
    @Override
    public double getBasePriceEntry() {
        return _imtdPsDetails.getBasePriceEntry();
    }

    /**
    * Sets the base price entry of this imtd ps details.
    *
    * @param basePriceEntry the base price entry of this imtd ps details
    */
    @Override
    public void setBasePriceEntry(double basePriceEntry) {
        _imtdPsDetails.setBasePriceEntry(basePriceEntry);
    }

    /**
    * Returns the base price date of this imtd ps details.
    *
    * @return the base price date of this imtd ps details
    */
    @Override
    public java.util.Date getBasePriceDate() {
        return _imtdPsDetails.getBasePriceDate();
    }

    /**
    * Sets the base price date of this imtd ps details.
    *
    * @param basePriceDate the base price date of this imtd ps details
    */
    @Override
    public void setBasePriceDate(java.util.Date basePriceDate) {
        _imtdPsDetails.setBasePriceDate(basePriceDate);
    }

    /**
    * Returns the base price ddlb of this imtd ps details.
    *
    * @return the base price ddlb of this imtd ps details
    */
    @Override
    public int getBasePriceDdlb() {
        return _imtdPsDetails.getBasePriceDdlb();
    }

    /**
    * Sets the base price ddlb of this imtd ps details.
    *
    * @param basePriceDdlb the base price ddlb of this imtd ps details
    */
    @Override
    public void setBasePriceDdlb(int basePriceDdlb) {
        _imtdPsDetails.setBasePriceDdlb(basePriceDdlb);
    }

    /**
    * Returns the net base price of this imtd ps details.
    *
    * @return the net base price of this imtd ps details
    */
    @Override
    public int getNetBasePrice() {
        return _imtdPsDetails.getNetBasePrice();
    }

    /**
    * Sets the net base price of this imtd ps details.
    *
    * @param netBasePrice the net base price of this imtd ps details
    */
    @Override
    public void setNetBasePrice(int netBasePrice) {
        _imtdPsDetails.setNetBasePrice(netBasePrice);
    }

    /**
    * Returns the net base price formula ID of this imtd ps details.
    *
    * @return the net base price formula ID of this imtd ps details
    */
    @Override
    public int getNetBasePriceFormulaId() {
        return _imtdPsDetails.getNetBasePriceFormulaId();
    }

    /**
    * Sets the net base price formula ID of this imtd ps details.
    *
    * @param netBasePriceFormulaId the net base price formula ID of this imtd ps details
    */
    @Override
    public void setNetBasePriceFormulaId(int netBasePriceFormulaId) {
        _imtdPsDetails.setNetBasePriceFormulaId(netBasePriceFormulaId);
    }

    /**
    * Returns the net base price formula no of this imtd ps details.
    *
    * @return the net base price formula no of this imtd ps details
    */
    @Override
    public java.lang.String getNetBasePriceFormulaNo() {
        return _imtdPsDetails.getNetBasePriceFormulaNo();
    }

    /**
    * Sets the net base price formula no of this imtd ps details.
    *
    * @param netBasePriceFormulaNo the net base price formula no of this imtd ps details
    */
    @Override
    public void setNetBasePriceFormulaNo(java.lang.String netBasePriceFormulaNo) {
        _imtdPsDetails.setNetBasePriceFormulaNo(netBasePriceFormulaNo);
    }

    /**
    * Returns the net base price formula name of this imtd ps details.
    *
    * @return the net base price formula name of this imtd ps details
    */
    @Override
    public java.lang.String getNetBasePriceFormulaName() {
        return _imtdPsDetails.getNetBasePriceFormulaName();
    }

    /**
    * Sets the net base price formula name of this imtd ps details.
    *
    * @param netBasePriceFormulaName the net base price formula name of this imtd ps details
    */
    @Override
    public void setNetBasePriceFormulaName(
        java.lang.String netBasePriceFormulaName) {
        _imtdPsDetails.setNetBasePriceFormulaName(netBasePriceFormulaName);
    }

    /**
    * Returns the subsequent period price type of this imtd ps details.
    *
    * @return the subsequent period price type of this imtd ps details
    */
    @Override
    public int getSubsequentPeriodPriceType() {
        return _imtdPsDetails.getSubsequentPeriodPriceType();
    }

    /**
    * Sets the subsequent period price type of this imtd ps details.
    *
    * @param subsequentPeriodPriceType the subsequent period price type of this imtd ps details
    */
    @Override
    public void setSubsequentPeriodPriceType(int subsequentPeriodPriceType) {
        _imtdPsDetails.setSubsequentPeriodPriceType(subsequentPeriodPriceType);
    }

    /**
    * Returns the net subsequent period price of this imtd ps details.
    *
    * @return the net subsequent period price of this imtd ps details
    */
    @Override
    public int getNetSubsequentPeriodPrice() {
        return _imtdPsDetails.getNetSubsequentPeriodPrice();
    }

    /**
    * Sets the net subsequent period price of this imtd ps details.
    *
    * @param netSubsequentPeriodPrice the net subsequent period price of this imtd ps details
    */
    @Override
    public void setNetSubsequentPeriodPrice(int netSubsequentPeriodPrice) {
        _imtdPsDetails.setNetSubsequentPeriodPrice(netSubsequentPeriodPrice);
    }

    /**
    * Returns the net subsequent price formula ID of this imtd ps details.
    *
    * @return the net subsequent price formula ID of this imtd ps details
    */
    @Override
    public int getNetSubsequentPriceFormulaId() {
        return _imtdPsDetails.getNetSubsequentPriceFormulaId();
    }

    /**
    * Sets the net subsequent price formula ID of this imtd ps details.
    *
    * @param netSubsequentPriceFormulaId the net subsequent price formula ID of this imtd ps details
    */
    @Override
    public void setNetSubsequentPriceFormulaId(int netSubsequentPriceFormulaId) {
        _imtdPsDetails.setNetSubsequentPriceFormulaId(netSubsequentPriceFormulaId);
    }

    /**
    * Returns the net subsequent price formula no of this imtd ps details.
    *
    * @return the net subsequent price formula no of this imtd ps details
    */
    @Override
    public java.lang.String getNetSubsequentPriceFormulaNo() {
        return _imtdPsDetails.getNetSubsequentPriceFormulaNo();
    }

    /**
    * Sets the net subsequent price formula no of this imtd ps details.
    *
    * @param netSubsequentPriceFormulaNo the net subsequent price formula no of this imtd ps details
    */
    @Override
    public void setNetSubsequentPriceFormulaNo(
        java.lang.String netSubsequentPriceFormulaNo) {
        _imtdPsDetails.setNetSubsequentPriceFormulaNo(netSubsequentPriceFormulaNo);
    }

    /**
    * Returns the net subsequent price formula name of this imtd ps details.
    *
    * @return the net subsequent price formula name of this imtd ps details
    */
    @Override
    public java.lang.String getNetSubsequentPriceFormulaName() {
        return _imtdPsDetails.getNetSubsequentPriceFormulaName();
    }

    /**
    * Sets the net subsequent price formula name of this imtd ps details.
    *
    * @param netSubsequentPriceFormulaName the net subsequent price formula name of this imtd ps details
    */
    @Override
    public void setNetSubsequentPriceFormulaName(
        java.lang.String netSubsequentPriceFormulaName) {
        _imtdPsDetails.setNetSubsequentPriceFormulaName(netSubsequentPriceFormulaName);
    }

    /**
    * Returns the reset price type of this imtd ps details.
    *
    * @return the reset price type of this imtd ps details
    */
    @Override
    public int getResetPriceType() {
        return _imtdPsDetails.getResetPriceType();
    }

    /**
    * Sets the reset price type of this imtd ps details.
    *
    * @param resetPriceType the reset price type of this imtd ps details
    */
    @Override
    public void setResetPriceType(int resetPriceType) {
        _imtdPsDetails.setResetPriceType(resetPriceType);
    }

    /**
    * Returns the net reset price type of this imtd ps details.
    *
    * @return the net reset price type of this imtd ps details
    */
    @Override
    public int getNetResetPriceType() {
        return _imtdPsDetails.getNetResetPriceType();
    }

    /**
    * Sets the net reset price type of this imtd ps details.
    *
    * @param netResetPriceType the net reset price type of this imtd ps details
    */
    @Override
    public void setNetResetPriceType(int netResetPriceType) {
        _imtdPsDetails.setNetResetPriceType(netResetPriceType);
    }

    /**
    * Returns the net reset price formula ID of this imtd ps details.
    *
    * @return the net reset price formula ID of this imtd ps details
    */
    @Override
    public int getNetResetPriceFormulaId() {
        return _imtdPsDetails.getNetResetPriceFormulaId();
    }

    /**
    * Sets the net reset price formula ID of this imtd ps details.
    *
    * @param netResetPriceFormulaId the net reset price formula ID of this imtd ps details
    */
    @Override
    public void setNetResetPriceFormulaId(int netResetPriceFormulaId) {
        _imtdPsDetails.setNetResetPriceFormulaId(netResetPriceFormulaId);
    }

    /**
    * Returns the net reset price formula no of this imtd ps details.
    *
    * @return the net reset price formula no of this imtd ps details
    */
    @Override
    public java.lang.String getNetResetPriceFormulaNo() {
        return _imtdPsDetails.getNetResetPriceFormulaNo();
    }

    /**
    * Sets the net reset price formula no of this imtd ps details.
    *
    * @param netResetPriceFormulaNo the net reset price formula no of this imtd ps details
    */
    @Override
    public void setNetResetPriceFormulaNo(
        java.lang.String netResetPriceFormulaNo) {
        _imtdPsDetails.setNetResetPriceFormulaNo(netResetPriceFormulaNo);
    }

    /**
    * Returns the net reset price formula name of this imtd ps details.
    *
    * @return the net reset price formula name of this imtd ps details
    */
    @Override
    public java.lang.String getNetResetPriceFormulaName() {
        return _imtdPsDetails.getNetResetPriceFormulaName();
    }

    /**
    * Sets the net reset price formula name of this imtd ps details.
    *
    * @param netResetPriceFormulaName the net reset price formula name of this imtd ps details
    */
    @Override
    public void setNetResetPriceFormulaName(
        java.lang.String netResetPriceFormulaName) {
        _imtdPsDetails.setNetResetPriceFormulaName(netResetPriceFormulaName);
    }

    @Override
    public boolean isNew() {
        return _imtdPsDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _imtdPsDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _imtdPsDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _imtdPsDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _imtdPsDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _imtdPsDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _imtdPsDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _imtdPsDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _imtdPsDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _imtdPsDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _imtdPsDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImtdPsDetailsWrapper((ImtdPsDetails) _imtdPsDetails.clone());
    }

    @Override
    public int compareTo(ImtdPsDetails imtdPsDetails) {
        return _imtdPsDetails.compareTo(imtdPsDetails);
    }

    @Override
    public int hashCode() {
        return _imtdPsDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ImtdPsDetails> toCacheModel() {
        return _imtdPsDetails.toCacheModel();
    }

    @Override
    public ImtdPsDetails toEscapedModel() {
        return new ImtdPsDetailsWrapper(_imtdPsDetails.toEscapedModel());
    }

    @Override
    public ImtdPsDetails toUnescapedModel() {
        return new ImtdPsDetailsWrapper(_imtdPsDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _imtdPsDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _imtdPsDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _imtdPsDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImtdPsDetailsWrapper)) {
            return false;
        }

        ImtdPsDetailsWrapper imtdPsDetailsWrapper = (ImtdPsDetailsWrapper) obj;

        if (Validator.equals(_imtdPsDetails, imtdPsDetailsWrapper._imtdPsDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImtdPsDetails getWrappedImtdPsDetails() {
        return _imtdPsDetails;
    }

    @Override
    public ImtdPsDetails getWrappedModel() {
        return _imtdPsDetails;
    }

    @Override
    public void resetOriginalValues() {
        _imtdPsDetails.resetOriginalValues();
    }
}
