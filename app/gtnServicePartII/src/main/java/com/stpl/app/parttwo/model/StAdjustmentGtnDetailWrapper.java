package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link StAdjustmentGtnDetail}.
 * </p>
 *
 * @author
 * @see StAdjustmentGtnDetail
 * @generated
 */
public class StAdjustmentGtnDetailWrapper implements StAdjustmentGtnDetail,
    ModelWrapper<StAdjustmentGtnDetail> {
    private StAdjustmentGtnDetail _stAdjustmentGtnDetail;

    public StAdjustmentGtnDetailWrapper(
        StAdjustmentGtnDetail stAdjustmentGtnDetail) {
        _stAdjustmentGtnDetail = stAdjustmentGtnDetail;
    }

    @Override
    public Class<?> getModelClass() {
        return StAdjustmentGtnDetail.class;
    }

    @Override
    public String getModelClassName() {
        return StAdjustmentGtnDetail.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjustmentCreatedDate", getAdjustmentCreatedDate());
        attributes.put("etlCheckRecord", getEtlCheckRecord());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("redemptionPeriod", getRedemptionPeriod());
        attributes.put("deductionId", getDeductionId());
        attributes.put("glYear", getGlYear());
        attributes.put("brandName", getBrandName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("account", getAccount());
        attributes.put("source", getSource());
        attributes.put("workflowApprovedDate", getWorkflowApprovedDate());
        attributes.put("udc6", getUdc6());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("udc5", getUdc5());
        attributes.put("workflowCreatedDate", getWorkflowCreatedDate());
        attributes.put("udc4", getUdc4());
        attributes.put("udc3", getUdc3());
        attributes.put("udc2", getUdc2());
        attributes.put("udc1", getUdc1());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("deductionType", getDeductionType());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("contractName", getContractName());
        attributes.put("deductionRate", getDeductionRate());
        attributes.put("deductionCategory", getDeductionCategory());
        attributes.put("deductionNo", getDeductionNo());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("sessionId", getSessionId());
        attributes.put("glCompanyId", getGlCompanyId());
        attributes.put("itemName", getItemName());
        attributes.put("deductionInclusion", getDeductionInclusion());
        attributes.put("deductionAmount", getDeductionAmount());
        attributes.put("companyName", getCompanyName());
        attributes.put("project", getProject());
        attributes.put("deductionUdc3", getDeductionUdc3());
        attributes.put("deductionUdc4", getDeductionUdc4());
        attributes.put("deductionUdc1", getDeductionUdc1());
        attributes.put("itemId", getItemId());
        attributes.put("deductionUdc2", getDeductionUdc2());
        attributes.put("accountType", getAccountType());
        attributes.put("glString", getGlString());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionUdc6", getDeductionUdc6());
        attributes.put("deductionUdc5", getDeductionUdc5());
        attributes.put("glCompanyName", getGlCompanyName());
        attributes.put("workflowId", getWorkflowId());
        attributes.put("itemNo", getItemNo());
        attributes.put("contractId", getContractId());
        attributes.put("deductionProgram", getDeductionProgram());
        attributes.put("businessUnitId", getBusinessUnitId());
        attributes.put("userId", getUserId());
        attributes.put("costCenter", getCostCenter());
        attributes.put("companyId", getCompanyId());
        attributes.put("outboundStatus", getOutboundStatus());
        attributes.put("future1", getFuture1());
        attributes.put("brandId", getBrandId());
        attributes.put("deductionName", getDeductionName());
        attributes.put("future2", getFuture2());
        attributes.put("workflowName", getWorkflowName());
        attributes.put("glDate", getGlDate());
        attributes.put("workflowCreatedBy", getWorkflowCreatedBy());
        attributes.put("glMonth", getGlMonth());
        attributes.put("batchId", getBatchId());
        attributes.put("accountCategory", getAccountCategory());
        attributes.put("glCompanyNo", getGlCompanyNo());
        attributes.put("workflowApprovedBy", getWorkflowApprovedBy());
        attributes.put("contractNo", getContractNo());
        attributes.put("originalBatchId", getOriginalBatchId());
        attributes.put("adjustmentLevel", getAdjustmentLevel());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date adjustmentCreatedDate = (Date) attributes.get(
                "adjustmentCreatedDate");

        if (adjustmentCreatedDate != null) {
            setAdjustmentCreatedDate(adjustmentCreatedDate);
        }

        Boolean etlCheckRecord = (Boolean) attributes.get("etlCheckRecord");

        if (etlCheckRecord != null) {
            setEtlCheckRecord(etlCheckRecord);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        Date redemptionPeriod = (Date) attributes.get("redemptionPeriod");

        if (redemptionPeriod != null) {
            setRedemptionPeriod(redemptionPeriod);
        }

        String deductionId = (String) attributes.get("deductionId");

        if (deductionId != null) {
            setDeductionId(deductionId);
        }

        Integer glYear = (Integer) attributes.get("glYear");

        if (glYear != null) {
            setGlYear(glYear);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String account = (String) attributes.get("account");

        if (account != null) {
            setAccount(account);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date workflowApprovedDate = (Date) attributes.get(
                "workflowApprovedDate");

        if (workflowApprovedDate != null) {
            setWorkflowApprovedDate(workflowApprovedDate);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        Date workflowCreatedDate = (Date) attributes.get("workflowCreatedDate");

        if (workflowCreatedDate != null) {
            setWorkflowCreatedDate(workflowCreatedDate);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        String deductionRate = (String) attributes.get("deductionRate");

        if (deductionRate != null) {
            setDeductionRate(deductionRate);
        }

        String deductionCategory = (String) attributes.get("deductionCategory");

        if (deductionCategory != null) {
            setDeductionCategory(deductionCategory);
        }

        String deductionNo = (String) attributes.get("deductionNo");

        if (deductionNo != null) {
            setDeductionNo(deductionNo);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String glCompanyId = (String) attributes.get("glCompanyId");

        if (glCompanyId != null) {
            setGlCompanyId(glCompanyId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String deductionInclusion = (String) attributes.get(
                "deductionInclusion");

        if (deductionInclusion != null) {
            setDeductionInclusion(deductionInclusion);
        }

        String deductionAmount = (String) attributes.get("deductionAmount");

        if (deductionAmount != null) {
            setDeductionAmount(deductionAmount);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String project = (String) attributes.get("project");

        if (project != null) {
            setProject(project);
        }

        String deductionUdc3 = (String) attributes.get("deductionUdc3");

        if (deductionUdc3 != null) {
            setDeductionUdc3(deductionUdc3);
        }

        String deductionUdc4 = (String) attributes.get("deductionUdc4");

        if (deductionUdc4 != null) {
            setDeductionUdc4(deductionUdc4);
        }

        String deductionUdc1 = (String) attributes.get("deductionUdc1");

        if (deductionUdc1 != null) {
            setDeductionUdc1(deductionUdc1);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String deductionUdc2 = (String) attributes.get("deductionUdc2");

        if (deductionUdc2 != null) {
            setDeductionUdc2(deductionUdc2);
        }

        String accountType = (String) attributes.get("accountType");

        if (accountType != null) {
            setAccountType(accountType);
        }

        String glString = (String) attributes.get("glString");

        if (glString != null) {
            setGlString(glString);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String deductionUdc6 = (String) attributes.get("deductionUdc6");

        if (deductionUdc6 != null) {
            setDeductionUdc6(deductionUdc6);
        }

        String deductionUdc5 = (String) attributes.get("deductionUdc5");

        if (deductionUdc5 != null) {
            setDeductionUdc5(deductionUdc5);
        }

        String glCompanyName = (String) attributes.get("glCompanyName");

        if (glCompanyName != null) {
            setGlCompanyName(glCompanyName);
        }

        String workflowId = (String) attributes.get("workflowId");

        if (workflowId != null) {
            setWorkflowId(workflowId);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String deductionProgram = (String) attributes.get("deductionProgram");

        if (deductionProgram != null) {
            setDeductionProgram(deductionProgram);
        }

        String businessUnitId = (String) attributes.get("businessUnitId");

        if (businessUnitId != null) {
            setBusinessUnitId(businessUnitId);
        }

        String userId = (String) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String costCenter = (String) attributes.get("costCenter");

        if (costCenter != null) {
            setCostCenter(costCenter);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String outboundStatus = (String) attributes.get("outboundStatus");

        if (outboundStatus != null) {
            setOutboundStatus(outboundStatus);
        }

        String future1 = (String) attributes.get("future1");

        if (future1 != null) {
            setFuture1(future1);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String deductionName = (String) attributes.get("deductionName");

        if (deductionName != null) {
            setDeductionName(deductionName);
        }

        String future2 = (String) attributes.get("future2");

        if (future2 != null) {
            setFuture2(future2);
        }

        String workflowName = (String) attributes.get("workflowName");

        if (workflowName != null) {
            setWorkflowName(workflowName);
        }

        Date glDate = (Date) attributes.get("glDate");

        if (glDate != null) {
            setGlDate(glDate);
        }

        String workflowCreatedBy = (String) attributes.get("workflowCreatedBy");

        if (workflowCreatedBy != null) {
            setWorkflowCreatedBy(workflowCreatedBy);
        }

        Integer glMonth = (Integer) attributes.get("glMonth");

        if (glMonth != null) {
            setGlMonth(glMonth);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String accountCategory = (String) attributes.get("accountCategory");

        if (accountCategory != null) {
            setAccountCategory(accountCategory);
        }

        String glCompanyNo = (String) attributes.get("glCompanyNo");

        if (glCompanyNo != null) {
            setGlCompanyNo(glCompanyNo);
        }

        String workflowApprovedBy = (String) attributes.get(
                "workflowApprovedBy");

        if (workflowApprovedBy != null) {
            setWorkflowApprovedBy(workflowApprovedBy);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        String originalBatchId = (String) attributes.get("originalBatchId");

        if (originalBatchId != null) {
            setOriginalBatchId(originalBatchId);
        }

        String adjustmentLevel = (String) attributes.get("adjustmentLevel");

        if (adjustmentLevel != null) {
            setAdjustmentLevel(adjustmentLevel);
        }
    }

    /**
    * Returns the primary key of this st adjustment gtn detail.
    *
    * @return the primary key of this st adjustment gtn detail
    */
    @Override
    public com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPK getPrimaryKey() {
        return _stAdjustmentGtnDetail.getPrimaryKey();
    }

    /**
    * Sets the primary key of this st adjustment gtn detail.
    *
    * @param primaryKey the primary key of this st adjustment gtn detail
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPK primaryKey) {
        _stAdjustmentGtnDetail.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the adjustment created date of this st adjustment gtn detail.
    *
    * @return the adjustment created date of this st adjustment gtn detail
    */
    @Override
    public java.util.Date getAdjustmentCreatedDate() {
        return _stAdjustmentGtnDetail.getAdjustmentCreatedDate();
    }

    /**
    * Sets the adjustment created date of this st adjustment gtn detail.
    *
    * @param adjustmentCreatedDate the adjustment created date of this st adjustment gtn detail
    */
    @Override
    public void setAdjustmentCreatedDate(java.util.Date adjustmentCreatedDate) {
        _stAdjustmentGtnDetail.setAdjustmentCreatedDate(adjustmentCreatedDate);
    }

    /**
    * Returns the etl check record of this st adjustment gtn detail.
    *
    * @return the etl check record of this st adjustment gtn detail
    */
    @Override
    public boolean getEtlCheckRecord() {
        return _stAdjustmentGtnDetail.getEtlCheckRecord();
    }

    /**
    * Returns <code>true</code> if this st adjustment gtn detail is etl check record.
    *
    * @return <code>true</code> if this st adjustment gtn detail is etl check record; <code>false</code> otherwise
    */
    @Override
    public boolean isEtlCheckRecord() {
        return _stAdjustmentGtnDetail.isEtlCheckRecord();
    }

    /**
    * Sets whether this st adjustment gtn detail is etl check record.
    *
    * @param etlCheckRecord the etl check record of this st adjustment gtn detail
    */
    @Override
    public void setEtlCheckRecord(boolean etlCheckRecord) {
        _stAdjustmentGtnDetail.setEtlCheckRecord(etlCheckRecord);
    }

    /**
    * Returns the business unit no of this st adjustment gtn detail.
    *
    * @return the business unit no of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getBusinessUnitNo() {
        return _stAdjustmentGtnDetail.getBusinessUnitNo();
    }

    /**
    * Sets the business unit no of this st adjustment gtn detail.
    *
    * @param businessUnitNo the business unit no of this st adjustment gtn detail
    */
    @Override
    public void setBusinessUnitNo(java.lang.String businessUnitNo) {
        _stAdjustmentGtnDetail.setBusinessUnitNo(businessUnitNo);
    }

    /**
    * Returns the redemption period of this st adjustment gtn detail.
    *
    * @return the redemption period of this st adjustment gtn detail
    */
    @Override
    public java.util.Date getRedemptionPeriod() {
        return _stAdjustmentGtnDetail.getRedemptionPeriod();
    }

    /**
    * Sets the redemption period of this st adjustment gtn detail.
    *
    * @param redemptionPeriod the redemption period of this st adjustment gtn detail
    */
    @Override
    public void setRedemptionPeriod(java.util.Date redemptionPeriod) {
        _stAdjustmentGtnDetail.setRedemptionPeriod(redemptionPeriod);
    }

    /**
    * Returns the deduction ID of this st adjustment gtn detail.
    *
    * @return the deduction ID of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionId() {
        return _stAdjustmentGtnDetail.getDeductionId();
    }

    /**
    * Sets the deduction ID of this st adjustment gtn detail.
    *
    * @param deductionId the deduction ID of this st adjustment gtn detail
    */
    @Override
    public void setDeductionId(java.lang.String deductionId) {
        _stAdjustmentGtnDetail.setDeductionId(deductionId);
    }

    /**
    * Returns the gl year of this st adjustment gtn detail.
    *
    * @return the gl year of this st adjustment gtn detail
    */
    @Override
    public int getGlYear() {
        return _stAdjustmentGtnDetail.getGlYear();
    }

    /**
    * Sets the gl year of this st adjustment gtn detail.
    *
    * @param glYear the gl year of this st adjustment gtn detail
    */
    @Override
    public void setGlYear(int glYear) {
        _stAdjustmentGtnDetail.setGlYear(glYear);
    }

    /**
    * Returns the brand name of this st adjustment gtn detail.
    *
    * @return the brand name of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getBrandName() {
        return _stAdjustmentGtnDetail.getBrandName();
    }

    /**
    * Sets the brand name of this st adjustment gtn detail.
    *
    * @param brandName the brand name of this st adjustment gtn detail
    */
    @Override
    public void setBrandName(java.lang.String brandName) {
        _stAdjustmentGtnDetail.setBrandName(brandName);
    }

    /**
    * Returns the modified date of this st adjustment gtn detail.
    *
    * @return the modified date of this st adjustment gtn detail
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _stAdjustmentGtnDetail.getModifiedDate();
    }

    /**
    * Sets the modified date of this st adjustment gtn detail.
    *
    * @param modifiedDate the modified date of this st adjustment gtn detail
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _stAdjustmentGtnDetail.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the account of this st adjustment gtn detail.
    *
    * @return the account of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getAccount() {
        return _stAdjustmentGtnDetail.getAccount();
    }

    /**
    * Sets the account of this st adjustment gtn detail.
    *
    * @param account the account of this st adjustment gtn detail
    */
    @Override
    public void setAccount(java.lang.String account) {
        _stAdjustmentGtnDetail.setAccount(account);
    }

    /**
    * Returns the source of this st adjustment gtn detail.
    *
    * @return the source of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getSource() {
        return _stAdjustmentGtnDetail.getSource();
    }

    /**
    * Sets the source of this st adjustment gtn detail.
    *
    * @param source the source of this st adjustment gtn detail
    */
    @Override
    public void setSource(java.lang.String source) {
        _stAdjustmentGtnDetail.setSource(source);
    }

    /**
    * Returns the workflow approved date of this st adjustment gtn detail.
    *
    * @return the workflow approved date of this st adjustment gtn detail
    */
    @Override
    public java.util.Date getWorkflowApprovedDate() {
        return _stAdjustmentGtnDetail.getWorkflowApprovedDate();
    }

    /**
    * Sets the workflow approved date of this st adjustment gtn detail.
    *
    * @param workflowApprovedDate the workflow approved date of this st adjustment gtn detail
    */
    @Override
    public void setWorkflowApprovedDate(java.util.Date workflowApprovedDate) {
        _stAdjustmentGtnDetail.setWorkflowApprovedDate(workflowApprovedDate);
    }

    /**
    * Returns the udc6 of this st adjustment gtn detail.
    *
    * @return the udc6 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getUdc6() {
        return _stAdjustmentGtnDetail.getUdc6();
    }

    /**
    * Sets the udc6 of this st adjustment gtn detail.
    *
    * @param udc6 the udc6 of this st adjustment gtn detail
    */
    @Override
    public void setUdc6(java.lang.String udc6) {
        _stAdjustmentGtnDetail.setUdc6(udc6);
    }

    /**
    * Returns the business unit name of this st adjustment gtn detail.
    *
    * @return the business unit name of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getBusinessUnitName() {
        return _stAdjustmentGtnDetail.getBusinessUnitName();
    }

    /**
    * Sets the business unit name of this st adjustment gtn detail.
    *
    * @param businessUnitName the business unit name of this st adjustment gtn detail
    */
    @Override
    public void setBusinessUnitName(java.lang.String businessUnitName) {
        _stAdjustmentGtnDetail.setBusinessUnitName(businessUnitName);
    }

    /**
    * Returns the udc5 of this st adjustment gtn detail.
    *
    * @return the udc5 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getUdc5() {
        return _stAdjustmentGtnDetail.getUdc5();
    }

    /**
    * Sets the udc5 of this st adjustment gtn detail.
    *
    * @param udc5 the udc5 of this st adjustment gtn detail
    */
    @Override
    public void setUdc5(java.lang.String udc5) {
        _stAdjustmentGtnDetail.setUdc5(udc5);
    }

    /**
    * Returns the workflow created date of this st adjustment gtn detail.
    *
    * @return the workflow created date of this st adjustment gtn detail
    */
    @Override
    public java.util.Date getWorkflowCreatedDate() {
        return _stAdjustmentGtnDetail.getWorkflowCreatedDate();
    }

    /**
    * Sets the workflow created date of this st adjustment gtn detail.
    *
    * @param workflowCreatedDate the workflow created date of this st adjustment gtn detail
    */
    @Override
    public void setWorkflowCreatedDate(java.util.Date workflowCreatedDate) {
        _stAdjustmentGtnDetail.setWorkflowCreatedDate(workflowCreatedDate);
    }

    /**
    * Returns the udc4 of this st adjustment gtn detail.
    *
    * @return the udc4 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getUdc4() {
        return _stAdjustmentGtnDetail.getUdc4();
    }

    /**
    * Sets the udc4 of this st adjustment gtn detail.
    *
    * @param udc4 the udc4 of this st adjustment gtn detail
    */
    @Override
    public void setUdc4(java.lang.String udc4) {
        _stAdjustmentGtnDetail.setUdc4(udc4);
    }

    /**
    * Returns the udc3 of this st adjustment gtn detail.
    *
    * @return the udc3 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getUdc3() {
        return _stAdjustmentGtnDetail.getUdc3();
    }

    /**
    * Sets the udc3 of this st adjustment gtn detail.
    *
    * @param udc3 the udc3 of this st adjustment gtn detail
    */
    @Override
    public void setUdc3(java.lang.String udc3) {
        _stAdjustmentGtnDetail.setUdc3(udc3);
    }

    /**
    * Returns the udc2 of this st adjustment gtn detail.
    *
    * @return the udc2 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getUdc2() {
        return _stAdjustmentGtnDetail.getUdc2();
    }

    /**
    * Sets the udc2 of this st adjustment gtn detail.
    *
    * @param udc2 the udc2 of this st adjustment gtn detail
    */
    @Override
    public void setUdc2(java.lang.String udc2) {
        _stAdjustmentGtnDetail.setUdc2(udc2);
    }

    /**
    * Returns the udc1 of this st adjustment gtn detail.
    *
    * @return the udc1 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getUdc1() {
        return _stAdjustmentGtnDetail.getUdc1();
    }

    /**
    * Sets the udc1 of this st adjustment gtn detail.
    *
    * @param udc1 the udc1 of this st adjustment gtn detail
    */
    @Override
    public void setUdc1(java.lang.String udc1) {
        _stAdjustmentGtnDetail.setUdc1(udc1);
    }

    /**
    * Returns the adjustment type of this st adjustment gtn detail.
    *
    * @return the adjustment type of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getAdjustmentType() {
        return _stAdjustmentGtnDetail.getAdjustmentType();
    }

    /**
    * Sets the adjustment type of this st adjustment gtn detail.
    *
    * @param adjustmentType the adjustment type of this st adjustment gtn detail
    */
    @Override
    public void setAdjustmentType(java.lang.String adjustmentType) {
        _stAdjustmentGtnDetail.setAdjustmentType(adjustmentType);
    }

    /**
    * Returns the modified by of this st adjustment gtn detail.
    *
    * @return the modified by of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _stAdjustmentGtnDetail.getModifiedBy();
    }

    /**
    * Sets the modified by of this st adjustment gtn detail.
    *
    * @param modifiedBy the modified by of this st adjustment gtn detail
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _stAdjustmentGtnDetail.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the deduction type of this st adjustment gtn detail.
    *
    * @return the deduction type of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionType() {
        return _stAdjustmentGtnDetail.getDeductionType();
    }

    /**
    * Sets the deduction type of this st adjustment gtn detail.
    *
    * @param deductionType the deduction type of this st adjustment gtn detail
    */
    @Override
    public void setDeductionType(java.lang.String deductionType) {
        _stAdjustmentGtnDetail.setDeductionType(deductionType);
    }

    /**
    * Returns the check record of this st adjustment gtn detail.
    *
    * @return the check record of this st adjustment gtn detail
    */
    @Override
    public boolean getCheckRecord() {
        return _stAdjustmentGtnDetail.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this st adjustment gtn detail is check record.
    *
    * @return <code>true</code> if this st adjustment gtn detail is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _stAdjustmentGtnDetail.isCheckRecord();
    }

    /**
    * Sets whether this st adjustment gtn detail is check record.
    *
    * @param checkRecord the check record of this st adjustment gtn detail
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _stAdjustmentGtnDetail.setCheckRecord(checkRecord);
    }

    /**
    * Returns the contract name of this st adjustment gtn detail.
    *
    * @return the contract name of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getContractName() {
        return _stAdjustmentGtnDetail.getContractName();
    }

    /**
    * Sets the contract name of this st adjustment gtn detail.
    *
    * @param contractName the contract name of this st adjustment gtn detail
    */
    @Override
    public void setContractName(java.lang.String contractName) {
        _stAdjustmentGtnDetail.setContractName(contractName);
    }

    /**
    * Returns the deduction rate of this st adjustment gtn detail.
    *
    * @return the deduction rate of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionRate() {
        return _stAdjustmentGtnDetail.getDeductionRate();
    }

    /**
    * Sets the deduction rate of this st adjustment gtn detail.
    *
    * @param deductionRate the deduction rate of this st adjustment gtn detail
    */
    @Override
    public void setDeductionRate(java.lang.String deductionRate) {
        _stAdjustmentGtnDetail.setDeductionRate(deductionRate);
    }

    /**
    * Returns the deduction category of this st adjustment gtn detail.
    *
    * @return the deduction category of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionCategory() {
        return _stAdjustmentGtnDetail.getDeductionCategory();
    }

    /**
    * Sets the deduction category of this st adjustment gtn detail.
    *
    * @param deductionCategory the deduction category of this st adjustment gtn detail
    */
    @Override
    public void setDeductionCategory(java.lang.String deductionCategory) {
        _stAdjustmentGtnDetail.setDeductionCategory(deductionCategory);
    }

    /**
    * Returns the deduction no of this st adjustment gtn detail.
    *
    * @return the deduction no of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionNo() {
        return _stAdjustmentGtnDetail.getDeductionNo();
    }

    /**
    * Sets the deduction no of this st adjustment gtn detail.
    *
    * @param deductionNo the deduction no of this st adjustment gtn detail
    */
    @Override
    public void setDeductionNo(java.lang.String deductionNo) {
        _stAdjustmentGtnDetail.setDeductionNo(deductionNo);
    }

    /**
    * Returns the company no of this st adjustment gtn detail.
    *
    * @return the company no of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getCompanyNo() {
        return _stAdjustmentGtnDetail.getCompanyNo();
    }

    /**
    * Sets the company no of this st adjustment gtn detail.
    *
    * @param companyNo the company no of this st adjustment gtn detail
    */
    @Override
    public void setCompanyNo(java.lang.String companyNo) {
        _stAdjustmentGtnDetail.setCompanyNo(companyNo);
    }

    /**
    * Returns the session ID of this st adjustment gtn detail.
    *
    * @return the session ID of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getSessionId() {
        return _stAdjustmentGtnDetail.getSessionId();
    }

    /**
    * Sets the session ID of this st adjustment gtn detail.
    *
    * @param sessionId the session ID of this st adjustment gtn detail
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _stAdjustmentGtnDetail.setSessionId(sessionId);
    }

    /**
    * Returns the gl company ID of this st adjustment gtn detail.
    *
    * @return the gl company ID of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getGlCompanyId() {
        return _stAdjustmentGtnDetail.getGlCompanyId();
    }

    /**
    * Sets the gl company ID of this st adjustment gtn detail.
    *
    * @param glCompanyId the gl company ID of this st adjustment gtn detail
    */
    @Override
    public void setGlCompanyId(java.lang.String glCompanyId) {
        _stAdjustmentGtnDetail.setGlCompanyId(glCompanyId);
    }

    /**
    * Returns the item name of this st adjustment gtn detail.
    *
    * @return the item name of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getItemName() {
        return _stAdjustmentGtnDetail.getItemName();
    }

    /**
    * Sets the item name of this st adjustment gtn detail.
    *
    * @param itemName the item name of this st adjustment gtn detail
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _stAdjustmentGtnDetail.setItemName(itemName);
    }

    /**
    * Returns the deduction inclusion of this st adjustment gtn detail.
    *
    * @return the deduction inclusion of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionInclusion() {
        return _stAdjustmentGtnDetail.getDeductionInclusion();
    }

    /**
    * Sets the deduction inclusion of this st adjustment gtn detail.
    *
    * @param deductionInclusion the deduction inclusion of this st adjustment gtn detail
    */
    @Override
    public void setDeductionInclusion(java.lang.String deductionInclusion) {
        _stAdjustmentGtnDetail.setDeductionInclusion(deductionInclusion);
    }

    /**
    * Returns the deduction amount of this st adjustment gtn detail.
    *
    * @return the deduction amount of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionAmount() {
        return _stAdjustmentGtnDetail.getDeductionAmount();
    }

    /**
    * Sets the deduction amount of this st adjustment gtn detail.
    *
    * @param deductionAmount the deduction amount of this st adjustment gtn detail
    */
    @Override
    public void setDeductionAmount(java.lang.String deductionAmount) {
        _stAdjustmentGtnDetail.setDeductionAmount(deductionAmount);
    }

    /**
    * Returns the company name of this st adjustment gtn detail.
    *
    * @return the company name of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getCompanyName() {
        return _stAdjustmentGtnDetail.getCompanyName();
    }

    /**
    * Sets the company name of this st adjustment gtn detail.
    *
    * @param companyName the company name of this st adjustment gtn detail
    */
    @Override
    public void setCompanyName(java.lang.String companyName) {
        _stAdjustmentGtnDetail.setCompanyName(companyName);
    }

    /**
    * Returns the project of this st adjustment gtn detail.
    *
    * @return the project of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getProject() {
        return _stAdjustmentGtnDetail.getProject();
    }

    /**
    * Sets the project of this st adjustment gtn detail.
    *
    * @param project the project of this st adjustment gtn detail
    */
    @Override
    public void setProject(java.lang.String project) {
        _stAdjustmentGtnDetail.setProject(project);
    }

    /**
    * Returns the deduction udc3 of this st adjustment gtn detail.
    *
    * @return the deduction udc3 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionUdc3() {
        return _stAdjustmentGtnDetail.getDeductionUdc3();
    }

    /**
    * Sets the deduction udc3 of this st adjustment gtn detail.
    *
    * @param deductionUdc3 the deduction udc3 of this st adjustment gtn detail
    */
    @Override
    public void setDeductionUdc3(java.lang.String deductionUdc3) {
        _stAdjustmentGtnDetail.setDeductionUdc3(deductionUdc3);
    }

    /**
    * Returns the deduction udc4 of this st adjustment gtn detail.
    *
    * @return the deduction udc4 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionUdc4() {
        return _stAdjustmentGtnDetail.getDeductionUdc4();
    }

    /**
    * Sets the deduction udc4 of this st adjustment gtn detail.
    *
    * @param deductionUdc4 the deduction udc4 of this st adjustment gtn detail
    */
    @Override
    public void setDeductionUdc4(java.lang.String deductionUdc4) {
        _stAdjustmentGtnDetail.setDeductionUdc4(deductionUdc4);
    }

    /**
    * Returns the deduction udc1 of this st adjustment gtn detail.
    *
    * @return the deduction udc1 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionUdc1() {
        return _stAdjustmentGtnDetail.getDeductionUdc1();
    }

    /**
    * Sets the deduction udc1 of this st adjustment gtn detail.
    *
    * @param deductionUdc1 the deduction udc1 of this st adjustment gtn detail
    */
    @Override
    public void setDeductionUdc1(java.lang.String deductionUdc1) {
        _stAdjustmentGtnDetail.setDeductionUdc1(deductionUdc1);
    }

    /**
    * Returns the item ID of this st adjustment gtn detail.
    *
    * @return the item ID of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getItemId() {
        return _stAdjustmentGtnDetail.getItemId();
    }

    /**
    * Sets the item ID of this st adjustment gtn detail.
    *
    * @param itemId the item ID of this st adjustment gtn detail
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _stAdjustmentGtnDetail.setItemId(itemId);
    }

    /**
    * Returns the deduction udc2 of this st adjustment gtn detail.
    *
    * @return the deduction udc2 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionUdc2() {
        return _stAdjustmentGtnDetail.getDeductionUdc2();
    }

    /**
    * Sets the deduction udc2 of this st adjustment gtn detail.
    *
    * @param deductionUdc2 the deduction udc2 of this st adjustment gtn detail
    */
    @Override
    public void setDeductionUdc2(java.lang.String deductionUdc2) {
        _stAdjustmentGtnDetail.setDeductionUdc2(deductionUdc2);
    }

    /**
    * Returns the account type of this st adjustment gtn detail.
    *
    * @return the account type of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getAccountType() {
        return _stAdjustmentGtnDetail.getAccountType();
    }

    /**
    * Sets the account type of this st adjustment gtn detail.
    *
    * @param accountType the account type of this st adjustment gtn detail
    */
    @Override
    public void setAccountType(java.lang.String accountType) {
        _stAdjustmentGtnDetail.setAccountType(accountType);
    }

    /**
    * Returns the gl string of this st adjustment gtn detail.
    *
    * @return the gl string of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getGlString() {
        return _stAdjustmentGtnDetail.getGlString();
    }

    /**
    * Sets the gl string of this st adjustment gtn detail.
    *
    * @param glString the gl string of this st adjustment gtn detail
    */
    @Override
    public void setGlString(java.lang.String glString) {
        _stAdjustmentGtnDetail.setGlString(glString);
    }

    /**
    * Returns the created date of this st adjustment gtn detail.
    *
    * @return the created date of this st adjustment gtn detail
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _stAdjustmentGtnDetail.getCreatedDate();
    }

    /**
    * Sets the created date of this st adjustment gtn detail.
    *
    * @param createdDate the created date of this st adjustment gtn detail
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _stAdjustmentGtnDetail.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this st adjustment gtn detail.
    *
    * @return the created by of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _stAdjustmentGtnDetail.getCreatedBy();
    }

    /**
    * Sets the created by of this st adjustment gtn detail.
    *
    * @param createdBy the created by of this st adjustment gtn detail
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _stAdjustmentGtnDetail.setCreatedBy(createdBy);
    }

    /**
    * Returns the deduction udc6 of this st adjustment gtn detail.
    *
    * @return the deduction udc6 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionUdc6() {
        return _stAdjustmentGtnDetail.getDeductionUdc6();
    }

    /**
    * Sets the deduction udc6 of this st adjustment gtn detail.
    *
    * @param deductionUdc6 the deduction udc6 of this st adjustment gtn detail
    */
    @Override
    public void setDeductionUdc6(java.lang.String deductionUdc6) {
        _stAdjustmentGtnDetail.setDeductionUdc6(deductionUdc6);
    }

    /**
    * Returns the deduction udc5 of this st adjustment gtn detail.
    *
    * @return the deduction udc5 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionUdc5() {
        return _stAdjustmentGtnDetail.getDeductionUdc5();
    }

    /**
    * Sets the deduction udc5 of this st adjustment gtn detail.
    *
    * @param deductionUdc5 the deduction udc5 of this st adjustment gtn detail
    */
    @Override
    public void setDeductionUdc5(java.lang.String deductionUdc5) {
        _stAdjustmentGtnDetail.setDeductionUdc5(deductionUdc5);
    }

    /**
    * Returns the gl company name of this st adjustment gtn detail.
    *
    * @return the gl company name of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getGlCompanyName() {
        return _stAdjustmentGtnDetail.getGlCompanyName();
    }

    /**
    * Sets the gl company name of this st adjustment gtn detail.
    *
    * @param glCompanyName the gl company name of this st adjustment gtn detail
    */
    @Override
    public void setGlCompanyName(java.lang.String glCompanyName) {
        _stAdjustmentGtnDetail.setGlCompanyName(glCompanyName);
    }

    /**
    * Returns the workflow ID of this st adjustment gtn detail.
    *
    * @return the workflow ID of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getWorkflowId() {
        return _stAdjustmentGtnDetail.getWorkflowId();
    }

    /**
    * Sets the workflow ID of this st adjustment gtn detail.
    *
    * @param workflowId the workflow ID of this st adjustment gtn detail
    */
    @Override
    public void setWorkflowId(java.lang.String workflowId) {
        _stAdjustmentGtnDetail.setWorkflowId(workflowId);
    }

    /**
    * Returns the item no of this st adjustment gtn detail.
    *
    * @return the item no of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getItemNo() {
        return _stAdjustmentGtnDetail.getItemNo();
    }

    /**
    * Sets the item no of this st adjustment gtn detail.
    *
    * @param itemNo the item no of this st adjustment gtn detail
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _stAdjustmentGtnDetail.setItemNo(itemNo);
    }

    /**
    * Returns the contract ID of this st adjustment gtn detail.
    *
    * @return the contract ID of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getContractId() {
        return _stAdjustmentGtnDetail.getContractId();
    }

    /**
    * Sets the contract ID of this st adjustment gtn detail.
    *
    * @param contractId the contract ID of this st adjustment gtn detail
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _stAdjustmentGtnDetail.setContractId(contractId);
    }

    /**
    * Returns the deduction program of this st adjustment gtn detail.
    *
    * @return the deduction program of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionProgram() {
        return _stAdjustmentGtnDetail.getDeductionProgram();
    }

    /**
    * Sets the deduction program of this st adjustment gtn detail.
    *
    * @param deductionProgram the deduction program of this st adjustment gtn detail
    */
    @Override
    public void setDeductionProgram(java.lang.String deductionProgram) {
        _stAdjustmentGtnDetail.setDeductionProgram(deductionProgram);
    }

    /**
    * Returns the business unit ID of this st adjustment gtn detail.
    *
    * @return the business unit ID of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getBusinessUnitId() {
        return _stAdjustmentGtnDetail.getBusinessUnitId();
    }

    /**
    * Sets the business unit ID of this st adjustment gtn detail.
    *
    * @param businessUnitId the business unit ID of this st adjustment gtn detail
    */
    @Override
    public void setBusinessUnitId(java.lang.String businessUnitId) {
        _stAdjustmentGtnDetail.setBusinessUnitId(businessUnitId);
    }

    /**
    * Returns the user ID of this st adjustment gtn detail.
    *
    * @return the user ID of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getUserId() {
        return _stAdjustmentGtnDetail.getUserId();
    }

    /**
    * Sets the user ID of this st adjustment gtn detail.
    *
    * @param userId the user ID of this st adjustment gtn detail
    */
    @Override
    public void setUserId(java.lang.String userId) {
        _stAdjustmentGtnDetail.setUserId(userId);
    }

    /**
    * Returns the cost center of this st adjustment gtn detail.
    *
    * @return the cost center of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getCostCenter() {
        return _stAdjustmentGtnDetail.getCostCenter();
    }

    /**
    * Sets the cost center of this st adjustment gtn detail.
    *
    * @param costCenter the cost center of this st adjustment gtn detail
    */
    @Override
    public void setCostCenter(java.lang.String costCenter) {
        _stAdjustmentGtnDetail.setCostCenter(costCenter);
    }

    /**
    * Returns the company ID of this st adjustment gtn detail.
    *
    * @return the company ID of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getCompanyId() {
        return _stAdjustmentGtnDetail.getCompanyId();
    }

    /**
    * Sets the company ID of this st adjustment gtn detail.
    *
    * @param companyId the company ID of this st adjustment gtn detail
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _stAdjustmentGtnDetail.setCompanyId(companyId);
    }

    /**
    * Returns the outbound status of this st adjustment gtn detail.
    *
    * @return the outbound status of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getOutboundStatus() {
        return _stAdjustmentGtnDetail.getOutboundStatus();
    }

    /**
    * Sets the outbound status of this st adjustment gtn detail.
    *
    * @param outboundStatus the outbound status of this st adjustment gtn detail
    */
    @Override
    public void setOutboundStatus(java.lang.String outboundStatus) {
        _stAdjustmentGtnDetail.setOutboundStatus(outboundStatus);
    }

    /**
    * Returns the future1 of this st adjustment gtn detail.
    *
    * @return the future1 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getFuture1() {
        return _stAdjustmentGtnDetail.getFuture1();
    }

    /**
    * Sets the future1 of this st adjustment gtn detail.
    *
    * @param future1 the future1 of this st adjustment gtn detail
    */
    @Override
    public void setFuture1(java.lang.String future1) {
        _stAdjustmentGtnDetail.setFuture1(future1);
    }

    /**
    * Returns the brand ID of this st adjustment gtn detail.
    *
    * @return the brand ID of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getBrandId() {
        return _stAdjustmentGtnDetail.getBrandId();
    }

    /**
    * Sets the brand ID of this st adjustment gtn detail.
    *
    * @param brandId the brand ID of this st adjustment gtn detail
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _stAdjustmentGtnDetail.setBrandId(brandId);
    }

    /**
    * Returns the deduction name of this st adjustment gtn detail.
    *
    * @return the deduction name of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getDeductionName() {
        return _stAdjustmentGtnDetail.getDeductionName();
    }

    /**
    * Sets the deduction name of this st adjustment gtn detail.
    *
    * @param deductionName the deduction name of this st adjustment gtn detail
    */
    @Override
    public void setDeductionName(java.lang.String deductionName) {
        _stAdjustmentGtnDetail.setDeductionName(deductionName);
    }

    /**
    * Returns the future2 of this st adjustment gtn detail.
    *
    * @return the future2 of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getFuture2() {
        return _stAdjustmentGtnDetail.getFuture2();
    }

    /**
    * Sets the future2 of this st adjustment gtn detail.
    *
    * @param future2 the future2 of this st adjustment gtn detail
    */
    @Override
    public void setFuture2(java.lang.String future2) {
        _stAdjustmentGtnDetail.setFuture2(future2);
    }

    /**
    * Returns the workflow name of this st adjustment gtn detail.
    *
    * @return the workflow name of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getWorkflowName() {
        return _stAdjustmentGtnDetail.getWorkflowName();
    }

    /**
    * Sets the workflow name of this st adjustment gtn detail.
    *
    * @param workflowName the workflow name of this st adjustment gtn detail
    */
    @Override
    public void setWorkflowName(java.lang.String workflowName) {
        _stAdjustmentGtnDetail.setWorkflowName(workflowName);
    }

    /**
    * Returns the gl date of this st adjustment gtn detail.
    *
    * @return the gl date of this st adjustment gtn detail
    */
    @Override
    public java.util.Date getGlDate() {
        return _stAdjustmentGtnDetail.getGlDate();
    }

    /**
    * Sets the gl date of this st adjustment gtn detail.
    *
    * @param glDate the gl date of this st adjustment gtn detail
    */
    @Override
    public void setGlDate(java.util.Date glDate) {
        _stAdjustmentGtnDetail.setGlDate(glDate);
    }

    /**
    * Returns the workflow created by of this st adjustment gtn detail.
    *
    * @return the workflow created by of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getWorkflowCreatedBy() {
        return _stAdjustmentGtnDetail.getWorkflowCreatedBy();
    }

    /**
    * Sets the workflow created by of this st adjustment gtn detail.
    *
    * @param workflowCreatedBy the workflow created by of this st adjustment gtn detail
    */
    @Override
    public void setWorkflowCreatedBy(java.lang.String workflowCreatedBy) {
        _stAdjustmentGtnDetail.setWorkflowCreatedBy(workflowCreatedBy);
    }

    /**
    * Returns the gl month of this st adjustment gtn detail.
    *
    * @return the gl month of this st adjustment gtn detail
    */
    @Override
    public int getGlMonth() {
        return _stAdjustmentGtnDetail.getGlMonth();
    }

    /**
    * Sets the gl month of this st adjustment gtn detail.
    *
    * @param glMonth the gl month of this st adjustment gtn detail
    */
    @Override
    public void setGlMonth(int glMonth) {
        _stAdjustmentGtnDetail.setGlMonth(glMonth);
    }

    /**
    * Returns the batch ID of this st adjustment gtn detail.
    *
    * @return the batch ID of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getBatchId() {
        return _stAdjustmentGtnDetail.getBatchId();
    }

    /**
    * Sets the batch ID of this st adjustment gtn detail.
    *
    * @param batchId the batch ID of this st adjustment gtn detail
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _stAdjustmentGtnDetail.setBatchId(batchId);
    }

    /**
    * Returns the account category of this st adjustment gtn detail.
    *
    * @return the account category of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getAccountCategory() {
        return _stAdjustmentGtnDetail.getAccountCategory();
    }

    /**
    * Sets the account category of this st adjustment gtn detail.
    *
    * @param accountCategory the account category of this st adjustment gtn detail
    */
    @Override
    public void setAccountCategory(java.lang.String accountCategory) {
        _stAdjustmentGtnDetail.setAccountCategory(accountCategory);
    }

    /**
    * Returns the gl company no of this st adjustment gtn detail.
    *
    * @return the gl company no of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getGlCompanyNo() {
        return _stAdjustmentGtnDetail.getGlCompanyNo();
    }

    /**
    * Sets the gl company no of this st adjustment gtn detail.
    *
    * @param glCompanyNo the gl company no of this st adjustment gtn detail
    */
    @Override
    public void setGlCompanyNo(java.lang.String glCompanyNo) {
        _stAdjustmentGtnDetail.setGlCompanyNo(glCompanyNo);
    }

    /**
    * Returns the workflow approved by of this st adjustment gtn detail.
    *
    * @return the workflow approved by of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getWorkflowApprovedBy() {
        return _stAdjustmentGtnDetail.getWorkflowApprovedBy();
    }

    /**
    * Sets the workflow approved by of this st adjustment gtn detail.
    *
    * @param workflowApprovedBy the workflow approved by of this st adjustment gtn detail
    */
    @Override
    public void setWorkflowApprovedBy(java.lang.String workflowApprovedBy) {
        _stAdjustmentGtnDetail.setWorkflowApprovedBy(workflowApprovedBy);
    }

    /**
    * Returns the contract no of this st adjustment gtn detail.
    *
    * @return the contract no of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getContractNo() {
        return _stAdjustmentGtnDetail.getContractNo();
    }

    /**
    * Sets the contract no of this st adjustment gtn detail.
    *
    * @param contractNo the contract no of this st adjustment gtn detail
    */
    @Override
    public void setContractNo(java.lang.String contractNo) {
        _stAdjustmentGtnDetail.setContractNo(contractNo);
    }

    /**
    * Returns the original batch ID of this st adjustment gtn detail.
    *
    * @return the original batch ID of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getOriginalBatchId() {
        return _stAdjustmentGtnDetail.getOriginalBatchId();
    }

    /**
    * Sets the original batch ID of this st adjustment gtn detail.
    *
    * @param originalBatchId the original batch ID of this st adjustment gtn detail
    */
    @Override
    public void setOriginalBatchId(java.lang.String originalBatchId) {
        _stAdjustmentGtnDetail.setOriginalBatchId(originalBatchId);
    }

    /**
    * Returns the adjustment level of this st adjustment gtn detail.
    *
    * @return the adjustment level of this st adjustment gtn detail
    */
    @Override
    public java.lang.String getAdjustmentLevel() {
        return _stAdjustmentGtnDetail.getAdjustmentLevel();
    }

    /**
    * Sets the adjustment level of this st adjustment gtn detail.
    *
    * @param adjustmentLevel the adjustment level of this st adjustment gtn detail
    */
    @Override
    public void setAdjustmentLevel(java.lang.String adjustmentLevel) {
        _stAdjustmentGtnDetail.setAdjustmentLevel(adjustmentLevel);
    }

    @Override
    public boolean isNew() {
        return _stAdjustmentGtnDetail.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _stAdjustmentGtnDetail.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _stAdjustmentGtnDetail.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _stAdjustmentGtnDetail.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _stAdjustmentGtnDetail.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _stAdjustmentGtnDetail.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _stAdjustmentGtnDetail.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _stAdjustmentGtnDetail.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _stAdjustmentGtnDetail.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _stAdjustmentGtnDetail.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _stAdjustmentGtnDetail.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new StAdjustmentGtnDetailWrapper((StAdjustmentGtnDetail) _stAdjustmentGtnDetail.clone());
    }

    @Override
    public int compareTo(StAdjustmentGtnDetail stAdjustmentGtnDetail) {
        return _stAdjustmentGtnDetail.compareTo(stAdjustmentGtnDetail);
    }

    @Override
    public int hashCode() {
        return _stAdjustmentGtnDetail.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<StAdjustmentGtnDetail> toCacheModel() {
        return _stAdjustmentGtnDetail.toCacheModel();
    }

    @Override
    public StAdjustmentGtnDetail toEscapedModel() {
        return new StAdjustmentGtnDetailWrapper(_stAdjustmentGtnDetail.toEscapedModel());
    }

    @Override
    public StAdjustmentGtnDetail toUnescapedModel() {
        return new StAdjustmentGtnDetailWrapper(_stAdjustmentGtnDetail.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _stAdjustmentGtnDetail.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _stAdjustmentGtnDetail.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _stAdjustmentGtnDetail.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StAdjustmentGtnDetailWrapper)) {
            return false;
        }

        StAdjustmentGtnDetailWrapper stAdjustmentGtnDetailWrapper = (StAdjustmentGtnDetailWrapper) obj;

        if (Validator.equals(_stAdjustmentGtnDetail,
                    stAdjustmentGtnDetailWrapper._stAdjustmentGtnDetail)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public StAdjustmentGtnDetail getWrappedStAdjustmentGtnDetail() {
        return _stAdjustmentGtnDetail;
    }

    @Override
    public StAdjustmentGtnDetail getWrappedModel() {
        return _stAdjustmentGtnDetail;
    }

    @Override
    public void resetOriginalValues() {
        _stAdjustmentGtnDetail.resetOriginalValues();
    }
}
