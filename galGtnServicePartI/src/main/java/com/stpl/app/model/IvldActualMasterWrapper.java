package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldActualMaster}.
 * </p>
 *
 * @author
 * @see IvldActualMaster
 * @generated
 */
public class IvldActualMasterWrapper implements IvldActualMaster,
    ModelWrapper<IvldActualMaster> {
    private IvldActualMaster _ivldActualMaster;

    public IvldActualMasterWrapper(IvldActualMaster ivldActualMaster) {
        _ivldActualMaster = ivldActualMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldActualMaster.class;
    }

    @Override
    public String getModelClassName() {
        return IvldActualMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualIntfid", getActualIntfid());
        attributes.put("accountId", getAccountId());
        attributes.put("programStateCode", getProgramStateCode());
        attributes.put("settlementNo", getSettlementNo());
        attributes.put("accrualActualEndDate", getAccrualActualEndDate());
        attributes.put("actualId", getActualId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("divisionId", getDivisionId());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("quantityInclusion", getQuantityInclusion());
        attributes.put("cashPaidDate", getCashPaidDate());
        attributes.put("source", getSource());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("analysisCode", getAnalysisCode());
        attributes.put("accrualActualStartDate", getAccrualActualStartDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("salesAmount", getSalesAmount());
        attributes.put("quantity", getQuantity());
        attributes.put("sentOut", getSentOut());
        attributes.put("accountNo", getAccountNo());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("accountName", getAccountName());
        attributes.put("provisionId", getProvisionId());
        attributes.put("amount", getAmount());
        attributes.put("marketId", getMarketId());
        attributes.put("isActive", getIsActive());
        attributes.put("settlementMethodNo", getSettlementMethodNo());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("priceAdjustmentName", getPriceAdjustmentName());
        attributes.put("errorField", getErrorField());
        attributes.put("recordSequence", getRecordSequence());
        attributes.put("mandatedDiscountAmount", getMandatedDiscountAmount());
        attributes.put("parentcomDivmktBrandProdkey",
            getParentcomDivmktBrandProdkey());
        attributes.put("price", getPrice());
        attributes.put("dispensedDate", getDispensedDate());
        attributes.put("itemId", getItemId());
        attributes.put("accrualProcessed", getAccrualProcessed());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("invoiceLineNo", getInvoiceLineNo());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("itemNo", getItemNo());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("acctIdentifierCodeQualifier",
            getAcctIdentifierCodeQualifier());
        attributes.put("contractId", getContractId());
        attributes.put("brandId", getBrandId());
        attributes.put("comDivMktBrandProdKey", getComDivMktBrandProdKey());
        attributes.put("claimIndicator", getClaimIndicator());
        attributes.put("ivldActualMasterSid", getIvldActualMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("provisionClaimIndicator", getProvisionClaimIndicator());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String actualIntfid = (String) attributes.get("actualIntfid");

        if (actualIntfid != null) {
            setActualIntfid(actualIntfid);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        String programStateCode = (String) attributes.get("programStateCode");

        if (programStateCode != null) {
            setProgramStateCode(programStateCode);
        }

        String settlementNo = (String) attributes.get("settlementNo");

        if (settlementNo != null) {
            setSettlementNo(settlementNo);
        }

        String accrualActualEndDate = (String) attributes.get(
                "accrualActualEndDate");

        if (accrualActualEndDate != null) {
            setAccrualActualEndDate(accrualActualEndDate);
        }

        String actualId = (String) attributes.get("actualId");

        if (actualId != null) {
            setActualId(actualId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String divisionId = (String) attributes.get("divisionId");

        if (divisionId != null) {
            setDivisionId(divisionId);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String quantityInclusion = (String) attributes.get("quantityInclusion");

        if (quantityInclusion != null) {
            setQuantityInclusion(quantityInclusion);
        }

        String cashPaidDate = (String) attributes.get("cashPaidDate");

        if (cashPaidDate != null) {
            setCashPaidDate(cashPaidDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String analysisCode = (String) attributes.get("analysisCode");

        if (analysisCode != null) {
            setAnalysisCode(analysisCode);
        }

        String accrualActualStartDate = (String) attributes.get(
                "accrualActualStartDate");

        if (accrualActualStartDate != null) {
            setAccrualActualStartDate(accrualActualStartDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String salesAmount = (String) attributes.get("salesAmount");

        if (salesAmount != null) {
            setSalesAmount(salesAmount);
        }

        String quantity = (String) attributes.get("quantity");

        if (quantity != null) {
            setQuantity(quantity);
        }

        String sentOut = (String) attributes.get("sentOut");

        if (sentOut != null) {
            setSentOut(sentOut);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String accountName = (String) attributes.get("accountName");

        if (accountName != null) {
            setAccountName(accountName);
        }

        String provisionId = (String) attributes.get("provisionId");

        if (provisionId != null) {
            setProvisionId(provisionId);
        }

        String amount = (String) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        String marketId = (String) attributes.get("marketId");

        if (marketId != null) {
            setMarketId(marketId);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String settlementMethodNo = (String) attributes.get(
                "settlementMethodNo");

        if (settlementMethodNo != null) {
            setSettlementMethodNo(settlementMethodNo);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String priceAdjustmentName = (String) attributes.get(
                "priceAdjustmentName");

        if (priceAdjustmentName != null) {
            setPriceAdjustmentName(priceAdjustmentName);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String recordSequence = (String) attributes.get("recordSequence");

        if (recordSequence != null) {
            setRecordSequence(recordSequence);
        }

        String mandatedDiscountAmount = (String) attributes.get(
                "mandatedDiscountAmount");

        if (mandatedDiscountAmount != null) {
            setMandatedDiscountAmount(mandatedDiscountAmount);
        }

        String parentcomDivmktBrandProdkey = (String) attributes.get(
                "parentcomDivmktBrandProdkey");

        if (parentcomDivmktBrandProdkey != null) {
            setParentcomDivmktBrandProdkey(parentcomDivmktBrandProdkey);
        }

        String price = (String) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        String dispensedDate = (String) attributes.get("dispensedDate");

        if (dispensedDate != null) {
            setDispensedDate(dispensedDate);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String accrualProcessed = (String) attributes.get("accrualProcessed");

        if (accrualProcessed != null) {
            setAccrualProcessed(accrualProcessed);
        }

        String uploadDate = (String) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String invoiceLineNo = (String) attributes.get("invoiceLineNo");

        if (invoiceLineNo != null) {
            setInvoiceLineNo(invoiceLineNo);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String acctIdentifierCodeQualifier = (String) attributes.get(
                "acctIdentifierCodeQualifier");

        if (acctIdentifierCodeQualifier != null) {
            setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String comDivMktBrandProdKey = (String) attributes.get(
                "comDivMktBrandProdKey");

        if (comDivMktBrandProdKey != null) {
            setComDivMktBrandProdKey(comDivMktBrandProdKey);
        }

        String claimIndicator = (String) attributes.get("claimIndicator");

        if (claimIndicator != null) {
            setClaimIndicator(claimIndicator);
        }

        Integer ivldActualMasterSid = (Integer) attributes.get(
                "ivldActualMasterSid");

        if (ivldActualMasterSid != null) {
            setIvldActualMasterSid(ivldActualMasterSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String provisionClaimIndicator = (String) attributes.get(
                "provisionClaimIndicator");

        if (provisionClaimIndicator != null) {
            setProvisionClaimIndicator(provisionClaimIndicator);
        }
    }

    /**
    * Returns the primary key of this ivld actual master.
    *
    * @return the primary key of this ivld actual master
    */
    @Override
    public int getPrimaryKey() {
        return _ivldActualMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld actual master.
    *
    * @param primaryKey the primary key of this ivld actual master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldActualMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the actual intfid of this ivld actual master.
    *
    * @return the actual intfid of this ivld actual master
    */
    @Override
    public java.lang.String getActualIntfid() {
        return _ivldActualMaster.getActualIntfid();
    }

    /**
    * Sets the actual intfid of this ivld actual master.
    *
    * @param actualIntfid the actual intfid of this ivld actual master
    */
    @Override
    public void setActualIntfid(java.lang.String actualIntfid) {
        _ivldActualMaster.setActualIntfid(actualIntfid);
    }

    /**
    * Returns the account ID of this ivld actual master.
    *
    * @return the account ID of this ivld actual master
    */
    @Override
    public java.lang.String getAccountId() {
        return _ivldActualMaster.getAccountId();
    }

    /**
    * Sets the account ID of this ivld actual master.
    *
    * @param accountId the account ID of this ivld actual master
    */
    @Override
    public void setAccountId(java.lang.String accountId) {
        _ivldActualMaster.setAccountId(accountId);
    }

    /**
    * Returns the program state code of this ivld actual master.
    *
    * @return the program state code of this ivld actual master
    */
    @Override
    public java.lang.String getProgramStateCode() {
        return _ivldActualMaster.getProgramStateCode();
    }

    /**
    * Sets the program state code of this ivld actual master.
    *
    * @param programStateCode the program state code of this ivld actual master
    */
    @Override
    public void setProgramStateCode(java.lang.String programStateCode) {
        _ivldActualMaster.setProgramStateCode(programStateCode);
    }

    /**
    * Returns the settlement no of this ivld actual master.
    *
    * @return the settlement no of this ivld actual master
    */
    @Override
    public java.lang.String getSettlementNo() {
        return _ivldActualMaster.getSettlementNo();
    }

    /**
    * Sets the settlement no of this ivld actual master.
    *
    * @param settlementNo the settlement no of this ivld actual master
    */
    @Override
    public void setSettlementNo(java.lang.String settlementNo) {
        _ivldActualMaster.setSettlementNo(settlementNo);
    }

    /**
    * Returns the accrual actual end date of this ivld actual master.
    *
    * @return the accrual actual end date of this ivld actual master
    */
    @Override
    public java.lang.String getAccrualActualEndDate() {
        return _ivldActualMaster.getAccrualActualEndDate();
    }

    /**
    * Sets the accrual actual end date of this ivld actual master.
    *
    * @param accrualActualEndDate the accrual actual end date of this ivld actual master
    */
    @Override
    public void setAccrualActualEndDate(java.lang.String accrualActualEndDate) {
        _ivldActualMaster.setAccrualActualEndDate(accrualActualEndDate);
    }

    /**
    * Returns the actual ID of this ivld actual master.
    *
    * @return the actual ID of this ivld actual master
    */
    @Override
    public java.lang.String getActualId() {
        return _ivldActualMaster.getActualId();
    }

    /**
    * Sets the actual ID of this ivld actual master.
    *
    * @param actualId the actual ID of this ivld actual master
    */
    @Override
    public void setActualId(java.lang.String actualId) {
        _ivldActualMaster.setActualId(actualId);
    }

    /**
    * Returns the modified date of this ivld actual master.
    *
    * @return the modified date of this ivld actual master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldActualMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld actual master.
    *
    * @param modifiedDate the modified date of this ivld actual master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldActualMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the division ID of this ivld actual master.
    *
    * @return the division ID of this ivld actual master
    */
    @Override
    public java.lang.String getDivisionId() {
        return _ivldActualMaster.getDivisionId();
    }

    /**
    * Sets the division ID of this ivld actual master.
    *
    * @param divisionId the division ID of this ivld actual master
    */
    @Override
    public void setDivisionId(java.lang.String divisionId) {
        _ivldActualMaster.setDivisionId(divisionId);
    }

    /**
    * Returns the organization key of this ivld actual master.
    *
    * @return the organization key of this ivld actual master
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _ivldActualMaster.getOrganizationKey();
    }

    /**
    * Sets the organization key of this ivld actual master.
    *
    * @param organizationKey the organization key of this ivld actual master
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _ivldActualMaster.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the quantity inclusion of this ivld actual master.
    *
    * @return the quantity inclusion of this ivld actual master
    */
    @Override
    public java.lang.String getQuantityInclusion() {
        return _ivldActualMaster.getQuantityInclusion();
    }

    /**
    * Sets the quantity inclusion of this ivld actual master.
    *
    * @param quantityInclusion the quantity inclusion of this ivld actual master
    */
    @Override
    public void setQuantityInclusion(java.lang.String quantityInclusion) {
        _ivldActualMaster.setQuantityInclusion(quantityInclusion);
    }

    /**
    * Returns the cash paid date of this ivld actual master.
    *
    * @return the cash paid date of this ivld actual master
    */
    @Override
    public java.lang.String getCashPaidDate() {
        return _ivldActualMaster.getCashPaidDate();
    }

    /**
    * Sets the cash paid date of this ivld actual master.
    *
    * @param cashPaidDate the cash paid date of this ivld actual master
    */
    @Override
    public void setCashPaidDate(java.lang.String cashPaidDate) {
        _ivldActualMaster.setCashPaidDate(cashPaidDate);
    }

    /**
    * Returns the source of this ivld actual master.
    *
    * @return the source of this ivld actual master
    */
    @Override
    public java.lang.String getSource() {
        return _ivldActualMaster.getSource();
    }

    /**
    * Sets the source of this ivld actual master.
    *
    * @param source the source of this ivld actual master
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldActualMaster.setSource(source);
    }

    /**
    * Returns the add chg del indicator of this ivld actual master.
    *
    * @return the add chg del indicator of this ivld actual master
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldActualMaster.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld actual master.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld actual master
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldActualMaster.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the analysis code of this ivld actual master.
    *
    * @return the analysis code of this ivld actual master
    */
    @Override
    public java.lang.String getAnalysisCode() {
        return _ivldActualMaster.getAnalysisCode();
    }

    /**
    * Sets the analysis code of this ivld actual master.
    *
    * @param analysisCode the analysis code of this ivld actual master
    */
    @Override
    public void setAnalysisCode(java.lang.String analysisCode) {
        _ivldActualMaster.setAnalysisCode(analysisCode);
    }

    /**
    * Returns the accrual actual start date of this ivld actual master.
    *
    * @return the accrual actual start date of this ivld actual master
    */
    @Override
    public java.lang.String getAccrualActualStartDate() {
        return _ivldActualMaster.getAccrualActualStartDate();
    }

    /**
    * Sets the accrual actual start date of this ivld actual master.
    *
    * @param accrualActualStartDate the accrual actual start date of this ivld actual master
    */
    @Override
    public void setAccrualActualStartDate(
        java.lang.String accrualActualStartDate) {
        _ivldActualMaster.setAccrualActualStartDate(accrualActualStartDate);
    }

    /**
    * Returns the modified by of this ivld actual master.
    *
    * @return the modified by of this ivld actual master
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldActualMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld actual master.
    *
    * @param modifiedBy the modified by of this ivld actual master
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldActualMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the sales amount of this ivld actual master.
    *
    * @return the sales amount of this ivld actual master
    */
    @Override
    public java.lang.String getSalesAmount() {
        return _ivldActualMaster.getSalesAmount();
    }

    /**
    * Sets the sales amount of this ivld actual master.
    *
    * @param salesAmount the sales amount of this ivld actual master
    */
    @Override
    public void setSalesAmount(java.lang.String salesAmount) {
        _ivldActualMaster.setSalesAmount(salesAmount);
    }

    /**
    * Returns the quantity of this ivld actual master.
    *
    * @return the quantity of this ivld actual master
    */
    @Override
    public java.lang.String getQuantity() {
        return _ivldActualMaster.getQuantity();
    }

    /**
    * Sets the quantity of this ivld actual master.
    *
    * @param quantity the quantity of this ivld actual master
    */
    @Override
    public void setQuantity(java.lang.String quantity) {
        _ivldActualMaster.setQuantity(quantity);
    }

    /**
    * Returns the sent out of this ivld actual master.
    *
    * @return the sent out of this ivld actual master
    */
    @Override
    public java.lang.String getSentOut() {
        return _ivldActualMaster.getSentOut();
    }

    /**
    * Sets the sent out of this ivld actual master.
    *
    * @param sentOut the sent out of this ivld actual master
    */
    @Override
    public void setSentOut(java.lang.String sentOut) {
        _ivldActualMaster.setSentOut(sentOut);
    }

    /**
    * Returns the account no of this ivld actual master.
    *
    * @return the account no of this ivld actual master
    */
    @Override
    public java.lang.String getAccountNo() {
        return _ivldActualMaster.getAccountNo();
    }

    /**
    * Sets the account no of this ivld actual master.
    *
    * @param accountNo the account no of this ivld actual master
    */
    @Override
    public void setAccountNo(java.lang.String accountNo) {
        _ivldActualMaster.setAccountNo(accountNo);
    }

    /**
    * Returns the reason for failure of this ivld actual master.
    *
    * @return the reason for failure of this ivld actual master
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldActualMaster.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld actual master.
    *
    * @param reasonForFailure the reason for failure of this ivld actual master
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldActualMaster.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the account name of this ivld actual master.
    *
    * @return the account name of this ivld actual master
    */
    @Override
    public java.lang.String getAccountName() {
        return _ivldActualMaster.getAccountName();
    }

    /**
    * Sets the account name of this ivld actual master.
    *
    * @param accountName the account name of this ivld actual master
    */
    @Override
    public void setAccountName(java.lang.String accountName) {
        _ivldActualMaster.setAccountName(accountName);
    }

    /**
    * Returns the provision ID of this ivld actual master.
    *
    * @return the provision ID of this ivld actual master
    */
    @Override
    public java.lang.String getProvisionId() {
        return _ivldActualMaster.getProvisionId();
    }

    /**
    * Sets the provision ID of this ivld actual master.
    *
    * @param provisionId the provision ID of this ivld actual master
    */
    @Override
    public void setProvisionId(java.lang.String provisionId) {
        _ivldActualMaster.setProvisionId(provisionId);
    }

    /**
    * Returns the amount of this ivld actual master.
    *
    * @return the amount of this ivld actual master
    */
    @Override
    public java.lang.String getAmount() {
        return _ivldActualMaster.getAmount();
    }

    /**
    * Sets the amount of this ivld actual master.
    *
    * @param amount the amount of this ivld actual master
    */
    @Override
    public void setAmount(java.lang.String amount) {
        _ivldActualMaster.setAmount(amount);
    }

    /**
    * Returns the market ID of this ivld actual master.
    *
    * @return the market ID of this ivld actual master
    */
    @Override
    public java.lang.String getMarketId() {
        return _ivldActualMaster.getMarketId();
    }

    /**
    * Sets the market ID of this ivld actual master.
    *
    * @param marketId the market ID of this ivld actual master
    */
    @Override
    public void setMarketId(java.lang.String marketId) {
        _ivldActualMaster.setMarketId(marketId);
    }

    /**
    * Returns the is active of this ivld actual master.
    *
    * @return the is active of this ivld actual master
    */
    @Override
    public java.lang.String getIsActive() {
        return _ivldActualMaster.getIsActive();
    }

    /**
    * Sets the is active of this ivld actual master.
    *
    * @param isActive the is active of this ivld actual master
    */
    @Override
    public void setIsActive(java.lang.String isActive) {
        _ivldActualMaster.setIsActive(isActive);
    }

    /**
    * Returns the settlement method no of this ivld actual master.
    *
    * @return the settlement method no of this ivld actual master
    */
    @Override
    public java.lang.String getSettlementMethodNo() {
        return _ivldActualMaster.getSettlementMethodNo();
    }

    /**
    * Sets the settlement method no of this ivld actual master.
    *
    * @param settlementMethodNo the settlement method no of this ivld actual master
    */
    @Override
    public void setSettlementMethodNo(java.lang.String settlementMethodNo) {
        _ivldActualMaster.setSettlementMethodNo(settlementMethodNo);
    }

    /**
    * Returns the item identifier code qualifier of this ivld actual master.
    *
    * @return the item identifier code qualifier of this ivld actual master
    */
    @Override
    public java.lang.String getItemIdentifierCodeQualifier() {
        return _ivldActualMaster.getItemIdentifierCodeQualifier();
    }

    /**
    * Sets the item identifier code qualifier of this ivld actual master.
    *
    * @param itemIdentifierCodeQualifier the item identifier code qualifier of this ivld actual master
    */
    @Override
    public void setItemIdentifierCodeQualifier(
        java.lang.String itemIdentifierCodeQualifier) {
        _ivldActualMaster.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
    }

    /**
    * Returns the price adjustment name of this ivld actual master.
    *
    * @return the price adjustment name of this ivld actual master
    */
    @Override
    public java.lang.String getPriceAdjustmentName() {
        return _ivldActualMaster.getPriceAdjustmentName();
    }

    /**
    * Sets the price adjustment name of this ivld actual master.
    *
    * @param priceAdjustmentName the price adjustment name of this ivld actual master
    */
    @Override
    public void setPriceAdjustmentName(java.lang.String priceAdjustmentName) {
        _ivldActualMaster.setPriceAdjustmentName(priceAdjustmentName);
    }

    /**
    * Returns the error field of this ivld actual master.
    *
    * @return the error field of this ivld actual master
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldActualMaster.getErrorField();
    }

    /**
    * Sets the error field of this ivld actual master.
    *
    * @param errorField the error field of this ivld actual master
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldActualMaster.setErrorField(errorField);
    }

    /**
    * Returns the record sequence of this ivld actual master.
    *
    * @return the record sequence of this ivld actual master
    */
    @Override
    public java.lang.String getRecordSequence() {
        return _ivldActualMaster.getRecordSequence();
    }

    /**
    * Sets the record sequence of this ivld actual master.
    *
    * @param recordSequence the record sequence of this ivld actual master
    */
    @Override
    public void setRecordSequence(java.lang.String recordSequence) {
        _ivldActualMaster.setRecordSequence(recordSequence);
    }

    /**
    * Returns the mandated discount amount of this ivld actual master.
    *
    * @return the mandated discount amount of this ivld actual master
    */
    @Override
    public java.lang.String getMandatedDiscountAmount() {
        return _ivldActualMaster.getMandatedDiscountAmount();
    }

    /**
    * Sets the mandated discount amount of this ivld actual master.
    *
    * @param mandatedDiscountAmount the mandated discount amount of this ivld actual master
    */
    @Override
    public void setMandatedDiscountAmount(
        java.lang.String mandatedDiscountAmount) {
        _ivldActualMaster.setMandatedDiscountAmount(mandatedDiscountAmount);
    }

    /**
    * Returns the parentcom divmkt brand prodkey of this ivld actual master.
    *
    * @return the parentcom divmkt brand prodkey of this ivld actual master
    */
    @Override
    public java.lang.String getParentcomDivmktBrandProdkey() {
        return _ivldActualMaster.getParentcomDivmktBrandProdkey();
    }

    /**
    * Sets the parentcom divmkt brand prodkey of this ivld actual master.
    *
    * @param parentcomDivmktBrandProdkey the parentcom divmkt brand prodkey of this ivld actual master
    */
    @Override
    public void setParentcomDivmktBrandProdkey(
        java.lang.String parentcomDivmktBrandProdkey) {
        _ivldActualMaster.setParentcomDivmktBrandProdkey(parentcomDivmktBrandProdkey);
    }

    /**
    * Returns the price of this ivld actual master.
    *
    * @return the price of this ivld actual master
    */
    @Override
    public java.lang.String getPrice() {
        return _ivldActualMaster.getPrice();
    }

    /**
    * Sets the price of this ivld actual master.
    *
    * @param price the price of this ivld actual master
    */
    @Override
    public void setPrice(java.lang.String price) {
        _ivldActualMaster.setPrice(price);
    }

    /**
    * Returns the dispensed date of this ivld actual master.
    *
    * @return the dispensed date of this ivld actual master
    */
    @Override
    public java.lang.String getDispensedDate() {
        return _ivldActualMaster.getDispensedDate();
    }

    /**
    * Sets the dispensed date of this ivld actual master.
    *
    * @param dispensedDate the dispensed date of this ivld actual master
    */
    @Override
    public void setDispensedDate(java.lang.String dispensedDate) {
        _ivldActualMaster.setDispensedDate(dispensedDate);
    }

    /**
    * Returns the item ID of this ivld actual master.
    *
    * @return the item ID of this ivld actual master
    */
    @Override
    public java.lang.String getItemId() {
        return _ivldActualMaster.getItemId();
    }

    /**
    * Sets the item ID of this ivld actual master.
    *
    * @param itemId the item ID of this ivld actual master
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ivldActualMaster.setItemId(itemId);
    }

    /**
    * Returns the accrual processed of this ivld actual master.
    *
    * @return the accrual processed of this ivld actual master
    */
    @Override
    public java.lang.String getAccrualProcessed() {
        return _ivldActualMaster.getAccrualProcessed();
    }

    /**
    * Sets the accrual processed of this ivld actual master.
    *
    * @param accrualProcessed the accrual processed of this ivld actual master
    */
    @Override
    public void setAccrualProcessed(java.lang.String accrualProcessed) {
        _ivldActualMaster.setAccrualProcessed(accrualProcessed);
    }

    /**
    * Returns the upload date of this ivld actual master.
    *
    * @return the upload date of this ivld actual master
    */
    @Override
    public java.lang.String getUploadDate() {
        return _ivldActualMaster.getUploadDate();
    }

    /**
    * Sets the upload date of this ivld actual master.
    *
    * @param uploadDate the upload date of this ivld actual master
    */
    @Override
    public void setUploadDate(java.lang.String uploadDate) {
        _ivldActualMaster.setUploadDate(uploadDate);
    }

    /**
    * Returns the created by of this ivld actual master.
    *
    * @return the created by of this ivld actual master
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldActualMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld actual master.
    *
    * @param createdBy the created by of this ivld actual master
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldActualMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld actual master.
    *
    * @return the created date of this ivld actual master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldActualMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld actual master.
    *
    * @param createdDate the created date of this ivld actual master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldActualMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the invoice line no of this ivld actual master.
    *
    * @return the invoice line no of this ivld actual master
    */
    @Override
    public java.lang.String getInvoiceLineNo() {
        return _ivldActualMaster.getInvoiceLineNo();
    }

    /**
    * Sets the invoice line no of this ivld actual master.
    *
    * @param invoiceLineNo the invoice line no of this ivld actual master
    */
    @Override
    public void setInvoiceLineNo(java.lang.String invoiceLineNo) {
        _ivldActualMaster.setInvoiceLineNo(invoiceLineNo);
    }

    /**
    * Returns the error code of this ivld actual master.
    *
    * @return the error code of this ivld actual master
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldActualMaster.getErrorCode();
    }

    /**
    * Sets the error code of this ivld actual master.
    *
    * @param errorCode the error code of this ivld actual master
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldActualMaster.setErrorCode(errorCode);
    }

    /**
    * Returns the intf inserted date of this ivld actual master.
    *
    * @return the intf inserted date of this ivld actual master
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldActualMaster.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld actual master.
    *
    * @param intfInsertedDate the intf inserted date of this ivld actual master
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldActualMaster.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the item no of this ivld actual master.
    *
    * @return the item no of this ivld actual master
    */
    @Override
    public java.lang.String getItemNo() {
        return _ivldActualMaster.getItemNo();
    }

    /**
    * Sets the item no of this ivld actual master.
    *
    * @param itemNo the item no of this ivld actual master
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _ivldActualMaster.setItemNo(itemNo);
    }

    /**
    * Returns the reprocessed flag of this ivld actual master.
    *
    * @return the reprocessed flag of this ivld actual master
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldActualMaster.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld actual master.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld actual master
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldActualMaster.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the acct identifier code qualifier of this ivld actual master.
    *
    * @return the acct identifier code qualifier of this ivld actual master
    */
    @Override
    public java.lang.String getAcctIdentifierCodeQualifier() {
        return _ivldActualMaster.getAcctIdentifierCodeQualifier();
    }

    /**
    * Sets the acct identifier code qualifier of this ivld actual master.
    *
    * @param acctIdentifierCodeQualifier the acct identifier code qualifier of this ivld actual master
    */
    @Override
    public void setAcctIdentifierCodeQualifier(
        java.lang.String acctIdentifierCodeQualifier) {
        _ivldActualMaster.setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
    }

    /**
    * Returns the contract ID of this ivld actual master.
    *
    * @return the contract ID of this ivld actual master
    */
    @Override
    public java.lang.String getContractId() {
        return _ivldActualMaster.getContractId();
    }

    /**
    * Sets the contract ID of this ivld actual master.
    *
    * @param contractId the contract ID of this ivld actual master
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _ivldActualMaster.setContractId(contractId);
    }

    /**
    * Returns the brand ID of this ivld actual master.
    *
    * @return the brand ID of this ivld actual master
    */
    @Override
    public java.lang.String getBrandId() {
        return _ivldActualMaster.getBrandId();
    }

    /**
    * Sets the brand ID of this ivld actual master.
    *
    * @param brandId the brand ID of this ivld actual master
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _ivldActualMaster.setBrandId(brandId);
    }

    /**
    * Returns the com div mkt brand prod key of this ivld actual master.
    *
    * @return the com div mkt brand prod key of this ivld actual master
    */
    @Override
    public java.lang.String getComDivMktBrandProdKey() {
        return _ivldActualMaster.getComDivMktBrandProdKey();
    }

    /**
    * Sets the com div mkt brand prod key of this ivld actual master.
    *
    * @param comDivMktBrandProdKey the com div mkt brand prod key of this ivld actual master
    */
    @Override
    public void setComDivMktBrandProdKey(java.lang.String comDivMktBrandProdKey) {
        _ivldActualMaster.setComDivMktBrandProdKey(comDivMktBrandProdKey);
    }

    /**
    * Returns the claim indicator of this ivld actual master.
    *
    * @return the claim indicator of this ivld actual master
    */
    @Override
    public java.lang.String getClaimIndicator() {
        return _ivldActualMaster.getClaimIndicator();
    }

    /**
    * Sets the claim indicator of this ivld actual master.
    *
    * @param claimIndicator the claim indicator of this ivld actual master
    */
    @Override
    public void setClaimIndicator(java.lang.String claimIndicator) {
        _ivldActualMaster.setClaimIndicator(claimIndicator);
    }

    /**
    * Returns the ivld actual master sid of this ivld actual master.
    *
    * @return the ivld actual master sid of this ivld actual master
    */
    @Override
    public int getIvldActualMasterSid() {
        return _ivldActualMaster.getIvldActualMasterSid();
    }

    /**
    * Sets the ivld actual master sid of this ivld actual master.
    *
    * @param ivldActualMasterSid the ivld actual master sid of this ivld actual master
    */
    @Override
    public void setIvldActualMasterSid(int ivldActualMasterSid) {
        _ivldActualMaster.setIvldActualMasterSid(ivldActualMasterSid);
    }

    /**
    * Returns the batch ID of this ivld actual master.
    *
    * @return the batch ID of this ivld actual master
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldActualMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld actual master.
    *
    * @param batchId the batch ID of this ivld actual master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldActualMaster.setBatchId(batchId);
    }

    /**
    * Returns the provision claim indicator of this ivld actual master.
    *
    * @return the provision claim indicator of this ivld actual master
    */
    @Override
    public java.lang.String getProvisionClaimIndicator() {
        return _ivldActualMaster.getProvisionClaimIndicator();
    }

    /**
    * Sets the provision claim indicator of this ivld actual master.
    *
    * @param provisionClaimIndicator the provision claim indicator of this ivld actual master
    */
    @Override
    public void setProvisionClaimIndicator(
        java.lang.String provisionClaimIndicator) {
        _ivldActualMaster.setProvisionClaimIndicator(provisionClaimIndicator);
    }

    @Override
    public boolean isNew() {
        return _ivldActualMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldActualMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldActualMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldActualMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldActualMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldActualMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldActualMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldActualMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldActualMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldActualMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldActualMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldActualMasterWrapper((IvldActualMaster) _ivldActualMaster.clone());
    }

    @Override
    public int compareTo(IvldActualMaster ivldActualMaster) {
        return _ivldActualMaster.compareTo(ivldActualMaster);
    }

    @Override
    public int hashCode() {
        return _ivldActualMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldActualMaster> toCacheModel() {
        return _ivldActualMaster.toCacheModel();
    }

    @Override
    public IvldActualMaster toEscapedModel() {
        return new IvldActualMasterWrapper(_ivldActualMaster.toEscapedModel());
    }

    @Override
    public IvldActualMaster toUnescapedModel() {
        return new IvldActualMasterWrapper(_ivldActualMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldActualMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldActualMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldActualMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldActualMasterWrapper)) {
            return false;
        }

        IvldActualMasterWrapper ivldActualMasterWrapper = (IvldActualMasterWrapper) obj;

        if (Validator.equals(_ivldActualMaster,
                    ivldActualMasterWrapper._ivldActualMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldActualMaster getWrappedIvldActualMaster() {
        return _ivldActualMaster;
    }

    @Override
    public IvldActualMaster getWrappedModel() {
        return _ivldActualMaster;
    }

    @Override
    public void resetOriginalValues() {
        _ivldActualMaster.resetOriginalValues();
    }
}
