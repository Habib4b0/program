package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.IvldCustomerGtsActualLocalServiceUtil;

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


public class IvldCustomerGtsActualClp extends BaseModelImpl<IvldCustomerGtsActual>
    implements IvldCustomerGtsActual {
    private String _parentAccountId;
    private int _ivldCustomerGtsActualSid;
    private String _accountId;
    private String _itemId;
    private Date _orderReceivedDate;
    private Date _modifiedDate;
    private String _orderNumber;
    private String _organizationKey;
    private String _source;
    private String _createdBy;
    private String _createdDate;
    private String _addChgDelIndicator;
    private String _errorCode;
    private String _itemUom;
    private String _invoiceNumber;
    private String _modifiedBy;
    private Date _intfInsertedDate;
    private String _lotNo;
    private String _reprocessedFlag;
    private String _quantity;
    private String _invoiceLineNumber;
    private String _contractId;
    private String _reasonForFailure;
    private String _amount;
    private Date _invoiceDate;
    private String _customerGtsActualIntfId;
    private String _batchId;
    private String _salesId;
    private String _errorField;
    private BaseModel<?> _ivldCustomerGtsActualRemoteModel;

    public IvldCustomerGtsActualClp() {
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
    public int getPrimaryKey() {
        return _ivldCustomerGtsActualSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldCustomerGtsActualSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldCustomerGtsActualSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
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
    }

    @Override
    public String getParentAccountId() {
        return _parentAccountId;
    }

    @Override
    public void setParentAccountId(String parentAccountId) {
        _parentAccountId = parentAccountId;

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setParentAccountId",
                        String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, parentAccountId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldCustomerGtsActualSid() {
        return _ivldCustomerGtsActualSid;
    }

    @Override
    public void setIvldCustomerGtsActualSid(int ivldCustomerGtsActualSid) {
        _ivldCustomerGtsActualSid = ivldCustomerGtsActualSid;

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldCustomerGtsActualSid",
                        int.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel,
                    ivldCustomerGtsActualSid);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, accountId);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, itemId);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setOrderReceivedDate",
                        Date.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel,
                    orderReceivedDate);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, modifiedDate);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setOrderNumber", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, orderNumber);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, organizationKey);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, source);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(String createdDate) {
        _createdDate = createdDate;

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    @Override
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel,
                    addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorCode() {
        return _errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, errorCode);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setItemUom", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, itemUom);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceNumber", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, invoiceNumber);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    @Override
    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel,
                    intfInsertedDate);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setLotNo", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, lotNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    @Override
    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getQuantity() {
        return _quantity;
    }

    @Override
    public void setQuantity(String quantity) {
        _quantity = quantity;

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantity", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, quantity);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceLineNumber",
                        String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel,
                    invoiceLineNumber);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, contractId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel,
                    reasonForFailure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAmount() {
        return _amount;
    }

    @Override
    public void setAmount(String amount) {
        _amount = amount;

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setAmount", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, amount);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceDate", Date.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, invoiceDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustomerGtsActualIntfId() {
        return _customerGtsActualIntfId;
    }

    @Override
    public void setCustomerGtsActualIntfId(String customerGtsActualIntfId) {
        _customerGtsActualIntfId = customerGtsActualIntfId;

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerGtsActualIntfId",
                        String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel,
                    customerGtsActualIntfId);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, batchId);
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

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesId", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, salesId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorField() {
        return _errorField;
    }

    @Override
    public void setErrorField(String errorField) {
        _errorField = errorField;

        if (_ivldCustomerGtsActualRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCustomerGtsActualRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldCustomerGtsActualRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldCustomerGtsActualRemoteModel() {
        return _ivldCustomerGtsActualRemoteModel;
    }

    public void setIvldCustomerGtsActualRemoteModel(
        BaseModel<?> ivldCustomerGtsActualRemoteModel) {
        _ivldCustomerGtsActualRemoteModel = ivldCustomerGtsActualRemoteModel;
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

        Class<?> remoteModelClass = _ivldCustomerGtsActualRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldCustomerGtsActualRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldCustomerGtsActualLocalServiceUtil.addIvldCustomerGtsActual(this);
        } else {
            IvldCustomerGtsActualLocalServiceUtil.updateIvldCustomerGtsActual(this);
        }
    }

    @Override
    public IvldCustomerGtsActual toEscapedModel() {
        return (IvldCustomerGtsActual) ProxyUtil.newProxyInstance(IvldCustomerGtsActual.class.getClassLoader(),
            new Class[] { IvldCustomerGtsActual.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldCustomerGtsActualClp clone = new IvldCustomerGtsActualClp();

        clone.setParentAccountId(getParentAccountId());
        clone.setIvldCustomerGtsActualSid(getIvldCustomerGtsActualSid());
        clone.setAccountId(getAccountId());
        clone.setItemId(getItemId());
        clone.setOrderReceivedDate(getOrderReceivedDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setOrderNumber(getOrderNumber());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setErrorCode(getErrorCode());
        clone.setItemUom(getItemUom());
        clone.setInvoiceNumber(getInvoiceNumber());
        clone.setModifiedBy(getModifiedBy());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setLotNo(getLotNo());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setQuantity(getQuantity());
        clone.setInvoiceLineNumber(getInvoiceLineNumber());
        clone.setContractId(getContractId());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setAmount(getAmount());
        clone.setInvoiceDate(getInvoiceDate());
        clone.setCustomerGtsActualIntfId(getCustomerGtsActualIntfId());
        clone.setBatchId(getBatchId());
        clone.setSalesId(getSalesId());
        clone.setErrorField(getErrorField());

        return clone;
    }

    @Override
    public int compareTo(IvldCustomerGtsActual ivldCustomerGtsActual) {
        int primaryKey = ivldCustomerGtsActual.getPrimaryKey();

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

        if (!(obj instanceof IvldCustomerGtsActualClp)) {
            return false;
        }

        IvldCustomerGtsActualClp ivldCustomerGtsActual = (IvldCustomerGtsActualClp) obj;

        int primaryKey = ivldCustomerGtsActual.getPrimaryKey();

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
        StringBundler sb = new StringBundler(59);

        sb.append("{parentAccountId=");
        sb.append(getParentAccountId());
        sb.append(", ivldCustomerGtsActualSid=");
        sb.append(getIvldCustomerGtsActualSid());
        sb.append(", accountId=");
        sb.append(getAccountId());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", orderReceivedDate=");
        sb.append(getOrderReceivedDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", orderNumber=");
        sb.append(getOrderNumber());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", itemUom=");
        sb.append(getItemUom());
        sb.append(", invoiceNumber=");
        sb.append(getInvoiceNumber());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", lotNo=");
        sb.append(getLotNo());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", quantity=");
        sb.append(getQuantity());
        sb.append(", invoiceLineNumber=");
        sb.append(getInvoiceLineNumber());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", amount=");
        sb.append(getAmount());
        sb.append(", invoiceDate=");
        sb.append(getInvoiceDate());
        sb.append(", customerGtsActualIntfId=");
        sb.append(getCustomerGtsActualIntfId());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", salesId=");
        sb.append(getSalesId());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(91);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.IvldCustomerGtsActual");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>parentAccountId</column-name><column-value><![CDATA[");
        sb.append(getParentAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldCustomerGtsActualSid</column-name><column-value><![CDATA[");
        sb.append(getIvldCustomerGtsActualSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountId</column-name><column-value><![CDATA[");
        sb.append(getAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderReceivedDate</column-name><column-value><![CDATA[");
        sb.append(getOrderReceivedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemUom</column-name><column-value><![CDATA[");
        sb.append(getItemUom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invoiceNumber</column-name><column-value><![CDATA[");
        sb.append(getInvoiceNumber());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lotNo</column-name><column-value><![CDATA[");
        sb.append(getLotNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>quantity</column-name><column-value><![CDATA[");
        sb.append(getQuantity());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invoiceLineNumber</column-name><column-value><![CDATA[");
        sb.append(getInvoiceLineNumber());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amount</column-name><column-value><![CDATA[");
        sb.append(getAmount());
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
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesId</column-name><column-value><![CDATA[");
        sb.append(getSalesId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
