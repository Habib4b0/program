package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldCustomerGtsActual}.
 * </p>
 *
 * @author
 * @see IvldCustomerGtsActual
 * @generated
 */
public class IvldCustomerGtsActualWrapper implements IvldCustomerGtsActual,
    ModelWrapper<IvldCustomerGtsActual> {
    private IvldCustomerGtsActual _ivldCustomerGtsActual;

    public IvldCustomerGtsActualWrapper(
        IvldCustomerGtsActual ivldCustomerGtsActual) {
        _ivldCustomerGtsActual = ivldCustomerGtsActual;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldCustomerGtsActual.class;
    }

    @Override
    public String getModelClassName() {
        return IvldCustomerGtsActual.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("parentAccountId", getParentAccountId());
        attributes.put("ivldCustomerGtsActualSid", getIvldCustomerGtsActualSid());
        attributes.put("accountId", getAccountId());
        attributes.put("itemId", getItemId());
        attributes.put("orderReceivedDate", getOrderReceivedDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("orderNumber", getOrderNumber());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("errorCode", getErrorCode());
        attributes.put("itemUom", getItemUom());
        attributes.put("invoiceNumber", getInvoiceNumber());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("lotNo", getLotNo());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("quantity", getQuantity());
        attributes.put("invoiceLineNumber", getInvoiceLineNumber());
        attributes.put("contractId", getContractId());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("amount", getAmount());
        attributes.put("invoiceDate", getInvoiceDate());
        attributes.put("customerGtsActualIntfId", getCustomerGtsActualIntfId());
        attributes.put("batchId", getBatchId());
        attributes.put("salesId", getSalesId());
        attributes.put("errorField", getErrorField());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String parentAccountId = (String) attributes.get("parentAccountId");

        if (parentAccountId != null) {
            setParentAccountId(parentAccountId);
        }

        Integer ivldCustomerGtsActualSid = (Integer) attributes.get(
                "ivldCustomerGtsActualSid");

        if (ivldCustomerGtsActualSid != null) {
            setIvldCustomerGtsActualSid(ivldCustomerGtsActualSid);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date orderReceivedDate = (Date) attributes.get("orderReceivedDate");

        if (orderReceivedDate != null) {
            setOrderReceivedDate(orderReceivedDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String orderNumber = (String) attributes.get("orderNumber");

        if (orderNumber != null) {
            setOrderNumber(orderNumber);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String createdDate = (String) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String itemUom = (String) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        String invoiceNumber = (String) attributes.get("invoiceNumber");

        if (invoiceNumber != null) {
            setInvoiceNumber(invoiceNumber);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String lotNo = (String) attributes.get("lotNo");

        if (lotNo != null) {
            setLotNo(lotNo);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String quantity = (String) attributes.get("quantity");

        if (quantity != null) {
            setQuantity(quantity);
        }

        String invoiceLineNumber = (String) attributes.get("invoiceLineNumber");

        if (invoiceLineNumber != null) {
            setInvoiceLineNumber(invoiceLineNumber);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String amount = (String) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        Date invoiceDate = (Date) attributes.get("invoiceDate");

        if (invoiceDate != null) {
            setInvoiceDate(invoiceDate);
        }

        String customerGtsActualIntfId = (String) attributes.get(
                "customerGtsActualIntfId");

        if (customerGtsActualIntfId != null) {
            setCustomerGtsActualIntfId(customerGtsActualIntfId);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String salesId = (String) attributes.get("salesId");

        if (salesId != null) {
            setSalesId(salesId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this ivld customer gts actual.
    *
    * @return the primary key of this ivld customer gts actual
    */
    @Override
    public int getPrimaryKey() {
        return _ivldCustomerGtsActual.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld customer gts actual.
    *
    * @param primaryKey the primary key of this ivld customer gts actual
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldCustomerGtsActual.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the parent account ID of this ivld customer gts actual.
    *
    * @return the parent account ID of this ivld customer gts actual
    */
    @Override
    public java.lang.String getParentAccountId() {
        return _ivldCustomerGtsActual.getParentAccountId();
    }

    /**
    * Sets the parent account ID of this ivld customer gts actual.
    *
    * @param parentAccountId the parent account ID of this ivld customer gts actual
    */
    @Override
    public void setParentAccountId(java.lang.String parentAccountId) {
        _ivldCustomerGtsActual.setParentAccountId(parentAccountId);
    }

    /**
    * Returns the ivld customer gts actual sid of this ivld customer gts actual.
    *
    * @return the ivld customer gts actual sid of this ivld customer gts actual
    */
    @Override
    public int getIvldCustomerGtsActualSid() {
        return _ivldCustomerGtsActual.getIvldCustomerGtsActualSid();
    }

    /**
    * Sets the ivld customer gts actual sid of this ivld customer gts actual.
    *
    * @param ivldCustomerGtsActualSid the ivld customer gts actual sid of this ivld customer gts actual
    */
    @Override
    public void setIvldCustomerGtsActualSid(int ivldCustomerGtsActualSid) {
        _ivldCustomerGtsActual.setIvldCustomerGtsActualSid(ivldCustomerGtsActualSid);
    }

    /**
    * Returns the account ID of this ivld customer gts actual.
    *
    * @return the account ID of this ivld customer gts actual
    */
    @Override
    public java.lang.String getAccountId() {
        return _ivldCustomerGtsActual.getAccountId();
    }

    /**
    * Sets the account ID of this ivld customer gts actual.
    *
    * @param accountId the account ID of this ivld customer gts actual
    */
    @Override
    public void setAccountId(java.lang.String accountId) {
        _ivldCustomerGtsActual.setAccountId(accountId);
    }

    /**
    * Returns the item ID of this ivld customer gts actual.
    *
    * @return the item ID of this ivld customer gts actual
    */
    @Override
    public java.lang.String getItemId() {
        return _ivldCustomerGtsActual.getItemId();
    }

    /**
    * Sets the item ID of this ivld customer gts actual.
    *
    * @param itemId the item ID of this ivld customer gts actual
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ivldCustomerGtsActual.setItemId(itemId);
    }

    /**
    * Returns the order received date of this ivld customer gts actual.
    *
    * @return the order received date of this ivld customer gts actual
    */
    @Override
    public java.util.Date getOrderReceivedDate() {
        return _ivldCustomerGtsActual.getOrderReceivedDate();
    }

    /**
    * Sets the order received date of this ivld customer gts actual.
    *
    * @param orderReceivedDate the order received date of this ivld customer gts actual
    */
    @Override
    public void setOrderReceivedDate(java.util.Date orderReceivedDate) {
        _ivldCustomerGtsActual.setOrderReceivedDate(orderReceivedDate);
    }

    /**
    * Returns the modified date of this ivld customer gts actual.
    *
    * @return the modified date of this ivld customer gts actual
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldCustomerGtsActual.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld customer gts actual.
    *
    * @param modifiedDate the modified date of this ivld customer gts actual
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldCustomerGtsActual.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the order number of this ivld customer gts actual.
    *
    * @return the order number of this ivld customer gts actual
    */
    @Override
    public java.lang.String getOrderNumber() {
        return _ivldCustomerGtsActual.getOrderNumber();
    }

    /**
    * Sets the order number of this ivld customer gts actual.
    *
    * @param orderNumber the order number of this ivld customer gts actual
    */
    @Override
    public void setOrderNumber(java.lang.String orderNumber) {
        _ivldCustomerGtsActual.setOrderNumber(orderNumber);
    }

    /**
    * Returns the organization key of this ivld customer gts actual.
    *
    * @return the organization key of this ivld customer gts actual
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _ivldCustomerGtsActual.getOrganizationKey();
    }

    /**
    * Sets the organization key of this ivld customer gts actual.
    *
    * @param organizationKey the organization key of this ivld customer gts actual
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _ivldCustomerGtsActual.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the source of this ivld customer gts actual.
    *
    * @return the source of this ivld customer gts actual
    */
    @Override
    public java.lang.String getSource() {
        return _ivldCustomerGtsActual.getSource();
    }

    /**
    * Sets the source of this ivld customer gts actual.
    *
    * @param source the source of this ivld customer gts actual
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldCustomerGtsActual.setSource(source);
    }

    /**
    * Returns the created by of this ivld customer gts actual.
    *
    * @return the created by of this ivld customer gts actual
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldCustomerGtsActual.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld customer gts actual.
    *
    * @param createdBy the created by of this ivld customer gts actual
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldCustomerGtsActual.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld customer gts actual.
    *
    * @return the created date of this ivld customer gts actual
    */
    @Override
    public java.lang.String getCreatedDate() {
        return _ivldCustomerGtsActual.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld customer gts actual.
    *
    * @param createdDate the created date of this ivld customer gts actual
    */
    @Override
    public void setCreatedDate(java.lang.String createdDate) {
        _ivldCustomerGtsActual.setCreatedDate(createdDate);
    }

    /**
    * Returns the add chg del indicator of this ivld customer gts actual.
    *
    * @return the add chg del indicator of this ivld customer gts actual
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldCustomerGtsActual.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld customer gts actual.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld customer gts actual
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldCustomerGtsActual.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the error code of this ivld customer gts actual.
    *
    * @return the error code of this ivld customer gts actual
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldCustomerGtsActual.getErrorCode();
    }

    /**
    * Sets the error code of this ivld customer gts actual.
    *
    * @param errorCode the error code of this ivld customer gts actual
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldCustomerGtsActual.setErrorCode(errorCode);
    }

    /**
    * Returns the item uom of this ivld customer gts actual.
    *
    * @return the item uom of this ivld customer gts actual
    */
    @Override
    public java.lang.String getItemUom() {
        return _ivldCustomerGtsActual.getItemUom();
    }

    /**
    * Sets the item uom of this ivld customer gts actual.
    *
    * @param itemUom the item uom of this ivld customer gts actual
    */
    @Override
    public void setItemUom(java.lang.String itemUom) {
        _ivldCustomerGtsActual.setItemUom(itemUom);
    }

    /**
    * Returns the invoice number of this ivld customer gts actual.
    *
    * @return the invoice number of this ivld customer gts actual
    */
    @Override
    public java.lang.String getInvoiceNumber() {
        return _ivldCustomerGtsActual.getInvoiceNumber();
    }

    /**
    * Sets the invoice number of this ivld customer gts actual.
    *
    * @param invoiceNumber the invoice number of this ivld customer gts actual
    */
    @Override
    public void setInvoiceNumber(java.lang.String invoiceNumber) {
        _ivldCustomerGtsActual.setInvoiceNumber(invoiceNumber);
    }

    /**
    * Returns the modified by of this ivld customer gts actual.
    *
    * @return the modified by of this ivld customer gts actual
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldCustomerGtsActual.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld customer gts actual.
    *
    * @param modifiedBy the modified by of this ivld customer gts actual
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldCustomerGtsActual.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the intf inserted date of this ivld customer gts actual.
    *
    * @return the intf inserted date of this ivld customer gts actual
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldCustomerGtsActual.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld customer gts actual.
    *
    * @param intfInsertedDate the intf inserted date of this ivld customer gts actual
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldCustomerGtsActual.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the lot no of this ivld customer gts actual.
    *
    * @return the lot no of this ivld customer gts actual
    */
    @Override
    public java.lang.String getLotNo() {
        return _ivldCustomerGtsActual.getLotNo();
    }

    /**
    * Sets the lot no of this ivld customer gts actual.
    *
    * @param lotNo the lot no of this ivld customer gts actual
    */
    @Override
    public void setLotNo(java.lang.String lotNo) {
        _ivldCustomerGtsActual.setLotNo(lotNo);
    }

    /**
    * Returns the reprocessed flag of this ivld customer gts actual.
    *
    * @return the reprocessed flag of this ivld customer gts actual
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldCustomerGtsActual.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld customer gts actual.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld customer gts actual
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldCustomerGtsActual.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the quantity of this ivld customer gts actual.
    *
    * @return the quantity of this ivld customer gts actual
    */
    @Override
    public java.lang.String getQuantity() {
        return _ivldCustomerGtsActual.getQuantity();
    }

    /**
    * Sets the quantity of this ivld customer gts actual.
    *
    * @param quantity the quantity of this ivld customer gts actual
    */
    @Override
    public void setQuantity(java.lang.String quantity) {
        _ivldCustomerGtsActual.setQuantity(quantity);
    }

    /**
    * Returns the invoice line number of this ivld customer gts actual.
    *
    * @return the invoice line number of this ivld customer gts actual
    */
    @Override
    public java.lang.String getInvoiceLineNumber() {
        return _ivldCustomerGtsActual.getInvoiceLineNumber();
    }

    /**
    * Sets the invoice line number of this ivld customer gts actual.
    *
    * @param invoiceLineNumber the invoice line number of this ivld customer gts actual
    */
    @Override
    public void setInvoiceLineNumber(java.lang.String invoiceLineNumber) {
        _ivldCustomerGtsActual.setInvoiceLineNumber(invoiceLineNumber);
    }

    /**
    * Returns the contract ID of this ivld customer gts actual.
    *
    * @return the contract ID of this ivld customer gts actual
    */
    @Override
    public java.lang.String getContractId() {
        return _ivldCustomerGtsActual.getContractId();
    }

    /**
    * Sets the contract ID of this ivld customer gts actual.
    *
    * @param contractId the contract ID of this ivld customer gts actual
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _ivldCustomerGtsActual.setContractId(contractId);
    }

    /**
    * Returns the reason for failure of this ivld customer gts actual.
    *
    * @return the reason for failure of this ivld customer gts actual
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldCustomerGtsActual.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld customer gts actual.
    *
    * @param reasonForFailure the reason for failure of this ivld customer gts actual
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldCustomerGtsActual.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the amount of this ivld customer gts actual.
    *
    * @return the amount of this ivld customer gts actual
    */
    @Override
    public java.lang.String getAmount() {
        return _ivldCustomerGtsActual.getAmount();
    }

    /**
    * Sets the amount of this ivld customer gts actual.
    *
    * @param amount the amount of this ivld customer gts actual
    */
    @Override
    public void setAmount(java.lang.String amount) {
        _ivldCustomerGtsActual.setAmount(amount);
    }

    /**
    * Returns the invoice date of this ivld customer gts actual.
    *
    * @return the invoice date of this ivld customer gts actual
    */
    @Override
    public java.util.Date getInvoiceDate() {
        return _ivldCustomerGtsActual.getInvoiceDate();
    }

    /**
    * Sets the invoice date of this ivld customer gts actual.
    *
    * @param invoiceDate the invoice date of this ivld customer gts actual
    */
    @Override
    public void setInvoiceDate(java.util.Date invoiceDate) {
        _ivldCustomerGtsActual.setInvoiceDate(invoiceDate);
    }

    /**
    * Returns the customer gts actual intf ID of this ivld customer gts actual.
    *
    * @return the customer gts actual intf ID of this ivld customer gts actual
    */
    @Override
    public java.lang.String getCustomerGtsActualIntfId() {
        return _ivldCustomerGtsActual.getCustomerGtsActualIntfId();
    }

    /**
    * Sets the customer gts actual intf ID of this ivld customer gts actual.
    *
    * @param customerGtsActualIntfId the customer gts actual intf ID of this ivld customer gts actual
    */
    @Override
    public void setCustomerGtsActualIntfId(
        java.lang.String customerGtsActualIntfId) {
        _ivldCustomerGtsActual.setCustomerGtsActualIntfId(customerGtsActualIntfId);
    }

    /**
    * Returns the batch ID of this ivld customer gts actual.
    *
    * @return the batch ID of this ivld customer gts actual
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldCustomerGtsActual.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld customer gts actual.
    *
    * @param batchId the batch ID of this ivld customer gts actual
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldCustomerGtsActual.setBatchId(batchId);
    }

    /**
    * Returns the sales ID of this ivld customer gts actual.
    *
    * @return the sales ID of this ivld customer gts actual
    */
    @Override
    public java.lang.String getSalesId() {
        return _ivldCustomerGtsActual.getSalesId();
    }

    /**
    * Sets the sales ID of this ivld customer gts actual.
    *
    * @param salesId the sales ID of this ivld customer gts actual
    */
    @Override
    public void setSalesId(java.lang.String salesId) {
        _ivldCustomerGtsActual.setSalesId(salesId);
    }

    /**
    * Returns the error field of this ivld customer gts actual.
    *
    * @return the error field of this ivld customer gts actual
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldCustomerGtsActual.getErrorField();
    }

    /**
    * Sets the error field of this ivld customer gts actual.
    *
    * @param errorField the error field of this ivld customer gts actual
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldCustomerGtsActual.setErrorField(errorField);
    }

    /**
    * Returns the check record of this ivld customer gts actual.
    *
    * @return the check record of this ivld customer gts actual
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldCustomerGtsActual.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld customer gts actual is check record.
    *
    * @return <code>true</code> if this ivld customer gts actual is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldCustomerGtsActual.isCheckRecord();
    }

    /**
    * Sets whether this ivld customer gts actual is check record.
    *
    * @param checkRecord the check record of this ivld customer gts actual
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldCustomerGtsActual.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _ivldCustomerGtsActual.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldCustomerGtsActual.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldCustomerGtsActual.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldCustomerGtsActual.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldCustomerGtsActual.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldCustomerGtsActual.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldCustomerGtsActual.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldCustomerGtsActual.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldCustomerGtsActual.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldCustomerGtsActual.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldCustomerGtsActual.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldCustomerGtsActualWrapper((IvldCustomerGtsActual) _ivldCustomerGtsActual.clone());
    }

    @Override
    public int compareTo(IvldCustomerGtsActual ivldCustomerGtsActual) {
        return _ivldCustomerGtsActual.compareTo(ivldCustomerGtsActual);
    }

    @Override
    public int hashCode() {
        return _ivldCustomerGtsActual.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldCustomerGtsActual> toCacheModel() {
        return _ivldCustomerGtsActual.toCacheModel();
    }

    @Override
    public IvldCustomerGtsActual toEscapedModel() {
        return new IvldCustomerGtsActualWrapper(_ivldCustomerGtsActual.toEscapedModel());
    }

    @Override
    public IvldCustomerGtsActual toUnescapedModel() {
        return new IvldCustomerGtsActualWrapper(_ivldCustomerGtsActual.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldCustomerGtsActual.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldCustomerGtsActual.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldCustomerGtsActual.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldCustomerGtsActualWrapper)) {
            return false;
        }

        IvldCustomerGtsActualWrapper ivldCustomerGtsActualWrapper = (IvldCustomerGtsActualWrapper) obj;

        if (Validator.equals(_ivldCustomerGtsActual,
                    ivldCustomerGtsActualWrapper._ivldCustomerGtsActual)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldCustomerGtsActual getWrappedIvldCustomerGtsActual() {
        return _ivldCustomerGtsActual;
    }

    @Override
    public IvldCustomerGtsActual getWrappedModel() {
        return _ivldCustomerGtsActual;
    }

    @Override
    public void resetOriginalValues() {
        _ivldCustomerGtsActual.resetOriginalValues();
    }
}
