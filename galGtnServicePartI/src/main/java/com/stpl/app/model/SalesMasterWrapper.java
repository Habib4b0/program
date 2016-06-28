package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SalesMaster}.
 * </p>
 *
 * @author
 * @see SalesMaster
 * @generated
 */
public class SalesMasterWrapper implements SalesMaster,
    ModelWrapper<SalesMaster> {
    private SalesMaster _salesMaster;

    public SalesMasterWrapper(SalesMaster salesMaster) {
        _salesMaster = salesMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return SalesMaster.class;
    }

    @Override
    public String getModelClassName() {
        return SalesMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemNo", getItemNo());
        attributes.put("recordSequence", getRecordSequence());
        attributes.put("quantity", getQuantity());
        attributes.put("accountId", getAccountId());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());
        attributes.put("isActive", getIsActive());
        attributes.put("marketId", getMarketId());
        attributes.put("invoiceDate", getInvoiceDate());
        attributes.put("accountName", getAccountName());
        attributes.put("docType", getDocType());
        attributes.put("orderReceivedDate", getOrderReceivedDate());
        attributes.put("amount", getAmount());
        attributes.put("salesMasterSid", getSalesMasterSid());
        attributes.put("orderSubtype", getOrderSubtype());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("price", getPrice());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("itemId", getItemId());
        attributes.put("priceAdjustmentName", getPriceAdjustmentName());
        attributes.put("itemCodeQualifier", getItemCodeQualifier());
        attributes.put("contractId", getContractId());
        attributes.put("itemUom", getItemUom());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("customerSubtype", getCustomerSubtype());
        attributes.put("provisionId", getProvisionId());
        attributes.put("wholesaleOwnerId", getWholesaleOwnerId());
        attributes.put("source", getSource());
        attributes.put("accountNo", getAccountNo());
        attributes.put("lotNo", getLotNo());
        attributes.put("parentItemId", getParentItemId());
        attributes.put("customerCompanyCode", getCustomerCompanyCode());
        attributes.put("analysisCode", getAnalysisCode());
        attributes.put("accountType", getAccountType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("contractNo", getContractNo());
        attributes.put("batchId", getBatchId());
        attributes.put("brandId", getBrandId());
        attributes.put("salesId", getSalesId());
        attributes.put("companyId", getCompanyId());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("itemParentNo", getItemParentNo());
        attributes.put("invoiceNumber", getInvoiceNumber());
        attributes.put("orderType", getOrderType());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("divisionId", getDivisionId());
        attributes.put("invoiceLineNumber", getInvoiceLineNumber());
        attributes.put("orderNumber", getOrderNumber());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Integer recordSequence = (Integer) attributes.get("recordSequence");

        if (recordSequence != null) {
            setRecordSequence(recordSequence);
        }

        Double quantity = (Double) attributes.get("quantity");

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

        String identifierCodeQualifier = (String) attributes.get(
                "identifierCodeQualifier");

        if (identifierCodeQualifier != null) {
            setIdentifierCodeQualifier(identifierCodeQualifier);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String marketId = (String) attributes.get("marketId");

        if (marketId != null) {
            setMarketId(marketId);
        }

        Date invoiceDate = (Date) attributes.get("invoiceDate");

        if (invoiceDate != null) {
            setInvoiceDate(invoiceDate);
        }

        String accountName = (String) attributes.get("accountName");

        if (accountName != null) {
            setAccountName(accountName);
        }

        String docType = (String) attributes.get("docType");

        if (docType != null) {
            setDocType(docType);
        }

        Date orderReceivedDate = (Date) attributes.get("orderReceivedDate");

        if (orderReceivedDate != null) {
            setOrderReceivedDate(orderReceivedDate);
        }

        Double amount = (Double) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        Integer salesMasterSid = (Integer) attributes.get("salesMasterSid");

        if (salesMasterSid != null) {
            setSalesMasterSid(salesMasterSid);
        }

        String orderSubtype = (String) attributes.get("orderSubtype");

        if (orderSubtype != null) {
            setOrderSubtype(orderSubtype);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        Date uploadDate = (Date) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
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

        String itemCodeQualifier = (String) attributes.get("itemCodeQualifier");

        if (itemCodeQualifier != null) {
            setItemCodeQualifier(itemCodeQualifier);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String itemUom = (String) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String customerSubtype = (String) attributes.get("customerSubtype");

        if (customerSubtype != null) {
            setCustomerSubtype(customerSubtype);
        }

        Integer provisionId = (Integer) attributes.get("provisionId");

        if (provisionId != null) {
            setProvisionId(provisionId);
        }

        String wholesaleOwnerId = (String) attributes.get("wholesaleOwnerId");

        if (wholesaleOwnerId != null) {
            setWholesaleOwnerId(wholesaleOwnerId);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String lotNo = (String) attributes.get("lotNo");

        if (lotNo != null) {
            setLotNo(lotNo);
        }

        String parentItemId = (String) attributes.get("parentItemId");

        if (parentItemId != null) {
            setParentItemId(parentItemId);
        }

        String customerCompanyCode = (String) attributes.get(
                "customerCompanyCode");

        if (customerCompanyCode != null) {
            setCustomerCompanyCode(customerCompanyCode);
        }

        String analysisCode = (String) attributes.get("analysisCode");

        if (analysisCode != null) {
            setAnalysisCode(analysisCode);
        }

        String accountType = (String) attributes.get("accountType");

        if (accountType != null) {
            setAccountType(accountType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String salesId = (String) attributes.get("salesId");

        if (salesId != null) {
            setSalesId(salesId);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String itemParentNo = (String) attributes.get("itemParentNo");

        if (itemParentNo != null) {
            setItemParentNo(itemParentNo);
        }

        String invoiceNumber = (String) attributes.get("invoiceNumber");

        if (invoiceNumber != null) {
            setInvoiceNumber(invoiceNumber);
        }

        String orderType = (String) attributes.get("orderType");

        if (orderType != null) {
            setOrderType(orderType);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String divisionId = (String) attributes.get("divisionId");

        if (divisionId != null) {
            setDivisionId(divisionId);
        }

        String invoiceLineNumber = (String) attributes.get("invoiceLineNumber");

        if (invoiceLineNumber != null) {
            setInvoiceLineNumber(invoiceLineNumber);
        }

        String orderNumber = (String) attributes.get("orderNumber");

        if (orderNumber != null) {
            setOrderNumber(orderNumber);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this sales master.
    *
    * @return the primary key of this sales master
    */
    @Override
    public int getPrimaryKey() {
        return _salesMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this sales master.
    *
    * @param primaryKey the primary key of this sales master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _salesMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item no of this sales master.
    *
    * @return the item no of this sales master
    */
    @Override
    public java.lang.String getItemNo() {
        return _salesMaster.getItemNo();
    }

    /**
    * Sets the item no of this sales master.
    *
    * @param itemNo the item no of this sales master
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _salesMaster.setItemNo(itemNo);
    }

    /**
    * Returns the record sequence of this sales master.
    *
    * @return the record sequence of this sales master
    */
    @Override
    public int getRecordSequence() {
        return _salesMaster.getRecordSequence();
    }

    /**
    * Sets the record sequence of this sales master.
    *
    * @param recordSequence the record sequence of this sales master
    */
    @Override
    public void setRecordSequence(int recordSequence) {
        _salesMaster.setRecordSequence(recordSequence);
    }

    /**
    * Returns the quantity of this sales master.
    *
    * @return the quantity of this sales master
    */
    @Override
    public double getQuantity() {
        return _salesMaster.getQuantity();
    }

    /**
    * Sets the quantity of this sales master.
    *
    * @param quantity the quantity of this sales master
    */
    @Override
    public void setQuantity(double quantity) {
        _salesMaster.setQuantity(quantity);
    }

    /**
    * Returns the account ID of this sales master.
    *
    * @return the account ID of this sales master
    */
    @Override
    public java.lang.String getAccountId() {
        return _salesMaster.getAccountId();
    }

    /**
    * Sets the account ID of this sales master.
    *
    * @param accountId the account ID of this sales master
    */
    @Override
    public void setAccountId(java.lang.String accountId) {
        _salesMaster.setAccountId(accountId);
    }

    /**
    * Returns the created date of this sales master.
    *
    * @return the created date of this sales master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _salesMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this sales master.
    *
    * @param createdDate the created date of this sales master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _salesMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the identifier code qualifier of this sales master.
    *
    * @return the identifier code qualifier of this sales master
    */
    @Override
    public java.lang.String getIdentifierCodeQualifier() {
        return _salesMaster.getIdentifierCodeQualifier();
    }

    /**
    * Sets the identifier code qualifier of this sales master.
    *
    * @param identifierCodeQualifier the identifier code qualifier of this sales master
    */
    @Override
    public void setIdentifierCodeQualifier(
        java.lang.String identifierCodeQualifier) {
        _salesMaster.setIdentifierCodeQualifier(identifierCodeQualifier);
    }

    /**
    * Returns the is active of this sales master.
    *
    * @return the is active of this sales master
    */
    @Override
    public java.lang.String getIsActive() {
        return _salesMaster.getIsActive();
    }

    /**
    * Sets the is active of this sales master.
    *
    * @param isActive the is active of this sales master
    */
    @Override
    public void setIsActive(java.lang.String isActive) {
        _salesMaster.setIsActive(isActive);
    }

    /**
    * Returns the market ID of this sales master.
    *
    * @return the market ID of this sales master
    */
    @Override
    public java.lang.String getMarketId() {
        return _salesMaster.getMarketId();
    }

    /**
    * Sets the market ID of this sales master.
    *
    * @param marketId the market ID of this sales master
    */
    @Override
    public void setMarketId(java.lang.String marketId) {
        _salesMaster.setMarketId(marketId);
    }

    /**
    * Returns the invoice date of this sales master.
    *
    * @return the invoice date of this sales master
    */
    @Override
    public java.util.Date getInvoiceDate() {
        return _salesMaster.getInvoiceDate();
    }

    /**
    * Sets the invoice date of this sales master.
    *
    * @param invoiceDate the invoice date of this sales master
    */
    @Override
    public void setInvoiceDate(java.util.Date invoiceDate) {
        _salesMaster.setInvoiceDate(invoiceDate);
    }

    /**
    * Returns the account name of this sales master.
    *
    * @return the account name of this sales master
    */
    @Override
    public java.lang.String getAccountName() {
        return _salesMaster.getAccountName();
    }

    /**
    * Sets the account name of this sales master.
    *
    * @param accountName the account name of this sales master
    */
    @Override
    public void setAccountName(java.lang.String accountName) {
        _salesMaster.setAccountName(accountName);
    }

    /**
    * Returns the doc type of this sales master.
    *
    * @return the doc type of this sales master
    */
    @Override
    public java.lang.String getDocType() {
        return _salesMaster.getDocType();
    }

    /**
    * Sets the doc type of this sales master.
    *
    * @param docType the doc type of this sales master
    */
    @Override
    public void setDocType(java.lang.String docType) {
        _salesMaster.setDocType(docType);
    }

    /**
    * Returns the order received date of this sales master.
    *
    * @return the order received date of this sales master
    */
    @Override
    public java.util.Date getOrderReceivedDate() {
        return _salesMaster.getOrderReceivedDate();
    }

    /**
    * Sets the order received date of this sales master.
    *
    * @param orderReceivedDate the order received date of this sales master
    */
    @Override
    public void setOrderReceivedDate(java.util.Date orderReceivedDate) {
        _salesMaster.setOrderReceivedDate(orderReceivedDate);
    }

    /**
    * Returns the amount of this sales master.
    *
    * @return the amount of this sales master
    */
    @Override
    public double getAmount() {
        return _salesMaster.getAmount();
    }

    /**
    * Sets the amount of this sales master.
    *
    * @param amount the amount of this sales master
    */
    @Override
    public void setAmount(double amount) {
        _salesMaster.setAmount(amount);
    }

    /**
    * Returns the sales master sid of this sales master.
    *
    * @return the sales master sid of this sales master
    */
    @Override
    public int getSalesMasterSid() {
        return _salesMaster.getSalesMasterSid();
    }

    /**
    * Sets the sales master sid of this sales master.
    *
    * @param salesMasterSid the sales master sid of this sales master
    */
    @Override
    public void setSalesMasterSid(int salesMasterSid) {
        _salesMaster.setSalesMasterSid(salesMasterSid);
    }

    /**
    * Returns the order subtype of this sales master.
    *
    * @return the order subtype of this sales master
    */
    @Override
    public java.lang.String getOrderSubtype() {
        return _salesMaster.getOrderSubtype();
    }

    /**
    * Sets the order subtype of this sales master.
    *
    * @param orderSubtype the order subtype of this sales master
    */
    @Override
    public void setOrderSubtype(java.lang.String orderSubtype) {
        _salesMaster.setOrderSubtype(orderSubtype);
    }

    /**
    * Returns the created by of this sales master.
    *
    * @return the created by of this sales master
    */
    @Override
    public int getCreatedBy() {
        return _salesMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this sales master.
    *
    * @param createdBy the created by of this sales master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _salesMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the price of this sales master.
    *
    * @return the price of this sales master
    */
    @Override
    public double getPrice() {
        return _salesMaster.getPrice();
    }

    /**
    * Sets the price of this sales master.
    *
    * @param price the price of this sales master
    */
    @Override
    public void setPrice(double price) {
        _salesMaster.setPrice(price);
    }

    /**
    * Returns the upload date of this sales master.
    *
    * @return the upload date of this sales master
    */
    @Override
    public java.util.Date getUploadDate() {
        return _salesMaster.getUploadDate();
    }

    /**
    * Sets the upload date of this sales master.
    *
    * @param uploadDate the upload date of this sales master
    */
    @Override
    public void setUploadDate(java.util.Date uploadDate) {
        _salesMaster.setUploadDate(uploadDate);
    }

    /**
    * Returns the item ID of this sales master.
    *
    * @return the item ID of this sales master
    */
    @Override
    public java.lang.String getItemId() {
        return _salesMaster.getItemId();
    }

    /**
    * Sets the item ID of this sales master.
    *
    * @param itemId the item ID of this sales master
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _salesMaster.setItemId(itemId);
    }

    /**
    * Returns the price adjustment name of this sales master.
    *
    * @return the price adjustment name of this sales master
    */
    @Override
    public java.lang.String getPriceAdjustmentName() {
        return _salesMaster.getPriceAdjustmentName();
    }

    /**
    * Sets the price adjustment name of this sales master.
    *
    * @param priceAdjustmentName the price adjustment name of this sales master
    */
    @Override
    public void setPriceAdjustmentName(java.lang.String priceAdjustmentName) {
        _salesMaster.setPriceAdjustmentName(priceAdjustmentName);
    }

    /**
    * Returns the item code qualifier of this sales master.
    *
    * @return the item code qualifier of this sales master
    */
    @Override
    public java.lang.String getItemCodeQualifier() {
        return _salesMaster.getItemCodeQualifier();
    }

    /**
    * Sets the item code qualifier of this sales master.
    *
    * @param itemCodeQualifier the item code qualifier of this sales master
    */
    @Override
    public void setItemCodeQualifier(java.lang.String itemCodeQualifier) {
        _salesMaster.setItemCodeQualifier(itemCodeQualifier);
    }

    /**
    * Returns the contract ID of this sales master.
    *
    * @return the contract ID of this sales master
    */
    @Override
    public java.lang.String getContractId() {
        return _salesMaster.getContractId();
    }

    /**
    * Sets the contract ID of this sales master.
    *
    * @param contractId the contract ID of this sales master
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _salesMaster.setContractId(contractId);
    }

    /**
    * Returns the item uom of this sales master.
    *
    * @return the item uom of this sales master
    */
    @Override
    public java.lang.String getItemUom() {
        return _salesMaster.getItemUom();
    }

    /**
    * Sets the item uom of this sales master.
    *
    * @param itemUom the item uom of this sales master
    */
    @Override
    public void setItemUom(java.lang.String itemUom) {
        _salesMaster.setItemUom(itemUom);
    }

    /**
    * Returns the modified date of this sales master.
    *
    * @return the modified date of this sales master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _salesMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this sales master.
    *
    * @param modifiedDate the modified date of this sales master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _salesMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the customer subtype of this sales master.
    *
    * @return the customer subtype of this sales master
    */
    @Override
    public java.lang.String getCustomerSubtype() {
        return _salesMaster.getCustomerSubtype();
    }

    /**
    * Sets the customer subtype of this sales master.
    *
    * @param customerSubtype the customer subtype of this sales master
    */
    @Override
    public void setCustomerSubtype(java.lang.String customerSubtype) {
        _salesMaster.setCustomerSubtype(customerSubtype);
    }

    /**
    * Returns the provision ID of this sales master.
    *
    * @return the provision ID of this sales master
    */
    @Override
    public int getProvisionId() {
        return _salesMaster.getProvisionId();
    }

    /**
    * Sets the provision ID of this sales master.
    *
    * @param provisionId the provision ID of this sales master
    */
    @Override
    public void setProvisionId(int provisionId) {
        _salesMaster.setProvisionId(provisionId);
    }

    /**
    * Returns the wholesale owner ID of this sales master.
    *
    * @return the wholesale owner ID of this sales master
    */
    @Override
    public java.lang.String getWholesaleOwnerId() {
        return _salesMaster.getWholesaleOwnerId();
    }

    /**
    * Sets the wholesale owner ID of this sales master.
    *
    * @param wholesaleOwnerId the wholesale owner ID of this sales master
    */
    @Override
    public void setWholesaleOwnerId(java.lang.String wholesaleOwnerId) {
        _salesMaster.setWholesaleOwnerId(wholesaleOwnerId);
    }

    /**
    * Returns the source of this sales master.
    *
    * @return the source of this sales master
    */
    @Override
    public java.lang.String getSource() {
        return _salesMaster.getSource();
    }

    /**
    * Sets the source of this sales master.
    *
    * @param source the source of this sales master
    */
    @Override
    public void setSource(java.lang.String source) {
        _salesMaster.setSource(source);
    }

    /**
    * Returns the account no of this sales master.
    *
    * @return the account no of this sales master
    */
    @Override
    public java.lang.String getAccountNo() {
        return _salesMaster.getAccountNo();
    }

    /**
    * Sets the account no of this sales master.
    *
    * @param accountNo the account no of this sales master
    */
    @Override
    public void setAccountNo(java.lang.String accountNo) {
        _salesMaster.setAccountNo(accountNo);
    }

    /**
    * Returns the lot no of this sales master.
    *
    * @return the lot no of this sales master
    */
    @Override
    public java.lang.String getLotNo() {
        return _salesMaster.getLotNo();
    }

    /**
    * Sets the lot no of this sales master.
    *
    * @param lotNo the lot no of this sales master
    */
    @Override
    public void setLotNo(java.lang.String lotNo) {
        _salesMaster.setLotNo(lotNo);
    }

    /**
    * Returns the parent item ID of this sales master.
    *
    * @return the parent item ID of this sales master
    */
    @Override
    public java.lang.String getParentItemId() {
        return _salesMaster.getParentItemId();
    }

    /**
    * Sets the parent item ID of this sales master.
    *
    * @param parentItemId the parent item ID of this sales master
    */
    @Override
    public void setParentItemId(java.lang.String parentItemId) {
        _salesMaster.setParentItemId(parentItemId);
    }

    /**
    * Returns the customer company code of this sales master.
    *
    * @return the customer company code of this sales master
    */
    @Override
    public java.lang.String getCustomerCompanyCode() {
        return _salesMaster.getCustomerCompanyCode();
    }

    /**
    * Sets the customer company code of this sales master.
    *
    * @param customerCompanyCode the customer company code of this sales master
    */
    @Override
    public void setCustomerCompanyCode(java.lang.String customerCompanyCode) {
        _salesMaster.setCustomerCompanyCode(customerCompanyCode);
    }

    /**
    * Returns the analysis code of this sales master.
    *
    * @return the analysis code of this sales master
    */
    @Override
    public java.lang.String getAnalysisCode() {
        return _salesMaster.getAnalysisCode();
    }

    /**
    * Sets the analysis code of this sales master.
    *
    * @param analysisCode the analysis code of this sales master
    */
    @Override
    public void setAnalysisCode(java.lang.String analysisCode) {
        _salesMaster.setAnalysisCode(analysisCode);
    }

    /**
    * Returns the account type of this sales master.
    *
    * @return the account type of this sales master
    */
    @Override
    public java.lang.String getAccountType() {
        return _salesMaster.getAccountType();
    }

    /**
    * Sets the account type of this sales master.
    *
    * @param accountType the account type of this sales master
    */
    @Override
    public void setAccountType(java.lang.String accountType) {
        _salesMaster.setAccountType(accountType);
    }

    /**
    * Returns the modified by of this sales master.
    *
    * @return the modified by of this sales master
    */
    @Override
    public int getModifiedBy() {
        return _salesMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this sales master.
    *
    * @param modifiedBy the modified by of this sales master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _salesMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the contract no of this sales master.
    *
    * @return the contract no of this sales master
    */
    @Override
    public java.lang.String getContractNo() {
        return _salesMaster.getContractNo();
    }

    /**
    * Sets the contract no of this sales master.
    *
    * @param contractNo the contract no of this sales master
    */
    @Override
    public void setContractNo(java.lang.String contractNo) {
        _salesMaster.setContractNo(contractNo);
    }

    /**
    * Returns the batch ID of this sales master.
    *
    * @return the batch ID of this sales master
    */
    @Override
    public java.lang.String getBatchId() {
        return _salesMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this sales master.
    *
    * @param batchId the batch ID of this sales master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _salesMaster.setBatchId(batchId);
    }

    /**
    * Returns the brand ID of this sales master.
    *
    * @return the brand ID of this sales master
    */
    @Override
    public java.lang.String getBrandId() {
        return _salesMaster.getBrandId();
    }

    /**
    * Sets the brand ID of this sales master.
    *
    * @param brandId the brand ID of this sales master
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _salesMaster.setBrandId(brandId);
    }

    /**
    * Returns the sales ID of this sales master.
    *
    * @return the sales ID of this sales master
    */
    @Override
    public java.lang.String getSalesId() {
        return _salesMaster.getSalesId();
    }

    /**
    * Sets the sales ID of this sales master.
    *
    * @param salesId the sales ID of this sales master
    */
    @Override
    public void setSalesId(java.lang.String salesId) {
        _salesMaster.setSalesId(salesId);
    }

    /**
    * Returns the company ID of this sales master.
    *
    * @return the company ID of this sales master
    */
    @Override
    public java.lang.String getCompanyId() {
        return _salesMaster.getCompanyId();
    }

    /**
    * Sets the company ID of this sales master.
    *
    * @param companyId the company ID of this sales master
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _salesMaster.setCompanyId(companyId);
    }

    /**
    * Returns the organization key of this sales master.
    *
    * @return the organization key of this sales master
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _salesMaster.getOrganizationKey();
    }

    /**
    * Sets the organization key of this sales master.
    *
    * @param organizationKey the organization key of this sales master
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _salesMaster.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the item parent no of this sales master.
    *
    * @return the item parent no of this sales master
    */
    @Override
    public java.lang.String getItemParentNo() {
        return _salesMaster.getItemParentNo();
    }

    /**
    * Sets the item parent no of this sales master.
    *
    * @param itemParentNo the item parent no of this sales master
    */
    @Override
    public void setItemParentNo(java.lang.String itemParentNo) {
        _salesMaster.setItemParentNo(itemParentNo);
    }

    /**
    * Returns the invoice number of this sales master.
    *
    * @return the invoice number of this sales master
    */
    @Override
    public java.lang.String getInvoiceNumber() {
        return _salesMaster.getInvoiceNumber();
    }

    /**
    * Sets the invoice number of this sales master.
    *
    * @param invoiceNumber the invoice number of this sales master
    */
    @Override
    public void setInvoiceNumber(java.lang.String invoiceNumber) {
        _salesMaster.setInvoiceNumber(invoiceNumber);
    }

    /**
    * Returns the order type of this sales master.
    *
    * @return the order type of this sales master
    */
    @Override
    public java.lang.String getOrderType() {
        return _salesMaster.getOrderType();
    }

    /**
    * Sets the order type of this sales master.
    *
    * @param orderType the order type of this sales master
    */
    @Override
    public void setOrderType(java.lang.String orderType) {
        _salesMaster.setOrderType(orderType);
    }

    /**
    * Returns the record lock status of this sales master.
    *
    * @return the record lock status of this sales master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _salesMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this sales master is record lock status.
    *
    * @return <code>true</code> if this sales master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _salesMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this sales master is record lock status.
    *
    * @param recordLockStatus the record lock status of this sales master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _salesMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the division ID of this sales master.
    *
    * @return the division ID of this sales master
    */
    @Override
    public java.lang.String getDivisionId() {
        return _salesMaster.getDivisionId();
    }

    /**
    * Sets the division ID of this sales master.
    *
    * @param divisionId the division ID of this sales master
    */
    @Override
    public void setDivisionId(java.lang.String divisionId) {
        _salesMaster.setDivisionId(divisionId);
    }

    /**
    * Returns the invoice line number of this sales master.
    *
    * @return the invoice line number of this sales master
    */
    @Override
    public java.lang.String getInvoiceLineNumber() {
        return _salesMaster.getInvoiceLineNumber();
    }

    /**
    * Sets the invoice line number of this sales master.
    *
    * @param invoiceLineNumber the invoice line number of this sales master
    */
    @Override
    public void setInvoiceLineNumber(java.lang.String invoiceLineNumber) {
        _salesMaster.setInvoiceLineNumber(invoiceLineNumber);
    }

    /**
    * Returns the order number of this sales master.
    *
    * @return the order number of this sales master
    */
    @Override
    public java.lang.String getOrderNumber() {
        return _salesMaster.getOrderNumber();
    }

    /**
    * Sets the order number of this sales master.
    *
    * @param orderNumber the order number of this sales master
    */
    @Override
    public void setOrderNumber(java.lang.String orderNumber) {
        _salesMaster.setOrderNumber(orderNumber);
    }

    /**
    * Returns the inbound status of this sales master.
    *
    * @return the inbound status of this sales master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _salesMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this sales master.
    *
    * @param inboundStatus the inbound status of this sales master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _salesMaster.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _salesMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _salesMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _salesMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _salesMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _salesMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _salesMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _salesMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _salesMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _salesMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _salesMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _salesMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new SalesMasterWrapper((SalesMaster) _salesMaster.clone());
    }

    @Override
    public int compareTo(SalesMaster salesMaster) {
        return _salesMaster.compareTo(salesMaster);
    }

    @Override
    public int hashCode() {
        return _salesMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<SalesMaster> toCacheModel() {
        return _salesMaster.toCacheModel();
    }

    @Override
    public SalesMaster toEscapedModel() {
        return new SalesMasterWrapper(_salesMaster.toEscapedModel());
    }

    @Override
    public SalesMaster toUnescapedModel() {
        return new SalesMasterWrapper(_salesMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _salesMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _salesMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _salesMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SalesMasterWrapper)) {
            return false;
        }

        SalesMasterWrapper salesMasterWrapper = (SalesMasterWrapper) obj;

        if (Validator.equals(_salesMaster, salesMasterWrapper._salesMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public SalesMaster getWrappedSalesMaster() {
        return _salesMaster;
    }

    @Override
    public SalesMaster getWrappedModel() {
        return _salesMaster;
    }

    @Override
    public void resetOriginalValues() {
        _salesMaster.resetOriginalValues();
    }
}
