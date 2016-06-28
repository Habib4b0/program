package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CustomerGtsActual}.
 * </p>
 *
 * @author
 * @see CustomerGtsActual
 * @generated
 */
public class CustomerGtsActualWrapper implements CustomerGtsActual,
    ModelWrapper<CustomerGtsActual> {
    private CustomerGtsActual _customerGtsActual;

    public CustomerGtsActualWrapper(CustomerGtsActual customerGtsActual) {
        _customerGtsActual = customerGtsActual;
    }

    @Override
    public Class<?> getModelClass() {
        return CustomerGtsActual.class;
    }

    @Override
    public String getModelClassName() {
        return CustomerGtsActual.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("parentAccountId", getParentAccountId());
        attributes.put("contractId", getContractId());
        attributes.put("accountId", getAccountId());
        attributes.put("customerGtsActualSid", getCustomerGtsActualSid());
        attributes.put("orderReceivedDate", getOrderReceivedDate());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("amount", getAmount());
        attributes.put("orderNumber", getOrderNumber());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("invoiceDate", getInvoiceDate());
        attributes.put("customerGtsActualIntfId", getCustomerGtsActualIntfId());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("salesId", getSalesId());
        attributes.put("itemUom", getItemUom());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("invoiceNumber", getInvoiceNumber());
        attributes.put("lotNo", getLotNo());
        attributes.put("invoiceLineNumber", getInvoiceLineNumber());
        attributes.put("quantity", getQuantity());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String parentAccountId = (String) attributes.get("parentAccountId");

        if (parentAccountId != null) {
            setParentAccountId(parentAccountId);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        Integer customerGtsActualSid = (Integer) attributes.get(
                "customerGtsActualSid");

        if (customerGtsActualSid != null) {
            setCustomerGtsActualSid(customerGtsActualSid);
        }

        Date orderReceivedDate = (Date) attributes.get("orderReceivedDate");

        if (orderReceivedDate != null) {
            setOrderReceivedDate(orderReceivedDate);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Double amount = (Double) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        String orderNumber = (String) attributes.get("orderNumber");

        if (orderNumber != null) {
            setOrderNumber(orderNumber);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        Date invoiceDate = (Date) attributes.get("invoiceDate");

        if (invoiceDate != null) {
            setInvoiceDate(invoiceDate);
        }

        Integer customerGtsActualIntfId = (Integer) attributes.get(
                "customerGtsActualIntfId");

        if (customerGtsActualIntfId != null) {
            setCustomerGtsActualIntfId(customerGtsActualIntfId);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String salesId = (String) attributes.get("salesId");

        if (salesId != null) {
            setSalesId(salesId);
        }

        String itemUom = (String) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String invoiceNumber = (String) attributes.get("invoiceNumber");

        if (invoiceNumber != null) {
            setInvoiceNumber(invoiceNumber);
        }

        String lotNo = (String) attributes.get("lotNo");

        if (lotNo != null) {
            setLotNo(lotNo);
        }

        String invoiceLineNumber = (String) attributes.get("invoiceLineNumber");

        if (invoiceLineNumber != null) {
            setInvoiceLineNumber(invoiceLineNumber);
        }

        Double quantity = (Double) attributes.get("quantity");

        if (quantity != null) {
            setQuantity(quantity);
        }
    }

    /**
    * Returns the primary key of this customer gts actual.
    *
    * @return the primary key of this customer gts actual
    */
    @Override
    public int getPrimaryKey() {
        return _customerGtsActual.getPrimaryKey();
    }

    /**
    * Sets the primary key of this customer gts actual.
    *
    * @param primaryKey the primary key of this customer gts actual
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _customerGtsActual.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the parent account ID of this customer gts actual.
    *
    * @return the parent account ID of this customer gts actual
    */
    @Override
    public java.lang.String getParentAccountId() {
        return _customerGtsActual.getParentAccountId();
    }

    /**
    * Sets the parent account ID of this customer gts actual.
    *
    * @param parentAccountId the parent account ID of this customer gts actual
    */
    @Override
    public void setParentAccountId(java.lang.String parentAccountId) {
        _customerGtsActual.setParentAccountId(parentAccountId);
    }

    /**
    * Returns the contract ID of this customer gts actual.
    *
    * @return the contract ID of this customer gts actual
    */
    @Override
    public java.lang.String getContractId() {
        return _customerGtsActual.getContractId();
    }

    /**
    * Sets the contract ID of this customer gts actual.
    *
    * @param contractId the contract ID of this customer gts actual
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _customerGtsActual.setContractId(contractId);
    }

    /**
    * Returns the account ID of this customer gts actual.
    *
    * @return the account ID of this customer gts actual
    */
    @Override
    public java.lang.String getAccountId() {
        return _customerGtsActual.getAccountId();
    }

    /**
    * Sets the account ID of this customer gts actual.
    *
    * @param accountId the account ID of this customer gts actual
    */
    @Override
    public void setAccountId(java.lang.String accountId) {
        _customerGtsActual.setAccountId(accountId);
    }

    /**
    * Returns the customer gts actual sid of this customer gts actual.
    *
    * @return the customer gts actual sid of this customer gts actual
    */
    @Override
    public int getCustomerGtsActualSid() {
        return _customerGtsActual.getCustomerGtsActualSid();
    }

    /**
    * Sets the customer gts actual sid of this customer gts actual.
    *
    * @param customerGtsActualSid the customer gts actual sid of this customer gts actual
    */
    @Override
    public void setCustomerGtsActualSid(int customerGtsActualSid) {
        _customerGtsActual.setCustomerGtsActualSid(customerGtsActualSid);
    }

    /**
    * Returns the order received date of this customer gts actual.
    *
    * @return the order received date of this customer gts actual
    */
    @Override
    public java.util.Date getOrderReceivedDate() {
        return _customerGtsActual.getOrderReceivedDate();
    }

    /**
    * Sets the order received date of this customer gts actual.
    *
    * @param orderReceivedDate the order received date of this customer gts actual
    */
    @Override
    public void setOrderReceivedDate(java.util.Date orderReceivedDate) {
        _customerGtsActual.setOrderReceivedDate(orderReceivedDate);
    }

    /**
    * Returns the item ID of this customer gts actual.
    *
    * @return the item ID of this customer gts actual
    */
    @Override
    public java.lang.String getItemId() {
        return _customerGtsActual.getItemId();
    }

    /**
    * Sets the item ID of this customer gts actual.
    *
    * @param itemId the item ID of this customer gts actual
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _customerGtsActual.setItemId(itemId);
    }

    /**
    * Returns the modified date of this customer gts actual.
    *
    * @return the modified date of this customer gts actual
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _customerGtsActual.getModifiedDate();
    }

    /**
    * Sets the modified date of this customer gts actual.
    *
    * @param modifiedDate the modified date of this customer gts actual
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _customerGtsActual.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the amount of this customer gts actual.
    *
    * @return the amount of this customer gts actual
    */
    @Override
    public double getAmount() {
        return _customerGtsActual.getAmount();
    }

    /**
    * Sets the amount of this customer gts actual.
    *
    * @param amount the amount of this customer gts actual
    */
    @Override
    public void setAmount(double amount) {
        _customerGtsActual.setAmount(amount);
    }

    /**
    * Returns the order number of this customer gts actual.
    *
    * @return the order number of this customer gts actual
    */
    @Override
    public java.lang.String getOrderNumber() {
        return _customerGtsActual.getOrderNumber();
    }

    /**
    * Sets the order number of this customer gts actual.
    *
    * @param orderNumber the order number of this customer gts actual
    */
    @Override
    public void setOrderNumber(java.lang.String orderNumber) {
        _customerGtsActual.setOrderNumber(orderNumber);
    }

    /**
    * Returns the organization key of this customer gts actual.
    *
    * @return the organization key of this customer gts actual
    */
    @Override
    public java.lang.String getOrganizationKey() {
        return _customerGtsActual.getOrganizationKey();
    }

    /**
    * Sets the organization key of this customer gts actual.
    *
    * @param organizationKey the organization key of this customer gts actual
    */
    @Override
    public void setOrganizationKey(java.lang.String organizationKey) {
        _customerGtsActual.setOrganizationKey(organizationKey);
    }

    /**
    * Returns the invoice date of this customer gts actual.
    *
    * @return the invoice date of this customer gts actual
    */
    @Override
    public java.util.Date getInvoiceDate() {
        return _customerGtsActual.getInvoiceDate();
    }

    /**
    * Sets the invoice date of this customer gts actual.
    *
    * @param invoiceDate the invoice date of this customer gts actual
    */
    @Override
    public void setInvoiceDate(java.util.Date invoiceDate) {
        _customerGtsActual.setInvoiceDate(invoiceDate);
    }

    /**
    * Returns the customer gts actual intf ID of this customer gts actual.
    *
    * @return the customer gts actual intf ID of this customer gts actual
    */
    @Override
    public int getCustomerGtsActualIntfId() {
        return _customerGtsActual.getCustomerGtsActualIntfId();
    }

    /**
    * Sets the customer gts actual intf ID of this customer gts actual.
    *
    * @param customerGtsActualIntfId the customer gts actual intf ID of this customer gts actual
    */
    @Override
    public void setCustomerGtsActualIntfId(int customerGtsActualIntfId) {
        _customerGtsActual.setCustomerGtsActualIntfId(customerGtsActualIntfId);
    }

    /**
    * Returns the created date of this customer gts actual.
    *
    * @return the created date of this customer gts actual
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _customerGtsActual.getCreatedDate();
    }

    /**
    * Sets the created date of this customer gts actual.
    *
    * @param createdDate the created date of this customer gts actual
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _customerGtsActual.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this customer gts actual.
    *
    * @return the created by of this customer gts actual
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _customerGtsActual.getCreatedBy();
    }

    /**
    * Sets the created by of this customer gts actual.
    *
    * @param createdBy the created by of this customer gts actual
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _customerGtsActual.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this customer gts actual.
    *
    * @return the source of this customer gts actual
    */
    @Override
    public java.lang.String getSource() {
        return _customerGtsActual.getSource();
    }

    /**
    * Sets the source of this customer gts actual.
    *
    * @param source the source of this customer gts actual
    */
    @Override
    public void setSource(java.lang.String source) {
        _customerGtsActual.setSource(source);
    }

    /**
    * Returns the batch ID of this customer gts actual.
    *
    * @return the batch ID of this customer gts actual
    */
    @Override
    public java.lang.String getBatchId() {
        return _customerGtsActual.getBatchId();
    }

    /**
    * Sets the batch ID of this customer gts actual.
    *
    * @param batchId the batch ID of this customer gts actual
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _customerGtsActual.setBatchId(batchId);
    }

    /**
    * Returns the sales ID of this customer gts actual.
    *
    * @return the sales ID of this customer gts actual
    */
    @Override
    public java.lang.String getSalesId() {
        return _customerGtsActual.getSalesId();
    }

    /**
    * Sets the sales ID of this customer gts actual.
    *
    * @param salesId the sales ID of this customer gts actual
    */
    @Override
    public void setSalesId(java.lang.String salesId) {
        _customerGtsActual.setSalesId(salesId);
    }

    /**
    * Returns the item uom of this customer gts actual.
    *
    * @return the item uom of this customer gts actual
    */
    @Override
    public java.lang.String getItemUom() {
        return _customerGtsActual.getItemUom();
    }

    /**
    * Sets the item uom of this customer gts actual.
    *
    * @param itemUom the item uom of this customer gts actual
    */
    @Override
    public void setItemUom(java.lang.String itemUom) {
        _customerGtsActual.setItemUom(itemUom);
    }

    /**
    * Returns the inbound status of this customer gts actual.
    *
    * @return the inbound status of this customer gts actual
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _customerGtsActual.getInboundStatus();
    }

    /**
    * Sets the inbound status of this customer gts actual.
    *
    * @param inboundStatus the inbound status of this customer gts actual
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _customerGtsActual.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the modified by of this customer gts actual.
    *
    * @return the modified by of this customer gts actual
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _customerGtsActual.getModifiedBy();
    }

    /**
    * Sets the modified by of this customer gts actual.
    *
    * @param modifiedBy the modified by of this customer gts actual
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _customerGtsActual.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the invoice number of this customer gts actual.
    *
    * @return the invoice number of this customer gts actual
    */
    @Override
    public java.lang.String getInvoiceNumber() {
        return _customerGtsActual.getInvoiceNumber();
    }

    /**
    * Sets the invoice number of this customer gts actual.
    *
    * @param invoiceNumber the invoice number of this customer gts actual
    */
    @Override
    public void setInvoiceNumber(java.lang.String invoiceNumber) {
        _customerGtsActual.setInvoiceNumber(invoiceNumber);
    }

    /**
    * Returns the lot no of this customer gts actual.
    *
    * @return the lot no of this customer gts actual
    */
    @Override
    public java.lang.String getLotNo() {
        return _customerGtsActual.getLotNo();
    }

    /**
    * Sets the lot no of this customer gts actual.
    *
    * @param lotNo the lot no of this customer gts actual
    */
    @Override
    public void setLotNo(java.lang.String lotNo) {
        _customerGtsActual.setLotNo(lotNo);
    }

    /**
    * Returns the invoice line number of this customer gts actual.
    *
    * @return the invoice line number of this customer gts actual
    */
    @Override
    public java.lang.String getInvoiceLineNumber() {
        return _customerGtsActual.getInvoiceLineNumber();
    }

    /**
    * Sets the invoice line number of this customer gts actual.
    *
    * @param invoiceLineNumber the invoice line number of this customer gts actual
    */
    @Override
    public void setInvoiceLineNumber(java.lang.String invoiceLineNumber) {
        _customerGtsActual.setInvoiceLineNumber(invoiceLineNumber);
    }

    /**
    * Returns the quantity of this customer gts actual.
    *
    * @return the quantity of this customer gts actual
    */
    @Override
    public double getQuantity() {
        return _customerGtsActual.getQuantity();
    }

    /**
    * Sets the quantity of this customer gts actual.
    *
    * @param quantity the quantity of this customer gts actual
    */
    @Override
    public void setQuantity(double quantity) {
        _customerGtsActual.setQuantity(quantity);
    }

    @Override
    public boolean isNew() {
        return _customerGtsActual.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _customerGtsActual.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _customerGtsActual.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _customerGtsActual.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _customerGtsActual.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _customerGtsActual.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _customerGtsActual.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _customerGtsActual.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _customerGtsActual.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _customerGtsActual.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _customerGtsActual.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CustomerGtsActualWrapper((CustomerGtsActual) _customerGtsActual.clone());
    }

    @Override
    public int compareTo(CustomerGtsActual customerGtsActual) {
        return _customerGtsActual.compareTo(customerGtsActual);
    }

    @Override
    public int hashCode() {
        return _customerGtsActual.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CustomerGtsActual> toCacheModel() {
        return _customerGtsActual.toCacheModel();
    }

    @Override
    public CustomerGtsActual toEscapedModel() {
        return new CustomerGtsActualWrapper(_customerGtsActual.toEscapedModel());
    }

    @Override
    public CustomerGtsActual toUnescapedModel() {
        return new CustomerGtsActualWrapper(_customerGtsActual.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _customerGtsActual.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _customerGtsActual.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _customerGtsActual.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CustomerGtsActualWrapper)) {
            return false;
        }

        CustomerGtsActualWrapper customerGtsActualWrapper = (CustomerGtsActualWrapper) obj;

        if (Validator.equals(_customerGtsActual,
                    customerGtsActualWrapper._customerGtsActual)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CustomerGtsActual getWrappedCustomerGtsActual() {
        return _customerGtsActual;
    }

    @Override
    public CustomerGtsActual getWrappedModel() {
        return _customerGtsActual;
    }

    @Override
    public void resetOriginalValues() {
        _customerGtsActual.resetOriginalValues();
    }
}
