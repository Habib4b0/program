package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.IvldItemPricingLocalServiceUtil;

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


public class IvldItemPricingClp extends BaseModelImpl<IvldItemPricing>
    implements IvldItemPricing {
    private String _itemNo;
    private String _modifiedBy;
    private String _pricingCodeQualifierName;
    private Date _createdDate;
    private String _endDate;
    private String _batchId;
    private String _itemName;
    private String _errorCode;
    private String _reprocessedFlag;
    private String _itemPricingIntfid;
    private int _ivldItemPricingSid;
    private String _pricingCodeStatus;
    private String _createdBy;
    private String _itemId;
    private String _errorField;
    private String _startDate;
    private String _itemUom;
    private Date _modifiedDate;
    private String _reasonForFailure;
    private String _source;
    private String _pricingCodeQualifier;
    private String _addChgDelIndicator;
    private String _entityCode;
    private String _itemPrice;
    private Date _intfInsertedDate;
    private boolean _checkRecord;
    private BaseModel<?> _ivldItemPricingRemoteModel;

    public IvldItemPricingClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldItemPricing.class;
    }

    @Override
    public String getModelClassName() {
        return IvldItemPricing.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldItemPricingSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldItemPricingSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldItemPricingSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemNo", getItemNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("pricingCodeQualifierName", getPricingCodeQualifierName());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("endDate", getEndDate());
        attributes.put("batchId", getBatchId());
        attributes.put("itemName", getItemName());
        attributes.put("errorCode", getErrorCode());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("itemPricingIntfid", getItemPricingIntfid());
        attributes.put("ivldItemPricingSid", getIvldItemPricingSid());
        attributes.put("pricingCodeStatus", getPricingCodeStatus());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemId", getItemId());
        attributes.put("errorField", getErrorField());
        attributes.put("startDate", getStartDate());
        attributes.put("itemUom", getItemUom());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("source", getSource());
        attributes.put("pricingCodeQualifier", getPricingCodeQualifier());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("entityCode", getEntityCode());
        attributes.put("itemPrice", getItemPrice());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String pricingCodeQualifierName = (String) attributes.get(
                "pricingCodeQualifierName");

        if (pricingCodeQualifierName != null) {
            setPricingCodeQualifierName(pricingCodeQualifierName);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String endDate = (String) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String itemPricingIntfid = (String) attributes.get("itemPricingIntfid");

        if (itemPricingIntfid != null) {
            setItemPricingIntfid(itemPricingIntfid);
        }

        Integer ivldItemPricingSid = (Integer) attributes.get(
                "ivldItemPricingSid");

        if (ivldItemPricingSid != null) {
            setIvldItemPricingSid(ivldItemPricingSid);
        }

        String pricingCodeStatus = (String) attributes.get("pricingCodeStatus");

        if (pricingCodeStatus != null) {
            setPricingCodeStatus(pricingCodeStatus);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String startDate = (String) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String itemUom = (String) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String pricingCodeQualifier = (String) attributes.get(
                "pricingCodeQualifier");

        if (pricingCodeQualifier != null) {
            setPricingCodeQualifier(pricingCodeQualifier);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        String itemPrice = (String) attributes.get("itemPrice");

        if (itemPrice != null) {
            setItemPrice(itemPrice);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getItemNo() {
        return _itemNo;
    }

    @Override
    public void setItemNo(String itemNo) {
        _itemNo = itemNo;

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_ivldItemPricingRemoteModel, itemNo);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldItemPricingRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPricingCodeQualifierName() {
        return _pricingCodeQualifierName;
    }

    @Override
    public void setPricingCodeQualifierName(String pricingCodeQualifierName) {
        _pricingCodeQualifierName = pricingCodeQualifierName;

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setPricingCodeQualifierName",
                        String.class);

                method.invoke(_ivldItemPricingRemoteModel,
                    pricingCodeQualifierName);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldItemPricingRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(String endDate) {
        _endDate = endDate;

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", String.class);

                method.invoke(_ivldItemPricingRemoteModel, endDate);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldItemPricingRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemName() {
        return _itemName;
    }

    @Override
    public void setItemName(String itemName) {
        _itemName = itemName;

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_ivldItemPricingRemoteModel, itemName);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldItemPricingRemoteModel, errorCode);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldItemPricingRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemPricingIntfid() {
        return _itemPricingIntfid;
    }

    @Override
    public void setItemPricingIntfid(String itemPricingIntfid) {
        _itemPricingIntfid = itemPricingIntfid;

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPricingIntfid",
                        String.class);

                method.invoke(_ivldItemPricingRemoteModel, itemPricingIntfid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldItemPricingSid() {
        return _ivldItemPricingSid;
    }

    @Override
    public void setIvldItemPricingSid(int ivldItemPricingSid) {
        _ivldItemPricingSid = ivldItemPricingSid;

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldItemPricingSid",
                        int.class);

                method.invoke(_ivldItemPricingRemoteModel, ivldItemPricingSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPricingCodeStatus() {
        return _pricingCodeStatus;
    }

    @Override
    public void setPricingCodeStatus(String pricingCodeStatus) {
        _pricingCodeStatus = pricingCodeStatus;

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setPricingCodeStatus",
                        String.class);

                method.invoke(_ivldItemPricingRemoteModel, pricingCodeStatus);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldItemPricingRemoteModel, createdBy);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldItemPricingRemoteModel, itemId);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldItemPricingRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStartDate() {
        return _startDate;
    }

    @Override
    public void setStartDate(String startDate) {
        _startDate = startDate;

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", String.class);

                method.invoke(_ivldItemPricingRemoteModel, startDate);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemUom", String.class);

                method.invoke(_ivldItemPricingRemoteModel, itemUom);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldItemPricingRemoteModel, modifiedDate);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldItemPricingRemoteModel, reasonForFailure);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldItemPricingRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPricingCodeQualifier() {
        return _pricingCodeQualifier;
    }

    @Override
    public void setPricingCodeQualifier(String pricingCodeQualifier) {
        _pricingCodeQualifier = pricingCodeQualifier;

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setPricingCodeQualifier",
                        String.class);

                method.invoke(_ivldItemPricingRemoteModel, pricingCodeQualifier);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldItemPricingRemoteModel, addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEntityCode() {
        return _entityCode;
    }

    @Override
    public void setEntityCode(String entityCode) {
        _entityCode = entityCode;

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setEntityCode", String.class);

                method.invoke(_ivldItemPricingRemoteModel, entityCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemPrice() {
        return _itemPrice;
    }

    @Override
    public void setItemPrice(String itemPrice) {
        _itemPrice = itemPrice;

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPrice", String.class);

                method.invoke(_ivldItemPricingRemoteModel, itemPrice);
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

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldItemPricingRemoteModel, intfInsertedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;

        if (_ivldItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _ivldItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldItemPricingRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldItemPricingRemoteModel() {
        return _ivldItemPricingRemoteModel;
    }

    public void setIvldItemPricingRemoteModel(
        BaseModel<?> ivldItemPricingRemoteModel) {
        _ivldItemPricingRemoteModel = ivldItemPricingRemoteModel;
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

        Class<?> remoteModelClass = _ivldItemPricingRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldItemPricingRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldItemPricingLocalServiceUtil.addIvldItemPricing(this);
        } else {
            IvldItemPricingLocalServiceUtil.updateIvldItemPricing(this);
        }
    }

    @Override
    public IvldItemPricing toEscapedModel() {
        return (IvldItemPricing) ProxyUtil.newProxyInstance(IvldItemPricing.class.getClassLoader(),
            new Class[] { IvldItemPricing.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldItemPricingClp clone = new IvldItemPricingClp();

        clone.setItemNo(getItemNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setPricingCodeQualifierName(getPricingCodeQualifierName());
        clone.setCreatedDate(getCreatedDate());
        clone.setEndDate(getEndDate());
        clone.setBatchId(getBatchId());
        clone.setItemName(getItemName());
        clone.setErrorCode(getErrorCode());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setItemPricingIntfid(getItemPricingIntfid());
        clone.setIvldItemPricingSid(getIvldItemPricingSid());
        clone.setPricingCodeStatus(getPricingCodeStatus());
        clone.setCreatedBy(getCreatedBy());
        clone.setItemId(getItemId());
        clone.setErrorField(getErrorField());
        clone.setStartDate(getStartDate());
        clone.setItemUom(getItemUom());
        clone.setModifiedDate(getModifiedDate());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setSource(getSource());
        clone.setPricingCodeQualifier(getPricingCodeQualifier());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setEntityCode(getEntityCode());
        clone.setItemPrice(getItemPrice());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldItemPricing ivldItemPricing) {
        int primaryKey = ivldItemPricing.getPrimaryKey();

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

        if (!(obj instanceof IvldItemPricingClp)) {
            return false;
        }

        IvldItemPricingClp ivldItemPricing = (IvldItemPricingClp) obj;

        int primaryKey = ivldItemPricing.getPrimaryKey();

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
        StringBundler sb = new StringBundler(53);

        sb.append("{itemNo=");
        sb.append(getItemNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", pricingCodeQualifierName=");
        sb.append(getPricingCodeQualifierName());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", itemPricingIntfid=");
        sb.append(getItemPricingIntfid());
        sb.append(", ivldItemPricingSid=");
        sb.append(getIvldItemPricingSid());
        sb.append(", pricingCodeStatus=");
        sb.append(getPricingCodeStatus());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", itemUom=");
        sb.append(getItemUom());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", pricingCodeQualifier=");
        sb.append(getPricingCodeQualifier());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", entityCode=");
        sb.append(getEntityCode());
        sb.append(", itemPrice=");
        sb.append(getItemPrice());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(82);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.IvldItemPricing");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pricingCodeQualifierName</column-name><column-value><![CDATA[");
        sb.append(getPricingCodeQualifierName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPricingIntfid</column-name><column-value><![CDATA[");
        sb.append(getItemPricingIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldItemPricingSid</column-name><column-value><![CDATA[");
        sb.append(getIvldItemPricingSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pricingCodeStatus</column-name><column-value><![CDATA[");
        sb.append(getPricingCodeStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemUom</column-name><column-value><![CDATA[");
        sb.append(getItemUom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pricingCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getPricingCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>entityCode</column-name><column-value><![CDATA[");
        sb.append(getEntityCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPrice</column-name><column-value><![CDATA[");
        sb.append(getItemPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
