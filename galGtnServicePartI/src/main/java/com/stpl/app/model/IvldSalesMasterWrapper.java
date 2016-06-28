package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldSalesMaster}.
 * </p>
 *
 * @author
 * @see IvldSalesMaster
 * @generated
 */
public class IvldSalesMasterWrapper implements IvldSalesMaster,
    ModelWrapper<IvldSalesMaster> {
    private IvldSalesMaster _ivldSalesMaster;

    public IvldSalesMasterWrapper(IvldSalesMaster ivldSalesMaster) {
        _ivldSalesMaster = ivldSalesMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldSalesMaster.class;
    }

    @Override
    public String getModelClassName() {
        return IvldSalesMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("accountId", getAccountId());
        attributes.put("salesIntfid", getSalesIntfid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("divisionId", getDivisionId());
        attributes.put("source", getSource());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("analysisCode", getAnalysisCode());
        attributes.put("ivldSalesMasterSid", getIvldSalesMasterSid());
        attributes.put("docType", getDocType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("lotNo", getLotNo());
        attributes.put("quantity", getQuantity());
        attributes.put("invoiceLineNumber", getInvoiceLineNumber());
        attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());
        attributes.put("accountCodeQualifier", getAccountCodeQualifier());
        attributes.put("parentItemId", getParentItemId());
        attributes.put("accountNo", getAccountNo());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("accountName", getAccountName());
        attributes.put("provisionId", getProvisionId());
        attributes.put("amount", getAmount());
        attributes.put("marketId", getMarketId());
        attributes.put("isActive", getIsActive());
        attributes.put("wholesaleOwnerId", getWholesaleOwnerId());
        attributes.put("priceAdjustmentName", getPriceAdjustmentName());
        attributes.put("salesId", getSalesId());
        attributes.put("errorField", getErrorField());
        attributes.put("recordSequence", getRecordSequence());
        attributes.put("price", getPrice());
        attributes.put("customerSubtype", getCustomerSubtype());
        attributes.put("parentItemNo", getParentItemNo());
        attributes.put("itemId", getItemId());
        attributes.put("orderReceivedDate", getOrderReceivedDate());
        attributes.put("orderNumber", getOrderNumber());
        attributes.put("accountType", getAccountType());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("errorCode", getErrorCode());
        attributes.put("itemUom", getItemUom());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("invoiceNumber", getInvoiceNumber());
        attributes.put("orderSubtype", getOrderSubtype());
        attributes.put("itemNo", getItemNo());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("contractId", getContractId());
        attributes.put("customerCompanyCode", getCustomerCompanyCode());
        attributes.put("orderType", getOrderType());
        attributes.put("companyId", getCompanyId());
        attributes.put("brandId", getBrandId());
        attributes.put("invoiceDate", getInvoiceDate());
        attributes.put("batchId", getBatchId());
        attributes.put("contractNo", getContractNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        String salesIntfid = (String) attributes.get("salesIntfid");

        if (salesIntfid != null) {
            setSalesIntfid(salesIntfid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String divisionId = (String) attributes.get("divisionId");

        if (divisionId != null) {
            setDivisionId(divisionId);
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

        Integer ivldSalesMasterSid = (Integer) attributes.get(
                "ivldSalesMasterSid");

        if (ivldSalesMasterSid != null) {
            setIvldSalesMasterSid(ivldSalesMasterSid);
        }

        String docType = (String) attributes.get("docType");

        if (docType != null) {
            setDocType(docType);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String lotNo = (String) attributes.get("lotNo");

        if (lotNo != null) {
            setLotNo(lotNo);
        }

        String quantity = (String) attributes.get("quantity");

        if (quantity != null) {
            setQuantity(quantity);
        }

        String invoiceLineNumber = (String) attributes.get("invoiceLineNumber");

        if (invoiceLineNumber != null) {
            setInvoiceLineNumber(invoiceLineNumber);
        }

        String identifierCodeQualifier = (String) attributes.get(
                "identifierCodeQualifier");

        if (identifierCodeQualifier != null) {
            setIdentifierCodeQualifier(identifierCodeQualifier);
        }

        String accountCodeQualifier = (String) attributes.get(
                "accountCodeQualifier");

        if (accountCodeQualifier != null) {
            setAccountCodeQualifier(accountCodeQualifier);
        }

        String parentItemId = (String) attributes.get("parentItemId");

        if (parentItemId != null) {
            setParentItemId(parentItemId);
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

        String wholesaleOwnerId = (String) attributes.get("wholesaleOwnerId");

        if (wholesaleOwnerId != null) {
            setWholesaleOwnerId(wholesaleOwnerId);
        }

        String priceAdjustmentName = (String) attributes.get(
                "priceAdjustmentName");

        if (priceAdjustmentName != null) {
            setPriceAdjustmentName(priceAdjustmentName);
        }

        String salesId = (String) attributes.get("salesId");

        if (salesId != null) {
            setSalesId(salesId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String recordSequence = (String) attributes.get("recordSequence");

        if (recordSequence != null) {
            setRecordSequence(recordSequence);
        }

        String price = (String) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        String customerSubtype = (String) attributes.get("customerSubtype");

        if (customerSubtype != null) {
            setCustomerSubtype(customerSubtype);
        }

        String parentItemNo = (String) attributes.get("parentItemNo");

        if (parentItemNo != null) {
            setParentItemNo(parentItemNo);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String orderReceivedDate = (String) attributes.get("orderReceivedDate");

        if (orderReceivedDate != null) {
            setOrderReceivedDate(orderReceivedDate);
        }

        String orderNumber = (String) attributes.get("orderNumber");

        if (orderNumber != null) {
            setOrderNumber(orderNumber);
        }

        String accountType = (String) attributes.get("accountType");

        if (accountType != null) {
            setAccountType(accountType);
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

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String itemUom = (String) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String invoiceNumber = (String) attributes.get("invoiceNumber");

        if (invoiceNumber != null) {
            setInvoiceNumber(invoiceNumber);
        }

        String orderSubtype = (String) attributes.get("orderSubtype");

        if (orderSubtype != null) {
            setOrderSubtype(orderSubtype);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String customerCompanyCode = (String) attributes.get(
                "customerCompanyCode");

        if (customerCompanyCode != null) {
            setCustomerCompanyCode(customerCompanyCode);
        }

        String orderType = (String) attributes.get("orderType");

        if (orderType != null) {
            setOrderType(orderType);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String invoiceDate = (String) attributes.get("invoiceDate");

        if (invoiceDate != null) {
            setInvoiceDate(invoiceDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }
    }

    /**
    * Returns the primary key of this ivld sales master.
    *
    * @return the primary key of this ivld sales master
    */
    @Override
    public int getPrimaryKey() {
        return _ivldSalesMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld sales master.
    *
    * @param primaryKey the primary key of this ivld sales master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldSalesMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the account ID of this ivld sales master.
    *
    * @return the account ID of this ivld sales master
    */
    @Override
    public java.lang.String getAccountId() {
        return _ivldSalesMaster.getAccountId();
    }

    /**
    * Sets the account ID of this ivld sales master.
    *
    * @param accountId the account ID of this ivld sales master
    */
    @Override
    public void setAccountId(java.lang.String accountId) {
        _ivldSalesMaster.setAccountId(accountId);
    }

    /**
    * Returns the sales intfid of this ivld sales master.
    *
    * @return the sales intfid of this ivld sales master
    */
    @Override
    public java.lang.String getSalesIntfid() {
        return _ivldSalesMaster.getSalesIntfid();
    }

    /**
    * Sets the sales intfid of this ivld sales master.
    *
    * @param salesIntfid the sales intfid of this ivld sales master
    */
    @Override
    public void setSalesIntfid(java.lang.String salesIntfid) {
        _ivldSalesMaster.setSalesIntfid(salesIntfid);
    }

    /**
    * Returns the modified date of this ivld sales master.
    *
    * @return the modified date of this ivld sales master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldSalesMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld sales master.
    *
    * @param modifiedDate the modified date of this ivld sales master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldSalesMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the organization key of this ivld sales master.
    *
    * @return the organization key of this ivld sales master
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _ivldSalesMaster.getOrganizationKey();
    }

    /**
    * Sets the organization key of this ivld sales master.
    *
    * @param organizationKey the organization key of this ivld sales master
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _ivldSalesMaster.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the division ID of this ivld sales master.
    *
    * @return the division ID of this ivld sales master
    */
    @Override
    public java.lang.String getDivisionId() {
        return _ivldSalesMaster.getDivisionId();
    }

    /**
    * Sets the division ID of this ivld sales master.
    *
    * @param divisionId the division ID of this ivld sales master
    */
    @Override
    public void setDivisionId(java.lang.String divisionId) {
        _ivldSalesMaster.setDivisionId(divisionId);
    }

    /**
    * Returns the source of this ivld sales master.
    *
    * @return the source of this ivld sales master
    */
    @Override
    public java.lang.String getSource() {
        return _ivldSalesMaster.getSource();
    }

    /**
    * Sets the source of this ivld sales master.
    *
    * @param source the source of this ivld sales master
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldSalesMaster.setSource(source);
    }

    /**
    * Returns the add chg del indicator of this ivld sales master.
    *
    * @return the add chg del indicator of this ivld sales master
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldSalesMaster.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld sales master.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld sales master
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldSalesMaster.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the analysis code of this ivld sales master.
    *
    * @return the analysis code of this ivld sales master
    */
    @Override
    public java.lang.String getAnalysisCode() {
        return _ivldSalesMaster.getAnalysisCode();
    }

    /**
    * Sets the analysis code of this ivld sales master.
    *
    * @param analysisCode the analysis code of this ivld sales master
    */
    @Override
    public void setAnalysisCode(java.lang.String analysisCode) {
        _ivldSalesMaster.setAnalysisCode(analysisCode);
    }

    /**
    * Returns the ivld sales master sid of this ivld sales master.
    *
    * @return the ivld sales master sid of this ivld sales master
    */
    @Override
    public int getIvldSalesMasterSid() {
        return _ivldSalesMaster.getIvldSalesMasterSid();
    }

    /**
    * Sets the ivld sales master sid of this ivld sales master.
    *
    * @param ivldSalesMasterSid the ivld sales master sid of this ivld sales master
    */
    @Override
    public void setIvldSalesMasterSid(int ivldSalesMasterSid) {
        _ivldSalesMaster.setIvldSalesMasterSid(ivldSalesMasterSid);
    }

    /**
    * Returns the doc type of this ivld sales master.
    *
    * @return the doc type of this ivld sales master
    */
    @Override
    public java.lang.String getDocType() {
        return _ivldSalesMaster.getDocType();
    }

    /**
    * Sets the doc type of this ivld sales master.
    *
    * @param docType the doc type of this ivld sales master
    */
    @Override
    public void setDocType(java.lang.String docType) {
        _ivldSalesMaster.setDocType(docType);
    }

    /**
    * Returns the modified by of this ivld sales master.
    *
    * @return the modified by of this ivld sales master
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldSalesMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld sales master.
    *
    * @param modifiedBy the modified by of this ivld sales master
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldSalesMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the lot no of this ivld sales master.
    *
    * @return the lot no of this ivld sales master
    */
    @Override
    public java.lang.String getLotNo() {
        return _ivldSalesMaster.getLotNo();
    }

    /**
    * Sets the lot no of this ivld sales master.
    *
    * @param lotNo the lot no of this ivld sales master
    */
    @Override
    public void setLotNo(java.lang.String lotNo) {
        _ivldSalesMaster.setLotNo(lotNo);
    }

    /**
    * Returns the quantity of this ivld sales master.
    *
    * @return the quantity of this ivld sales master
    */
    @Override
    public java.lang.String getQuantity() {
        return _ivldSalesMaster.getQuantity();
    }

    /**
    * Sets the quantity of this ivld sales master.
    *
    * @param quantity the quantity of this ivld sales master
    */
    @Override
    public void setQuantity(java.lang.String quantity) {
        _ivldSalesMaster.setQuantity(quantity);
    }

    /**
    * Returns the invoice line number of this ivld sales master.
    *
    * @return the invoice line number of this ivld sales master
    */
    @Override
    public java.lang.String getInvoiceLineNumber() {
        return _ivldSalesMaster.getInvoiceLineNumber();
    }

    /**
    * Sets the invoice line number of this ivld sales master.
    *
    * @param invoiceLineNumber the invoice line number of this ivld sales master
    */
    @Override
    public void setInvoiceLineNumber(java.lang.String invoiceLineNumber) {
        _ivldSalesMaster.setInvoiceLineNumber(invoiceLineNumber);
    }

    /**
    * Returns the identifier code qualifier of this ivld sales master.
    *
    * @return the identifier code qualifier of this ivld sales master
    */
    @Override
    public java.lang.String getIdentifierCodeQualifier() {
        return _ivldSalesMaster.getIdentifierCodeQualifier();
    }

    /**
    * Sets the identifier code qualifier of this ivld sales master.
    *
    * @param identifierCodeQualifier the identifier code qualifier of this ivld sales master
    */
    @Override
    public void setIdentifierCodeQualifier(
        java.lang.String identifierCodeQualifier) {
        _ivldSalesMaster.setIdentifierCodeQualifier(identifierCodeQualifier);
    }

    /**
    * Returns the account code qualifier of this ivld sales master.
    *
    * @return the account code qualifier of this ivld sales master
    */
    @Override
    public java.lang.String getAccountCodeQualifier() {
        return _ivldSalesMaster.getAccountCodeQualifier();
    }

    /**
    * Sets the account code qualifier of this ivld sales master.
    *
    * @param accountCodeQualifier the account code qualifier of this ivld sales master
    */
    @Override
    public void setAccountCodeQualifier(java.lang.String accountCodeQualifier) {
        _ivldSalesMaster.setAccountCodeQualifier(accountCodeQualifier);
    }

    /**
    * Returns the parent item ID of this ivld sales master.
    *
    * @return the parent item ID of this ivld sales master
    */
    @Override
    public java.lang.String getParentItemId() {
        return _ivldSalesMaster.getParentItemId();
    }

    /**
    * Sets the parent item ID of this ivld sales master.
    *
    * @param parentItemId the parent item ID of this ivld sales master
    */
    @Override
    public void setParentItemId(java.lang.String parentItemId) {
        _ivldSalesMaster.setParentItemId(parentItemId);
    }

    /**
    * Returns the account no of this ivld sales master.
    *
    * @return the account no of this ivld sales master
    */
    @Override
    public java.lang.String getAccountNo() {
        return _ivldSalesMaster.getAccountNo();
    }

    /**
    * Sets the account no of this ivld sales master.
    *
    * @param accountNo the account no of this ivld sales master
    */
    @Override
    public void setAccountNo(java.lang.String accountNo) {
        _ivldSalesMaster.setAccountNo(accountNo);
    }

    /**
    * Returns the reason for failure of this ivld sales master.
    *
    * @return the reason for failure of this ivld sales master
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldSalesMaster.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld sales master.
    *
    * @param reasonForFailure the reason for failure of this ivld sales master
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldSalesMaster.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the account name of this ivld sales master.
    *
    * @return the account name of this ivld sales master
    */
    @Override
    public java.lang.String getAccountName() {
        return _ivldSalesMaster.getAccountName();
    }

    /**
    * Sets the account name of this ivld sales master.
    *
    * @param accountName the account name of this ivld sales master
    */
    @Override
    public void setAccountName(java.lang.String accountName) {
        _ivldSalesMaster.setAccountName(accountName);
    }

    /**
    * Returns the provision ID of this ivld sales master.
    *
    * @return the provision ID of this ivld sales master
    */
    @Override
    public java.lang.String getProvisionId() {
        return _ivldSalesMaster.getProvisionId();
    }

    /**
    * Sets the provision ID of this ivld sales master.
    *
    * @param provisionId the provision ID of this ivld sales master
    */
    @Override
    public void setProvisionId(java.lang.String provisionId) {
        _ivldSalesMaster.setProvisionId(provisionId);
    }

    /**
    * Returns the amount of this ivld sales master.
    *
    * @return the amount of this ivld sales master
    */
    @Override
    public java.lang.String getAmount() {
        return _ivldSalesMaster.getAmount();
    }

    /**
    * Sets the amount of this ivld sales master.
    *
    * @param amount the amount of this ivld sales master
    */
    @Override
    public void setAmount(java.lang.String amount) {
        _ivldSalesMaster.setAmount(amount);
    }

    /**
    * Returns the market ID of this ivld sales master.
    *
    * @return the market ID of this ivld sales master
    */
    @Override
    public java.lang.String getMarketId() {
        return _ivldSalesMaster.getMarketId();
    }

    /**
    * Sets the market ID of this ivld sales master.
    *
    * @param marketId the market ID of this ivld sales master
    */
    @Override
    public void setMarketId(java.lang.String marketId) {
        _ivldSalesMaster.setMarketId(marketId);
    }

    /**
    * Returns the is active of this ivld sales master.
    *
    * @return the is active of this ivld sales master
    */
    @Override
    public java.lang.String getIsActive() {
        return _ivldSalesMaster.getIsActive();
    }

    /**
    * Sets the is active of this ivld sales master.
    *
    * @param isActive the is active of this ivld sales master
    */
    @Override
    public void setIsActive(java.lang.String isActive) {
        _ivldSalesMaster.setIsActive(isActive);
    }

    /**
    * Returns the wholesale owner ID of this ivld sales master.
    *
    * @return the wholesale owner ID of this ivld sales master
    */
    @Override
    public java.lang.String getWholesaleOwnerId() {
        return _ivldSalesMaster.getWholesaleOwnerId();
    }

    /**
    * Sets the wholesale owner ID of this ivld sales master.
    *
    * @param wholesaleOwnerId the wholesale owner ID of this ivld sales master
    */
    @Override
    public void setWholesaleOwnerId(java.lang.String wholesaleOwnerId) {
        _ivldSalesMaster.setWholesaleOwnerId(wholesaleOwnerId);
    }

    /**
    * Returns the price adjustment name of this ivld sales master.
    *
    * @return the price adjustment name of this ivld sales master
    */
    @Override
    public java.lang.String getPriceAdjustmentName() {
        return _ivldSalesMaster.getPriceAdjustmentName();
    }

    /**
    * Sets the price adjustment name of this ivld sales master.
    *
    * @param priceAdjustmentName the price adjustment name of this ivld sales master
    */
    @Override
    public void setPriceAdjustmentName(java.lang.String priceAdjustmentName) {
        _ivldSalesMaster.setPriceAdjustmentName(priceAdjustmentName);
    }

    /**
    * Returns the sales ID of this ivld sales master.
    *
    * @return the sales ID of this ivld sales master
    */
    @Override
    public java.lang.String getSalesId() {
        return _ivldSalesMaster.getSalesId();
    }

    /**
    * Sets the sales ID of this ivld sales master.
    *
    * @param salesId the sales ID of this ivld sales master
    */
    @Override
    public void setSalesId(java.lang.String salesId) {
        _ivldSalesMaster.setSalesId(salesId);
    }

    /**
    * Returns the error field of this ivld sales master.
    *
    * @return the error field of this ivld sales master
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldSalesMaster.getErrorField();
    }

    /**
    * Sets the error field of this ivld sales master.
    *
    * @param errorField the error field of this ivld sales master
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldSalesMaster.setErrorField(errorField);
    }

    /**
    * Returns the record sequence of this ivld sales master.
    *
    * @return the record sequence of this ivld sales master
    */
    @Override
    public java.lang.String getRecordSequence() {
        return _ivldSalesMaster.getRecordSequence();
    }

    /**
    * Sets the record sequence of this ivld sales master.
    *
    * @param recordSequence the record sequence of this ivld sales master
    */
    @Override
    public void setRecordSequence(java.lang.String recordSequence) {
        _ivldSalesMaster.setRecordSequence(recordSequence);
    }

    /**
    * Returns the price of this ivld sales master.
    *
    * @return the price of this ivld sales master
    */
    @Override
    public java.lang.String getPrice() {
        return _ivldSalesMaster.getPrice();
    }

    /**
    * Sets the price of this ivld sales master.
    *
    * @param price the price of this ivld sales master
    */
    @Override
    public void setPrice(java.lang.String price) {
        _ivldSalesMaster.setPrice(price);
    }

    /**
    * Returns the customer subtype of this ivld sales master.
    *
    * @return the customer subtype of this ivld sales master
    */
    @Override
    public java.lang.String getCustomerSubtype() {
        return _ivldSalesMaster.getCustomerSubtype();
    }

    /**
    * Sets the customer subtype of this ivld sales master.
    *
    * @param customerSubtype the customer subtype of this ivld sales master
    */
    @Override
    public void setCustomerSubtype(java.lang.String customerSubtype) {
        _ivldSalesMaster.setCustomerSubtype(customerSubtype);
    }

    /**
    * Returns the parent item no of this ivld sales master.
    *
    * @return the parent item no of this ivld sales master
    */
    @Override
    public java.lang.String getParentItemNo() {
        return _ivldSalesMaster.getParentItemNo();
    }

    /**
    * Sets the parent item no of this ivld sales master.
    *
    * @param parentItemNo the parent item no of this ivld sales master
    */
    @Override
    public void setParentItemNo(java.lang.String parentItemNo) {
        _ivldSalesMaster.setParentItemNo(parentItemNo);
    }

    /**
    * Returns the item ID of this ivld sales master.
    *
    * @return the item ID of this ivld sales master
    */
    @Override
    public java.lang.String getItemId() {
        return _ivldSalesMaster.getItemId();
    }

    /**
    * Sets the item ID of this ivld sales master.
    *
    * @param itemId the item ID of this ivld sales master
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ivldSalesMaster.setItemId(itemId);
    }

    /**
    * Returns the order received date of this ivld sales master.
    *
    * @return the order received date of this ivld sales master
    */
    @Override
    public java.lang.String getOrderReceivedDate() {
        return _ivldSalesMaster.getOrderReceivedDate();
    }

    /**
    * Sets the order received date of this ivld sales master.
    *
    * @param orderReceivedDate the order received date of this ivld sales master
    */
    @Override
    public void setOrderReceivedDate(java.lang.String orderReceivedDate) {
        _ivldSalesMaster.setOrderReceivedDate(orderReceivedDate);
    }

    /**
    * Returns the order number of this ivld sales master.
    *
    * @return the order number of this ivld sales master
    */
    @Override
    public java.lang.String getOrderNumber() {
        return _ivldSalesMaster.getOrderNumber();
    }

    /**
    * Sets the order number of this ivld sales master.
    *
    * @param orderNumber the order number of this ivld sales master
    */
    @Override
    public void setOrderNumber(java.lang.String orderNumber) {
        _ivldSalesMaster.setOrderNumber(orderNumber);
    }

    /**
    * Returns the account type of this ivld sales master.
    *
    * @return the account type of this ivld sales master
    */
    @Override
    public java.lang.String getAccountType() {
        return _ivldSalesMaster.getAccountType();
    }

    /**
    * Sets the account type of this ivld sales master.
    *
    * @param accountType the account type of this ivld sales master
    */
    @Override
    public void setAccountType(java.lang.String accountType) {
        _ivldSalesMaster.setAccountType(accountType);
    }

    /**
    * Returns the upload date of this ivld sales master.
    *
    * @return the upload date of this ivld sales master
    */
    @Override
    public java.lang.String getUploadDate() {
        return _ivldSalesMaster.getUploadDate();
    }

    /**
    * Sets the upload date of this ivld sales master.
    *
    * @param uploadDate the upload date of this ivld sales master
    */
    @Override
    public void setUploadDate(java.lang.String uploadDate) {
        _ivldSalesMaster.setUploadDate(uploadDate);
    }

    /**
    * Returns the created by of this ivld sales master.
    *
    * @return the created by of this ivld sales master
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldSalesMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld sales master.
    *
    * @param createdBy the created by of this ivld sales master
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldSalesMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld sales master.
    *
    * @return the created date of this ivld sales master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldSalesMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld sales master.
    *
    * @param createdDate the created date of this ivld sales master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldSalesMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the error code of this ivld sales master.
    *
    * @return the error code of this ivld sales master
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldSalesMaster.getErrorCode();
    }

    /**
    * Sets the error code of this ivld sales master.
    *
    * @param errorCode the error code of this ivld sales master
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldSalesMaster.setErrorCode(errorCode);
    }

    /**
    * Returns the item uom of this ivld sales master.
    *
    * @return the item uom of this ivld sales master
    */
    @Override
    public java.lang.String getItemUom() {
        return _ivldSalesMaster.getItemUom();
    }

    /**
    * Sets the item uom of this ivld sales master.
    *
    * @param itemUom the item uom of this ivld sales master
    */
    @Override
    public void setItemUom(java.lang.String itemUom) {
        _ivldSalesMaster.setItemUom(itemUom);
    }

    /**
    * Returns the intf inserted date of this ivld sales master.
    *
    * @return the intf inserted date of this ivld sales master
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldSalesMaster.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld sales master.
    *
    * @param intfInsertedDate the intf inserted date of this ivld sales master
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldSalesMaster.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the invoice number of this ivld sales master.
    *
    * @return the invoice number of this ivld sales master
    */
    @Override
    public java.lang.String getInvoiceNumber() {
        return _ivldSalesMaster.getInvoiceNumber();
    }

    /**
    * Sets the invoice number of this ivld sales master.
    *
    * @param invoiceNumber the invoice number of this ivld sales master
    */
    @Override
    public void setInvoiceNumber(java.lang.String invoiceNumber) {
        _ivldSalesMaster.setInvoiceNumber(invoiceNumber);
    }

    /**
    * Returns the order subtype of this ivld sales master.
    *
    * @return the order subtype of this ivld sales master
    */
    @Override
    public java.lang.String getOrderSubtype() {
        return _ivldSalesMaster.getOrderSubtype();
    }

    /**
    * Sets the order subtype of this ivld sales master.
    *
    * @param orderSubtype the order subtype of this ivld sales master
    */
    @Override
    public void setOrderSubtype(java.lang.String orderSubtype) {
        _ivldSalesMaster.setOrderSubtype(orderSubtype);
    }

    /**
    * Returns the item no of this ivld sales master.
    *
    * @return the item no of this ivld sales master
    */
    @Override
    public java.lang.String getItemNo() {
        return _ivldSalesMaster.getItemNo();
    }

    /**
    * Sets the item no of this ivld sales master.
    *
    * @param itemNo the item no of this ivld sales master
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _ivldSalesMaster.setItemNo(itemNo);
    }

    /**
    * Returns the reprocessed flag of this ivld sales master.
    *
    * @return the reprocessed flag of this ivld sales master
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldSalesMaster.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld sales master.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld sales master
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldSalesMaster.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the contract ID of this ivld sales master.
    *
    * @return the contract ID of this ivld sales master
    */
    @Override
    public java.lang.String getContractId() {
        return _ivldSalesMaster.getContractId();
    }

    /**
    * Sets the contract ID of this ivld sales master.
    *
    * @param contractId the contract ID of this ivld sales master
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _ivldSalesMaster.setContractId(contractId);
    }

    /**
    * Returns the customer company code of this ivld sales master.
    *
    * @return the customer company code of this ivld sales master
    */
    @Override
    public java.lang.String getCustomerCompanyCode() {
        return _ivldSalesMaster.getCustomerCompanyCode();
    }

    /**
    * Sets the customer company code of this ivld sales master.
    *
    * @param customerCompanyCode the customer company code of this ivld sales master
    */
    @Override
    public void setCustomerCompanyCode(java.lang.String customerCompanyCode) {
        _ivldSalesMaster.setCustomerCompanyCode(customerCompanyCode);
    }

    /**
    * Returns the order type of this ivld sales master.
    *
    * @return the order type of this ivld sales master
    */
    @Override
    public java.lang.String getOrderType() {
        return _ivldSalesMaster.getOrderType();
    }

    /**
    * Sets the order type of this ivld sales master.
    *
    * @param orderType the order type of this ivld sales master
    */
    @Override
    public void setOrderType(java.lang.String orderType) {
        _ivldSalesMaster.setOrderType(orderType);
    }

    /**
    * Returns the company ID of this ivld sales master.
    *
    * @return the company ID of this ivld sales master
    */
    @Override
    public java.lang.String getCompanyId() {
        return _ivldSalesMaster.getCompanyId();
    }

    /**
    * Sets the company ID of this ivld sales master.
    *
    * @param companyId the company ID of this ivld sales master
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _ivldSalesMaster.setCompanyId(companyId);
    }

    /**
    * Returns the brand ID of this ivld sales master.
    *
    * @return the brand ID of this ivld sales master
    */
    @Override
    public java.lang.String getBrandId() {
        return _ivldSalesMaster.getBrandId();
    }

    /**
    * Sets the brand ID of this ivld sales master.
    *
    * @param brandId the brand ID of this ivld sales master
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _ivldSalesMaster.setBrandId(brandId);
    }

    /**
    * Returns the invoice date of this ivld sales master.
    *
    * @return the invoice date of this ivld sales master
    */
    @Override
    public java.lang.String getInvoiceDate() {
        return _ivldSalesMaster.getInvoiceDate();
    }

    /**
    * Sets the invoice date of this ivld sales master.
    *
    * @param invoiceDate the invoice date of this ivld sales master
    */
    @Override
    public void setInvoiceDate(java.lang.String invoiceDate) {
        _ivldSalesMaster.setInvoiceDate(invoiceDate);
    }

    /**
    * Returns the batch ID of this ivld sales master.
    *
    * @return the batch ID of this ivld sales master
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldSalesMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld sales master.
    *
    * @param batchId the batch ID of this ivld sales master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldSalesMaster.setBatchId(batchId);
    }

    /**
    * Returns the contract no of this ivld sales master.
    *
    * @return the contract no of this ivld sales master
    */
    @Override
    public java.lang.String getContractNo() {
        return _ivldSalesMaster.getContractNo();
    }

    /**
    * Sets the contract no of this ivld sales master.
    *
    * @param contractNo the contract no of this ivld sales master
    */
    @Override
    public void setContractNo(java.lang.String contractNo) {
        _ivldSalesMaster.setContractNo(contractNo);
    }

    @Override
    public boolean isNew() {
        return _ivldSalesMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldSalesMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldSalesMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldSalesMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldSalesMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldSalesMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldSalesMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldSalesMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldSalesMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldSalesMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldSalesMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldSalesMasterWrapper((IvldSalesMaster) _ivldSalesMaster.clone());
    }

    @Override
    public int compareTo(IvldSalesMaster ivldSalesMaster) {
        return _ivldSalesMaster.compareTo(ivldSalesMaster);
    }

    @Override
    public int hashCode() {
        return _ivldSalesMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldSalesMaster> toCacheModel() {
        return _ivldSalesMaster.toCacheModel();
    }

    @Override
    public IvldSalesMaster toEscapedModel() {
        return new IvldSalesMasterWrapper(_ivldSalesMaster.toEscapedModel());
    }

    @Override
    public IvldSalesMaster toUnescapedModel() {
        return new IvldSalesMasterWrapper(_ivldSalesMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldSalesMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldSalesMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldSalesMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldSalesMasterWrapper)) {
            return false;
        }

        IvldSalesMasterWrapper ivldSalesMasterWrapper = (IvldSalesMasterWrapper) obj;

        if (Validator.equals(_ivldSalesMaster,
                    ivldSalesMasterWrapper._ivldSalesMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldSalesMaster getWrappedIvldSalesMaster() {
        return _ivldSalesMaster;
    }

    @Override
    public IvldSalesMaster getWrappedModel() {
        return _ivldSalesMaster;
    }

    @Override
    public void resetOriginalValues() {
        _ivldSalesMaster.resetOriginalValues();
    }
}
