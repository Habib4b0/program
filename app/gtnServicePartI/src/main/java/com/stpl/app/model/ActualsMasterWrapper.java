package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ActualsMaster}.
 * </p>
 *
 * @author
 * @see ActualsMaster
 * @generated
 */
public class ActualsMasterWrapper implements ActualsMaster,
    ModelWrapper<ActualsMaster> {
    private ActualsMaster _actualsMaster;

    public ActualsMasterWrapper(ActualsMaster actualsMaster) {
        _actualsMaster = actualsMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return ActualsMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ActualsMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("quantityInclusion", getQuantityInclusion());
        attributes.put("mandatedDiscountAmount", getMandatedDiscountAmount());
        attributes.put("itemNo", getItemNo());
        attributes.put("analysisCode", getAnalysisCode());
        attributes.put("recordSequence", getRecordSequence());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("settlementMethodNo", getSettlementMethodNo());
        attributes.put("quantity", getQuantity());
        attributes.put("accountId", getAccountId());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("provisionClaimIndicator", getProvisionClaimIndicator());
        attributes.put("dispensedDate", getDispensedDate());
        attributes.put("isActive", getIsActive());
        attributes.put("batchId", getBatchId());
        attributes.put("accrualActualEndDate", getAccrualActualEndDate());
        attributes.put("marketId", getMarketId());
        attributes.put("brandId", getBrandId());
        attributes.put("accountName", getAccountName());
        attributes.put("amount", getAmount());
        attributes.put("actualsMasterSid", getActualsMasterSid());
        attributes.put("acctIdentifierCodeQualifier",
            getAcctIdentifierCodeQualifier());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("accrualProcessed", getAccrualProcessed());
        attributes.put("parentcomDivmktBrandProdkey",
            getParentcomDivmktBrandProdkey());
        attributes.put("cashPaidDate", getCashPaidDate());
        attributes.put("salesAmount", getSalesAmount());
        attributes.put("accrualActualStartDate", getAccrualActualStartDate());
        attributes.put("settlementNo", getSettlementNo());
        attributes.put("price", getPrice());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("claimIndicator", getClaimIndicator());
        attributes.put("itemId", getItemId());
        attributes.put("priceAdjustmentName", getPriceAdjustmentName());
        attributes.put("contractId", getContractId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("actualId", getActualId());
        attributes.put("provisionId", getProvisionId());
        attributes.put("sentOut", getSentOut());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("divisionId", getDivisionId());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("programStateCode", getProgramStateCode());
        attributes.put("source", getSource());
        attributes.put("invoiceLineNo", getInvoiceLineNo());
        attributes.put("accountNo", getAccountNo());
        attributes.put("comDivMktBrandProdKey", getComDivMktBrandProdKey());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String quantityInclusion = (String) attributes.get("quantityInclusion");

        if (quantityInclusion != null) {
            setQuantityInclusion(quantityInclusion);
        }

        Double mandatedDiscountAmount = (Double) attributes.get(
                "mandatedDiscountAmount");

        if (mandatedDiscountAmount != null) {
            setMandatedDiscountAmount(mandatedDiscountAmount);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String analysisCode = (String) attributes.get("analysisCode");

        if (analysisCode != null) {
            setAnalysisCode(analysisCode);
        }

        String recordSequence = (String) attributes.get("recordSequence");

        if (recordSequence != null) {
            setRecordSequence(recordSequence);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String settlementMethodNo = (String) attributes.get(
                "settlementMethodNo");

        if (settlementMethodNo != null) {
            setSettlementMethodNo(settlementMethodNo);
        }

        String quantity = (String) attributes.get("quantity");

        if (quantity != null) {
            setQuantity(quantity);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String provisionClaimIndicator = (String) attributes.get(
                "provisionClaimIndicator");

        if (provisionClaimIndicator != null) {
            setProvisionClaimIndicator(provisionClaimIndicator);
        }

        Date dispensedDate = (Date) attributes.get("dispensedDate");

        if (dispensedDate != null) {
            setDispensedDate(dispensedDate);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date accrualActualEndDate = (Date) attributes.get(
                "accrualActualEndDate");

        if (accrualActualEndDate != null) {
            setAccrualActualEndDate(accrualActualEndDate);
        }

        String marketId = (String) attributes.get("marketId");

        if (marketId != null) {
            setMarketId(marketId);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String accountName = (String) attributes.get("accountName");

        if (accountName != null) {
            setAccountName(accountName);
        }

        String amount = (String) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        Integer actualsMasterSid = (Integer) attributes.get("actualsMasterSid");

        if (actualsMasterSid != null) {
            setActualsMasterSid(actualsMasterSid);
        }

        String acctIdentifierCodeQualifier = (String) attributes.get(
                "acctIdentifierCodeQualifier");

        if (acctIdentifierCodeQualifier != null) {
            setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String accrualProcessed = (String) attributes.get("accrualProcessed");

        if (accrualProcessed != null) {
            setAccrualProcessed(accrualProcessed);
        }

        String parentcomDivmktBrandProdkey = (String) attributes.get(
                "parentcomDivmktBrandProdkey");

        if (parentcomDivmktBrandProdkey != null) {
            setParentcomDivmktBrandProdkey(parentcomDivmktBrandProdkey);
        }

        Date cashPaidDate = (Date) attributes.get("cashPaidDate");

        if (cashPaidDate != null) {
            setCashPaidDate(cashPaidDate);
        }

        String salesAmount = (String) attributes.get("salesAmount");

        if (salesAmount != null) {
            setSalesAmount(salesAmount);
        }

        Date accrualActualStartDate = (Date) attributes.get(
                "accrualActualStartDate");

        if (accrualActualStartDate != null) {
            setAccrualActualStartDate(accrualActualStartDate);
        }

        String settlementNo = (String) attributes.get("settlementNo");

        if (settlementNo != null) {
            setSettlementNo(settlementNo);
        }

        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        Date uploadDate = (Date) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        String claimIndicator = (String) attributes.get("claimIndicator");

        if (claimIndicator != null) {
            setClaimIndicator(claimIndicator);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String priceAdjustmentName = (String) attributes.get(
                "priceAdjustmentName");

        if (priceAdjustmentName != null) {
            setPriceAdjustmentName(priceAdjustmentName);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String actualId = (String) attributes.get("actualId");

        if (actualId != null) {
            setActualId(actualId);
        }

        String provisionId = (String) attributes.get("provisionId");

        if (provisionId != null) {
            setProvisionId(provisionId);
        }

        String sentOut = (String) attributes.get("sentOut");

        if (sentOut != null) {
            setSentOut(sentOut);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String divisionId = (String) attributes.get("divisionId");

        if (divisionId != null) {
            setDivisionId(divisionId);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String programStateCode = (String) attributes.get("programStateCode");

        if (programStateCode != null) {
            setProgramStateCode(programStateCode);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String invoiceLineNo = (String) attributes.get("invoiceLineNo");

        if (invoiceLineNo != null) {
            setInvoiceLineNo(invoiceLineNo);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String comDivMktBrandProdKey = (String) attributes.get(
                "comDivMktBrandProdKey");

        if (comDivMktBrandProdKey != null) {
            setComDivMktBrandProdKey(comDivMktBrandProdKey);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this actuals master.
    *
    * @return the primary key of this actuals master
    */
    @Override
    public int getPrimaryKey() {
        return _actualsMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this actuals master.
    *
    * @param primaryKey the primary key of this actuals master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _actualsMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the quantity inclusion of this actuals master.
    *
    * @return the quantity inclusion of this actuals master
    */
    @Override
    public java.lang.String getQuantityInclusion() {
        return _actualsMaster.getQuantityInclusion();
    }

    /**
    * Sets the quantity inclusion of this actuals master.
    *
    * @param quantityInclusion the quantity inclusion of this actuals master
    */
    @Override
    public void setQuantityInclusion(java.lang.String quantityInclusion) {
        _actualsMaster.setQuantityInclusion(quantityInclusion);
    }

    /**
    * Returns the mandated discount amount of this actuals master.
    *
    * @return the mandated discount amount of this actuals master
    */
    @Override
    public double getMandatedDiscountAmount() {
        return _actualsMaster.getMandatedDiscountAmount();
    }

    /**
    * Sets the mandated discount amount of this actuals master.
    *
    * @param mandatedDiscountAmount the mandated discount amount of this actuals master
    */
    @Override
    public void setMandatedDiscountAmount(double mandatedDiscountAmount) {
        _actualsMaster.setMandatedDiscountAmount(mandatedDiscountAmount);
    }

    /**
    * Returns the item no of this actuals master.
    *
    * @return the item no of this actuals master
    */
    @Override
    public java.lang.String getItemNo() {
        return _actualsMaster.getItemNo();
    }

    /**
    * Sets the item no of this actuals master.
    *
    * @param itemNo the item no of this actuals master
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _actualsMaster.setItemNo(itemNo);
    }

    /**
    * Returns the analysis code of this actuals master.
    *
    * @return the analysis code of this actuals master
    */
    @Override
    public java.lang.String getAnalysisCode() {
        return _actualsMaster.getAnalysisCode();
    }

    /**
    * Sets the analysis code of this actuals master.
    *
    * @param analysisCode the analysis code of this actuals master
    */
    @Override
    public void setAnalysisCode(java.lang.String analysisCode) {
        _actualsMaster.setAnalysisCode(analysisCode);
    }

    /**
    * Returns the record sequence of this actuals master.
    *
    * @return the record sequence of this actuals master
    */
    @Override
    public java.lang.String getRecordSequence() {
        return _actualsMaster.getRecordSequence();
    }

    /**
    * Sets the record sequence of this actuals master.
    *
    * @param recordSequence the record sequence of this actuals master
    */
    @Override
    public void setRecordSequence(java.lang.String recordSequence) {
        _actualsMaster.setRecordSequence(recordSequence);
    }

    /**
    * Returns the modified by of this actuals master.
    *
    * @return the modified by of this actuals master
    */
    @Override
    public int getModifiedBy() {
        return _actualsMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this actuals master.
    *
    * @param modifiedBy the modified by of this actuals master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _actualsMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the settlement method no of this actuals master.
    *
    * @return the settlement method no of this actuals master
    */
    @Override
    public java.lang.String getSettlementMethodNo() {
        return _actualsMaster.getSettlementMethodNo();
    }

    /**
    * Sets the settlement method no of this actuals master.
    *
    * @param settlementMethodNo the settlement method no of this actuals master
    */
    @Override
    public void setSettlementMethodNo(java.lang.String settlementMethodNo) {
        _actualsMaster.setSettlementMethodNo(settlementMethodNo);
    }

    /**
    * Returns the quantity of this actuals master.
    *
    * @return the quantity of this actuals master
    */
    @Override
    public java.lang.String getQuantity() {
        return _actualsMaster.getQuantity();
    }

    /**
    * Sets the quantity of this actuals master.
    *
    * @param quantity the quantity of this actuals master
    */
    @Override
    public void setQuantity(java.lang.String quantity) {
        _actualsMaster.setQuantity(quantity);
    }

    /**
    * Returns the account ID of this actuals master.
    *
    * @return the account ID of this actuals master
    */
    @Override
    public java.lang.String getAccountId() {
        return _actualsMaster.getAccountId();
    }

    /**
    * Sets the account ID of this actuals master.
    *
    * @param accountId the account ID of this actuals master
    */
    @Override
    public void setAccountId(java.lang.String accountId) {
        _actualsMaster.setAccountId(accountId);
    }

    /**
    * Returns the created date of this actuals master.
    *
    * @return the created date of this actuals master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _actualsMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this actuals master.
    *
    * @param createdDate the created date of this actuals master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _actualsMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the provision claim indicator of this actuals master.
    *
    * @return the provision claim indicator of this actuals master
    */
    @Override
    public java.lang.String getProvisionClaimIndicator() {
        return _actualsMaster.getProvisionClaimIndicator();
    }

    /**
    * Sets the provision claim indicator of this actuals master.
    *
    * @param provisionClaimIndicator the provision claim indicator of this actuals master
    */
    @Override
    public void setProvisionClaimIndicator(
        java.lang.String provisionClaimIndicator) {
        _actualsMaster.setProvisionClaimIndicator(provisionClaimIndicator);
    }

    /**
    * Returns the dispensed date of this actuals master.
    *
    * @return the dispensed date of this actuals master
    */
    @Override
    public java.util.Date getDispensedDate() {
        return _actualsMaster.getDispensedDate();
    }

    /**
    * Sets the dispensed date of this actuals master.
    *
    * @param dispensedDate the dispensed date of this actuals master
    */
    @Override
    public void setDispensedDate(java.util.Date dispensedDate) {
        _actualsMaster.setDispensedDate(dispensedDate);
    }

    /**
    * Returns the is active of this actuals master.
    *
    * @return the is active of this actuals master
    */
    @Override
    public java.lang.String getIsActive() {
        return _actualsMaster.getIsActive();
    }

    /**
    * Sets the is active of this actuals master.
    *
    * @param isActive the is active of this actuals master
    */
    @Override
    public void setIsActive(java.lang.String isActive) {
        _actualsMaster.setIsActive(isActive);
    }

    /**
    * Returns the batch ID of this actuals master.
    *
    * @return the batch ID of this actuals master
    */
    @Override
    public java.lang.String getBatchId() {
        return _actualsMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this actuals master.
    *
    * @param batchId the batch ID of this actuals master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _actualsMaster.setBatchId(batchId);
    }

    /**
    * Returns the accrual actual end date of this actuals master.
    *
    * @return the accrual actual end date of this actuals master
    */
    @Override
    public java.util.Date getAccrualActualEndDate() {
        return _actualsMaster.getAccrualActualEndDate();
    }

    /**
    * Sets the accrual actual end date of this actuals master.
    *
    * @param accrualActualEndDate the accrual actual end date of this actuals master
    */
    @Override
    public void setAccrualActualEndDate(java.util.Date accrualActualEndDate) {
        _actualsMaster.setAccrualActualEndDate(accrualActualEndDate);
    }

    /**
    * Returns the market ID of this actuals master.
    *
    * @return the market ID of this actuals master
    */
    @Override
    public java.lang.String getMarketId() {
        return _actualsMaster.getMarketId();
    }

    /**
    * Sets the market ID of this actuals master.
    *
    * @param marketId the market ID of this actuals master
    */
    @Override
    public void setMarketId(java.lang.String marketId) {
        _actualsMaster.setMarketId(marketId);
    }

    /**
    * Returns the brand ID of this actuals master.
    *
    * @return the brand ID of this actuals master
    */
    @Override
    public java.lang.String getBrandId() {
        return _actualsMaster.getBrandId();
    }

    /**
    * Sets the brand ID of this actuals master.
    *
    * @param brandId the brand ID of this actuals master
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _actualsMaster.setBrandId(brandId);
    }

    /**
    * Returns the account name of this actuals master.
    *
    * @return the account name of this actuals master
    */
    @Override
    public java.lang.String getAccountName() {
        return _actualsMaster.getAccountName();
    }

    /**
    * Sets the account name of this actuals master.
    *
    * @param accountName the account name of this actuals master
    */
    @Override
    public void setAccountName(java.lang.String accountName) {
        _actualsMaster.setAccountName(accountName);
    }

    /**
    * Returns the amount of this actuals master.
    *
    * @return the amount of this actuals master
    */
    @Override
    public java.lang.String getAmount() {
        return _actualsMaster.getAmount();
    }

    /**
    * Sets the amount of this actuals master.
    *
    * @param amount the amount of this actuals master
    */
    @Override
    public void setAmount(java.lang.String amount) {
        _actualsMaster.setAmount(amount);
    }

    /**
    * Returns the actuals master sid of this actuals master.
    *
    * @return the actuals master sid of this actuals master
    */
    @Override
    public int getActualsMasterSid() {
        return _actualsMaster.getActualsMasterSid();
    }

    /**
    * Sets the actuals master sid of this actuals master.
    *
    * @param actualsMasterSid the actuals master sid of this actuals master
    */
    @Override
    public void setActualsMasterSid(int actualsMasterSid) {
        _actualsMaster.setActualsMasterSid(actualsMasterSid);
    }

    /**
    * Returns the acct identifier code qualifier of this actuals master.
    *
    * @return the acct identifier code qualifier of this actuals master
    */
    @Override
    public java.lang.String getAcctIdentifierCodeQualifier() {
        return _actualsMaster.getAcctIdentifierCodeQualifier();
    }

    /**
    * Sets the acct identifier code qualifier of this actuals master.
    *
    * @param acctIdentifierCodeQualifier the acct identifier code qualifier of this actuals master
    */
    @Override
    public void setAcctIdentifierCodeQualifier(
        java.lang.String acctIdentifierCodeQualifier) {
        _actualsMaster.setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
    }

    /**
    * Returns the organization key of this actuals master.
    *
    * @return the organization key of this actuals master
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _actualsMaster.getOrganizationKey();
    }

    /**
    * Sets the organization key of this actuals master.
    *
    * @param organizationKey the organization key of this actuals master
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _actualsMaster.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the created by of this actuals master.
    *
    * @return the created by of this actuals master
    */
    @Override
    public int getCreatedBy() {
        return _actualsMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this actuals master.
    *
    * @param createdBy the created by of this actuals master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _actualsMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the accrual processed of this actuals master.
    *
    * @return the accrual processed of this actuals master
    */
    @Override
    public java.lang.String getAccrualProcessed() {
        return _actualsMaster.getAccrualProcessed();
    }

    /**
    * Sets the accrual processed of this actuals master.
    *
    * @param accrualProcessed the accrual processed of this actuals master
    */
    @Override
    public void setAccrualProcessed(java.lang.String accrualProcessed) {
        _actualsMaster.setAccrualProcessed(accrualProcessed);
    }

    /**
    * Returns the parentcom divmkt brand prodkey of this actuals master.
    *
    * @return the parentcom divmkt brand prodkey of this actuals master
    */
    @Override
    public java.lang.String getParentcomDivmktBrandProdkey() {
        return _actualsMaster.getParentcomDivmktBrandProdkey();
    }

    /**
    * Sets the parentcom divmkt brand prodkey of this actuals master.
    *
    * @param parentcomDivmktBrandProdkey the parentcom divmkt brand prodkey of this actuals master
    */
    @Override
    public void setParentcomDivmktBrandProdkey(
        java.lang.String parentcomDivmktBrandProdkey) {
        _actualsMaster.setParentcomDivmktBrandProdkey(parentcomDivmktBrandProdkey);
    }

    /**
    * Returns the cash paid date of this actuals master.
    *
    * @return the cash paid date of this actuals master
    */
    @Override
    public java.util.Date getCashPaidDate() {
        return _actualsMaster.getCashPaidDate();
    }

    /**
    * Sets the cash paid date of this actuals master.
    *
    * @param cashPaidDate the cash paid date of this actuals master
    */
    @Override
    public void setCashPaidDate(java.util.Date cashPaidDate) {
        _actualsMaster.setCashPaidDate(cashPaidDate);
    }

    /**
    * Returns the sales amount of this actuals master.
    *
    * @return the sales amount of this actuals master
    */
    @Override
    public java.lang.String getSalesAmount() {
        return _actualsMaster.getSalesAmount();
    }

    /**
    * Sets the sales amount of this actuals master.
    *
    * @param salesAmount the sales amount of this actuals master
    */
    @Override
    public void setSalesAmount(java.lang.String salesAmount) {
        _actualsMaster.setSalesAmount(salesAmount);
    }

    /**
    * Returns the accrual actual start date of this actuals master.
    *
    * @return the accrual actual start date of this actuals master
    */
    @Override
    public java.util.Date getAccrualActualStartDate() {
        return _actualsMaster.getAccrualActualStartDate();
    }

    /**
    * Sets the accrual actual start date of this actuals master.
    *
    * @param accrualActualStartDate the accrual actual start date of this actuals master
    */
    @Override
    public void setAccrualActualStartDate(java.util.Date accrualActualStartDate) {
        _actualsMaster.setAccrualActualStartDate(accrualActualStartDate);
    }

    /**
    * Returns the settlement no of this actuals master.
    *
    * @return the settlement no of this actuals master
    */
    @Override
    public java.lang.String getSettlementNo() {
        return _actualsMaster.getSettlementNo();
    }

    /**
    * Sets the settlement no of this actuals master.
    *
    * @param settlementNo the settlement no of this actuals master
    */
    @Override
    public void setSettlementNo(java.lang.String settlementNo) {
        _actualsMaster.setSettlementNo(settlementNo);
    }

    /**
    * Returns the price of this actuals master.
    *
    * @return the price of this actuals master
    */
    @Override
    public double getPrice() {
        return _actualsMaster.getPrice();
    }

    /**
    * Sets the price of this actuals master.
    *
    * @param price the price of this actuals master
    */
    @Override
    public void setPrice(double price) {
        _actualsMaster.setPrice(price);
    }

    /**
    * Returns the upload date of this actuals master.
    *
    * @return the upload date of this actuals master
    */
    @Override
    public java.util.Date getUploadDate() {
        return _actualsMaster.getUploadDate();
    }

    /**
    * Sets the upload date of this actuals master.
    *
    * @param uploadDate the upload date of this actuals master
    */
    @Override
    public void setUploadDate(java.util.Date uploadDate) {
        _actualsMaster.setUploadDate(uploadDate);
    }

    /**
    * Returns the claim indicator of this actuals master.
    *
    * @return the claim indicator of this actuals master
    */
    @Override
    public java.lang.String getClaimIndicator() {
        return _actualsMaster.getClaimIndicator();
    }

    /**
    * Sets the claim indicator of this actuals master.
    *
    * @param claimIndicator the claim indicator of this actuals master
    */
    @Override
    public void setClaimIndicator(java.lang.String claimIndicator) {
        _actualsMaster.setClaimIndicator(claimIndicator);
    }

    /**
    * Returns the item ID of this actuals master.
    *
    * @return the item ID of this actuals master
    */
    @Override
    public java.lang.String getItemId() {
        return _actualsMaster.getItemId();
    }

    /**
    * Sets the item ID of this actuals master.
    *
    * @param itemId the item ID of this actuals master
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _actualsMaster.setItemId(itemId);
    }

    /**
    * Returns the price adjustment name of this actuals master.
    *
    * @return the price adjustment name of this actuals master
    */
    @Override
    public java.lang.String getPriceAdjustmentName() {
        return _actualsMaster.getPriceAdjustmentName();
    }

    /**
    * Sets the price adjustment name of this actuals master.
    *
    * @param priceAdjustmentName the price adjustment name of this actuals master
    */
    @Override
    public void setPriceAdjustmentName(java.lang.String priceAdjustmentName) {
        _actualsMaster.setPriceAdjustmentName(priceAdjustmentName);
    }

    /**
    * Returns the contract ID of this actuals master.
    *
    * @return the contract ID of this actuals master
    */
    @Override
    public java.lang.String getContractId() {
        return _actualsMaster.getContractId();
    }

    /**
    * Sets the contract ID of this actuals master.
    *
    * @param contractId the contract ID of this actuals master
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _actualsMaster.setContractId(contractId);
    }

    /**
    * Returns the modified date of this actuals master.
    *
    * @return the modified date of this actuals master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _actualsMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this actuals master.
    *
    * @param modifiedDate the modified date of this actuals master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _actualsMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the actual ID of this actuals master.
    *
    * @return the actual ID of this actuals master
    */
    @Override
    public java.lang.String getActualId() {
        return _actualsMaster.getActualId();
    }

    /**
    * Sets the actual ID of this actuals master.
    *
    * @param actualId the actual ID of this actuals master
    */
    @Override
    public void setActualId(java.lang.String actualId) {
        _actualsMaster.setActualId(actualId);
    }

    /**
    * Returns the provision ID of this actuals master.
    *
    * @return the provision ID of this actuals master
    */
    @Override
    public java.lang.String getProvisionId() {
        return _actualsMaster.getProvisionId();
    }

    /**
    * Sets the provision ID of this actuals master.
    *
    * @param provisionId the provision ID of this actuals master
    */
    @Override
    public void setProvisionId(java.lang.String provisionId) {
        _actualsMaster.setProvisionId(provisionId);
    }

    /**
    * Returns the sent out of this actuals master.
    *
    * @return the sent out of this actuals master
    */
    @Override
    public java.lang.String getSentOut() {
        return _actualsMaster.getSentOut();
    }

    /**
    * Sets the sent out of this actuals master.
    *
    * @param sentOut the sent out of this actuals master
    */
    @Override
    public void setSentOut(java.lang.String sentOut) {
        _actualsMaster.setSentOut(sentOut);
    }

    /**
    * Returns the record lock status of this actuals master.
    *
    * @return the record lock status of this actuals master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _actualsMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this actuals master is record lock status.
    *
    * @return <code>true</code> if this actuals master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _actualsMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this actuals master is record lock status.
    *
    * @param recordLockStatus the record lock status of this actuals master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _actualsMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the division ID of this actuals master.
    *
    * @return the division ID of this actuals master
    */
    @Override
    public java.lang.String getDivisionId() {
        return _actualsMaster.getDivisionId();
    }

    /**
    * Sets the division ID of this actuals master.
    *
    * @param divisionId the division ID of this actuals master
    */
    @Override
    public void setDivisionId(java.lang.String divisionId) {
        _actualsMaster.setDivisionId(divisionId);
    }

    /**
    * Returns the item identifier code qualifier of this actuals master.
    *
    * @return the item identifier code qualifier of this actuals master
    */
    @Override
    public java.lang.String getItemIdentifierCodeQualifier() {
        return _actualsMaster.getItemIdentifierCodeQualifier();
    }

    /**
    * Sets the item identifier code qualifier of this actuals master.
    *
    * @param itemIdentifierCodeQualifier the item identifier code qualifier of this actuals master
    */
    @Override
    public void setItemIdentifierCodeQualifier(
        java.lang.String itemIdentifierCodeQualifier) {
        _actualsMaster.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
    }

    /**
    * Returns the program state code of this actuals master.
    *
    * @return the program state code of this actuals master
    */
    @Override
    public java.lang.String getProgramStateCode() {
        return _actualsMaster.getProgramStateCode();
    }

    /**
    * Sets the program state code of this actuals master.
    *
    * @param programStateCode the program state code of this actuals master
    */
    @Override
    public void setProgramStateCode(java.lang.String programStateCode) {
        _actualsMaster.setProgramStateCode(programStateCode);
    }

    /**
    * Returns the source of this actuals master.
    *
    * @return the source of this actuals master
    */
    @Override
    public java.lang.String getSource() {
        return _actualsMaster.getSource();
    }

    /**
    * Sets the source of this actuals master.
    *
    * @param source the source of this actuals master
    */
    @Override
    public void setSource(java.lang.String source) {
        _actualsMaster.setSource(source);
    }

    /**
    * Returns the invoice line no of this actuals master.
    *
    * @return the invoice line no of this actuals master
    */
    @Override
    public java.lang.String getInvoiceLineNo() {
        return _actualsMaster.getInvoiceLineNo();
    }

    /**
    * Sets the invoice line no of this actuals master.
    *
    * @param invoiceLineNo the invoice line no of this actuals master
    */
    @Override
    public void setInvoiceLineNo(java.lang.String invoiceLineNo) {
        _actualsMaster.setInvoiceLineNo(invoiceLineNo);
    }

    /**
    * Returns the account no of this actuals master.
    *
    * @return the account no of this actuals master
    */
    @Override
    public java.lang.String getAccountNo() {
        return _actualsMaster.getAccountNo();
    }

    /**
    * Sets the account no of this actuals master.
    *
    * @param accountNo the account no of this actuals master
    */
    @Override
    public void setAccountNo(java.lang.String accountNo) {
        _actualsMaster.setAccountNo(accountNo);
    }

    /**
    * Returns the com div mkt brand prod key of this actuals master.
    *
    * @return the com div mkt brand prod key of this actuals master
    */
    @Override
    public java.lang.String getComDivMktBrandProdKey() {
        return _actualsMaster.getComDivMktBrandProdKey();
    }

    /**
    * Sets the com div mkt brand prod key of this actuals master.
    *
    * @param comDivMktBrandProdKey the com div mkt brand prod key of this actuals master
    */
    @Override
    public void setComDivMktBrandProdKey(java.lang.String comDivMktBrandProdKey) {
        _actualsMaster.setComDivMktBrandProdKey(comDivMktBrandProdKey);
    }

    /**
    * Returns the inbound status of this actuals master.
    *
    * @return the inbound status of this actuals master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _actualsMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this actuals master.
    *
    * @param inboundStatus the inbound status of this actuals master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _actualsMaster.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _actualsMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _actualsMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _actualsMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _actualsMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _actualsMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _actualsMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _actualsMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _actualsMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _actualsMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _actualsMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _actualsMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ActualsMasterWrapper((ActualsMaster) _actualsMaster.clone());
    }

    @Override
    public int compareTo(ActualsMaster actualsMaster) {
        return _actualsMaster.compareTo(actualsMaster);
    }

    @Override
    public int hashCode() {
        return _actualsMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ActualsMaster> toCacheModel() {
        return _actualsMaster.toCacheModel();
    }

    @Override
    public ActualsMaster toEscapedModel() {
        return new ActualsMasterWrapper(_actualsMaster.toEscapedModel());
    }

    @Override
    public ActualsMaster toUnescapedModel() {
        return new ActualsMasterWrapper(_actualsMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _actualsMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _actualsMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _actualsMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ActualsMasterWrapper)) {
            return false;
        }

        ActualsMasterWrapper actualsMasterWrapper = (ActualsMasterWrapper) obj;

        if (Validator.equals(_actualsMaster, actualsMasterWrapper._actualsMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ActualsMaster getWrappedActualsMaster() {
        return _actualsMaster;
    }

    @Override
    public ActualsMaster getWrappedModel() {
        return _actualsMaster;
    }

    @Override
    public void resetOriginalValues() {
        _actualsMaster.resetOriginalValues();
    }
}
