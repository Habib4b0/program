package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link GcmGlobalDetails}.
 * </p>
 *
 * @author
 * @see GcmGlobalDetails
 * @generated
 */
public class GcmGlobalDetailsWrapper implements GcmGlobalDetails,
    ModelWrapper<GcmGlobalDetails> {
    private GcmGlobalDetails _gcmGlobalDetails;

    public GcmGlobalDetailsWrapper(GcmGlobalDetails gcmGlobalDetails) {
        _gcmGlobalDetails = gcmGlobalDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return GcmGlobalDetails.class;
    }

    @Override
    public String getModelClassName() {
        return GcmGlobalDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemStatus", getItemStatus());
        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("moduleName", getModuleName());
        attributes.put("paymentFrequency", getPaymentFrequency());
        attributes.put("endDate", getEndDate());
        attributes.put("cfpStartDate", getCfpStartDate());
        attributes.put("priceProtectionStartDate", getPriceProtectionStartDate());
        attributes.put("tempItemMasterSid", getTempItemMasterSid());
        attributes.put("brandName", getBrandName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("subModuleName", getSubModuleName());
        attributes.put("theraputicClass", getTheraputicClass());
        attributes.put("gcmGlobalDetailsSid", getGcmGlobalDetailsSid());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("paymentMethod", getPaymentMethod());
        attributes.put("contractPriceEndDate", getContractPriceEndDate());
        attributes.put("psContractSid", getPsContractSid());
        attributes.put("priceProtectionEndDate", getPriceProtectionEndDate());
        attributes.put("startDate", getStartDate());
        attributes.put("screenName", getScreenName());
        attributes.put("rsContractSid", getRsContractSid());
        attributes.put("itemName", getItemName());
        attributes.put("sessionId", getSessionId());
        attributes.put("cfpStatus", getCfpStatus());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("cfpContractSid", getCfpContractSid());
        attributes.put("price", getPrice());
        attributes.put("tempEndDate", getTempEndDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemType", getItemType());
        attributes.put("forecastingType", getForecastingType());
        attributes.put("itemId", getItemId());
        attributes.put("basePrice", getBasePrice());
        attributes.put("status", getStatus());
        attributes.put("formulaName", getFormulaName());
        attributes.put("workflowMasterSid", getWorkflowMasterSid());
        attributes.put("priceTolerance", getPriceTolerance());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("tempStartDate", getTempStartDate());
        attributes.put("cfpEndDate", getCfpEndDate());
        attributes.put("psModelSid", getPsModelSid());
        attributes.put("formulaId", getFormulaId());
        attributes.put("itemNo", getItemNo());
        attributes.put("contractPrice", getContractPrice());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("priceToleranceType", getPriceToleranceType());
        attributes.put("rebateAmount", getRebateAmount());
        attributes.put("userId", getUserId());
        attributes.put("projectionMasterSid", getProjectionMasterSid());
        attributes.put("contractPriceStartDate", getContractPriceStartDate());
        attributes.put("priceToleranceFrequency", getPriceToleranceFrequency());
        attributes.put("ifpContractAttachedStatus",
            getIfpContractAttachedStatus());
        attributes.put("rebatePlanSystemId", getRebatePlanSystemId());
        attributes.put("rebatePlanName", getRebatePlanName());
        attributes.put("calendar", getCalendar());
        attributes.put("pricingQualifierSid", getPricingQualifierSid());
        attributes.put("tempStatus", getTempStatus());
        attributes.put("itemRebateEndDate", getItemRebateEndDate());
        attributes.put("priceToleranceInterval", getPriceToleranceInterval());
        attributes.put("itemRebateStartDate", getItemRebateStartDate());
        attributes.put("operation", getOperation());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("itemStatusSid", getItemStatusSid());
        attributes.put("ifpContractSid", getIfpContractSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemStatus = (String) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        String moduleName = (String) attributes.get("moduleName");

        if (moduleName != null) {
            setModuleName(moduleName);
        }

        String paymentFrequency = (String) attributes.get("paymentFrequency");

        if (paymentFrequency != null) {
            setPaymentFrequency(paymentFrequency);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Date cfpStartDate = (Date) attributes.get("cfpStartDate");

        if (cfpStartDate != null) {
            setCfpStartDate(cfpStartDate);
        }

        Date priceProtectionStartDate = (Date) attributes.get(
                "priceProtectionStartDate");

        if (priceProtectionStartDate != null) {
            setPriceProtectionStartDate(priceProtectionStartDate);
        }

        String tempItemMasterSid = (String) attributes.get("tempItemMasterSid");

        if (tempItemMasterSid != null) {
            setTempItemMasterSid(tempItemMasterSid);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String subModuleName = (String) attributes.get("subModuleName");

        if (subModuleName != null) {
            setSubModuleName(subModuleName);
        }

        String theraputicClass = (String) attributes.get("theraputicClass");

        if (theraputicClass != null) {
            setTheraputicClass(theraputicClass);
        }

        Integer gcmGlobalDetailsSid = (Integer) attributes.get(
                "gcmGlobalDetailsSid");

        if (gcmGlobalDetailsSid != null) {
            setGcmGlobalDetailsSid(gcmGlobalDetailsSid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String paymentMethod = (String) attributes.get("paymentMethod");

        if (paymentMethod != null) {
            setPaymentMethod(paymentMethod);
        }

        Date contractPriceEndDate = (Date) attributes.get(
                "contractPriceEndDate");

        if (contractPriceEndDate != null) {
            setContractPriceEndDate(contractPriceEndDate);
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

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String screenName = (String) attributes.get("screenName");

        if (screenName != null) {
            setScreenName(screenName);
        }

        Integer rsContractSid = (Integer) attributes.get("rsContractSid");

        if (rsContractSid != null) {
            setRsContractSid(rsContractSid);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String cfpStatus = (String) attributes.get("cfpStatus");

        if (cfpStatus != null) {
            setCfpStatus(cfpStatus);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer cfpContractSid = (Integer) attributes.get("cfpContractSid");

        if (cfpContractSid != null) {
            setCfpContractSid(cfpContractSid);
        }

        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        Date tempEndDate = (Date) attributes.get("tempEndDate");

        if (tempEndDate != null) {
            setTempEndDate(tempEndDate);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String itemType = (String) attributes.get("itemType");

        if (itemType != null) {
            setItemType(itemType);
        }

        String forecastingType = (String) attributes.get("forecastingType");

        if (forecastingType != null) {
            setForecastingType(forecastingType);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Double basePrice = (Double) attributes.get("basePrice");

        if (basePrice != null) {
            setBasePrice(basePrice);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        String formulaName = (String) attributes.get("formulaName");

        if (formulaName != null) {
            setFormulaName(formulaName);
        }

        Integer workflowMasterSid = (Integer) attributes.get(
                "workflowMasterSid");

        if (workflowMasterSid != null) {
            setWorkflowMasterSid(workflowMasterSid);
        }

        Double priceTolerance = (Double) attributes.get("priceTolerance");

        if (priceTolerance != null) {
            setPriceTolerance(priceTolerance);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Date tempStartDate = (Date) attributes.get("tempStartDate");

        if (tempStartDate != null) {
            setTempStartDate(tempStartDate);
        }

        Date cfpEndDate = (Date) attributes.get("cfpEndDate");

        if (cfpEndDate != null) {
            setCfpEndDate(cfpEndDate);
        }

        Integer psModelSid = (Integer) attributes.get("psModelSid");

        if (psModelSid != null) {
            setPsModelSid(psModelSid);
        }

        String formulaId = (String) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
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

        Integer rebateAmount = (Integer) attributes.get("rebateAmount");

        if (rebateAmount != null) {
            setRebateAmount(rebateAmount);
        }

        Integer userId = (Integer) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Integer projectionMasterSid = (Integer) attributes.get(
                "projectionMasterSid");

        if (projectionMasterSid != null) {
            setProjectionMasterSid(projectionMasterSid);
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

        String ifpContractAttachedStatus = (String) attributes.get(
                "ifpContractAttachedStatus");

        if (ifpContractAttachedStatus != null) {
            setIfpContractAttachedStatus(ifpContractAttachedStatus);
        }

        Integer rebatePlanSystemId = (Integer) attributes.get(
                "rebatePlanSystemId");

        if (rebatePlanSystemId != null) {
            setRebatePlanSystemId(rebatePlanSystemId);
        }

        String rebatePlanName = (String) attributes.get("rebatePlanName");

        if (rebatePlanName != null) {
            setRebatePlanName(rebatePlanName);
        }

        String calendar = (String) attributes.get("calendar");

        if (calendar != null) {
            setCalendar(calendar);
        }

        String pricingQualifierSid = (String) attributes.get(
                "pricingQualifierSid");

        if (pricingQualifierSid != null) {
            setPricingQualifierSid(pricingQualifierSid);
        }

        String tempStatus = (String) attributes.get("tempStatus");

        if (tempStatus != null) {
            setTempStatus(tempStatus);
        }

        Date itemRebateEndDate = (Date) attributes.get("itemRebateEndDate");

        if (itemRebateEndDate != null) {
            setItemRebateEndDate(itemRebateEndDate);
        }

        Integer priceToleranceInterval = (Integer) attributes.get(
                "priceToleranceInterval");

        if (priceToleranceInterval != null) {
            setPriceToleranceInterval(priceToleranceInterval);
        }

        Date itemRebateStartDate = (Date) attributes.get("itemRebateStartDate");

        if (itemRebateStartDate != null) {
            setItemRebateStartDate(itemRebateStartDate);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        Integer itemStatusSid = (Integer) attributes.get("itemStatusSid");

        if (itemStatusSid != null) {
            setItemStatusSid(itemStatusSid);
        }

        Integer ifpContractSid = (Integer) attributes.get("ifpContractSid");

        if (ifpContractSid != null) {
            setIfpContractSid(ifpContractSid);
        }
    }

    /**
    * Returns the primary key of this gcm global details.
    *
    * @return the primary key of this gcm global details
    */
    @Override
    public int getPrimaryKey() {
        return _gcmGlobalDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this gcm global details.
    *
    * @param primaryKey the primary key of this gcm global details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _gcmGlobalDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item status of this gcm global details.
    *
    * @return the item status of this gcm global details
    */
    @Override
    public java.lang.String getItemStatus() {
        return _gcmGlobalDetails.getItemStatus();
    }

    /**
    * Sets the item status of this gcm global details.
    *
    * @param itemStatus the item status of this gcm global details
    */
    @Override
    public void setItemStatus(java.lang.String itemStatus) {
        _gcmGlobalDetails.setItemStatus(itemStatus);
    }

    /**
    * Returns the formula method ID of this gcm global details.
    *
    * @return the formula method ID of this gcm global details
    */
    @Override
    public java.lang.String getFormulaMethodId() {
        return _gcmGlobalDetails.getFormulaMethodId();
    }

    /**
    * Sets the formula method ID of this gcm global details.
    *
    * @param formulaMethodId the formula method ID of this gcm global details
    */
    @Override
    public void setFormulaMethodId(java.lang.String formulaMethodId) {
        _gcmGlobalDetails.setFormulaMethodId(formulaMethodId);
    }

    /**
    * Returns the module name of this gcm global details.
    *
    * @return the module name of this gcm global details
    */
    @Override
    public java.lang.String getModuleName() {
        return _gcmGlobalDetails.getModuleName();
    }

    /**
    * Sets the module name of this gcm global details.
    *
    * @param moduleName the module name of this gcm global details
    */
    @Override
    public void setModuleName(java.lang.String moduleName) {
        _gcmGlobalDetails.setModuleName(moduleName);
    }

    /**
    * Returns the payment frequency of this gcm global details.
    *
    * @return the payment frequency of this gcm global details
    */
    @Override
    public java.lang.String getPaymentFrequency() {
        return _gcmGlobalDetails.getPaymentFrequency();
    }

    /**
    * Sets the payment frequency of this gcm global details.
    *
    * @param paymentFrequency the payment frequency of this gcm global details
    */
    @Override
    public void setPaymentFrequency(java.lang.String paymentFrequency) {
        _gcmGlobalDetails.setPaymentFrequency(paymentFrequency);
    }

    /**
    * Returns the end date of this gcm global details.
    *
    * @return the end date of this gcm global details
    */
    @Override
    public java.util.Date getEndDate() {
        return _gcmGlobalDetails.getEndDate();
    }

    /**
    * Sets the end date of this gcm global details.
    *
    * @param endDate the end date of this gcm global details
    */
    @Override
    public void setEndDate(java.util.Date endDate) {
        _gcmGlobalDetails.setEndDate(endDate);
    }

    /**
    * Returns the cfp start date of this gcm global details.
    *
    * @return the cfp start date of this gcm global details
    */
    @Override
    public java.util.Date getCfpStartDate() {
        return _gcmGlobalDetails.getCfpStartDate();
    }

    /**
    * Sets the cfp start date of this gcm global details.
    *
    * @param cfpStartDate the cfp start date of this gcm global details
    */
    @Override
    public void setCfpStartDate(java.util.Date cfpStartDate) {
        _gcmGlobalDetails.setCfpStartDate(cfpStartDate);
    }

    /**
    * Returns the price protection start date of this gcm global details.
    *
    * @return the price protection start date of this gcm global details
    */
    @Override
    public java.util.Date getPriceProtectionStartDate() {
        return _gcmGlobalDetails.getPriceProtectionStartDate();
    }

    /**
    * Sets the price protection start date of this gcm global details.
    *
    * @param priceProtectionStartDate the price protection start date of this gcm global details
    */
    @Override
    public void setPriceProtectionStartDate(
        java.util.Date priceProtectionStartDate) {
        _gcmGlobalDetails.setPriceProtectionStartDate(priceProtectionStartDate);
    }

    /**
    * Returns the temp item master sid of this gcm global details.
    *
    * @return the temp item master sid of this gcm global details
    */
    @Override
    public java.lang.String getTempItemMasterSid() {
        return _gcmGlobalDetails.getTempItemMasterSid();
    }

    /**
    * Sets the temp item master sid of this gcm global details.
    *
    * @param tempItemMasterSid the temp item master sid of this gcm global details
    */
    @Override
    public void setTempItemMasterSid(java.lang.String tempItemMasterSid) {
        _gcmGlobalDetails.setTempItemMasterSid(tempItemMasterSid);
    }

    /**
    * Returns the brand name of this gcm global details.
    *
    * @return the brand name of this gcm global details
    */
    @Override
    public java.lang.String getBrandName() {
        return _gcmGlobalDetails.getBrandName();
    }

    /**
    * Sets the brand name of this gcm global details.
    *
    * @param brandName the brand name of this gcm global details
    */
    @Override
    public void setBrandName(java.lang.String brandName) {
        _gcmGlobalDetails.setBrandName(brandName);
    }

    /**
    * Returns the modified date of this gcm global details.
    *
    * @return the modified date of this gcm global details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _gcmGlobalDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this gcm global details.
    *
    * @param modifiedDate the modified date of this gcm global details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _gcmGlobalDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the contract master sid of this gcm global details.
    *
    * @return the contract master sid of this gcm global details
    */
    @Override
    public int getContractMasterSid() {
        return _gcmGlobalDetails.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this gcm global details.
    *
    * @param contractMasterSid the contract master sid of this gcm global details
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _gcmGlobalDetails.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the modified by of this gcm global details.
    *
    * @return the modified by of this gcm global details
    */
    @Override
    public int getModifiedBy() {
        return _gcmGlobalDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this gcm global details.
    *
    * @param modifiedBy the modified by of this gcm global details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _gcmGlobalDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the sub module name of this gcm global details.
    *
    * @return the sub module name of this gcm global details
    */
    @Override
    public java.lang.String getSubModuleName() {
        return _gcmGlobalDetails.getSubModuleName();
    }

    /**
    * Sets the sub module name of this gcm global details.
    *
    * @param subModuleName the sub module name of this gcm global details
    */
    @Override
    public void setSubModuleName(java.lang.String subModuleName) {
        _gcmGlobalDetails.setSubModuleName(subModuleName);
    }

    /**
    * Returns the theraputic class of this gcm global details.
    *
    * @return the theraputic class of this gcm global details
    */
    @Override
    public java.lang.String getTheraputicClass() {
        return _gcmGlobalDetails.getTheraputicClass();
    }

    /**
    * Sets the theraputic class of this gcm global details.
    *
    * @param theraputicClass the theraputic class of this gcm global details
    */
    @Override
    public void setTheraputicClass(java.lang.String theraputicClass) {
        _gcmGlobalDetails.setTheraputicClass(theraputicClass);
    }

    /**
    * Returns the gcm global details sid of this gcm global details.
    *
    * @return the gcm global details sid of this gcm global details
    */
    @Override
    public int getGcmGlobalDetailsSid() {
        return _gcmGlobalDetails.getGcmGlobalDetailsSid();
    }

    /**
    * Sets the gcm global details sid of this gcm global details.
    *
    * @param gcmGlobalDetailsSid the gcm global details sid of this gcm global details
    */
    @Override
    public void setGcmGlobalDetailsSid(int gcmGlobalDetailsSid) {
        _gcmGlobalDetails.setGcmGlobalDetailsSid(gcmGlobalDetailsSid);
    }

    /**
    * Returns the check record of this gcm global details.
    *
    * @return the check record of this gcm global details
    */
    @Override
    public boolean getCheckRecord() {
        return _gcmGlobalDetails.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this gcm global details is check record.
    *
    * @return <code>true</code> if this gcm global details is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _gcmGlobalDetails.isCheckRecord();
    }

    /**
    * Sets whether this gcm global details is check record.
    *
    * @param checkRecord the check record of this gcm global details
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _gcmGlobalDetails.setCheckRecord(checkRecord);
    }

    /**
    * Returns the payment method of this gcm global details.
    *
    * @return the payment method of this gcm global details
    */
    @Override
    public java.lang.String getPaymentMethod() {
        return _gcmGlobalDetails.getPaymentMethod();
    }

    /**
    * Sets the payment method of this gcm global details.
    *
    * @param paymentMethod the payment method of this gcm global details
    */
    @Override
    public void setPaymentMethod(java.lang.String paymentMethod) {
        _gcmGlobalDetails.setPaymentMethod(paymentMethod);
    }

    /**
    * Returns the contract price end date of this gcm global details.
    *
    * @return the contract price end date of this gcm global details
    */
    @Override
    public java.util.Date getContractPriceEndDate() {
        return _gcmGlobalDetails.getContractPriceEndDate();
    }

    /**
    * Sets the contract price end date of this gcm global details.
    *
    * @param contractPriceEndDate the contract price end date of this gcm global details
    */
    @Override
    public void setContractPriceEndDate(java.util.Date contractPriceEndDate) {
        _gcmGlobalDetails.setContractPriceEndDate(contractPriceEndDate);
    }

    /**
    * Returns the ps contract sid of this gcm global details.
    *
    * @return the ps contract sid of this gcm global details
    */
    @Override
    public int getPsContractSid() {
        return _gcmGlobalDetails.getPsContractSid();
    }

    /**
    * Sets the ps contract sid of this gcm global details.
    *
    * @param psContractSid the ps contract sid of this gcm global details
    */
    @Override
    public void setPsContractSid(int psContractSid) {
        _gcmGlobalDetails.setPsContractSid(psContractSid);
    }

    /**
    * Returns the price protection end date of this gcm global details.
    *
    * @return the price protection end date of this gcm global details
    */
    @Override
    public java.util.Date getPriceProtectionEndDate() {
        return _gcmGlobalDetails.getPriceProtectionEndDate();
    }

    /**
    * Sets the price protection end date of this gcm global details.
    *
    * @param priceProtectionEndDate the price protection end date of this gcm global details
    */
    @Override
    public void setPriceProtectionEndDate(java.util.Date priceProtectionEndDate) {
        _gcmGlobalDetails.setPriceProtectionEndDate(priceProtectionEndDate);
    }

    /**
    * Returns the start date of this gcm global details.
    *
    * @return the start date of this gcm global details
    */
    @Override
    public java.util.Date getStartDate() {
        return _gcmGlobalDetails.getStartDate();
    }

    /**
    * Sets the start date of this gcm global details.
    *
    * @param startDate the start date of this gcm global details
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _gcmGlobalDetails.setStartDate(startDate);
    }

    /**
    * Returns the screen name of this gcm global details.
    *
    * @return the screen name of this gcm global details
    */
    @Override
    public java.lang.String getScreenName() {
        return _gcmGlobalDetails.getScreenName();
    }

    /**
    * Sets the screen name of this gcm global details.
    *
    * @param screenName the screen name of this gcm global details
    */
    @Override
    public void setScreenName(java.lang.String screenName) {
        _gcmGlobalDetails.setScreenName(screenName);
    }

    /**
    * Returns the rs contract sid of this gcm global details.
    *
    * @return the rs contract sid of this gcm global details
    */
    @Override
    public int getRsContractSid() {
        return _gcmGlobalDetails.getRsContractSid();
    }

    /**
    * Sets the rs contract sid of this gcm global details.
    *
    * @param rsContractSid the rs contract sid of this gcm global details
    */
    @Override
    public void setRsContractSid(int rsContractSid) {
        _gcmGlobalDetails.setRsContractSid(rsContractSid);
    }

    /**
    * Returns the item name of this gcm global details.
    *
    * @return the item name of this gcm global details
    */
    @Override
    public java.lang.String getItemName() {
        return _gcmGlobalDetails.getItemName();
    }

    /**
    * Sets the item name of this gcm global details.
    *
    * @param itemName the item name of this gcm global details
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _gcmGlobalDetails.setItemName(itemName);
    }

    /**
    * Returns the session ID of this gcm global details.
    *
    * @return the session ID of this gcm global details
    */
    @Override
    public java.lang.String getSessionId() {
        return _gcmGlobalDetails.getSessionId();
    }

    /**
    * Sets the session ID of this gcm global details.
    *
    * @param sessionId the session ID of this gcm global details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _gcmGlobalDetails.setSessionId(sessionId);
    }

    /**
    * Returns the cfp status of this gcm global details.
    *
    * @return the cfp status of this gcm global details
    */
    @Override
    public java.lang.String getCfpStatus() {
        return _gcmGlobalDetails.getCfpStatus();
    }

    /**
    * Sets the cfp status of this gcm global details.
    *
    * @param cfpStatus the cfp status of this gcm global details
    */
    @Override
    public void setCfpStatus(java.lang.String cfpStatus) {
        _gcmGlobalDetails.setCfpStatus(cfpStatus);
    }

    /**
    * Returns the rs model sid of this gcm global details.
    *
    * @return the rs model sid of this gcm global details
    */
    @Override
    public int getRsModelSid() {
        return _gcmGlobalDetails.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this gcm global details.
    *
    * @param rsModelSid the rs model sid of this gcm global details
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _gcmGlobalDetails.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the cfp contract sid of this gcm global details.
    *
    * @return the cfp contract sid of this gcm global details
    */
    @Override
    public int getCfpContractSid() {
        return _gcmGlobalDetails.getCfpContractSid();
    }

    /**
    * Sets the cfp contract sid of this gcm global details.
    *
    * @param cfpContractSid the cfp contract sid of this gcm global details
    */
    @Override
    public void setCfpContractSid(int cfpContractSid) {
        _gcmGlobalDetails.setCfpContractSid(cfpContractSid);
    }

    /**
    * Returns the price of this gcm global details.
    *
    * @return the price of this gcm global details
    */
    @Override
    public double getPrice() {
        return _gcmGlobalDetails.getPrice();
    }

    /**
    * Sets the price of this gcm global details.
    *
    * @param price the price of this gcm global details
    */
    @Override
    public void setPrice(double price) {
        _gcmGlobalDetails.setPrice(price);
    }

    /**
    * Returns the temp end date of this gcm global details.
    *
    * @return the temp end date of this gcm global details
    */
    @Override
    public java.util.Date getTempEndDate() {
        return _gcmGlobalDetails.getTempEndDate();
    }

    /**
    * Sets the temp end date of this gcm global details.
    *
    * @param tempEndDate the temp end date of this gcm global details
    */
    @Override
    public void setTempEndDate(java.util.Date tempEndDate) {
        _gcmGlobalDetails.setTempEndDate(tempEndDate);
    }

    /**
    * Returns the item master sid of this gcm global details.
    *
    * @return the item master sid of this gcm global details
    */
    @Override
    public int getItemMasterSid() {
        return _gcmGlobalDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this gcm global details.
    *
    * @param itemMasterSid the item master sid of this gcm global details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _gcmGlobalDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the item type of this gcm global details.
    *
    * @return the item type of this gcm global details
    */
    @Override
    public java.lang.String getItemType() {
        return _gcmGlobalDetails.getItemType();
    }

    /**
    * Sets the item type of this gcm global details.
    *
    * @param itemType the item type of this gcm global details
    */
    @Override
    public void setItemType(java.lang.String itemType) {
        _gcmGlobalDetails.setItemType(itemType);
    }

    /**
    * Returns the forecasting type of this gcm global details.
    *
    * @return the forecasting type of this gcm global details
    */
    @Override
    public java.lang.String getForecastingType() {
        return _gcmGlobalDetails.getForecastingType();
    }

    /**
    * Sets the forecasting type of this gcm global details.
    *
    * @param forecastingType the forecasting type of this gcm global details
    */
    @Override
    public void setForecastingType(java.lang.String forecastingType) {
        _gcmGlobalDetails.setForecastingType(forecastingType);
    }

    /**
    * Returns the item ID of this gcm global details.
    *
    * @return the item ID of this gcm global details
    */
    @Override
    public java.lang.String getItemId() {
        return _gcmGlobalDetails.getItemId();
    }

    /**
    * Sets the item ID of this gcm global details.
    *
    * @param itemId the item ID of this gcm global details
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _gcmGlobalDetails.setItemId(itemId);
    }

    /**
    * Returns the base price of this gcm global details.
    *
    * @return the base price of this gcm global details
    */
    @Override
    public double getBasePrice() {
        return _gcmGlobalDetails.getBasePrice();
    }

    /**
    * Sets the base price of this gcm global details.
    *
    * @param basePrice the base price of this gcm global details
    */
    @Override
    public void setBasePrice(double basePrice) {
        _gcmGlobalDetails.setBasePrice(basePrice);
    }

    /**
    * Returns the status of this gcm global details.
    *
    * @return the status of this gcm global details
    */
    @Override
    public java.lang.String getStatus() {
        return _gcmGlobalDetails.getStatus();
    }

    /**
    * Sets the status of this gcm global details.
    *
    * @param status the status of this gcm global details
    */
    @Override
    public void setStatus(java.lang.String status) {
        _gcmGlobalDetails.setStatus(status);
    }

    /**
    * Returns the formula name of this gcm global details.
    *
    * @return the formula name of this gcm global details
    */
    @Override
    public java.lang.String getFormulaName() {
        return _gcmGlobalDetails.getFormulaName();
    }

    /**
    * Sets the formula name of this gcm global details.
    *
    * @param formulaName the formula name of this gcm global details
    */
    @Override
    public void setFormulaName(java.lang.String formulaName) {
        _gcmGlobalDetails.setFormulaName(formulaName);
    }

    /**
    * Returns the workflow master sid of this gcm global details.
    *
    * @return the workflow master sid of this gcm global details
    */
    @Override
    public int getWorkflowMasterSid() {
        return _gcmGlobalDetails.getWorkflowMasterSid();
    }

    /**
    * Sets the workflow master sid of this gcm global details.
    *
    * @param workflowMasterSid the workflow master sid of this gcm global details
    */
    @Override
    public void setWorkflowMasterSid(int workflowMasterSid) {
        _gcmGlobalDetails.setWorkflowMasterSid(workflowMasterSid);
    }

    /**
    * Returns the price tolerance of this gcm global details.
    *
    * @return the price tolerance of this gcm global details
    */
    @Override
    public double getPriceTolerance() {
        return _gcmGlobalDetails.getPriceTolerance();
    }

    /**
    * Sets the price tolerance of this gcm global details.
    *
    * @param priceTolerance the price tolerance of this gcm global details
    */
    @Override
    public void setPriceTolerance(double priceTolerance) {
        _gcmGlobalDetails.setPriceTolerance(priceTolerance);
    }

    /**
    * Returns the created by of this gcm global details.
    *
    * @return the created by of this gcm global details
    */
    @Override
    public int getCreatedBy() {
        return _gcmGlobalDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this gcm global details.
    *
    * @param createdBy the created by of this gcm global details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _gcmGlobalDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this gcm global details.
    *
    * @return the created date of this gcm global details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _gcmGlobalDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this gcm global details.
    *
    * @param createdDate the created date of this gcm global details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _gcmGlobalDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the temp start date of this gcm global details.
    *
    * @return the temp start date of this gcm global details
    */
    @Override
    public java.util.Date getTempStartDate() {
        return _gcmGlobalDetails.getTempStartDate();
    }

    /**
    * Sets the temp start date of this gcm global details.
    *
    * @param tempStartDate the temp start date of this gcm global details
    */
    @Override
    public void setTempStartDate(java.util.Date tempStartDate) {
        _gcmGlobalDetails.setTempStartDate(tempStartDate);
    }

    /**
    * Returns the cfp end date of this gcm global details.
    *
    * @return the cfp end date of this gcm global details
    */
    @Override
    public java.util.Date getCfpEndDate() {
        return _gcmGlobalDetails.getCfpEndDate();
    }

    /**
    * Sets the cfp end date of this gcm global details.
    *
    * @param cfpEndDate the cfp end date of this gcm global details
    */
    @Override
    public void setCfpEndDate(java.util.Date cfpEndDate) {
        _gcmGlobalDetails.setCfpEndDate(cfpEndDate);
    }

    /**
    * Returns the ps model sid of this gcm global details.
    *
    * @return the ps model sid of this gcm global details
    */
    @Override
    public int getPsModelSid() {
        return _gcmGlobalDetails.getPsModelSid();
    }

    /**
    * Sets the ps model sid of this gcm global details.
    *
    * @param psModelSid the ps model sid of this gcm global details
    */
    @Override
    public void setPsModelSid(int psModelSid) {
        _gcmGlobalDetails.setPsModelSid(psModelSid);
    }

    /**
    * Returns the formula ID of this gcm global details.
    *
    * @return the formula ID of this gcm global details
    */
    @Override
    public java.lang.String getFormulaId() {
        return _gcmGlobalDetails.getFormulaId();
    }

    /**
    * Sets the formula ID of this gcm global details.
    *
    * @param formulaId the formula ID of this gcm global details
    */
    @Override
    public void setFormulaId(java.lang.String formulaId) {
        _gcmGlobalDetails.setFormulaId(formulaId);
    }

    /**
    * Returns the item no of this gcm global details.
    *
    * @return the item no of this gcm global details
    */
    @Override
    public java.lang.String getItemNo() {
        return _gcmGlobalDetails.getItemNo();
    }

    /**
    * Sets the item no of this gcm global details.
    *
    * @param itemNo the item no of this gcm global details
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _gcmGlobalDetails.setItemNo(itemNo);
    }

    /**
    * Returns the contract price of this gcm global details.
    *
    * @return the contract price of this gcm global details
    */
    @Override
    public double getContractPrice() {
        return _gcmGlobalDetails.getContractPrice();
    }

    /**
    * Sets the contract price of this gcm global details.
    *
    * @param contractPrice the contract price of this gcm global details
    */
    @Override
    public void setContractPrice(double contractPrice) {
        _gcmGlobalDetails.setContractPrice(contractPrice);
    }

    /**
    * Returns the ifp model sid of this gcm global details.
    *
    * @return the ifp model sid of this gcm global details
    */
    @Override
    public int getIfpModelSid() {
        return _gcmGlobalDetails.getIfpModelSid();
    }

    /**
    * Sets the ifp model sid of this gcm global details.
    *
    * @param ifpModelSid the ifp model sid of this gcm global details
    */
    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _gcmGlobalDetails.setIfpModelSid(ifpModelSid);
    }

    /**
    * Returns the price tolerance type of this gcm global details.
    *
    * @return the price tolerance type of this gcm global details
    */
    @Override
    public int getPriceToleranceType() {
        return _gcmGlobalDetails.getPriceToleranceType();
    }

    /**
    * Sets the price tolerance type of this gcm global details.
    *
    * @param priceToleranceType the price tolerance type of this gcm global details
    */
    @Override
    public void setPriceToleranceType(int priceToleranceType) {
        _gcmGlobalDetails.setPriceToleranceType(priceToleranceType);
    }

    /**
    * Returns the rebate amount of this gcm global details.
    *
    * @return the rebate amount of this gcm global details
    */
    @Override
    public int getRebateAmount() {
        return _gcmGlobalDetails.getRebateAmount();
    }

    /**
    * Sets the rebate amount of this gcm global details.
    *
    * @param rebateAmount the rebate amount of this gcm global details
    */
    @Override
    public void setRebateAmount(int rebateAmount) {
        _gcmGlobalDetails.setRebateAmount(rebateAmount);
    }

    /**
    * Returns the user ID of this gcm global details.
    *
    * @return the user ID of this gcm global details
    */
    @Override
    public int getUserId() {
        return _gcmGlobalDetails.getUserId();
    }

    /**
    * Sets the user ID of this gcm global details.
    *
    * @param userId the user ID of this gcm global details
    */
    @Override
    public void setUserId(int userId) {
        _gcmGlobalDetails.setUserId(userId);
    }

    /**
    * Returns the projection master sid of this gcm global details.
    *
    * @return the projection master sid of this gcm global details
    */
    @Override
    public int getProjectionMasterSid() {
        return _gcmGlobalDetails.getProjectionMasterSid();
    }

    /**
    * Sets the projection master sid of this gcm global details.
    *
    * @param projectionMasterSid the projection master sid of this gcm global details
    */
    @Override
    public void setProjectionMasterSid(int projectionMasterSid) {
        _gcmGlobalDetails.setProjectionMasterSid(projectionMasterSid);
    }

    /**
    * Returns the contract price start date of this gcm global details.
    *
    * @return the contract price start date of this gcm global details
    */
    @Override
    public java.util.Date getContractPriceStartDate() {
        return _gcmGlobalDetails.getContractPriceStartDate();
    }

    /**
    * Sets the contract price start date of this gcm global details.
    *
    * @param contractPriceStartDate the contract price start date of this gcm global details
    */
    @Override
    public void setContractPriceStartDate(java.util.Date contractPriceStartDate) {
        _gcmGlobalDetails.setContractPriceStartDate(contractPriceStartDate);
    }

    /**
    * Returns the price tolerance frequency of this gcm global details.
    *
    * @return the price tolerance frequency of this gcm global details
    */
    @Override
    public int getPriceToleranceFrequency() {
        return _gcmGlobalDetails.getPriceToleranceFrequency();
    }

    /**
    * Sets the price tolerance frequency of this gcm global details.
    *
    * @param priceToleranceFrequency the price tolerance frequency of this gcm global details
    */
    @Override
    public void setPriceToleranceFrequency(int priceToleranceFrequency) {
        _gcmGlobalDetails.setPriceToleranceFrequency(priceToleranceFrequency);
    }

    /**
    * Returns the ifp contract attached status of this gcm global details.
    *
    * @return the ifp contract attached status of this gcm global details
    */
    @Override
    public java.lang.String getIfpContractAttachedStatus() {
        return _gcmGlobalDetails.getIfpContractAttachedStatus();
    }

    /**
    * Sets the ifp contract attached status of this gcm global details.
    *
    * @param ifpContractAttachedStatus the ifp contract attached status of this gcm global details
    */
    @Override
    public void setIfpContractAttachedStatus(
        java.lang.String ifpContractAttachedStatus) {
        _gcmGlobalDetails.setIfpContractAttachedStatus(ifpContractAttachedStatus);
    }

    /**
    * Returns the rebate plan system ID of this gcm global details.
    *
    * @return the rebate plan system ID of this gcm global details
    */
    @Override
    public int getRebatePlanSystemId() {
        return _gcmGlobalDetails.getRebatePlanSystemId();
    }

    /**
    * Sets the rebate plan system ID of this gcm global details.
    *
    * @param rebatePlanSystemId the rebate plan system ID of this gcm global details
    */
    @Override
    public void setRebatePlanSystemId(int rebatePlanSystemId) {
        _gcmGlobalDetails.setRebatePlanSystemId(rebatePlanSystemId);
    }

    /**
    * Returns the rebate plan name of this gcm global details.
    *
    * @return the rebate plan name of this gcm global details
    */
    @Override
    public java.lang.String getRebatePlanName() {
        return _gcmGlobalDetails.getRebatePlanName();
    }

    /**
    * Sets the rebate plan name of this gcm global details.
    *
    * @param rebatePlanName the rebate plan name of this gcm global details
    */
    @Override
    public void setRebatePlanName(java.lang.String rebatePlanName) {
        _gcmGlobalDetails.setRebatePlanName(rebatePlanName);
    }

    /**
    * Returns the calendar of this gcm global details.
    *
    * @return the calendar of this gcm global details
    */
    @Override
    public java.lang.String getCalendar() {
        return _gcmGlobalDetails.getCalendar();
    }

    /**
    * Sets the calendar of this gcm global details.
    *
    * @param calendar the calendar of this gcm global details
    */
    @Override
    public void setCalendar(java.lang.String calendar) {
        _gcmGlobalDetails.setCalendar(calendar);
    }

    /**
    * Returns the pricing qualifier sid of this gcm global details.
    *
    * @return the pricing qualifier sid of this gcm global details
    */
    @Override
    public java.lang.String getPricingQualifierSid() {
        return _gcmGlobalDetails.getPricingQualifierSid();
    }

    /**
    * Sets the pricing qualifier sid of this gcm global details.
    *
    * @param pricingQualifierSid the pricing qualifier sid of this gcm global details
    */
    @Override
    public void setPricingQualifierSid(java.lang.String pricingQualifierSid) {
        _gcmGlobalDetails.setPricingQualifierSid(pricingQualifierSid);
    }

    /**
    * Returns the temp status of this gcm global details.
    *
    * @return the temp status of this gcm global details
    */
    @Override
    public java.lang.String getTempStatus() {
        return _gcmGlobalDetails.getTempStatus();
    }

    /**
    * Sets the temp status of this gcm global details.
    *
    * @param tempStatus the temp status of this gcm global details
    */
    @Override
    public void setTempStatus(java.lang.String tempStatus) {
        _gcmGlobalDetails.setTempStatus(tempStatus);
    }

    /**
    * Returns the item rebate end date of this gcm global details.
    *
    * @return the item rebate end date of this gcm global details
    */
    @Override
    public java.util.Date getItemRebateEndDate() {
        return _gcmGlobalDetails.getItemRebateEndDate();
    }

    /**
    * Sets the item rebate end date of this gcm global details.
    *
    * @param itemRebateEndDate the item rebate end date of this gcm global details
    */
    @Override
    public void setItemRebateEndDate(java.util.Date itemRebateEndDate) {
        _gcmGlobalDetails.setItemRebateEndDate(itemRebateEndDate);
    }

    /**
    * Returns the price tolerance interval of this gcm global details.
    *
    * @return the price tolerance interval of this gcm global details
    */
    @Override
    public int getPriceToleranceInterval() {
        return _gcmGlobalDetails.getPriceToleranceInterval();
    }

    /**
    * Sets the price tolerance interval of this gcm global details.
    *
    * @param priceToleranceInterval the price tolerance interval of this gcm global details
    */
    @Override
    public void setPriceToleranceInterval(int priceToleranceInterval) {
        _gcmGlobalDetails.setPriceToleranceInterval(priceToleranceInterval);
    }

    /**
    * Returns the item rebate start date of this gcm global details.
    *
    * @return the item rebate start date of this gcm global details
    */
    @Override
    public java.util.Date getItemRebateStartDate() {
        return _gcmGlobalDetails.getItemRebateStartDate();
    }

    /**
    * Sets the item rebate start date of this gcm global details.
    *
    * @param itemRebateStartDate the item rebate start date of this gcm global details
    */
    @Override
    public void setItemRebateStartDate(java.util.Date itemRebateStartDate) {
        _gcmGlobalDetails.setItemRebateStartDate(itemRebateStartDate);
    }

    /**
    * Returns the operation of this gcm global details.
    *
    * @return the operation of this gcm global details
    */
    @Override
    public java.lang.String getOperation() {
        return _gcmGlobalDetails.getOperation();
    }

    /**
    * Sets the operation of this gcm global details.
    *
    * @param operation the operation of this gcm global details
    */
    @Override
    public void setOperation(java.lang.String operation) {
        _gcmGlobalDetails.setOperation(operation);
    }

    /**
    * Returns the cfp model sid of this gcm global details.
    *
    * @return the cfp model sid of this gcm global details
    */
    @Override
    public int getCfpModelSid() {
        return _gcmGlobalDetails.getCfpModelSid();
    }

    /**
    * Sets the cfp model sid of this gcm global details.
    *
    * @param cfpModelSid the cfp model sid of this gcm global details
    */
    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _gcmGlobalDetails.setCfpModelSid(cfpModelSid);
    }

    /**
    * Returns the item status sid of this gcm global details.
    *
    * @return the item status sid of this gcm global details
    */
    @Override
    public int getItemStatusSid() {
        return _gcmGlobalDetails.getItemStatusSid();
    }

    /**
    * Sets the item status sid of this gcm global details.
    *
    * @param itemStatusSid the item status sid of this gcm global details
    */
    @Override
    public void setItemStatusSid(int itemStatusSid) {
        _gcmGlobalDetails.setItemStatusSid(itemStatusSid);
    }

    /**
    * Returns the ifp contract sid of this gcm global details.
    *
    * @return the ifp contract sid of this gcm global details
    */
    @Override
    public int getIfpContractSid() {
        return _gcmGlobalDetails.getIfpContractSid();
    }

    /**
    * Sets the ifp contract sid of this gcm global details.
    *
    * @param ifpContractSid the ifp contract sid of this gcm global details
    */
    @Override
    public void setIfpContractSid(int ifpContractSid) {
        _gcmGlobalDetails.setIfpContractSid(ifpContractSid);
    }

    @Override
    public boolean isNew() {
        return _gcmGlobalDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _gcmGlobalDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _gcmGlobalDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _gcmGlobalDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _gcmGlobalDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _gcmGlobalDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _gcmGlobalDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _gcmGlobalDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _gcmGlobalDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _gcmGlobalDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _gcmGlobalDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new GcmGlobalDetailsWrapper((GcmGlobalDetails) _gcmGlobalDetails.clone());
    }

    @Override
    public int compareTo(GcmGlobalDetails gcmGlobalDetails) {
        return _gcmGlobalDetails.compareTo(gcmGlobalDetails);
    }

    @Override
    public int hashCode() {
        return _gcmGlobalDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<GcmGlobalDetails> toCacheModel() {
        return _gcmGlobalDetails.toCacheModel();
    }

    @Override
    public GcmGlobalDetails toEscapedModel() {
        return new GcmGlobalDetailsWrapper(_gcmGlobalDetails.toEscapedModel());
    }

    @Override
    public GcmGlobalDetails toUnescapedModel() {
        return new GcmGlobalDetailsWrapper(_gcmGlobalDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _gcmGlobalDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _gcmGlobalDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _gcmGlobalDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof GcmGlobalDetailsWrapper)) {
            return false;
        }

        GcmGlobalDetailsWrapper gcmGlobalDetailsWrapper = (GcmGlobalDetailsWrapper) obj;

        if (Validator.equals(_gcmGlobalDetails,
                    gcmGlobalDetailsWrapper._gcmGlobalDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public GcmGlobalDetails getWrappedGcmGlobalDetails() {
        return _gcmGlobalDetails;
    }

    @Override
    public GcmGlobalDetails getWrappedModel() {
        return _gcmGlobalDetails;
    }

    @Override
    public void resetOriginalValues() {
        _gcmGlobalDetails.resetOriginalValues();
    }
}
