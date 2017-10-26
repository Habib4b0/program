package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.CustomerGtsActualLocalServiceUtil;

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


public class CustomerGtsActualClp extends BaseModelImpl<CustomerGtsActual>
    implements CustomerGtsActual {
    private String _parentAccountId;
    private String _contractId;
    private String _accountId;
    private int _customerGtsActualSid;
    private Date _orderReceivedDate;
    private String _itemId;
    private Date _modifiedDate;
    private double _amount;
    private String _orderNumber;
    private String _organizationKey;
    private Date _invoiceDate;
    private int _customerGtsActualIntfId;
    private Date _createdDate;
    private String _createdBy;
    private String _source;
    private String _batchId;
    private String _salesId;
    private String _itemUom;
    private String _inboundStatus;
    private String _modifiedBy;
    private String _invoiceNumber;
    private String _lotNo;
    private String _invoiceLineNumber;
    private double _quantity;
    private BaseModel<?> _customerGtsActualRemoteModel;

    public CustomerGtsActualClp() {
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
    public int getPrimaryKey() {
        return _customerGtsActualSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCustomerGtsActualSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _customerGtsActualSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
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

    @Override
    public String getParentAccountId() {
        return _parentAccountId;
    }

    @Override
    public void setParentAccountId(String parentAccountId) {
        _parentAccountId = parentAccountId;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setParentAccountId",
                        String.class);

                method.invoke(_customerGtsActualRemoteModel, parentAccountId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractId() {
        return _contractId;
    }

    @Override
    public void setContractId(String contractId) {
        _contractId = contractId;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_customerGtsActualRemoteModel, contractId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountId() {
        return _accountId;
    }

    @Override
    public void setAccountId(String accountId) {
        _accountId = accountId;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_customerGtsActualRemoteModel, accountId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCustomerGtsActualSid() {
        return _customerGtsActualSid;
    }

    @Override
    public void setCustomerGtsActualSid(int customerGtsActualSid) {
        _customerGtsActualSid = customerGtsActualSid;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerGtsActualSid",
                        int.class);

                method.invoke(_customerGtsActualRemoteModel,
                    customerGtsActualSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getOrderReceivedDate() {
        return _orderReceivedDate;
    }

    @Override
    public void setOrderReceivedDate(Date orderReceivedDate) {
        _orderReceivedDate = orderReceivedDate;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setOrderReceivedDate",
                        Date.class);

                method.invoke(_customerGtsActualRemoteModel, orderReceivedDate);
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

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_customerGtsActualRemoteModel, itemId);
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

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_customerGtsActualRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAmount() {
        return _amount;
    }

    @Override
    public void setAmount(double amount) {
        _amount = amount;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setAmount", double.class);

                method.invoke(_customerGtsActualRemoteModel, amount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrderNumber() {
        return _orderNumber;
    }

    @Override
    public void setOrderNumber(String orderNumber) {
        _orderNumber = orderNumber;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setOrderNumber", String.class);

                method.invoke(_customerGtsActualRemoteModel, orderNumber);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrganizationKey() {
        return _organizationKey;
    }

    @Override
    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_customerGtsActualRemoteModel, organizationKey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getInvoiceDate() {
        return _invoiceDate;
    }

    @Override
    public void setInvoiceDate(Date invoiceDate) {
        _invoiceDate = invoiceDate;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceDate", Date.class);

                method.invoke(_customerGtsActualRemoteModel, invoiceDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCustomerGtsActualIntfId() {
        return _customerGtsActualIntfId;
    }

    @Override
    public void setCustomerGtsActualIntfId(int customerGtsActualIntfId) {
        _customerGtsActualIntfId = customerGtsActualIntfId;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerGtsActualIntfId",
                        int.class);

                method.invoke(_customerGtsActualRemoteModel,
                    customerGtsActualIntfId);
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

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_customerGtsActualRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_customerGtsActualRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSource() {
        return _source;
    }

    @Override
    public void setSource(String source) {
        _source = source;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_customerGtsActualRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBatchId() {
        return _batchId;
    }

    @Override
    public void setBatchId(String batchId) {
        _batchId = batchId;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_customerGtsActualRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSalesId() {
        return _salesId;
    }

    @Override
    public void setSalesId(String salesId) {
        _salesId = salesId;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesId", String.class);

                method.invoke(_customerGtsActualRemoteModel, salesId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemUom() {
        return _itemUom;
    }

    @Override
    public void setItemUom(String itemUom) {
        _itemUom = itemUom;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemUom", String.class);

                method.invoke(_customerGtsActualRemoteModel, itemUom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInboundStatus() {
        return _inboundStatus;
    }

    @Override
    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_customerGtsActualRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_customerGtsActualRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInvoiceNumber() {
        return _invoiceNumber;
    }

    @Override
    public void setInvoiceNumber(String invoiceNumber) {
        _invoiceNumber = invoiceNumber;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceNumber", String.class);

                method.invoke(_customerGtsActualRemoteModel, invoiceNumber);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLotNo() {
        return _lotNo;
    }

    @Override
    public void setLotNo(String lotNo) {
        _lotNo = lotNo;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setLotNo", String.class);

                method.invoke(_customerGtsActualRemoteModel, lotNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInvoiceLineNumber() {
        return _invoiceLineNumber;
    }

    @Override
    public void setInvoiceLineNumber(String invoiceLineNumber) {
        _invoiceLineNumber = invoiceLineNumber;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceLineNumber",
                        String.class);

                method.invoke(_customerGtsActualRemoteModel, invoiceLineNumber);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getQuantity() {
        return _quantity;
    }

    @Override
    public void setQuantity(double quantity) {
        _quantity = quantity;

        if (_customerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _customerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantity", double.class);

                method.invoke(_customerGtsActualRemoteModel, quantity);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCustomerGtsActualRemoteModel() {
        return _customerGtsActualRemoteModel;
    }

    public void setCustomerGtsActualRemoteModel(
        BaseModel<?> customerGtsActualRemoteModel) {
        _customerGtsActualRemoteModel = customerGtsActualRemoteModel;
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

        Class<?> remoteModelClass = _customerGtsActualRemoteModel.getClass();

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

        Object returnValue = method.invoke(_customerGtsActualRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CustomerGtsActualLocalServiceUtil.addCustomerGtsActual(this);
        } else {
            CustomerGtsActualLocalServiceUtil.updateCustomerGtsActual(this);
        }
    }

    @Override
    public CustomerGtsActual toEscapedModel() {
        return (CustomerGtsActual) ProxyUtil.newProxyInstance(CustomerGtsActual.class.getClassLoader(),
            new Class[] { CustomerGtsActual.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CustomerGtsActualClp clone = new CustomerGtsActualClp();

        clone.setParentAccountId(getParentAccountId());
        clone.setContractId(getContractId());
        clone.setAccountId(getAccountId());
        clone.setCustomerGtsActualSid(getCustomerGtsActualSid());
        clone.setOrderReceivedDate(getOrderReceivedDate());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setAmount(getAmount());
        clone.setOrderNumber(getOrderNumber());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setInvoiceDate(getInvoiceDate());
        clone.setCustomerGtsActualIntfId(getCustomerGtsActualIntfId());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setBatchId(getBatchId());
        clone.setSalesId(getSalesId());
        clone.setItemUom(getItemUom());
        clone.setInboundStatus(getInboundStatus());
        clone.setModifiedBy(getModifiedBy());
        clone.setInvoiceNumber(getInvoiceNumber());
        clone.setLotNo(getLotNo());
        clone.setInvoiceLineNumber(getInvoiceLineNumber());
        clone.setQuantity(getQuantity());

        return clone;
    }

    @Override
    public int compareTo(CustomerGtsActual customerGtsActual) {
        int primaryKey = customerGtsActual.getPrimaryKey();

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

        if (!(obj instanceof CustomerGtsActualClp)) {
            return false;
        }

        CustomerGtsActualClp customerGtsActual = (CustomerGtsActualClp) obj;

        int primaryKey = customerGtsActual.getPrimaryKey();

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
        StringBundler sb = new StringBundler(49);

        sb.append("{parentAccountId=");
        sb.append(getParentAccountId());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", accountId=");
        sb.append(getAccountId());
        sb.append(", customerGtsActualSid=");
        sb.append(getCustomerGtsActualSid());
        sb.append(", orderReceivedDate=");
        sb.append(getOrderReceivedDate());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", amount=");
        sb.append(getAmount());
        sb.append(", orderNumber=");
        sb.append(getOrderNumber());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", invoiceDate=");
        sb.append(getInvoiceDate());
        sb.append(", customerGtsActualIntfId=");
        sb.append(getCustomerGtsActualIntfId());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", salesId=");
        sb.append(getSalesId());
        sb.append(", itemUom=");
        sb.append(getItemUom());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", invoiceNumber=");
        sb.append(getInvoiceNumber());
        sb.append(", lotNo=");
        sb.append(getLotNo());
        sb.append(", invoiceLineNumber=");
        sb.append(getInvoiceLineNumber());
        sb.append(", quantity=");
        sb.append(getQuantity());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(76);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.CustomerGtsActual");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>parentAccountId</column-name><column-value><![CDATA[");
        sb.append(getParentAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountId</column-name><column-value><![CDATA[");
        sb.append(getAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerGtsActualSid</column-name><column-value><![CDATA[");
        sb.append(getCustomerGtsActualSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderReceivedDate</column-name><column-value><![CDATA[");
        sb.append(getOrderReceivedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amount</column-name><column-value><![CDATA[");
        sb.append(getAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderNumber</column-name><column-value><![CDATA[");
        sb.append(getOrderNumber());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invoiceDate</column-name><column-value><![CDATA[");
        sb.append(getInvoiceDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerGtsActualIntfId</column-name><column-value><![CDATA[");
        sb.append(getCustomerGtsActualIntfId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesId</column-name><column-value><![CDATA[");
        sb.append(getSalesId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemUom</column-name><column-value><![CDATA[");
        sb.append(getItemUom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invoiceNumber</column-name><column-value><![CDATA[");
        sb.append(getInvoiceNumber());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lotNo</column-name><column-value><![CDATA[");
        sb.append(getLotNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invoiceLineNumber</column-name><column-value><![CDATA[");
        sb.append(getInvoiceLineNumber());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>quantity</column-name><column-value><![CDATA[");
        sb.append(getQuantity());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
